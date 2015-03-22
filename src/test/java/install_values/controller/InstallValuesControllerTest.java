package install_values.controller;

import config.controller.ConfigController;
import config.model.CommonConfig;
import install_values.model.InstallValues;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class InstallValuesControllerTest {

    CommonConfig commonConfig = null;
    InstallValuesController installValuesController = null;

    @Before
    public void setUp() throws Exception {

        commonConfig = ConfigController.getConfig("d:/#GIT_HUB/AutoinstallServer/config/config.xml");
        installValuesController = new InstallValuesController();
    }

    @Test
    public void testGetInstallValues() throws Exception {
        installValuesController.getInstallValues(commonConfig.getSubsystemConfigTreeMap().get(0));

        assertNotNull( "Проверим что объект install_values был получен" , installValuesController.getInstallValues(commonConfig.getSubsystemConfigTreeMap().get(0)));
        assertNotNull( "Проверим что объект install_values был получен" , installValuesController.getInstallValues(commonConfig.getSubsystemConfigTreeMap().get(1)));
        assertNotNull( "Проверим что объект install_values был получен" , installValuesController.getInstallValues(commonConfig.getSubsystemConfigTreeMap().get(2)));

        assertNull( "Проверим что объект install_values был получен" , installValuesController.getInstallValues(commonConfig.getSubsystemConfigTreeMap().get(3)));
    }
}