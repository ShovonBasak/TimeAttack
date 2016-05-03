package gameObjects;

public abstract class Enemy extends movableObject{
    protected boolean horizontalDirection;
    protected boolean verticalDirection;
    Player player;

    public Enemy(double centerX, double centerY, double radius, String color, Player player) {
        super(centerX, centerY, radius, color);
        this.player = player;
    }

    public boolean intersect(Player player) {
        if ((player.intersects(this.getBoundsInParent()))) {
            return true;
        }
        return false;
    }


    public void setHorizontalDirection(boolean direction){
        horizontalDirection = direction;
    }
    public void setVerticalDirection(boolean direction){
        verticalDirection = direction;
    }
}
