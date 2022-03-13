<%-- 
    Document   : product
    Author     : Duy Phuong
--%>

<%@page import="dal.CategoryDAO"%>
<%@page import="java.util.List"%>
<%@page import="model.Product"%>
<%@page import="dal.ProductDAO"%>
<%@page import="model.Category"%>
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
        <script src="js/bootstrap/jquery.min.js"></script>
        <script src="js/set-theme.js"></script>
    </head>
    <body>
        <jsp:include page="element/navbar.jsp"></jsp:include>
        <c:set var="product" value="${requestScope.product}"></c:set>
            <div class="content row" style="justify-content: center">
                <div class="container col-ms-12 col-lg-9 row product">
                    <div class="product-image col-6 pd-lr-0">
                        <a href="product?action=details&productID=${product.productID}"><img src="images/${product.image}" alt=""></a>
                    </div>
                    <div class="product-details col-6">
                        <form action="addtocart">
                            <input type="text" name="productID" value="${product.productID}" hidden>
                            <p>${product.productName}</p>
                            <!--<p>${product.brandID}</p>-->
                            <p><b>$${product.price}</b></p>
                            <div class="number-input">
                                <span>Quantity:</span>
                                <label class="col-ms-9 col-md-3 col-lg-2 rei-input-label">
                                    <input min="1" type="number" name="num" value="1">
                                    <div class="rei-input-name">
                                        <span></span>
                                        <span class="rei-float-name"><p></p></span>
                                        <span></span>
                                    </div>
                                </label>
                            </div>
                            <div class="add-btn">
                                <input type="submit" name="submit" value="Add to Cart">
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        <div class="home-show col-ms-12 col-lg-9">
            <div class="home-block relatedproduct">
                <h2>Related Products</h2>
                <div class="tab slide product_cate_0">
                    <div class="product-home row mrg-lr-0">
                        <c:forEach begin="0" end="11" var="product" items="${requestScope.relatedproduct}">
                            <div class="product col-1">
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
                    </div>
                    <button class="left-btn"><</button>
                    <button class="right-btn">></button>
                </div>
            </div>
        </div>
        <c:import url="element/footer.jsp"></c:import>
    </body>
    <script src="js/rei-input.js"></script>
    <script>
        document.querySelectorAll('.home-block>.slide').forEach((d) => {
            var index=0;
            var area = d.querySelector('.product-home');
            d.querySelector('.left-btn').addEventListener('click', function(){
                if(index!=0){
                    index--;
                    area.style = 'transform: translateX(-'+ index*33.3333333333 +'%);';
                }
            });
            d.querySelector('.right-btn').addEventListener('click', function(){
                if(index!=2){
                    index++;
                    area.style = 'transform: translateX(-'+ index*33.3333333333 +'%);';
                }
            });
        });
    </script>
</html> 
