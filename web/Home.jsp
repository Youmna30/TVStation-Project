<%-- 
    Document   : Home
    Created on : Oct 3, 2018, 3:08:45 AM
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
            function watch(id){
                console.log(id);
                var xmlhttp=new XMLHttpRequest(); 
                xmlhttp.open("POST","view",true);
                xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
                xmlhttp.send("id="+id);
                xmlhttp.onreadystatechange=function(){
                if(this.readyState===4&&this.status===200){
                   // document.getElementById(id).innerHTML=xmlhttp.responseText;
                   var x = xmlhttp.responseText;
                   console.log(x);
                   document.getElementById(id).innerHTML='<iframe width="420" height="345" src="'+x+'" allowfullscreen="allowfullscreen"> </iframe>';
                }

                }; 
            }
        </script>
        <style>
            *{
                margin: 0;
                padding: 0;
            }
        body{
            background-color: #4d4d4d;
            }
       .header{
            color: white;
            font-style: italic;
            font-size: 100px;
              }
                
       .name{
            float: left;
            margin-top: 20px;
            margin-left: 10px;
            position: absolute;
                
            }
     .logout{
                float: right;
                margin: 20px;
                margin-right: 30px
            }
           table{
                 border-collapse: collapse;
                 background-color: white;
                 display: inline-block;
                 width:500px;
                 margin:20px

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
        input[type="submit"],input[type="text"]{
            width: 120px;
            height: 62px; 
        }
         input[type="submit"]{
            background-color: #e8e8e8;
            color: #555555;
            font-style: italic;
            font-size: 30px;
            border: solid 2px #555555;
            border-radius: 20px;
        }
    input[type="submit"]:hover{
         text-decoration: underline;

        }
        .menu{
            margin-top: 130px;
            position: absolute;
            background-color: #555555;
            width: 100%;
            height: 100px

        }
        .search,.station,.logout{
            display: inline-block
        }
        .station input[type="submit"]{
            width: 220px;
            margin: 20px;
            margin-right: 620px;
            margin-left: 30px

        }
        .station{
            float: left
        }
        .search input[type="text"]{
            width: 500px;
            height: 30px;
            padding: 10px;
            font-size: 30px;
        }
        .search{
            margin-top: 20px
        }
     button{
                    width: 25%;
                    padding: 2%;
                    margin: 3%;
                    font-size: 20px;
                    background-color: #4d4d4d;
                    color: white;
                    border:none;
                    margin-left: 37%;
            }
            .container{
                margin-top: 250px;
                position: absolute
            }
            
            
     
        </style>
    </head>
    <body>
         <%
            String email=request.getParameter("email");
            session.setAttribute("UName", email);
         %>
          <div class="name">
            <span class="header"> TV STATIONS</span>
          </div>

        <div class="menu">
        <form class="station" action="station.html">
            <input type="submit" value="Add TV Station" />
        </form>
        <form class="search" action="search">
            <input type="text" placeholder="Search" name="search">
            <input type="submit" value="Search">
        </form>
        <form class="logout" action="logout">
             <input type="submit" value="Logout"/>
        </form>
        </div>
        <%
          DBConnection connection = new DBConnection();
          Connection conn = connection.getConnection();
          String query,name,url,country;
          int id,userId;
          Statement stmt;
          query= "select * from station where flag = '1'";
          stmt = conn.createStatement();
          ResultSet rs=stmt.executeQuery(query);
          %>
          <div class="container">
          <%
          
          boolean empty=true;
          while(rs.next())
          {
              empty=false;
              id=rs.getInt("idstation");
              name=rs.getString("name");
              url=rs.getString("url");
              country=rs.getString("country");
              %>
               <table>
                         <tr>
                             <th><% out.print(name);%></th>
                         </tr>
               <tr>
                   <td>
                         <span>Country: <% out.print(country);%></span><br>
                         <button onclick="watch(<%= id %>)"> Watch </button>
                         <div id=<%= id %>></div>
                   </td>
               </tr>
               </table>

              <%
          }
           %>
           </div>
           <%
          if(empty)
          {
           %>
          <span>No Added TV Stations</span>
           <% 
               rs.close();
               stmt.close();
           }
          
          
          %>

          
          
            
    </body>
</html>
