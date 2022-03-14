<%-- 
    Document   : home
    Author     : Duy Phuong
--%>

<%@page import="model.Product"%>
<%@page import="dal.ProductDAO"%>
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
            <form class="search-form" action="product">
                <input type="text" id="search" class="search-input" name="searchkey" value="${requestScope.searchkey}" placeholder="Search" autocomplete="off">
                <div class="search-icon"><i class="fa fa-search" aria-hidden="true"></i></div>
            </form>
            
            <!-- Best Seller -->
            <div class="home-block">
                <div class="slide bestseller">
                    <h2>Bestseller</h2>
                    <div class="product-home row mrg-lr-0">
                        <c:forEach begin="0" end="11" var="product" items="${requestScope.bestseller_productlist}">
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
            <!-- Mini Banner -->
            <div class="mini-banner">
                <div class="row mrg-lr-0">
                    <div class="col-4">
                        <a class="button" href="product?categoryID=1">Men</a>
                        <a href="product?categoryID=1">
                            <img src="img/men.jpg">
                        </a>
                    </div>
                    <div class="col-4">
                        <a class="button" href="product?categoryID=2">Women</a>
                        <a href="product?categoryID=2">
                            <img src="img/women.jpg">
                        </a>
                    </div>
                    <div class="col-4">
                        <a class="button" href="product?categoryID=3">Kid</a>
                        <a href="product?categoryID=3">
                            <img src="img/kid.jpg">
                        </a>
                    </div>
                </div>
            </div>
            <!-- All Category -->
            <div class="home-block allcategory">
                <div>
                    <ul>
                        <li><button class="focus" name="categoryID" onclick="moveTab('.product_cate_0')">All</button></li>  
                        <%
                            CategoryDAO c_dao = new CategoryDAO();
                            List<Category> categoryList = c_dao.getAllCategory();
                            for (Category category : categoryList) {
                        %>
                        <li><button name="categoryID" onclick="moveTab('.product_cate_<%= category.getCategoryID()%>')"><%= category.getCategoryName() %></button></li>  
                        <%
                            }
                        %>
                    </ul>
                </div>
                <div class="tab slide product_cate_0">
                    <div class="product-home row mrg-lr-0">
                        <c:forEach begin="0" end="11" var="product" items="${requestScope.productlist_all}">
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
                    <div class="extend-link">
                        <a href="product">See more...</a>
                    </div>
                </div>
                <%
                    for (Category category : categoryList) {
                %>
                <div class="tab slide product_cate_<%= category.getCategoryID()%>">
                    <div class="product-home row mrg-lr-0">
                    <%
                            ProductDAO p_dao = new ProductDAO();
                            List<Product> productList = p_dao.getProducts(category.getCategoryID());
                            int start=0;
                            for (Product product : productList) {
                                if(start<12){
                    %>
                        <div class="product-list product col-1">
                            <div class="product-image">
                                <a href="product?action=details&productID=<%= product.getProductID() %>"><img src="images/<%= product.getImage()%>" alt=""></a>
                            </div>
                            <p class="product-name"><%= product.getProductName()%></p>
                            <div>
                                <p><b class="price">$<%= product.getPrice()%></b></p>
                            </div>
                            <div class="add-btn">
                                <a class="link-button" href="addtocart?productID=<%= product.getProductID() %>">Add to Cart</a>
                            </div>
                        </div>
                    <%
                                }
                                start++;
                            }
                    %>
                    </div>
                    <button class="left-btn"><</button>
                    <button class="right-btn">></button>
                    <div class="extend-link">
                        <a href="product?categoryID=<%= category.getCategoryID()%>">See more...</a>
                    </div>
                </div>
                <%
                    }
                %>
                
            </div>
            <script>
                var x = document.querySelectorAll('.allcategory .tab');
                for (var i = 0; i < x.length; i++) {
                    x[i].style = "display: none";
                }
                document.querySelector('.product_cate_0').style = '';
                function moveTab(b) {
                    for (var i = 0; i < x.length; i++) {
                        x[i].style = "display: none";
                    }
                    document.querySelector(b).style = '';
                }
                
                var y = document.querySelectorAll('.allcategory ul button');
                y.forEach((b) => {
                    
                    b.addEventListener('click', function(){
                        for(var i=0; i<y.length; i++) {
                            y[i].classList = '';
                        }
                        b.classList='focus';
                    });
                });
            </script>
        </div>
        <c:import url="element/footer.jsp"></c:import>
    </body>
    <script src="js/banner.js"></script>
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
