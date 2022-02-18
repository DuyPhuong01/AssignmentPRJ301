<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="product-list row">
    <c:forEach var="product" items="${requestScope.productList}">
        <div class="product col-3">
            <a href="product?action=details&productID=${product.productID}"><img src="images/product-${product.productID}.jpg" alt=""></a>
            <p class="product-name">${product.productName}</p>
          <!--  <div class="color">
                <svg width="30" height="20"><rect width="30" height="20" style="fill:rgb(0,0,0);" /></svg>
                <svg width="30" height="20"><rect width="30" height="20" style="fill:red;" /></svg>
                <svg width="30" height="20"><rect width="30" height="20" style="fill:blue;" /></svg>
                <svg width="30" height="20"><rect width="30" height="20" style="fill:orange;" /></svg>
                <svg width="30" height="20"><rect width="30" height="20" style="fill:rgb(0,0,0);" /></svg>
            </div> -->
            <div>
                <p><b class="price">${product.price}</b></p>
            </div>
            <div class="add-btn">
                <a class="link-button" href="">Add to Cart</a>
            </div>
            <div class="delete-btn">
                <a href="deleteproduct?id=${product.productID}"><i class="fa fa-trash" aria-hidden="true"></i></a>
            </div>
        </div>
    </c:forEach>
</div>