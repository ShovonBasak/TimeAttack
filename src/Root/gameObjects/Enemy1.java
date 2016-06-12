package Root.gameObjects;


import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import Root.scenes.GameScene;

public class Enemy1 extends Enemy implements Runnable{
    private double rightBound;
    private double leftBound;
    private double upperBound;
    private double lowerBound;

    public Enemy1(double centerX, double centerY, double radius, Player player, Coin coin) {
        super(centerX, centerY, radius, "red", player, coin);
        this.setFill(new ImagePattern(new Image("Resources/image/enemy1.gif")));
        rightBound = getCenterX() + getRadius();
        leftBound = rightBound - (getRadius() * 2);
        upperBound = getCenterY() - getRadius();
        lowerBound = upperBound + (getRadius() * 2);

        thisThread = new Thread(this);
        thisThread.start();
    }


    private void collidesWithWall(){
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
    }


    public void run() {
        while(!Player.dead){
            Platform.runLater(() -> {
                rightBound = getCenterX() + getRadius();
                leftBound = rightBound - (getRadius() * 2);
                upperBound = getCenterY() - getRadius();
                lowerBound = upperBound + (getRadius() * 2);

                if(this.isVisible()){
                    collidesWithWall();
                    if (this.intersect(player)) {
                        Player.dead = true;
                    }

                    if (this.intersect(coin)) {
                        coin.collides(this);
                    }

                }
            });
            try{
                Thread.sleep(10);
                synchronized (this) {
                    while (GameScene.isPaused) {
                        wait();
                    }
                }
            }catch (Exception ignored){
                ignored.printStackTrace();
            }
        }
    }
}