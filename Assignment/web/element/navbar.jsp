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
<%
    OrderDAO o_dao = new OrderDAO();
    Cookie[] cookies = request.getCookies();
    Cookie user_cookie = null;
    if (cookies != null)
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("userAccount")) user_cookie = cookie;
        }
    if(user_cookie!=null) {
        String[] userAccount = user_cookie.getValue().split(",");
        try {
            int userID = Integer.parseInt(userAccount[0]);
%>
<c:set var="cart_number" value="<%= o_dao.getCart(userID).getList().size() %>"></c:set>
<%
        } catch(NumberFormatException nfe){
            System.out.println(nfe);
        }
    }
%>
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
                <a class="link-btn" href="signin">Sign In</a>
                <a class="link-btn sign-up-btn" href="signup">Sign Up</a>
            </c:if>
            <c:if test="${cookie.userAccount.getValue()!=null && cookie.userAccount.getValue()!=''}">
                <div class="account-navigation">
                    <i class="fa fa-user" aria-hidden="true"></i>
                    <ul class="dropdown-menu">
                        <c:if test="${cookie.userRole.getValue() == '1'}">
                            <li><a href="admin">Admin</a></li>
                            <li><a href="analytics">Analytics</a></li>
                            <hr style="margin: 5px 0;">
                        </c:if>
                        <li><a href="account?action=details">My Account</a></li>
                        <li><a href="account?action=signout">Sign Out</a></li>
                    </ul>
                </div>
                <a class="link-btn notification" href="mycart">My Cart
                    <span class="badge num-circle">${cart_number}</span>
                </a>
            </c:if>
        </div>
        <div class="nav-menu">
            <label class="setting-icon">
                <input type="checkbox" name="setting-switch">
                <i class="fa fa-cog" aria-hidden="true"></i></i>
                <ul class="setting-dropdown-menu">
                    <c:if test="${cookie.userAccount.getValue()==null || cookie.userAccount.getValue()==''}">
                        <li><a href="signin">Sign In</a></li>
                        <li><a href="signup">Sign Up</a></li>
                    </c:if>
                    <c:if test="${cookie.userAccount.getValue()!=null && cookie.userAccount.getValue()!=''}">
                        <c:if test="${cookie.userRole.getValue() == '1'}">
                            <li><a href="admin">Admin</a></li>
                            <li><a href="analytics">Analytics</a></li>
                            <hr style="margin: 5px 0;">
                        </c:if>
                        <li><a href="account?action=details">My Account</a></li>
                        <li><a href="mycart">My Cart
                                <span class="num-circle">${cart_number}</span>
                            </a>
                        </li>
                        <hr style="margin: 5px 0;">
                        <li><a href="account?action=signout">Sign Out</a></li>
                    </c:if>
                </ul>
            </label>
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