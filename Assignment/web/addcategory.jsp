<%-- 
    Document   : addcategory
    Author     : Duy Phuong
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Category</title>
    </head>
    <body>
        <form action="addcategory" method="post">
            <table>
                <tr>
                    <td>
                        ID
                    </td>
                    <td>
                        <input type="number" name="id"><br/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Name
                    </td>
                    <td>
                        <input type="text" name="name"><br/>
                    </td>
                </tr>
                <tr>
                    <td>
                        Describe
                    </td>
                    <td>
                        <input type="text" name="describe"><br/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Add Category">
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
