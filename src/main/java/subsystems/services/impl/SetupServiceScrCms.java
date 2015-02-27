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
public class SetupServiceScrCms extends SetupServiceImpl {


    /**
     * Устанавливаем подсистему.
     * Запускаем механизм установки подсистемы
     *
     * @param subsystem   Подсистема, которую будем устанавливать. Её исходники должны быть предваритально склонированы и подготовлены.
     * @param destination Путь, куда была склонирована подсистема
     * @return TRUE - если все успешно
     */
    @Override
    public boolean setupSubsystem(SubsystemConfig subsystem, String destination) throws Exception {
// Перенесем в склонированную папку файл с параметрами
        String command;

        //
        // К сожалению в связи с особенностями системы батник может быть запущен только локально.
        // Поэтому надо сначала перейти в соответствующую папку а потом запускать батник
        // Подготавливаем команду
        if (subsystem.getSetupMode().equals("install")) {
            command = "cmd /c \"cd /d " + destination + "/Srv_Part/" + subsystem.getDistribDirectory() + " && CreateCMS.bat /full\"";
        }
        else if (subsystem.getSetupMode().equals("update")) {
            command = "cmd /c \"cd /d " + destination + "/Srv_Part/" + subsystem.getDistribDirectory() + " && UpdateCMS.bat\"";
        }
        else
            throw new Exception("Ошибка при установке подсистемы SCR_CMS");
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
            throw new Exception("Ошибка при выполнении команды. Команда : " + command +
                    "\n" + e.getMessage());
        }

        return true;
    }
}
