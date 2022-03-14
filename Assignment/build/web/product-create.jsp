<%-- 
    Document   : addproduct
    Author     : Duy Phuong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="model.Brand"%>
<%@page import="java.util.List"%>
<%@page import="model.Category"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Product</title>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link rel="stylesheet" href="css/main.css">
        <script src="js/jquery.min.js"></script>
        <script src="js/set-theme.js"></script>
    </head>
    <body>
        <div class="center row" style="justify-content: center">
            <div class="col-6">
                <a href="home">Home</a>
                <div class="container">
                    <form action="product" method="post" enctype="multipart/form-data">
                        <input type="text" name="action" value="create" hidden>
                        <h3 style="text-transform: uppercase">Add Product</h3>    
                        Category: 
                        <select name="categoryID">
                            <c:if test="${requestScope.categoryList == '[]'}"><option name="category">Empty</option></c:if>
                            <c:forEach items="${requestScope.categoryList}" var="c">
                                <option value="${c.categoryID}">${c.categoryName}</option>
                            </c:forEach>
                        </select>
                        Brand: 
                        <select name="brandID">
                            <c:if test="${requestScope.brandList == '[]'}"><option name="brand">Empty</option></c:if>
                            <c:forEach items="${requestScope.brandList}" var="b">
                                <option value="${b.brandID}">${b.brandName}</option>
                            </c:forEach>
                        </select>
                        <div class="row">
                            <label class="col-ms-12 rei-input-label">
                                <input type="text" name="name">
                                <div class="rei-input-name">
                                    <span></span>
                                    <span class="rei-float-name"><p>Name</p></span>
                                    <span></span>
                                </div>
                            </label>
                            <label class="col-ms-6 rei-input-label">
                                <input type="number" name="price">
                                <div class="rei-input-name">
                                    <span></span>
                                    <span class="rei-float-name"><p>Price</p></span>
                                    <span></span>
                                </div>
                            </label>
                            <label class="col-ms-6 rei-input-label">
                                <input type="number" name="quantity">
                                <div class="rei-input-name">
                                    <span></span>
                                    <span class="rei-float-name"><p>Quantity</p></span>
                                    <span></span>
                                </div>
                            </label>
                            <label class="rei-input-file-image col-ms-4">
                                <span class="input-name">Product Picture</span>
                                <input type="file" name="productPhoto">
                                <div class="preview">
                                    <img id="uploadPreview" style="display: none;">
                                    <div class="box-hover"><p>Upload File</p></div>
                                </div>
                            </label>
                            <div class="col-ms-12 rei-input-radio">
                                <span class="input-radio-name">Activate Status</span>
                                <label>
                                    <input type="radio" name="activate" value="1" checked>
                                    <span>In Stock</span>
                                </label>
                                <label>
                                    <input type="radio" name="activate" value="0">
                                    <span>Out Of Stock</span>
                                </label>
                            </div>
                        </div>
                        <input type="submit" value="Add Product">
                    </form>
                </div>
            </div>
        </div>
    </body>
    <script src="js/rei-input.js"></script>
    <script>
        var imgPreview =document.querySelector('#uploadPreview');
        document.querySelector('input[name="productPhoto"]').addEventListener('change', function(){
            const files = this.files[0];
            if (files) {
              const fileReader = new FileReader();
              fileReader.readAsDataURL(files);
              fileReader.addEventListener("load", function () {
                imgPreview.style = '';
                imgPreview.classList = 'preview-img';
                imgPreview.setAttribute('src', this.result)
              });    
            }
        })
    </script>
</html>
