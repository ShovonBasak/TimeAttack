package gameObjects;

import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
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
    private int visibilityTime;

    private Random randomNumber;
    private Player player;
    private ScoreLabel scoreLabel;
    private Group coin;

    private double adjustTimeLabelX;
    private double adjustTimeLabelY;

    public Coin(double centerX, double centerY, double radius, Player player, ScoreLabel scoreLabel){
        super(centerX, centerY, radius, "Yellow");
        setFill(new ImagePattern(new Image("Resources/image/Coin.gif")));
        this.player = player;
        this.scoreLabel = scoreLabel;

        randomNumber = new Random();
        this.setSpeed(15);
        this.setBounds();
        time = 7;
        adjustTimeLabelX = -8;
        adjustTimeLabelY = 6;

        timer = new Timer();
        delay = 1000;
        period = 1000;
        interval = 7;
        timeLabel = new Text("" + time);
        timeLabel.setVisible(true);
        timeLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        timeLabel.setFill(Paint.valueOf("grey"));
        adjustTimeLabelPosition();

        coin = new Group(this, timeLabel);

        thisThread = new Thread(this);
        thisThread.start();

        this.countDown();
    }

    public Group getCoin(){
        return coin;
    }

    private void countDown(){
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Platform.runLater(()->{
                    time = --interval;;
                });
                if(time == 7 ){
                    showCoin();
                }
            }
        }, delay, period);
    }

    public synchronized void resume() {
        GameScene.isPaused = false;
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

    public void collides(Enemy enemy){
        scoreLabel.setScore(scoreLabel.getScore());
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
            scoreLabel.setScore(scoreLabel.getScore() + time);
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
                    while (GameScene.isPaused) {
                        wait();
                    }
                }
            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }
    }
}
