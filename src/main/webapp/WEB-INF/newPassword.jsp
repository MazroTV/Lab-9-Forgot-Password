<%-- 
    Document   : newPassword
    Created on : Apr 11, 2022, 1:07:02 PM
    Author     : Marek PC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Creating New Password.</title>
    </head>
    <body>
        <h1>Enter a new password:</h1>
        <form action="reset" method ="post">
        <input type="password" name="password" id ="password">
        <button type="submit">Change Password</button>
        </form>
        <p>${message}</p>
        <a href="login">Login</a>
    </body>
</html>
