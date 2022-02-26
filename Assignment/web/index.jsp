<%-- 
    Document   : index
    Author     : Duy Phuong
--%>

<%@page import="java.util.List"%>
<%@page import="model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>R'Store Page</title>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link rel="stylesheet" href="css/main.css">
        <link rel="stylesheet" href="libraries/nouislider.css">
        <script src="js/bootstrap/jquery.min.js"></script>
        <script src="js/set-theme.js"></script>
    </head>

    <body>
        <c:import url="navbar.jsp"></c:import>
        <div class="content row">
            <c:import url="sidebar.jsp"></c:import>
            <div class="container col-9">
                <div class="heading">
                    <div class="option-btn">
                        <div class="select-btn">
                            <ul style="width:200px;">
                                <li values="0" class="option">Default</li>
                                <li values="1" class="option">ByName</li>
                                <li values="2" class="option">Price</li>
                                <li values="3" class="option">Newest</li>
                            </ul>
                            <span>Sort By</span>
                        </div>
                    </div>
                    <div class="search-container">
                        <form class="search-form" action="search">
                            <input type="text" class="search-input" name="string" value="${requestScope.string}" placeholder="Search" id="search" autocomplete="off">
                            <div class="search-icon"><i class="fa fa-search" aria-hidden="true"></i></div>
                        </form>
                    </div>
                </div>
            <c:import url="stall.jsp"></c:import>
            </div>
        </div>
        <script src="js/dark-theme.js"></script>
        <script src="js/list-select.js"></script>
    </body>
</html>