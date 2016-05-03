package gameObjects;

import javafx.application.Platform;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Coin extends movableObject {
    private Timer timer;
    private int period;
    private int delay;
    private int interval;
    private Text timeLabel;
    private int time;
    private Random randomNumber;
    private int visibilityTime;

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

        timer = new Timer();
        delay = 1000;
        period = 1000;
        interval = 15;
        timeLabel = new Text("" + time);
        timeLabel.setX(getCenterX() + adjustTimeLabelX);
        timeLabel.setY(getCenterY() + adjustTimeLabelY);
        timeLabel.setVisible(true);
        timeLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        timeLabel.setFill(Paint.valueOf("grey"));

        leftBound = centerX - radius;
        rightBound = centerX + radius;
        upperBound = centerY - radius;
        lowerBound = centerY + radius;

        thisTherad = new Thread(this);
        thisTherad.start();


        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                time = setInterval();
            }
        }, delay, period);
    }

    public void showCoin(){
        this.setVisible(true);
        this.timeLabel.setVisible(true);
    }

    private int setInterval() {
        return --interval;
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

    public void setTimeAndPosition(int waitingTime, int visibilityTime){
        this.setCoinCenterX(28 + randomNumber.nextInt((int) getScene().getWidth() - 28));
        this.setCoinCenterY(28 + randomNumber.nextInt((int) getScene().getHeight() - 28));
        this.time = waitingTime + visibilityTime;
        this.interval = this.time;
        this.setVisibilityTime(visibilityTime);
    }


    public void setVisibilityTime(int visibilityTime){
        this.visibilityTime = visibilityTime;
    }

    public boolean isCoinVisible(){
        if(time == visibilityTime){
            return true;
        }
        else{
            return false;
        }
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

    public void collides(Enemy enemy){
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
        while(!Player.dead) {
            Platform.runLater(() -> {
                if(time > 0){
                    if(isCoinVisible()){
                        this.showCoin();
                    }
                    else if(time > 15){
                        time--;
                    }
                    timeLabel.setText("" + time);
                }else {
                    this.hideCoin();
                    this.setTimeAndPosition(0,15);
                }
            });
            try {
                thisTherad.sleep(1);
            } catch (Exception ignored) {
            }
        }
    }
}
