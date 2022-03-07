
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
@WebServlet(name = "Sign_In_Servlet", urlPatterns = {"/signin"})
public class Sign_In_Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("alert", request.getParameter("alert"));
        request.getRequestDispatcher("signin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO u_dao = new UserDAO();
        /* Get sign in information */
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        /* Get sign in */
        User user = u_dao.getUser(username, password);
        if(user != null) {
            Cookie userAccount_cookie = new Cookie("userAccount", user.getUserID() + "," + username);
            userAccount_cookie.setMaxAge(24*60*60);
            response.addCookie(userAccount_cookie);
            
            Cookie userRole_cookie = new Cookie("userRole", "" + user.getRole());
            userRole_cookie.setMaxAge(24*60*60);
            response.addCookie(userRole_cookie);
            response.sendRedirect("home");
        } else {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("signin.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
