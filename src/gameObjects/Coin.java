package gameObjects;

import UserInterface.ScoreLabel;
import javafx.application.Platform;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import scenes.GameScene;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Coin extends MovableObject {
    private Timer timer;
    private int period;
    private int delay;
    private int interval;
    private Text timeLabel;
    private int time;

    public int getTime() {
        return time;
    }

    private Random randomNumber;
    private int visibilityTime;
    private Player player;
    private ScoreLabel scoreLabel;

    private double adjustTimeLabelX;
    private double adjustTimeLabelY;

    private double leftBound;
    private double rightBound;
    private double upperBound;
    private double lowerBound;

    public Coin(double centerX, double centerY, double radius, Player player, ScoreLabel scoreLabel){
        super(centerX, centerY, radius, "Yellow");
        this.player = player;
        this.scoreLabel = scoreLabel;

        randomNumber = new Random();
        this.setSpeed(15);
        time = 7;
        adjustTimeLabelX = -8;
        adjustTimeLabelY = 6;

        timer = new Timer();
        delay = 1000;
        period = 1000;
        interval = 7;
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

        thisThread = new Thread(this);
        thisThread.start();


        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                time = setInterval();
            }
        }, delay, period);
    }

    public synchronized void resume() {
        GameScene.isPaused = false;
        notify();
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
        this.setCoinCenterX(getRadius()*2 + randomNumber.nextInt((int) getScene().getWidth() - (int)getRadius()*2 ));
        this.setCoinCenterY(getRadius()*2 + randomNumber.nextInt((int) getScene().getHeight() - (int)getRadius()*2 ));
        this.time = waitingTime + visibilityTime;
        this.interval = this.time;
        this.setVisibilityTime(visibilityTime);
    }


    public void setVisibilityTime(int visibilityTime){
        this.visibilityTime = visibilityTime;
    }

    public boolean isCoinVisible(){
        return time == visibilityTime;
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
        scoreLabel.setScore(scoreLabel.getScore());
        this.setSpeed(enemy.getSpeed());

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

    public void collides(Player player){
        if(!Player.dead){
            scoreLabel.setScore(scoreLabel.getScore() + time);
            hideCoin();
            setTimeAndPosition(0,7);
        }
    }

    public void run() {
        while(!Player.dead) {
            Platform.runLater(() -> {
                if (this.intersect(player)) {
                    this.collides(player);
                }
                if(time > 0){
                    if(isCoinVisible()){
                        this.showCoin();
                    }
                    else if(time > 7){
                        time--;
                    }
                    timeLabel.setText("" + time);
                }else {
                    this.hideCoin();
                    Player.dead = true;
                }
            });
            try {
                thisThread.sleep(40);
                synchronized (this) {
                    while (GameScene.isPaused) {
                        wait();
                    }
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }
    }
}
