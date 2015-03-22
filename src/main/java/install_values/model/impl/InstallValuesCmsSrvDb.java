package install_values.model.impl;

import install_values.model.InstallValues;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Anatoly.Cherkasov on 23.01.2015.
 */
public class InstallValuesCmsSrvDb implements InstallValues {

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
     * Схема для аутентификации
     */
    private String authUser;
    /**
     * Пароль для схема аутентификации
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
     *
     */
    private String apiRole;
    /**
     * База где стоит подсистема CDM_LOADER
     */
    private String cdmBase;
    /**
     * Схема где стоит подсистема CDM_LOADER
     */
    private String cdmUser;
    /**
     * Пароль для схема где стоит подсистема CDM_LOADER
     */
    private String cdmPswd;
    /**
    *	Значение параметра Time_Translate
    */
    private String timeTranslate;

    /**
     * Конструктор с проверкой существования файла
     *
     * @param filename Файл в котором находится конфиг подсистемы
     */
    public InstallValuesCmsSrvDb(String filename) throws Exception {

        File file = new File(filename);
        if (!(file.exists() && file.isFile())) {
            throw new Exception("Ошибка InstallValuesCmsSrvDb. Файл (" + file.getAbsoluteFile() + ") по указанному пути не найден ");
        }
        this.filename = file.getAbsoluteFile().toString();
    }


    public String toString() {
        // Строим строку
        StringBuilder sb = new StringBuilder();
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
                "*        CDM_LOADER                                 *\n" +
                "*****************************************************/\n");
        sb.append("--db alias with CDM_LOADER module\n");
        sb.append("DEFINE cdmBase = ").append(cdmBase).append("\n");
        sb.append("--CDM_LOADER scheme name\n");
        sb.append("DEFINE cdmUser = ").append(cdmUser).append("\n");
        sb.append("--cdmUser user password\n");
        sb.append("DEFINE cdmPswd = ").append(cdmPswd).append("\n");

        sb.append("/****************************************************\n" +
                "*        CRM-CMS parameters                           *\n" +
                "*****************************************************/\n");

        sb.append("--use time translate parameter (Y,N)\n");
        sb.append("DEFINE timeTranslate = ").append(timeTranslate).append("\n");
        // Возвращаем сконструированную строку
        return sb.toString();
    }


    public void fromString (List<String> stringList) throws Exception{
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
                        if (m.group(1).equals("sysUser"))       { sysUser  = m.group(2); }
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
                        else if (m.group(1).equals("cdmBase"))       { cdmBase  = m.group(2); }
                        else if (m.group(1).equals("cdmUser"))       { cdmUser  = m.group(2); }
                        else if (m.group(1).equals("cdmPswd"))       { cdmPswd  = m.group(2); }
                        else if (m.group(1).equals("timeTranslate")) { timeTranslate  = m.group(2); }
                    }
                }
            }
        } catch (Exception e) {
            throw new Exception("Ошибка при разборе содержимого файла cms_install_values для подсистемы CMS_SRV_DB.\n Имя файла: " + filename);
        }
    }

    /**
     * Проверка того что объект валиден по своему наполнению
     *
     * @return TRUE - если успешно
     */
    public boolean validate() {
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

        // Если все проверки прошли успешно то выходим с успехом
        return true;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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

    public String getCrmBase() {
        return crmBase;
    }

    public void setCrmBase(String crmBase) {
        this.crmBase = crmBase;
    }

    public String getCrmUser() {
        return crmUser;
    }

    public void setCrmUser(String crmUser) {
        this.crmUser = crmUser;
    }

    public String getCrmPswd() {
        return crmPswd;
    }

    public void setCrmPswd(String crmPswd) {
        this.crmPswd = crmPswd;
    }

    public String getDefTbs() {
        return defTbs;
    }

    public void setDefTbs(String defTbs) {
        this.defTbs = defTbs;
    }

    public String getIdxTbs() {
        return idxTbs;
    }

    public void setIdxTbs(String idxTbs) {
        this.idxTbs = idxTbs;
    }

    public String getAuthRole() {
        return authRole;
    }

    public void setAuthRole(String authRole) {
        this.authRole = authRole;
    }

    public String getAppRole() {
        return appRole;
    }

    public void setAppRole(String appRole) {
        this.appRole = appRole;
    }

    public String getAuthUser() {
        return authUser;
    }

    public void setAuthUser(String authUser) {
        this.authUser = authUser;
    }

    public String getAppUser() {
        return appUser;
    }

    public void setAppUser(String appUser) {
        this.appUser = appUser;
    }

    public String getAppPswd() {
        return appPswd;
    }

    public void setAppPswd(String appPswd) {
        this.appPswd = appPswd;
    }

    public String getApiRole() {
        return apiRole;
    }

    public void setApiRole(String apiRole) {
        this.apiRole = apiRole;
    }

    public String getCdmBase() {
        return cdmBase;
    }

    public void setCdmBase(String cdmBase) {
        this.cdmBase = cdmBase;
    }

    public String getCdmUser() {
        return cdmUser;
    }

    public void setCdmUser(String cdmUser) {
        this.cdmUser = cdmUser;
    }

    public String getCdmPswd() {
        return cdmPswd;
    }

    public void setCdmPswd(String cdmPswd) {
        this.cdmPswd = cdmPswd;
    }

    public String getTimeTranslate() {
        return timeTranslate;
    }

    public void setTimeTranslate(String timeTranslate) {
        this.timeTranslate = timeTranslate;
    }
}
