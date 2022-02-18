<%-- 
    Document   : signin
    Author     : Duy Phuong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html data-theme="light">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/main.css">
    </head>
    <body>
        <div class="row center">
            <div class="col-3 container">
                <h1 class="text-center">Sign In!</h1>
                <c:set var="error" value="${requestScope.error}"></c:set>
                <h3 style="color: red">${error}</h3>
                <form class="container" action="signin" method="post">
                    Enter Username:<br/>
                    <input type="text" name="username" required><br/>
                    Enter Password:<br/>
                    <input type="password" name="password" required><br/>
                    <div class="row">
                        <a href="signup">Create an Account</a>
                        <input class="link-btn" type="submit" name="password">
                    </div>
                    
                </form>
            </div>
        </div>
    </body>
</html>
