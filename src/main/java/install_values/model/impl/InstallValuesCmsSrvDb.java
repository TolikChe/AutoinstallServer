package install_values.model.impl;

import install_values.model.InstallValues;

import java.io.File;
import java.util.List;

/**
 * Created by Anatoly.Cherkasov on 23.01.2015.
 */
public class InstallValuesCmsSrvDb implements InstallValues {

    /**
     * Имя файла конфигурации
     */
    private String filename;
    /**
     * Имя системного пользователя
     */
    private String sysUser;
    /**
     * Пароль для системного пользователя
     */
    private String sysPswd;
    /**
     * Тип подключения
     */
    private String sysCntp;
    /**
     * База на которой находится подсистема SCR_CMS
     */
    private String crmBase;
    /**
     * Имя схемы для подсистемы SCR_CMS
     */
    private String crmUser;
    /**
     * Пароль для схемы SCR_CMS
     */
    private String crmPswd;
    /**
     * Имя тейблспейса для объектов
     */
    private String defTbs;
    /**
     * Имя тейблспейса для индексов
     */
    private String idxTbs;
    /**
     * Роль для схемы аутентификации
     */
    private String authRole;
    /**
     * Роль для схемы через которую работает CMS_ADMIN
     */
    private String appRole;
    /**
     * Схема для аутентификации
     */
    private String authUser;
    /**
     * Схема через которую работает CMS_ADMIN
     */
    private String appUser;
    /**
     * Пароль для схемы через которую работает CMS_ADMIN
     */
    private String appPswd;
    /**
     *
     */
    private String apiRole;
    /**
     * База где стоит подсистема CDM_LOADER
     */
    private String cdmBase;
    /**
     * Схема где стоит подсистема CDM_LOADER
     */
    private String cdmUser;
    /**
     * Пароль для схема где стоит подсистема CDM_LOADER
     */
    private String cdmPswd;
    /**
    *	Значение параметра Time_Translate
    */
    private String timeTranslate;

    /**
     * Конструктор с проверкой существования файла
     *
     * @param filename Файл в котором находится конфиг подсистемы
     */
    public InstallValuesCmsSrvDb(String filename) throws Exception {

        File file = new File(filename);
        if (!(file.exists() && file.isFile())) {
            throw new Exception("Ошибка InstallValuesCmsSrvDb. Файл (" + file.getAbsoluteFile() + ") по указанному пути не найден ");
        }
        this.filename = file.getAbsoluteFile().toString();
    }


    public String toString() {
        return "";
    }


    public void fromString (List<String> stringList) throws RuntimeException{
    }

    /**
     * Проверка того что объект валиден по своему наполнению
     *
     * @return TRUE - если успешно
     */
    public boolean validate() {
        return false;
    }
}
