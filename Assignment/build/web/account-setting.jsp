<%-- 
    Document   : account-details
    Created on : Feb 21, 2022, 7:55:31 PM
    Author     : Duy Phuong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<a onclick="deleteAccount('${sessionScope.userAccount.username}')">Delete this Account</a>

<script>
    function deleteAccount(username){
        if(confirm('Are you sure to delete your Account?')) window.location = "account?acion=delete&username="+username;
    }
</script>