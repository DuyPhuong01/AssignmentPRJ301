
package controller;

import dal.DAO;
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
@WebServlet(name = "UpdateServlet", urlPatterns = {"/update"})
public class UpdateServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UpdateServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String target = request.getParameter("target");
        String id_raw = request.getParameter("id");
        DAO dao = new DAO();
        try {
            int id = Integer.parseInt(id_raw);
            if (target.equals("product")) {
                request.setAttribute("product", dao.getProductById(id));
                request.setAttribute("categoryList", dao.getAllCategory());
                request.setAttribute("brandList", dao.getAllBrand());
                request.getRequestDispatcher("product-update.jsp").forward(request, response);
            }
        } catch(NumberFormatException e) {
            System.out.println(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String target = request.getParameter("target");
        DAO dao = new DAO();
        if (target.equals("product")) {
            String categoryID_raw = request.getParameter("categoryID");
            String brandID_raw = request.getParameter("brandID");
            String name = request.getParameter("name");
            String price_raw = request.getParameter("price");
            String quantity_raw = request.getParameter("quantity");
            String activate_raw = request.getParameter("activate");
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
