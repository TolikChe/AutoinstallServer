package install_values.services.impl;

import install_values.model.impl.InstallValuesScrCms;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class StorageServiceImplTest {

    StorageServiceImpl storageService;

    @Before
    public void setUp() throws Exception {
        storageService = new StorageServiceImpl();
    }

    @Test
    /**
     * Прочитаем файл cms_install_values.sql с параметрами
     */
    public void testReadFromFile() throws Exception {
        InstallValuesScrCms installValuesScrCms = (InstallValuesScrCms) storageService.readFromFile("c:/Users/echo/IdeaProjects/AutoinstallServer/config/SCR_CMS/cms_install_values.sql");

        assertEquals("Проверим что прочитан SysUser", "SYS", installValuesScrCms.getSysUser());
        assertEquals("Проверим что прочитан CrmUser", "CRM_DAILY", installValuesScrCms.getCrmUser());
        assertEquals("Проверим что прочитан AppUser", "CRM_DAILY_APP", installValuesScrCms.getAppUser());

        assertEquals("Проверим что прочитан DBlink", "samara.NET.BILLING.RU", installValuesScrCms.getCrmToCommonLink());
    }

    @Ignore
    @Test
    public void testWriteToFile() throws Exception {

    }

    @Ignore
    @Test
    public void testCheckData() throws Exception {

    }
}