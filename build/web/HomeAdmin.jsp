<%-- 
    Document   : HomeAdmin
    Created on : Oct 3, 2018, 11:14:22 PM
    Author     : BEST WAY
--%>

<%@page import="Model.TVStation"%>
<%@page import="Model.TVStation"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Model.User"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="Model.DBConnection"%>

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
            *{
                margin: 0;
                padding:0;
            }
            body{
                background-color: #4d4d4d;
            }
            .text{
                margin: 12%;
                font-size: 30px;
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
                
            }
            .logout{
                float: right;
                margin: 20px;
                margin-top: 40px
            }
            .logout,.name{
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
                background-color: #e8e8e8;
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
        <div class="name">
            <span class="header"> TV STATIONS</span>
        </div>
        <div class="logout">
            <form action="logout">
             <input type="submit" value="Logout"/>
                </form>
        </div>
        
        <%
          DBConnection connection = new DBConnection();
          ArrayList<User> allUsers = new ArrayList<>();
          allUsers=connection.getAllUnAcceptedUsers();
          %>
                          <table>

                    <tr>
                        <th> Users </th>
                    </tr>
          <%
          if(allUsers.size() == 0){
              %>
              <tr>
              <td>
                  <span class="text">
                  No new Users
                  </span>
              </td>
          </tr>
              <%
          
          }
          else{
               for(int i=0;i<allUsers.size();i++)
                {
      %>
                 <tr>
                     <td id="<%= allUsers.get(i).getId() %>">
                         <span class="text">Name: <% out.print(allUsers.get(i).getName());%></span><br>
                         <span class="text">Email: <% out.print(allUsers.get(i).getEmail());%></span><br>
                         <span class="text">Mobile: <% out.print(allUsers.get(i).getMobile());%></span><br><br>
                         <button class="accept" onclick="accept(<%= allUsers.get(i).getId()%>)">Accept</button>
                         <button class="reject" onclick="reject(<%= allUsers.get(i).getId()%>)">Reject</button><br>
                   </td>
               </tr>
             <%
                }
              }
              %>
              </table>
              <table>
              <tr>  
                 <th>Stations</th>
              </tr>
          <%
          ArrayList<TVStation> allStations = new ArrayList<>();
          allStations=connection.getAllStation(0);
          if(allStations.size() == 0){
            %>
              <tr>
              <td>
                  <span class="text">No new Stations</span>
              </td>
          </tr>
          <%
          }
         else{
              for(int i =0;i<allStations.size();i++)
               {
                User user = new User();
                user=connection.getUserById(allStations.get(i).getUserId());
                %>
                 <tr>
                     <td id="<%= allStations.get(i).getId() %>">
                         <span class="text">Name: <% out.print(allStations.get(i).getName());%></span><br>
                         <span class="text">URL: <% out.print(allStations.get(i).getUrl());%></span><br>
                         <span class="text">Country: <% out.print(allStations.get(i).getCountry());%></span><br>
                         <span>Added By: <% out.print(user.getEmail());%></span><br><br>
                         <button class="accept" onclick="acceptStation(<%= allStations.get(i).getId() %>)">Accept</button>
                         <button class="reject" onclick="rejectStation(<%= allStations.get(i).getId() %>)">Reject</button><br>
                   </td>
               </tr>

                <%
                 }
                 }
               %>
        
                     </table>

            
               
       
         </body>
</html>
