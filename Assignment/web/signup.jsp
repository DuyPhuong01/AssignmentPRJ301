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
        <title>Sign Up</title>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link rel="stylesheet" href="css/main.css">
        <link rel="stylesheet" href="css/login.css">
        <link rel="stylesheet" href="css/rei-input.css">
        <script src="js/bootstrap/jquery.min.js"></script>
        <script src="js/set-theme.js"></script>
    </head>
    <body>
        <div class="pd-lr-15">
            <div class="row center signup-container">
                <div class="col-ms-12 col-lg-6">
                    <a href="home">Home</a>
                    <div class="container">
                        <h3 style="color: red">${requestScope.alert}</h3>
                        <h1 class="text-center">Sign Up!</h1>
                        <form action="signup" method="post">
                            <input type="text" name="action" value="signup" hidden>
                            <div class="row">
                                <label class="col-ms-12 col-md-6 rei-input-label">
                                    <input type="text" name="firstname" required>
                                    <div class="rei-input-name">
                                        <span></span>
                                        <span class="rei-float-name"><p>First Name</p></span>
                                        <span></span>
                                    </div>
                                </label>
                                <label class="col-ms-12 col-md-6 rei-input-label">
                                    <input type="text" name="lastname" required>
                                    <div class="rei-input-name">
                                        <span></span>
                                        <span class="rei-float-name"><p>Last Name</p></span>
                                        <span></span>
                                    </div>
                                </label>
                            </div>
                            <div class="row">
                                <label class="col-ms-12 col-md-6 rei-input-label">
                                    <input type="text" name="username" required>
                                    <div class="rei-input-name">
                                        <span></span>
                                        <span class="rei-float-name"><p>Username</p></span>
                                        <span></span>
                                    </div>
                                </label>
                            </div>
                            <div class="row">
                                <label class="col-ms-12 col-md-6 rei-input-label">
                                    <input id="password" type="password" name="password" required>
                                    <div class="rei-input-name">
                                        <span></span>
                                        <span class="rei-float-name"><p>Password</p></span>
                                        <span></span>
                                    </div>
                                </label>
                                <label class="col-ms-12 col-md-6 rei-input-label">
                                    <input id="confirm_password" type="password" name="confirm-password" required>
                                    <div class="rei-input-name">
                                        <span></span>
                                        <span class="rei-float-name"><p>Confirm Password</p></span>
                                        <span></span>
                                    </div>
                                </label>
                            </div>
                            <p id="message"></p>
                            <div class="row">
                                <label class="col-ms-6 rei-input-label">
                                    <input type="text" name="country">
                                    <div class="rei-input-name">
                                        <span></span>
                                        <span class="rei-float-name"><p>Country</p></span>
                                        <span></span>
                                    </div>
                                </label>
                                <label class="col-ms-6 rei-input-label">
                                    <input type="text" name="city">
                                    <div class="rei-input-name">
                                        <span></span>
                                        <span class="rei-float-name"><p>City</p></span>
                                        <span></span>
                                    </div>
                                </label>
                                <label class="col-ms-12 col-md-8 rei-input-label">
                                    <input type="text" name="address">
                                    <div class="rei-input-name">
                                        <span></span>
                                        <span class="rei-float-name"><p>Address</p></span>
                                        <span></span>
                                    </div>
                                </label>
                                <label class="col-ms-12 col-md-4 rei-input-label">
                                    <input type="text" name="phone">
                                    <div class="rei-input-name">
                                        <span></span>
                                        <span class="rei-float-name"><p>Phone</p></span>
                                        <span></span>
                                    </div>
                                </label>
                            </div>
                            <div class="row jt-spc-btw mrg-top-20">
                                <a href="signin">Back to Sign In</a>
                                <input id="submit" class="link-btn" type="submit" name="password" disabled>
                            </div>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script src="js/rei-input.js"></script>
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
