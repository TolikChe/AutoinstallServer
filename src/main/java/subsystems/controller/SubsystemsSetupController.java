package subsystems.controller;

import config.model.CommonConfig;
import config.model.SubsystemConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import subsystems.services.SetupService;
import subsystems.services.impl.SetupServiceCmsDebug;
import subsystems.services.impl.SetupServiceCmsDsAdapter;
import subsystems.services.impl.SetupServiceCmsSrvDb;
import subsystems.services.impl.SetupServiceScrCms;

import java.util.Arrays;

/**
 * Created by Anatoly.Cherkasov on 17.03.2015.
 * Класс выполняет установку подсистем. Можно установить
 */
public class SubsystemsSetupController {

    // Объявляем переменную логгера
    private static Logger log = LoggerFactory.getLogger(SubsystemsSetupController.class);
    //
    public static boolean setupSubsystem( SubsystemConfig subsystemConfig, String globalDestination ) {

        log.info("Установка подсистемы " + subsystemConfig.getOrder() + "_" + subsystemConfig.getType() );

        SetupService setupService = null;
        if ( subsystemConfig.getType().equals("SCR_CMS") ) {
            setupService = new SetupServiceScrCms();
        }
        else if ( subsystemConfig.getType().equals("CMS_SRV_DB") ) {
            setupService = new SetupServiceCmsSrvDb();
        }
        else if ( subsystemConfig.getType().equals("CMS_DS_ADAPTER") ) {
            setupService = new SetupServiceCmsDsAdapter();
        }
        else if ( subsystemConfig.getType().equals("CMS_DEBUG") ) {
            setupService = new SetupServiceCmsDebug();
        }


        String destination = null;
        try {
            destination = setupService.cloneSubsystem(subsystemConfig, globalDestination);
        } catch (Exception ex) {
            log.error("Ошибка при клонировании подсистемы " + subsystemConfig.getOrder() + "_" + subsystemConfig.getType() );
            log.error(ex.getMessage());
            log.error(Arrays.toString(ex.getStackTrace()));
            return false;
        }

        try {
            setupService.makeDistrib(subsystemConfig, destination);
        } catch (Exception ex) {
            log.error("Ошибка при создании дистрибутива подсистемы " + subsystemConfig.getOrder() + "_" + subsystemConfig.getType() );
            log.error(ex.getMessage());
            log.error(Arrays.toString(ex.getStackTrace()));
            return false;
        }

        try {
            setupService.setupSubsystem(subsystemConfig, destination);
        } catch (Exception ex) {
            log.error("Ошибка при установке подсистемы " + subsystemConfig.getOrder() + "_" + subsystemConfig.getType() );
            log.error(ex.getMessage());
            log.error(Arrays.toString(ex.getStackTrace()));
            return false;
        }

        return true;
    }


    /**
     * Устанавливаем все подсистемы из файла конфига
     * @param commonConfig
     */
    public static void setupSubsystems(CommonConfig commonConfig) {
        // Выполняем установку всех подсистм в цикле по порядку
        for ( SubsystemConfig subsystemConfig : commonConfig.getSubsystemConfigTreeMap().values() ) {
            setupSubsystem(subsystemConfig, commonConfig.getDestinationDirectory());
        }
    }
}
