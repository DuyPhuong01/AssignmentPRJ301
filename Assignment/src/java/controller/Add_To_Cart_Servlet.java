
package controller;

import dal.OrderDAO;
import dal.ProductDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Item;

/**
 *
 * @author Duy Phuong
 */
@WebServlet(name = "Add_To_Cart_Servlet", urlPatterns = {"/addtocart"})
public class Add_To_Cart_Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO p_dao = new ProductDAO();
        OrderDAO o_dao = new OrderDAO();
        String id_raw = request.getParameter("productID");
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
        try {
            int productID = Integer.parseInt(id_raw);
            int orderID = o_dao.getOrderID(user_cookie.getValue());
            Item  i = o_dao.getItemsByID(productID, orderID);
            if(i!=null) {
                o_dao.updateItemsQuantity(i.getQuantity()+1, productID, orderID);
            }
            else{
                Item item = new Item(p_dao.getProductById(productID), 1, o_dao.getOrderID(user_cookie.getValue()));
                o_dao.addItemToCart(item, user_cookie.getValue());
            }
            response.sendRedirect("mycart");
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
