/**
 * @author Adam Śmieja
 * @version 1.0
 */
package core.managers;

import interfaces.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ControllerManager {

    private Stage mainStage;
    private FXMLLoader loader;
    private Parent root;
    private Scene mainScene;

    /**
     * @param path cesta k FXML souboru
     * @throws IOException pokud nenajde soubor
     */
    public ControllerManager(String path) throws IOException {
        loader = new FXMLLoader();
        mainStage = new Stage();
        root = FXMLLoader.load(getClass().getResource(path));
        mainScene = new Scene(root);
        mainStage.setScene(mainScene);
    }

    /**
     * @return vrací hlavní Stage
     */
    public Stage getMainStage() {
        return this.mainStage;
    }

    /**
     * @return vrací Controller načteného FXML
     */
    public Controller getController() {
        return loader.getController();
    }

    /**
     * @return vrací kořen FXML
     */
    public Parent getRoot() {
        return this.root;
    }

    /**
     * @return vrací hlavní scénu
     */
    public Scene getMainScene() {
        return this.mainScene;
    }

}
