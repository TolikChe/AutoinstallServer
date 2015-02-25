package install_values.model.impl;

import install_values.model.InstallValues;

import java.io.File;
import java.util.List;

/**
 * Created by Anatoly.Cherkasov on 16.01.2015.
 * Класс описывает файл CMS_INSTALL_VALUES.sql для подсистемы SCR_CMS
 */
public class InstallValuesAdapter2Bis implements InstallValues {

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
    public void fromString(List<String> stringList) throws RuntimeException{
    }

    public String toString(){
        return "";
    }

    /**
     * Проверка того что объект валиден по своему наполнению
     * @return TRUE - если успешно
     */
    public boolean validate(){
        return false;
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
