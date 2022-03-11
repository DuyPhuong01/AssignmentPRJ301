<%-- 
    Document   : home
    Author     : Duy Phuong
--%>

<%@page import="model.Category"%>
<%@page import="java.util.List"%>
<%@page import="dal.CategoryDAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home</title>
        <script src="js/set-theme.js"></script>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link rel="stylesheet" href="css/main.css">
        <script src="js/bootstrap/jquery.min.js"></script>
    </head>
    <body>
        <jsp:include page="element/navbar.jsp"></jsp:include>
        <!-- Banner -->
        <div class="rei_banner_container">
            <div class="rei_banner_body">
                <div>
                    <a href="product?categoryID=1"><img src="img/banner_01.jpg"></a>
                </div>
                <div>
                    <a href="product?categoryID=2"><img src="img/banner_02.jpg"></a>
                </div>
                <div>
                    <a href="product?categoryID=3"><img src="img/banner_03.jpg"></a>
                </div>
            </div>
            <div class="rei_banner_bottombtns">
                <span onclick="getBanner(1)" style='background-image:url(img/banner_01.jpg)'></span>
                <span onclick="getBanner(2)" style='background-image:url(img/banner_02.jpg)'></span>
                <span onclick="getBanner(3)" style='background-image:url(img/banner_03.jpg)'></span>
            </div>
        </div>
        <div class="home-show col-ms-12 col-lg-10">
            <div class="home-block">
                <div class="allcategory">
                    <form action="product" method="get">
                        <ul>
                            <%
                                CategoryDAO c_dao = new CategoryDAO();
                                List<Category> categoryList = c_dao.getAllCategory();
                                for (Category category : categoryList) {
                            %>
                                <li><label><input type="radio" name="categoryID" value="<%= category.getCategoryID() %>" hidden><%= category.getCategoryName() %></label></li>  
                            <%
                                }
                            %>
                        </ul>
                    </form>
                    <div class="product-home row mrg-lr-0">
                        <c:forEach begin="0" end="12" var="product" items="${requestScope.bestseller_productlist}">
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
                </div>
                <button class="left-btn"><</button>
                <button class="right-btn">></button>
            </div>
            <div class="home-block">
                <div class="bestseller">
                    <h2>Bestseller</h2>
                    <div class="product-home row mrg-lr-0">
                        <c:forEach begin="0" end="12" var="product" items="${requestScope.bestseller_productlist}">
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
                </div>
                <button class="left-btn"><</button>
                <button class="right-btn">></button>
            </div>
        </div>
        <c:import url="element/footer.jsp"></c:import>
    </body>
    <script src="js/banner.js"></script>
    <script>
        document.querySelectorAll('.home-block').forEach((d) => {
            var index=0;
            var area = d.querySelector('.product-home');
            d.querySelector('.left-btn').addEventListener('click', function(){
                if(index!=0){
                    index--;
                    area.style = 'transform: translateX(-'+ index*33.3333333333 +'%);';
                }
            });
            d.querySelector('.right-btn').addEventListener('click', function(){
                if(index!=3){
                    index++;
                    area.style = 'transform: translateX(-'+ index*33.3333333333 +'%);';
                }
            });
        });
    </script>
</html>
