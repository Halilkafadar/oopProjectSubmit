package oopprojecthalilkayra;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {
    
    // database informations. only for this class
    final private static int port = 3306;
    final private static String host = "mysql-dbdeneme.alwaysdata.net";
    final private static String dbUser = "dbdeneme";
    final private static String dbPassword = "123456Mm..";
    final private static String dbName = "dbdeneme_2025";
    final private static String dburl = "jdbc:mysql://"+host+":"+port+"/"+dbName;
    
    private Connection cn = null;
     
     
    public  Connection getConnection(){
        
        try {
            setCn(DriverManager.getConnection(dburl, dbUser, dbPassword));
            System.out.println("\nYou're connecting to the  online server please wait...");
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("\nServer Connection Failed");
        }
        
        return getCn();
    }
    
    public Connection getCn() {
        return cn;
    }
    
    public void setCn(Connection cn) {
        this.cn = cn;
    }
    
}