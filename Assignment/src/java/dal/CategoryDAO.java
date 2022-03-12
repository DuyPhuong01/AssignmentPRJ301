package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Category;

/**
 *
 * @author Duy Phuong
 */
public class CategoryDAO extends DBContext {

    /* Category DAO */
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
        String sql = "insert into Categories (CategoryID, CategoryName, Description, Status) values (?, ?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, getLastCategory().getCategoryID()+1);
            st.setString(2, c.getCategoryName());
            st.setString(3, c.getDescription());
            st.setInt(4, c.getStatus());
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
    public Category getLastCategory() {
        String sql = "select * from Categories order by CategoryID desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
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
}
