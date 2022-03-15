
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
@WebServlet(name = "Account_Servlet", urlPatterns = {"/account"})
public class Account_Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* */
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        UserDAO u_dao = new UserDAO();
        
        String action = request.getParameter("action");
        Cookie[] cookies = request.getCookies();
        Cookie user_cookie = null;
        if (cookies != null)
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("userAccount")) user_cookie = cookie;
            }
        
        if(user_cookie == null){
            response.sendRedirect("signin");
            return;
        }
        String[] userAccount = user_cookie.getValue().split(",");
        
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
                request.setAttribute("user", u_dao.getUser(userAccount[1]));
                request.setAttribute("page", "account-details");
                request.getRequestDispatcher("account.jsp").forward(request, response);
                break;
                
            case "setting":
                request.setAttribute("page", "account-setting");
                request.getRequestDispatcher("account.jsp").forward(request, response);
                break;
                
            case "changeusernameandpassword":
                request.setAttribute("user", u_dao.getUser(userAccount[1]));
                request.getRequestDispatcher("account-change.jsp").forward(request, response);
                break;
                
            case "update":
                request.setAttribute("user", u_dao.getUser(userAccount[1]));
                request.getRequestDispatcher("account-update.jsp").forward(request, response);
                break;
                
            case "delete":
                try {
                    int userID = Integer.parseInt(userAccount[0]);
                    u_dao.deleteUser(userID);
                } catch(NumberFormatException nfe) {
                    System.out.println(nfe);
                }
                response.sendRedirect("account?action=signout");
                break;
                
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        UserDAO u_dao = new UserDAO();
        
        String action = request.getParameter("action");
        
        Cookie[] cookies = request.getCookies();
        Cookie user_cookie = null;
        if (cookies != null)
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("userAccount")) user_cookie = cookie;
            }
        
        if(user_cookie == null){
            response.sendRedirect("signin");
            return;
        }
        String[] userAccount = user_cookie.getValue().split(",");
        
        if(action == null) action = "details";
        switch(action) {
            case "changeusernameandpassword":
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                int userID = Integer.parseInt(userAccount[0]);
                if(u_dao.updateUser(username, password, userID)){
                    Cookie userAccount_cookie = new Cookie("userAccount", userID+ "," + username);
                    userAccount_cookie.setMaxAge(24*60*60);
                    response.addCookie(userAccount_cookie);
                    request.setAttribute("alert", "Change information successful!");
                }
                else request.setAttribute("error", "Change information fail!");
                request.setAttribute("user", u_dao.getUser(username));
                request.getRequestDispatcher("account-change.jsp").forward(request, response);
                break;
                
            case "update":
                String fullname = request.getParameter("fullname");
                String country = request.getParameter("country");
                String city = request.getParameter("city");
                String address = request.getParameter("address");
                String phone = request.getParameter("phone");
                System.out.println(city);
                
                User u = new User(userAccount[1], fullname, city, country, address, phone);
                if(u_dao.updateUserInformation(u)) request.setAttribute("alert", "Change information successful!");
                else request.setAttribute("error", "Change information fail!");
                request.setAttribute("user", u_dao.getUser(userAccount[1]));
                request.getRequestDispatcher("account-update.jsp").forward(request, response);
                break;
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
