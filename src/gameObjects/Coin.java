package gameObjects;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;


public class Coin extends Circle {
    public Coin(double radius) {
        super(radius,Paint.valueOf("Yellow"));
    }

    public Coin(double centerX, double centerY, double radius){
        super(centerX,centerY,radius, Paint.valueOf("Yellow"));
    }
}
