package gameObjects;

import javafx.scene.paint.Paint;

public abstract class Enemy extends movableObject{
    protected boolean horizontalDirection;
    protected boolean verticalDirection;

    public Enemy(double centerX, double centerY, double radius, String color) {
        super(centerX, centerY, radius, color);
    }

    public Enemy(double radius, String color) {
        super(radius, color);
        setSpeed(1);
    }



    public void setHorizontalDirection(boolean direction){
        horizontalDirection = direction;
    }
    public void setVerticalDirection(boolean direction){
        verticalDirection = direction;
    }
}
