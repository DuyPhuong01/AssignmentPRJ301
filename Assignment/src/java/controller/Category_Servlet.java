
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
@WebServlet(name = "Category_Servlet", urlPatterns = {"/category"})
public class Category_Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoryDAO c_dao = new CategoryDAO();
        
        String action = request.getParameter("action");
        
        if(action==null) action = "view";
        switch(action) {
            case "create":
                request.getRequestDispatcher("category-create.jsp").forward(request, response);
                break;
                
            case "update":
                String id_raw = request.getParameter("id");
                try {
                    int id = Integer.parseInt(id_raw);
                    request.setAttribute("category", c_dao.getCategoryById(id));
                    request.getRequestDispatcher("category-update.jsp").forward(request, response);
                } catch(NumberFormatException e) {
                    System.out.println(e);
                }
                break;
                
            case "delete":
                String delete_id_raw = request.getParameter("id");
                try {
                    int id = Integer.parseInt(delete_id_raw);
                    c_dao.deleteCategory(id);
                    response.sendRedirect("main");
                } catch(NumberFormatException e) {
                    System.out.println(e);
                }
                break;
                
            default :
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CategoryDAO c_dao = new CategoryDAO();
        
        String action = request.getParameter("action");
        
        if(action==null) action = "view";
        switch(action) {
            case "create":
                String name = request.getParameter("name");
                String describe = request.getParameter("describe");
                String activate_raw = request.getParameter("activate");
                try {
                    int activate = Integer.parseInt(activate_raw);
                    Category c = new Category(1, name, describe, activate);
                    c_dao.addCategory(c);
                    response.sendRedirect("main");
                } catch(NumberFormatException e) {
                    System.out.println(e);
                }
                break;
                
            case "update":
                break;

            default :
                break;
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
