package gameObjects;


import javafx.application.Platform;

public class Enemy2 extends Enemy {

    Player player;


    public Enemy2(double radius, Player player) {
        super(radius, "Blue");
        this.player = player;

        thisThread = new Thread(this);
        thisThread.start();
    }






    public void run() {
        //This object needs to follow the player
        while (!player.isDead()) {
            System.out.println("X:" + player.getCenterX() + "\nY:" + player.getCenterY());
            System.out.println("X:" + getCenterX() + "\nY:" + getCenterY());
            Platform.runLater(() -> {
                double newX;
                double newY;

                //Y=Mx(M=dy/dx)
                newY = (player.getCenterY() / player.getCenterX());
                System.out.println("newY:" + String.valueOf(newY));

                //X=My(M=dx/dy)
                newX = (player.getCenterX() / player.getCenterY());
                System.out.println("newX:" + String.valueOf(newX));

                if (player.getCenterX() > getCenterX()) {
                    setCenterX(getCenterX() + newX);
                }

                if (player.getCenterX() < getCenterX()) {
                    setCenterX(getCenterX() - newX);
                }

                if (player.getCenterY() > getCenterY()) {
                    setCenterY(getCenterY() + newY);
                }

                if (player.getCenterY() < getCenterY()) {
                    setCenterY(getCenterY() - newY);
                }


            });

            try {
                thisThread.sleep(1);
            } catch (Exception e) {
            }
        }


    }
}
