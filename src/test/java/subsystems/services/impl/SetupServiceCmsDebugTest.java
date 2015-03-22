package subsystems.services.impl;

import config.controller.ConfigController;
import config.model.CommonConfig;
import config.model.SubsystemConfig;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import prepare.controller.PrepareController;

import java.io.File;

import static org.junit.Assert.*;

public class SetupServiceCmsDebugTest {


    SetupServiceCmsDebug setupServiceCmsDebug;
    SubsystemConfig subsystemConfigCmsDebug;
    String paramDestination = "";

    @Before
    public void setUp() throws Exception {
        setupServiceCmsDebug = new SetupServiceCmsDebug();

        // Прочитаем конфигурацию
        CommonConfig commonConfig = ConfigController.getConfig("d:/#GIT_HUB/AutoinstallServer/config/config.xml");

        // Выполним подготовку перед установкой
        PrepareController prepareController = new PrepareController();
        prepareController.prepare(commonConfig);

        // Наполним объект данными
        subsystemConfigCmsDebug = (commonConfig.getSubsystemConfigTreeMap()).get(3);
        // Выберем мето назначение, куда будем клонировать подсистему
        paramDestination = commonConfig.getDestinationDirectory();
    }

    @Ignore
    @Test
    public void testSetupSubsystem() throws Exception {

    }

    @Test
    public void testCloneSubsystem() throws Exception {
        // Сначала склонируем подсистему
        String destination =  setupServiceCmsDebug.cloneSubsystem( subsystemConfigCmsDebug, paramDestination);

        // Проверка того что подсистема склонировалась верно - наличие новой папкис заданным именем и наличие в ней файлов.
        File res = new File(destination);
        assertNotEquals("Проверим что клонирвоание успешно", 0, res.listFiles().length );
    }

    @Test
    public void testMakeDistrib() throws Exception {
        String destination = setupServiceCmsDebug.cloneSubsystem( subsystemConfigCmsDebug, paramDestination);
        setupServiceCmsDebug.makeDistrib(subsystemConfigCmsDebug, destination);

        // Проверим что клонирвоание успешно
        File res = new File(paramDestination);
        assertNotEquals("Проверим что клонирвоание успешно", 0, res.listFiles().length );

        // Проверим что появлиась папка Distrib
        File dstr = new File (destination + "/cms_install_values.sql");
        System.out.println(dstr.lastModified());
        //assertTrue("Проверим что появлиась папка Distrib", dstr.exists() && dstr.isFile() && dstr.lastModified() == );
    }
}