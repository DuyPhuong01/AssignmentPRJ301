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
        <div class="pd-lr-15 mycart">
            <div class="row" style="justify-content: center">
                <div class="col-ms-12">
                    <div class="row jt-right pd-lr-15">
                        <a href="mycart?action=history"><i class="fa fa-history" aria-hidden="true"></i>Buy History</a>
                    </div>
                    <div class="container">
                        <div class="row">
                            <div class="col-9 itemlist">
                                <table>
                                    <tr>
                                        <th><a onclick="chooseAll()">Choose All</input></th>
                                        <th>Product Name</th>
                                        <th>Product Image</th>
                                        <th>Price</th>
                                        <th>Quantity</th>
                                        <th></th>
                                    </tr>
                                    <c:forEach var="item" items="${requestScope.list}">
                                        <tr class="item">
                                            <!--  productID: ${item.getProduct().productID}  -->
                                            <td><input type="checkbox" name="chooseitems" value="${item.getProduct().productID}"></td>
                                            <td>${item.getProduct().productName}</td>
                                            <td><img src="images/${item.getProduct().image}"></td>
                                            <td>${item.getProduct().price}</td>
                                            <td><input type="number" name="quantity" value="${item.quantity}" min="1" max="${item.getProduct().quantity}"></td>
                                            <td>
                                                <a class="save-quantity-icon" style="visibility: hidden" onclick="updateQuantity()"><i class="fa fa-floppy-o" aria-hidden="true"></i></a>
                                                <a class="reset-quantity-icon" style="visibility: hidden"><i class="fa fa-refresh" aria-hidden="true"></i></a>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </div>
                            <div class="infor col-3">
                                <h1>Your Information</h1>
                                <a onclick="fillInfor()">Use my Address</a>
                                <h3 id="form-alert" style="color: red"></h3>
                                <form id="buy-form" action="" class="row" method="post">
                                    <label class="col-ms-12 rei-input-label">
                                        <input type="text" name="city" value="" required>
                                        <div class="rei-input-name">
                                            <span></span>
                                            <span class="rei-float-name"><p>City</p></span>
                                            <span></span>
                                        </div>
                                    </label>
                                    <label class="col-ms-12 rei-input-label">
                                        <input type="text" name="country" value="" required>
                                        <div class="rei-input-name">
                                            <span></span>
                                            <span class="rei-float-name"><p>Country</p></span>
                                            <span></span>
                                        </div>
                                    </label>
                                    <label class="col-ms-12 rei-input-label">
                                        <input type="text" name="address" value="" required>
                                        <div class="rei-input-name">
                                            <span></span>
                                            <span class="rei-float-name"><p>Address</p></span>
                                            <span></span>
                                        </div>
                                    </label>
                                    <label class="col-ms-12 rei-input-label">
                                        <input type="text" name="phone" value="" required>
                                        <div class="rei-input-name">
                                            <span></span>
                                            <span class="rei-float-name"><p>Phone</p></span>
                                            <span></span>
                                        </div>
                                    </label>
                                </form>
                            </div>
                        </div>
                        <div class="row jt-spc-btw mrg-top-20 pd-lr-15">
                            <div>
                                <a onclick="removeAll()">Clear All</input>&nbsp;&nbsp;&nbsp;
                                <a onclick="removeItem()">Remove</a>
                            </div>
                            <a class="link-btn" name="buy-btn">Buy</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <c:import url="element/footer.jsp"></c:import>
        <script>
            function removeItem(){
                if(confirm('Do you want to remove all Choosen Items in your cart?')){
                    document.querySelector('input[name="action"]').value = 'remove';
                    document.querySelector('form[action="mycart"]').submit();
                }
            }
            function removeAll(){
                if(confirm('Do you want to remove all Items in your cart?'))
                    window.location = "mycart?action=clear";
            }
            function chooseAll(){   
                document.querySelectorAll('input[name="chooseitems"]').forEach((i) => {
                    if(i.checked === true) i.checked = false;
                    else i.checked = true;
                    
                });
            }
            function fillInfor() {
                document.querySelector('input[name="city"]').value = '${requestScope.account.city}';
                document.querySelector('input[name="country"]').value = '${requestScope.account.country}';
                document.querySelector('input[name="address"]').value = '${requestScope.account.address}';
                document.querySelector('input[name="phone"]').value = '${requestScope.account.phone}';
                setFloat();
            }
            
            document.querySelectorAll('.item').forEach((item) => {
                var save_icon = item.querySelector('.save-quantity-icon');
                var reset_icon = item.querySelector('.reset-quantity-icon');
                var input_quantity = item.querySelector('input[name="quantity"');
                
                input_quantity.addEventListener('change', function(){
                    save_icon.style = 'visibility: visible';
                    reset_icon.style = 'visibility: visible';
                });
                reset_icon.addEventListener('click', function() {
                    input_quantity.value = input_quantity.getAttribute('value');
                    save_icon.style = 'visibility: hidden';
                    reset_icon.style = 'visibility: hidden';
                });
                save_icon.addEventListener('click', function() {
                    window.location = 'mycart?action=updatequantity&productid=' 
                            + item.querySelector('input[name="chooseitems"').value 
                            + '&quantity='+input_quantity.value;
                });
            });
            
            document.querySelector('a[name="buy-btn"]').addEventListener('click', function(){
                var url = 'mycart?action=buy';
                
                document.querySelectorAll('.item').forEach((item) => {
                    var checkbox = item.querySelector('input[name="chooseitems"]');
                    if(checkbox.checked){
                        url += '&productid='+checkbox.value;
                        url += '&quantity='+item.querySelector('input[name="quantity"]').value;
                    };
                });
                var form = document.getElementById('buy-form');
                if(form.checkValidity()){
                    form.action = url;
                    form.submit();
                } else {
                    document.getElementById('form-alert').innerHTML = 'You must be enter your Information';
                }
                
            });
        </script>
    </body>
    <script src="js/rei-input.js"></script>
</html>
