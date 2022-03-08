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
        <title>Sign In</title>
        <link rel="stylesheet" href="css/main.css">
        <link rel="stylesheet" href="css/login.css">
        <link rel="stylesheet" href="css/rei-input.css">
        <script src="js/bootstrap/jquery.min.js"></script>
        <script src="js/set-theme.js"></script>
    </head>
    <body>
        <div class="pd-lr-15">
            <div class="row center signin-container">
                <div class="col-ms-12 col-md-8 col-lg-4">
                    <a href="home">Home</a>
                    <div class="container">
                        <h1 class="text-center">Sign In!</h1>
                        <h3 style="color: red">${requestScope.error}</h3>
                        <h3 style="color: green">${requestScope.alert}</h3>
                        <form action="signin" method="post">
                            <input type="text" name="action" value="signin" hidden>
                            <label class="rei-input-label">
                                <input type="text" name="username" required>
                                <div class="rei-input-name">
                                    <span></span>
                                    <span class="rei-float-name"><p>Username</p></span>
                                    <span></span>
                                </div>
                            </label>
                            <label class="rei-input-label">
                                <input type="password" name="password" required>
                                <div class="rei-input-name">
                                    <span></span>
                                    <span class="rei-float-name"><p>Password</p></span>
                                    <span></span>
                                </div>
                            </label>
                            <div class="row jt-spc-btw mrg-top-20 pd-lr-15">
                                <a href="signup">Create an Account</a>
                                <input class="link-btn" type="submit">
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script src="js/rei-input.js"></script>
    </body>
</html>
