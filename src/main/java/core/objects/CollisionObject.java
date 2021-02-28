/**
 * @author Adam Śmieja
 * @version 1.0
 */
package core.objects;

import javafx.animation.AnimationTimer;
import other.Constants;

public class CollisionObject extends MovableObject {

    protected double velocityY;

    /**
     * Konstruktor objektu
     * @param texturePath cesta k textuře objektu
     * @param posX pozice X na které se vykreslí
     * @param posY pozice Y na které se vykreslí
     * @param velocityX rychlost podle osy X
     * @param velocityY rychlost podle osy Y
     */
    public CollisionObject(String texturePath, double posX, double posY,
      double velocityX, double velocityY) {
        super(texturePath, posX, posY, velocityX);
        this.velocityY = velocityY;
    }

    /**
     * Metoda která se stará o pohyb po plátně
     */
    @Override public void move() {
        AnimationTimer timer = new AnimationTimer() {
            @Override public void handle(long now) {
                if (texture.getX() >= Constants.WIDTH - sizeX || texture
                  .getX() == 0) velocityX = 0;
                texture.setX(texture.getX() + 1 * velocityX);
            }
        };
        timer.start();
    }

}
