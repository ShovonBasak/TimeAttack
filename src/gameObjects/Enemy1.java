package gameObjects;


import javafx.application.Platform;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import scenes.LevelOne;

public class Enemy1 extends Circle implements Runnable{
    private LevelOne gameScene;

    private boolean horizontalDirection;
    private boolean verticalDirection;

    private double radius;
    private double rightBound;
    private double leftBound;
    private double upperBound;
    private double lowerBound;
    public double centerX;
    public double centerY;
    private double speedX;
    private double speedY;

    private Thread enemyThread;

    public Enemy1(double centerX, double centerY, double radius, LevelOne gameScene) {
        super(centerX,centerY,radius, Paint.valueOf("red"));

        radius = this.getRadius();
        centerX = this.getCenterX();
        centerY = this.getCenterY();
        rightBound = centerX + radius;
        leftBound = centerX - radius;
        upperBound = centerY - radius;
        lowerBound = centerY + radius;
        speedX = 2;
        speedY = 2;
        this.gameScene = gameScene;
        horizontalDirection = true;
        verticalDirection = true;

        gameScene.group.getChildren().add(this);

        enemyThread = new Thread(this);
        enemyThread.start();
    }

    public void setHorizontalDirection(boolean direction){
        horizontalDirection = direction;
    }
    public void setVerticalDirection(boolean direction){
        verticalDirection = direction;
    }

    public void setSpeed(double x){
        speedX = x;
        speedY = x;
    }

    public void moveRight(){
        this.setCenterX(this.centerX + speedX);
    }
    public void moveLeft(){
        this.setCenterX(this.centerX - speedX);
    }
    public void moveDown(){
        this.setCenterY(this.centerY + speedY);
    }
    public void moveUp(){
        this.setCenterY(this.centerY - speedY);
    }

    public void run() {
        while(!Player.dead){
            Platform.runLater(() -> {
                this.centerX = this.getCenterX();
                this.centerY = this.getCenterY();
                this.rightBound = this.centerX + this.radius;
                this.leftBound = this.centerX - this.radius;
                this.upperBound = this.centerY - this.radius;
                this.lowerBound = this.centerY + this.radius;


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
                enemyThread.sleep(5);
            }catch (Exception e){}
        }
    }
}
