package Root.GameObjects.PickUps;

import Root.GameObjects.Enemy;
import Root.GameObjects.Player;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

/**
 * Created by SBS on 7/15/2016.
 */
public class HourGlass extends Pickup{
    private static boolean status;
    private ObjectTimer timer;

    public HourGlass(int height, int width, Player player){
        status = false;
        setVisible(false);
        setPlayer(player);
        setHeight(height);
        setWidth(width);
        timer = new ObjectTimer();
        this.setFill(new ImagePattern(new Image("image/HourGlass.gif")));
        thisThread = new Thread(this);
        thisThread.start();
    }

    public static boolean isPaused(){
        return status;
    }

    @Override
    public void Trigger() {
        timer = new ObjectTimer();
        HourGlass.status = true;
    }

    public void resume(){
        Enemy.list.forEach(Enemy::resume);
    }

    @Override
    public void run() {
        while (!Player.dead) {
            Platform.runLater(() -> {

                if(this.intersect(player) && isVisible()) {
                    Trigger();
                    collidesWithPlayer();
                }
                else if(timer.getTime() == 5 && HourGlass.isPaused()){
                    HourGlass.status = false;
                    resume();
                }
            });


            try {
                Thread.sleep(20);

            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }
    }
}
