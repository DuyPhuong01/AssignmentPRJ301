<%-- 
    Document   : addproduct
    Created on : Jan 25, 2022, 8:35:13 PM
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
        <div class="row" style="justify-content: center">
        <div class="container col-6">
        <form action="addproduct" method="post">
            <h3 style="text-transform: uppercase">Add Product</h3>    
            Category: 
            <select name="categoryID">
                <c:if test="${requestScope.categoryList == '[]'}"><option name="category">Empty</option></c:if>
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
            Name: <input type="text" name="name"><br/>
            Price: <input type="number" name="price"><br/>
            Picture: <input type="file" name="productPhoto"><br/>
            Activate Status: 
                <input type="radio" name="activate" value="in_stock" checked>In Stock<br/>
                <input type="radio" name="activate" value="out_of_stock">Out Of Stock<br/>
                <input type="radio" name="activate" value="running_low">Running Low<br/>
            <input type="submit" value="Add Product">
            </table>
        </form></div></div>
    </body>
</html>
