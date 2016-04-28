package gameObjects;


import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Player extends Circle{
    Player(double radius){
        super(radius, Paint.valueOf("Green"));
    }
}
