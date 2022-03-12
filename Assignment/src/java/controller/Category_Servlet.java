
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
        System.out.println(action);
        
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
                    response.sendRedirect("admin?action=category");
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
        String name, describe, activate_raw, categoryID_raw;
        String action = request.getParameter("action");
        
        if(action==null) action = "view";
        switch(action) {
            case "create":
                name = request.getParameter("name");
                describe = request.getParameter("describe");
                activate_raw = request.getParameter("activate");
                try {
                    int activate = Integer.parseInt(activate_raw);
                    Category c = new Category(1, name, describe, activate);
                    c_dao.addCategory(c);
                    response.sendRedirect("admin?action=category");
                } catch(NumberFormatException e) {
                    System.out.println(e);
                }
                break;
                
            case "update":
                categoryID_raw = request.getParameter("categoryID");
                name = request.getParameter("name");
                describe = request.getParameter("describe");
                activate_raw = request.getParameter("activate");
                try {
                    int activate = Integer.parseInt(activate_raw);
                    int categoryID = Integer.parseInt(categoryID_raw);
                    Category c = new Category(categoryID, name, describe, activate);
                    c_dao.updateCategory(c);
                    response.sendRedirect("admin?action=category");
                } catch(NumberFormatException e) {
                    System.out.println(e);
                }
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
