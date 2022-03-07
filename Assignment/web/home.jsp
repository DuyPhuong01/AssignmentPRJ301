<%-- 
    Document   : home
    Author     : Duy Phuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home</title>
        <script src="js/set-theme.js"></script>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link rel="stylesheet" href="css/main.css">
        <link rel="stylesheet" href="css/banner.css">
        <link rel="stylesheet" href="css/menubar.css">
        <script src="js/bootstrap/jquery.min.js"></script>
    </head>
    <body>
        <jsp:include page="navbar.jsp"></jsp:include>
        <div class="rei_banner_container">
            <div class="rei_banner_body">
                <div>
                    <a href="product?categoryID=1"><img src="img/banner_01.jpg"></a>
                </div>
                <div>
                    <a href="product?categoryID=2"><img src="img/banner_02.jpg"></a>
                </div>
                <div>
                    <a href="product?categoryID=3"><img src="img/banner_03.jpg"></a>
                </div>
            </div>
            <div class="rei_banner_bottombtns">
                <span onclick="getBanner(1)" style='background-image:url(img/banner_01.jpg)'></span>
                <span onclick="getBanner(2)" style='background-image:url(img/banner_02.jpg)'></span>
                <span onclick="getBanner(3)" style='background-image:url(img/banner_03.jpg)'></span>
            </div>
        </div>
    </body>
    <script src="js/dark-theme.js"></script>
    <script src="js/banner.js"></script>
</html>
