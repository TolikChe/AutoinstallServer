package subsystems.controller;

import config.controller.ConfigController;
import config.model.CommonConfig;
import org.junit.Before;
import org.junit.Test;
import prepare.controller.PrepareController;

import static org.junit.Assert.*;

public class SubsystemsSetupControllerTest {

    CommonConfig commonConfig;

    @Before
    public void setUp() throws Exception {
        // Прочитаем конфигурацию
        commonConfig = ConfigController.getConfig("d:/#GIT_HUB/AutoinstallServer/config/config.xml");

        // Выполним подготовку перед установкой
        PrepareController prepareController = new PrepareController();
        prepareController.prepare(commonConfig);
    }

    @Test
    public void testSetupSubsystems() throws Exception {
        SubsystemsSetupController subsystemsSetupController = new SubsystemsSetupController();
        subsystemsSetupController.setupSubsystems(commonConfig);
    }
}