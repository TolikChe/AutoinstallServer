package config.services.impl;

import config.model.CommonConfig;
import config.model.SubsystemConfig;
import config.services.StorageService;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;

/**
 * Created by Anatoly.Cherkasov on 26.01.2015.
 * Этот сервис умеет: считывать файл конфигурации,
 *                    cохранять файл конфигурации,
 *                    выделять из файла конфигурации отдельные подсистемы.
 */
public class StorageServiceImpl implements StorageService{

    /**
     * Прочитать конфигурацию из файла
     * @param configFilename Имя файла с конфигурацией
     * @return Объект, содержащий файл конфигурации
     */
    public CommonConfig readFromFile (String configFilename) throws RuntimeException {
        CommonConfig cfg = new CommonConfig();

        // Открываем файл конфига и читаем из него параметры
        File file = new File(configFilename);
        if (!(file.exists() && file.isFile())) {
            throw new RuntimeException("Ошибка. Файл (" + file.getAbsoluteFile() + ") по указанному пути не найден ");
        }
        // Сохраним путь к конфигу в объекте конфига
        String filename = file.getAbsoluteFile().toString();
        cfg.setConfigFilename( filename );

        File fXmlFile = new File(filename);

        DocumentBuilderFactory dbFactory;
        DocumentBuilder dBuilder;
        Document doc;

        try {
            dbFactory = DocumentBuilderFactory.newInstance();
            dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(fXmlFile);
        } catch (Exception ex) {
            throw new RuntimeException("Ошибка при разборе файла " + filename );
        }

        //optional, but recommended
        //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
        doc.getDocumentElement().normalize();
        //

        // Получаем значения из файла парметров
        // Сохраним имя конфигурации
        cfg.setConfigName(doc.getDocumentElement().getAttribute("name"));
        // Сохраним путь, в который должны быть скопированы исходники
        cfg.setDestinationDirectory(doc.getElementsByTagName("destination_directory").item(0).getTextContent());
        // Сохраним путь, по которому находятся вспомогательные файлы
        cfg.setPrepareDirectory(doc.getElementsByTagName("prepare_directory").item(0).getTextContent());

        /*
        File file = new File(prepareDirectory);
        // Если папки не существует то создадим её
        if (!(file.exists() && file.isDirectory())) {
            throw new Exception("Ошибка. Папка (" + file.getAbsoluteFile() + ") по указанному пути не существует ");
        }
        this.prepareDirectory = file.getAbsoluteFile().toString();
        */

        // Получим список подсистем в файле конфига
        NodeList nList = doc.getElementsByTagName("subsystem");

        // Перебираем список подсистем
        for (int temp = 0; temp < nList.getLength(); temp++) {

            Node nNode = nList.item(temp);

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                Element eElement = (Element) nNode;

                // Наполним объект "Настроечные параметры подсистемы"
                SubsystemConfig subsCfg = new SubsystemConfig();
                subsCfg.setType(eElement.getAttribute("type"));
                subsCfg.setOrder(Integer.parseInt(eElement.getElementsByTagName("order").item(0).getTextContent()));
                subsCfg.setBranch(eElement.getElementsByTagName("branch").item(0).getTextContent());
                subsCfg.setTag(eElement.getElementsByTagName("tag").item(0).getTextContent());
                subsCfg.setGitUrl(eElement.getElementsByTagName("git_url").item(0).getTextContent());
                subsCfg.setSetupMode(eElement.getElementsByTagName("setup_mode").item(0).getTextContent());
                subsCfg.setInstallValuesFileName(eElement.getElementsByTagName("install_values_file").item(0).getTextContent());
                subsCfg.setInstallValuesDirectory(eElement.getElementsByTagName("install_values_directory").item(0).getTextContent());
                subsCfg.setDistribFileName(eElement.getElementsByTagName("distrib_file").item(0).getTextContent());
                subsCfg.setDistribDirectory(eElement.getElementsByTagName("distrib_directory").item(0).getTextContent());
                // Добавить подсистему в список подсистем
                cfg.addSubsystemToList(subsCfg.getOrder(), subsCfg);
            }
        }
        // Возвращаем полученную конфигурацию
        return cfg;
    }
}
