package gameObjects;


import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public abstract class MovableObject extends Circle implements Runnable {
    double speed;
    public Thread thisThread;

    public MovableObject(double centerX, double centerY, double radius, String color) {
        super(centerX, centerY, radius, Paint.valueOf(color));
        setSpeed(1);
    }





    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public boolean intersect(MovableObject object) {
        if ((object.intersects(this.getBoundsInParent()))) {
            return true;
        }
        return false;
    }



    public void moveRight() {
        this.setCenterX(this.getCenterX() + speed);
    }

    public void moveLeft() {
        this.setCenterX(this.getCenterX() - speed);
    }

    public void moveDown() {
        this.setCenterY(this.getCenterY() + speed);
    }

    public void moveUp() {
        this.setCenterY(this.getCenterY() - speed);
    }


}
