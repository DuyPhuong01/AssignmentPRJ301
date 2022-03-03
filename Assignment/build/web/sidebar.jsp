<%-- 
    Document   : sidebar
    Author     : Duy Phuong
--%>

<%@page import="model.Brand"%>
<%@page import="java.util.List"%>
<%@page import="dal.DAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="option-list col-2">
    <c:set var="min" value="10"></c:set>
    <c:set var="max" value="100"></c:set>
        <form action="product">
            <ul><span>Brands</span>
            <%
                DAO dao = new DAO();
                List<Brand> brandList = dao.getAllBrand();
                if (brandList.size() == 0) {
            %>
                    <li name="brand">Empty</li>
            <%
                } else {
                    for(Brand b: brandList){
            %>
                        <li>
                            <label>
                                <input type="checkbox" name="brand" value="<%= b.getBrandID() %>">
                                <img width="20px" src="images/<%= b.getBrandLogo() %>"><span><%= b.getBrandName() %></span>
                            </label>
                        </li>
            <%
                    }
                }
            %>
        </ul>
        Price:
        <div id="slider"></div>
        <div class="display-slider">
            <div><input type="text" id="input-with-keypress-0" name="min"></div>
            <span>-</span>
            <div><input type="text" id="input-with-keypress-1" name="max"></div>
        </div>
        <button type="submit" disabled style="display: none" aria-hidden="true"></button>
        <input class="filter-button" type="button" onclick="submitFilter()" value="Filter">
    </form>
</div>
<script src="js/changeurl.js"></script>
<script src="libraries/nouislider.js"></script>
<script>
    var stepsSlider = document.getElementById('slider');
    var input0 = document.getElementById('input-with-keypress-0');
    var input1 = document.getElementById('input-with-keypress-1');
    var inputs = [input0, input1];

    noUiSlider.create(stepsSlider, {
        start: [20, 200],
        connect: true,
        step: 1,
        range: {
            'min': 0,
            'max': 200
        }
    });

    stepsSlider.noUiSlider.on('update', function (values, handle) {
        inputs[handle].setAttribute('value', parseInt(values[handle], 10));
        inputs[handle].value=parseInt(values[handle], 10);
    });

    inputs.forEach(function (input, handle) {
        input.addEventListener('change', function () {
            stepsSlider.noUiSlider.setHandle(handle, this.value);
        });
        input.addEventListener('keydown', function (e) {
            var values = stepsSlider.noUiSlider.get();
            var value = Number(values[handle]);
            var steps = stepsSlider.noUiSlider.steps();
            var step = steps[handle];
            var position;
            switch (e.which) {
                case 13:
                    stepsSlider.noUiSlider.setHandle(handle, this.value);
                    break;

                case 38:
                    position = step[1];
                    if (position === false)
                        position = 1;
                    if (position !== null)
                        stepsSlider.noUiSlider.setHandle(handle, value + position);
                    break;

                case 40:
                    position = step[0];
                    if (position === false)
                        position = 1;
                    if (position !== null)
                        stepsSlider.noUiSlider.setHandle(handle, value - position);
                    break;
            }
        });
    });
</script>
