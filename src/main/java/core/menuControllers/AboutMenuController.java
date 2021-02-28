/**
 * @author Adam Śmieja
 * @version 1.0
 */
package core.menuControllers;

import core.managers.ControllerManager;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import other.Constants;
import java.io.IOException;

public class AboutMenuController {

    @FXML private Button back;

    /**
     * Volá se při stisku tlačítka a vrací nás do hlavního menu.
     */
    public void backPressed() {
        ControllerManager cm = null;
        try {
            cm = new ControllerManager(Constants.MAIN_MENU_FXML_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) back.getScene().getWindow();
        cm.getRoot().requestFocus();
        Scene scene = cm.getMainScene();
        stage.setScene(scene);
    }

}
