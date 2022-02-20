<%-- 
    Document   : product-manager
    Created on : Feb 20, 2022, 4:11:47 AM
    Author     : Duy Phuong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1>product manager</h1>
<table>
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Description</td>
        <td>Activate</td>
        <td>Action</td>
    </tr>
    <c:forEach var="category" items="${requestScope.categoryList}">
        <tr>
            <td>${category.categoryID}</td>
            <td>${category.categoryName}</td>
            <td>${category.description}</td>
            <td>${category.activate}</td>
            <td>
                <i onclick="deleteProduct('${product.productID}')" class="fa fa-trash" aria-hidden="true"></i>&nbsp;
                <a href="update"><i class="fa fa-pencil" aria-hidden="true"></i></a>
            </td>
        </tr>
    </c:forEach>
</table>