<%-- 
    Document   : resultSearch
    Created on : Oct 9, 2018, 8:17:56 PM
    Author     : BEST WAY
--%>

<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Results</title>
        <style>
            *{
                margin:0;
                padding: 0;
            }
              body{
            background-color: #4d4d4d;
            }
             span{
                     color: white;
                     font-style: italic;
                     font-size: 100px;
                }
                
            .name{
                margin-top: 20px;
                margin-left: 10px
            }
            .container{
                display: inline-block;
                width: 500px;
                margin: 20px;
                border: 2px solid #4d4d4d;
                background-color: #e8e8e8;
                border-radius: 10px;
                padding: 20px;
                margin-top:120px 
            }
            a{
                color: #555555;
                font-size: 25px;
                text-decoration: none;
            }
            a:hover{
                text-decoration: underline;
            }
                  .menu input[type="submit"],.menu input[type="text"]{
                width: 120px;
                height: 62px; 
            }
             .menu input[type="submit"]{
                background-color: #e8e8e8;
                color: #555555;
                font-style: italic;
                font-size: 30px;
                border: solid 2px #555555;
                border-radius: 20px;
            }
       .menu input[type="submit"]:hover{
             text-decoration: underline;

            }
            .menu{
                margin-top: 5px;
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
            .logout{
                float: right;
                margin: 20px;
                margin-right: 30px
            }
        </style>
    </head>
    <body>
          <div class="name">
            <span> TV STATIONS</span>
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
            String result = (String)request.getAttribute("json");
            JSONObject js=new JSONObject(result);
            JSONArray items = js.getJSONArray("items");
            for (int i = 0; i < items.length(); i++) {
                JSONObject videoObject = items.getJSONObject(i);
                JSONObject videoLink = videoObject.getJSONObject("id");
                JSONObject videoDescription = videoObject.getJSONObject("snippet");
                JSONObject imageTag = videoDescription.getJSONObject("thumbnails");
                JSONObject imageTag1 = imageTag.getJSONObject("default");
                
                
               // String title = videoObject.getString("title");
                String videoId = "https://www.youtube.com/watch?v="+videoLink.getString("videoId");
                String videoTitle = videoDescription.getString("title");
                String imageUrl = imageTag1.getString("url");
                %>
                <div class="container">         
                <a target="_blank" href=<%= videoId %> ><%= videoTitle %></a><br>
                <img src=<%= imageUrl %> ><br>
                </div>
                <%
            }

        %>
    </body>
</html>
