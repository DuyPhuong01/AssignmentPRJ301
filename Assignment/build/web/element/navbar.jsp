<%-- 
    Document   : navbar
    Author     : Duy Phuong
--%>

<%@page import="dal.OrderDAO"%>
<%@page import="dal.CategoryDAO"%>
<%@page import="java.util.List"%>
<%@page import="model.Category"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="navbar">
    <div class="navigator">
        <div class="logo">
            <a href="home">
                <img id="store-logo" src="images/logo.png" alt="logo">
                <p class="store-name">R'Shoes</p>
            </a>
        </div>
        <nav>
            <ul>
                <li><a href="product">All</a></li>
                <%
                    CategoryDAO c_dao = new CategoryDAO();
                    List<Category> categoryList = c_dao.getAllCategory();
                    for (Category category : categoryList) {
                %>
                        <li><a href="product?categoryID=<%= category.getCategoryID() %>"><%= category.getCategoryName() %></a></li>
                <%
                    }
                %>
            </ul>
        </nav>
        <div class="option-container">
            <label>
                <input id="theme-checkbox" type="checkbox" name="theme">
                <i class="fa fa-moon-o" aria-hidden="true"></i>
            </label>
            <c:if test="${cookie.userAccount.getValue()==null || cookie.userAccount.getValue()==''}">
                <a class="link-btn" href="account?action=signin">Sign In</a>
                <a class="link-btn sign-up-btn" href="account?action=signup">Sign Up</a>
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
                    OrderDAO o_dao = new OrderDAO();
                    Cookie[] cookies = request.getCookies();
                    Cookie user_cookie = null;
                    if (cookies != null)
                        for (Cookie cookie : cookies) {
                            if(cookie.getName().equals("userAccount")) user_cookie = cookie;
                        }
                    String[] userAccount = user_cookie.getValue().split("|");
                    try {
                        int userID = Integer.parseInt(userAccount[0]);
                %>
                <a class="link-btn notification" href="mycart">My Cart
                    <span class="badge"><%= o_dao.getCart(userID).getList().size() %></span>
                </a>
                <%
                    } catch(NumberFormatException nfe){
                        System.out.println(nfe);
                    }
                %>
            </c:if> 
        </div>
        <div class="nav-menu">
            <label class="menu-open-icon">
                <input type="checkbox" name="menu-switch">
                <i class="fa fa-bars" aria-hidden="true"></i>
            </label>
        </div>
    </div>
    <c:import url="element/menubar.jsp"></c:import> 
</div>
<div class="header"></div>
<script src="js/dark-theme.js"></script>
<script src="js/menubar.js"></script>
<script>
        var fixmeTop = $('.navbar').offset().top;
        $(window).scroll(function() {
            var currentScroll = $(window).scrollTop();
            if (currentScroll >= fixmeTop) {
                $('.navbar').style = "box-shadow: 1px 1px 5px var(--theme-light-color)";
            } else $('.navbar').style = "box-shadow: none";
        });
</script>