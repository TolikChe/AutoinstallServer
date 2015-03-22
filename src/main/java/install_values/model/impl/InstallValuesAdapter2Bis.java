package install_values.model.impl;

import install_values.model.InstallValues;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Anatoly.Cherkasov on 16.01.2015.
 * Класс описывает файл CMS_INSTALL_VALUES.sql для подсистемы SCR_CMS
 */
public class InstallValuesAdapter2Bis implements InstallValues {

    // Объявляем переменную логгера
    private Logger log = LoggerFactory.getLogger(InstallValuesAdapter2Bis.class);

    /**
     * Имя файла конфигурации
     */
    private String filename;
    /**
     * Имя системного пользователя
     */
    private String sysUser;
    /**
     * Пароль для системного пользователя
     */
    private String sysPswd;
    /**
     * Тип подключения
     */
    private String sysCntp;
    /**
     * База на которой находится подсистема SCR_CMS
     */
    private String crmBase;
    /**
     * Имя схемы для подсистемы SCR_CMS
     */
    private String crmUser;
    /**
     * Пароль для схемы SCR_CMS
     */
    private String crmPswd;
    /**
     * Роль для схемы через которую работает CMS_ADMIN
     */
    private String appRole;
    /**
     * Схема через которую работает CMS_ADMIN
     */
    private String appUser;
    /**
     * Пароль для схемы через которую работает CMS_ADMIN
     */
    private String appPswd;
    /**
     * База на которой находится подсистема CMS_DS_ADAPTER2BIS
     */
    private String adapterBase;
    /**
     * Имя схемы для подсистемы CMS_DS_ADAPTER2BIS
     */
    private String adapterUser;
    /**
     * Пароль для схемы CMS_DS_ADAPTER2BIS
     */
    private String adapterPswd;
    /**
     * Кейнейм источника, с которым будет работать адаптер
     */
    private String sourceKeyName;
    /**
     * Имя тейблспейса для объектов адаптера
     */
    private String defTbs;
    /**
     * Имя тейблспейса для индексов адаптера
     */
    private String idxTbs;
    /**
     * База где стоит подсистема COMMON
     */
    private String scrCommonBase;
    /**
     * Схема COMMON
     */
    private String scrCommonUser;
    /**
     * Пароль для схемы COMMON
     */
    private String scrCommonPswd;
    /**
     * DBlink в схему подсистемы COMMON
     */
    private String crmToCommonLink;
    /**
     * База где стоит подсистема BIS
     */
    private String bisBase;
    /**
     * Схема BIS
     */
    private String bisSchema;
    /**
     * Пароль для схемы BIS
     */
    private String bisPswd;
    /**
     * DBlink в схему подсистемы BIS
     */
    private String dbLinkToBis;

    /**
     * Конструктор с проверкой существования файла
     *
     * @param filename Файл в котором находится конфиг подсистемы
     */
    public InstallValuesAdapter2Bis(String filename) throws Exception {

        File file = new File(filename);
        if (!(file.exists() && file.isFile())) {
            throw new Exception("Ошибка InstallValuesAdapter2Bis. Файл (" + file.getAbsoluteFile() + ") по указанному пути не найден ");
        }
        this.filename = file.getAbsoluteFile().toString();
    }

    /************************************************************************************/

