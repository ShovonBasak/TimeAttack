package gameObjects;


import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import javax.swing.plaf.basic.BasicSplitPaneUI;

public class Player extends Circle implements Runnable {

    double rightBound;
    double leftBound;
    double upperBound;
    double lowerBound;
    double centerX;
    double centerY;
    double speedX;
    double speedY;
    Thread playerThread;

    public Player(double radius){
        super(radius, Paint.valueOf("Green"));
        radius = this.getRadius();
        centerX = this.getCenterX();
        centerY = this.getCenterY();
        rightBound = centerX + radius;
        leftBound = centerX - radius;
        upperBound = centerY - radius;
        lowerBound = centerY + radius;
        speedX = 2;
        speedY = 2;
        playerThread = new Thread(this);
        playerThread.start();
    }

    public void setSpeed(double x) {
        speedX = x;
        speedY = x;
    }

    public double getSpeedX() {
        return speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void moveRight() {
        centerX = centerX + getSpeedX();
        this.setCenterX(centerX);
    }

    public void moveLeft() {
        centerX = centerX - getSpeedX();
        this.setCenterX(centerX);
    }


    public void moveUp() {
        centerY = centerY - getSpeedY();
        this.setCenterY(centerY);
    }

    public void moveDown() {
        centerY = centerY + getSpeedY();
        this.setCenterY(centerY);
    }

    public void run() {
        while (this.isVisible()) {
            Platform.runLater(() -> {
                getScene().setOnKeyPressed(e -> {
                    //move according to key press
                    if (e.getCode().equals(KeyCode.RIGHT)) {
                        this.moveRight();
                    }

                    if (e.getCode().equals(KeyCode.LEFT)) {
                        this.moveLeft();
                    }

                    if (e.getCode().equals(KeyCode.UP)) {
                        this.moveUp();
                    }

                    if (e.getCode().equals(KeyCode.DOWN)) {
                        this.moveDown();
                    }


                });
            });

            try {
                playerThread.sleep(1);
                //System.out.println("Running");
            } catch (Exception e) {
            }
        }
    }


}
