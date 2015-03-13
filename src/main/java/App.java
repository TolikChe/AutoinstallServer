import config.controller.ConfigController;
import config.model.CommonConfig;

/**
 * Created by echo on 28.02.2015.
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Start");
        CommonConfig commonConfig = ConfigController.getConfig("d:/#GIT_HUB/AutoinstallServer/config/config.xml");




    }
}
