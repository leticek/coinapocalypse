/**
 * @author Adam Śmieja
 * @version 1.0
 */
package interfaces;

import core.player.Player;
import javafx.fxml.Initializable;
import other.MyWriter;

public interface Controller extends Initializable {
    MyWriter myWriter = new MyWriter();

    /**
     * defaultní metoda pro nastavení výsledku po hře
     *
     * @param player hráč
     */
    default void setPlayerResults(Player player) {
    }

}
