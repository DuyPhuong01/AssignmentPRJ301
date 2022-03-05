<%-- 
    Document   : addcategory
    Author     : Duy Phuong
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Category</title>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link rel="stylesheet" href="css/main.css">
        <script src="js/jquery.min.js"></script>
        <script src="js/gallery.js"></script>
    </head>
    <body>
        <c:set var="category" value="${requestScope.category}"></c:set>
        <div class="center row" style="justify-content: center">
            <form class="container col-6" action="updatecategory" method="post">
                <input type="text" name="categoryID" value="${category.categoryID}" hidden><br/>
                <table>
                    <tr>
                        <td>
                            Name:
                        </td>
                        <td>
                            <input type="text" name="name" value="${category.categoryName}"><br/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Describe:
                        </td>
                        <td>
                            <input type="text" name="describe" value="${category.description}"><br/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Activate:
                        </td>
                        <td>
                            <input type="radio" name="activate" value="1" <c:if test="${category.status ==1}">checked</c:if>>Yes
                            <input type="radio" name="activate" value="0" <c:if test="${category.status ==0}">checked</c:if>>No
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" value="Update Category">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
