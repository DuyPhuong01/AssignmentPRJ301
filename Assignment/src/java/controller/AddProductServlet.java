
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
@MultipartConfig
@WebServlet(name = "AddProductServlet", urlPatterns = {"/addproduct"})
public class AddProductServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        request.setAttribute("categoryList", dao.getAll());
        request.setAttribute("brandList", dao.getAllBrand());
        request.getRequestDispatcher("addproduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* declaration */
        DAO dao = new DAO();
        int productID = 0;
        /* get Product information */        
        String categoryID_raw = request.getParameter("categoryID");
        String brandID_raw = request.getParameter("brandID");
        String name = request.getParameter("name");
        String price_raw = request.getParameter("price");
        String quantity_raw = request.getParameter("quantity");
        Part filePart = request.getPart("productPhoto");
        String activate_raw = request.getParameter("activate");
        
        try {
            int categoryID = Integer.parseInt(categoryID_raw);
            int brandID = Integer.parseInt(brandID_raw);
            double price = Double.parseDouble(price_raw);
            int quantity = Integer.parseInt(quantity_raw);
            String fileName = getFileName(filePart);
            int activate = Integer.parseInt(activate_raw);
            
            /* add product to database*/
            Product p = new Product(name, brandID, price, quantity, fileName, activate);
            System.out.println(p + " are creating");
            productID = dao.addProduct(p, categoryID);
            if(productID > 0) {
                System.out.println(p + "created");

                /* save product iamge */
                String filePath = getFolderUploadPath() + File.separator +  fileName;
                File f = new File(filePath);
                OutputStream out = new FileOutputStream(f);
                InputStream filecontent = filePart.getInputStream();
                int read = 0;
                byte[] bytes = new byte[1024];
                while ((read = filecontent.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                System.out.println("New file created at " + filePath);
                out.close();
            
                filecontent.close();
            }

            response.sendRedirect("main");
        } catch(NumberFormatException nfe) {
            System.out.println(nfe);
        } catch (FileNotFoundException fne) {
            System.out.println("<br/> ERROR: " + fne.getMessage());
            dao.deleteProduct(productID);
        }
    }
    private String getFileName(final Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
    
    public String getFolderUploadPath() {
      String path = getServletContext().getRealPath("/") + "images";
      File folderUpload = new File(path);
      if (!folderUpload.exists()) {
        folderUpload.mkdirs();
      }
      return path;
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
