/**
 * @author Adam Śmieja
 * @version 1.0
 */
package core.menuControllers;

import core.managers.ControllerManager;
import core.player.Player;
import interfaces.Controller;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import other.Constants;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DeathController implements Controller {

    @FXML public AnchorPane rootPane;
    @FXML private Button playAgainButton;
    @FXML private Button mainMenuButton;
    @FXML private Text finalScore, fairiesPicked, highScore;

    /**
     * Volá methodu, která inicializuje FXML soubor
     *
     * @param url
     * @param resourceBundle
     */
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        myWriter.saveAndLoadPlayer();
    }

    /**
     * Nastaví výsledky za danou hru.
     * @param player hráč
     */
    public void setPlayerResults(Player player) {
        this.finalScore.setText(Double.toString((int) player.getScore()));
        this.fairiesPicked.setText(Integer.toString(player.getWingManPicked()));
        myWriter.writeHighScore(Double.toString((int) player.getScore()));
        myWriter.writeWingMan(
          (int) (player.getWingManPicked() + (player.getScore() / 10)));
        this.highScore
          .setText(Double.toString(myWriter.getHighScore()).stripTrailing());
    }

    /**
     * Volá se po stisku tlačítka a spouští další hru.
     */
    public void playAgain() {
        ControllerManager cm = null;
        try {
            cm = new ControllerManager(Constants.PLAY_FXML_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) playAgainButton.getScene().getWindow();
        cm.getRoot().requestFocus();
        Scene scene = cm.getMainScene();
        stage.setScene(scene);
    }

    /**
     * Volá se po stisku tlačítka a vrací nás do hlavního menu.
     */
    public void mainMenu() {
        ControllerManager cm = null;
        try {
            cm = new ControllerManager(Constants.MAIN_MENU_FXML_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) mainMenuButton.getScene().getWindow();
        cm.getRoot().requestFocus();
        Scene scene = cm.getMainScene();
        stage.setScene(scene);
    }
}
