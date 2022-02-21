<%-- 
    Document   : account-details
    Created on : Feb 21, 2022, 7:55:31 PM
    Author     : Duy Phuong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:set var="user" value="${sessionScope.userAccount}"></c:set>
Username: ${user.username}<br/>
Password: ${user.password}<br/>
FullName: ${user.fullname}<c:if test="${user.role==1}"> (Admin)</c:if><br/>
City: ${user.city}<br/>
Country: ${user.country}<br/>
Address: ${user.address}<br/>
Phone: ${user.phone}<br/>