<%-- 
    Document   : account-details
    Created on : Feb 21, 2022, 7:55:31 PM
    Author     : Duy Phuong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<a href="account?action=update">Change Information</a><br/>
<a href="account?action=changeusernameandpassword">Change Username and Password</a><br/>
<a onclick="deleteAccount()">Delete this Account</a>

<script>
    function deleteAccount(){
        if(confirm('Are you sure to delete your Account?')) window.location = "account?action=delete";
    }
</script>