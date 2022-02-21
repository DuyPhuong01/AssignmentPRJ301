<%-- 
    Document   : user-manager
    Created on : Feb 20, 2022, 4:11:47 AM
    Author     : Duy Phuong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1>user manager</h1>
<table>
    <tr>
        <td>Username</td>
        <td>Password</td>
        <td>Role</td>
        <td>FullName</td>
        <td>City</td>
        <td>Country</td>
        <td>Address</td>
        <td>Phone</td>
        <td>Action</td>
    </tr>
    <c:forEach var="user" items="${requestScope.userList}">
        <tr>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td><c:if test="${user.role==1}">Admin</c:if><c:if test="${user.role==0}">Customer</c:if></td>
            <td>${user.fullname}</td>
            <td>${user.city}</td>
            <td>${user.country}</td>
            <td>${user.address}</td>
            <td>${user.phone}</td>
            <td>
                <i onclick="deleteProduct('${user.username}')" class="fa fa-trash" aria-hidden="true"></i>&nbsp;
                <a href="update?target=user&id=${user.username}"><i class="fa fa-pencil" aria-hidden="true"></i></a>
            </td>
        </tr>
    </c:forEach>
</table>