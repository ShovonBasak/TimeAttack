package database;

import java.sql.*;

public class DBCon {
    private  String sqlQuery;
    private  Connection con;
    private  Statement stmt;
    private  ResultSet result;
    private boolean connectionCheck=true;
    public  DBCon() {
        try {
            //step1 load the driver class
            Class.forName("oracle.jdbc.driver.OracleDriver");

            //step2 create  the connection object
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "scott", "tiger");

            //step3 create the statement object
            stmt = con.createStatement();
        } catch (Exception ex) {
            connectionCheck = false;
        }
    }

    public boolean connectionCheck(){
        return connectionCheck;

    }

    public int inUpdateDelete(String sql) throws SQLException {
        return stmt.executeUpdate(sql);
    }

    public ResultSet selectQuery(String sql) throws SQLException {
        return   result = stmt.executeQuery(sql);
    }


}
