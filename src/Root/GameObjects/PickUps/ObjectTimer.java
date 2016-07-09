package Root.GameObjects.PickUps;

import javafx.application.Platform;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Timer;
import java.util.TimerTask;

import static Root.scenes.GameScene.isPaused;

public class ObjectTimer extends java.util.Timer{
    private Timer timer;
    private int period;
    private int delay;
    private int interval;
    public int time;

    public ObjectTimer(int interval){
        this.timer = new Timer();
        this.delay = 1000;
        this.period = 1000;
        this.interval = interval;

        countDown();
    }

    private void countDown(){
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                Platform.runLater(()-> {
                    if(!isPaused)
                        time = interval--;
                });
            }
        }, delay, period);
    }

    public int getTime(){
        return time;
    }
}
