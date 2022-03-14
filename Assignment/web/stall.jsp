<%-- 
    Document   : productlist
    Author     : Duy Phuong
--%>
 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="product-list-1" class="product-list row">
    <c:forEach var="product" items="${requestScope.productList}">
        <div class="product col-ms-6 col-md-4 col-lg-3">
            <div class="product-image">
                <a href="product?action=details&productID=${product.productID}"><img src="images/${product.image}" alt=""></a>
            </div>
            <p class="product-name">${product.productName}</p>
            <div>
                <p><b class="price">$${product.price}</b></p>
            </div>
            <div class="add-btn">
                <a class="link-button" href="addtocart?productID=${product.productID}">Add to Cart</a>
            </div>
        </div>
    </c:forEach>
    <div class="paging-btn-grp">
        <div class="btn-group">
        <% 
            int number_of_page = (Integer)request.getAttribute("number_of_page");
            try{
                for(int i=1; i<=number_of_page; i++){
        %>
            <button type="button" class="" onclick="movePage(<%= i %>)"><%= i %></button>
        <%
                }
            } catch(NumberFormatException nfe){
                System.out.println(nfe);
            }
        %>
        </div>
    </div>
    <script>
        function movePage(page_number) {
            var page = [page_number];
            window.location.search = changeURL('page', page, window.location.search);
        }
    </script>
</div>