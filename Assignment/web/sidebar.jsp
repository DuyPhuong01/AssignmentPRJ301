<%-- 
    Document   : sidebar
    Author     : Duy Phuong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="option-list col-2">
    <c:set var="min" value="10"></c:set>
    <c:set var="max" value="100"></c:set>
    <form>
        <ul><span>Brands</span>
            <c:if test="${requestScope.brandList == '[]'}"><li name="brand">Empty</li></c:if>
            <c:forEach items="${requestScope.brandList}" var="b">
                <li><input type="checkbox" name="brand" value="${b.brandID}">${b.brandName}</li>
            </c:forEach>
        </ul>
        <div id="slider"></div>
        <div class="display">
            <input type="number" id="input-with-keypress-0" value="20">
            <input type="number" id="input-with-keypress-1" value="80">
        </div>
        </form>
</div>
            <script src="libraries/nouislider/nouislider.js"></script>
    <script>
        var stepsSlider = document.getElementById('slider');
        var input0 = document.getElementById('input-with-keypress-0');
        var input1 = document.getElementById('input-with-keypress-1');
        var inputs = [input0, input1];

        noUiSlider.create(stepsSlider, {
            start: [0, 200],
            connect: true,
            range: {
                'min': [0],
                'max': 200
            }
        });

        stepsSlider.noUiSlider.on('update', function (values, handle) {
            inputs[handle].value = values[handle];
        });
    </script>
