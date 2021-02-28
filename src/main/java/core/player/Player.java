/**
 * @author Adam Śmieja
 * @version 1.0
 */
package core.player;

import core.objects.CollisionObject;
import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import other.Constants;

public class Player extends CollisionObject {

    private double gravity = 3;
    private double score = 0;
    private int wingManPicked = 0;

    /**
     * Konstruktor objektu hráče
     * @param texturePath Cesta k obrázku hráče
     */
    public Player(String texturePath) {
        super(texturePath, 400, 380, 0, 0);
    }

    /**
     * Změna textury hráče
     * @param path Cesta k textuře
     */
    public void changeTexture(String path) {
        this.texture
          .setImage(new Image(String.valueOf(getClass().getResource(path))));
    }

    /**
     *
     * @return vrátí počet sebraných víl
     */
    public int getWingManPicked() {
        return wingManPicked;
    }

    /**
     * nastavuje počet sebraných víl.
     * @param wingManPicked počet víl
     */

    public void setWingManPicked(int wingManPicked) {
        this.wingManPicked = wingManPicked;
    }

    /**
     *
     * @return vrátí aktualní skore
     */
    public double getScore() {
        return score;
    }

    /**
     * nastaví aktualní skóre
     * @param score aktuální skore
     */
    public void setScore(double score) {
        this.score = score;
    }

    /**
     * vrátí rychlost v ose X
     * @return rychlost dle osy X
     */
    public double getVelocityX() {
        return this.velocityX;
    }

    /**
     * vrátí rychlost v ose Y
     * @return rychlost dle osy Y
     */
    public double getVelocityY() {
        return this.velocityY;
    }

    /**
     * nastavuje rychlost hráče v obou osách
     * @param velocityX rychlost dle osy X
     * @param velocityY rychlost dle osy Y
     */
    public void setVelocity(double velocityX, double velocityY) {
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    /**
     * nastaví gravitaci hráče
     * @param gravity gravitace
     */
    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    /**
     * Metoda se stará o pohyb hráče po plátně
     */
    @Override public void move() {
        AnimationTimer timer = new AnimationTimer() {
            @Override public void handle(long now) {
                if (texture.getX() >= Constants.WIDTH - sizeX) {
                    setVelocity(0, 0);
                    texture.setX(Constants.WIDTH - sizeX - 1);
                }
                if (texture.getX() <= 0) {
                    setVelocity(0, 0);
                    texture.setX(1);
                }
                if (texture.getY() > 380) {
                    setVelocity(velocityX, 0);
                    texture.setY(380);
                    gravity = 0;
                    texture.setImage(new Image(String.valueOf(
                      getClass().getResource(Constants.PLAYER_STAND))));
                }
                if (texture.getY() < 280) {
                    setVelocity(velocityX, velocityY * (-1));
                }
                texture.setX(texture.getX() + velocityX);
                texture.setY(texture.getY() - velocityY + gravity);
            }
        };
        timer.start();
    }

}
