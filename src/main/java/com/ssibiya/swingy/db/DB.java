package com.ssibiya.swingy.db;

import com.ssibiya.swingy.model.characters.heroes.Hero;
import com.ssibiya.swingy.model.characters.heroes.Saiyan;
import com.ssibiya.swingy.view.console.StartScreen;

import java.sql.*;

public class DB {
    private static Connection con;
    StartScreen screen = new StartScreen();

    private void getConnection() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("JDBC:sqlite:Swingy.db");
        }catch (ClassNotFoundException e){
            screen.printError(e.getMessage());
            System.exit(0);
        }
        screen.printSuccessDB("Database");
        initialise();
    }

    private void initialise() throws SQLException {

        Statement stmt = con.createStatement();
        ResultSet result = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='HEROES'");

        if (!result.next()) {
            String sql = "CREATE TABLE HEROES " +
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "name varchar(30) NOT NULL,"+
                    "type varchar(10) NOT NULL,"+
                    "level INT NOT NULL,"+
                    "experience INT NOT NULL,"+
                    "hitpoints INT NOT NULL,"+
                    "attack INT NOT NULL,"+
                    "defense INT NOT NULL,"+
                    "speed INT NOT NULL,"+
                    "attSpd INT NOT NULL,"+
                    "defSpd INT NOT NULL)";
            stmt.execute(sql);
            screen.printSuccessDB("Table");
        }
    }

    public ResultSet getAllHeroStats() throws ClassNotFoundException, SQLException {
        if (con == null)
            getConnection();

        Statement stmt = con.createStatement();
        ResultSet result = stmt.executeQuery("SELECT * FROM HEROES");

        return result;
    }

    public void insertNewHeroStats(Hero hero) throws SQLException, ClassNotFoundException {

        if (con == null)
            getConnection();

        PreparedStatement prepStmt = con.prepareStatement("INSERT INTO HEROES values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

        prepStmt.setString(2, hero.getName());
        prepStmt.setString(3, hero.getType());
        prepStmt.setInt(4, hero.getLevel());
        prepStmt.setInt(5, hero.getExperience());
        prepStmt.setInt(6, hero.getHitpoints());
        prepStmt.setInt(7, hero.getAttack());
        prepStmt.setInt(8, hero.getDefense());
        prepStmt.setInt(9, hero.getSpeed());
        prepStmt.setInt(10, hero.getAtt_speed());
        prepStmt.setInt(11, hero.getDef_speed());

        prepStmt.execute();
        screen.printSuccessDB("Insert");
    }

    public void updateHeroStats(Hero hero) throws SQLException, ClassNotFoundException {

        if (con == null)
            getConnection();

        String sql = "UPDATE HEROES SET experience = " + hero.getExperience() +
                ", hitpoints = " + hero.getHitpoints() +
                ", attack = " + hero.getAttack() +
                ", defense = " + hero.getDefense() +
                ", speed = " + hero.getSpeed() +
                ", attspd = " + hero.getAtt_speed() +
                ", defspd = " + hero.getDef_speed() +
                " WHERE name = '" + hero.getName() + "'";

        PreparedStatement prepStmt = con.prepareStatement(sql);
        prepStmt.executeUpdate();
        screen.printSuccessDB("Update");
    }
}
