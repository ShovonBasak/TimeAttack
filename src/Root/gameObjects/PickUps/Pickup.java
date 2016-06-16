package Root.gameObjects.PickUps;


import Root.gameObjects.Player;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.Random;

import static Root.scenes.GameScene.isPaused;

public abstract class Pickup extends Rectangle implements Runnable {
    boolean isDead=false;
    Player player;
    Random randomPostion;
    Image fillImage;//image to fill the object


    boolean intersect(Player player) { //returns true if collides with player
        return (player.intersects(this.getBoundsInParent()));
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setRandomPosition(){
        randomPostion=new Random();
        this.setX(this.getWidth()*2 + randomPostion.nextInt((int) getScene().getWidth() - (int)this.getWidth()*2 ));
        this.setY(this.getHeight()*2 + randomPostion.nextInt((int) getScene().getHeight() - (int)this.getHeight()*2 ));
    }

    public abstract void Trigger();

    private void collidesWithPlayer(){
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

    public void run() {
        while(!Player.dead) {
            Platform.runLater(() -> {
                if (this.intersect(player)) {
                    this.collidesWithPlayer();
                }
            });
            try {
                Thread.sleep(30);
                synchronized (this) {
                    while (isPaused) {
                        wait();
                    }
                }
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }
    }


}
