<%-- 
    Document   : addcategory
    Author     : Duy Phuong
--%>

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
        <script src="js/set-theme.js"></script>
    </head>
    <body>
        <div class="center row mrg-lr-0" style="justify-content: center">
            <div class="col-ms-12 col-md-6 col-lg-4">
                <a href="home">Home</a>
                <label>
                    <input id="theme-checkbox" type="checkbox" name="theme">
                    <span class="btn"></span>
                    <i class="fa fa-moon-o" aria-hidden="true"></i>
                </label>
                <form class="container" action="category" method="post">
                    <input type="text" name="action" value="create" hidden>
                    <div class="row">
                        <label class="col-ms-12 rei-input-label">
                            <input type="text" name="name">
                            <div class="rei-input-name">
                                <span></span>
                                <span class="rei-float-name"><p>Name</p></span>
                                <span></span>
                            </div>
                        </label>
                        <label class="col-ms-12 rei-input-label">
                            <input type="text" name="describe">
                            <div class="rei-input-name">
                                <span></span>
                                <span class="rei-float-name"><p>Describe</p></span>
                                <span></span>
                            </div>
                        </label>
                        <div class="col-ms-12 rei-input-radio">
                            <span class="input-radio-name">Activate</span>
                            <label>
                                <input type="radio" name="activate" value="1" checked>
                                <span>Yes</span>
                            </label>
                            <label>
                                <input type="radio" name="activate" value="0">
                                <span>No</span>
                            </label>
                        </div>
                    </div>
                    <br/>
                    <input type="submit" value="Create Category">
                </form>
            </div>
        </div>
    </body>
    <script src="js/rei-input.js"></script>
    <script src="js/dark-theme.js"></script>
</html>
