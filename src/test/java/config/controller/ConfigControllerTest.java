package config.controller;

import config.model.CommonConfig;
import install_values.model.impl.InstallValuesAdapter2Bis;
import install_values.model.impl.InstallValuesCmsSrvDb;
import install_values.model.impl.InstallValuesScrCms;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Набор тестов проверяет что при считывании файла конфигурации считываются все файлы с параметрами подсистем входящих в конфигурацию
 */
public class ConfigControllerTest {

    @Test
    public void testGetConfig_getConfigName() throws Exception {

        CommonConfig commonConfig = ConfigController.getConfig("d:/#GIT_HUB/AutoinstallServer/config/config.xml");

        // Проверим что объект наполнился
        assertNotNull("Проверим что заполнено имя конфигурации", commonConfig.getConfigName());
        assertNotEquals("Проверим что заполнено имя конфигурации len", 0, commonConfig.getConfigName().length());
    }

    @Test
    public void testGetConfig_getTns() throws Exception {

        CommonConfig commonConfig = ConfigController.getConfig("d:/#GIT_HUB/AutoinstallServer/config/config.xml");

        // Проверим что объект наполнился
        System.out.println(commonConfig.getTns());
        assertNotNull("Проверим что заполнен TNS str", commonConfig.getTns());
        assertNotEquals("Проверим что заполнен TNS len", 0, commonConfig.getTns().length());
    }


    @Test
    public void testGetConfig_getDestinationDirectory() throws Exception {

        CommonConfig commonConfig = ConfigController.getConfig("d:/#GIT_HUB/AutoinstallServer/config/config.xml");

        // Проверим что объект наполнился
        assertNotNull("Проверим что заполнен путь, куда будут перенесены дистрибутивы", commonConfig.getDestinationDirectory());
        assertNotEquals("Проверим что заполнен путь, куда будут перенесены дистрибутивы len", 0, commonConfig.getDestinationDirectory().length());
    }

    @Test
    public void testGetConfig_getConfigFilename() throws Exception {

        CommonConfig commonConfig = ConfigController.getConfig("d:/#GIT_HUB/AutoinstallServer/config/config.xml");

        // Проверим что объект наполнился
        assertNotNull("Проверим что заполнено совйство - Имя файла с конфигурацией", commonConfig.getConfigFilename());
        assertNotEquals("Проверим что заполнено совйство - Имя файла с конфигурацией len", 0, commonConfig.getConfigFilename().length());
    }

    @Test
    public void testGetConfig_getSubsystemConfigList() throws Exception {

        CommonConfig commonConfig = ConfigController.getConfig("d:/#GIT_HUB/AutoinstallServer/config/config.xml");

        // Проверим что в объекте список из трех подсистем
        assertEquals("Проверим число элементов в списке подсистем", 3, commonConfig.getSubsystemConfigTreeMap().size());
    }

    @Test
    public void testGetConfig_getSubsystemType() throws Exception {

        CommonConfig commonConfig = ConfigController.getConfig("d:/#GIT_HUB/AutoinstallServer/config/config.xml");

        // Проверим типы подсистем в списке
        assertEquals("SCR_CMS", commonConfig.getSubsystemConfigTreeMap().get(0).getType());
        assertEquals("CMS_SRV_DB", commonConfig.getSubsystemConfigTreeMap().get(1).getType());
        assertEquals("CMS_DS_ADAPTER", commonConfig.getSubsystemConfigTreeMap().get(2).getType());
    }

    @Test
    public void testGetConfig_getInstallValuesScrCms() throws Exception {

        CommonConfig commonConfig = ConfigController.getConfig("d:/#GIT_HUB/AutoinstallServer/config/config.xml");

        // Проверим что у подсистем заполнены объекты install_values
        assertEquals("SCR_CMS install_values", "ntdb10", ((InstallValuesScrCms) commonConfig.getSubsystemConfigTreeMap().get(0).getInstallValues()).getCrmBase());
    }

    @Test
    public void testGetConfig_getInstallValuesCmsSrvDb() throws Exception {

        CommonConfig commonConfig = ConfigController.getConfig("d:/#GIT_HUB/AutoinstallServer/config/config.xml");

        // Проверим что у подсистем заполнены объекты install_values
        assertEquals("CMS_SRV_DB install_values", "ntdb10", ((InstallValuesCmsSrvDb) commonConfig.getSubsystemConfigTreeMap().get(1).getInstallValues()).getCrmBase());
    }

    @Test
    public void testGetConfig_getInstallValuesAdapter2Bis() throws Exception {

        CommonConfig commonConfig = ConfigController.getConfig("d:/#GIT_HUB/AutoinstallServer/config/config.xml");

        // Проверим что у подсистем заполнены объекты install_values
        assertEquals("CMS_DS_ADAPTER install_values", "ntdb10", ((InstallValuesAdapter2Bis) commonConfig.getSubsystemConfigTreeMap().get(2).getInstallValues()).getCrmBase());
    }
}