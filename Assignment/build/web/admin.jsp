<%-- 
    Document   : admin
    Author     : Duy Phuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Mode</title>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link rel="stylesheet" href="css/main.css">
        <link rel="stylesheet" href="css/admin.css">
        <script src="js/jquery.min.js"></script>
        <script src="js/gallery.js"></script>
    </head>
    <body>
        
        <div class="row center">
            <div class="container col-10">
            <a href="main">Home</a>
        <div class="row">
                <div class="admin-menu col-2">
                    <h1>Admin Mode</h1>
                    <a href="admin?action=product">Products</a><br/>
                    <a href="admin?action=category">Categories</a><br/>
                    <a href="admin?action=brand">Brands</a><br/>
                    <a href="admin?action=user">Users</a><br/>
                </div>
                <div class="col-10">
                    <jsp:include page="${requestScope.page}.jsp"></jsp:include>
                </div>
        </div>
            </div>
        </div>
    </body>
    <script src="js/live-search.js"></script>
</html>