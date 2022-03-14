<%-- 
    Document   : cart
    Author     : Duy Phuong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>My Cart</title>
        <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous">
        <link rel="stylesheet" href="css/main.css">
        <script src="js/bootstrap/jquery.min.js"></script>
        <script src="js/set-theme.js"></script>
    </head>
    <body>
        <c:import url="element/navbar.jsp"></c:import>
        <div class="pd-lr-15">
            <div class="row" style="justify-content: center">
                <div class="col-ms-12 col-lg-10">
                    <div class="container row">
                        <div class="cart-history col-8">
                            <div class="cart">
                                <div>Order Date</div>
                                <div>Order Time</div>
                                <div>Total Price</div>
                            </div>
                            <c:forEach var="cart" items="${requestScope.history_cart}">
                                <div class="cart-details">
                                    <label class="cart">
                                        <input type="checkbox" name="cart" hidden>
                                        <div>${cart.orderDate.date}/${cart.orderDate.month+1}/${cart.orderDate.year+1900}</div>
                                        <div>${cart.orderDate.hours}:${cart.orderDate.minutes}:${cart.orderDate.seconds}</div>
                                        <div>$${cart.totalPrice}</div>
                                    </label>
                                    <table style="display: none">
                                        <tr>
                                            <th>Product Name</th>
                                            <th>Product Image</th>
                                            <th>Price</th>
                                            <th>Quantity</th>
                                        </tr>
                                        <c:forEach var="item" items="${cart.list}">
                                            <tr class="item">
                                                <td>${item.getProduct().productName}</td>
                                                <td><img src="images/${item.getProduct().image}"></td>
                                                <td>$${item.getProduct().price}</td>
                                                <td>${item.quantity}</td>
                                            </tr>
                                        </c:forEach>
                                    </table>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="col-4 total-spend">
                            <h2>Total Spend</h2>
                            <div>$${requestScope.total_spend}</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <c:import url="element/footer.jsp"></c:import>
        <script>
            document.querySelectorAll('.cart-details').forEach((c) => {
                c.querySelector('input[name="cart"]').addEventListener('change', function () {
                    if (this.checked) {
                        c.querySelector('table').style = '';
                    } else c.querySelector('table').style = 'display: none;';
                });
            });
        </script>
    </body>
</html>
