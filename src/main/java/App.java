import config.controller.ConfigController;
import config.model.CommonConfig;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import prepare.controller.PrepareController;
import subsystems.controller.SubsystemsSetupController;
import gui.controller.Controller;

/**
 * Created by echo on 28.02.2015.
 */
public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        final FXMLLoader loader = new FXMLLoader(getClass().getResource("gui.fxml"));
        Parent root = loader.load();

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Hello World");
        primaryStage.show();

        Controller controller = loader.getController();
        controller.initStage(primaryStage);
    }

    public static void main(String[] args) {

        launch(args);

        /*
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
        */
    }
}
