package install_values.model.impl;

import install_values.model.InstallValues;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Anatoly.Cherkasov on 16.01.2015.
 * Класс описывает файл CMS_INSTALL_VALUES.sql для подсистемы SCR_CMS
 */
public class InstallValuesScrCms implements InstallValues {

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
     * Язык
     */
    private String sysLanguage;
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
     * Имя тейблспейса для объектов
     */
    private String defTbs;
    /**
     * Имя тейблспейса для индексов
     */
    private String idxTbs;
    /**
     * Роль для схемы аутентификации
     */
    private String authRole;
    /**
     * Роль для схемы через которую работает CMS_ADMIN
     */
    private String appRole;

    /**
     *
     */
    private String apiRole;
    /**
     * Схема для аутентификации
     */
    private String authUser;
    /**
     * Пароль для схемы аутентификации
     */
    private String authPswd;
    /**
     * Схема через которую работает CMS_ADMIN
     */
    private String appUser;
    /**
     * Пароль для схемы через которую работает CMS_ADMIN
     */
    private String appPswd;
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
     * Конструктор с проверкой существования файла
     *
     * @param filename Файл в котором находится конфиг подсистемы
     */
    public InstallValuesScrCms(String filename) throws Exception {

        File file = new File(filename);
        if (!(file.exists() && file.isFile())) {
            throw new Exception("Ошибка InstallValuesScrCms. Файл (" + file.getAbsoluteFile() + ") по указанному пути не найден ");
        }
        this.filename = file.getAbsoluteFile().toString();
    }

    /****************************************************************************/

    /**
     * Переопределим метод toString что бы на выходе получалась нужная строка, которую можно записать в файл
     * @return Строка которая повторяет содержимое файла cms_install_values
     */
    public String toString() {
        // Строим строку
        StringBuilder sb = new StringBuilder();
        sb.append("-- Language of system CMS (possible values are {RUS,ENG})\n");
        sb.append("DEFINE sysLanguage = ").append(sysLanguage).append("\n\n");
        sb.append("-- Name of system administrator user\n");
        sb.append("DEFINE sysUser = ").append(sysUser).append("\n");
        sb.append("-- System administrator password\n");
        sb.append("DEFINE sysPswd = ").append(sysPswd).append("\n");
        sb.append("-- Type of connect for System administrator\n");
        sb.append("DEFINE sysCntp = ").append(sysCntp).append("\n\n");
        sb.append("/**********************************************\n" +
                "*                     CRM-CMS                 *\n" +
                "***********************************************/\n");
        sb.append("-- CRM database name\n");
        sb.append("DEFINE crmBase = ").append(crmBase).append("\n");
        sb.append("-- Name of CRM schema\n");
        sb.append("DEFINE crmUser = ").append(crmUser).append("\n");
        sb.append("-- CRM user password\n");
        sb.append("DEFINE crmPswd = ").append(crmPswd).append("\n");
        sb.append("-- default tablespace for CRM\n");
        sb.append("DEFINE defTbs = ").append(defTbs).append("\n");
        sb.append("-- index tablespace for CRM\n");
        sb.append("DEFINE idxTbs = ").append(idxTbs).append("\n\n");
        sb.append("-- Authorization Role name\n");
        sb.append("DEFINE authRole = ").append(authRole).append("\n");
        sb.append("-- Application Role name\n");
        sb.append("DEFINE appRole = ").append(appRole).append("\n\n");
        sb.append("-- Authorization User name\n");
        sb.append("DEFINE authUser = ").append(authUser).append("\n");
        sb.append("-- Authorization User Password\n");
        sb.append("DEFINE authPswd = ").append(authPswd).append("\n\n");
        sb.append("-- Application User name\n");
        sb.append("DEFINE appUser = ").append(appUser).append("\n");
        sb.append("-- Application User Password\n");
        sb.append("DEFINE appPswd = ").append(appPswd).append("\n\n");
        sb.append("-- Role name for CMS API\n");
        sb.append("DEFINE apiRole = ").append(apiRole).append("\n\n");

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
        // Возвращаем сконструированную строку
        return sb.toString();
    }

