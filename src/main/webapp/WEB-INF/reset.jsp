<%-- 
    Document   : reset
    Created on : Apr 11, 2022, 11:52:26 AM
    Author     : Marek PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Password Reset</title>
    </head>
    <body>
        <h1>Reset Password</h1>
        <form action="login" method="post">
            <p>Please Enter you Email Address</p><br>
            
            <label for="email">Email Address: &nbsp; </label>
            <input type="email" name="email" id ="email">
            
            <input type ="submit" value ="Submit">
            
        </form> 
        
       <p> ${message} </p>
      <a href="login">Login</a>
    </body>
</html>
