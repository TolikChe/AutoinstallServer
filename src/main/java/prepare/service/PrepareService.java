package prepare.service;

import config.model.CommonConfig;
import config.model.SubsystemConfig;
import install_values.model.impl.InstallValuesScrCms;

import java.io.File;
import java.sql.*;
import java.util.Properties;

/**
 * Created by echo on 28.02.2015.
 */
public class PrepareService {
    /**
     * Подготавливаем базу для установки новых схем.
     * Из файла с параметрами cms_install_values для подсистемы достаем значения всех схем и ролей и удаляем их
     * @param installValuesScrCms Параметры для установки для подсистема SCR_CMS
     * @return TRUE - если все успешно
     */
    public boolean prepareBase (InstallValuesScrCms installValuesScrCms) throws Exception {

        /**
         * Подклюаемся к схеме сисом при помощи параметров указанных в файле настроек.
         */
        Properties props = new Properties();
        props.put("user", installValuesScrCms.getSysUser());
        props.put("password", installValuesScrCms.getSysPswd());
        props.put("internal_logon", "sysdba");
        String url = "jdbc:oracle:thin:@"+installValuesScrCms.getCrmBase();
        Connection conn = DriverManager.getConnection(url, props);

        Statement stmt = null;
        String query = "select 'a' as NAME, 'b' as SURNAME from dual";
        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String name = rs.getString("NAME");
                String surname = rs.getString("SURNAME");
                System.out.println(name + "\t" + surname);
            }
        } catch (SQLException e ) {
            System.out.println(e.getMessage());
        } finally {
            if (stmt != null) { stmt.close(); }
        }

        /**
         * Отключаем все сессии от указанной схемы
         */

        /**
         * Удаляем заданные схемы
         */

        /**
         * Удаляем заданные роли
         */

        return true;
    }

    /**
     * Используется при вызове prepareFileSystem
     */
    private void removeDirectory(File dir) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null && files.length > 0) {
                for (File aFile : files) {
                    removeDirectory(aFile);
                }
            }
            dir.delete();
        } else {
            dir.delete();
        }
    }

    /**
     * Подготовка файловой системы к клонированию новых дистрибутивов
     * Использует рекурсивный возов removeDirectory
     * @param config Объект с полной конфигурацией
     * @return TRUE если все успешно
     */
    public boolean prepareFileSystem (CommonConfig config) {

        // Используется для подготовки перед клонированием
        try {
            removeDirectory(new File(config.getDestinationDirectory()));
        } catch (Exception ex) {
            System.out.println("Ошибка при подготовке файловой системы");
            System.out.println("попытка удалить папку " + config.getDestinationDirectory() );
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }
        return true;
    }

}
