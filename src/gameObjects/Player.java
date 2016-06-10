package gameObjects;


import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.ImagePattern;
import scenes.GameScene;

public class Player extends MovableObject {
    public static boolean dead = false;

    public synchronized void resume() {
        GameScene.isPaused = false;
        notify();
    }

    public Player(double centerX, double centerY, double radius) {

        super(centerX, centerY, radius, "green");
        setFill(new ImagePattern(new Image("Resources/image/Player.gif")));
        thisThread = new Thread(this);
        thisThread.start();
        try {
            Media audioClip = new Media("file:///" +
                    System.getProperty("user.dir").replace("\\","//")+
                    "//src//Resources//AudioClip//GameBGM.mp3");



            MediaPlayer mediaPlayer= new MediaPlayer(audioClip);
            mediaPlayer.play();
        }
        catch (Exception e){
            e.printStackTrace();
        }

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
