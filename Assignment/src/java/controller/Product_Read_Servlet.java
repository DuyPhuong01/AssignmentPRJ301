
package controller;

import dal.DAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Duy Phuong
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/product"})
public class Product_Read_Servlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProductServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProductServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = new DAO();
        String action = request.getParameter("action");
        
        if(action == null){
            String categoryID_raw = request.getParameter("categoryID");
            if(categoryID_raw == null) categoryID_raw = "0";
            
            String min_raw = request.getParameter("min");
            if(min_raw == null) min_raw = "0";
            
            String max_raw = request.getParameter("max");
            if(max_raw == null) max_raw = "200";
            
            String orderby = request.getParameter("orderby");
            request.setAttribute("orderby", orderby==null ? "default" : orderby);
            if(orderby==null || orderby.equals("default")) orderby = "ProductId";
            else if(orderby.equals("name"))orderby = "ProductName";
            else if(orderby.equals("price"))orderby = "Price";
            
            String[] brandID_raw = request.getParameterValues("brand");
            if(brandID_raw == null) brandID_raw = new String[0];
            int[] brandID = new int[brandID_raw.length];
            
            /* search key*/
            String searchkey = request.getParameter("searchkey");
            if(searchkey==null) searchkey="";
            
            try{
                int categoryID = Integer.parseInt(categoryID_raw);
                int min = Integer.parseInt(min_raw);
                int max = Integer.parseInt(max_raw);
                if(brandID_raw.length !=0)
                for(int i=0; i<brandID_raw.length; i++) {
                    brandID[i] = Integer.parseInt(brandID_raw[i]);
                }
                request.setAttribute("productList", dao.getProducts(categoryID, brandID, min, max, orderby));
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } catch (NumberFormatException e){
                System.out.println(e);
            }
        }
        else if(action.equals("details")){
            String productID_raw = request.getParameter("productID");
            try{
                int productID = Integer.parseInt(productID_raw);
                request.setAttribute("product", dao.getProductById(productID));
                request.getRequestDispatcher("product.jsp").forward(request, response);
            }catch (NumberFormatException e){
                System.out.println(e);
            }
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
