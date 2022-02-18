
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Brand;
import model.Category;
import model.Color;
import model.Product;
import model.User;

public class DAO extends DBContext {
    
    /**
     * Category DAO
     */
    public List<Category> getAll() {
        List<Category> list = new ArrayList<>();
        String sql = "select * from Categories";
        try {
            PreparedStatement st = connection.prepareStatement(sql);            
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Category c = new Category(rs.getInt("CategoryID"), 
                    rs.getString("CategoryName"), rs.getString("Description"),
                    (rs.getInt("Active")) == 1);
                list.add(c);
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public void addCategory(Category c) {
        String sql = "insert into Categories (CategoryName, Description) values (?,?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getCategoryName());
            st.setString(2, c.getDescription());
            st.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e);
        }
    }
    
    
    /**
     * Brand DAO
     */
    public List<Brand> getAllBrand() {
        List<Brand> list = new ArrayList<>();
        String sql = "select * from Brands";
        try {
            PreparedStatement st = connection.prepareStatement(sql);            
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Brand b = new Brand(rs.getInt("BrandID"), 
                        rs.getString("BrandName"),
                        rs.getString("BrandLogo"));
                list.add(b);
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    
    public void addBrand(Brand b) {
        String sql = "insert into Brands (BrandName) values (?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, b.getBrandName());
            st.executeUpdate();
        } catch(SQLException e) {
            System.out.println(e);
        }
    }
    
    
    /**
     * Color DAO
     */
    public List<Color> getAllColor() {
        List<Color> list = new ArrayList<>();
        String sql = "select * from Colors";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Color c = new Color();
                c.setColorID(rs.getString("ColorID"));
                c.setColorName(rs.getString("ColorName"));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    public Color getColorById(String colorID) {
        String sql = "select * from Colors where ColorID = ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, colorID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Color c = new Color();
                c.setColorID(rs.getString("ColorID"));
                c.setColorName(rs.getString("ColorName"));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public void addColor(String colorID, String colorName) {
        String sql = "insert into Colors (ColorID, ColorName) values (?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, colorID);
            st.setString(2, colorName);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    /**
     * Product DAO
     */
    public List<Product> getAllProduct(int productID) {
        return getProductByCategory(0);
    }
    public Product getProductById(int productID) {
        String sql = "select * from Products";
        try {
            if (productID != 0) {
                sql += " where ProductID = " + productID;
            }
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setBrandID(rs.getInt("BrandID"));
                p.setPrice(rs.getDouble("Price"));
                p.setStatus(rs.getString("Status"));
                return p;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public List<Product> getProductByCategory(int categoryID) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Products";
        try {
            if (categoryID != 0) {
                sql += " p inner join CatePro cp on p.ProductID = cp.ProductID"
                        + " inner join Categories c on cp.CategoryID = c.CategoryID"
                        + " where c.CategoryID = " + categoryID;
            }
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setBrandID(rs.getInt("BrandID"));
                p.setPrice(rs.getDouble("Price"));
                p.setStatus(rs.getString("Status"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Product getLastProduct() {
        String sql = "select top(1)* from Products order by ProductID desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setBrandID(rs.getInt("BrandID"));
                p.setPrice(rs.getDouble("Price"));
                p.setStatus(rs.getString("Status"));
                return p;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public int addProduct(Product p, int categoryID) {
        String sql1 = "insert into Products (ProductName, BrandID, Price, Status) values (?, ?, ?, ?)";
        String sql2 = "insert into CatePro (ProductID, CategoryID) values (?, ?)";
        try {
            PreparedStatement st1 = connection.prepareStatement(sql1);
            PreparedStatement st2 = connection.prepareStatement(sql2);
            st1.setString(1, p.getProductName());
            st1.setInt(2, p.getBrandID());
            st1.setDouble(3, p.getPrice());
            st1.setString(4, p.getStatus());
            st1.executeUpdate();

            int productID = getLastProduct().getProductID();
            st2.setInt(1, productID);
            st2.setInt(2, categoryID);
            st2.executeUpdate();
            return productID;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }
    
    public void deleteProduct(int productID) {
        String sql = "delete from Products where productID=?";
        
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(2, productID);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
    
    
    /**
     * User DAO
     */
     public User signInCheck(String username, String password) {
        String sql = "select * from Users where Username = ? and Password = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User u = new User();
                u.setUsername(rs.getString("Username"));
                u.setPassword(rs.getString("Password"));
                u.setRole(rs.getInt("Role"));
                u.setFullname(rs.getString("Fullname"));
                u.setCity(rs.getString("City"));
                u.setCountry(rs.getString("Country"));
                u.setAddress(rs.getString("Address"));
                u.setPhone(rs.getString("Phone"));
                return u;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
