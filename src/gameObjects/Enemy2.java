package gameObjects;

import javafx.application.Platform;
import scenes.GameScene;


public class Enemy2 extends Enemy {

    private Player player;
    private Coin coin;
    private Thread enemy2Thread;
    private double m;

    public Enemy2(double centerX, double centerY,double radius, Player player, Coin coin) {
        super(centerX, centerY, radius, "Blue", player, coin);
        this.player = player;
        this.coin = coin;

        setSpeed(1);
        thisThread = new Thread(this);
        thisThread.start();
    }

    public void followPlayer(){
        if(player.getCenterY() == this.getCenterY()){
            if(player.getCenterX() < this.getCenterX()){
                moveLeft();
            }
            else{
                moveRight();
            }
        }
        else if(player.getCenterX() == this.getCenterX()){
            if(player.getCenterY() < this.getCenterY()){
                moveUp();
            }
            else{
                moveDown();
            }
        }
        else{
            if (player.getCenterX() < this.getCenterX()) {
                moveLeft();
            }
            if (player.getCenterX() > this.getCenterX()) {
                moveRight();
            }

            if (player.getCenterY() < this.getCenterY()) {
                moveUp();
            }

            if (player.getCenterY() > this.getCenterY()) {
                moveDown();
            }
        }
    }


    public void run() {
        //This object needs to follow the player
        while (!Player.dead) {
            Platform.runLater(() -> {
                this.followPlayer();

                if (this.intersect(player)) {
                    Player.dead = true;
                }

                if (this.intersect(coin)) {
                    coin.collides(this);
                }
            });
            try {
                thisThread.sleep(10);
                synchronized (this) {
                    while (GameScene.isPaused) {
                        wait();
                    }
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            catch (Exception ignored) {
            }
        }


    }
}