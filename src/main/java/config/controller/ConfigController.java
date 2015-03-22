package config.controller;

import config.model.CommonConfig;
import config.model.SubsystemConfig;
import config.services.impl.ConfigStorageServiceImpl;
import install_values.controller.InstallValuesController;
import install_values.model.InstallValues;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Anatoly.Cherkasov on 13.03.2015.
 * Этот клас содержит функции по работе с конфигурацией.
 * Для работы с конфигурацией нужно использовать только его.
 * Контроллер знает как прочитать файлы с конфигурацией и параметрами в объект
 * Контроллер знает как сохранить объект в файлы с конфигурайией и параметрами
 */
public class ConfigController {

    // Объявляем переменную логгера
    private static Logger log = LoggerFactory.getLogger(ConfigController.class);

    /**
     * Получим полностью объект конфигурацию
     * Пробежимся по всем файлам параметров подсистем cms_install_values и тоже зачитаем их
     * @param configFileName Имя файла конфигурации с путем до него
     * @return Объект файла конфигурации
     */
    public static CommonConfig getConfig( String configFileName ) {

        ConfigStorageServiceImpl configStorageService = new ConfigStorageServiceImpl();
        InstallValuesController installValuesController = new InstallValuesController();

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
        TreeMap<Integer, SubsystemConfig> subsystemConfigTreeMap = commonConfig.getSubsystemConfigTreeMap();
        for ( Map.Entry<Integer, SubsystemConfig> subsystemConfigMapEntry : subsystemConfigTreeMap.entrySet() )
        {
            SubsystemConfig subsystemConfig = subsystemConfigMapEntry.getValue();
            // Изменим элемент из списка
            // Для подсистемы прочитаем её файл cms_install_values.sql
            InstallValues installValues = installValuesController.getInstallValues(subsystemConfig);
            if (installValues == null) {
                log.info("Файл cms_install_values не был прочитан!");
            }
            // Сохраним полученный объект cms_install_values в конфигурации подсистемы
            subsystemConfig.setInstallValues(installValues);
            // Заменим элемент "конфигурация подсистемы" в спике конфигураций подсистем
            subsystemConfigTreeMap.put(subsystemConfigMapEntry.getKey(),subsystemConfig);
        }

        // Перепишем список все конфигураций подсистем в объекте Конфиг
        commonConfig.setSubsystemConfigTreeMap(subsystemConfigTreeMap);

        // Вернем заполненный объект конфигурации
        return commonConfig;
    }

    public static boolean setConfig( String configFileName ) {
        return false;
    }
}
