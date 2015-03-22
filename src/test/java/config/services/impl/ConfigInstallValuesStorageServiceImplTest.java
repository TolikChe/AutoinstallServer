package config.services.impl;

import config.model.CommonConfig;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigInstallValuesStorageServiceImplTest {

    ConfigStorageServiceImpl storageService;

    @Before
    public void setUp() throws Exception {
        storageService = new ConfigStorageServiceImpl();
    }

    @Test
    public void testReadFromFile() throws Exception {
        CommonConfig cfg = storageService.readFromFile("c:/Users/echo/IdeaProjects/AutoinstallServer/config/config.xml");


        // Проверим что объект наполнился
        assertNotNull("Проверим что заполнено имя конфигурации", cfg.getConfigName());
        assertNotNull("Проверим что заполнен путь, куда будут перенесены дистрибутивы", cfg.getDestinationDirectory());
        assertNotNull("Проверим что заполнено совйство - Имя файла с конфигурацией", cfg.getConfigFilename());

        // Проверим что в объекте список из трех подсистем
        assertEquals( "Проверим число элементов в списке подсистем", 3, cfg.getSubsystemConfigTreeMap().size());

        // Проверим типы подсистем в списке
        assertEquals("SCR_CMS", cfg.getSubsystemConfigTreeMap().get(0).getType());
        assertEquals("CMS_SRV_DB", cfg.getSubsystemConfigTreeMap().get(1).getType());
        assertEquals("CMS_DS_ADAPTER", cfg.getSubsystemConfigTreeMap().get(2).getType());
    }
}