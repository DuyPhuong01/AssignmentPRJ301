
package controller;

import dal.CategoryDAO;
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
            CategoryDAO c_dao = new CategoryDAO();
            Category c = new Category(1, name, describe, activate);
            c_dao.addCategory(c);
            response.sendRedirect("main");
        } catch(NumberFormatException e) {
            System.out.println(e);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
