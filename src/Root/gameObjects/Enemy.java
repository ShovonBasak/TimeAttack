package Root.GameObjects;

import Root.GameObjects.CandyCane;
import Root.scenes.GameScene;

public abstract class Enemy extends MovableObject {
    boolean horizontalDirection;
    boolean verticalDirection;
    Player player;
    CandyCane candyCane;

    Enemy(double centerX, double centerY, double radius, String color, Player player, CandyCane candyCane) {
        super(centerX, centerY, radius, color);
        this.player = player;
        this.candyCane = candyCane;
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
