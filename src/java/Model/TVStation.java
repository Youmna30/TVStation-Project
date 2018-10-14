/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author BEST WAY
 */
public class TVStation {
    private String name;
    private String url;
    private String country;
    private int id;
    private int userId;


    final Pattern NAME_REGEX=Pattern.compile("^(?:[A-Za-z]+)(?:[A-Za-z0-9 ]*)$", Pattern.CASE_INSENSITIVE);
    final Pattern URL_REGEX=Pattern.compile("http(?:s?):\\/\\/(?:www\\.)?youtu(?:be\\.com\\/watch\\?v=|\\.be\\/)([\\w\\-\\_]*)(&(amp;)?‌​[\\w\\?‌​=]*)?", Pattern.CASE_INSENSITIVE);
    final Pattern COUNTRY_REGEX=Pattern.compile("^[a-zA-Z]+(([a-zA-Z ])?[a-zA-Z]*)*$", Pattern.CASE_INSENSITIVE);
    public TVStation() {
        name="";
        url="";
        country="";
        id=0;
    }
    public TVStation(String name,String url,String country){
    this.name = name;
    this.url = url;
    this.country = country;
    }
    public TVStation(int id,String name,String url,String country){
    this.name = name;
    this.url = url;
    this.country = country;
    this.id = id;
    }

    public int getUserId() {
        return userId;
    }
    public TVStation(int id,String name,String url,String country,int userId){
    this.name = name;
    this.url = url;
    this.country = country;
    this.id = id;
    this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getCountry() {
        return country;
    }

    public int getId() {
        return id;
    }
    
    
    public String checkValidation(TVStation station){
        Matcher matcher;
        String result="";
        matcher = NAME_REGEX.matcher(station.name);
        if(!matcher.find())
        {
            result+="Enter a valid Name <br>";
        }
        matcher = URL_REGEX.matcher(station.url);
        if(!matcher.find())
        {
            result+="Enter a valid Youtube URL <br>";
        }
        matcher = COUNTRY_REGEX.matcher(station.country);
        if(!matcher.find())
        {
            result+= "Enter a valid Country <br>";
        }
        return result;
    }
    public String addStation(TVStation station,String userEmail) throws SQLException{
        DBConnection connection = new DBConnection();
        Statement stmt;
        String query,result="";
        query="select * from station where url = '"+station.url+"'";

        try (Connection conn = connection.getConnection()) {
            stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            if(rs.next())
            {
                if(rs.getString("flag").equals("0"))
                {
                  result = "Wait for confirmation";
                }
                else
                {
                  result = "This station already existed";
                }
            }
            else{
            query="select * from user where email = '"+userEmail+"'";
            ResultSet rs1=stmt.executeQuery(query);
            int userId;
            if(rs1.next())
            {
            userId=rs1.getInt("id");
            query ="insert into station(name,url,userId,country) values('"+station.name+"','"+station.url+"','"+userId+"','"+station.country+"')";

            }
   
            rs.close();
            rs1.close();
            stmt.executeUpdate(query);
            conn.close();
            }
        }

       stmt.close();

        return result;
    }
    public String reject(int id) throws SQLException{
          Connection conn=DBConnection.getConnection();
          String query="delete from station where idstation='"+id+"'";
          Statement stmt=conn.createStatement();
          int validation = stmt.executeUpdate(query);
         if(validation > 0)
         {
             return"You rejected the station,Successfully";             
         }
         else
         {
            return"Something went wrong, the id not found in database";
         }  
    }
    public String accept(int id) throws SQLException{
          Connection conn=DBConnection.getConnection();
         String query="update station set flag='1' where idstation='"+id+"'";
         Statement stmt=conn.createStatement();
         int validation = stmt.executeUpdate(query);
         if(validation > 0)
         {
         return"You added the station,Successfully";
             
         }
         else
         {
         return"Something went wrong, the id not found in database";
         }
    }
    public String getUrl(int id) throws SQLException{
     Connection conn=DBConnection.getConnection();
         String query="select url from station where idstation='"+id+"'";
         String url;
         String result = "";
         Statement stmt = conn.createStatement();
         ResultSet rs=stmt.executeQuery(query);
         if(rs.next())
         {
             url=rs.getString("url");
             String newUrl=url.replace("https://www.youtube.com/watch?v=","https://www.youtube.com/embed/");
             result=newUrl;
         }
   
         return result;
    }
    
}
