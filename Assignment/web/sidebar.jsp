<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="option-list col-2">
    <ul><span>Brands</span>
        <c:if test="${requestScope.brandList == '[]'}"><li name="brand">Empty</li></c:if>
        <c:forEach items="${requestScope.brandList}" var="b">
            <li name="brand" value="${b.brandID}">${b.brandName}</li>
        </c:forEach>
    </ul>
</div>