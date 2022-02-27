
package controller;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Cart;
import model.Item;
import model.User;

/**
 *
 * @author Duy Phuong
 */
@WebServlet(name = "Add_To_Cart_Servlet", urlPatterns = {"/addtocart"})
public class Add_To_Cart_Servlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Add_To_Cart_Servlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Add_To_Cart_Servlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id_raw = request.getParameter("productID");
        DAO dao = new DAO();
        try {
            int productID = Integer.parseInt(id_raw);
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("userAccount");
            if(user==null){
                response.sendRedirect("signin");
                return;
            }
            Item item = new Item(dao.getProductById(productID), 1, dao.getOrderID(user.getUsername()));
            dao.addItemToCart(item, user.getUsername());
            response.sendRedirect("mycart");
        } catch(NumberFormatException nfe) {
            System.out.println(nfe);
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
