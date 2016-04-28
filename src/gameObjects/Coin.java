package gameObjects;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;


public class Coin extends Circle {
    private Text label = new Text("$");

    Coin(double radius){
        super(radius, Paint.valueOf("Yellow"));
        label.setFill(Paint.valueOf("Green"));
        label.setX(this.getLayoutX());
        label.setY(this.getLayoutY());
    }
}
