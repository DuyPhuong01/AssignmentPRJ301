
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
@WebServlet(name = "AddCategoryServlet", urlPatterns = {"/createcategory"})
public class Category_Create_Servlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("category-create.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String describe = request.getParameter("describe");
        String activate_raw = request.getParameter("activate");
        try {
            int activate = Integer.parseInt(activate_raw);
            DAO dao = new DAO();
            Category c = new Category(1, name, describe, activate);
            dao.addCategory(c);
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
