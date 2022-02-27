
package controller;

import dal.DAO;
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
@WebServlet(name = "SignInServlet", urlPatterns = {"/signin"})
public class SignInServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("signin.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User u = dao.signInCheck(username, password);
        if(u != null) {
            Cookie cookie1 = new Cookie("userAccount", username);
            Cookie cookie2 = new Cookie("userRole", ""+u.getRole());
            cookie1.setMaxAge(24*60*60);
            cookie2.setMaxAge(24*60*60);
            response.addCookie(cookie1);
            response.addCookie(cookie2);
            response.sendRedirect("home");
        } else {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("signin.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
