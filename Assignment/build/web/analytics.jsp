<%-- 
    Document   : analytics
    Author     : Duy Phuong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Analytics</title>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link rel="stylesheet" href="css/main.css">
        <script src="js/jquery.min.js"></script>
        <script src="js/set-theme.js"></script>
        <style>
            #chartdiv.revenue {
                width: 100%;
                height: 40%;
            }
            #chartdiv.mostfavorite,
            #chartdiv.bestseller{
                width: 100%;
                height: 120%;
                max-width: 100%;
            }

        </style>
    </head>
    <body>
        <div class="admin row center">
            <div class="col-10">
                <a href="home">Home</a>
                <label>
                    <input id="theme-checkbox" type="checkbox" name="theme">
                    <span class="btn"></span>
                    <i class="fa fa-moon-o" aria-hidden="true"></i>
                </label>
                <div class="container row">
                    <div class="admin-menu col-2">
                        <h1>Analytics Page</h1>
                        <a href="analytics?action=revenue">Revenue</a><br/>
                        <a href="analytics?action=bestseller">Best Seller</a><br/>
                        <a href="analytics?action=mostfavorite">Most Favorite</a><br/>
                    </div>
                    <div class="admin-content col-10 pd-lr-0">
                        <div id="chartdiv" class="${requestScope.page}"></div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script src="js/live-search.js"></script>
    <script src="js/dark-theme.js"></script>
    <script src="https://cdn.amcharts.com/lib/5/index.js"></script>
    <script src="https://cdn.amcharts.com/lib/5/xy.js"></script>
    <script src="https://cdn.amcharts.com/lib/5/themes/Animated.js"></script>
    <c:import url="analytics_${requestScope.page}.jsp"></c:import>
</html>
