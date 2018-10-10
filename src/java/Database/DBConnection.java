package Database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author BEST WAY
 */
public class DBConnection {
    private static String URL="jdbc:mysql://localhost:3306/tvStation";
    private static String usernameDB="root";
    private static String passwordDB="root";
    private static Connection con;
    public static Connection getConnection() throws SQLException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(URL, usernameDB, passwordDB);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
     return con;
    }
}
