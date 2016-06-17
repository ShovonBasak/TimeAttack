package Root.GameObjects;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import Root.scenes.GameScene;

import static java.lang.Math.abs;



public class Enemy2 extends Enemy {

    private Player player;
    private Coin coin;


    public Enemy2(double centerX, double centerY,double radius, Player player, Coin coin) {
        super(centerX, centerY, radius, "Blue", player, coin);
        setFill(new ImagePattern(new Image("image/enemy2.gif")));
        this.player = player;
        this.coin = coin;

        setSpeed(1);
        thisThread = new Thread(this);
        thisThread.start();
    }

    private void followPlayer(){
        double dx = player.getCenterX() - this.getCenterX();
        double dy = player.getCenterY() - this.getCenterY();
        double m = dy/dx;

        if(player.getCenterX() < this.getCenterX()){
            if(player.getCenterY() > this.getCenterY()){
                if(abs(dx) > abs(dy)){
                    this.setCenterX(this.getCenterX() - this.getSpeed());
                    this.setCenterY(this.getCenterY() - (this.getSpeed()*m));
                }
                else{
                    this.setCenterX(this.getCenterX() + (this.getSpeed()*(1/m)));
                    this.setCenterY(this.getCenterY() + this.getSpeed());
                }
            }
            else{
                if(abs(dx) > abs(dy)){
                    this.setCenterX(this.getCenterX() - this.getSpeed());
                    this.setCenterY(this.getCenterY() - (this.getSpeed()*m));
                }
                else{
                    this.setCenterX(this.getCenterX() - (this.getSpeed()*(1/m)));
                    this.setCenterY(this.getCenterY() - this.getSpeed());
                }
            }
        }
        else if(player.getCenterX() > this.getCenterX()){
            if(player.getCenterY() < this.getCenterY()){
                if(abs(dx) > abs(dy)){
                    this.setCenterX(this.getCenterX() + this.getSpeed());
                    this.setCenterY(this.getCenterY() + (this.getSpeed()*m));
                }
                else{
                    this.setCenterX(this.getCenterX() - (this.getSpeed()*(1/m)));
                    this.setCenterY(this.getCenterY() - this.getSpeed());
                }
            }
            else{
                if(abs(dx) > abs(dy)){
                    this.setCenterX(this.getCenterX() + this.getSpeed());
                    this.setCenterY(this.getCenterY() + (this.getSpeed()*m));
                }
                else{
                    this.setCenterX(this.getCenterX() + (this.getSpeed()*(1/m)));
                    this.setCenterY(this.getCenterY() + this.getSpeed());
                }
            }
        }
    }


    public void run() {
        //This object needs to follow the player
        while (!Player.dead) {
            Platform.runLater(() -> {
                this.followPlayer();

                if (this.intersect(player)) {
                    player.substractHealth(1);
                }

                if (this.intersect(coin)) {
                    coin.collides(this);
                }
            });
            try {
                Thread.sleep(20);
                synchronized (this) {
                    while (GameScene.isPaused) {
                        wait();
                    }
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            catch (Exception ignored) {}
        }
    }
}