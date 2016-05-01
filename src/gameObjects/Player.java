package gameObjects;


import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;

public class Player extends movableObject {
    private boolean dead;

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public boolean isDead() {
        return dead;
    }


    public Player(double centerX, double centerY, double radius) {
        super(centerX, centerY, radius, "green");
        dead = false;
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



    public void run() {
        while (!this.isDead()) {
            Platform.runLater(() -> {
                    getScene().addEventFilter(MouseEvent.MOUSE_DRAGGED, e->{
                        this.setCenterX(e.getSceneX());
                        this.setCenterY(e.getSceneY());
                    });

                    /*getScene().setOnKeyPressed(e -> {
                        //move according to key press
                        if (e.getCode().equals(KeyCode.RIGHT)) {
                            this.moveRight();
                        }

                        if (e.getCode().equals(KeyCode.LEFT)) {
                            this.moveLeft();
                        }

                        if (e.getCode().equals(KeyCode.UP)) {
                            this.moveUp();
                        }

                        if (e.getCode().equals(KeyCode.DOWN)) {
                            this.moveDown();
                        }
                    });*/
            });

            try {
                Thread.sleep(1);
            } catch (Exception ignored) {
            }
        }
    }


}
