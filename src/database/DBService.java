package database;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;


public class DBService {
    private DBCon dbCon = new DBCon();
    private Gamer newGamer, logGamer;
    private String user, pass;
    private int score;
    private int isUserInserted;
    private boolean isNameAvailable = true;

    public int playerRegistration(String u, String p) {
        newGamer = new Gamer(u, p, 0);
        String query = "INSERT INTO scoretable VALUES('" + newGamer.getGamerName() + "'," + "'" + newGamer.getGamerPassword() + "'," + newGamer.getGamerPoint() + "," + 1 + ");";
        System.out.println(query);

        try {
            return isUserInserted = dbCon.inUpdateDelete(query);
        } catch (SQLIntegrityConstraintViolationException e) {
            isNameAvailable = false;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 420;
    }

    public boolean dbConnctionCheck() {
        return dbCon.connectionCheck();
    }

    public boolean getNameAvailable() {
        return isNameAvailable;
    }

    public boolean isUserinserted() {
        if (isUserInserted == 1)
            return true;
        else
            return false;
    }

    public boolean playerLogin(String u, String p) {
        logGamer = new Gamer(u, p);
        String query = "SELECT auth FROM `scoretable` WHERE name='" + logGamer.getGamerName() + "' And BINARY pass='" + logGamer.getGamerPassword() + "';";
        System.out.println(query);
        try {
            ResultSet rs = dbCon.selectQuery(query);
            if (rs.next()) {
                int a = rs.getInt("auth");
                if (a == 1)
                    return true;
                else
                    return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;

    }


    public int pastScore(String username) {

        String query = "SELECT score FROM `scoretable` WHERE name='" + username + "';";
        System.out.println(query);
        try {
            ResultSet rs = dbCon.selectQuery(query);
            if (rs.next()) {
                int score = rs.getInt("score");

                return score;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;

    }


    public int updatescore(String userName,int score) {
        String query = "UPDATE scoretable Set Score="+score+" WHERE name='"+userName+"';";
        System.out.println(query);

        try {
            return isUserInserted = dbCon.inUpdateDelete(query);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 420;
    }

    public boolean unregister(String UserName) {

        String query = "DELETE FROM `scoretable` WHERE name='" + UserName + "';";
        System.out.println(query);
        try {
            int dl = dbCon.inUpdateDelete(query);

            if (dl == 1)
                return true;
            else
                return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }




    public ArrayList<Gamer> playerScore() {
        int rankCounter = 0;
        String query="SELECT nAme,score FROM `scoretable` WHERE score > 0 ORDER By score DESC";
        System.out.println(query);

        ArrayList<Gamer> scoreList = new ArrayList<>();
        try {
            ResultSet rs = dbCon.selectQuery(query);
            while (rs.next()) {
                scoreList.add(new Gamer(++rankCounter, rs.getString("name").toUpperCase(), rs.getInt("score") ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return scoreList;
    }
}