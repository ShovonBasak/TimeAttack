package gameObjects;


import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class Player extends Circle{
    public Player(double radius){
        super(radius, Paint.valueOf("Green"));
    }
}
