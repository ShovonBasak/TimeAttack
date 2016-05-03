package gameObjects;

import javafx.application.Platform;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;


public class Enemy2 extends Enemy {

    Player player;
    double speed;
    Thread enemy2Thread;

    public Enemy2(double radius, Player player) {
        super(100, 100, radius, Paint.valueOf("Blue"));
        speed = .1;
        this.player = player;

        enemy2Thread = new Thread(this);
        enemy2Thread.start();
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    public void moveRight() {
        this.setCenterX(this.getCenterX() + getSpeed());
    }

    public void moveLeft() {
        this.setCenterX(this.getCenterX() - getSpeed());
    }

    public void moveDown() {
        this.setCenterY(this.getCenterY() + getSpeed());
    }

    public void moveUp() {
        this.setCenterY(this.getCenterY() - getSpeed());
    }

    public void run() {
        //This object needs to follow the player
        while (!Player.dead) {
            Platform.runLater(() -> {
                //do anything
            //System.out.println("X:" + player.getCenterX() + "\nY:" + player.getCenterY());
            //System.out.println("X:" + getCenterX() + "\nY:" + getCenterY());

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
                        //System.out.println("MoveLeft");
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



            });
            try {
                enemy2Thread.sleep(1);
            } catch (Exception e) {
            }
        }


    }
}
