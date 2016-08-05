package Root.GameObjects;

import Root.GameObjects.PickUps.HourGlass;
import Root.scenes.GameScene;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;


import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Created by SBS on 7/23/2016.
 */
public class Enemy3 extends Enemy {

    private Player player;
    private CandyCane candyCane;
    private int y;
    private String direction;
    private final double PI = 3.14159;

    public Enemy3(double centerX, double centerY, double radius, String color, Player player, CandyCane candyCane) {
        super(centerX, centerY, radius, color, player, candyCane);
        this.setFill(new ImagePattern(new Image("image/enemy3.gif")));
        this.player = player;
        this.candyCane = candyCane;

        setSpeed(1);
        thisThread = new Thread(this);
        thisThread.start();
    }

    public void setRelativeY(int y){
        this.y = y;
    }

    public void setDirection(String direction){
        this.direction = direction;
    }

    public void move(){
        if(direction.equals("right")){
            super.moveRight();
            this.setCenterY((70*sin(this.getCenterX()*PI/64))+y);
        }
        else if(direction.equals("left")){
            super.moveLeft();
            this.setCenterY((70*cos(this.getCenterX()*PI/64))+y);
        }

    }

    @Override
    public void run() {
        while (!Player.dead) {
            Platform.runLater(() -> {
                this.move();

                if (this.intersect(player)) {
                    player.substractHealth(1);
                }

                if (this.intersect(candyCane)) {
                    candyCane.collides(this);
                }
            });
            try {
                Thread.sleep(30);
                synchronized (this) {
                    while (GameScene.isPaused || HourGlass.isPaused()) {
                        wait();
                    }
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
            catch (Exception ignored) {}
        }
    }
}
