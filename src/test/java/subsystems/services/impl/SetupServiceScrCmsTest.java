package subsystems.services.impl;

import config.controller.ConfigController;
import config.model.CommonConfig;
import config.model.SubsystemConfig;
import org.junit.Before;
import org.junit.Test;
import prepare.controller.PrepareController;

import java.io.File;

import static org.junit.Assert.*;

public class SetupServiceScrCmsTest {

    SetupServiceScrCms setupServiceScrCms;
    SetupServiceCmsSrvDb setupServiceCmsSrvDb;
    SetupServiceCmsDsAdapter setupServiceCmsDsAdapter;
    SubsystemConfig subsystemConfigScrCms;
    SubsystemConfig subsystemConfigCmsSrvDb;
    SubsystemConfig subsystemConfigCmsDsAdapter;
    String paramDestination = "";

    @Before
    public void setUp() throws Exception {
        setupServiceScrCms = new SetupServiceScrCms();
        setupServiceCmsSrvDb = new SetupServiceCmsSrvDb();

        // Прочитаем конфигурацию
        CommonConfig commonConfig = ConfigController.getConfig("d:/#GIT_HUB/AutoinstallServer/config/config.xml");

        // Выполним подготовку перед установкой
        PrepareController prepareController = new PrepareController();
        prepareController.prepare(commonConfig);

        // Наполним объект данными
        subsystemConfigScrCms = (commonConfig.getSubsystemConfigTreeMap()).get(0);
        subsystemConfigCmsSrvDb = (commonConfig.getSubsystemConfigTreeMap()).get(1);
        subsystemConfigCmsDsAdapter = (commonConfig.getSubsystemConfigTreeMap()).get(2);
        // Выберем мето назначение, куда будем клонировать подсистему
        paramDestination = commonConfig.getDestinationDirectory();
    }

    /**
     * Проверим как работает клонирование подсистемы
     */
    @Test
    public void testCloneSubsystemScrCms() throws Exception {
        // Сначала склонируем подсистему
        setupServiceScrCms.cloneSubsystem( subsystemConfigScrCms, paramDestination);

        // Проверка того что подсистема склонировалась верно - наличие новой папкис заданным именем и наличие в ней файлов.
        File res = new File(paramDestination);
        assertNotEquals("Проверим что клонирвоание успешно", 0, res.listFiles().length );
    }

    /**
     * Проверим как работает подготовка исходников подсистемы
     */
    @Test
    public void testPrepareSourcesScrCms() throws Exception {
        String destination = setupServiceScrCms.cloneSubsystem( subsystemConfigScrCms, paramDestination);
        setupServiceScrCms.makeDistrib(subsystemConfigScrCms, destination);

        // Проверим что клонирвоание успешно
        File res = new File(paramDestination);
        assertNotEquals("Проверим что клонирвоание успешно", 0, res.listFiles().length );

        // Проверим что появлиась папка Distrib
        File dstr = new File (destination + "/Srv_Part/" + subsystemConfigScrCms.getDistribResultDirectory());
        assertTrue("Проверим что появлиась папка Distrib", dstr.exists() && dstr.isDirectory());
    }

    /**
     * Проверим что установка выполняется верно.
     */
    @Test
    public void testSetupSubsystemScrCms() throws Exception {
        String destination = setupServiceScrCms.cloneSubsystem( subsystemConfigScrCms, paramDestination);
        setupServiceScrCms.makeDistrib(subsystemConfigScrCms, destination);
        setupServiceScrCms.setupSubsystem(subsystemConfigScrCms, destination );
    }

    /**
     * Проверим как работает клонирование подсистемы
     */
    @Test
    public void testCloneSubsystemCmsSrvDb() throws Exception {
        // Сначала склонируем подсистему
        setupServiceScrCms.cloneSubsystem( subsystemConfigCmsSrvDb, paramDestination);

        // Проверка того что подсистема склонировалась верно - наличие новой папкис заданным именем и наличие в ней файлов.
        File res = new File(paramDestination);
        assertNotEquals("Проверим что клонирвоание успешно", 0, res.listFiles().length );
    }

    /**
     * Проверим как работает подготовка исходников подсистемы
     */
    @Test
    public void testPrepareSourcesCmsSrvDb() throws Exception {
        String destination = setupServiceScrCms.cloneSubsystem( subsystemConfigCmsSrvDb, paramDestination);
        setupServiceScrCms.makeDistrib(subsystemConfigCmsSrvDb, destination);

        // Проверим что клонирвоание успешно
        File res = new File(paramDestination);
        assertNotEquals("Проверим что клонирвоание успешно", 0, res.listFiles().length );

        // Проверим что появлиась папка Distrib
        File dstr = new File (destination + "/Srv_Part/" + subsystemConfigCmsSrvDb.getDistribResultDirectory());
        assertTrue("Проверим что появлиась папка Distrib", dstr.exists() && dstr.isDirectory());
    }

    /**
     * Проверим что установка выполняется верно.
     */
    @Test
    public void testSetupSubsystems() throws Exception {
        String destinationScrCms = setupServiceScrCms.cloneSubsystem( subsystemConfigScrCms, paramDestination);
        setupServiceScrCms.makeDistrib(subsystemConfigScrCms, destinationScrCms);
        setupServiceScrCms.setupSubsystem(subsystemConfigScrCms, destinationScrCms );

        String destinationCmsSrvDb = setupServiceCmsSrvDb.cloneSubsystem( subsystemConfigCmsSrvDb, paramDestination);
        setupServiceCmsSrvDb.makeDistrib(subsystemConfigCmsSrvDb, destinationCmsSrvDb);
        setupServiceCmsSrvDb.setupSubsystem(subsystemConfigCmsSrvDb, destinationCmsSrvDb );

        String destinationCmsDsAdapter = setupServiceCmsDsAdapter.cloneSubsystem(subsystemConfigCmsDsAdapter, paramDestination);
        setupServiceCmsDsAdapter.makeDistrib(subsystemConfigCmsDsAdapter, destinationCmsDsAdapter);
        setupServiceCmsDsAdapter.setupSubsystem(subsystemConfigCmsDsAdapter, destinationCmsDsAdapter );
    }

}