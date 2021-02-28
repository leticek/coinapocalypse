/**
 * @author Adam Śmieja
 * @version 1.0
 */
package core.objects;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MyObject {
    protected double posX;
    protected double posY;
    protected ImageView texture;

    /**
     * Konstruktor objektu
     * @param texturePath Cesta k textuře objektu
     * @param posX pozice X na které se vykreslí
     * @param posY pozice Y na které se vykreslí
     */

    public MyObject(String texturePath, double posX, double posY) {
        this.posX = posX;
        this.posY = posY;
        this.texture = new ImageView(
          new Image(String.valueOf(getClass().getResource(texturePath))));
        this.texture.setX(this.posX);
        this.texture.setY(this.posY);
    }

    /**
     * Vrátí texturu hráče
     * @return textura hráče
     */
    public ImageView getTexture() {
        return this.texture;
    }

    /**
     * nedefinovaná metoda
     */
    public void move() {
    }
}
