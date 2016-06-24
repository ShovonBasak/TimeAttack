package Root.scenes;


import Root.Application.AudioManager;

import Root.GameObjects.PickUps.Health;
import Root.GameObjects.PickUps.Pickup;
import Root.GameObjects.PickUps.SpeedDown;
import Root.UserInterface.CustomLable;
import Root.Application.Main;
import Root.GameObjects.*;
import Root.GameObjects.PickUps.SpeedUp;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;


import java.util.ArrayList;
import java.util.Random;

import static javafx.scene.input.KeyCode.ENTER;
import static javafx.scene.input.KeyCode.ESCAPE;
import static javafx.scene.input.KeyCode.SPACE;

public class GameScene implements Runnable {
    private Scene scene;
    private Pane Pane;
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
    private SpeedUp speedUp;
    private SpeedDown speedDown;
    private Health health;

    public GameScene(Main mainMenu) {
        AudioManager.GameBGM();

        enemies = new ArrayList<>();
        pickups=new ArrayList<>();




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


        Pane = new Pane(player);
        Pane.getChildren().addAll(ScoreLable,LevelLable,Hp);
        Pane.getChildren().addAll(coin.getCoin());

        //Background background = new Background(new BackgroundFill(Color.DEEPSKYBLUE, CornerRadii.EMPTY, Insets.EMPTY));
        //Pane.setBackground(background);
        Pane.setStyle("-fx-background-color: linear-gradient(from 10% 25% to 100% 50%, #45484d , #661a33);");


        scene = new Scene(Pane,800,600);
        scene.setCursor(Cursor.NONE);

        scene.addEventFilter(KeyEvent.KEY_PRESSED, e -> {
            if(e.getCode() == ESCAPE){
                isPaused = true;
                mainMenu.getWindow().setScene(new PauseMenu(mainMenu,this).getScene());
            }
        });

        //Pickups


        health = new Health(50,50,player);
        speedDown = new SpeedDown(50,50,player);
        speedUp = new SpeedUp(50,50,player);


        Pane.getChildren().addAll(speedUp,health,speedDown);
        pickups.add(speedUp);
        pickups.add(health);
        pickups.add(speedDown);






        mainMenu.getWindow().resizableProperty().setValue(true);
        Thread mainThread = new Thread(this);
        mainThread.start();
    }

    public synchronized void resume() {
        isPaused = false;
        notify();
        player.resume();
        coin.resume();
        enemies.forEach(Enemy::resume);
    }




    public Scene getScene() {
        return this.scene;
    }

    private void checkLevel() {
        if (ScoreLable.getValue() >= scoreLevelCounter && level < level+1) {
            level++;
            Pickup pickup = pickups.get(new Random().nextInt(pickups.size()));
            pickup.setRandomPosition();
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
                speedDown.setEnemies(enemies);
                Pane.getChildren().addAll(enemy);
                enemy.setSpeed(2);


            }
            if(level == 5){

                Enemy enemy = new Enemy2(1024, 0, 35, player, coin);
                enemy.setSpeed(2);
                enemies.add(enemy);
                speedUp.setEnemies(enemies);
                speedDown.setEnemies(enemies);
                Pane.getChildren().addAll(enemy);
            }

        }
    }

    public void run() {
        while (!Player.dead) {
            Platform.runLater(() -> {
                //do anything
                if(!isPaused){

                    checkLevel();

                    ScoreLable.setText(ScoreLable.getTextAsString());
                    LevelLable.setText(LevelLable.getTextAsString());
                    LevelLable.setLayoutX(mainMenu.getWindow().getWidth()/2-20);
                    Hp.setLayoutX(mainMenu.getWindow().getWidth()-110);
                    Hp.setValue(player.getHealthPoint());
                    Hp.setText(Hp.getTextAsString());



                }


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
            mainMenu.getWindow().setResizable(false);
            gameOverScene = new GameOverScene(mainMenu, ScoreLable, level);
            mainMenu.getWindow().setScene(gameOverScene.getScene());
        });
    }
}

