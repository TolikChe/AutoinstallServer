package prepare.controller;

import config.controller.ConfigController;
import config.model.CommonConfig;
import org.junit.Before;
import org.junit.Test;
import prepare.service.PrepareService;

import static org.junit.Assert.*;

public class PrepareControllerTest {

    PrepareController prepareController;
    CommonConfig commonConfig = null;


    @Before
    public void setUp() throws Exception {
        commonConfig = ConfigController.getConfig("d:/#GIT_HUB/AutoinstallServer/config/config.xml");
        prepareController = new PrepareController();
    }

    @Test
    public void testPrepare() throws Exception {
        prepareController.prepare(commonConfig);
    }
}