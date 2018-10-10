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
        <title>JSP Page</title>
    </head>
    <body>
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
                <a href=<%= videoId %> ><%= videoTitle %></a><br>
                <img src=<%= imageUrl %> ><br>

                <%
            }

        %>
    </body>
</html>
