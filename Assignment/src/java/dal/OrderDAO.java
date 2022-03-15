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
                + "where p.Status=1 and o.UserID=? and o.Status=?";
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
            st.setInt(3, item.getQuantity());
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
            
            /* set new Quantity to Product */
            String sql4 = "update Products set Quantity-=? where ProductID=?";
            PreparedStatement st4 = connection.prepareStatement(sql4);
            for(int i=0; i<productIDList.length; i++) {
                st4.setInt(1, quantityList[i]);
                st4.setInt(2, productIDList[i]);
                st4.executeUpdate();
                System.out.println("update Products set Quantity-="+quantityList[i]+" where ProductID="+productIDList[i]);
                System.out.println("done");
            }
            
            return true;
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
        return false;
    }
    
    public List<List> getRevenue(){
        List<List> list = new ArrayList<>();
        List<Date> date = new ArrayList<>();
        List<Integer> revenue = new ArrayList<>();
        
        String sql = "select SUM(TotalPrice) as Revenue, CONVERT(DATE, OrderDate) as OrderDate "
                + "from Orders where Status=0 group by CONVERT(DATE, OrderDate) "
                + "order by CONVERT(DATE, OrderDate) asc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                date.add(rs.getDate("OrderDate"));
                revenue.add(rs.getInt("Revenue"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        list.add(date);
        list.add(revenue);
        return list;
    }
    public List<Cart> getHistory(int userID){
        List<Cart> list = new ArrayList<>();
        
        String sql1 = "select * from Orders where UserID=? and Status=0 order by OrderDate asc";
        try {
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setInt(1, userID);
            ResultSet rs1 = st1.executeQuery();
            while (rs1.next()) {
                int orderID = rs1.getInt("OrderID");
                List<Item> i_list = new ArrayList<>();
                String sql2 = "select p.*, i.Quantity as SoldQuantity, i.OrderID "
                        + "from Items i inner join Products p "
                        + "on i.ProductID = p.ProductID "
                        + "where i.OrderID=?";
                PreparedStatement st2 = connection.prepareStatement(sql2);
                st2.setInt(1, orderID);
                ResultSet rs2 = st2.executeQuery();
                while (rs2.next()) {
                    Product p = new Product();
                    p.setProductID(rs2.getInt("ProductID"));
                    p.setProductName(rs2.getString("ProductName"));
                    p.setBrandID(rs2.getInt("BrandID"));
                    p.setPrice(rs2.getDouble("Price"));
                    p.setQuantity(rs2.getInt("Quantity"));
                    p.setImage(rs2.getString("ProductImage"));
                    p.setStatus(rs2.getInt("Status"));

                    Item i = new Item(p, rs2.getInt("OrderID"), rs2.getInt("SoldQuantity"));
                    i_list.add(i);
                }
                Cart c = new Cart(i_list, rs1.getTimestamp("OrderDate"), rs1.getInt("TotalPrice"), rs1.getInt("Status")==1);
                list.add(c);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }
    public int getTotalSpend(int userID){
        String sql = "select SUM(TotalPrice) as TotalSpend from Orders where UserID=? and Status=0";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt("TotalSpend");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }
}
