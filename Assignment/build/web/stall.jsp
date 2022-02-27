<%-- 
    Document   : productlist
    Author     : Duy Phuong
--%>
 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="product-list-1" class="product-list row">
    <c:forEach var="product" items="${requestScope.productList}">
        <div class="product col-3">
            <a href="product?action=details&productID=${product.productID}"><img src="images/${product.image}" alt=""></a>
            <p class="product-name">${product.productName}</p>
            <div>
                <p><b class="price">$${product.price}</b></p>
            </div>
            <div class="add-btn">
                <a class="link-button" href="addtocart?productID=${product.productID}">Add to Cart</a>
            </div>
        </div>
    </c:forEach>
</div>