package subsystems.services.impl;

import config.model.SubsystemConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Created by Anatoly.Cherkasov on 26.01.2015.
 * Класс, описывающий работу с подсистемой CMS_DEBUG.
 */
public class SetupServiceCmsDebug extends SetupServiceImpl {

    // Объявляем переменную логгера
    private Logger log = LoggerFactory.getLogger(SetupServiceCmsDebug.class);

    /**
     * Устанавливаем подсистему.
     * Запускаем механизм установки подсистемы
     *
     * @param subsystem   Подсистема, которую будем устанавливать. Её исходники должны быть предваритально склонированы и подготовлены.
     * @param destination Путь, куда была склонирована подсистема
     * @return TRUE - если все успешно
     */
    @Override
    public void setupSubsystem(SubsystemConfig subsystem, String destination) throws Exception {

        log.info("Выполняется установка подсистемы " + subsystem.getOrder() +"_"+ subsystem.getType());

        // Перенесем в склонированную папку файл с параметрами
        String command;

        //
        // К сожалению в связи с особенностями системы батник может быть запущен только локально.
        // Поэтому надо сначала перейти в соответствующую папку а потом запускать батник
        // Подготавливаем команду
        command = "cmd /c \"cd /d " + destination + " && cms_debug_setup.bat\"";

        try {
            log.info(command);
            Process process = Runtime.getRuntime().exec(command);


            // Вывод текста из консоли
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                log.info(line);
            }

            process.waitFor();
        } catch (Exception e) {
            throw new Exception("Ошибка при выполнении команды. Команда : " + command + "\n" + e.getMessage());
        }
    }


    /**
     * Копирование папки
     * @param sourceLocation
     * @param targetLocation
     * @throws IOException
     */
    private void copyDirectory(File sourceLocation , File targetLocation) throws IOException {

        if (sourceLocation.isDirectory()) {
            if (!targetLocation.exists()) {
                Files.createDirectories(targetLocation.toPath());
            }

            String[] children = sourceLocation.list();
            for (int i=0; i<children.length; i++){
                copyDirectory(new File(sourceLocation, children[i]), new File(targetLocation, children[i]));
            }
        } else {
            Files.copy( sourceLocation.toPath() , targetLocation.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    /**
     * Клонирование исходников подсистемы в указанную папку
     *
     * @param subsystem   Подсистема, чьи файлы надо склонировать
     * @param destination Папка в которую надо склонировать подсистему
     * @return Путь по которому склонирована подсистема. Если NULL то ошибка клонирования
     */
    @Override
    public String cloneSubsystem(SubsystemConfig subsystem, String destination) throws Exception {
        log.info("Выполняется клонировение подсистемы " + subsystem.getOrder() +"_"+ subsystem.getType());

        // Получим тип подсистемы, которую надо склонировать
        String subsType = subsystem.getType();
        // Получим метку подсистемы
        String subsTag = subsystem.getTag();
        // Получим git-ссылку на подсисетму
        String subsGitUrl = subsystem.getGitUrl() + "/" + subsTag;
        // Получаем порядковый номер подсистемы
        int subsOrder = subsystem.getOrder();

        // ----- Конструируем команду для клонирования ----
        // Папка, куда будет перенесено содержимое подсистемы в гите
        String dirName = destination;
        // Строим команду для получения исходников из гита
        String command = "";
        // Если задана метка то достаем источник по метке
        dirName = dirName + "/" + subsOrder + "_" + subsType + "_" + subsTag;

        log.info("Копирование папки " + subsGitUrl + " в папку " + dirName);

        copyDirectory(new File(subsGitUrl), new File(dirName));

        // Если мы добрались сюда то значит все ок и возвращаем нужный путь.
        return dirName;
    }

    /**
     * Деалем из Source Distrib.
     * Переносим в в папку подсистемы файл cms_install_values. Для подсистемы CMS_DEBUG не нужно выполнять distrib.bat
     *
     * @param subsystem   Подсистема, чьи исходники надо подготовить. Её исходники должны быть предваритально склонированы
     * @param destination Путь, куда была склонирована подсистема
     * @return TRUE - если все успешно
     */
    @Override
    public void makeDistrib(SubsystemConfig subsystem, String destination) throws Exception {
        log.info("Выполняется создание дистрибутива подсистемы " + subsystem.getOrder() +"_"+ subsystem.getType());

        try {
            log.info("copy " + Paths.get(subsystem.getInstallValuesFileName()) + " to " + Paths.get(destination + "/" + new File(subsystem.getInstallValuesFileName()).getName()) );
            Files.copy(Paths.get(subsystem.getInstallValuesFileName()), Paths.get(destination + "/" + new File(subsystem.getInstallValuesFileName()).getName()), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new Exception("Ошибка при копировании файла с параметрами в папку с клонированной подсистемой" +
                    "\nФайл cms_install_values: " + subsystem.getInstallValuesFileName() +
                    "\nМесто назначения : " + destination + "/Srv_Part/Source/" + new File(subsystem.getInstallValuesFileName()).getName() +
                    "\n" + e.getMessage());
        }
    }
}
