package subsystems.services.impl;

import config.model.SubsystemConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Anatoly.Cherkasov on 26.01.2015.
 * Класс, описывающий работу с подсистемой CMS_DS_ADAPTER2BIS.
 */
public class SetupServiceCmsDsAdapter extends SetupServiceImpl {

    // Объявляем переменную логгера
    private Logger log = LoggerFactory.getLogger(SetupServiceCmsDsAdapter.class);

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
        command = "cmd /c \"cd /d " + destination + "/Srv_Part/" + subsystem.getDistribResultDirectory() + " && install.bat\"";
        //
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
}
