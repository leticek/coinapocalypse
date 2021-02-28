/**
 * @author Adam Śmieja
 * @version 1.0
 */
package core.objects;

import javafx.animation.AnimationTimer;
import other.Constants;

public class MovableObject extends MyObject {
    protected double velocityX;
    protected double sizeX;

    /**
     * Konstruktor objektu
     *
     * @param texturePath Cesta k textuře objektu
     * @param posX        pozice X na které se vykreslí
     * @param posY        pozice Y na které se vykreslí
     * @param velocityX   rychlost podle osy X
     */
    public MovableObject(String texturePath, double posX, double posY,
      double velocityX) {
        super(texturePath, posX, posY);
        this.sizeX = texture.getImage().getWidth();
        this.velocityX = velocityX;
    }

    /**
     * Metoda která se stará o pohyb po plátně
     */
    @Override public void move() {
        AnimationTimer timer = new AnimationTimer() {
            @Override public void handle(long now) {
                if (texture.getX() >= Constants.WIDTH - sizeX) {
                    velocityX *= -1;
                }
                if (texture.getX() <= 0) {
                    velocityX *= -1;
                    texture.setX(1);
                }
                texture.setX(texture.getX() + 1 * velocityX);
            }
        };
        timer.start();
    }
}
