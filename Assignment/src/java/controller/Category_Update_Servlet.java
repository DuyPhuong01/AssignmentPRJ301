
package controller;

import dal.DAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Category;

/**
 *
 * @author Duy Phuong
 */
@WebServlet(name = "Category_Update_Servlet", urlPatterns = {"/updatecategory"})
public class Category_Update_Servlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id_raw = request.getParameter("id");
        DAO dao = new DAO();
        try {
            int id = Integer.parseInt(id_raw);
            request.setAttribute("category", dao.getCategoryById(id));
            request.getRequestDispatcher("category-update.jsp").forward(request, response);
        } catch(NumberFormatException e) {
            System.out.println(e);
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id_raw = request.getParameter("categoryID");
        String name = request.getParameter("name");
        String describe = request.getParameter("describe");
        String activate_raw = request.getParameter("activate");
        try {
            int id = Integer.parseInt(id_raw);
            int activate = Integer.parseInt(activate_raw);
            DAO dao = new DAO();
            Category c = new Category(id, name, describe, activate);
            dao.updateCategory(c);
            response.sendRedirect("main");
        } catch(NumberFormatException e) {
            System.out.println(e);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