    /**
     * Построим объект из строк. Распарсим строки в соответствии с реализацией класса
     *
     * @param stringList Список строк, которые будем разбирать
     */
    @Override
    public void fromString(List<String> stringList) throws Exception{
        // Сконструируем regexp выражение
        String pattern = "^DEFINE\\s+([A-Za-z]+)\\s+=\\s*([\\S']+\\s*[\\S']+)$";
        Pattern p = Pattern.compile(pattern);
        Matcher m;
        // Прочитаем файл по строкам
        try {
            for (String line : stringList) {
                if (line.contains("DEFINE")) {
                    m = p.matcher(line);
                    while (m.find()) {
                        if (m.group(1).equals("sysUser"))            { sysUser  = m.group(2); }
                        else if (m.group(1).equals("sysPswd"))       { sysPswd  = m.group(2); }
                        else if (m.group(1).equals("sysCntp"))       { sysCntp  = m.group(2); }
                        else if (m.group(1).equals("crmBase"))       { crmBase  = m.group(2); }
                        else if (m.group(1).equals("crmUser"))       { crmUser  = m.group(2); }
                        else if (m.group(1).equals("crmPswd"))       { crmPswd  = m.group(2); }
                        else if (m.group(1).equals("appRole"))       { appRole  = m.group(2); }
                        else if (m.group(1).equals("appUser"))       { appUser  = m.group(2); }
                        else if (m.group(1).equals("appPswd"))       { appPswd  = m.group(2); }
                        else if (m.group(1).equals("adapterBase"))   { adapterBase  = m.group(2); }
                        else if (m.group(1).equals("adapterUser"))   { adapterUser  = m.group(2); }
                        else if (m.group(1).equals("adapterPswd"))   { adapterPswd  = m.group(2); }
                        else if (m.group(1).equals("sourceKeyName")) { sourceKeyName  = m.group(2); }
                        else if (m.group(1).equals("defTbs"))        { defTbs  = m.group(2); }
                        else if (m.group(1).equals("idxTbs"))        { idxTbs  = m.group(2); }
                        else if (m.group(1).equals("scrCommonBase")) { scrCommonBase  = m.group(2); }
                        else if (m.group(1).equals("scrCommonUser")) { scrCommonUser  = m.group(2); }
                        else if (m.group(1).equals("scrCommonPswd")) { scrCommonPswd  = m.group(2); }
                        else if (m.group(1).equals("crmToCommonLink")) { crmToCommonLink  = m.group(2); }
                        else if (m.group(1).equals("bisBase"))       { bisBase  = m.group(2); }
                        else if (m.group(1).equals("bisSchema"))     { bisSchema  = m.group(2); }
                        else if (m.group(1).equals("bisPswd"))       { bisPswd  = m.group(2); }
                        else if (m.group(1).equals("dbLinkToBis"))   { dbLinkToBis  = m.group(2); }
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("Ошибка при разборе содержимого файла cms_install_values для подсистемы CMS_DS_ADAPTER.\n Имя файла: " + filename);
        }
    }

    public String toString(){
        // Строим строку
        StringBuilder sb = new StringBuilder();
        sb.append("/**********************************************\n" +
                "*             BASE SYS identification           *\n" +
                "***********************************************/\n");

        sb.append("-- Name of system administrator user\n");
        sb.append("DEFINE sysUser = ").append(sysUser).append("\n");
        sb.append("-- System administrator password\n");
        sb.append("DEFINE sysPswd = ").append(sysPswd).append("\n");
        sb.append("-- Type of connect for System administrator\n");
        sb.append("DEFINE sysCntp = ").append(sysCntp).append("\n\n");

        sb.append("/**********************************************\n" +
                "*            BASE where CMS has installed       *\n" +
                "***********************************************/\n");
        sb.append("-- CRM database name\n");
        sb.append("DEFINE crmBase = ").append(crmBase).append("\n");
        sb.append("-- Name of CRM schema\n");
        sb.append("DEFINE crmUser = ").append(crmUser).append("\n");
        sb.append("-- CRM user password\n");
        sb.append("DEFINE crmPswd = ").append(crmPswd).append("\n");
        sb.append("-- Application Role name\n");
        sb.append("DEFINE appRole = ").append(appRole).append("\n\n");
        sb.append("-- Application User name\n");
        sb.append("DEFINE appUser = ").append(appUser).append("\n");
        sb.append("-- Application User Password\n");
        sb.append("DEFINE appPswd = ").append(appPswd).append("\n\n");

        sb.append("/****************************************************\n" +
                "*          BASE where ADAPTER has installed           *\n" +
                "*****************************************************/\n");
        sb.append("-- Adapter database name\n");
        sb.append("DEFINE adapterBase = ").append(adapterBase).append("\n");
        sb.append("-- Adapter schema name\n");
        sb.append("DEFINE adapterUser = ").append(adapterUser).append("\n");
        sb.append("-- Adapter password\n");
        sb.append("DEFINE adapterPswd = ").append(adapterPswd).append("\n");

        sb.append("DEFINE sourceKeyName = ").append(sourceKeyName).append("\n");

        sb.append("-- default tablespace for ADAPTER\n");
        sb.append("DEFINE defTbs = ").append(defTbs).append("\n");
        sb.append("-- index tablespace for ADAPTER\n");
        sb.append("DEFINE idxTbs = ").append(idxTbs).append("\n");

        sb.append("/****************************************************\n" +
                "*        BASE where SCR_COMMON has installed        *\n" +
                "*****************************************************/\n");
        sb.append("--SCR_COMMON base\n");
        sb.append("DEFINE scrCommonBase = ").append(scrCommonBase).append("\n");
        sb.append("--SCR_COMMON scheme name\n");
        sb.append("DEFINE scrCommonUser = ").append(scrCommonUser).append("\n");
        sb.append("--SCR_COMMON user password\n");
        sb.append("DEFINE scrCommonPswd = ").append(scrCommonPswd).append("\n");
        sb.append("-- DbLink from CMS schema To SCR_COMMON schema\n");
        sb.append("DEFINE crmToCommonLink = ").append(crmToCommonLink).append("\n");

        sb.append("/****************************************************\n" +
                "*           BASE where BIS has installed              *\n" +
                "*****************************************************/\n");
        sb.append("-- BIS database name\n");
        sb.append("DEFINE bisBase = ").append(bisBase).append("\n");
        sb.append("-- BIS schema name\n");
        sb.append("DEFINE bisSchema = ").append(bisSchema).append("\n");
        sb.append("-- BIS password\n");
        sb.append("DEFINE bisPswd = ").append(bisPswd).append("\n");
        sb.append("-- Name of DB Link from ADAPTER to BIS\n");
        sb.append("DEFINE dbLinkToBis = ").append(crmToCommonLink).append("\n");

        // Возвращаем сконструированную строку
        return sb.toString();
    }

    /**
     * Проверка того что объект валиден по своему наполнению
     * @return TRUE - если успешно
     */
    public boolean validate(){
        if ( sysUser.equals("???") || sysUser == null) {
            log.error("Validate error: check sysUser");
            return false;
        }

        if (  sysPswd.equals("???") || sysPswd == null) {
            log.error("Validate error: check sysPswd");
            return false;
        }

        if (  sysCntp.equals("???") || sysCntp == null) {
            log.error("Validate error: check sysCntp");
            return false;
        }

        if ( crmBase.equals("???") || crmBase == null) {
            log.error("Validate error: check sysLanguage");
            return false;
        }

        if ( crmUser.equals("???") || crmUser == null) {
            log.error("Validate error: check crmUser");
            return false;
        }

        if ( crmPswd.equals("???") ||  crmPswd == null) {
            log.error("Validate error: check crmPswd");
            return false;
        }

        if ( defTbs.equals("???") || defTbs == null) {
            log.error("Validate error: check defTbs");
            return false;
        }

        if ( idxTbs.equals("???") || idxTbs == null) {
            log.error("Validate error: check idxTbs");
            return false;
        }

        if ( appRole.equals("???") || appRole == null) {
            log.error("Validate error: check appRole");
            return false;
        }

        if ( appUser.equals("???") || appUser == null) {
            log.error("Validate error: check appUser");
            return false;
        }

        if ( appPswd.equals("???") || appPswd == null) {
            log.error("Validate error: check appPswd");
            return false;
        }

        if ( adapterBase.equals("???") || adapterBase == null) {
            log.error("Validate error: check adapterBase");
            return false;
        }

        if ( adapterUser.equals("???") || adapterUser == null) {
            log.error("Validate error: check adapterUser");
            return false;
        }

        if ( adapterPswd.equals("???") || adapterPswd == null) {
            log.error("Validate error: check adapterPswd");
            return false;
        }

        if ( sourceKeyName.equals("???") || sourceKeyName == null) {
            log.error("Validate error: check sourceKeyName");
            return false;
        }

        if ( scrCommonUser.equals("???") || scrCommonUser == null) {
            log.error("Validate error: check scrCommonUser");
            return false;
        }

        if ( scrCommonPswd.equals("???") || scrCommonPswd == null) {
            log.error("Validate error: check scrCommonPswd");
            return false;
        }

        if ( crmToCommonLink.equals("???") || crmToCommonLink == null) {
            log.error("Validate error: check crmToCommonLink");
            return false;
        }

        if ( bisBase.equals("???") || bisBase == null) {
            log.error("Validate error: check bisBase");
            return false;
        }


        if ( bisSchema.equals("???") || bisSchema == null) {
            log.error("Validate error: check bisSchema");
            return false;
        }

        if ( bisPswd.equals("???") || bisPswd == null) {
            log.error("Validate error: check bisPswd");
            return false;
        }

        if ( dbLinkToBis.equals("???") || dbLinkToBis == null) {
            log.error("Validate error: check dbLinkToBis");
            return false;
        }

        return true;
    }

    /**
     * ***************************Геттеры и сеттеры****************************
     */

    public String getAppPswd() {
        return appPswd;
    }

    public void setAppPswd(String appPswd) {
        this.appPswd = appPswd;
    }

    public String getAppUser() {
        return appUser;
    }

    public void setAppUser(String appUser) {
        this.appUser = appUser;
    }

    public String getAppRole() {
        return appRole;
    }

    public void setAppRole(String appRole) {
        this.appRole = appRole;
    }

    public String getIdxTbs() {
        return idxTbs;
    }

    public void setIdxTbs(String idxTbs) {
        this.idxTbs = idxTbs;
    }

    public String getDefTbs() {
        return defTbs;
    }

    public void setDefTbs(String defTbs) {
        this.defTbs = defTbs;
    }

    public String getCrmPswd() {
        return crmPswd;
    }

    public void setCrmPswd(String crmPswd) {
        this.crmPswd = crmPswd;
    }

    public String getCrmUser() {
        return crmUser;
    }

    public void setCrmUser(String crmUser) {
        this.crmUser = crmUser;
    }

    public String getCrmBase() {
        return crmBase;
    }

    public void setCrmBase(String crmBase) {
        this.crmBase = crmBase;
    }

    public String getScrCommonBase() {
        return scrCommonBase;
    }

    public void setScrCommonBase(String scrCommonBase) {
        this.scrCommonBase = scrCommonBase;
    }

    public String getScrCommonUser() {
        return scrCommonUser;
    }

    public void setScrCommonUser(String scrCommonUser) {
        this.scrCommonUser = scrCommonUser;
    }

    public String getScrCommonPswd() {
        return scrCommonPswd;
    }

    public void setScrCommonPswd(String scrCommonPswd) {
        this.scrCommonPswd = scrCommonPswd;
    }

    public String getCrmToCommonLink() {
        return crmToCommonLink;
    }

    public void setCrmToCommonLink(String crmToCommonLink) {
        this.crmToCommonLink = crmToCommonLink;
    }

    public String getSysUser() {
        return sysUser;
    }

    public void setSysUser(String sysUser) {
        this.sysUser = sysUser;
    }

    public String getSysPswd() {
        return sysPswd;
    }

    public void setSysPswd(String sysPswd) {
        this.sysPswd = sysPswd;
    }

    public String getSysCntp() {
        return sysCntp;
    }

    public void setSysCntp(String sysCntp) {
        this.sysCntp = sysCntp;
    }

}
