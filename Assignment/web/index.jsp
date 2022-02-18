<%-- 
    Document   : index
    Author     : Duy Phuong
--%>

<%@page import="java.util.List"%>
<%@page import="model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html data-theme="light">

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>R'Store Page</title>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <link rel="stylesheet" href="css/main.css">
</head>

<body>
    <jsp:include page="navbar.jsp"></jsp:include>
    <div class="content row">
        <jsp:include page="sidebar.jsp"></jsp:include>
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
                    <form class="search-form">
                        <input type="text" class="search-input" placeholder="Search">
                        <div class="search-icon"><i class="fa fa-search" aria-hidden="true"></i></div>
                    </form>
                </div>
            </div>
        <jsp:include page="productlist.jsp"></jsp:include>
        </div>
    </div>
    <script src="js/jquery.min.js"></script>
    <script src="js/gallery.js"></script>
</body>
</html>