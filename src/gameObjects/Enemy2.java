package gameObjects;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;


public class Enemy2 extends Circle implements Runnable {

    Player player;
    double speed;
    Thread enemy2Thread;

    public Enemy2(double radius, Player player) {
        super(radius, Paint.valueOf("Blue"));
        speed = 1;
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
        while (player.isVisible()) {
            System.out.println("X:" + player.getCenterX() + "\nY:" + player.getCenterY());
            System.out.println("X:" + getCenterX() + "\nY:" + getCenterY());


            if (player.getCenterX() < this.getCenterX()) {
                System.out.println("MoveLeft");
                moveLeft();
            }
            if (player.getCenterX() > this.getCenterX()) {
                moveRight();
            }

            if (player.getCenterY() < this.getCenterY()) {
                moveDown();
            }

            if (player.getCenterY() > this.getCenterY()) {
                moveUp();
            }


            try {
                enemy2Thread.sleep(1000);
            } catch (Exception e) {
            }
        }


    }
}
