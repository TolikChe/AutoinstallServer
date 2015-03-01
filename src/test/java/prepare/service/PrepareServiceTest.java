package prepare.service;

import install_values.model.impl.InstallValuesScrCms;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PrepareServiceTest {

    InstallValuesScrCms installValuesScrCms;
    PrepareService prepareService;
    String instalValuesFilePath = "";

    @Before
    public void setUp() throws Exception {
        installValuesScrCms = new InstallValuesScrCms(instalValuesFilePath);
        prepareService = new PrepareService();
    }

    @Test
    public void testPrepareBase() throws Exception {
        prepareService.prepareBase(installValuesScrCms);
    }
}