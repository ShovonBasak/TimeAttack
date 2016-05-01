package gameObjects;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import scenes.LevelOne;


public class Coin extends movableObject {
    private Text timeLabel;
    private int time;

    private double adjustTimeLabelX;
    private double adjustTimeLabelY;


    public Coin(double centerX, double centerY, double radius){
        super(centerX, centerY, radius, "Yellow");

        speed = 1;
        time = 16;
        adjustTimeLabelX = -5;
        adjustTimeLabelY = 3;

        timeLabel = new Text("" + time);
        timeLabel.setX(getCenterX() + adjustTimeLabelX);
        timeLabel.setY(getCenterY() + adjustTimeLabelY);
        thisTherad = new Thread(this);
        thisTherad.start();
    }

    public Text getTimeLabel() {
        return timeLabel;
    }


    public void moveRight(){
        setCenterX(getCenterX() + speed);
        timeLabel.setX(getCenterX() + adjustTimeLabelX);
        timeLabel.setY(getCenterY() + adjustTimeLabelY);
    }
    public void moveLeft(){
        setCenterX(getCenterX() - speed);
        timeLabel.setX(getCenterX() + adjustTimeLabelX);
        timeLabel.setY(getCenterY() + adjustTimeLabelY);
    }
    public void moveDown(){
        this.setCenterY(getCenterY() + speed);
        timeLabel.setX(this.getCenterX() + adjustTimeLabelX);
        timeLabel.setY(this.getCenterY() + adjustTimeLabelY);
    }
    public void moveUp(){
        this.setCenterY(getCenterY() - speed);
        timeLabel.setX(this.getCenterX() + adjustTimeLabelX);
        timeLabel.setY(this.getCenterY() + adjustTimeLabelY);
    }


    public void run() {
        while(this.isVisible()) {
            Platform.runLater(() -> {
                timeLabel.setText("" + --time);
                if(time < 0){
                    this.setVisible(false);
                    timeLabel.setVisible(false);
                }
            });
            try {
                thisTherad.sleep(1000);
            } catch (Exception e) {
            }
        }
    }
}
