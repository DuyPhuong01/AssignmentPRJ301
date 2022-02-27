
package controller;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Duy Phuong
 */
public class AccountServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SignInServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignInServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if((action.equals("signout"))){
            Cookie[] cookies = request.getCookies();
            if (cookies != null)
                for (Cookie cookie : cookies) {
                    if(cookie.getName().equals("userAccount") || cookie.getName().equals("userRole")){
                        cookie.setValue("");
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                    }
                }
            response.sendRedirect("home");
        } else if((action.equals("details"))){
            Cookie user_cookie = null;
            Cookie[] cookies = request.getCookies();
            if (cookies != null)
                for (Cookie cookie : cookies) {
                    if(cookie.getName().equals("userAccount")) user_cookie = cookie;
                }
            DAO dao = new DAO();
            request.setAttribute("user", dao.getUser(user_cookie.getValue()));
            request.setAttribute("page", "account-details");
            request.getRequestDispatcher("account.jsp").forward(request, response);
        } else if((action.equals("setting"))){
            request.setAttribute("page", "account-setting");
            request.getRequestDispatcher("account.jsp").forward(request, response);
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
