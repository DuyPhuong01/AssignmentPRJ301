<%-- 
    Document   : user-manager
    Created on : Feb 20, 2022, 4:11:47 AM
    Author     : Duy Phuong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1>User manager</h1>
<table>
    <tr>
        <td>Username</td>
        <td>Password</td>
        <td>Role</td>
        <td>FullName</td>
        <td>Action</td>
    </tr>
    <c:forEach var="user" items="${requestScope.userList}">
        <tr>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td><c:if test="${user.role==1}">Admin</c:if><c:if test="${user.role==0}">Customer</c:if></td>
            <td>${user.fullname}</td>
            <td>
                <i onclick="deleteAccount('${user.userID}')" class="fa fa-trash" aria-hidden="true"></i>
                <a href="user?action=update&id=${user.userID}"><i class="fa fa-pencil" aria-hidden="true"></i></a>
                <i onclick="setAdmin('${user.userID}')" class="fa fa-cog" aria-hidden="true"></i>
            </td>
        </tr>
    </c:forEach>
</table>
<script>
    function deleteAccount(id) {
        if(confirm('Are you sure to delete Account with id = ' + id + "?")) window.location = "user?action=delete&id="+id;
    }
    function setAdmin(id) {
        if(confirm('Are you sure to set Account with id = ' + id + " to be an Admin?")) window.location = "user?action=setadmin&id="+id;
    }
</script>