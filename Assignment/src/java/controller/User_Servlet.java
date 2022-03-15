
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
@WebServlet(name = "User_Servlet", urlPatterns = {"/user"})
public class User_Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        /* */
        UserDAO u_dao = new UserDAO();
        int userID;
                
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
        
        try {
            if(action == null) action = "details";
            switch(action) {
                case "update":
                    userID = Integer.parseInt(request.getParameter("id"));
                    request.setAttribute("user", u_dao.getUser(userID));
                    request.getRequestDispatcher("user-update.jsp").forward(request, response);
                    break;

                case "delete":
                    userID = Integer.parseInt(request.getParameter("id"));
                    u_dao.deleteUser(userID);
                    response.sendRedirect("admin?action=user");
                    break;
                    
                case "setadmin":
                    userID = Integer.parseInt(request.getParameter("id"));
                    u_dao.setAdmin(userID);
                    response.sendRedirect("admin?action=user");
                    break;

                default:
                    break;
            }
        } catch(NumberFormatException nfe) {
            System.out.println(nfe);
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
                
            case "update":
                String userID_raw = request.getParameter("userID");
                String fullname = request.getParameter("fullname");
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String country = request.getParameter("country");
                String city = request.getParameter("city");
                String address = request.getParameter("address");
                String phone = request.getParameter("phone");
                int userID = Integer.parseInt(userID_raw);
                
                User u = new User(userID, username, password, 0, fullname, city, country, address, phone);
                
                u_dao.updateUser(u, userID);
                request.setAttribute("user", u_dao.getUser(username));
                response.sendRedirect("admin?action=user");
                break;
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
