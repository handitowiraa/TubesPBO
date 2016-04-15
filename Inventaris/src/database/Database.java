/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Emp. Elesar II
 */
public class Database {
    private String dbUser = "root";
    private String dbPass = "@nim@ri@n142434";
    private Statement stmt = null;
    private Connection con = null;
    private ResultSet rs = null;
    
    public Database(){
        try{
            Class.forName("org.gjt.mm.mysql.Driver");
        } catch (Exception e){
            System.out.println(e);
        }
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/dbTubesPBO",dbUser,dbPass);
        } catch (Exception e){
            System.out.println(e);
        }
    }
    
    public ResultSet getData(String SQLString){
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(SQLString);
        } catch (Exception e) {
            System.out.println(e);
        }
        return  rs;
    }
    
    public void query(String SQLString) throws SQLException{
        try {
            stmt = con.createStatement();
            stmt.executeUpdate(SQLString);
        } catch (SQLException c){
            throw new SQLException("Error eksekusi query");
        }
    }
}