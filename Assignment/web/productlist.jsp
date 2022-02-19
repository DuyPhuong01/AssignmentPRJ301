<%-- 
    Document   : productlist
    Author     : Duy Phuong
--%>
 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="product-list row">
    <c:forEach var="product" items="${requestScope.productList}">
        <div class="product col-3">
            <a href="product?action=details&productID=${product.productID}"><img src="images/${product.image}" alt=""></a>
            <p class="product-name">${product.productName}</p>
            <div>
                <p><b class="price">$${product.price}</b></p>
            </div>
            <div class="add-btn">
                <a class="link-button" href="">Add to Cart</a>
            </div>
            <c:if test="${sessionScope.userAccount!=null}">
                <c:if test="${sessionScope.userAccount.role == 1}">
                    <div class="delete-btn">
                        <i onclick="deleteProduct('${product.productID}')" class="fa fa-trash delete-btn" aria-hidden="true"></i>
                    </div>
                </c:if>
            </c:if> 
        </div>
    </c:forEach>
</div>