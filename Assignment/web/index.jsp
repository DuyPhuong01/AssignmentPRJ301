<%-- 
    Document   : index
    Author     : Duy Phuong
--%>

<%@page import="java.util.List"%>
<%@page import="model.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>R'Store Page</title>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
    <link rel="stylesheet" href="css/main.css">
    <link rel="stylesheet" href="libraries/nouislider.css">
    <script src="js/bootstrap/jquery.min.js"></script>
    <script src="js/set-theme.js"></script>
</head>

<body>
    <c:import url="element/navbar.jsp"></c:import>
    <div class="content row">
        <c:import url="element/sidebar.jsp"></c:import>
        <div class="stall container col-xs-9">
            <div class="heading">
                <div id="sorting" class="select-btn">
                    <div class="selected">
                        <p>${requestScope.orderby}</p>
                    </div>
                    <div class="select-box">
                        <span></span>
                        <span class="select-name"><p>Sort By</p></span>
                        <span></span>
                    </div>
                    <ul class="select-list">
                        <li value="default" class="option">Default</li>
                        <li value="name" class="option">ByName</li>
                        <li value="price" class="option">Price</li>
                    </ul>
                </div>
                <div class="search-container">
                    <form class="search-form" action="search">
                        <input type="text" id="search" class="search-input" name="searchkey" value="${requestScope.searchkey}" placeholder="Search" autocomplete="off">
                        <div class="search-icon"><i class="fa fa-search" aria-hidden="true"></i></div>
                    </form>
                </div>
            </div>
            <c:import url="stall.jsp"></c:import>
        </div>
    </div>
    <c:import url="element/footer.jsp"></c:import>
    <script src="js/ul-option.js"></script>
    <script>
        var order = document.querySelectorAll('#sorting li');

        order.forEach((o) => {
            o.addEventListener('click', function() {
                var val = [o.getAttribute('value')];
                window.location = changeURL('orderby', val, window.location.href);
            });
        });
    </script>
    <script>
        $(document).on("keydown", ':input[name="searchkey"]', function(event) {
            if (event.key === "Enter") {
                event.preventDefault();
                search();
            }
        });
        function search(){
            var searchkey = [];
            searchkey.push(document.querySelector('input[name="searchkey"]').value);
            url = window.location.href;
            url = changeURL('searchkey', searchkey, url);
            window.location = url;
        }
    </script>
    <script src="js/menubar.js"></script>
</body>

</html>