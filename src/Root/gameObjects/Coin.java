package Root.gameObjects;

import Root.Application.AudioManager;
import Root.UserInterface.CustomLable;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.application.Platform;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import static Root.scenes.GameScene.isPaused;


public class Coin extends MovableObject {
    private Timer timer;
    private int period;
    private int delay;
    private int interval;
    private Text timeLabel;
    private int time;
    private int visibilityTime;

    private Random randomNumber;
    private Player player;
    private CustomLable ScoreLable;
    private Group coin;

    private double adjustTimeLabelX;
    private double adjustTimeLabelY;

    public Coin(double centerX, double centerY, double radius, Player player, CustomLable ScoreLable){
        super(centerX, centerY, radius, "Yellow");
        setFill(new ImagePattern(new Image("image/Coin.gif")));
        this.player = player;
        this.ScoreLable = ScoreLable;

        randomNumber = new Random();
        this.setBounds();
        time = 7;
        adjustTimeLabelX = -8;
        adjustTimeLabelY = -35;

        timer = new Timer();
        delay = 1000;
        period = 1000;
        interval = 7;
        timeLabel = new Text("" + time);
        timeLabel.setVisible(true);
        timeLabel.setFont(Font.font("Edwardian Script ITC", FontWeight.BOLD, 20));

        adjustTimeLabelPosition();

        coin = new Group(this, timeLabel);

        thisThread = new Thread(this);
        thisThread.start();

        countDown();
    }

    public Group getCoin(){
        return coin;
    }

    private void countDown(){
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Platform.runLater(()-> {
                    if(!isPaused)
                        time = --interval;
                });

                if(time<=4){
                    timeLabel.setFill(Paint.valueOf("RED"));
                }else {
                    timeLabel.setFill(Paint.valueOf("GREEN"));
                }
                if(time == 7 ){
                    showCoin();
                }
            }
        }, delay, period);
    }



    public synchronized void resume() {
        isPaused = false;
        notify();
    }

    private void showCoin(){
        this.setVisible(true);
        this.timeLabel.setVisible(true);
    }

    private void hideCoin(){
        this.setVisible(false);
        this.timeLabel.setVisible(false);
    }

    private void setPosition(){
        this.setCenterX(getRadius()*2 + randomNumber.nextInt((int) getScene().getWidth() - (int)getRadius()*2 ));
        this.setCenterY(getRadius()*2 + randomNumber.nextInt((int) getScene().getHeight() - (int)getRadius()*2 ));
        adjustTimeLabelPosition();
    }

    private void setTime(int waitingTime, int visibilityTime){
        this.time = waitingTime + visibilityTime;
        this.interval = this.time;
        this.setVisibilityTime(visibilityTime);
    }

    private void setVisibilityTime(int visibilityTime){
        this.visibilityTime = visibilityTime;
    }

    private boolean isTimeToShow(){
        return time == visibilityTime;
    }

    private void adjustTimeLabelPosition(){
        timeLabel.setX(this.getCenterX() + adjustTimeLabelX);
        timeLabel.setY(this.getCenterY() + adjustTimeLabelY);
    }

    void collides(Enemy enemy){

        this.setSpeed(enemy.getSpeed());

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
        adjustTimeLabelPosition();
    }

    private void collidesWithPlayer(){
        if(!Player.dead){
            ScoreLable.setValue(ScoreLable.getValue() + time);
            AudioManager.CoinAudio();
            this.hideCoin();
            this.setTime(1,7);
            this.setPosition();
        }
    }

    public void run() {
        while(!Player.dead) {
            Platform.runLater(() -> {
                if (this.intersect(player)) {
                    this.collidesWithPlayer();
                }
                if(time > 0){
                    if(isTimeToShow()){
                        this.showCoin();
                    }
                    timeLabel.setText("" + time);
                }else {
                    this.hideCoin();
                    Player.dead = true;
                }
            });
            try {
                Thread.sleep(30);
                synchronized (this) {
                    while (isPaused) {
                        wait();
                    }
                }
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }
    }
}
