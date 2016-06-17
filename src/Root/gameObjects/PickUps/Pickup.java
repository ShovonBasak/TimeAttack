package Root.GameObjects.PickUps;


import Root.GameObjects.Player;
import javafx.scene.shape.Rectangle;

import java.util.Random;

import static Root.scenes.GameScene.isPaused;

public abstract class Pickup extends Rectangle implements Runnable {
    boolean isDead=false;
    Player player;
    Random randomPostion;
    Thread thisThread;

    boolean intersect(Player player) { //returns true if collides with player
        return (player.intersects(this.getBoundsInParent()));

    }

    public void setPlayer(Player player) {
        randomPostion = new Random();
        this.player = player;
    }

    public void setRandomPosition(){
        this.setX(randomPostion.nextInt((int) getScene().getWidth() - 1 + 1) + 1);
        this.setY(randomPostion.nextInt((int) getScene().getHeight() - 1 + 1) + 1);
    }

    public abstract void Trigger();

    void collidesWithPlayer(){
        if(!Player.dead){
            this.setVisible(false);
            this.setRandomPosition();
            this.setVisible(true);
        }
    }

    public synchronized void resume() {
        isPaused = false;
        notify();
    }

}
