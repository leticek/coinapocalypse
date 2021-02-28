/**
 * @author Adam Śmieja
 * @version 1.0
 */
package core.menuControllers;

import core.managers.ObjectManager;
import core.objects.MyObject;
import core.objects.SpikeMan;
import core.objects.WingMan;
import core.player.Player;
import core.player.PlayerAttributes;
import interfaces.Controller;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import other.Constants;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class GameController implements Controller {

    @FXML private AnchorPane rootPane;
    @FXML private Text score;
    private Player player = new Player(Constants.PLAYER_STAND);
    private ObjectManager wingManObjectManager;
    private ObjectManager spikeManObjectManager;
    private Random randomX = new Random();
    private Random randomY = new Random();
    private AnimationTimer timer3;
    private boolean jumpPressed = false;

    /**
     * Volá methodu, která inicializuje FXML soubor
     *
     * @param url
     * @param resourceBundle
     */
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        rootPane.getChildren().add(player.getTexture());
        generateWingManObjectManager();
        generateSpikeManObjectManager();
        player.move();
        checkCollision();
    }

    /**
     * Metoda která kontroluje kolizi hráče a přičítá skóre.
     */
    private void checkCollision() {
        timer3 = new AnimationTimer() {
            @Override public void handle(long now) {
                for (MyObject spikeMan : spikeManObjectManager
                  .getObjectArrayList()) {
                    if (player.getTexture()
                      .intersects(spikeMan.getTexture().getBoundsInParent())) {
                        timer3.stop();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass()
                          .getResource(Constants.DEATH_MENU_FXML_PATH));
                        Parent root = null;
                        try {
                            root = fxmlLoader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        DeathController controller = fxmlLoader.getController();
                        controller.setPlayerResults(player);
                        Scene scene = new Scene(root);
                        Stage stage = (Stage) rootPane.getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    }
                }

                for (MyObject wingMan : wingManObjectManager
                  .getObjectArrayList()) {
                    if (player.getTexture()
                      .intersects(wingMan.getTexture().getBoundsInParent())) {
                        wingMan.getTexture()
                          .setY(((randomY.nextInt(1000))) * (-1));
                        player.setScore(player.getScore() + PlayerAttributes
                          .getWingManValue());
                        PlayerAttributes.setAccountBalance(PlayerAttributes
                          .getAccountBalance() + PlayerAttributes
                          .getWingManValue());
                        player.setWingManPicked(player.getWingManPicked() + 1);
                    }
                }
                player.setScore(player.getScore() + 0.1);
                score.setText(Double.toString((int) player.getScore()));
            }
        };
        timer3.start();
    }

    /**
     * Vygeneruje daný počet "víl" ke sbírání a rozpohybuje je.
     */
    private void generateWingManObjectManager() {
        this.wingManObjectManager = new ObjectManager();

        for (int i = 0; i < PlayerAttributes.getNumberOfWingMan(); i++) {
            this.wingManObjectManager.addCollisionObject(
              new WingMan(Constants.WING_MAN,
                randomX.nextInt((Constants.WIDTH) + 1),
                randomY.nextInt(1000) * (-1)));
        }

        for (MyObject wingMan : wingManObjectManager.getObjectArrayList()) {
            rootPane.getChildren().add(wingMan.getTexture());
            wingMan.move();
        }
    }

    /**
     * Vygeneruje nepřítele a rozpohybuje je.
     */
    private void generateSpikeManObjectManager() {
        this.spikeManObjectManager = new ObjectManager();

        for (int i = 0; i < Constants.NUM_OF_SPIKE_MAN; i++) {
            this.spikeManObjectManager.addCollisionObject(
              new SpikeMan(Constants.SPIKE_MAN,
                randomX.nextInt((Constants.WIDTH) + 1),
                randomY.nextInt(1000) * (-1)));
        }
        for (MyObject spikeMan : spikeManObjectManager.getObjectArrayList()) {
            rootPane.getChildren().add(spikeMan.getTexture());
            spikeMan.move();
        }
    }

    /**
     * Metoda snímá stisky kláves a podle toho nastavuje rychlost.
     * @param keyEvent stiknutá klávesa
     */
    @FXML public void handleKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.A) || keyEvent.getCode()
          .equals(KeyCode.LEFT)) {
            player.setVelocity(PlayerAttributes.getSpeedX(), 0);
            player
              .setVelocity(player.getVelocityX() * (-1), player.getVelocityY());
            this.player.changeTexture(Constants.PLAYER_RUN_LEFT_0);
        }

        if (keyEvent.getCode().equals(KeyCode.D) || keyEvent.getCode()
          .equals(KeyCode.RIGHT)) {
            player.setVelocity(PlayerAttributes.getSpeedX(), 0);
            player.setVelocity(player.getVelocityX(), player.getVelocityY());
            this.player.changeTexture(Constants.PLAYER_RUN_RIGHT_0);
        }

        if (keyEvent.getCode().equals(KeyCode.W) || keyEvent.getCode()
          .equals(KeyCode.UP) || keyEvent.getCode().equals(KeyCode.SPACE)) {
            if (player.getTexture().getY() == 380) {
                jumpPressed = true;
                player.setVelocity(player.getVelocityX(), 5);
                this.player.changeTexture(Constants.PLAYER_JUMP);
            }
        }
    }

    /**
     * Metoda snímá uvolnění kláves a podle toho nastavuje rychlost.
     * @param keyEvent uvolněná klávesa
     */
    @FXML public void handleKeyReleased(KeyEvent keyEvent) {
        if (keyEvent.getCode().equals(KeyCode.A) || keyEvent.getCode()
          .equals(KeyCode.LEFT)) {
            player.setVelocity(0, 0);
            this.player.changeTexture(Constants.PLAYER_STAND);
        }

        if (keyEvent.getCode().equals(KeyCode.D) || keyEvent.getCode()
          .equals(KeyCode.RIGHT)) {
            player.setVelocity(0, 0);
            this.player.changeTexture(Constants.PLAYER_STAND);
        }

        if (keyEvent.getCode().equals(KeyCode.W) || keyEvent.getCode()
          .equals(KeyCode.UP) || keyEvent.getCode().equals(KeyCode.SPACE)) {
            jumpPressed = false;
            player.setVelocity(player.getVelocityX(), 0);
            player.setGravity(3);
            this.player.changeTexture(Constants.PLAYER_FALL);
        }
    }
}





