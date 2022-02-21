<%-- 
    Document   : addproduct
    Author     : Duy Phuong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Brand"%>
<%@page import="java.util.List"%>
<%@page import="model.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Product</title>
        <link rel="stylesheet" href="css/main.css">
    </head>
    <body>
        <c:set var="product" value="${requestScope.product}"></c:set>
        <div class="row" style="justify-content: center">
            <div class="container col-6">
                <form action="update" method="post" enctype="multipart/form-data">
                    <h3 style="text-transform: uppercase">Update Product</h3>
                    <input type="text" name="target" value="product" hidden>
                    Category: 
                    <select name="categoryID"><c:if test="${requestScope.categoryList == '[]'}"><option name="category">Empty</option></c:if>
                        <c:forEach items="${requestScope.categoryList}" var="c">
                            <option value="${c.categoryID}">${c.categoryName}</option>
                        </c:forEach>
                    </select>
                    Brand: 
                    <select name="brandID">
                        <c:if test="${requestScope.brandList == '[]'}"><option name="brand">Empty</option></c:if>
                        <c:forEach items="${requestScope.brandList}" var="b">
                            <option value="${b.brandID}">${b.brandName}</option>
                        </c:forEach>
                    </select>
                    <br/>
                    Name: <input type="text" name="name" value="${product.productName}"><br/>
                    Price: <input type="number" name="price" value="${product.price}"><br/>
                    Quantity: <input type="number" name="quantity" value="${product.quantity}"><br/>
                    Picture: <img src="images/${product.image}" alt="">
                    Activate Status: 
                        <input type="radio" name="activate" value="1" checked>In Stock<br/>
                        <input type="radio" name="activate" value="0">Out Of Stock<br/>
                    <input type="submit" value="Add Product">
                </form>
            </div>
        </div>
    </body>
</html>