package Root.gameObjects.PickUps;


import Root.gameObjects.Player;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

import java.util.Random;

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





}
