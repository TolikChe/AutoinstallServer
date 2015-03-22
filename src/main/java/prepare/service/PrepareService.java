package prepare.service;

import config.model.CommonConfig;
import config.model.SubsystemConfig;
import install_values.model.impl.InstallValuesScrCms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.sql.*;
import java.util.*;

/**
 * Created by echo on 28.02.2015.
 */
public class PrepareService {

    // Объявляем переменную логгера
    private static Logger log = LoggerFactory.getLogger(PrepareService.class);

    /**
     * Процедура отключает любые подключения к указанной схеме
     * Используется при вызове prepareBase
     * @param connection Подключение к базе. на которую смотреть
     * @param schemaName Имя схемы, сессиии к которой надо рубить
     */
    private static Boolean killSessions (Connection connection, String schemaName) throws Exception{
        Boolean result = true;

        PreparedStatement pstmt = null;

        // Сюда скидаем команды для удаления сессий
        ArrayList<String> killSessionList = new ArrayList<String>();

        // Получим все сессии подключеныые к указанной схеме
        try {
            pstmt = connection.prepareStatement("select SID, SERIAL#, OSUSER, MACHINE, STATUS from v$session where username=?");
            pstmt.setString(1,schemaName);
            ResultSet rs = pstmt.executeQuery();
            log.info("Session list: " + schemaName);
            while (rs.next()) {
                String sid = rs.getString("SID");
                String serial = rs.getString("SERIAL#");
                String osuser = rs.getString("OSUSER");
                String machine = rs.getString("MACHINE");
                String status = rs.getString("STATUS");
                log.info(sid + "\t" + serial + "\t" + osuser + "\t" + machine + "\t" + status);

                //
                // Строим строки, которые будут удалять сессии
                killSessionList.add("alter system kill session '" + sid + ", " + serial + "'");
            }
        } catch (SQLException e ) {
            log.error("Ошибка в процессе получения инфрмации о сессиях");
            log.error(e.getMessage());
            result = false;
        } finally {
            if (pstmt != null) { pstmt.close(); }
        }
        //
        //
        // Все сессии которые нашли надо убить
        for (String sql : killSessionList) {
            try {
                log.debug(sql);
                pstmt = connection.prepareCall(sql);
                pstmt.execute();
            }
            catch ( SQLException ex ) {
                log.error("Ошибка в процессе удаления сессии: " + sql);
                log.error(ex.getMessage());
                result = false;
            } finally {
                if (pstmt != null) { pstmt.close(); }
            }
        }
        return result;
    }


    /**
     * Удаляем указанные схемы
     * Используется при вызове prepareBase
     * @param connection Соединение с базой
     * @param schemasArray Массив схем
     */
    private static Boolean dropSchemas(Connection connection, String[] schemasArray) throws Exception
    {
        PreparedStatement pstmt = null;
        String sql = null;
        Boolean result = true;

        for (String schema : schemasArray) {
            sql  = "DROP USER " + schema + " CASCADE";
            try {
                log.debug(sql);
                pstmt = connection.prepareCall(sql);
                pstmt.execute();
            }
            catch ( SQLException ex ) {
                log.error("Ошибка в процессе удаления схемы: " + sql);
                log.error(ex.getMessage());
                result = false;
            } finally {
                if (pstmt != null) { pstmt.close(); }
            }
        }

        return result;
    }

    /**
     * Удаляем указанные роли
     * Используется при вызове prepareBase*
     * @param connection Соединение с базой
     * @param rolesArray Массив ролей
     */
    private static Boolean dropRoles(Connection connection, String[] rolesArray) throws Exception
    {
        PreparedStatement pstmt = null;
        String sql = null;
        Boolean result = true;

        for (String role : rolesArray) {
            sql  = "DROP ROLE " + role;
            try {
                log.debug(sql);
                pstmt = connection.prepareCall(sql);
                pstmt.execute();
            }
            catch ( SQLException ex ) {
                log.error("Ошибка в процессе удаления роли: " + sql);
                log.error(ex.getMessage());
                result = false;
            } finally {
                if (pstmt != null) { pstmt.close(); }
            }
        }

        return result;
    }


