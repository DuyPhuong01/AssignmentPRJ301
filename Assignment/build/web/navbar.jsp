<%-- 
    Document   : navbar
    Author     : Duy Phuong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="navbar">
    <div class="logo">
        <a href="main">
            <img class="store-logo" src="images/logo.png" alt="logo">
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
        <span>
            <label>
                <input type="checkbox" name="theme">
                <span class="btn"></span>
                <i class="fa fa-moon-o" aria-hidden="true"></i>
            </label>
        </span>
        <c:if test="${sessionScope.userAccount==null}"><a class="link-btn" href="signin">Sign in</a></c:if>
        <c:if test="${sessionScope.userAccount!=null}">
            <div class="account-navigation">
                <i class="fa fa-user" aria-hidden="true"></i>
                <ul>
                    <li><a href="account?action=details">My Account</a></li>
                    <li><a href="account?action=signout">Sign Out</a></li>
                </ul>
            </div>
        </c:if> 
        <a class="link-btn notification" href="">My Cart
            <span class="badge">5</span>
        </a>
    </div>
</div>