package Root.Application;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.net.URL;
import java.security.PublicKey;

/**
 * Created by tazim on 6/16/2016.
 */
public class AudioManager {
    public static MediaPlayer mediaPlayer;
    public static double volume=1;

    public static void updateVolume(){
        mediaPlayer.setVolume(volume);

    }

    public static  void buttonAudio(){
        try{
            Media audioClip = new Media("file:///" +
                    System.getProperty("user.dir").replace("\\","//").replace(" ","%20")+
                    "//Resources//AudioClip//Button01.wav");

            MediaPlayer mediaPlayer= new MediaPlayer(audioClip);
            mediaPlayer.setVolume(volume);
            mediaPlayer.play();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void LoadingScreenAudio(){
        try{
            Media audioClip = new Media("file:///" +
                    System.getProperty("user.dir").replace("\\","//").replace(" ","%20")+
                    "//Resources//AudioClip//LoadingScreen.mp3");

            mediaPlayer= new MediaPlayer(audioClip);
            mediaPlayer.setVolume(volume);
            mediaPlayer.play();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void TitleScreenAudio(){
        try{
            Media audioClip = new Media("file:///" +
                    System.getProperty("user.dir").replace("\\","//").replace(" ","%20")+
                    "//Resources//AudioClip//TitleScreenAudio.mp3");

            mediaPlayer= new MediaPlayer(audioClip);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setVolume(volume);
            mediaPlayer.play();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void TitleKeyPress(){
        try{
            Media audioClip = new Media("file:///" +
                    System.getProperty("user.dir").replace("\\","//").replace(" ","%20")+
                    "//Resources//AudioClip//TitleClickSound.mp3");

            MediaPlayer mediaPlayer= new MediaPlayer(audioClip);
            mediaPlayer.setVolume(volume);
            mediaPlayer.play();
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public static void GameBGM(){
        try{
            Media audioClip = new Media("file:///" +
                    System.getProperty("user.dir").replace("\\","//").replace(" ","%20")+
                    "//Resources//AudioClip//GameBGM.mp3");

            mediaPlayer= new MediaPlayer(audioClip);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setVolume(volume);
            mediaPlayer.play();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static void MainMenuAudio(){
        try{
            Media audioClip = new Media("file:///" +
                    System.getProperty("user.dir").replace("\\","//").replace(" ","%20")+
                    "//Resources//AudioClip//MainMenu.mp3");

            mediaPlayer= new MediaPlayer(audioClip);
            mediaPlayer.setVolume(volume);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

            mediaPlayer.play();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public static void CoinAudio(){
        try{
            Media audioClip = new Media("file:///" +
                    System.getProperty("user.dir").replace("\\","//").replace(" ","%20")+
                    "//Resources//AudioClip//Coin.mp3");



            MediaPlayer mediaPlayer= new MediaPlayer(audioClip);
            mediaPlayer.setVolume(volume*0.2);
            mediaPlayer.play();
        }catch (Exception e){e.printStackTrace();}

    }

    public static void gameOverMusic(){
        try{
            Media audioClip = new Media("file:///" +
                    System.getProperty("user.dir").replace("\\","//").replace(" ","%20")+
                    "//Resources//AudioClip//GameOver.mp3");

            mediaPlayer= new MediaPlayer(audioClip);
            mediaPlayer.setVolume(volume);
            mediaPlayer.play();
        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
