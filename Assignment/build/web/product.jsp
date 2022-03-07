<%-- 
    Document   : product
    Author     : Duy Phuong
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Product</title>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link rel="stylesheet" href="css/main.css">
        <link rel="stylesheet" href="css/menubar.css">
        <script src="js/bootstrap/jquery.min.js"></script>
        <script src="js/set-theme.js"></script>
    </head>
    <body>
        <jsp:include page="element/navbar.jsp"></jsp:include>
        <c:set var="product" value="${requestScope.product}"></c:set>
        <div class="content row" style="justify-content: center">
            <div class="container col-9 row product">
                <div class="col-6">
                    <img src="images/${product.image}">
                </div>
                <div class="col-6">
                    <p>${product.productName}</p>
                    <p>${product.brandID}</p>
                    <p>${product.price}</p>
                    <div class="add-btn">
                        <a class="link-btn" href="addtocart?productID=${product.productID}">Add to Cart</a>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html> 
