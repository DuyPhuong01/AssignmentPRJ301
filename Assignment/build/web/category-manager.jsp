<%-- 
    Document   : product-manager
    Created on : Feb 20, 2022, 4:11:47 AM
    Author     : Duy Phuong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1>Category manager</h1>
<a href="createcategory">Create Category</a>
<table>
    <tr>
        <td>ID</td>
        <td>Name</td>
        <td>Description</td>
        <td>Activate</td>
        <td>Action</td>
    </tr>
    <c:forEach var="category" items="${requestScope.categoryList}">
        <tr>
            <td>${category.categoryID}</td>
            <td>${category.categoryName}</td>
            <td>${category.description}</td>
            <td><i style="color: ${category.status==0 ? "red" : "green"}" class="fa fa-circle" aria-hidden="true"></i></td>
            <td>
                <i onclick="deleteCategory('${category.categoryID}')" class="fa fa-trash" aria-hidden="true"></i>&nbsp;
                <a href="updatecategory?id=${category.categoryID}"><i class="fa fa-pencil" aria-hidden="true"></i></a>
            </td>
        </tr>
    </c:forEach>
</table>

<script>
    function deleteCategory(id){
        if(confirm('Are you sure to delete category with id = ' + id + "?")) window.location = "deletecategory?id="+id;
    }
</script>