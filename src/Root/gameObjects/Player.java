package Root.GameObjects;


import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.ImagePattern;
import Root.scenes.GameScene;

public class Player extends MovableObject {
    public static boolean dead = false;
    private int healthPoint=100;

    public synchronized void resume() {
        GameScene.isPaused = false;
        notify();
    }

    public Player(double centerX, double centerY, double radius) {

        super(centerX, centerY, radius, "green");
        setFill(new ImagePattern(new Image("image/Player.gif")));
        thisThread = new Thread(this);
        thisThread.start();
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public void addHealth(int healthPoint) {
        this.healthPoint += healthPoint;
    }

    public void substractHealth(int healthPoint) {
        if(this.healthPoint-healthPoint>0){
            this.healthPoint -= healthPoint;
        }
        else dead=true;

    }



    private void movePlayer(){
        getScene().addEventFilter(MouseEvent.MOUSE_MOVED, e -> {
            if (!GameScene.isPaused) {
                if (e.getSceneX() < getScene().getWidth() && e.getSceneX() > getScene().getX()) {
                    this.setCenterX(e.getSceneX());
                }
                if (e.getSceneY() < getScene().getHeight() && e.getSceneY() > getScene().getY()) {
                    this.setCenterY(e.getSceneY());
                }
            }


        });
    }

    public void run() {
        while (!dead) {
            Platform.runLater(this::movePlayer);
            try {
                Thread.sleep(20);
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
