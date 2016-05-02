package gameObjects;

import javafx.application.Platform;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Random;


public class Coin extends movableObject {
    private Text timeLabel;
    private int time;
    private Random randomNumber;

    private double adjustTimeLabelX;
    private double adjustTimeLabelY;

    private double leftBound;
    private double rightBound;
    private double upperBound;
    private double lowerBound;

    public Coin(double centerX, double centerY, double radius){
        super(centerX, centerY, radius, "Yellow");

        randomNumber = new Random();
        this.setSpeed(15);
        time = 15;
        adjustTimeLabelX = -8;
        adjustTimeLabelY = 6;

        timeLabel = new Text("" + time);
        timeLabel.setX(getCenterX() + adjustTimeLabelX);
        timeLabel.setY(getCenterY() + adjustTimeLabelY);
        timeLabel.setVisible(false);
        timeLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        timeLabel.setFill(Paint.valueOf("grey"));

        leftBound = centerX - radius;
        rightBound = centerX + radius;
        upperBound = centerY - radius;
        lowerBound = centerY + radius;

        thisTherad = new Thread(this);
        thisTherad.start();
    }

    public void showCoin(){
        this.setCoinCenterX(28 + randomNumber.nextInt((int) getScene().getWidth() - 28));
        this.setCoinCenterY(28 + randomNumber.nextInt((int) getScene().getHeight() - 28));
        this.setVisible(true);
        setTime(15);
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

    public void setTime(int time){
        this.time =time;
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

    public void collides(Enemy1 enemy){
        this.setSpeed(enemy.getSpeed() * 15);

        leftBound = this.getCenterX() - this.getRadius();
        rightBound = this.getCenterX() + this.getRadius();
        upperBound = this.getCenterY() - this.getRadius();
        lowerBound = this.getCenterY() + this.getRadius();

        if (this.getCenterY() > enemy.getCenterY()) {
            enemy.setVerticalDirection(false);
            if(lowerBound < getScene().getHeight())
                this.moveDown();
        } else if (this.getCenterY() < enemy.getCenterY()) {
            enemy.setVerticalDirection(true);
            if(upperBound > getScene().getY())
                this.moveUp();
        }
        if (this.getCenterX() > enemy.getCenterX()) {
            enemy.setHorizontalDirection(false);
            if(rightBound < getScene().getWidth())
                this.moveRight();
        } else if (this.getCenterX() > enemy.getCenterX()) {
            enemy.setHorizontalDirection(false);
            if(leftBound > getScene().getX())
                this.moveLeft();
        }
    }


    public void run() {
        while(this.isVisible()) {
            Platform.runLater(() -> {
                if(time > 0){
                    if(time == 15){
                        timeLabel.setVisible(true);
                    }
                    timeLabel.setText("" + time--);
                }else{
                    this.showCoin();
                }
            });
            try {
                thisTherad.sleep(1000);
            } catch (Exception ignored) {
            }
        }
    }
}
