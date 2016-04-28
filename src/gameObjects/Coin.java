package gameObjects;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

/**
 * Created by tazim on 4/28/2016.
 */
public class Coin extends Circle {
    Coin(double radius){
        super(radius, Paint.valueOf("Yellow"));
    }
}
