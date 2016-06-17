package Root.GameObjects.PickUps;

import Root.GameObjects.Enemy;
import Root.GameObjects.Player;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;



/**
 * Created by tazim on 6/17/2016.
 */
public class Health extends Pickup {


    public Health(int height, int width, Player player){
        setVisible(false);
        setPlayer(player);
        setHeight(height);
        setWidth(width);
        setFill(new ImagePattern(new Image("image/HP.gif-c200")));
        thisThread = new Thread(this);
        thisThread.start();
    }


    @Override
    public void Trigger() {
        player.addHealth(20);
    }

    @Override
    public void run() {
        while (!player.dead) {
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
