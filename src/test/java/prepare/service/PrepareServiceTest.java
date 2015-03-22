package prepare.service;

import config.controller.ConfigController;
import config.model.CommonConfig;
import install_values.model.impl.InstallValuesScrCms;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class PrepareServiceTest {

    PrepareService prepareService;
    CommonConfig commonConfig = null;


    @Before
    public void setUp() throws Exception {
        prepareService = new PrepareService();
        commonConfig = ConfigController.getConfig("d:/#GIT_HUB/AutoinstallServer/config/config.xml");
    }

    @Test
    public void testPrepareBase() throws Exception {
        prepareService.prepareBase(commonConfig);
    }

    @Test
    public void testPrepareFileSystem() throws Exception {
        // Создадим папку если она не сущесвует, что бы было чего удалять
        if (Files.notExists(Paths.get(commonConfig.getDestinationDirectory()))) {
            Files.createDirectories(Paths.get(commonConfig.getDestinationDirectory()));
        }

        // Выполним подготовку (удаление папки)
        prepareService.prepareFileSystem(commonConfig);

        // Проверим что папки нет
        assertFalse("При подготовке папка удалена", Files.exists(Paths.get(commonConfig.getDestinationDirectory())));
    }
}