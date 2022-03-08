package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;

/**
 *
 * @author Duy Phuong
 */
public class UserDAO extends DBContext {

    /* User DAO */
    public List<User> getAllUser() {
        List<User> list = new ArrayList<>();
        String sql = "select * from Users";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User u = new User(rs.getInt("UserID"), 
                        rs.getString("Username"), rs.getString("Password"), 
                        rs.getInt("Role"), rs.getString("FullName"), 
                        rs.getString("City"), rs.getString("Country"), 
                        rs.getString("Address"), rs.getString("Phone"));
                list.add(u);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public User getUser(String username, String password) {
        String sql = "select * from Users where Username = ? and Password = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User u = new User(rs.getInt("UserID"), 
                        rs.getString("Username"), rs.getString("Password"), 
                        rs.getInt("Role"), rs.getString("FullName"), 
                        rs.getString("City"), rs.getString("Country"), 
                        rs.getString("Address"), rs.getString("Phone"));
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
                User u = new User(rs.getInt("UserID"), 
                        rs.getString("Username"), rs.getString("Password"), 
                        rs.getInt("Role"), rs.getString("FullName"), 
                        rs.getString("City"), rs.getString("Country"), 
                        rs.getString("Address"), rs.getString("Phone"));
                return u;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public boolean createUser(User u) {
        String sql1 = "insert into Users (UserID, Username, Password, Role, "
                + "FullName, City, Country, Address, Phone) "
                + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sql2 = "insert into Orders (OrderID, UserID, Status) values (?, ?, ?)";
        try {
            int userID = getLastUser().getUserID()+1;
            
            /* Add User */
            PreparedStatement st1 = connection.prepareStatement(sql1);
            st1.setInt(1, userID);
            st1.setString(2, u.getUsername());
            st1.setString(3, u.getPassword());
            st1.setInt(4, u.getRole());
            st1.setString(5, u.getFullname());
            st1.setString(6, u.getCity());
            st1.setString(7, u.getCountry());
            st1.setString(8, u.getAddress());
            st1.setString(9, u.getPhone());
            st1.executeUpdate();

            /* Add Cart */
            OrderDAO order_dao = new OrderDAO();
            PreparedStatement st2 = connection.prepareStatement(sql2);
            st2.setInt(1, order_dao.getLastOrderID()+1);
            st2.setInt(2, userID);
            st2.setInt(3, 1);
            st2.executeUpdate();
            return true;
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
        return false;
    }

    public boolean deleteUser(int userID) {
        OrderDAO o_dao = new OrderDAO();
        List<Integer> orderIDList = o_dao.getAllOrderID(userID);
        
        String sql1 = "delete Items where OrderID=?";
        String sql2 = "delete Orders where UserID=?";
        String sql3 = "delete Users where UserID=?";
        try {
            for(int orderID : orderIDList){
                PreparedStatement st1 = connection.prepareStatement(sql1);
                st1.setInt(1, orderID);
                st1.executeUpdate();
            }
            PreparedStatement st2 = connection.prepareStatement(sql2);
            st2.setInt(1, userID);
            st2.executeUpdate();
            
            PreparedStatement st3 = connection.prepareStatement(sql3);
            st3.setInt(1, userID);
            st3.executeUpdate();

            return true;
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
        return false;
    }
    
    public User getLastUser() {
        String sql = "select * from Users order by UserID desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                User u = new User(rs.getInt("UserID"), 
                        rs.getString("Username"), rs.getString("Password"), 
                        rs.getInt("Role"), rs.getString("FullName"), 
                        rs.getString("City"), rs.getString("Country"), 
                        rs.getString("Address"), rs.getString("Phone"));
                return u;
            }
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
        return null;
    }
    
    public boolean updateUserInformation(User u) {
        String sql = "update Users set Fullname=?, Country=?, City=?, Address=?, Phone=? where Username=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, u.getFullname());
            st.setString(2, u.getCountry());
            st.setString(3, u.getCity());
            st.setString(4, u.getAddress());
            st.setString(5, u.getPhone());
            st.setString(6, u.getUsername());
            st.executeUpdate();
            return true;
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
        return false;
    }
    
    public boolean updateUser(String username, String password, int userID) {
        String sql = "update Users set Username=?, Password=? where UserID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, password);
            st.setInt(3, userID);
            st.executeUpdate();
            return true;
        } catch (SQLException sqle) {
            System.out.println(sqle);
        }
        return false;
    }
    
}
