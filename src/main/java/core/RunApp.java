/**
 * @author Adam Śmieja
 * @version 1.0
 */
package core;

import core.managers.ControllerManager;
import javafx.application.Application;
import javafx.stage.Stage;
import other.Constants;
import java.io.IOException;

public class RunApp extends Application {
    /**
     * Vstupní bod při spuštění programu
     * @param args vstupní argumetny
     */
    public static void main(String[] args) {
        Application.launch(args);
    }

    /**
     * Start FX programu
     * @param stage hlavní Stage
     */
    @Override public void start(Stage stage) {
        ControllerManager cm = null;
        try {
            cm = new ControllerManager(Constants.MAIN_MENU_FXML_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage = cm.getMainStage();
        stage.setResizable(false);
        stage.setTitle("Coin Apocalypse");
        stage.show();
    }

}
