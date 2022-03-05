<%-- 
    Document   : signin
    Author     : Duy Phuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html data-theme="light">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>JSP Page</title>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link rel="stylesheet" href="css/main.css">
        <script src="js/bootstrap/jquery.min.js"></script>
        <script src="js/set-theme.js"></script>
    </head>
    <body>
        <div class="row center">
            <div class="col-6">
                <a href="home">Home</a>
                <div class="container">
                    <h1 class="text-center">Sign Up!</h1>
                    <form class="container" action="signup" method="post">
                        <h3 style="color: red">${requestScope.alert}</h3>
                        <div class="row">
                            <div class="col-6">
                                First Name<br/>
                                <input type="text" name="firstname" required><br/>
                            </div>
                            <div class="col-6">
                                Last Name<br/>
                                <input type="text" name="lastname" required><br/>
                            </div>
                        </div>
                        Username:<br/>
                        <input type="text" name="username" required><br/>
                        Password: <input id="password" type="password" name="password" required><br/>
                        Confirm Password: <input id="confirm_password" type="password" name="confirm-password" required><br/>
                        <p id="message"></p>
                        City: <input type="text" name="city"><br/>
                        Country: <input type="text" name="country"><br/>
                        Address: <input type="text" name="address"><br/>
                        Phone: <input type="text" name="phone"><br/>
                        <div class="row">
                            <input id="submit" class="link-btn" type="submit" name="password" disabled>
                        </div>

                    </form>
                </div>
            </div>
        </div>
    </body>
    <script>
        $('#password, #confirm_password').on('keyup', function () {
            if ($('#password').val() == ''){
                $('#message').html('');
                $('#submit')[0].disabled = true;
            } else if ($('#password').val() == $('#confirm_password').val()) {
                $('#message').html('Matching').css('color', 'green');
                $('#submit')[0].disabled = false;
            }
            else {
                $('#message').html('Not Matching').css('color', 'red');
                $('#submit')[0].disabled = true;
            }
        });
    </script>
</html>
