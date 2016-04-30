package scenes;


import gameObjects.Enemy1;
import gameObjects.Player;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GameScene implements Runnable{
    Stage window;
    Scene background;
    Group group;
    Player player;
    Enemy1 enemy;
    Text text;
    Thread enemyThread;

    boolean horizontalDirection;
    boolean verticalDirection;
    double rightWall;
    double leftWall;
    double topWall;
    double bottomWall;


    public GameScene(){
        horizontalDirection = true;
        verticalDirection = true;
    }

    public void show(){
        window = new Stage();
        window.setTitle("Time Attack");

        player = new Player(20);
        player.setLayoutX(50);
        player.setLayoutY(50);

        enemy = new Enemy1(100, 100, 15);
        enemy.setSpeed(4);

        group = new Group(player,enemy);

        background = new Scene(group,800,600);

        window.setScene(background);
        window.show();

        enemyThread = new Thread(this);
        enemyThread.start();
    }

    public void run() {
        enemy1Logic();
    }

    public void enemy1Logic() {
        while(enemy.isVisible()){
            enemy.radius = enemy.getRadius();
            enemy.centerX = enemy.getCenterX();
            enemy.centerY = enemy.getCenterY();
            enemy.rightBound = enemy.centerX + enemy.radius;
            enemy.leftBound = enemy.centerX - enemy.radius;
            enemy.upperBound = enemy.centerY - enemy.radius;
            enemy.lowerBound = enemy.centerY + enemy.radius;

            rightWall = window.getWidth() - 20;
            leftWall = 5;
            topWall = 5;
            bottomWall = window.getHeight() - 40;

            if(horizontalDirection){
                if(enemy.rightBound < rightWall){
                    enemy.setCenterX(enemy.centerX + enemy.speedX);
                }
                else{
                    horizontalDirection = false;
                }
            }
            else{
                if(enemy.leftBound > leftWall){
                    enemy.setCenterX(enemy.centerX - enemy.speedX);
                }
                else{
                    horizontalDirection = true;
                }
            }


            if(verticalDirection){
                if(enemy.lowerBound < bottomWall){
                    enemy.setCenterY(enemy.centerY + enemy.speedY);
                }
                else{
                    verticalDirection = false;
                }
            }
            else{
                if(enemy.upperBound > topWall){
                    enemy.setCenterY(enemy.centerY - enemy.speedY);
                }
                else{
                    verticalDirection = true;
                }
            }

            try{
                enemyThread.sleep(40);
            }catch (Exception e){}
        }
    }

}
