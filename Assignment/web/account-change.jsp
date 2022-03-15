<%-- 
    Document   : signin
    Author     : Duy Phuong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html data-theme="light">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Account Update</title>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link rel="stylesheet" href="css/main.css">
        <script src="js/bootstrap/jquery.min.js"></script>
        <script src="js/set-theme.js"></script>

    </head>
    <body>
        <c:set var="user" value="${requestScope.user}"></c:set>
        <div class="row center">
            <div class="col-4">
                <a href="home">Home</a>
                <label>
                    <input id="theme-checkbox" type="checkbox" name="theme">
                    <span class="btn"></span>
                    <i class="fa fa-moon-o" aria-hidden="true"></i>
                </label>
                <form class="container" action="account" method="post">
                    <h1 class="text-center">Update for ${user.username}!</h1>
                    <h3 style="color: red">${requestScope.error}</h3>
                    <h3 style="color: green">${requestScope.alert}</h3>
                    <input type="text" name="action" value="changeusernameandpassword" hidden>
                    <div class="row">
                        <label class="col-ms-12 rei-input-label">
                            <input type="text" name="username" value="${user.username}" required>
                            <div class="rei-input-name">
                                <span></span>
                                <span class="rei-float-name"><p>Username</p></span>
                                <span></span>
                            </div>
                        </label>
                    </div>
                    <div class="row">
                        <label class="col-ms-12 col-md-6 rei-input-label">
                            <input id="password" type="password" name="password" value="${user.password}" required>
                            <div class="rei-input-name">
                                <span></span>
                                <span class="rei-float-name"><p>Password</p></span>
                                <span></span>
                            </div>
                        </label>
                        <label class="col-ms-12 col-md-6 rei-input-label">
                            <input id="confirm_password" type="password" name="confirm-password" value="${user.password}" required>
                            <div class="rei-input-name">
                                <span></span>
                                <span class="rei-float-name"><p>Confirm Password</p></span>
                                <span></span>
                            </div>
                        </label>
                    </div>
                    <p id="message"></p>
                    <div class="row jt-spc-btw mrg-top-20 pd-lr-15">
                        <a href="account">Back to Account</a>
                        <input id="submit" class="link-btn" type="submit" name="password" value="Update">
                    </div>
                </form>
            </div>
        </div>
        <script src="js/rei-input.js"></script>
        <script src="js/dark-theme.js"></script>
    </body>
    <script>
        $('#password, #confirm_password').on('keyup', function () {
            if ($('#password').val() == '') {
                $('#message').html('');
                $('#submit')[0].disabled = true;
            } else if ($('#password').val() == $('#confirm_password').val()) {
                $('#message').html('Matching').css('color', 'green');
                $('#submit')[0].disabled = false;
            } else {
                $('#message').html('Not Matching').css('color', 'red');
                $('#submit')[0].disabled = true;
            }
        });
    </script>
</html>
