package Root.gameObjects.PickUps;

import Root.gameObjects.Enemy;
import Root.gameObjects.Player;
import Root.scenes.GameScene;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by tazim on 6/13/2016.
 */
public class SpeedUp extends Pickup {

    List<Enemy> enemies=new ArrayList<>();

    public SpeedUp(int height, int width, Player player){
        setPlayer(player);
        setHeight(height);
        setWidth(width);
        setFill(new ImagePattern(new Image("image/SpeedUp.png")));
        setRandomPosition();
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    @Override
    public void Trigger() {
        for (Enemy e:enemies) {
            e.setSpeed(e.getSpeed()+.2);
        }

    }

    @Override
    public void run() {
        while (isVisible()) {

            try {
                if(intersect(player)){
                    Trigger();
                    this.setVisible(false);
                }
                Thread.sleep(20);

            } catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }
    }
}
