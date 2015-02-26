package subsystems.services.impl;

import config.model.SubsystemConfig;
import subsystems.services.SetupService;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Created by Anatoly.Cherkasov on 26.01.2015.
 * Класс, описывающий работу с подсистемой SCR_CMS.
 */
public class SetupServiceScrCms implements SetupService {

    // Используется для подготовки перед клонированием
    private static void removeDirectory(File dir) {
        if (dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null && files.length > 0) {
                for (File aFile : files) {
                    removeDirectory(aFile);
                }
            }
            dir.delete();
        } else {
            dir.delete();
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
    public String cloneSubsystem(SubsystemConfig subsystem, String destination, Boolean deleteBeforeClone) throws Exception{

        // Получим тип подсистемы, которую надо склонировать
        String subsType = subsystem.getType();
        // Получим метку подсистемы
        String subsTag = subsystem.getTag();
        // Получим ветку подсистемы
        String subsBranch = subsystem.getBranch();
        // Получим git-ссылку на подсисетму
        String subsGitUrl = subsystem.getGitUrl();
        // Получаем порядковый номер подсистемы
        int subsOrder = subsystem.getOrder();

        //------ Удаляем если надо директорию,куда будем клонировать данные
        if (deleteBeforeClone) {
            removeDirectory(new File(destination));
        }

        // ----- Конструируем команду для клонирования ----
        // Если заданы метка и ветка предупредим об этом пользователя
        if (!subsTag.isEmpty() && !subsBranch.isEmpty()){
            System.out.println("Внимание! Заданы метка и ветка одновременно. Будет использована метка.");
        }
        // Папка, куда будет перенесено содержимое подсистемы в гите
        String dirName = destination;
        // Строим команду для получения исходников из гита
        String command = "";
        // Если задана метка то достаем источник по метке
        if (!subsTag.isEmpty()){
            dirName = dirName + "/" + subsOrder + "_" + subsType + "_" + subsTag;
            command = "git clone --verbose  --single-branch --branch " + subsTag + " " + subsGitUrl + " " + dirName;
        }
        // Если задана ветка но не задана метка то достаем по имени ветки
        else if (!subsBranch.isEmpty()){
            dirName = dirName + "/" + subsOrder + "_" + subsType + "_" + subsBranch;
            command = "git clone --verbose --single-branch --branch " + subsBranch + " " + subsGitUrl + " " + dirName;
        }

        // Вызываем команду клонирования
        try {
            System.out.println(command);
            Process process = Runtime.getRuntime().exec(command);

            // Вывод текста из консоли
            /*
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                System.out.println(line);
            }
            */
            process.waitFor();
        } catch (Exception e) {
            throw new Exception("Ошибка при клонирование подсистемы " + subsType +
                                "\nКоманда клонирования: " + command +
                                "\nМесто назначения клонирования: " + dirName +
                                "\n" + e.getMessage());
        }

        // Если мы добрались сюда то значит все ок и возвращаем нужный путь.
        return dirName;
    }


    /**
     * Подготавливаем текст подсистемы. Деалем из Source Distrib.
     * Переносим в в папку подсистемы файл cms_install_values и вызываем distib10.bat что бы исходники превратились в готовые файлы
     *
     * @param subsystem Подсистема, чьи исходники надо подготовить
     * @param scrDestination Путь, куда была склонирована подсистема
     * @return TRUE - если все успешно
     */
    @Override
    public boolean prepareSources(SubsystemConfig subsystem, String scrDestination) throws Exception{

        // Перенесем в склонированную папку файл с параметрами
        String command;

        try {
            Files.copy(Paths.get( subsystem.getInstallValuesDirectory() + "/" + subsystem.getInstallValuesFileName()), Paths.get(scrDestination + "/Srv_Part/Source/" + subsystem.getInstallValuesFileName() ), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new Exception("Ошибка при копировании файла с параметрами в папку с клонированной подсистемой" +
                    "\nФайл cms_install_values: " + subsystem.getInstallValuesFileName() +
                    "\nМесто назначения : " + scrDestination + "/Srv_Part/Source/cms_install_values.sql" +
                    "\n" + e.getMessage());
        }
        //
        // Подготавливаем команду
        command = scrDestination + "/Srv_Part/Source/" + subsystem.getDistribFileName() ;
        try {
            System.out.println(command);
            Process process = Runtime.getRuntime().exec(command);
            /*
            // Вывод текста из консоли
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = bufferedReader.readLine()) != null)
            {
                System.out.println(line);
            }
            */
            process.waitFor();
        } catch (Exception e) {
            throw new Exception("Ошибка при выполнении файла " + subsystem.getDistribFileName() +
                    "\nКоманда : " + command +
                    "\n" + e.getMessage());
        }

        return true;
    }
}
