
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
@WebServlet(name = "Cart_Servlet", urlPatterns = {"/mycart"})
public class Cart_Servlet extends HttpServlet {

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
        if(user_cookie==null){
            response.sendRedirect("signin");
            return;
        }
        String[] userAccount = user_cookie.getValue().split(",");
        
        String action = request.getParameter("action");
        if(action==null) action = "showlist";
        try {
            int userID = Integer.parseInt(userAccount[0]);
            switch(action) {
                case "showlist":
                        request.setAttribute("list", o_dao.getCart(userID).getList());
                        request.getRequestDispatcher("cart.jsp").forward(request, response);
                    break;

                case "remove":
                    String[] productID_raw = request.getParameterValues("chooseitems");
                    int size = productID_raw==null ? 0 : productID_raw.length;
                    int[] productID = new int[size];
                    for(int i=0; i<size; i++)
                        productID[i] = Integer.parseInt(productID_raw[i]);
                    o_dao.removeFromCart(productID, userID);
                    request.setAttribute("list", o_dao.getCart(userID).getList());
                    request.getRequestDispatcher("cart.jsp").forward(request, response);
                    break;
                    
                case "clear":
                    o_dao.removeAllFromCart(userID);
                    request.setAttribute("list", o_dao.getCart(userID).getList());
                    response.sendRedirect("mycart");
                    break;
                    
                case "updatequantity":
                    int productid = Integer.parseInt(request.getParameter("productid"));
                    int quantity = Integer.parseInt(request.getParameter("quantity"));
                    o_dao.updateQuantity(productid, quantity, userID);
                    response.sendRedirect("mycart");
                    break;
                    
                case "buy":
                    String[] productid_raw_list = request.getParameterValues("productid");
                    String[] quantity_raw_list = request.getParameterValues("quantity");
                    int[] productid_list = new int[productid_raw_list.length];
                    int[] quantity_list = new int[productid_raw_list.length];
                    for(int i=0; i<productid_raw_list.length; i++) {
                        productid_list[i] = Integer.parseInt(productid_raw_list[i]);
                        quantity_list[i] = Integer.parseInt(quantity_raw_list[i]);
                    }
                    o_dao.buy(productid_list, quantity_list, userID);
                    response.sendRedirect("mycart");
                    break;
                    
                case "history":
                    request.setAttribute("total_spend", o_dao.getTotalSpend(userID));
                    request.setAttribute("history_cart", o_dao.getHistory(userID));
                    request.getRequestDispatcher("cart-history.jsp").forward(request, response);
                    break;
            }
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
