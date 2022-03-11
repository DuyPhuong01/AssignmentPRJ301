
package controller;

import model.Category;
import dal.CategoryDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Duy Phuong
 */
@WebServlet(name = "Home_Servlet", urlPatterns = {"/home"})
public class Home_Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO p_dao = new ProductDAO();
        CategoryDAO c_dao = new CategoryDAO();
        List<Category> c_list = c_dao.getAllCategory();
        
        request.setAttribute("bestseller_productlist", p_dao.getBestSellerProduct());
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
