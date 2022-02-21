<%-- 
    Document   : product-manager
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1>product manager</h1>
<table>
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Logo</td>
        <td>Action</td>
    </tr>
    <c:forEach var="brand" items="${requestScope.brandList}">
        <tr>
            <td>${brand.brandID}</td>
            <td>${brand.brandName}</td>
            <td><img style="width: 50px" src="images/${brand.brandLogo}"></td>
            <td>
                <i onclick="deleteProduct('${brand.brandID}')" class="fa fa-trash" aria-hidden="true"></i>&nbsp;
                <a href="update?target=brand&id=${brand.brandID}"><i class="fa fa-pencil" aria-hidden="true"></i></a>
            </td>
        </tr>
    </c:forEach>
</table>