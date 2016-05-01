package gameObjects;


import javafx.application.Platform;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import scenes.LevelOne;

public class Enemy1 extends movableObject implements Runnable{
    private LevelOne gameScene;

    private boolean horizontalDirection;
    private boolean verticalDirection;

    private double rightBound;
    private double leftBound;
    private double upperBound;
    private double lowerBound;

    public Enemy1(double centerX, double centerY, double radius, LevelOne gameScene) {
        super(centerX, centerY, radius, "red");

        rightBound = getCenterX() + getRadius();
        leftBound = rightBound - (getRadius() * 2);
        upperBound = getCenterY() - getRadius();
        lowerBound = upperBound + (getRadius() * 2);
        this.gameScene = gameScene;
        horizontalDirection = true;
        verticalDirection = true;

        thisTherad = new Thread(this);
        thisTherad.start();
    }


    public void setSpeed(double x) {
        speed = x;
    }

    public double getSpeed() {
        return speed;
    }

    public void setHorizontalDirection(boolean direction){
        horizontalDirection = direction;
    }
    public void setVerticalDirection(boolean direction){
        verticalDirection = direction;
    }

    public void run() {
        while(!Player.dead){
            Platform.runLater(() -> {
                rightBound = getCenterX() + getRadius();
                leftBound = rightBound - (getRadius() * 2);
                upperBound = getCenterY() - getRadius();
                lowerBound = upperBound + (getRadius() * 2);

                if (horizontalDirection) {
                    if (this.rightBound < gameScene.window.getWidth() - 30) {
                        this.moveRight();
                    } else {
                        horizontalDirection = false;
                    }
                } else {
                    if (this.leftBound > 20) {
                        this.moveLeft();
                    } else {
                        horizontalDirection = true;
                    }
                }


                if (verticalDirection) {
                    if (this.lowerBound < gameScene.window.getHeight() - 55) {
                        this.moveDown();
                    } else {
                        verticalDirection = false;
                    }
                } else {
                    if (this.upperBound > 20) {
                        this.moveUp();
                    } else {
                        verticalDirection = true;
                    }
                }
            });
            try{
                thisTherad.sleep(5);
            }catch (Exception e){}
        }
    }
}
