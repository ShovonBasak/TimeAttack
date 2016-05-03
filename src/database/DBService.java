package database;


import Application.ScoreBoard;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class DBService {
    private DBCon dbCon ;
    private int isDatabaseUpdated;

    public DBService(){
        dbCon = new DBCon();
    }

    public int updateScoreBoard(String userName, String score, String lvlReached) {
        String query = "insert into scoreBoard values('" + userName + "', " + "'" + score + "', " + "'" + lvlReached+ "'" + ")";
        if(dbConnectionCheck()) {
            try {
                return isDatabaseUpdated = dbCon.inUpdateDelete(query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 420;
    }

    public boolean dbConnectionCheck() {
        return dbCon.connectionCheck();
    }

    public String pastScore(String username) {
        String query = "SELECT score FROM scoreBoard WHERE name='" + username + "'";
        System.out.println(query);
        try {
            ResultSet rs = dbCon.selectQuery(query);
            if (rs.next()) {
                String score = rs.getString("score");

                return score;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public ArrayList<ScoreBoard> getScoreList() {
        String query="SELECT name,score,lvlReached FROM scoreBoard WHERE score > 0 ORDER By score DESC";
        System.out.println(query);

        ArrayList<ScoreBoard> scoreList = new ArrayList<>();
        try {
            ResultSet rs = dbCon.selectQuery(query);
            while (rs.next()) {
                scoreList.add(new ScoreBoard(rs.getString("name").toUpperCase(), rs.getString("score"), rs.getString("lvlReached") ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scoreList;
    }
}