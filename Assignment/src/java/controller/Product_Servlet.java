
package controller;

import dal.BrandDAO;
import dal.CategoryDAO;
import dal.ProductDAO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Product;

/**
 *
 * @author Duy Phuong
 */
@MultipartConfig
@WebServlet(name = "ProductServlet", urlPatterns = {"/product"})
public class Product_Servlet extends HttpServlet {
    final int p_per_page = 12;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO p_dao = new ProductDAO();
        CategoryDAO c_dao = new CategoryDAO();
        BrandDAO b_dao = new BrandDAO();
        
        String action = request.getParameter("action");
        
        if(action == null) action = "read";
        switch(action) {
            case "create":
                request.setAttribute("categoryList", c_dao.getAllCategory());
                request.setAttribute("brandList", b_dao.getAllBrand());
                request.getRequestDispatcher("product-create.jsp").forward(request, response);
                break;
                
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

                /* search key */
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
                
            case "update":
                String id_raw = request.getParameter("id");
                try {
                    int id = Integer.parseInt(id_raw);
                    request.setAttribute("product", p_dao.getProductById(id));
                    request.setAttribute("categoryList", c_dao.getAllCategory());
                    request.setAttribute("brandList", b_dao.getAllBrand());
                    request.getRequestDispatcher("product-update.jsp").forward(request, response);
                } catch(NumberFormatException e) {
                    System.out.println(e);
                }
                break;
                
            case "delete":
                String delete_id_raw = request.getParameter("id");
                try {
                    int id = Integer.parseInt(delete_id_raw);
                    String fileName = p_dao.getProductById(id).getImage();
                    p_dao.deleteProduct(id);
                    File f = new File(getFolderUploadPath() + File.separator +  fileName);
                    if(f.exists()){
                        f.delete();
                    }
                    response.sendRedirect("admin");
                } catch(NumberFormatException e) {
                    System.out.println(e);
                }
                break;
            
            case "details":
                String productID_raw = request.getParameter("productID");
                try{
                    int productID = Integer.parseInt(productID_raw);
                    request.setAttribute("product", p_dao.getProductById(productID));
                    request.setAttribute("relatedproduct", p_dao.getProducts(p_dao.getCategoryID(productID)));
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
        ProductDAO p_dao = new ProductDAO();
        
        String action = request.getParameter("action");
        System.out.println(action);
        if(action == null) action = "read";
        switch(action) {
            case "create":
                int productID = 0;
                /* get Product information */        
                String categoryID_raw = request.getParameter("categoryID");
                String brandID_raw = request.getParameter("brandID");
                String name = request.getParameter("name");
                String price_raw = request.getParameter("price");
                String quantity_raw = request.getParameter("quantity");
                Part filePart = request.getPart("productPhoto");
                String activate_raw = request.getParameter("activate");

                try {
                    int categoryID = Integer.parseInt(categoryID_raw);
                    int brandID = Integer.parseInt(brandID_raw);
                    double price = Double.parseDouble(price_raw);
                    int quantity = Integer.parseInt(quantity_raw);
                    String fileName = getFileName(filePart);
                    int activate = Integer.parseInt(activate_raw);

                    /* add product to database*/
                    Product p = new Product(name, brandID, price, quantity, fileName, activate);
                    System.out.println(p + " are creating");
                    productID = p_dao.addProduct(p, categoryID);
                    if(productID > 0) {
                        System.out.println(p + "created");

                        /* save product iamge */
                        String filePath = getFolderUploadPath() + File.separator +  fileName;
                        File f = new File(filePath);
                        OutputStream out = new FileOutputStream(f);
                        InputStream filecontent = filePart.getInputStream();
                        int read = 0;
                        byte[] bytes = new byte[1024];
                        while ((read = filecontent.read(bytes)) != -1) {
                            out.write(bytes, 0, read);
                        }
                        System.out.println("New file created at " + filePath);

                        filecontent.close();
                        out.close();
                    }
                    TimeUnit.SECONDS.sleep(2);
                    response.sendRedirect("admin?action=product");
                } catch(NumberFormatException | InterruptedException nfe) {
                    System.out.println(nfe);
                } catch (FileNotFoundException fne) {
                    System.out.println(fne.getMessage());
                    p_dao.deleteProduct(productID);
                }
                break;
                
            case "update":
                /* get Product information */   
                String productID_raw = request.getParameter("productID");
                String update_categoryID_raw = request.getParameter("categoryID");
                String update_brandID_raw = request.getParameter("brandID");
                String update_name = request.getParameter("name");
                String update_price_raw = request.getParameter("price");
                String update_quantity_raw = request.getParameter("quantity");
                Part update_filePart = request.getPart("productPhoto");
                String update_activate_raw = request.getParameter("activate");

                try {
                    int update_productID = Integer.parseInt(productID_raw);
                    int categoryID = Integer.parseInt(update_categoryID_raw);
                    int brandID = Integer.parseInt(update_brandID_raw);
                    double price = Double.parseDouble(update_price_raw);
                    int quantity = Integer.parseInt(update_quantity_raw);
                    String fileName = getFileName(update_filePart);
                    int activate = Integer.parseInt(update_activate_raw);
                    String deleteFileName = p_dao.getProductById(update_productID).getImage();

                    if(fileName.equals("")){
                        fileName = deleteFileName;
                    } else {
                        /* save old product iamge */
                        File df = new File(getFolderUploadPath() + File.separator +  deleteFileName);
                        if(df.exists()){
                            df.delete();
                        }

                        /* save product iamge */
                        String filePath = getFolderUploadPath() + File.separator +  fileName;
                        File f = new File(filePath);
                        OutputStream out = new FileOutputStream(f);
                        InputStream filecontent = update_filePart.getInputStream();
                        int read = 0;
                        byte[] bytes = new byte[1024];
                        while ((read = filecontent.read(bytes)) != -1) {
                            out.write(bytes, 0, read);
                        }
                        System.out.println("New file created at " + filePath);

                        filecontent.close();
                        out.close();
                    }

                    /* update product to database*/
                    Product p = new Product(update_productID, update_name, brandID, price, quantity, fileName, activate);
                    p_dao.updateProduct(p, categoryID);

                    TimeUnit.SECONDS.sleep(2);
                    response.sendRedirect("admin");
                } catch(NumberFormatException | InterruptedException nfe) {
                    System.out.println(nfe);
                }
                break;
                
        }
    }

    private String getFileName(final Part part) {
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(
                        content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }
    
    public String getFolderUploadPath() {
      String path = getServletContext().getRealPath("/").replace("\\build", "") + "images";
      File folderUpload = new File(path);
      if (!folderUpload.exists()) {
        folderUpload.mkdirs();
      }
      return path;
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
