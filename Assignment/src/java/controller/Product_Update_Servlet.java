/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.DAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;

/**
 *
 * @author Duy Phuong
 */
@MultipartConfig
@WebServlet(name = "Product_Update_Servlet", urlPatterns = {"/updateproduct"})
public class Product_Update_Servlet extends HttpServlet {
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id_raw = request.getParameter("id");
        DAO dao = new DAO();
        try {
            int id = Integer.parseInt(id_raw);
            request.setAttribute("product", dao.getProductById(id));
            request.setAttribute("categoryList", dao.getAllCategory());
            request.setAttribute("brandList", dao.getAllBrand());
            request.getRequestDispatcher("product-update.jsp").forward(request, response);
        } catch(NumberFormatException e) {
            System.out.println(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        
        /* get Product information */   
        String productID_raw = request.getParameter("productID");
        String categoryID_raw = request.getParameter("categoryID");
        String brandID_raw = request.getParameter("brandID");
        String name = request.getParameter("name");
        String price_raw = request.getParameter("price");
        String quantity_raw = request.getParameter("quantity");
        String activate_raw = request.getParameter("activate");
        
        try {
            int productID = Integer.parseInt(productID_raw);
            int categoryID = Integer.parseInt(categoryID_raw);
            int brandID = Integer.parseInt(brandID_raw);
            double price = Double.parseDouble(price_raw);
            int quantity = Integer.parseInt(quantity_raw);
            int activate = Integer.parseInt(activate_raw);
            
            /* update product to database*/
            Product p = new Product(productID, name, brandID, price, quantity, "", activate);
            System.out.println(p + " are updating");
            dao.updateProduct(p, categoryID);
            response.sendRedirect("admin");
        } catch(NumberFormatException nfe) {
            System.out.println(nfe);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
