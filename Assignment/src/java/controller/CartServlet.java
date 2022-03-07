
package controller;

import dal.OrderDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Duy Phuong
 */
@WebServlet(name = "CartServlet", urlPatterns = {"/mycart"})
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        OrderDAO o_dao = new OrderDAO();
        
        Cookie[] cookies = request.getCookies();
        Cookie user_cookie = null;
        if (cookies != null)
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals("userAccount")) user_cookie = cookie;
            }
        if(user_cookie==null) response.sendRedirect("account?action=login");
        String[] userAccount = user_cookie.getValue().split("|");
        try {
            int userID = Integer.parseInt(userAccount[0]);
            request.setAttribute("list", o_dao.getCart(userID).getList());
            request.getRequestDispatcher("cart.jsp").forward(request, response);
        } catch(NumberFormatException nfe) {
            System.out.println(nfe);
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
