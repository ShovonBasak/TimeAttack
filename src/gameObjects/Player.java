package gameObjects;


import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Player extends Circle{
    public double radius;
    public double rightBound;
    public double leftBound;
    public double upperBound;
    public double lowerBound;
    public double centerX;
    public double centerY;
    public double speedX;
    public double speedY;

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
    }

    public void setSpeed(double x) {
        speedX = x;
        speedY = x;
    }

    public void moveX(double x) {

    }
}
