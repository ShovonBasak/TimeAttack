package gameObjects;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import scenes.LevelOne;

import java.util.Random;


public class Coin extends movableObject {
    private Text timeLabel;
    private int time;
    private Random randomNumber;

    private double adjustTimeLabelX;
    private double adjustTimeLabelY;


    public Coin(double centerX, double centerY, double radius){
        super(centerX, centerY, radius, "Yellow");

        randomNumber = new Random();
        this.setSpeed(15);
        time = 15;
        adjustTimeLabelX = -5;
        adjustTimeLabelY = 3;

        timeLabel = new Text("" + time);
        timeLabel.setX(getCenterX() + adjustTimeLabelX);
        timeLabel.setY(getCenterY() + adjustTimeLabelY);
        timeLabel.setVisible(false);
        thisTherad = new Thread(this);
        thisTherad.start();
    }

    public void showCoin(){
        this.setCoinCenterX(5 + randomNumber.nextInt((int) getScene().getWidth()));
        this.setCoinCenterY(5 + randomNumber.nextInt((int) getScene().getHeight()));
        this.setVisible(true);
        setTime();
    }

    public void hideCoin(){
        this.setVisible(false);
        this.timeLabel.setVisible(false);
    }

    public Text getTimeLabel() {
        return timeLabel;
    }

    public void setCoinCenterX(double x){
        super.setCenterX(x);
        setTimeLabelAdjustment();
    }

    public void setCoinCenterY(double y){
        super.setCenterY(y);
        setTimeLabelAdjustment();
    }

    public void setTime(){
        time =15;
        timeLabel.setVisible(false);
    }

    private void setTimeLabelAdjustment(){
        timeLabel.setX(this.getCenterX() + adjustTimeLabelX);
        timeLabel.setY(this.getCenterY() + adjustTimeLabelY);
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
                if(time > 0){
                    timeLabel.setText("" + time--);
                    if(time == 5){
                        timeLabel.setVisible(true);
                    }
                }else{
                    this.showCoin();
                }
            });
            try {
                Thread.sleep(1000);
            } catch (Exception ignored) {
            }
        }
    }
}
