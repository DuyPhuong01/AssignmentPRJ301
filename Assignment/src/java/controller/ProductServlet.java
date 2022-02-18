
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
import model.User;

/**
 *
 * @author Duy Phuong
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/product"})
public class ProductServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        request.setAttribute("categoryList", dao.getAll());
        request.setAttribute("brandList", dao.getAllBrand());
        String action = request.getParameter("action");
        
        if(action == null){
            String categoryID_raw = request.getParameter("categoryID");
            if(categoryID_raw == null){
                categoryID_raw = "0";
            }
            try {   
                System.out.println("Account: " + (User)session.getAttribute("userAccount"));
            } catch(NullPointerException npe) {
                System.out.println(npe);
                session.setAttribute("userAccount", null);
            }
            try{
                int categoryID = Integer.parseInt(categoryID_raw);
                request.setAttribute("productList", dao.getProductByCategory(categoryID));
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
