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
                   document.getElementById("msg").innerHTML='<iframe width="420" height="345" src="'+x+'"> </iframe>';
                }

                }; 
            }
                   </script>
                   <style>
                       
                   </style>
    </head>
    <body>
         <%
            String email=request.getParameter("email");
            session.setAttribute("UName", email);
         %>
        <a href="station.html">Add TV Station</a>
        <form action="search">
            <input type="text" placeholder="Search" name="search">
            <input type="submit" value="Search">
        </form>
      
        <form action="logout">
             <input type="submit" value="Logout"/>
                </form>
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
          <table>
                         <tr>
                             <th>TV Stations</th>
                         </tr>
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
               <tr>
                   <td id="<%= id %>">
                         <span>Name: <% out.print(name);%></span><br>
                         <span>Country: <% out.print(country);%></span><br>
                         <button onclick="watch(<%= id %>)"> Watch </button>
                         <div id="msg"></div>
                   </td>
               </tr>
              <%
          }
           %>
           </table>
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
