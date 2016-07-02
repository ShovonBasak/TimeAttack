package Root.GameObjects;

import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import Root.scenes.GameScene;

import static java.lang.Math.abs;



public class Enemy2 extends Enemy {

    private Player player;
    private CandyCane candyCane;
    private boolean facingRight=false;


    public Enemy2(double centerX, double centerY,double radius, Player player, CandyCane candyCane) {
        super(centerX, centerY, radius, "Blue", player, candyCane);
        setFill(new ImagePattern(new Image("image/EvilNyan.gif")));
        this.player = player;
        this.candyCane = candyCane;

        setSpeed(1);
        thisThread = new Thread(this);
        thisThread.start();
    }

    private void followPlayer(){
        double dx = player.getCenterX() - this.getCenterX();
        double dy = player.getCenterY() - this.getCenterY();
        double m = dy/dx;

        if(player.getCenterX() < this.getCenterX()){//goes left
            if(facingRight){
                this.setScaleX(getScaleX()*-1);
                facingRight=!facingRight;
            }
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
        else if(player.getCenterX() > this.getCenterX()){//goes right
            if(!facingRight){
                this.setScaleX(getScaleX()*-1);
                facingRight=!facingRight;
            }

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

                if (this.intersect(candyCane)) {
                    candyCane.collides(this);
                }
            });
            try {
                Thread.sleep(40);
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