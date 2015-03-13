package prepare.controller;

import config.model.CommonConfig;
import config.model.SubsystemConfig;
import install_values.model.impl.InstallValuesScrCms;
import prepare.service.PrepareService;

import java.util.List;

/**
 * Created by Anatoly.Cherkasov on 27.02.2015.
 *
 */
public class PrepareController {

    /**
     * Выполняется подготовительные действия перед новой установкой.
     * 1. Выполняется удаление переустанавливаемых схем из базы данных.
     * 2. Выполняется подготовка файловой системы к переносу скриптов устновки
     * @param commonConfig Файл с общей конфигурацией
     * @return TRUE если подготовка выполнена успешно.
      */
    public static boolean prepare(CommonConfig commonConfig) {
        PrepareService prepareService = new PrepareService();
        InstallValuesScrCms installValuesScrCms = null;

        // Надем в конфиге первую подсистему SCR_CMS.
        // Возьмем её файл cms_install_values и передадим его как параметр.
        List<SubsystemConfig> subsystemConfigList = commonConfig.getSubsystemConfigList();
        for (SubsystemConfig subsystemConfig : subsystemConfigList){
            if (subsystemConfig.getType().equals("SCR_CMS")) {
                installValuesScrCms = (InstallValuesScrCms)subsystemConfig.getInstallValues();
                break;
            }
        }

        // Выполним подготовку базы перед установкой в нее новых схем.
        try {
            prepareService.prepareBase(installValuesScrCms);
        } catch (Exception ex) {
            System.out.println("Ошибка в процессе подготовки к установке");
            System.out.println("Ошибка при подготовке базы");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }

        // Выполним подготовку файловой системы.
        try {
            prepareService.prepareFileSystem(commonConfig);
        } catch (Exception ex) {
            System.out.println("Ошибка в процессе подготовки к установке");
            System.out.println("Ошибка при подготовке файловой системы");
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return false;
        }

        return true;
    }

}
