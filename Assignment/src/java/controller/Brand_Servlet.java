
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
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.Brand;
import model.Product;

/**
 *
 * @author Duy Phuong
 */
@MultipartConfig
@WebServlet(name = "Brand_Servlet", urlPatterns = {"/brand"})
public class Brand_Servlet extends HttpServlet {
    final int p_per_page = 12;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BrandDAO b_dao = new BrandDAO();
        
        String action = request.getParameter("action");
        
        if(action == null) action = "read";
        switch(action) {
            case "create":
                request.getRequestDispatcher("brand-create.jsp").forward(request, response);
                break;
                
                
            case "update":
                String id_raw = request.getParameter("id");
                try {
                    int id = Integer.parseInt(id_raw);
                    request.setAttribute("brand", b_dao.getBrand(id));
                    request.getRequestDispatcher("brand-update.jsp").forward(request, response);
                } catch(NumberFormatException e) {
                    System.out.println(e);
                }
                break;
                
            case "delete":
                String delete_id_raw = request.getParameter("id");
                try {
                    int id = Integer.parseInt(delete_id_raw);
                    String fileName = b_dao.getBrand(id).getBrandLogo();
                    b_dao.deleteBrand(id);
                    File f = new File(getFolderUploadPath() + File.separator +  fileName);
                    if(f.exists()){
                        f.delete();
                    }
                    response.sendRedirect("admin?action=brand");
                } catch(NumberFormatException e) {
                    System.out.println(e);
                }
                break;
            
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BrandDAO b_dao = new BrandDAO();
        int brandID = 0;
        String name; Part filePart;
        String action = request.getParameter("action");
        System.out.println(action);
        if(action == null) action = "read";
        switch(action) {
            case "create":
                /* get Product information */
                name = request.getParameter("name");
                filePart = request.getPart("brandLogo");

                try {
                    String fileName = getFileName(filePart);

                    /* add product to database*/
                    Brand b = new Brand(1, name, fileName);
                    System.out.println(b + " are creating");
                    brandID = b_dao.addBrand(b);
                    if(brandID > 0) {
                        System.out.println(b + "created");

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
                    TimeUnit.SECONDS.sleep(3);
                    response.sendRedirect("admin?action=brand");
                } catch(NumberFormatException | InterruptedException nfe) {
                    System.out.println(nfe);
                } catch (FileNotFoundException fne) {
                    System.out.println(fne.getMessage());
                    b_dao.deleteBrand(brandID);
                }
                break;
                
            case "update":
                /* get Product information */   
                String brandID_raw = request.getParameter("brandID");
                name = request.getParameter("name");
                filePart = request.getPart("brandLogo");

                try {
                    brandID = Integer.parseInt(brandID_raw);
                    String fileName = getFileName(filePart);
                    String deleteFileName = b_dao.getBrand(brandID).getBrandLogo();

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

                    /* update product to database*/
                    Brand b = new Brand(brandID, name, fileName);
                    b_dao.updateBrand(b);

                    TimeUnit.SECONDS.sleep(3);
                    response.sendRedirect("admin?action=brand");
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
