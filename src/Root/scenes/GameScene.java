package Root.scenes;


import Root.Application.AudioManager;

import Root.GameObjects.PickUps.Pickup;
import Root.UserInterface.CustomLable;
import Root.Application.Main;
import Root.GameObjects.*;
import Root.GameObjects.PickUps.SpeedUp;
import javafx.application.Platform;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


import java.util.ArrayList;
import java.util.Random;

import static javafx.scene.input.KeyCode.ENTER;
import static javafx.scene.input.KeyCode.SPACE;

public class GameScene implements Runnable {
    private Scene scene;
    private Group group;
    private Player player;
    private Coin coin;
    private GameOverScene gameOverScene;
    private Main mainMenu;
    private CustomLable ScoreLable;
    private CustomLable LevelLable;
    private CustomLable Hp;
    private int level;
    private int scoreLevelCounter;
    private ArrayList<Enemy> enemies;
    private ArrayList<Pickup> pickups;
    public static boolean isPaused = false;
    private Text pauseText;
    public SpeedUp speedUp;
    public GameScene(Main mainMenu) {
        AudioManager.GameBGM();

        enemies = new ArrayList<>();
        pickups=new ArrayList<>();

        pauseText=new Text("Paused");
        pauseText.setFont(Font.font("Verdana", FontWeight.BOLD, 30));
        pauseText.setFill(Paint.valueOf("BLUE"));
        pauseText.setVisible(false);


        level = 0;
        scoreLevelCounter = 50;






        this.mainMenu = mainMenu;

        Player.dead = false;
        Random randomPosition = new Random();

        player = new Player(50, 500, 30);

        ScoreLable = new CustomLable("Score",0,Color.PALEVIOLETRED,Font.font("Verdana", FontWeight.BOLD, 20));
        LevelLable=new CustomLable("Level",level,Color.RED,Font.font("Verdana", FontWeight.BOLD, 20));
        Hp=new CustomLable("HP",player.getHealthPoint(),Color.VIOLET,Font.font("Verdana", FontWeight.BOLD, 20));



        coin = new Coin(28 + randomPosition.nextInt(800), 28 + randomPosition.nextInt(600), 35, player, ScoreLable);


        group = new Group(player);
        group.getChildren().addAll(ScoreLable,LevelLable, pauseText,Hp);
        group.getChildren().addAll(coin.getCoin());

        scene = new Scene(group,800,600,Color.CYAN);
        scene.setCursor(Cursor.NONE);

        //Pickups
        speedUp=new SpeedUp(50,50,player);
        speedUp.setX(100);
        speedUp.setY(100);
        group.getChildren().addAll(speedUp);
        pickups.add(speedUp);





        mainMenu.getWindow().resizableProperty().setValue(true);
        Thread mainThread = new Thread(this);
        mainThread.start();
    }

    private synchronized void resume() {
        isPaused = false;
        notify();
        player.resume();
        coin.resume();
        enemies.forEach(Enemy::resume);
    }



    private void controlScene(){
        getScene().addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if(e.getCode() == ENTER){
                pauseText.setVisible(false);
                resume();
            }
            if(e.getCode() == SPACE){
                isPaused = true;
                pauseText.setVisible(true);
            }
        });
    }

    public Scene getScene() {
        return this.scene;
    }

    private void checkLevel() {
        if (ScoreLable.getValue() >= scoreLevelCounter && level < level+1) {
            level++;
            Pickup pickup = pickups.get(new Random().nextInt(pickups.size()));
            pickup.setVisible(true);
            scoreLevelCounter += 50;
            LevelLable.setValue(level);


            for(Enemy enemy:enemies){
                enemy.setSpeed(enemy.getSpeed()+.2);
            }

            if(level % 3 == 0 || level == 1 ){

                Enemy1 enemy = new Enemy1(0, 0, 35, player, coin);
                enemies.add(enemy);
                speedUp.setEnemies(enemies);
                group.getChildren().addAll(enemy);
                enemy.setSpeed(2);


            }
            if(level == 5){

                Enemy enemy = new Enemy2(1024, 0, 35, player, coin);
                enemy.setSpeed(1);
                enemies.add(enemy);


                group.getChildren().addAll(enemy);
            }

        }
    }

    public void run() {
        while (!Player.dead) {
            Platform.runLater(() -> {
                //do anything
                controlScene();
                checkLevel();

                pauseText.setLayoutX(getScene().getWindow().getWidth()/2-30);
                pauseText.setLayoutY(getScene().getWindow().getHeight()/2-30);
                ScoreLable.setText(ScoreLable.getTextAsString());
                LevelLable.setText(LevelLable.getTextAsString());
                LevelLable.setLayoutX(getScene().getWindow().getWidth()/2-20);
                Hp.setLayoutX(getScene().getWindow().getWidth()-110);
                Hp.setValue(player.getHealthPoint());
                Hp.setText(Hp.getTextAsString());
            });


            try {
                Thread.sleep(40);
            }  catch (Exception ignored) {
                ignored.printStackTrace();
            }
        }
        //runs when player is dead
        Platform.runLater(() ->
        {
            AudioManager.mediaPlayer.stop();
            gameOverScene = new GameOverScene(mainMenu, ScoreLable, level);
            mainMenu.getWindow().setScene(gameOverScene.getScene());
        });
    }
}

