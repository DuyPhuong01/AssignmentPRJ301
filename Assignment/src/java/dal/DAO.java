package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Brand;
import model.Cart;
import model.Category;
import model.Color;
import model.Item;
import model.Product;
import model.User;

/**
 *
 * @author Duy Phuong
 */
public class DAO extends DBContext {

    /**
     * Category DAO
     */
    public Category getCategoryById(int categoryID) {
        String sql = "select * from Categories where CategoryID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, categoryID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Category c = new Category(rs.getInt("CategoryID"),
                        rs.getString("CategoryName"), rs.getString("Description"),
                        (rs.getInt("Status")));
                return c;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public List<Category> getAllCategory() {
        List<Category> list = new ArrayList<>();
        String sql = "select * from Categories";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Category c = new Category(rs.getInt("CategoryID"),
                        rs.getString("CategoryName"), rs.getString("Description"),
                        (rs.getInt("Status")));
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public void addCategory(Category c) {
        String sql = "insert into Categories (CategoryName, Description, Status) values (?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getCategoryName());
            st.setString(2, c.getDescription());
            st.setInt(3, c.getStatus());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void updateCategory(Category c) {
        String sql = "update Categories set CategoryName=?, Description=?, Status=? where CategoryID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, c.getCategoryName());
            st.setString(2, c.getDescription());
            st.setInt(3, c.getStatus());
            st.setInt(4, c.getCategoryID());
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void deleteCategory(int categoryID) {
        String sql1 = "delete from CatePro where categoryID=?";
        String sql2 = "delete from Categories where categoryID=?";
        try {
            connection.setAutoCommit(false);
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setInt(1, categoryID);
            st1.executeUpdate();

            PreparedStatement st2 = connection.prepareStatement(sql2);
            st2.setInt(1, categoryID);
            st2.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException sqle3) {
                System.out.println(sqle3);
            }
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
            while (rs.next()) {
                Brand b = new Brand(rs.getInt("BrandID"),
                        rs.getString("BrandName"),
                        rs.getString("BrandLogo"));
                list.add(b);
            }
        } catch (SQLException e) {
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
        } catch (SQLException e) {
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
    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Products";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setBrandID(rs.getInt("BrandID"));
                p.setPrice(rs.getDouble("Price"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setImage(rs.getString("ProductImage"));
                p.setStatus(rs.getInt("Status"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public List<Product> getProducts(int categoryID, int[] brandID, int priceMin, 
            int priceMax, String orderby, String searchkey) {
        List<Product> list = new ArrayList<>();
        String sql = "select * from Products p";
        try {
            if (categoryID != 0) {
                sql += " inner join CatePro cp on p.ProductID = cp.ProductID"
                        + " inner join Categories c on cp.CategoryID = c.CategoryID"
                        + " where c.CategoryID=" + categoryID + " and ";
            } else {
                sql += " where ";
            }
            for (int i = 0; i < brandID.length; i++) {
                if (i == 0) {
                    sql += "(BrandID=?";
                } else {
                    sql += " or BrandID=?";
                }
                if (i == brandID.length - 1) {
                    sql += ") and";
                }
                System.out.println("test1");
            }
            sql += " Price>? and Price<? and ProductName like ? "
                    + "order by p." + orderby;
            PreparedStatement st = connection.prepareStatement(sql);System.out.println(sql);
            for (int i = 0; i < brandID.length; i++) {
                st.setInt(1 + i, brandID[i]);
            }
            st.setInt(1 + brandID.length, priceMin);
            st.setInt(2 + brandID.length, priceMax);
            st.setString(3 + brandID.length, "%" + searchkey + "%");
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setBrandID(rs.getInt("BrandID"));
                p.setPrice(rs.getDouble("Price"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setImage(rs.getString("ProductImage"));
                p.setStatus(rs.getInt("Status"));
                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
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
                p.setQuantity(rs.getInt("Quantity"));
                p.setImage(rs.getString("ProductImage"));
                p.setStatus(rs.getInt("Status"));
                return p;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Product getLastProduct() {
        String sql = "select * from Products order by ProductID desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setBrandID(rs.getInt("BrandID"));
                p.setPrice(rs.getDouble("Price"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setImage(rs.getString("ProductImage"));
                p.setStatus(rs.getInt("Status"));
                return p;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public int addProduct(Product p, int categoryID) {
        String sql1 = "insert into Products (ProductID, ProductName, BrandID, Price, Quantity, ProductImage, Status) values (?, ?, ?, ?, ?, ?, ?)";
        String sql2 = "insert into CatePro (ProductID, CategoryID) values (?, ?)";
        try {
            connection.setAutoCommit(false);
            int productID = getLastProduct().getProductID()+1;
            
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setInt(1, productID);
            st1.setString(2, p.getProductName());
            st1.setInt(3, p.getBrandID());
            st1.setDouble(4, p.getPrice());
            st1.setInt(5, p.getQuantity());
            st1.setString(6, p.getImage());
            st1.setInt(7, p.getStatus());
            st1.executeUpdate();

            PreparedStatement st2 = connection.prepareStatement(sql2);
            st2.setInt(1, productID);
            st2.setInt(2, categoryID);
            st2.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            return productID;
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException sqle3) {
                System.out.println(sqle3);
            }
        }
        return -1;
    }

    public void deleteProduct(int productID) {
        String sql1 = "delete from CatePro where productID=?";
        String sql2 = "delete from Products where productID=?";
        String sql3 = "delete from Items where productID=?";

        try {
            connection.setAutoCommit(false);
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setInt(1, productID);
            st1.executeUpdate();
            
            PreparedStatement st2 = connection.prepareStatement(sql2);
            st2.setInt(1, productID);
            st2.executeUpdate();
            
            PreparedStatement st3 = connection.prepareStatement(sql3);
            st3.setInt(1, productID);
            st3.executeUpdate();
            
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException sqle3) {
                System.out.println(sqle3);
            }
        }
    }

    public void updateProduct(Product p, int categoryID) {
        String sql1 = "update Products set ProductName=?, BrandID=?, Price=?, "
                + "Quantity=?, ProductImage=?, Status=? where productID=?";
        String sql2 = "update CatePro set CategoryID=? where ProductID=?";
        try {
            connection.setAutoCommit(false);
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setString(1, p.getProductName());
            st1.setInt(2, p.getBrandID());
            st1.setDouble(3, p.getPrice());
            st1.setInt(4, p.getQuantity());
            st1.setString(5, p.getImage());
            st1.setInt(6, p.getStatus());
            st1.setInt(7, p.getProductID());
            st1.executeUpdate();

            PreparedStatement st2 = connection.prepareStatement(sql2);
            st2.setInt(1, categoryID);
            st2.setInt(2, p.getProductID());
            st2.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException sqle3) {
                System.out.println(sqle3);
            }
        }
    }

    /**
     * User DAO
     */
    public List<User> getAllUser() {
        List<User> list = new ArrayList<>();
        String sql = "select * from Users";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setUsername(rs.getString("Username"));
                u.setPassword(rs.getString("Password"));
                u.setRole(rs.getInt("Role"));
                u.setFullname(rs.getString("FullName"));
                u.setCity(rs.getString("City"));
                u.setCountry(rs.getString("Country"));
                u.setAddress(rs.getString("Address"));
                u.setPhone(rs.getString("Phone"));
                list.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

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

    public User getUser(String username) {
        String sql = "select * from Users where Username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
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

    public boolean checkUsername(String username) {
        String sql = "select * from Users where Username = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    public boolean createUser(User u) {
        String sql1 = "insert into Users values(?, ?, ?, ?, ?, ?, ?, ?)";
        String sql2 = "insert into Orders (Username, Status) values(?, ?)";
        try {
            connection.setAutoCommit(false);
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setString(1, u.getUsername());
            st1.setString(2, u.getPassword());
            st1.setInt(3, u.getRole());
            st1.setString(4, u.getFullname());
            st1.setString(5, u.getCity());
            st1.setString(6, u.getCountry());
            st1.setString(7, u.getAddress());
            st1.setString(8, u.getPhone());
            st1.executeUpdate();

            PreparedStatement st2 = connection.prepareStatement(sql2);
            st2.setString(1, u.getUsername());
            st2.setInt(2, 1);
            st2.executeUpdate();

            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException sqle3) {
                System.out.println(sqle3);
            }
        }
        return false;
    }

    public boolean deleteUser(String username) {
        String sql1 = "delete Orders where Username=?";
        String sql2 = "delete Users where Username=?";
        try {
            connection.setAutoCommit(false);
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setString(1, username);
            st1.executeUpdate();

            PreparedStatement st2 = connection.prepareStatement(sql2);
            st2.setString(1, username);
            st2.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            return true;
        } catch (SQLException sqle) {
            try {
                if (connection != null) {
                    connection.rollback();
                }
            } catch (SQLException sqle3) {
                System.out.println(sqle3);
            }
        }
        return false;
    }

    /**
     * Cart DAO
     */
    public int getOrderID(String username) {
        String sql = "select * from Orders where Username=? and Status=1";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("OrderID");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return 0;
    }

    public Cart getCartByUsername(String username) {
        List<Item> list = new ArrayList();
        String sql = "select i.OrderID, i.Quantity, p.*, o.OrderID from Items i "
                + "inner join Orders o "
                + "on i.OrderID = o.OrderID "
                + "inner join Products p "
                + "on i.ProductID = p.ProductID "
                + "where o.Username=? and o.Status=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setInt(2, 1);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setBrandID(rs.getInt("BrandID"));
                p.setPrice(rs.getDouble("Price"));
                p.setQuantity(rs.getInt("Quantity"));
                p.setImage(rs.getString("ProductImage"));
                p.setStatus(rs.getInt("Status"));

                Item i = new Item(p, rs.getInt("Quantity"), rs.getInt("OrderID"));
                list.add(i);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return (new Cart(list, true));
    }

    public boolean addItemToCart(Item item, String username) {
        String sql = "insert into Items values (?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, item.getOrderID());
            st.setInt(2, item.getProduct().getProductID());
            st.setInt(3, 1);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

}
