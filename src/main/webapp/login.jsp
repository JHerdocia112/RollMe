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
    <form action = "login" method = "post">
        <label for="username">Username:</label><br>
        <input type="text" id="username" name="username"><br>
        <label for="password">Password:</label><br>
        <input type="password" id="password" name="password"><br>
        <input type="submit" value="Submit">
    </form>
</p>
</body>
</html>