    /**
     * Построим объект из строк. Распарсим строки в соответствии с реализацией класса
     *
     * @param stringList Список строк, которые будем разбирать
     * @return TRUE если успешно
     */
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
                        if (m.group(1).equals("sysLanguage"))        { sysLanguage = m.group(2); }
                        else if (m.group(1).equals("sysUser"))       { sysUser  = m.group(2); }
                        else if (m.group(1).equals("sysPswd"))       { sysPswd  = m.group(2); }
                        else if (m.group(1).equals("sysCntp"))       { sysCntp  = m.group(2); }
                        else if (m.group(1).equals("crmBase"))       { crmBase  = m.group(2); }
                        else if (m.group(1).equals("crmUser"))       { crmUser  = m.group(2); }
                        else if (m.group(1).equals("crmPswd"))       { crmPswd  = m.group(2); }
                        else if (m.group(1).equals("defTbs"))        { defTbs  = m.group(2); }
                        else if (m.group(1).equals("idxTbs"))        { idxTbs  = m.group(2); }
                        else if (m.group(1).equals("authRole"))      { authRole  = m.group(2); }
                        else if (m.group(1).equals("appRole"))       { appRole  = m.group(2); }
                        else if (m.group(1).equals("authUser"))      { authUser  = m.group(2); }
                        else if (m.group(1).equals("authPswd"))      { authPswd  = m.group(2); }
                        else if (m.group(1).equals("appUser"))       { appUser  = m.group(2); }
                        else if (m.group(1).equals("appPswd"))       { appPswd  = m.group(2); }
                        else if (m.group(1).equals("apiRole"))       { apiRole  = m.group(2); }
                        else if (m.group(1).equals("scrCommonBase")) { scrCommonBase  = m.group(2); }
                        else if (m.group(1).equals("scrCommonUser")) { scrCommonUser  = m.group(2); }
                        else if (m.group(1).equals("scrCommonPswd")) { scrCommonPswd  = m.group(2); }
                        else if (m.group(1).equals("crmToCommonLink")) { crmToCommonLink  = m.group(2); }
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("Ошибка при разборе содержимого файла cms_install_values для подсистемы SCR_CMS.\n Имя файла: " + filename);
        }
    }

    /**
     * Проверка того что объект валиден по своему наполнению
     * @return TRUE - если успешно
     */
    public boolean validate () {
        if ( sysUser.equals("???") || sysUser == null) {
            System.out.println("Error: check sysUser");
            return false;
        }

        if (  sysPswd.equals("???") || sysPswd == null) {
            System.out.println("Error: check sysPswd");
            return false;
        }

        if (  sysCntp.equals("???") || sysCntp == null) {
            System.out.println("Error: check sysCntp");
            return false;
        }

        if ( sysLanguage.equals("???") || sysLanguage == null) {
            System.out.println("Error: check sysLanguage");
            return false;
        }

        if ( crmBase.equals("???") || crmBase == null) {
            System.out.println("Error: check sysLanguage");
            return false;
        }

        if ( crmUser.equals("???") || crmUser == null) {
            System.out.println("Error: check crmUser");
            return false;
        }

        if ( crmPswd.equals("???") ||  crmPswd == null) {
            System.out.println("Error: check crmPswd");
            return false;
        }

        if ( defTbs.equals("???") || defTbs == null) {
            System.out.println("Error: check defTbs");
            return false;
        }

        if ( idxTbs.equals("???") || idxTbs == null) {
            System.out.println("Error: check idxTbs");
            return false;
        }

        if ( authRole.equals("???") || authRole == null) {
            System.out.println("Error: check authRole");
            return false;
        }

        if ( appRole.equals("???") || appRole == null) {
            System.out.println("Error: check appRole");
            return false;
        }

        if ( apiRole.equals("???") || apiRole == null) {
            System.out.println("Error: check apiRole");
            return false;
        }

        if ( authUser.equals("???") || authUser == null) {
            System.out.println("Error: check authUser");
            return false;
        }

        if ( authPswd.equals("???") || authPswd == null) {
            System.out.println("Error: check authPswd");
            return false;
        }

        if ( appUser.equals("???") || appUser == null) {
            System.out.println("Error: check appUser");
            return false;
        }

        if ( appPswd.equals("???") || appPswd == null) {
            System.out.println("Error: check appPswd");
            return false;
        }

        if ( scrCommonBase.equals("???") || scrCommonBase == null) {
            System.out.println("Error: check scrCommonBase");
            return false;
        }

        if ( scrCommonUser.equals("???") || scrCommonUser == null) {
            System.out.println("Error: check scrCommonUser");
            return false;
        }

        if ( scrCommonPswd.equals("???") || scrCommonPswd == null) {
            System.out.println("Error: check scrCommonPswd");
            return false;
        }

        if ( crmToCommonLink.equals("???") || crmToCommonLink == null) {
            System.out.println("Error: check crmToCommonLink");
            return false;
        }
        // Если все проверки прошли успешно то выходим с успехом
        return true;
    }

    /******************************Геттеры и сеттеры*****************************/

    public String getSysLanguage() {
        return sysLanguage;
    }

    public void setSysLanguage(String sysLanguage) {
        this.sysLanguage = sysLanguage;
    }

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

    public String getAuthPswd() {
        return authPswd;
    }

    public void setAuthPswd(String authPswd) {
        this.authPswd = authPswd;
    }

    public String getAuthUser() {
        return authUser;
    }

    public void setAuthUser(String authUser) {
        this.authUser = authUser;
    }

    public String getApiRole() {
        return apiRole;
    }

    public void setApiRole(String apiRole) {
        this.apiRole = apiRole;
    }

    public String getAppRole() {
        return appRole;
    }

    public void setAppRole(String appRole) {
        this.appRole = appRole;
    }

    public String getAuthRole() {
        return authRole;
    }

    public void setAuthRole(String authRole) {
        this.authRole = authRole;
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
