package config.model;

/**
 * Created by echo on 25.02.2015.
 * Этот класс определяет объект-подсистему, описанную в файле конфигурации
 */
public class SubsystemConfig {
    /**
     * Тип подсистемы (SCR_CMS / CMS_SRV_DB / CMS_DS_ADAPTER2BIS)
     */
    private String type;
    /**
     * Порядок установки подсистемы 0, 1, 2 ...
     */
    private int order;
    /**
     * Имя ветки в гите по которой брать файлы
      */
    private String branch;
    /**
     * Имя метки в гите по которой брать файлы
      */
    private String tag;
    /**
     * Url гита, по которому брать файлы
     */
    private String gitUrl;
    /**
     * Режим установки (install / update)
     */
    private String setupMode;
    /**
     * Имя файла с параметрами
     */
    private String installValuesFileName;
    /**
     * Директория где лежит файла с параметрами
     */
    private String installValuesDirectory;
    /**
     * Имя файла, который делает дистрибутив (обычно это distrib10.bat или distrib.bat)
     */
    private String distribFileName;
    /**
     * Имя папки, которая получается на выходе после запуска файла ${distribFileName}
     */
    private String distribDirectory;


    /******************************Геттеры и сеттеры*************************************/

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getGitUrl() {
        return gitUrl;
    }

    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    public String getSetupMode() {
        return setupMode;
    }

    public void setSetupMode(String setupMode) {
        this.setupMode = setupMode;
    }

    public String getInstallValuesFileName() {
        return installValuesFileName;
    }

    public void setInstallValuesFileName(String installValuesFileName) {
        this.installValuesFileName = installValuesFileName;
    }

    public String getDistribFileName() {
        return distribFileName;
    }

    public void setDistribFileName(String distribFileName) {
        this.distribFileName = distribFileName;
    }

    public String getDistribDirectory() {
        return distribDirectory;
    }

    public void setDistribDirectory(String distribDirectory) {
        this.distribDirectory = distribDirectory;
    }

    public String getInstallValuesDirectory() {
        return installValuesDirectory;
    }

    public void setInstallValuesDirectory(String installValuesDirectory) {
        this.installValuesDirectory = installValuesDirectory;
    }
}
