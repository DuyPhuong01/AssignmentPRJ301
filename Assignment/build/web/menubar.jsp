<%-- 
    Document   : sidebar
    Author     : Duy Phuong
--%>

<%@page import="model.Category"%>
<%@page import="model.Brand"%>
<%@page import="java.util.List"%>
<%@page import="dal.DAO"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div id="menubar">
    <ul>
    <%
        DAO dao = new DAO();
        List<Category> categoryList = dao.getAllCategory();
        for (Category category : categoryList) {
    %>
            <li><a href="product?categoryID=<%= category.getCategoryID() %>"><%= category.getCategoryName() %></a></li>
    <%
        }
    %>
    </ul>
        <form action="product">
            <ul><span>Brands</span>
            <%
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
                                <img class="flexible-img" width="20px" src="images/<%= b.getBrandLogo() %>"><span><%= b.getBrandName() %></span>
                            </label>
                        </li>
            <%
                    }
                }
            %>
        </ul>
        <script>
            var url = window.location.href;
            var brands = document.querySelectorAll('input[name="brand"]');
            brands.forEach((b) => {
                if(url.includes('brand='+b.value))
                    b.setAttribute('checked', true);
            })
            
        </script>
        <hr>
        Price:
        <div id="slider2"></div>
        <div class="display-slider">
            <div><input type="text" id="input-with-keypress-3" name="min"></div>
            <span>-</span>
            <div><input type="text" id="input-with-keypress-4" name="max"></div>
        </div>
        <button type="submit" disabled style="display: none" aria-hidden="true"></button>
        <input class="filter-button" type="button" onclick="submitFilter()" value="Filter">
    </form>
</div>
<script>
    var stepsSlider = document.getElementById('slider2');
    var input0 = document.getElementById('input-with-keypress-3');
    var input1 = document.getElementById('input-with-keypress-4');
    var inputs = [input0, input1];

    noUiSlider.create(stepsSlider, {
        start: [${requestScope.min}, ${requestScope.max}],
        connect: true,
        step: 1,
        range: {
            'min': 0,
            'max': 300
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
