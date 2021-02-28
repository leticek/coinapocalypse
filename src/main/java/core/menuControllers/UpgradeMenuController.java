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

public class UpgradeMenuController implements Controller {

    @FXML private Button backButton;
    @FXML private Text actualSpeed;
    @FXML private Text nextLevelSpeed;
    @FXML private Text priceSpeed;
    @FXML private Text accountBalance;
    @FXML private Text priceFairyValue;
    @FXML private Text nextLevelFairyValue;
    @FXML private Text actualFairyValue;
    @FXML private Text actualNumOfFairies, nextNumOfFairies, priceMoreFairies;
    private int intPriceMoreFairies =
      PlayerAttributes.getNumberOfWingMan() * 10000;
    private int intNextNumOfFairies = PlayerAttributes.getNumberOfWingMan() + 1;
    private int intPriceFairyValue =
      (PlayerAttributes.getWingManValue() + 7) * 25;
    private int intNextLevelFairyValue = PlayerAttributes.getWingManValue() + 1;
    private double doubleNextLevelSpeed = PlayerAttributes.getSpeedX() + 0.5;
    private double doublePriceSpeed =
      (PlayerAttributes.getSpeedX() + 1) * PlayerAttributes.getSpeedX() * 300;

    /**
     * Volá methodu, která inicializuje FXML soubor
     *
     * @param url
     * @param resourceBundle
     */
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        init();
    }

    /**
     * Načte hráče a inicializuje obchod.
     */
    private void init() {
        myWriter.saveAndLoadPlayer();
        accountBalance
          .setText(Integer.toString(PlayerAttributes.getAccountBalance()));
        initNumOfFairies();
        initSpeed();
        initFairyValue();
    }

    /**
     * Spočítá ceny a hodnoty atributů
     */
    private void calculateValues() {
        intPriceMoreFairies = PlayerAttributes.getNumberOfWingMan() * 10000;
        intNextNumOfFairies = PlayerAttributes.getNumberOfWingMan() + 1;
        intPriceFairyValue = (PlayerAttributes.getWingManValue() + 7) * 25;
        intNextLevelFairyValue = PlayerAttributes.getWingManValue() + 1;
        doubleNextLevelSpeed = PlayerAttributes.getSpeedX() + 0.5;
        doublePriceSpeed = (PlayerAttributes.getSpeedX() + 1) * PlayerAttributes
          .getSpeedX() * 300;
    }

    /**
     * Načte počet víl
     */
    private void initNumOfFairies() {
        actualNumOfFairies
          .setText(Integer.toString(PlayerAttributes.getNumberOfWingMan()));
        if (PlayerAttributes
          .getNumberOfWingMan() < Constants.MAX_NUM_OF_WING_MAN) {
            nextNumOfFairies.setText(Integer.toString(intNextNumOfFairies));
            priceMoreFairies.setText(Integer.toString(intPriceMoreFairies));
        } else {
            nextNumOfFairies.setText("Max level.");
            priceMoreFairies.setText("Max level.");
        }
    }

    /**
     * Načte hodnotu víly
     */
    private void initFairyValue() {
        actualFairyValue
          .setText(Integer.toString(PlayerAttributes.getWingManValue()));
        if (PlayerAttributes
          .getWingManValue() < Constants.FAIRY_VALUE_MAX_LEVEL) {
            nextLevelFairyValue
              .setText(Integer.toString(intNextLevelFairyValue));
            priceFairyValue.setText(Integer.toString(intPriceFairyValue));
        } else {
            nextLevelFairyValue.setText("Max level.");
            priceFairyValue.setText("Max level.");
        }
    }

    /**
     * Načte rychlost
     */
    private void initSpeed() {
        actualSpeed.setText(Double.toString(PlayerAttributes.getSpeedX()));
        if (PlayerAttributes.getSpeedX() < Constants.SPEED_MAX_LEVEL) {
            nextLevelSpeed.setText(Double.toString(doubleNextLevelSpeed));
            priceSpeed.setText(Double.toString(doublePriceSpeed));
        } else {
            nextLevelSpeed.setText("Max level.");
            priceSpeed.setText("Max level.");
        }
    }

    /**
     * Volá se po stisku tlačítka a zvýší úroven rychlosti.
     */
    public void upgradeSpeedPressed() {
        if (doublePriceSpeed <= PlayerAttributes
          .getAccountBalance() && PlayerAttributes
          .getSpeedX() < Constants.SPEED_MAX_LEVEL) {
            PlayerAttributes.setAccountBalance(
              (int) (PlayerAttributes.getAccountBalance() - doublePriceSpeed));
            PlayerAttributes.setSpeedX(doubleNextLevelSpeed);
            accountBalance
              .setText(Integer.toString(PlayerAttributes.getAccountBalance()));
            calculateValues();
            initSpeed();
            myWriter.saveAndLoadPlayer();
        }
    }

    /**
     * Volá se po stisku tlačítka a zvýší hodnoty víl
     */
    public void upgradeFairyValuePressed() {
        if (intPriceFairyValue <= PlayerAttributes
          .getAccountBalance() && PlayerAttributes
          .getWingManValue() < Constants.FAIRY_VALUE_MAX_LEVEL) {
            PlayerAttributes.setAccountBalance(
              (PlayerAttributes.getAccountBalance() - intPriceFairyValue));
            PlayerAttributes.setWingManValue(intNextLevelFairyValue);
            accountBalance
              .setText(Integer.toString(PlayerAttributes.getAccountBalance()));
            calculateValues();
            initFairyValue();
            myWriter.saveAndLoadPlayer();
        }
    }

    /**
     * Volá se po stisku tlačítka a zvýší počet víl.
     */
    public void upgradeNumOfFairies() {
        if (intPriceMoreFairies <= PlayerAttributes
          .getAccountBalance() && PlayerAttributes
          .getNumberOfWingMan() < Constants.MAX_NUM_OF_WING_MAN) {
            PlayerAttributes.setAccountBalance(
              (PlayerAttributes.getAccountBalance() - intPriceMoreFairies));
            PlayerAttributes.setNumberOfWingMan(intNextNumOfFairies);
            accountBalance
              .setText(Integer.toString(PlayerAttributes.getAccountBalance()));
            calculateValues();
            initNumOfFairies();
            myWriter.saveAndLoadPlayer();
        }
    }

    /**
     * Volá se po stisku tlačítka a vrací nás do hlavního menu.
     */
    public void backPressed() {
        ControllerManager cm = null;
        try {
            cm = new ControllerManager(Constants.MAIN_MENU_FXML_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage stage = (Stage) backButton.getScene().getWindow();
        Scene scene = cm.getMainScene();
        stage.setScene(scene);
    }
}
