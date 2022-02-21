<%-- 
    Document   : sidebar
    Author     : Duy Phuong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="option-list col-2">
    <ul><span>Brands</span>
        <c:if test="${requestScope.brandList == '[]'}"><li name="brand">Empty</li></c:if>
        <c:forEach items="${requestScope.brandList}" var="b">
            <li><input type="checkbox" name="brand" value="${b.brandID}">${b.brandName}</li>
        </c:forEach>
    </ul>
        <form>
            <input type="checkbox" name="brand" value="1">80-90
            <input type="checkbox" name="brand" value="2">91-100
            <input type="checkbox" name="brand" value="3">101-120
            <input type="checkbox" name="brand" value="4">121-140
        </form>
</div>