package subsystems.services.impl;

import config.model.SubsystemConfig;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class SetupServiceScrCmsTest {

    SetupServiceScrCms setupServiceScrCms;
    SubsystemConfig subsystemConfig;

    @Before
    public void setUp() throws Exception {
        setupServiceScrCms = new SetupServiceScrCms();
        subsystemConfig = new SubsystemConfig();

        // Наполним объект данными
        subsystemConfig.setType("SCR_CMS");
        subsystemConfig.setOrder(0);
        subsystemConfig.setGitUrl("http://git.billing.ru/Repositories/peterservice/SCR_CMS.git");
        subsystemConfig.setBranch("master");
        subsystemConfig.setTag("");
        subsystemConfig.setSetupMode("install");
        subsystemConfig.setInstallValuesFileName("cms_install_values.sql");
        subsystemConfig.setInstallValuesDirectory("d:/#GIT_HUB/AutoinstallServer/config/SCR_CMS");
        subsystemConfig.setDistribFileName("distrib10.bat");
        subsystemConfig.setDistribDirectory("Distrib10");

    }

    /**
     * Проверим как работает клонирование подсистемы
     */
    @Test
    public void testCloneSubsystem() throws Exception {
        setupServiceScrCms.cloneSubsystem( subsystemConfig, "D:/TEMP/ZZZ", true);
    }

    /**
     * Проверим как работает подготовка исходников подсистемы
     */
    @Ignore
    @Test
    public void testPrepareSources() throws Exception {
        String destination = setupServiceScrCms.cloneSubsystem( subsystemConfig, "D:/TEMP/ZZZ", true);
        setupServiceScrCms.prepareSources(subsystemConfig, destination );
    }
}