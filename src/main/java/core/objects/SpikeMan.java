/*
 *   1.0, 10.12.2019, [Adam Śmieja]
 */
package core.objects;

import javafx.animation.AnimationTimer;
import java.util.Random;

public class SpikeMan extends CollisionObject {

    private Random randomY = new Random();
    public SpikeMan(String texturePath, double posX, double posY) {
        super(texturePath, posX, posY, 0, 0);
        setVelocityY(3);
    }

    private void setVelocityY(double velocityY){
        this.velocityY = velocityY;
    }

    @Override
    public void move(){
        AnimationTimer timer2 = new AnimationTimer(){
            @Override
            public void handle(long now) {
                if(texture.getY() > 500) {
                    texture.setY(((randomY.nextInt(1000))) * (-1));
                }
                else {
                    texture.setY(texture.getY() + velocityY);
                }
            }
        };
        timer2.start();
    }
}
