<%-- 
    Document   : account-details
    Created on : Feb 21, 2022, 7:55:31 PM
    Author     : Duy Phuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Account</title>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link rel="stylesheet" href="css/main.css">
        <script src="js/jquery.min.js"></script>
        <script src="js/set-theme.js"></script>
    </head>
    <body>
        <div class="row mrg-lr-0 center">
            <div class="col-ms-12 col-lg-8">
                <a href="home">Home</a>
                <label>
                    <input id="theme-checkbox" type="checkbox" name="theme">
                    <span class="btn"></span>
                    <i class="fa fa-moon-o" aria-hidden="true"></i>
                </label>
                <div class="container row">
                        <div class="admin-menu col-2">
                            <h1>My Account</h1>
                            <a href="account?action=details">Information</a><br/>
                            <a href="account?action=setting">Setting</a><br/>
                        </div>
                        <div class="col-10">
                            <jsp:include page="${requestScope.page}.jsp"></jsp:include>
                        </div>
                </div>
            </div>
        </div>
    </body>
    <script src="js/dark-theme.js"></script>
</html>
