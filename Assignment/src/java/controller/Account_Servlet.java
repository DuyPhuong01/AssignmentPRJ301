
package controller;

import dal.UserDAO;
import java.io.IOException;
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
@WebServlet(name = "AccountServlet", urlPatterns = {"/account"})
public class Account_Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* */
        UserDAO u_dao = new UserDAO();
        
        String action = request.getParameter("action");
        Cookie[] cookies = request.getCookies();
        
        if(action == null) action = "details";
        switch(action) {
                
            case "signout":
                if (cookies != null)
                    for (Cookie cookie : cookies) {
                        if(cookie.getName().equals("userAccount") || cookie.getName().equals("userRole")){
                            cookie.setValue("");
                            cookie.setMaxAge(0);
                            response.addCookie(cookie);
                        }
                    }
                response.sendRedirect("home");
                break;
                
            case "details":
                Cookie user_cookie = null;
                if (cookies != null)
                    for (Cookie cookie : cookies) {
                        if(cookie.getName().equals("userAccount")) user_cookie = cookie;
                    }
                String[] userAccount = user_cookie.getValue().split("|");
                request.setAttribute("user", u_dao.getUser(userAccount[0]));
                request.setAttribute("page", "account-details");
                request.getRequestDispatcher("account.jsp").forward(request, response);
                break;
                
            case "setting":
                request.setAttribute("page", "account-setting");
                request.getRequestDispatcher("account.jsp").forward(request, response);
                break;
                
            case "delete":
                String username = request.getParameter("username");
                u_dao.deleteUser(username);
                System.out.println("Account with username=" + username + "was deleted.");
                response.sendRedirect("admin?action=user");
                break;
                
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if(action == null) action = "details";
        switch(action) {
                
            default:
                break;
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