    /**
     * Подготавливаем базу для установки новых схем.
     * Из файла с параметрами cms_install_values для подсистемы достаем значения всех схем и ролей и удаляем их
     * @param commonConfig Объект с полной конфигурацией
     * @return TRUE - если все успешно
     */
    public static boolean prepareBase (CommonConfig commonConfig) {

        Boolean result = true;

        InstallValuesScrCms installValuesScrCms = null;

        // Надем в конфиге первую подсистему SCR_CMS.
        // Возьмем её файл cms_install_values и передадим его как параметр.
        for ( SubsystemConfig subsystemConfig : commonConfig.getSubsystemConfigTreeMap().values()){
            if (subsystemConfig.getType().equals("SCR_CMS")) {
                installValuesScrCms = (InstallValuesScrCms)subsystemConfig.getInstallValues();
                break;
            }
        }

        //
        // Логирование
        log.debug("Connection parameters:");
        log.debug("user " + installValuesScrCms.getSysUser());
        log.debug("password " + installValuesScrCms.getSysPswd());
        log.debug("url " + "jdbc:oracle:thin:@" + commonConfig.getTns());
        //
        //
        /**
         * Подклюаемся к схеме сисом при помощи параметров указанных в файле настроек.
         */
        Properties props = new Properties();
        // Заполним параметры подключения
        props.put("user", installValuesScrCms.getSysUser());
        props.put("password", installValuesScrCms.getSysPswd());
        props.put("internal_logon", "sysdba");

        // Подготовим соединение
        Connection conn = null;
        try  {
            conn = DriverManager.getConnection("jdbc:oracle:thin:@" + commonConfig.getTns(), props);
            /**
             * Отключаем все сессии от указанной схемы
             */
            // CRM
            if (!killSessions(conn, installValuesScrCms.getCrmUser()))
                result = false;
            // APP
            if (!killSessions(conn, installValuesScrCms.getAppUser()))
                result = false;
            // AUTH
            if (!killSessions(conn, installValuesScrCms.getAuthUser()))
                result = false;
            /**
             * Удаляем заданные схемы
             */
            if (!dropSchemas(conn, new String[]{installValuesScrCms.getCrmUser(), installValuesScrCms.getAppUser(), installValuesScrCms.getAuthUser()}))
                result = false;
            /**
             * Удаляем заданные роли
             */
            if (!dropRoles(conn, new String[]{installValuesScrCms.getAppRole(), installValuesScrCms.getAuthRole(), installValuesScrCms.getApiRole()}))
                result = false;

            // Закончим все тем что закроем подключение
            conn.close();
        }
        catch (Exception e) {
            log.error("Неожиданная ошибка при подготовке базы к установке новых схем");
            log.error(e.getMessage());
            return false;
        }

        return result;
    }

    /**
     * Используется при вызове prepareFileSystem
     */
    private static void removeDirectory(File dir) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null && files.length > 0) {
                for (File aFile : files) {
                    removeDirectory(aFile);
                }
            }
            log.debug("Delete " + dir.getAbsolutePath());
            dir.delete();
        } else {
            log.debug("Delete " + dir.getAbsolutePath());
            dir.delete();
        }
    }

    /**
     * Подготовка файловой системы к клонированию новых дистрибутивов
     * Использует рекурсивный возов removeDirectory
     * @param config Объект с полной конфигурацией
     * @return TRUE если все успешно
     */
    public static boolean prepareFileSystem (CommonConfig config) {

        // Используется для подготовки перед клонированием
        try {
            log.info("Чистим файловую систему. Удаляем папку " + config.getDestinationDirectory());
            removeDirectory(new File(config.getDestinationDirectory()));
        } catch (Exception ex) {
            log.error("Ошибка при подготовке файловой системы");
            log.error("Попытка удалить папку " + config.getDestinationDirectory());
            log.error(ex.getMessage());
            log.error(Arrays.toString(ex.getStackTrace()));
            return false;
        }
        return true;
    }

}
