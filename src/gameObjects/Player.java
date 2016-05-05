package gameObjects;


import javafx.application.Platform;
import javafx.scene.input.MouseEvent;
import scenes.GameScene;

public class Player extends MovableObject {
    public static boolean dead = false;

    public boolean isDead() {
        return dead;
    }
    public synchronized void resume() {
        GameScene.isPaused = false;
        notify();
    }

    public Player(double centerX, double centerY, double radius) {
        super(centerX, centerY, radius, "green");
        setSpeed(1);

        thisThread = new Thread(this);
        thisThread.start();
    }


    public void movePlayer(){
        getScene().addEventFilter(MouseEvent.MOUSE_MOVED, e -> {
            if(e.getSceneX() < getScene().getWidth() && e.getSceneX() > getScene().getX()){
                this.setCenterX(e.getSceneX());
            }
            if(e.getSceneY() < getScene().getHeight() && e.getSceneY() > getScene().getY()){
                this.setCenterY(e.getSceneY());
            }

        });
    }

    public void run() {
        while (!isDead()) {
            Platform.runLater(this::movePlayer);

            try {
                thisThread.sleep(1);
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
