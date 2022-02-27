
package controller;

import dal.DAO;
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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Admin</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Admin at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        DAO dao = new DAO();
        Cookie user_cookie = null;
        Cookie[] cookies = request.getCookies();
        if( cookies != null ){
            for (Cookie cookie : cookies) {
                    if(cookie.getName().equals("userAccount")) user_cookie = cookie;
                }
        }
        User u = dao.getUser(user_cookie.getValue());
        if(u.getUsername()!=null && u.getRole()==1) {
            if(action == null) {
                request.setAttribute("productList", dao.getAllProduct());
                request.setAttribute("page", "product-manager");
            }
            else switch (action) {
                case "product":
                        request.setAttribute("productList", dao.getAllProduct());
                        request.setAttribute("page", "product-manager");
                        break;
                case "category":
                        request.setAttribute("categoryList", dao.getAllCategory());
                        request.setAttribute("page", "category-manager");
                        break;
                case "brand":
                        request.setAttribute("brandList", dao.getAllBrand());
                        request.setAttribute("page", "brand-manager");
                        break;
                case "user":
                        request.setAttribute("userList", dao.getAllUser());
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
        processRequest(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
