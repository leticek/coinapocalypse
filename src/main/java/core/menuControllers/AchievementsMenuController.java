/**
 * @author Adam Śmieja
 * @version 1.0
 */
package core.menuControllers;

import core.managers.ControllerManager;
import interfaces.Controller;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import other.AchievementsEnum;
import other.Constants;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AchievementsMenuController implements Controller {
    @FXML private ImageView imageViewLifeTimeScoreSilver;
    @FXML private ImageView imageViewLifeTimeScoreGold;
    @FXML private ImageView imageViewLifeTimeScoreBronze;
    @FXML private ImageView imageViewHighScoreSilver;
    @FXML private ImageView imageViewHighScoreGold;
    @FXML private ImageView imageViewHighScoreBronze;
    @FXML private ImageView imageViewLifeTimeCoinsSilver;
    @FXML private ImageView imageViewLifeTimeCoinsGold;
    @FXML private ImageView imageViewLifeTimeCoinsBronze;
    @FXML private Button backButton;

    /**
     * Volá methodu, která inicializuje FXML soubor
     *
     * @param url
     * @param resourceBundle
     */
    @Override public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeAchievements();
    }

    /**
     * Volá pro každou kategorii metody, které ukáží úspěchy
     */
    private void initializeAchievements() {
        showCoins(AchievementsEnum.HIGH_SCORE, myWriter.getHighScore());
        showCoins(AchievementsEnum.LIFETIME_COINS,
          (double) myWriter.getWingManCollected());
        showCoins(AchievementsEnum.LIFETIME_SCORE, myWriter.getLifeTimeScore());
    }

    /**
     * Nastaví obrázek do ImageView
     *
     * @param imageView   Do kterého se má obrázek načíst
     * @param texturePath Cesta k obrázku
     */
    private void setImageView(ImageView imageView, String texturePath) {
        imageView.setImage(
          new Image(String.valueOf(getClass().getResource(texturePath))));
    }

    /**
     * Porovnává, zda hráč dosáhl nějakého úspěchu a pokud ano, tak volá metodu pro nastavení obrázku
     *
     * @param actualValue Aktuální hodnota, která se bude porovnávat
     * @param constValue1 Hodnota potřebná pro "GOLD" úspěch
     * @param constValue2 Hodnota potřebná pro "SILVER" úspěch
     * @param constValue3 Hodnota potřebná pro "BRONZE" úspěch
     * @param imageView1  Do kterého se načte "GOLD" mince
     * @param imageView2  Do kterého se načte "BRONZE" mince
     * @param imageView3  Do kterého se načte "SILVER" mince
     */
    private void checkValues(Double actualValue, Double constValue1,
      Double constValue2, Double constValue3, ImageView imageView1,
      ImageView imageView2, ImageView imageView3) {
        if (actualValue >= constValue1) {
            setImageView(imageView1, Constants.GOLD_COIN);
            setImageView(imageView2, Constants.SILVER_COIN);
            setImageView(imageView3, Constants.BRONZE_COIN);
            return;
        }
        if (actualValue >= constValue2) {
            setImageView(imageView2, Constants.SILVER_COIN);
            setImageView(imageView3, Constants.BRONZE_COIN);
            return;
        }
        if (actualValue >= constValue3) {
            setImageView(imageView3, Constants.BRONZE_COIN);
        }
    }

    /**
     * Metoda která kontroluje o jaký úspěch se jedná a dále volá metody pro kontrolu
     *
     * @param achievementsEnum Typ úspěchu
     * @param actualValue      Hodnota pro porovnání
     */
    private void showCoins(AchievementsEnum achievementsEnum,
      Double actualValue) {
        if (achievementsEnum == AchievementsEnum.HIGH_SCORE) {
            checkValues(actualValue, (double) Constants.HIGH_SCORE_GOLD,
              (double) Constants.HIGH_SCORE_SILVER,
              (double) Constants.HIGH_SCORE_BRONZE, imageViewHighScoreGold,
              imageViewHighScoreSilver, imageViewHighScoreBronze);
        }
        if (achievementsEnum == AchievementsEnum.LIFETIME_COINS) {
            checkValues(actualValue, (double) Constants.LIFETIME_COINS_GOLD,
              (double) Constants.LIFETIME_COINS_SILVER,
              (double) Constants.LIFETIME_COINS_BRONZE,
              imageViewLifeTimeCoinsGold, imageViewLifeTimeCoinsSilver,
              imageViewLifeTimeCoinsBronze);
        }
        if (achievementsEnum == AchievementsEnum.LIFETIME_SCORE) {
            checkValues(actualValue, (double) Constants.LIFETIME_SCORE_GOLD,
              (double) Constants.LIFETIME_SCORE_SILVER,
              (double) Constants.LIFETIME_SCORE_BRONZE,
              imageViewLifeTimeScoreGold, imageViewLifeTimeScoreSilver,
              imageViewLifeTimeScoreBronze);
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
        cm.getRoot().requestFocus();
        Scene scene = cm.getMainScene();
        stage.setScene(scene);
    }
}
