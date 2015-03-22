package prepare.controller;

import config.model.CommonConfig;
import config.model.SubsystemConfig;
import install_values.model.impl.InstallValuesScrCms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import prepare.service.PrepareService;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Anatoly.Cherkasov on 27.02.2015.
 *
 */
public class PrepareController {

    // Объявляем переменную логгера
    private static Logger log = LoggerFactory.getLogger(PrepareController.class);

    /**
     * Выполняется подготовительные действия перед новой установкой.
     * 1. Выполняется удаление переустанавливаемых схем из базы данных.
     * 2. Выполняется подготовка файловой системы к переносу скриптов устновки
     * @param commonConfig Файл с общей конфигурацией
     * @return TRUE если подготовка выполнена успешно.
      */
    public static boolean prepare(CommonConfig commonConfig) {
        PrepareService prepareService = new PrepareService();

        // Выполним подготовку базы перед установкой в нее новых схем.
        try {
            prepareService.prepareBase(commonConfig);
        } catch (Exception ex) {
            log.error("Ошибка в процессе подготовки к установке");
            log.error("Ошибка при подготовке базы");
            log.error(ex.getMessage());
            log.error(Arrays.toString(ex.getStackTrace()));
            return false;
        }

        // Выполним подготовку файловой системы.
        try {
            prepareService.prepareFileSystem(commonConfig);
        } catch (Exception ex) {
            log.error("Ошибка в процессе подготовки к установке");
            log.error("Ошибка при подготовке файловой системы");
            log.error(ex.getMessage());
            log.error(Arrays.toString(ex.getStackTrace()));
            return false;
        }

        return true;
    }

}
