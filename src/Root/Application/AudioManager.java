package Root.Application;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * Created by tazim on 6/16/2016.
 */
public class AudioManager {
    public static MediaPlayer mediaPlayer;

    public static  void buttonAudio(){
        try{
            Media audioClip = new Media("file:///" +
                    System.getProperty("user.dir").replace("\\","//")+
                    "//Resources//AudioClip//Button01.wav");

            MediaPlayer mediaPlayer= new MediaPlayer(audioClip);
            mediaPlayer.play();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void LoadingScreenAudio(){
        try{
            Media audioClip = new Media("file:///" +
                    System.getProperty("user.dir").replace("\\","//")+
                    "//Resources//AudioClip//LoadingScreen.mp3");

            mediaPlayer= new MediaPlayer(audioClip);
            mediaPlayer.play();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void TitleScreenAudio(){
        try{
            Media audioClip = new Media("file:///" +
                    System.getProperty("user.dir").replace("\\","//")+
                    "//Resources//AudioClip//TitleScreenAudio.mp3");

            mediaPlayer= new MediaPlayer(audioClip);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public static void GameBGM(){
        try{
            Media audioClip = new Media("file:///" +
                    System.getProperty("user.dir").replace("\\","//")+
                    "//Resources//AudioClip//GameBGM.mp3");

            mediaPlayer= new MediaPlayer(audioClip);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.play();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void MainMenuAudio(){
        try{
            Media audioClip = new Media("file:///" +
                    System.getProperty("user.dir").replace("\\","//")+
                    "//Resources//AudioClip//MainMenu.mp3");

            mediaPlayer= new MediaPlayer(audioClip);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

            mediaPlayer.play();
        }catch (Exception e){
            e.printStackTrace();
        }

    }



}
