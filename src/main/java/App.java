import config.controller.ConfigController;
import config.model.CommonConfig;
import prepare.controller.PrepareController;
import subsystems.controller.SubsystemsSetupController;

/**
 * Created by echo on 28.02.2015.
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Start");
        // Прочитаем конфигурацию
        CommonConfig commonConfig = ConfigController.getConfig("d:/#GIT_HUB/AutoinstallServer/config/config.xml");
        // Выполним подготовку
        if (!PrepareController.prepare(commonConfig))
        {
            System.out.println("Prepare fail");
            return;
        }
        // Выполним установку
        SubsystemsSetupController.setupSubsystems(commonConfig);
        System.out.println("Finish");
    }
}
