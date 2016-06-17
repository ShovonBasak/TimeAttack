package Root.GameObjects.PickUps;

import Root.GameObjects.Enemy;
import Root.GameObjects.Player;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tazim on 6/17/2016.
 */
public class SpeedDown extends Pickup {
    private List<Enemy> enemies=new ArrayList<>();

    public SpeedDown(int height, int width, Player player){
        setVisible(false);
        setPlayer(player);
        setHeight(height);
        setWidth(width);
        setFill(new ImagePattern(new Image("image/SlowDown.gif")));
        thisThread = new Thread(this);
        thisThread.start();
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    @Override
    public void Trigger() {
        for (Enemy e:enemies) {
            e.setSpeed(e.getSpeed()-.2);
        }
    }

    @Override
    public void run() {
        while (!Player.dead) {
            Platform.runLater(() -> {

                if(this.intersect(player) && isVisible()){
                    Trigger();
                    collidesWithPlayer();
                    this.setVisible(false);
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
