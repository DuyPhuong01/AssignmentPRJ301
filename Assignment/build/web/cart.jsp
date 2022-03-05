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
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>My Cart</title>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link rel="stylesheet" href="css/main.css">
        <link rel="stylesheet" href="css/admin.css">
        <script src="js/jquery.min.js"></script>
        <script src="js/set-theme.js"></script>
        <style>
            .item img {
                width:100px;
            }
            td{
                text-align: center;
            }
            tr:hover {
                border-radius: 8px;
                box-shadow: 0.5px 0.5px 5px gray;
            }
            tr:first-child {
                box-shadow: none;
            }
        </style>
    </head>
    <body>
        <c:import url="navbar.jsp"></c:import>
        <div class="row" style="justify-content: center">
            <div class="col-10">
                <a href="home">Home</a><br/>
                <div class="container">
                    <table>
                        <tr>
                            <th>ProductID</th>
                            <th>Product Name</th>
                            <th>Product Image</th>
                            <th>Price</th>
                            <th>Quantity</th>
                        </tr>
                        <c:forEach var="item" items="${requestScope.list}">
                            <tr class="item">
                                <td>${item.getProduct().productID}</td>
                                <td>${item.getProduct().productName}</td>
                                <td><img src="images/${item.getProduct().image}"></td>
                                <td>${item.getProduct().price}</td>
                                <td>${item.quantity}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
