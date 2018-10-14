package Model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
    public ArrayList<TVStation> getAllStation(int flag) throws SQLException{
    ArrayList<TVStation> allStations=new ArrayList<>();
    Connection conn = getConnection();
    String query,name,url,country;
    int id,userId;
    Statement stmt;
    query= "select * from station where flag = '"+flag+"'";
    stmt = conn.createStatement();
    ResultSet rs=stmt.executeQuery(query);
    while(rs.next())
    {
        id=rs.getInt("idstation");
        name=rs.getString("name");
        url=rs.getString("url");
        country=rs.getString("country");
        userId=rs.getInt("userId");
        TVStation station = new TVStation(id,name,url,country,userId);
        allStations.add(station);

    }
    rs.close();
    stmt.close();
    conn.close();
    
    return allStations;
    }
    
    public ArrayList<User> getAllUnAcceptedUsers() throws SQLException{
    ArrayList<User> allUsers=new ArrayList<>();
    Connection conn = getConnection();
    String email,name,mobile,query;
    int id;
    Statement stmt;
    query= "select * from user where flag = '0'";
    stmt = conn.createStatement();
    ResultSet rs=stmt.executeQuery(query);
    while(rs.next())
    { 
        id = rs.getInt("id");
        name=rs.getString("name");
        email=rs.getString("email");
        mobile=rs.getString("mobile");
        User user = new User(id,name,email,mobile);
        allUsers.add(user);

    }
    rs.close();
    stmt.close();
    conn.close();
    
    return allUsers;
    }
    public User getUserById(int id) throws SQLException{
    Connection conn = getConnection();
    String query,name,email,mobile;
    Statement stmt;
    User user = new User();
    query= "select * from user where id = '"+id+"'";
    stmt = conn.createStatement();
    ResultSet rs=stmt.executeQuery(query);
    if(rs.next())
    { 
        id = rs.getInt("id");
        name=rs.getString("name");
        email=rs.getString("email");
        mobile=rs.getString("mobile");
        user = new User(id,name,email,mobile);
    }
 
    rs.close();
    stmt.close();
    conn.close();
    
    return user;
    }
}
