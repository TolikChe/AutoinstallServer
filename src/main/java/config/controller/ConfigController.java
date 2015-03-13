package config.controller;

import config.model.CommonConfig;
import config.model.SubsystemConfig;
import config.services.impl.ConfigStorageServiceImpl;
import install_values.services.impl.InstallValuesStorageServiceImpl;

import java.util.List;

/**
 * Created by Anatoly.Cherkasov on 13.03.2015.
 * Этот клас содержит функции по работе с конфигурацией.
 * Контроллер знает как прочитать файлы с конфигурацией и параметрами в объект
 * Контроллер знает как сохранить объект в файлы с конфигурайией и параметрами
 */
public class ConfigController {

    /**
     * Получим полностью объект конфигурацию
     * Пробежимся по всем файлам параметров подсистем cms_install_values и тоже зачитаем их
     * @param configFileName Имя файла конфигурации с путем до него
     * @return Объект файла конфигурации
     */
    public static CommonConfig getConfig( String configFileName ) {

        ConfigStorageServiceImpl configStorageService = new ConfigStorageServiceImpl();
        InstallValuesStorageServiceImpl installValuesStorageService = new InstallValuesStorageServiceImpl();


        // Вычитываем главный файл конфигурации в объект
        // Если возникла ошибка чтения то прерываем операцию
        CommonConfig commonConfig = null;
        try {
            commonConfig = configStorageService.readFromFile(configFileName);
        } catch (Exception ex) {
            System.out.println("Ошибка при чтении файла конфигурации " + configFileName);
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return null;
        }

        //
        // Для каждой подсистемы из файлы конфигурации вычитываем его файл cms_install_values
        // Если возникла ошибка чтения то перехаодим к следующей подсистеме.
        List<SubsystemConfig> subsystemConfigList = commonConfig.getSubsystemConfigList();
        SubsystemConfig subsystemConfig = null;
        for (int x = 0; x < subsystemConfigList.size(); x++)
        {
            // Выдернем элемент из списка
            subsystemConfig = subsystemConfigList.get(x);
            try {
                // Изменим элемент из списка
                subsystemConfig.setInstallValues(installValuesStorageService.readFromFile( subsystemConfig.getInstallValuesDirectory() + "/" +  subsystemConfig.getInstallValuesFileName()));
            } catch (Exception ex) {
                System.out.println("Ошибка при чтении файла " + subsystemConfig.getInstallValuesFileName() + " для подсистемы " + subsystemConfig.getOrder() + "_" + subsystemConfig.getType());
                System.out.println(ex.getMessage());
                ex.printStackTrace();
                return null;
            }
            // Заменим элемент в спике конфигураций подсистем
            subsystemConfigList.set(x, subsystemConfig);
        }

        // Перепишем список конфигураций подсистем
        commonConfig.setSubsystemConfigList(subsystemConfigList);

        // Вернем заполненный объект конфигурации
        return commonConfig;
    }

    public static boolean setConfig( String configFileName ) {
        return false;
    }
}
