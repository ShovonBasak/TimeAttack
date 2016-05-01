package gameObjects;


import javafx.application.Platform;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import scenes.LevelOne;

public class Enemy1 extends movableObject implements Runnable{
    private LevelOne gameScene;

    private boolean horizontalDirection;
    private boolean verticalDirection;

    private double radius;
    private double rightBound;
    private double leftBound;
    private double upperBound;
    private double lowerBound;

    public Enemy1(double centerX, double centerY, double radius, LevelOne gameScene) {
        super(centerX, centerY, radius, "red");

        radius = getRadius();
        rightBound = getCenterX() + radius;
        leftBound = rightBound - (radius * 2);
        upperBound = getCenterY() - radius;
        lowerBound = upperBound + (radius * 2);
        this.gameScene = gameScene;
        horizontalDirection = true;
        verticalDirection = true;

        gameScene.group.getChildren().add(this);

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
                rightBound = getCenterX() + radius;
                leftBound = rightBound - (radius * 2);
                upperBound = getCenterY() - radius;
                lowerBound = upperBound + (radius * 2);

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
