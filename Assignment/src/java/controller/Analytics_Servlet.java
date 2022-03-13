
package controller;

import dal.OrderDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Duy Phuong
 */
@WebServlet(name = "Analytics_Servlet", urlPatterns = {"/analytics"})
public class Analytics_Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO p_dao = new ProductDAO();
        
        String action = request.getParameter("action");
        if(action==null) action="revenue";
        
        switch(action) {
            case "revenue":
                OrderDAO o_dao = new OrderDAO();
                List<List> list = o_dao.getRevenue();
                request.setAttribute("date", list.get(0));
                request.setAttribute("revenue", list.get(1));
                request.setAttribute("page", "revenue");
                request.getRequestDispatcher("analytics.jsp").forward(request, response);
                break;
                
            case "bestseller":
                request.setAttribute("bestseller_productlist", p_dao.getBestSellerProduct());
                request.setAttribute("page", "bestseller");
                request.getRequestDispatcher("analytics.jsp").forward(request, response);
                break;
                
            case "mostfavorite":
                request.setAttribute("mostfavorite_productlist", p_dao.getMostFavoriteProduct());
                request.setAttribute("page", "mostfavorite");
                request.getRequestDispatcher("analytics.jsp").forward(request, response);
                break;
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
