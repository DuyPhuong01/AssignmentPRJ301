
package controller;

import dal.ProductDAO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;

/**
 *
 * @author Duy Phuong
 */
@WebServlet(name = "ProductServlet", urlPatterns = {"/product"})
public class Product_Read_Servlet extends HttpServlet {
    final int p_per_page = 12;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO p_dao = new ProductDAO();
        String action = request.getParameter("action");
        
        if(action == null) action = "read";
        switch(action) {
            case "read":
                String categoryID_raw = request.getParameter("categoryID");
                if(categoryID_raw == null) categoryID_raw = "0";

                String min_raw = request.getParameter("min");
                if(min_raw == null) min_raw = "0";

                String max_raw = request.getParameter("max");
                if(max_raw == null) max_raw = "300";

                String orderby = request.getParameter("orderby");
                request.setAttribute("orderby", orderby);
                request.setAttribute("orderby", orderby==null ? "default" : orderby);
                if(orderby==null || orderby.equals("default")) orderby = "ProductId";
                else if(orderby.equals("name"))orderby = "ProductName";
                else if(orderby.equals("price"))orderby = "Price";

                String[] brandID_raw = request.getParameterValues("brand");
                if(brandID_raw == null) brandID_raw = new String[0];
                int[] brandID = new int[brandID_raw.length];

                String page_number_raw = request.getParameter("page");
                if(page_number_raw==null) page_number_raw="1";

                /* search key*/
                String searchkey = request.getParameter("searchkey");
                if(searchkey==null) searchkey="";

                try{
                    int categoryID = Integer.parseInt(categoryID_raw);
                    int min = Integer.parseInt(min_raw);
                    int max = Integer.parseInt(max_raw);

                    for(int i=0; i<brandID_raw.length; i++) {
                        brandID[i] = Integer.parseInt(brandID_raw[i]);
                    }

                    request.setAttribute("searchkey", searchkey);
                    request.setAttribute("min", min);
                    request.setAttribute("max", max);
                    List<Product> p_list = p_dao.getProducts(categoryID, brandID, min, max, orderby, searchkey);

                    List<Product> productList = new ArrayList<>();
                    int number_of_page = p_list.size()%p_per_page==0 ? p_list.size()/p_per_page : p_list.size()/p_per_page+1;
                    int page_number = Integer.parseInt(page_number_raw);

                    System.out.println("p_list.size(): "+ p_list.size());
                    System.out.println("p_per_page: "+ p_per_page);
                    System.out.println("number_of_page: "+ number_of_page);
                    int start = (page_number-1)*p_per_page;
                    int end = page_number*p_per_page<p_list.size() ? page_number*p_per_page : p_list.size();
                    for(int i=start; i<end; i++){
                        productList.add(p_list.get(i));
                    };

                    request.setAttribute("number_of_page", number_of_page);
                    request.setAttribute("productList", productList);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                } catch (NumberFormatException e){
                    System.out.println(e);
                }
                break;
                
            case "details":
                String productID_raw = request.getParameter("productID");
                try{
                    int productID = Integer.parseInt(productID_raw);
                    request.setAttribute("product", p_dao.getProductById(productID));
                    request.getRequestDispatcher("product.jsp").forward(request, response);
                }catch (NumberFormatException e){
                    System.out.println(e);
                }
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
