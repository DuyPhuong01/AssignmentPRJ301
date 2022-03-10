package dal;

import java.util.Date;
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
    public boolean removeFromCart(int[] productID, int userID) {
        int orderID = getOrderID(userID);
        String sql = "delete Items where OrderID=? and ProductID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderID);
            for(int id : productID) {
                st.setInt(2, id);
                st.executeUpdate();
            }
                
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    public boolean removeAllFromCart(int userID) {
        int orderID = getOrderID(userID);
        String sql = "delete Items where OrderID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, orderID);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    public boolean updateQuantity(int productID, int quantity, int userID) {
        int orderID = getOrderID(userID);
        String sql = "update Items set Quantity=? where OrderID=? and ProductID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, quantity);
            st.setInt(2, orderID);
            st.setInt(3, productID);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    
    public boolean buy(int[] productIDList, int[] quantityList, int userID){
        int orderID = getOrderID(userID);
        try {
            /* create new unactivate Cart */
            String sql1 = "insert into Orders (OrderID, UserID, Status) values (?, ?, ?)";
            OrderDAO order_dao = new OrderDAO();
            int new_orderID = order_dao.getLastOrderID()+1;
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setInt(1, new_orderID);
            st1.setInt(2, userID);
            st1.setInt(3, 0);
            st1.executeUpdate();
            
            /* move items to new cart */
            String sql2 = "update Items set OrderID=?, Quantity=? where ProductID=? and OrderID=?";
            PreparedStatement st2 = connection.prepareStatement(sql2);
            st2.setInt(1, new_orderID);
            st2.setInt(4, orderID);
            for(int i=0; i<productIDList.length; i++) {
                st2.setInt(2, quantityList[i]);
                st2.setInt(3, productIDList[i]);
                System.out.println("update Items set OrderID="+new_orderID+
                        ", Quantity="+quantityList[i]+" where ProductID="+productIDList[i]+
                        " and OrderID="+orderID);
                System.out.println(st2.executeUpdate());
                System.out.println("done");
            }
            
            /* set other value for new cart */
            String sql3 = "update Orders set TotalPrice=("
                    + "select SUM(p.Price*i.Quantity) from Items i "
                    + "inner join Products p on i.ProductID = p.ProductID "
                    + "where OrderID=?"
                    + "), OrderDate=GETDATE() where OrderID=?";
            PreparedStatement st3 = connection.prepareStatement(sql3);
            st3.setInt(1, new_orderID);
            st3.setInt(2, new_orderID);
            st3.executeUpdate();
            return true;
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
        
        
        return false;
    }
}
