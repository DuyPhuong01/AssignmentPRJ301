
package controller;

import dal.DAO;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Duy Phuong
 */
@WebServlet(name = "DeleteProductServlet", urlPatterns = {"/deleteproduct"})
public class DeleteProductServlet extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DeleteProductServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteProductServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id_raw = request.getParameter("id");
        System.out.println(id_raw);
        DAO dao = new DAO();
        try {
            int id = Integer.parseInt(id_raw);
            String fileName = dao.getProductById(id).getImage();
            dao.deleteProduct(id);
            File f = new File(getFolderUploadPath() + File.separator +  fileName);
            if(f.exists()){
                f.delete();
            }
            response.sendRedirect("product");
        } catch(NumberFormatException e) {
            System.out.println(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
