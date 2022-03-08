package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.Item;
import model.Product;

/**
 *
 * @author Duy Phuong
 */
public class OrderDAO extends DBContext {

    /* Cart DAO */
    public int getOrderID(int userID) {
        String sql = "select * from Orders where UserID=? and Status=1";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("OrderID");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }
    public List<Integer> getAllOrderID(int userID) {
        List<Integer> list = new ArrayList<>();
        String sql = "select * from Orders where UserID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                list.add(rs.getInt("OrderID"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public Cart getCart(int userID) {
        List<Item> list = new ArrayList();
        String sql = "select i.OrderID, i.Quantity as Number, p.*, o.OrderID from Items i "
                + "inner join Orders o "
                + "on i.OrderID = o.OrderID "
                + "inner join Products p "
                + "on i.ProductID = p.ProductID "
                + "where o.UserID=? and o.Status=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userID);
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
                
                Item i = new Item(p, rs.getInt("OrderID"), rs.getInt("Number"));
                list.add(i);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return (new Cart(list, true));
    }

    public boolean addItemToCart(Item item) {
        String sql = "insert into Items (OrderID, ProductID, Quantity) values (?, ?, ?)";
        try {
            System.out.println("check2");
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
    public Item getItemsByID(int productID, int orderID) {
        String sql = "select i.OrderID, i.Quantity as Number, p.* from Items i "
                + "inner join Products p on p.ProductID = i.ProductID "
                + "where i.productID=? and i.orderID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, productID);
            st.setInt(2, orderID);
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
                return new Item(p, rs.getInt("OrderID"), rs.getInt("Number"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    public boolean updateItemsQuantity(int quantity, int productID, int orderID){
        String sql = "update Items set Quantity = ? where productID=? and orderID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, quantity);
            st.setInt(2, productID);
            st.setInt(3, orderID);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    public int getLastOrderID() {
        String sql = "select * from Orders order by OrderID desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("OrderID");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }
}
