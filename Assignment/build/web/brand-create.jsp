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
        <script src="js/gallery.js"></script>
    </head>
    <body>
        <div class="center row" style="justify-content: center">
            <form class="container col-6" action="category" method="post">
                <input type="text" name="action" value="create" hidden>
                <table>
                    <tr>
                        <td>
                            Name:
                        </td>
                        <td>
                            <input type="text" name="name"><br/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Describe:
                        </td>
                        <td>
                            <input type="text" name="describe"><br/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Activate:
                        </td>
                        <td>
                            <input type="radio" name="activate" value="1">Yes
                            <input type="radio" name="activate" value="0">No
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <input type="submit" value="Create Category">
                        </td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
