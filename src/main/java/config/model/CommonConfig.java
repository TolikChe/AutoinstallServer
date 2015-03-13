package config.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anatoly.Cherkasov on 19.01.2015.
 * Этот объект описывает конфиг верхнего уровня, который находится в файле сonfig.xml.
 * В нем содержаться все параметры установки подсистем и ссылки на файлы с параметрами для каждой подсистемы.
 * Файл сonfig.xml содержит xml документ.
 */
public class CommonConfig {

    /**
     * Имя конфигурации
     */
    private String configName;
    /**
     * Сюда будут перенесены дистрибутивы перед установкой
     */
    private String destinationDirectory;
    /**
     * В этой папке находятся скрипты, которые должны выполнятсья перед установкой подсистем
     */
    private String prepareDirectory;
    /**
     * Имя файла, содержищего файл конфигурации. Заполняется в процессе работы
     */
    private String configFilename;
    /**
     * Список подсистем, описанных в конфигурации
     */
    private List<SubsystemConfig> subsystemConfigList = new ArrayList<SubsystemConfig>(10);

    /******************************Геттеры и сеттеры*************************************/

    public String getDestinationDirectory() {
        return destinationDirectory;
    }

    public String getPrepareDirectory() {
        return prepareDirectory;
    }

    public List<SubsystemConfig> getSubsystemConfigList() {
        return subsystemConfigList;
    }

    public String getConfigFilename() {
        return configFilename;
    }

    public void setConfigFilename(String configFilename) {
        this.configFilename = configFilename;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public void setDestinationDirectory(String destinationDirectory) {
        this.destinationDirectory = destinationDirectory;
    }

    public void setPrepareDirectory(String prepareDirectory) {
        this.prepareDirectory = prepareDirectory;
    }

    public void setSubsystemConfigList(List<SubsystemConfig> subsystemConfigList) {
        this.subsystemConfigList = subsystemConfigList;
    }

    public void addSubsystemConfigToList(int order, SubsystemConfig subsystemConfig) {
        subsystemConfigList.add(order, subsystemConfig);
    }

}
