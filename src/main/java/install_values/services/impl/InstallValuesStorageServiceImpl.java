package install_values.services.impl;

import install_values.services.StorageService;
import install_values.model.InstallValues;
import install_values.model.impl.*;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Anatoly.Cherkasov on 22.01.2015.
 */
public class InstallValuesStorageServiceImpl implements StorageService {
    /**
     * Прочитаем конфиг из файла в объект
     */
    @Override
    public InstallValues readFromFile(String filename) throws Exception{

        // Надо разобрать путь к файлу cms_install_values
        // В пути будет имя папки совпадающее с именем подсистемы.
        // Из имени папки можно понять что за подсистема
        if ( filename.contains("SCR_CMS") && !filename.contains("CMS_SRV_DB") && !filename.contains("CMS_DS_ADAPTER") ){
            try {
                // Инициализация нового объекта
                InstallValuesScrCms installValuesScrCms = new InstallValuesScrCms(filename);
                // Прочитаем файл в набор строк
                List<String> stringList = Files.readAllLines(Paths.get(filename), StandardCharsets.UTF_8);
                // Наполним объект на оcнове строк
                installValuesScrCms.fromString(stringList);
                // Вернем наполненный объект
                return installValuesScrCms;
            } catch (Exception e) {
                throw new Exception("Ошибка при чтении файла cms_install_values для подсистемы SCR_CMS \n" + e.getMessage());
            }
        }

        else if ( !filename.contains("SCR_CMS") && !filename.contains("CMS_SRV_DB") && filename.contains("CMS_DS_ADAPTER") ) {
            try {
                // Инициализация нового объекта
                InstallValuesAdapter2Bis installValuesAdapter2Bis = new InstallValuesAdapter2Bis(filename);
                // Прочитаем файл в набор строк
                BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
                String line;
                List<String> stringList = new ArrayList<String>(15);
                while ((line = reader.readLine() ) != null) {
                    stringList.add(line);
                }
                // Наполним объект на сонове строк
                installValuesAdapter2Bis.fromString(stringList);
                // Вернем наполненный объект
                return installValuesAdapter2Bis;
            } catch (Exception e) {
                throw new Exception("Ошибка при чтении файла cms_install_values для подсистемы CMS_SRV_DB");
            }
        }

        else if ( !filename.contains("SCR_CMS") && filename.contains("CMS_SRV_DB") && !filename.contains("CMS_DS_ADAPTER") ) {
            try {
                // Инициализация нового объекта
                InstallValuesCmsSrvDb installValuesCmsSrvDb = new InstallValuesCmsSrvDb(filename);
                // Прочитаем файл в набор строк
                BufferedReader reader = new BufferedReader(new FileReader(new File(filename)));
                String line;
                List<String> stringList = new ArrayList<String>(15);
                while ((line = reader.readLine() ) != null) {
                    stringList.add(line);
                }
                // Наполним объект на сонове строк
                installValuesCmsSrvDb.fromString(stringList);
                // Вернем наполненный объект
                return installValuesCmsSrvDb;
            } catch (Exception e) {
                throw new Exception("Ошибка при чтении файла cms_install_values для подсистемы CMS_DS_ADAPTER");
            }
        }

        throw new Exception("Ошибка при раcпозновании типа подсистемы.\nИмя файла: " + filename);
    }

    /**
     * Сохраним объект описывающий cms_install_values в файл
     */
    @Override
    public void writeToFile(InstallValues installValues, String filename) throws Exception{

        try {
            FileWriter fileInstallValues = new FileWriter (new File(filename));
            // Запишем объект в файл
            fileInstallValues.write(installValues.toString());
            fileInstallValues.close();
        } catch (IOException e) {
            throw new Exception("Ошибка при сохранении файла cms_install_values.\nИмя файла: " + filename);
        }
    }

    /**
     * Проверяем что бы все обязательные поля в конфиге были заполнены
     *
     * @return Если true то проверка пройдена, если false то не пройдена
     */
    @Override
    public boolean checkData(InstallValues installValues ) {
        return installValues.validate();
    }

}
