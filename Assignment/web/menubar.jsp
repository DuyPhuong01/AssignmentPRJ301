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
    <ul class="categories">
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
            <ul class="option-list"><span>Brands</span>
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
        Price
        <div class="row price-range">
            <div class="col-6"><input type="number" id="input-with-keypress-3" name="min" autocomplete="off" placeholder="Min" value="0"></div>
            <div class="col-6"><input type="number" id="input-with-keypress-4" name="max" autocomplete="off" placeholder="Max" value="300"></div>
        </div>
        <button type="submit" disabled style="display: none" aria-hidden="true"></button>
        <input class="filter-button" type="button" onclick="submitFilter()" value="Filter">
    </form>
</div>