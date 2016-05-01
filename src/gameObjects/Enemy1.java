package gameObjects;


import javafx.application.Platform;

public class Enemy1 extends Enemy implements Runnable{
    private double rightBound;
    private double leftBound;
    private double upperBound;
    private double lowerBound;

    public Enemy1(double centerX, double centerY, double radius) {
        super(centerX, centerY, radius, "red");

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
        while(this.isVisible()){
            Platform.runLater(() -> {
                rightBound = getCenterX() + getRadius();
                leftBound = rightBound - (getRadius() * 2);
                upperBound = getCenterY() - getRadius();
                lowerBound = upperBound + (getRadius() * 2);

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
                thisTherad.sleep(5);
            }catch (Exception e){}
        }
    }
}
