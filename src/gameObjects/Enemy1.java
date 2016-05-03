package gameObjects;


import javafx.application.Platform;
import gameObjects.Player;

import static sun.audio.AudioPlayer.player;

public class Enemy1 extends Enemy implements Runnable{
    private double rightBound;
    private double leftBound;
    private double upperBound;
    private double lowerBound;

    public Enemy1(double centerX, double centerY, double radius, Player player, Coin coin) {
        super(centerX, centerY, radius, "red", player, coin);

        rightBound = getCenterX() + getRadius();
        leftBound = rightBound - (getRadius() * 2);
        upperBound = getCenterY() - getRadius();
        lowerBound = upperBound + (getRadius() * 2);

        thisTherad = new Thread(this);
        thisTherad.start();
    }


    public void setSpeed(double x) {
        speed = x;
    }

    public double getSpeed() {
        return speed;
    }

    public void run() {
        while(!Player.dead){
            Platform.runLater(() -> {
                rightBound = getCenterX() + getRadius();
                leftBound = rightBound - (getRadius() * 2);
                upperBound = getCenterY() - getRadius();
                lowerBound = upperBound + (getRadius() * 2);

                if (intersect(player)) {
                    System.out.println("Player Intersect");
                    // player.setDead(true);
                }

                if (intersect(coin)) {
                    System.out.println("Coin Intersect");
                    //Do bounce off code here
                }


                if (horizontalDirection) {
                    if (this.rightBound < getScene().getWidth()) {
                        this.moveRight();
                    } else {
                        horizontalDirection = false;
                    }
                } else {
                    if (this.leftBound > 0) {
                        this.moveLeft();
                    } else {
                        horizontalDirection = true;
                    }
                }


                if (verticalDirection) {
                    if (this.lowerBound < getScene().getHeight()) {
                        this.moveDown();
                    } else {
                        verticalDirection = false;
                    }
                } else {
                    if (this.upperBound > 0) {
                        this.moveUp();
                    } else {
                        verticalDirection = true;
                    }
                }
            });
            try{
                thisTherad.sleep(1);
            }catch (Exception ignored){}
        }
    }
}
