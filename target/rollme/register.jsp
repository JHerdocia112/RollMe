<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<body>
<h2>Roll Me</h2>
<c:if test = "${message != null}">
    <p>${message}</p>
</c:if>
<p> 
    <form action = "register" method = "post">
        <label for="username">Username:</label><br>
        <input type="text" id="username" name="username"><br>
        <label for="email">Email:</label><br>
        <input type="email" id="email" name="email"><br>
        <label for="confirmemail">Confirm Email:</label><br>
        <input type="email" id="confirmemail" name="confirmemail"><br>
        <label for="password">Password:</label><br>
        <input type="password" id="password" name="password"><br>
        <label for="confirmpassword">Confirm Password:</label><br>
        <input type="password" id="confirmpassword" name="confirmpassword"><br>
        <input type="submit" value="Submit">
    </form>
</p>
</body>
</html>