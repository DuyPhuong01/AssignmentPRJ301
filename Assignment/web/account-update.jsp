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
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>JSP Page</title>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link rel="stylesheet" href="css/main.css">
        <script src="js/jquery.min.js"></script>
        <script src="js/gallery.js"></script>
    </head>
    <body>
        <c:set var="account" value="${requestScope.account}"></c:set>
        <div class="row center">
            <div class="col-6 container">
                <h1 class="text-center">Update Account!</h1>
                <form class="container" action="account" method="post">
                <input type="text" name="action" value="update" hidden><br/>
                    <div class="row">
                        <div class="col-6">
                            First Name<br/>
                            <input type="text" name="firstname" value="${account.username}" required><br/>
                        </div>
                        <div class="col-6">
                            Last Name<br/>
                            <input type="text" name="lastname" required><br/>
                        </div>
                    </div>
                    Username:<br/>
                    <input type="text" name="username" required><br/>
                    Password: <input type="password" name="password" required><br/>
                    Confirm Password: <input type="password" name="confirm-password" required><br/>
                    <div class="row">
                        <a href="main">Home</a>
                        <input class="link-btn" type="submit" name="password">
                    </div>
                    
                </form>
            </div>
        </div>
    </body>
</html>
