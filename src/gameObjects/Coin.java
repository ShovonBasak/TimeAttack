package gameObjects;

import javafx.application.Platform;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import scenes.LevelOne;


public class Coin extends movableObject {
    private Text timeLabel;
    private int time;
    private LevelOne gameScene;

    private double rightBound;
    private double leftBound;
    private double upperBound;
    private double lowerBound;
    private double adjustTimeLabelX;
    private double adjustTimeLabelY;


    public Coin(double centerX, double centerY, double radius, LevelOne gameScene){
        super(centerX, centerY, radius, "Yellow");


        rightBound = getCenterX() + getRadius();
        leftBound = getCenterX() - getRadius();
        upperBound = getCenterY() - getRadius();
        lowerBound = getCenterY() + getRadius();
        speed = 1;
        time = 16;
        adjustTimeLabelX = -5;
        adjustTimeLabelY = 3;

        timeLabel = new Text("" + time);
        timeLabel.setX(getCenterX() + adjustTimeLabelX);
        timeLabel.setY(getCenterY() + adjustTimeLabelY);
        this.gameScene = gameScene;
        thisTherad = new Thread(this);
        thisTherad.start();
        gameScene.group.getChildren().addAll(this ,timeLabel);
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
        while(!Player.dead) {
            Platform.runLater(() -> {
                rightBound = getCenterX() + getRadius();
                leftBound = getCenterX() - getRadius();
                upperBound = getCenterY() - getRadius();
                lowerBound = getCenterY() + getRadius();

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
