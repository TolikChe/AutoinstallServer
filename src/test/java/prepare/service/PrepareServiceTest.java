package prepare.service;

import config.controller.ConfigController;
import config.model.CommonConfig;
import install_values.model.impl.InstallValuesScrCms;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PrepareServiceTest {

    InstallValuesScrCms installValuesScrCms;
    PrepareService prepareService;
    String instalValuesFilePath = "";
    CommonConfig commonConfig = null;


    @Before
    public void setUp() throws Exception {
        installValuesScrCms = new InstallValuesScrCms(instalValuesFilePath);
        prepareService = new PrepareService();
        commonConfig = ConfigController.getConfig("d:/#GIT_HUB/AutoinstallServer/config/config.xml");
    }

    @Test
    public void testPrepareBase() throws Exception {
        prepareService.prepareBase(installValuesScrCms);
    }

    @Test
    public void testPrepareFileSystem() throws Exception {
        prepareService.prepareFileSystem(commonConfig);
    }
}