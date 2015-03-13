package config.controller;

import config.model.CommonConfig;
import install_values.model.impl.InstallValuesAdapter2Bis;
import install_values.model.impl.InstallValuesCmsSrvDb;
import install_values.model.impl.InstallValuesScrCms;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigControllerTest {

    @Test
    public void testGetConfig_getConfigName() throws Exception {

        CommonConfig commonConfig = ConfigController.getConfig("d:/#GIT_HUB/AutoinstallServer/config/config.xml");

        // Проверим что объект наполнился
        assertNotNull("Проверим что заполнено имя конфигурации", commonConfig.getConfigName());
    }

    @Test
    public void testGetConfig_getDestinationDirectory() throws Exception {

        CommonConfig commonConfig = ConfigController.getConfig("d:/#GIT_HUB/AutoinstallServer/config/config.xml");

        // Проверим что объект наполнился
        assertNotNull("Проверим что заполнен путь, куда будут перенесены дистрибутивы", commonConfig.getDestinationDirectory());
    }

    @Test
    public void testGetConfig_getPrepareDirectory() throws Exception {

        CommonConfig commonConfig = ConfigController.getConfig("d:/#GIT_HUB/AutoinstallServer/config/config.xml");

        // Проверим что объект наполнился
        assertNotNull("Проверим что заполнен путь, где нахходятся вспомогательные файлы", commonConfig.getPrepareDirectory());
    }

    @Test
    public void testGetConfig_getConfigFilename() throws Exception {

        CommonConfig commonConfig = ConfigController.getConfig("d:/#GIT_HUB/AutoinstallServer/config/config.xml");

        // Проверим что объект наполнился
        assertNotNull("Проверим что заполнено совйство - Имя файла с конфигурацией", commonConfig.getConfigFilename());
    }

    @Test
    public void testGetConfig_getSubsystemConfigList() throws Exception {

        CommonConfig commonConfig = ConfigController.getConfig("d:/#GIT_HUB/AutoinstallServer/config/config.xml");

        // Проверим что в объекте список из трех подсистем
        assertEquals("Проверим число элементов в списке подсистем", 3, commonConfig.getSubsystemConfigList().size());
    }

    @Test
    public void testGetConfig_getSubsystemType() throws Exception {

        CommonConfig commonConfig = ConfigController.getConfig("d:/#GIT_HUB/AutoinstallServer/config/config.xml");

        // Проверим типы подсистем в списке
        assertEquals("SCR_CMS", commonConfig.getSubsystemConfigList().get(0).getType());
        assertEquals("CMS_SRV_DB", commonConfig.getSubsystemConfigList().get(1).getType());
        assertEquals("CMS_DS_ADAPTER", commonConfig.getSubsystemConfigList().get(2).getType());
    }

    @Test
    public void testGetConfig_getInstallValuesScrCms() throws Exception {

        CommonConfig commonConfig = ConfigController.getConfig("d:/#GIT_HUB/AutoinstallServer/config/config.xml");

        // Проверим что у подсистем заполнены объекты install_values
        assertEquals("SCR_CMS install_values", "ntdb10", ((InstallValuesScrCms) commonConfig.getSubsystemConfigList().get(0).getInstallValues()).getCrmBase());
    }

    @Test
    public void testGetConfig_getInstallValuesCmsSrvDb() throws Exception {

        CommonConfig commonConfig = ConfigController.getConfig("d:/#GIT_HUB/AutoinstallServer/config/config.xml");

        // Проверим что у подсистем заполнены объекты install_values
        assertEquals("CMS_SRV_DB install_values", "ntdb10", ((InstallValuesCmsSrvDb) commonConfig.getSubsystemConfigList().get(1).getInstallValues()).getCrmBase());
    }

    @Test
    public void testGetConfig_getInstallValuesAdapter2Bis() throws Exception {

        CommonConfig commonConfig = ConfigController.getConfig("d:/#GIT_HUB/AutoinstallServer/config/config.xml");

        // Проверим что у подсистем заполнены объекты install_values
        assertEquals("CMS_DS_ADAPTER install_values", "ntdb10", ((InstallValuesAdapter2Bis) commonConfig.getSubsystemConfigList().get(2).getInstallValues()).getCrmBase());
    }
}