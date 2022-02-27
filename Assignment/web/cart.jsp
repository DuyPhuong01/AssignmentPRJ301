<%-- 
    Document   : cart
    Author     : Duy Phuong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Cart</title>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link rel="stylesheet" href="css/main.css">
        <link rel="stylesheet" href="css/admin.css">
        <script src="js/jquery.min.js"></script>
        <script src="js/set-theme.js"></script>
    </head>
    <body>
        <c:import url="navbar.jsp"></c:import>
        <div class="row" style="justify-content: center">
            <div class="col-10">
                <a href="home">Home</a><br/>
                <div class="container">
                    <c:forEach var="item" items="${requestScope.list}">
                        <div>
                            ${item.getProduct().productID}
                            ${item.getProduct().productName}
                            ${item.getProduct().price}
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </body>
</html>
