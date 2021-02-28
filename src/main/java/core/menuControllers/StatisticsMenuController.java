/**
 * @author Adam Śmieja
 * @version 1.0
 */
package core.menuControllers;

import core.managers.ControllerManager;
import core.player.PlayerAttributes;
import interfaces.Controller;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import other.Constants;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class StatisticsMenuController implements Controller {

    @FXML private Button button;
    @FXML private Text highScore;
    @FXML private Text lifetimeFairies;
    @FXML private Text accountBalance;
    @FXML private Text fairiesSpawning;
    @FXML private Text fairyValue;
    @FXML private Text playerSpeed;
    @FXML private Text lifetimeScore;

    /**
     * Volá methodu, která inicializuje FXML soubor a statistiky hráče
     *
     * @param url
     * @param resourceBundle
     */
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        highScore.setText(Double.toString(myWriter.getHighScore()));
        lifetimeFairies
          .setText(Integer.toString(myWriter.getWingManCollected()));
        accountBalance
          .setText(Integer.toString(PlayerAttributes.getAccountBalance()));
        fairiesSpawning
          .setText(Integer.toString(PlayerAttributes.getNumberOfWingMan()));
        fairyValue
          .setText(Integer.toString(PlayerAttributes.getWingManValue()));
        playerSpeed.setText(Double.toString(PlayerAttributes.getSpeedX()));
        lifetimeScore.setText(Double.toString(myWriter.getLifeTimeScore()));
    }

    /**
     * Volá se stiskem tlačítka a vrací nás zpět do hlavního menu
     */
    public void backPressed() {
        ControllerManager cm = null;
        try {
            cm = new ControllerManager(Constants.MAIN_MENU_FXML_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) button.getScene().getWindow();
        cm.getRoot().requestFocus();
        Scene scene = cm.getMainScene();
        stage.setScene(scene);
    }

}
