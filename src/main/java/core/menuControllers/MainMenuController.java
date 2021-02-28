/**
 * @author Adam Śmieja
 * @version 1.0
 */
package core.menuControllers;

import core.managers.ControllerManager;
import core.managers.ObjectManager;
import core.objects.MovableObject;
import core.objects.MyObject;
import interfaces.Controller;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import other.Constants;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class MainMenuController implements Controller {

    @FXML private Button play;
    @FXML private Button about;
    @FXML private Button statistics;
    @FXML private Button instructions;
    @FXML private Button upgrade;
    @FXML private Button achievements;
    @FXML private AnchorPane rootPane;
    private ObjectManager objectManager;

    /**
     * Volá methodu, která inicializuje FXML soubor, přiáá mraky a načte hráče z datových souborů
     *
     * @param url
     * @param resourceBundle
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myWriter.loadPlayer();
        objectManager = new ObjectManager();
        Random rand = new Random();
        Double velocity = rand.nextDouble();
        for (int i = 0; i < 4; i++) {
            if (i % 2 == 0) velocity *= -1;
            objectManager.addMovableObject(
              new MovableObject(Constants.CLOUD_PNG_PATH, rand.nextInt(900),
                rand.nextInt(150), velocity));
        }
        rootPane.getChildren().addAll(objectManager.getImageViewArrayList());
        for (MyObject movableObject : objectManager.getObjectArrayList()) {
            movableObject.move();
        }
    }

    /**
     * Volá se stiskem tlačítka, načte hráče a vezme nás do menu statistik
     */
    public void statisticsPressed() {
        myWriter.saveAndLoadPlayer();
        ControllerManager cm = null;
        try {
            cm = new ControllerManager(Constants.STATISTICS_FXML_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) statistics.getScene().getWindow();
        Scene scene = cm.getMainScene();
        stage.setScene(scene);
    }

    /**
     * Spouští hru.
     */
    public void playPressed() {
        ControllerManager cm = null;
        try {
            cm = new ControllerManager(Constants.PLAY_FXML_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) play.getScene().getWindow();
        cm.getRoot().requestFocus();
        Scene scene = cm.getMainScene();
        stage.setScene(scene);
    }

    /**
     * Volá se stiskem tlačítka a vezme nás do informací.
     */
    public void aboutPressed() {
        ControllerManager cm = null;
        try {
            cm = new ControllerManager(Constants.ABOUT_FXML_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) about.getScene().getWindow();
        cm.getRoot().requestFocus();
        Scene scene = cm.getMainScene();
        stage.setScene(scene);
    }

    /**
     * Volá se stikem tlačítka a vezme nás do instrukcí
     */
    public void instructionsPressed() {
        ControllerManager cm = null;
        try {
            cm = new ControllerManager(Constants.INSTRUCTIONS_FXML_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) instructions.getScene().getWindow();
        cm.getRoot().requestFocus();
        Scene scene = cm.getMainScene();
        stage.setScene(scene);
    }

    /**
     * Volá se stiskem tlačítka a vezme nás do obchodu.
     */
    public void upgradePressed() {
        ControllerManager cm = null;
        try {
            cm = new ControllerManager(Constants.UPGRADE_FXML_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) upgrade.getScene().getWindow();
        cm.getRoot().requestFocus();
        Scene scene = cm.getMainScene();
        stage.setScene(scene);
    }

    /**
     * Volá se stiskem tlačítka a volá nás do menu úspěchů.
     */
    public void achievementsPressed() {
        ControllerManager cm = null;
        try {
            cm = new ControllerManager(Constants.ACHIEVEMENTS_FXML_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) achievements.getScene().getWindow();
        cm.getRoot().requestFocus();
        Scene scene = cm.getMainScene();
        stage.setScene(scene);
    }
}

