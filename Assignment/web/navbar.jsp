<%-- 
    Document   : navbar
    Author     : Duy Phuong
--%>

<%@page import="dal.DAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="navbar">
    <div class="logo">
        <a href="home">
            <img id="store-logo" src="images/logo.png" alt="logo">
            <p class="store-name">R'Shoes</p>
        </a>
    </div>
    <nav>
        <ul>
            <li><a href="product">All</a></li>
            <c:forEach var="category" items="${requestScope.categoryList}">
                <li><a href="product?categoryID=${category.categoryID}">${category.categoryName}</a></li>
            </c:forEach>
        </ul>
    </nav>
    <div class="option-container">
        <label>
            <input id="theme-checkbox" type="checkbox" name="theme">
            <i class="fa fa-moon-o" aria-hidden="true"></i>
        </label>
        <c:if test="${cookie.userAccount.getValue()==null || cookie.userAccount.getValue()==''}">
            <a class="link-btn" href="signin">Sign In</a>
            <a class="link-btn" href="signup">Sign Up</a>
        </c:if>
        <c:if test="${cookie.userAccount.getValue()!=null && cookie.userAccount.getValue()!=''}">
            <div class="account-navigation">
                <i class="fa fa-user" aria-hidden="true"></i>
                <ul class="dropdown-menu">
                    <c:if test="${cookie.userRole.getValue() == '1'}">
                        <li><a href="admin">Admin</a></li>
                        <hr style="margin: 5px 0;">
                    </c:if>
                    <li><a href="account?action=details">My Account</a></li>
                    <li><a href="account?action=signout">Sign Out</a></li>
                </ul>
            </div>
            <%
                DAO dao = new DAO();
                Cookie[] cookies = request.getCookies();
                Cookie user_cookie = null;
                if (cookies != null)
                    for (Cookie cookie : cookies) {
                        if(cookie.getName().equals("userAccount")) user_cookie = cookie;
                    }
            %>
            <a class="link-btn notification" href="mycart">My Cart
                <span class="badge"><%= dao.getCartByUsername(user_cookie.getValue()).getList().size() %></span>
            </a>
        </c:if> 
    </div>
</div>
<div class="header"></div>
<script src="js/dark-theme.js"></script>
<script>
        var fixmeTop = $('.navbar').offset().top;
        $(window).scroll(function() {
            var currentScroll = $(window).scrollTop();
            if (currentScroll >= fixmeTop) {
                $('.navbar').style = "box-shadow: 1px 1px 5px var(--theme-light-color)";
            } else $('.navbar').style = "box-shadow: none";
        });
</script>