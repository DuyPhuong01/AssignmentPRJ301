<%-- 
    Document   : signin
    Created on : Feb 17, 2022, 2:17:54 AM
    Author     : Duy Phuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html data-theme="light">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/main.css">
    </head>
    <body>
        <div class="row center">
            <div class="col-6 container">
                <h1 class="text-center">Sign Up!</h1>
                <form class="container" action="signin" method="post">
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
                    Enter Username:<br/>
                    <input type="text" name="username" required><br/>
                    Enter Password:<br/>
                    <input type="password" name="password" required><br/>
                    <div class="row">
                        <a href="account?action=signup">Create an Account</a>
                        <input class="link-btn" type="submit" name="password">
                    </div>
                    
                </form>
            </div>
        </div>
    </body>
</html>
