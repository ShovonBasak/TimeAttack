package gameObjects;

import javafx.application.Platform;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import scenes.LevelOne;


public class Coin extends movableObject implements Runnable{
    private Text timeLabel;
    private int time;
    private LevelOne gameScene;

    private double radius;
    private double rightBound;
    private double leftBound;
    private double upperBound;
    private double lowerBound;
    public double centerX;
    public double centerY;
    private double speedX;
    private double speedY;
    private double adjustTimeLabelX;
    private double adjustTimeLabelY;


    public Coin(double centerX, double centerY, double radius, LevelOne gameScene){
        super(centerX, centerY, radius, "Yellow");

        radius = this.getRadius();
        centerX = this.getCenterX();
        centerY = this.getCenterY();

        rightBound = centerX + radius;
        leftBound = centerX - radius;
        upperBound = centerY - radius;
        lowerBound = centerY + radius;
        speedX = 10;
        speedY = 10;
        time = 16;
        adjustTimeLabelX = -5;
        adjustTimeLabelY = 3;

        timeLabel = new Text("" + time);
        timeLabel.setX(centerX + adjustTimeLabelX);
        timeLabel.setY(centerY + adjustTimeLabelY);
        this.gameScene = gameScene;
        thisTherad = new Thread(this);
        thisTherad.start();
        gameScene.group.getChildren().addAll(this ,timeLabel);
    }

    public Text getTimeLabel() {
        return timeLabel;
    }


    public void moveRight(){
        this.setCenterX(this.centerX + speedX);
        timeLabel.setX(this.getCenterX() + adjustTimeLabelX);
        timeLabel.setY(this.getCenterY() + adjustTimeLabelY);
    }
    public void moveLeft(){
        this.setCenterX(this.centerX - speedX);
        timeLabel.setX(this.getCenterX() + adjustTimeLabelX);
        timeLabel.setY(this.getCenterY() + adjustTimeLabelY);
    }
    public void moveDown(){
        this.setCenterY(this.centerY + speedY);
        timeLabel.setX(this.getCenterX() + adjustTimeLabelX);
        timeLabel.setY(this.getCenterY() + adjustTimeLabelY);
    }
    public void moveUp(){
        this.setCenterY(this.centerY - speedY);
        timeLabel.setX(this.getCenterX() + adjustTimeLabelX);
        timeLabel.setY(this.getCenterY() + adjustTimeLabelY);
    }


    public void run() {
        while(!Player.dead) {
            Platform.runLater(() -> {
                this.centerX = this.getCenterX();
                this.centerY = this.getCenterY();
                this.rightBound = this.centerX + this.radius;
                this.leftBound = this.centerX - this.radius;
                this.upperBound = this.centerY - this.radius;
                this.lowerBound = this.centerY + this.radius;

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
