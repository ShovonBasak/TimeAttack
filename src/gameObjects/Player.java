package gameObjects;


import javafx.application.Platform;
import javafx.scene.input.MouseEvent;

public class Player extends movableObject {
    public static boolean dead = false;


    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public boolean isDead() {
        return dead;
    }


    public Player(double centerX, double centerY, double radius) {
        super(centerX, centerY, radius, "green");
        setSpeed(1);

        thisTherad = new Thread(this);
        thisTherad.start();
    }

    public void setSpeed(double x) {
        speed = x;
    }

    public double getSpeed() {
        return speed;
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
        while (!dead) {
            Platform.runLater(() ->
                movePlayer()
            );

            try {
                Thread.sleep(1);
            } catch (Exception ignored) {
            }
        }
    }


}
