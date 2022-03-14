<%-- 
    Document   : product-manager
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1>Product manager</h1>
<div style="align-items: center" class="jt-spc-btw">
    <a href="product?action=create">Create product</a>
    <input type="text" id="search" class="search-input" name="searchkey" placeholder="Search" autocomplete="off">
</div>
<table>
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Image</td>
        <td>Price</td>
        <td>Quantity</td>
        <td>Status</td>
        <td>Action</td>
    </tr>
    <c:forEach var="product" items="${requestScope.productList}">
        <tr class="productlist">
            <td>${product.productID}</td>
            <td class="product-name">${product.productName}</td>
            <td><img style="width: 50px" src="images/${product.image}"></td>
            <td>$${product.price}</td>
            <td>${product.quantity}</td>
            <td><i style="color: ${product.status==0 ? "red" : "green"}" class="fa fa-circle" aria-hidden="true"></i></td>
            <td>
                <i onclick="deleteProduct('${product.productID}')" class="fa fa-trash" aria-hidden="true"></i>&nbsp;
                <a href="product?action=update&id=${product.productID}"><i class="fa fa-pencil" aria-hidden="true"></i></a>
            </td>
        </tr>
    </c:forEach>
</table>
<script src="js/live-search.js"></script>
<script>
    function deleteProduct(id){
        if(confirm("Are you sure to delete Product with id = "+id+"?")) window.location="product?action=delete&id="+id;
    }
</script>