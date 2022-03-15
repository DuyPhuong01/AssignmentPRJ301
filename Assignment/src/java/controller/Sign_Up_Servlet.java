
package controller;

import dal.UserDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author Duy Phuong
 */
@WebServlet(name = "Sign_Up_Servlet", urlPatterns = {"/signup"})
public class Sign_Up_Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("signup.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        UserDAO u_dao = new UserDAO();
        
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String city = request.getParameter("city");
        String country = request.getParameter("country");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        /* Check username */
        if(u_dao.getUser(username) != null) {
            request.setAttribute("alert", "Username already exist");
            request.getRequestDispatcher("signup.jsp").forward(request, response);
        }
        else {
            User signup_user = new User(0, username, password, 0, firstname + " " + lastname, 
                    city, country, address, phone);
            u_dao.createUser(signup_user);
            request.setAttribute("alert", "Create account successful! Please Sign In");
            request.getRequestDispatcher("signin").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
