<%-- 
    Document   : sidebar
    Author     : Duy Phuong
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="option-list col-2">
    <c:set var="min" value="10"></c:set>
    <c:set var="max" value="100"></c:set>
    <form action="product" onkeydown="return event.key != 'Enter';">
        <ul><span>Brands</span>
            <c:if test="${requestScope.brandList == '[]'}"><li name="brand">Empty</li></c:if>
            <c:forEach items="${requestScope.brandList}" var="b">
                <li><input type="checkbox" name="brand" value="${b.brandID}">${b.brandName}</li>
            </c:forEach>
        </ul>
        Price:
        <div id="slider"></div>
        <div class="display-slider">
            <div><input type="number" id="input-with-keypress-0" name="min"></div>
            <span>-</span>
            <div><input type="number" id="input-with-keypress-1" name="max"></div>
        </div>
        <input class="filter-button" type="submit" value="Filter">
    </form>
</div>
<script src="libraries/nouislider.js"></script>
<script>
    var stepsSlider = document.getElementById('slider');
    var input0 = document.getElementById('input-with-keypress-0');
    var input1 = document.getElementById('input-with-keypress-1');
    var inputs = [input0, input1];

    noUiSlider.create(stepsSlider, {
        start: [0, 200],
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

            // [[handle0_down, handle0_up], [handle1_down, handle1_up]]
            var steps = stepsSlider.noUiSlider.steps();

            // [down, up]
            var step = steps[handle];

            var position;

            // 13 is enter,
            // 38 is key up,
            // 40 is key down.
            switch (e.which) {

                case 13:
                    stepsSlider.noUiSlider.setHandle(handle, this.value);
                    break;

                case 38:

                    // Get step to go increase slider value (up)
                    position = step[1];

                    // false = no step is set
                    if (position === false) {
                        position = 1;
                    }

                    // null = edge of slider
                    if (position !== null) {
                        stepsSlider.noUiSlider.setHandle(handle, value + position);
                    }

                    break;

                case 40:

                    position = step[0];

                    if (position === false) {
                        position = 1;
                    }

                    if (position !== null) {
                        stepsSlider.noUiSlider.setHandle(handle, value - position);
                    }

                    break;
            }
        });
    });
</script>
