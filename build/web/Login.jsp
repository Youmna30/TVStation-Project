<%-- 
    Document   : login.jsp
    Created on : Oct 3, 2018, 2:03:23 AM
    Author     : BEST WAY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>Login</title>
         <script type="text/javascript">
           function sendreq()
            {              
            var email=document.getElementById("email").value;
            var password=document.getElementById("password").value;
            if(email=="" || password=="")
            {
                 document.getElementById("show_div").innerHTML="Please, fill out all the fields";

            }
            else
            {
            var xmlhttp=new XMLHttpRequest(); 
            xmlhttp.open("POST","checkLogin",true);
            xmlhttp.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
            xmlhttp.send("&email="+ email+"&password="+password);
            xmlhttp.onreadystatechange=function(){
                if(this.readyState===4&&this.status===200){
                    var x=xmlhttp.responseText;
                    if(x=="Done")
                    {
                        document.location.href=("Home.jsp?email="+email);
                    }
                    else if(x=="Admin"){
                        document.location.href=("HomeAdmin.jsp");

                    }
                    else
                    {
                    document.getElementById("show_div").innerHTML=x;
                     }
                 }
            };    
            }
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
                span{
                     color: white;
                     font-style: italic;
                     font-size: 100px;
                }
                
            .name{
                margin-top: 20px;
                margin-left: 10px
            }
              h1{
                    color: black;
                    margin-left: 36.5%;
                    margin-top:10%;
                    margin-bottom: 10%;
                    font-weight:bold
              }
                .form{
                    width:20%;
                    height: 75%;
                    padding: 1.5%;
                    margin-left: 35%;
                    margin-top: 7%;
                    background-color:#b3b3b3;
                    border-radius: 10px;
                }
                label,#show_div{
                    color: black;
                    font-size:30px;
                    margin: 4%
                    
                }
                input{
                    margin: 4%;
                    width: 80%;
                    padding: 2%;
                    border-radius: 5px;
                    font-size: 20px
                    
                }
                input[type="submit"]
                {
                    width: 30%;
                    padding: 2%;
                    font-size: 25px;
                    background-color: #4d4d4d;
                    color: white;
                    border:none;
                    margin-left: 35%

                }
            </style>
    </head>
    <body>
         <div class="name">
            <span> TV STATIONS</span>
        </div>
        <div class="form">
            <h1>Welcome</h1>
        <label>Email</label><br>
        <input type="text" name="email" id="email" required placeholder="Enter Your Email"><br>
        <label>Password</label><br>
        <input type="password" name="passowrd" id="password" required placeholder="Enter Your password"><br>
        <input type="submit" onclick="sendreq()" value="Login"><br> 
        <div id="show_div"></div>        
        </div>
    </body>
</html>
