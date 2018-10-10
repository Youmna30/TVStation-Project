<%-- 
    Document   : HomeAdmin
    Created on : Oct 3, 2018, 11:14:22 PM
    Author     : BEST WAY
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="Database.DBConnection"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home</title>
        <script>
           
            function accept(id){
                //alert(id);
                var xmlhttp=new XMLHttpRequest(); 
                xmlhttp.open("POST","accept",true);
                xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                xmlhttp.send("id="+id);
                xmlhttp.onreadystatechange=function(){
                if(this.readyState===4&&this.status===200){
                    document.getElementById(id).innerHTML=xmlhttp.responseText;
                }
            };
            }
                 function acceptStation(id){
                //alert(id);
                var xmlhttp=new XMLHttpRequest(); 
                xmlhttp.open("POST","acceptStation",true);
                xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                xmlhttp.send("id="+id);
                xmlhttp.onreadystatechange=function(){
                if(this.readyState===4&&this.status===200){
                    document.getElementById(id).innerHTML=xmlhttp.responseText;
                }
            };
            }
            function reject(id)
            {
                var xmlhttp=new XMLHttpRequest(); 
                xmlhttp.open("POST","reject",true);
                xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                xmlhttp.send("id="+id);
                xmlhttp.onreadystatechange=function(){
                if(this.readyState===4&&this.status===200){
                    document.getElementById(id).innerHTML=xmlhttp.responseText;
                }
            };
            }
            function rejectStation(id)
            {
                var xmlhttp=new XMLHttpRequest(); 
                xmlhttp.open("POST","rejectStation",true);
                xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                xmlhttp.send("id="+id);
                xmlhttp.onreadystatechange=function(){
                if(this.readyState===4&&this.status===200){
                    document.getElementById(id).innerHTML=xmlhttp.responseText;
                }
            };
            }            
        </script>
        <style>
            body{
                background-color: #cccfd2;
            }
            .text{
                margin: 12%;
                font-size: 30px;
            }
            .header{
                     color: #555555;
                     font-style: italic;
                     font-size: 100px;
                }
                
            .name{
                float: left;
                margin-top: 20px;
                margin-left: 10px;
                
            }
            .form{
                float: right;
                margin: 20px
            }
            .form,.name{
                display: inline
            }
            table{
                 border-collapse: collapse;
                 background-color: white;
                 float:left;
                 display: inline-block;
                 margin: 20px;
                 margin-right: 100px;
                 width:500px;
                 margin-top: 200px

            }
            tr,td{
               border: 2px solid black; 

            }
            th,tr,td{
                    padding: 15px;
                    width: 500px;

            }
            th,td{
                color:#555555;
                font-size: 25px
            }
            input[type="submit"]
            {
                background-color: #cccfd2;
                color: #555555;
                font-style: italic;
                font-size: 30px;
                width: 120px;
                height: 60px;
                border: solid 2px #555555;
                border-radius: 20px;
            }
            input[type="submit"]:hover{
             text-decoration: underline;

            }
            button{
                    width: 25%;
                    padding: 2%;
                    margin: 4%;
                    font-size: 25px;
                    background-color: #4d4d4d;
                    color: white;
                    border:none;
            }
            .reject{
                float:right
            }
           
        </style>
    </head>
    <body>
      <div class="head">
        <div class="name">
            <span class="header"> TV STATIONS</span>
        </div>
        <div class="form">
            <form action="logout">
             <input type="submit" value="Logout"/>
                </form>
        </div>
      </div>
        
        <%
          DBConnection connection = new DBConnection();
          Connection conn = connection.getConnection();
          String query, name,email,mobile,url,country;
          int id,idUser;
          Statement stmt,stmt1;
          query= "select * from user where flag = '0'";
          stmt = conn.createStatement();
          stmt1 = conn.createStatement();
          ResultSet rs=stmt.executeQuery(query);
          boolean empty=true;
          %>
                     <table>
                         <tr>
                             <th>Users</th>
                         </tr>
<%
          while(rs.next()){
              empty=false;
              id=rs.getInt("id");
              name=rs.getString("name");
              email=rs.getString("email");
              mobile=rs.getString("mobile");
           %>
               <tr>
                   <td id="<%= id %>">
                         <span class="text">Name: <% out.print(name);%></span><br>
                         <span class="text">Email: <% out.print(email);%></span><br>
                         <span class="text">Mobile: <% out.print(mobile);%></span><br><br>
                         <button class="accept" onclick="accept(<%= id %>)">Accept</button>
                         <button class="reject" onclick="reject(<%= id%>)">Reject</button><br>
                   </td>
               </tr>
              <%
          }
           %>
           <%
          if(empty)
          {%>
          <tr>
              <td>
                  <span class="text">
                  No new Users
                  </span>
              </td>
          </tr>
           </table>

          <% 
               rs.close();
               stmt.close();
           }
          
          
          %>
          <table>
              <tr>  
                 <th>Stations</th>
              </tr>
          <%
          empty=true;
          query= "select * from station where flag = '0'";
          ResultSet rs2=stmt1.executeQuery(query);
          while(rs2.next()){
              empty=false;
              id=rs2.getInt("idstation");
              name=rs2.getString("name");
              url=rs2.getString("url");
              country=rs2.getString("country");
              idUser=rs2.getInt("userId");
              ResultSet rs1=stmt.executeQuery("select email from user where id = '"+idUser+"'");
              if(rs1.next())
              {
              email=rs1.getString("email");
              }
              else{
              email="None";
              }
           %>
               <tr>
                   <td id="<%= id %>">
                         <span class="text">Name: <% out.print(name);%></span><br>
                         <span class="text">URL: <% out.print(url);%></span><br>
                         <span class="text">Country: <% out.print(country);%></span><br>
                         <span>Added By: <% out.print(email);%></span><br><br>
                         <button class="accept" onclick="acceptStation(<%= id %>)">Accept</button>
                         <button class="reject" onclick="rejectStation(<%= id%>)">Reject</button><br>
                   </td>
               </tr>
              <%
          }
           %>
           <%
          rs2.close();
          stmt1.close();
          if(empty)
          {%>
          <tr>
              <td>
                  <span class="text">No new Stations</span>
              </td>
          </tr>
                     </table>

           <% 
               
           }
       
           
           %>
    </body>
</html>
