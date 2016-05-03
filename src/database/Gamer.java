package database;

import scenes.GameOverScene;

public class Gamer {
    private String gamerName;
    private String gamerPassword;
    private int gamerPoint;
    public Gamer(String gamerName, String password, int point){

    }

    public Gamer(String gamerName, String password){

    }

    public Gamer(int rank, String name, int score){

    }

    public String getGamerName() {
        return gamerName;
    }

    public void setGamerName(String gamerName) {
        this.gamerName = gamerName;
    }

    public String getGamerPassword() {
        return gamerPassword;
    }

    public void setGamerPassword(String gamerPassword) {
        this.gamerPassword = gamerPassword;
    }

    public int getGamerPoint() {
        return gamerPoint;
    }

    public void setGamerPoint(int gamerPoint) {
        this.gamerPoint = gamerPoint;
    }
}
