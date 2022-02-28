<%-- 
    Document   : user-manager
    Created on : Feb 20, 2022, 4:11:47 AM
    Author     : Duy Phuong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1>user manager</h1>
<a href="signup"> Add an account</a>
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
                <i onclick="deleteAccount('${user.username}')" class="fa fa-trash" aria-hidden="true"></i>&nbsp;
                <a href="update?target=user&id=${user.username}"><i class="fa fa-pencil" aria-hidden="true"></i></a>
                <i onclick="showInformation('${user}')" class="fa fa-info-circle" aria-hidden="true"></i>
                <i onclick="setAdmin('${user.username}')" class="fa fa-cog" aria-hidden="true"></i>
            </td>
        </tr>
    </c:forEach>
</table>
<script>
    function deleteAccount(username) {
        if(confirm('Are you sure to delete Account with username = ' + username + "?")) window.location = "deleteaccount?username="+username;
    }
    function setAdmin(username) {
        if(confirm('Are you sure to set Account with username = ' + username + " to be an Admin?")) window.location = "setadmin?username="+username;
    }
    function showInformation(username) {
        
    }
</script>