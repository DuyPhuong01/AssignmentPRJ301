
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
            case "signin":
                request.getRequestDispatcher("signin.jsp").forward(request, response);
                break;
                
            case "signup":
                request.getRequestDispatcher("signup.jsp").forward(request, response);
                break;
                
            case "signout":
                if (cookies != null)
                    for (Cookie cookie : cookies) {
                        if(cookie.getName().equals("userAccount")){
                            cookie.setValue("");
                            cookie.setMaxAge(0);
                            response.addCookie(cookie);
                            break;
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
        UserDAO u_dao = new UserDAO();
                
        String action = request.getParameter("action");
        
        if(action == null) action = "details";
        switch(action) {
            case "signin":
                /* Get sign in information */
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                
                /* Get sign in */
                User user = u_dao.getUser(username, password);
                if(user != null) {
                    Cookie user_cookie = new Cookie("userAccount", user.getUserID() + "|" + username + "|" + user.getRole());
                    user_cookie.setMaxAge(24*60*60);
                    response.addCookie(user_cookie);
                    response.sendRedirect("home");
                } else {
                    request.setAttribute("error", "Invalid username or password");
                    request.getRequestDispatcher("signin.jsp").forward(request, response);
                }
                break;
                
            case "signup":
                /* Get user sign up information */
                String signup_firstname = request.getParameter("firstname");
                String signup_lastname = request.getParameter("lastname");
                String signup_username = request.getParameter("username");
                String signup_password = request.getParameter("password");
                String city = request.getParameter("city");
                String country = request.getParameter("country");
                String address = request.getParameter("address");
                String phone = request.getParameter("phone");
                
                /* Check username */
                if(u_dao.getUser(signup_username) != null) {
                    request.setAttribute("alert", "Username already exist");
                    request.getRequestDispatcher("signup.jsp").forward(request, response);
                }
                else {
                    User signup_user = new User(0, signup_username, signup_password, 0, signup_firstname + " " + signup_lastname, 
                            city, country, address, phone);
                    u_dao.createUser(signup_user);
                    request.setAttribute("alert", "Create account successful! Please Sign In");
                    request.getRequestDispatcher("signin.jsp").forward(request, response);
                }
                break;
                
            default:
                break;
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
