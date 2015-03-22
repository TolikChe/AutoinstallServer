package install_values.controller;

import config.model.SubsystemConfig;
import install_values.model.InstallValues;
import install_values.services.impl.InstallValuesStorageServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by Anatoly.Cherkasov on 18.03.2015.
 * Этот клас содержит функции по работе с файлами cms_install_values.sql
 * Для работы с этими файлами нужно использовать только его.
 * Контроллер знает как прочитать файлы в объект
 * Контроллер знает как сохранить объект в файлы

 */
public class InstallValuesController {

    // Объявляем переменную логгера
    private Logger log = LoggerFactory.getLogger(InstallValuesController.class);

    /**
     * Получаем объект типа InstallValues для заданной подсистемы
     * @param subsystemConfig Конфигурация подсистемы, для которой необходимо получить файл cms_install_values
     * @return объект типа InstallValues либо NULL если произошли какие то ошибки
     */
    public InstallValues getInstallValues (SubsystemConfig subsystemConfig){
        // Заявим сервис
        InstallValuesStorageServiceImpl installValuesStorageService = new InstallValuesStorageServiceImpl();
        // На основе пришедшей конфигурации получим значение
        try {
            log.info("Прочитаем файл " + subsystemConfig.getInstallValuesFileName() + " для подсистемы " + subsystemConfig.getOrder() + "_" + subsystemConfig.getType() );
            return installValuesStorageService.readFromFile(subsystemConfig.getInstallValuesFileName());
        } catch (NullPointerException ex) {
            log.error("Ошибка работе с объектом subsystemConfig");
            log.error(ex.getMessage());
            log.error(Arrays.toString(ex.getStackTrace()));
            return null;
        }
        catch (Exception ex) {
            log.error("Ошибка при чтении файла cms_install_values.sql для подсистемы " + subsystemConfig.getOrder() + "_" + subsystemConfig.getType() );
            log.error("Имя файла: " + subsystemConfig.getInstallValuesFileName());
            log.error(ex.getMessage());
            log.error(Arrays.toString(ex.getStackTrace()));
            return null;
        }
    }


    public static void setInstallValues(InstallValues installValues){

    }
}
