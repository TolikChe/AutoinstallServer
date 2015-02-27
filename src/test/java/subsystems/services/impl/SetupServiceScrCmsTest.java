package subsystems.services.impl;

import config.model.SubsystemConfig;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class SetupServiceScrCmsTest {

    SetupServiceScrCms setupServiceScrCms;
    SubsystemConfig subsystemConfig;
    String paramDestination = "";

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

        // Выберем мето назначение, куда будем клонировать подсистему
        paramDestination = "D:/TEMP/ZZZ";
    }

    /**
     * Проверим как работает клонирование подсистемы
     */
    @Test
    public void testCloneSubsystem() throws Exception {
        // Сначала склонируем подсистему
        setupServiceScrCms.cloneSubsystem( subsystemConfig, paramDestination, true);

        // Проверка того что подсистема склонировалась верно - наличие новой папкис заданным именем и наличие в ней файлов.
        File res = new File(paramDestination);
        assertNotEquals("Проверим что клонирвоание успешно", 0, res.listFiles().length );
    }

    /**
     * Проверим как работает подготовка исходников подсистемы
     */
    @Test
    public void testPrepareSources() throws Exception {
        String destination = setupServiceScrCms.cloneSubsystem( subsystemConfig, paramDestination, true);
        setupServiceScrCms.prepareSources(subsystemConfig, destination );

        // Проверим что клонирвоание успешно
        File res = new File(paramDestination);
        assertNotEquals("Проверим что клонирвоание успешно", 0, res.listFiles().length );

        // Проверим что появлиась папка Distrib
        File dstr = new File (destination + "/Srv_Part/" + subsystemConfig.getDistribDirectory());
        assertTrue("Проверим что появлиась папка Distrib", dstr.exists() && dstr.isDirectory());

    }

    /**
     * Проверим что установка выполняется верно.
     */
    @Test
    public void testSetupSubsystem() throws Exception {
        String destination = setupServiceScrCms.cloneSubsystem( subsystemConfig, paramDestination, true);
        setupServiceScrCms.prepareSources(subsystemConfig, destination );
        setupServiceScrCms.setupSubsystem(subsystemConfig, destination );
    }
}