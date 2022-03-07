package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Product;

/**
 *
 * @author Duy Phuong
 */
public class ProductDAO extends DBContext {

    /* Product DAO */
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

}
