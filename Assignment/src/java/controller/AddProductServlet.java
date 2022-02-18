/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.DAO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Product;

/**
 *
 * @author Duy Phuong
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,
    maxFileSize = 1024 * 1024 * 50,
    maxRequestSize = 1024 * 1024 * 50)
@WebServlet(name = "AddProductServlet", urlPatterns = {"/addproduct"})
public class AddProductServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddProductServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddProductServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        request.setAttribute("categoryList", dao.getAll());
        request.setAttribute("brandList", dao.getAllBrand());
        request.getRequestDispatcher("addproduct.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        /* get Product information */        
        String categoryID_raw = request.getParameter("categoryID");
        String brandID_raw = request.getParameter("brandID");
        String name = request.getParameter("name");
        String price_raw = request.getParameter("price");
        String activate_raw = request.getParameter("activate");
        
        try {
            int categoryID = Integer.parseInt(categoryID_raw);
            int brandID = Integer.parseInt(brandID_raw);
            double price = Double.parseDouble(price_raw);
            int activate = Integer.parseInt(activate_raw);
            DAO dao = new DAO();
            
            Product p = new Product();
            p.setBrandID(brandID);
            p.setProductName(name);
            p.setPrice(price);
            p.setStatus(activate);
            int productID = dao.addProduct(p, categoryID);
            /* upload product image */
            uploadFile(request, response, productID);
            response.sendRedirect("main");
        } catch(NumberFormatException e) {
            System.out.println(e);
        }
    }
    private void uploadFile(HttpServletRequest request, HttpServletResponse response,
            int productID)
            throws IOException, ServletException{
        String path = getFolderUploadPath();
        Part filePart = request.getPart("productPhoto");
        String fileType = getFileType(filePart);

        OutputStream out = null;
        InputStream filecontent = null;

        try {
            File f = new File(path + File.separator + "product-" + productID + fileType);
            System.out.println(path + File.separator + "product-" + productID + fileType);
            out = new FileOutputStream(f);
            filecontent = filePart.getInputStream();
            int read = 0;
            final byte[] bytes = new byte[1024];
            while ((read = filecontent.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            System.out.println("New file created at " + path);
        } catch (FileNotFoundException fne) {
            System.out.println("<br/> ERROR: " + fne.getMessage());
        } finally {
            if (out != null) out.close();
            if (filecontent != null) filecontent.close();
        }
    }
    
    private String getFileType(final Part part) {
        String content = part.getHeader("content-disposition");
        return content.substring(content.indexOf('.')).trim().replace("\"", "");
    }
    public String getFolderUploadPath() {
      String path = getServletContext().getRealPath("/") + "images";
      File folderUpload = new File(path);
      if (!folderUpload.exists()) {
        folderUpload.mkdirs();
      }
      return path;
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
