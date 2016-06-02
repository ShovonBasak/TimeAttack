package gameObjects;

import gameObjects.Coin;
import gameObjects.MovableObject;
import gameObjects.Player;
import scenes.GameScene;

public abstract class Enemy extends MovableObject {
    boolean horizontalDirection;
    boolean verticalDirection;
    Player player;
    Coin coin;

    Enemy(double centerX, double centerY, double radius, String color, Player player, Coin coin) {
        super(centerX, centerY, radius, color);
        this.player = player;
        this.coin = coin;
    }

    public synchronized void resume() {
        GameScene.isPaused = false;
        notify();
    }


    void setHorizontalDirection(boolean direction){
        horizontalDirection = direction;
    }
    void setVerticalDirection(boolean direction){
        verticalDirection = direction;
    }
}
