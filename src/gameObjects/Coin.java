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

        this.setSpeed(15);
        time = 16;
        adjustTimeLabelX = -5;
        adjustTimeLabelY = 3;

        timeLabel = new Text("" + time);
        timeLabel.setX(getCenterX() + adjustTimeLabelX);
        timeLabel.setY(getCenterY() + adjustTimeLabelY);
        thisTherad = new Thread(this);
        thisTherad.start();
    }

    public void showCoin(){
        this.setVisible(true);
        this.timeLabel.setVisible(true);
    }

    public void hideCoin(){
        this.setVisible(false);
        this.timeLabel.setVisible(false);
    }

    public Text getTimeLabel() {
        return timeLabel;
    }

    private void setTimeLabelAdjustment(){
        timeLabel.setX(getCenterX() + adjustTimeLabelX);
        timeLabel.setY(getCenterY() + adjustTimeLabelY);
    }

    public void moveRight(){
        super.moveRight();
        setTimeLabelAdjustment();
    }
    public void moveLeft(){
        super.moveRight();
        setTimeLabelAdjustment();
    }
    public void moveDown(){
        super.moveDown();
        setTimeLabelAdjustment();
    }
    public void moveUp(){
        super.moveUp();
        setTimeLabelAdjustment();
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
                Thread.sleep(1000);
            } catch (Exception ignored) {
            }
        }
    }
}
