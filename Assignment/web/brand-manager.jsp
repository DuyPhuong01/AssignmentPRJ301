<%-- 
    Document   : product-manager
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1>Brand manager</h1>
<a href="brand?action=create">Create Brand</a>
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
                <i onclick="deleteBrand('${brand.brandID}')" class="fa fa-trash" aria-hidden="true"></i>&nbsp;
                <a href="brand?action=update&id=${brand.brandID}"><i class="fa fa-pencil" aria-hidden="true"></i></a>
            </td>
        </tr>
    </c:forEach>
</table>
<script>
    function deleteBrand(id){
        if(confirm("Are you sure to delete Brand with id = "+id+"?")) window.location="brand?action=delete&id="+id;
    }
</script>