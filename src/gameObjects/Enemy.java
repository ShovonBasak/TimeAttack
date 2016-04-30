package gameObjects;


import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Enemy extends Circle{

    public double radius;
    public double rightBound;
    public double leftBound;
    public double upperBound;
    public double lowerBound;
    public double centerX;
    public double centerY;
    public double dx;
    public double dy;

    public Enemy(double radius) {
        super(radius,Paint.valueOf("red"));
        radius = this.getRadius();
        centerX = this.getCenterX();
        centerY = this.getCenterY();
        rightBound = centerX + radius;
        leftBound = centerX - radius;
        upperBound = centerY - radius;
        lowerBound = centerY + radius;
        dx = 1;
        dy = 1;
    }

    public Enemy(double centerX, double centerY, double radius){
        super(centerX,centerY,radius, Paint.valueOf("red"));
        radius = this.getRadius();
        centerX = this.getCenterX();
        centerY = this.getCenterY();
        rightBound = centerX + radius;
        leftBound = centerX - radius;
        upperBound = centerY - radius;
        lowerBound = centerY + radius;
        dx = 1;
        dy = 1;
    }
}
