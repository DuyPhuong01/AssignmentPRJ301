
package controller;

import dal.BrandDAO;
import dal.CategoryDAO;
import dal.ProductDAO;
import dal.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author Duy Phuong
 */
@WebServlet(name = "Admin", urlPatterns = {"/admin"})
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO p_dao = new ProductDAO();
        CategoryDAO c_dao = new CategoryDAO();
        BrandDAO b_dao = new BrandDAO();
        UserDAO u_dao = new UserDAO();
        
        String action = request.getParameter("action");
        Cookie user_cookie = null;
        
        Cookie[] cookies = request.getCookies();
        if( cookies != null ){
            for (Cookie cookie : cookies) {
                    if(cookie.getName().equals("userAccount")) user_cookie = cookie;
                }
        }
        String[] userAccount = user_cookie.getValue().split("|");
        
        if(user_cookie!=null && userAccount[2].equals("1")) {
            if(action == null) action = "product";
            else switch (action) {
                case "product":
                        request.setAttribute("productList", p_dao.getAllProduct());
                        request.setAttribute("page", "product-manager");
                        break;
                case "category":
                        request.setAttribute("categoryList", c_dao.getAllCategory());
                        request.setAttribute("page", "category-manager");
                        break;
                case "brand":
                        request.setAttribute("brandList", b_dao.getAllBrand());
                        request.setAttribute("page", "brand-manager");
                        break;
                case "user":
                        request.setAttribute("userList", u_dao.getAllUser());
                        request.setAttribute("page", "user-manager");
                        break;
                default:
                    break;
            }
            request.getRequestDispatcher("admin.jsp").forward(request, response);
        } else {    
            PrintWriter out = response.getWriter();
            out.println("access denied");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
