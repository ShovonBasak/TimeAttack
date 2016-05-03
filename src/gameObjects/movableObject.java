package gameObjects;


import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public abstract class movableObject extends Circle implements Runnable {
    double speed;
    Thread thisTherad;

    public movableObject(double centerX, double centerY, double radius, String color) {
        super(centerX, centerY, radius, Paint.valueOf(color));
        setSpeed(1);
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public movableObject(double radius, String color) {
        super(radius, Paint.valueOf(color));
        setSpeed(.1);
    }

    public boolean intersect(movableObject object) {
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
