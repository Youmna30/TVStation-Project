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
public class User {
    private String name;
    private String email;
    private String mobile;
    private String password;
    private int id;

    final Pattern EMAIL_REGEX=Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    final Pattern MOBILE_REGEX=Pattern.compile("^01[0-2]{1}[0-9]{8}", Pattern.CASE_INSENSITIVE);
    final Pattern NAME_REGEX=Pattern.compile("^[a-zA-Z]+(([a-zA-Z ])?[a-zA-Z]*)*$", Pattern.CASE_INSENSITIVE);
    
    private Matcher matcher;
    public User(String name,String email,String mobile,String password){
    this.name=name;
    this.email=email;
    this.mobile=mobile;
    this.password=password;
    }
    public User(int id,String name,String email,String mobile){
    this.id = id;
    this.name=name;
    this.email=email;
    this.mobile=mobile;
    }

    public User() {
     name="";
     email="";
     password="";
     mobile="";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }

    public String getPassword() {
        return password;
    }

    public int getId() {
        return id;
    }
    

    public String checkValidation(User user){
      String result="";
      String checkPassword=user.password;
      checkPassword=checkPassword.replaceAll(" ", "");
      matcher = EMAIL_REGEX.matcher(user.email);
            if(!matcher.find())
            {
                result+="Enter a valid Email <br>";
               
            }
            matcher=MOBILE_REGEX.matcher(user.mobile);
            if(!matcher.find())
            {
               result+="Enter a valid Mobile Number <br>";
            }
            matcher= NAME_REGEX.matcher(user.name);
            if(!matcher.find())
            {
               result+="Enter a valid Name <br>";
            }
            if(checkPassword.length() == 0)
            {
                result+="Your password must contains any characters";

            }
            else if(user.password.length()<6)
            {
              result+="Your password must be at least 6 characters";
            }
            return result;
    }
    public String addUser(User user) throws SQLException
    {
       DBConnection connection = new DBConnection();
       Statement stmt;
       String query;
       String result="";
       query="select * from user where email = '"+user.email+"'";
       try (Connection conn = connection.getConnection()) 
       {
            stmt = conn.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            if(rs.next())
            {
                if(rs.getString("flag").equals("0"))
                {
                  result="Wait for confirmation";
                }
                else
                {
                  result= "You are already a user";
                }
                conn.close();
            }
            else
            {
                query ="insert into user(name,email,mobile,password) values('"+user.name+"','"+user.email+"','"+user.mobile+"','"+user.password+"')";
                stmt = conn.createStatement();
                stmt.executeUpdate(query);
                conn.close();
            }
        }
                
               stmt.close();
               return result;
  
    }
    public String loginValidation(String email,String password) throws SQLException{
         DBConnection connection = new DBConnection();
         Statement stmt;
         String query,result="";
         query="select * from user where email = '"+email+"'";
         try (Connection conn = connection.getConnection()) {
                    stmt = conn.createStatement();
                    ResultSet rs=stmt.executeQuery(query);
                    if(rs.next())
                    {
                        if(rs.getString("flag").equals("0"))
                        {
                          result="Wait for confirmation";
                        }
                        else
                        {
                          if(password.equals(rs.getString("password")))
                          {
                              if(email.equals("Admin"))
                              {
                                  result="Admin";
                              }
                              else
                              {
                                  result="Done";
                              }
                          }
                          else{
                            result="Wrong password";
                          }
                        }
                        conn.close();
                    }
                    else{
                       result="You aren't a User, SignUp please ";

                    }
             }


    return result;
    }
    public String reject(int id) throws SQLException{
      Connection conn=DBConnection.getConnection();
      String query="delete from user where id='"+id+"'";
      Statement stmt=conn.createStatement();
      int validation = stmt.executeUpdate(query);
      if(validation > 0)
      {
          return"Your deleted the user, successfully";
      }
      else
      {
          return"Something went wrong, the user doesn't exist in database";

      }
    }
    public String accept(int id) throws SQLException{
         Connection conn=DBConnection.getConnection();
         String query="update user set flag='1' where id='"+id+"'";
         Statement stmt=conn.createStatement();
         int validation = stmt.executeUpdate(query);
         if(validation > 0)
         {
                   return"You accepted the user,Successfully";

         }
         else{
                  return"Something went wrong, the user doesn't exist in database";

         }
    }
}

        