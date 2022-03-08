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
        <link rel="stylesheet" href="css/login.css">
        <link rel="stylesheet" href="css/rei-input.css">
        <script src="js/jquery.min.js"></script>
        <script src="js/gallery.js"></script>
    </head>
    <body>
        <c:set var="user" value="${requestScope.user}"></c:set>
        <div class="row center">
            <div class="col-6">
                <a href="home">Home</a>
                <form class="container" action="account" method="post">
                    <h1 class="text-center">Update Account!</h1>
                    <h3 style="color: red">${requestScope.error}</h3>
                    <h3 style="color: green">${requestScope.alert}</h3>
                    <input type="text" name="action" value="update" hidden>
                    <div class="row">
                        <label class="col-ms-12 rei-input-label">
                            <input type="text" name="fullname" value="${user.fullname}" required>
                            <div class="rei-input-name">
                                <span></span>
                                <span class="rei-float-name"><p>Full Name</p></span>
                                <span></span>
                            </div>
                        </label>
                    </div>
                    <div class="row">
                        <label class="col-ms-6 rei-input-label">
                            <input type="text" name="country"  value="${user.country}">
                            <div class="rei-input-name">
                                <span></span>
                                <span class="rei-float-name"><p>Country</p></span>
                                <span></span>
                            </div>
                        </label>
                        <label class="col-ms-6 rei-input-label">
                            <input type="text" name="city"  value="${user.city}">
                            <div class="rei-input-name">
                                <span></span>
                                <span class="rei-float-name"><p>City</p></span>
                                <span></span>
                            </div>
                        </label>
                        <label class="col-ms-12 col-md-8 rei-input-label">
                            <input type="text" name="address"  value="${user.address}">
                            <div class="rei-input-name">
                                <span></span>
                                <span class="rei-float-name"><p>Address</p></span>
                                <span></span>
                            </div>
                        </label>
                        <label class="col-ms-12 col-md-4 rei-input-label">
                            <input type="text" name="phone" value="${user.phone}">
                            <div class="rei-input-name">
                                <span></span>
                                <span class="rei-float-name"><p>Phone</p></span>
                                <span></span>
                            </div>
                        </label>
                    </div>
                    <div class="row jt-spc-btw mrg-top-20 pd-lr-15">
                        <a href="account">Back to Account</a>
                        <input class="link-btn" type="submit" name="password" value="Update">
                    </div>
                </form>
            </div>
        </div>
        <script src="js/rei-input.js"></script>
    </body>
</html>
