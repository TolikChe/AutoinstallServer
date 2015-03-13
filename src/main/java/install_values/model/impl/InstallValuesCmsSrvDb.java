package install_values.model.impl;

import install_values.model.InstallValues;

import java.io.File;
import java.util.List;

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
        return "";
    }


    public void fromString (List<String> stringList) throws RuntimeException{
    }

    /**
     * Проверка того что объект валиден по своему наполнению
     *
     * @return TRUE - если успешно
     */
    public boolean validate() {
        return false;
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
