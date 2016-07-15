package Root.GameObjects.PickUps;

import Root.GameObjects.Enemy;
import Root.GameObjects.Player;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;



public class SpeedUp extends Pickup {

    public SpeedUp(int height, int width, Player player){
        setVisible(false);
        setPlayer(player);
        setHeight(height);
        setWidth(width);
        setFill(new ImagePattern(new Image("image/SpeedUp.gif")));
        thisThread = new Thread(this);
        thisThread.start();
    }


    @Override
    public void Trigger() {
        for (Enemy e:Enemy.list) {
            e.setSpeed(e.getSpeed()+.2);
        }
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