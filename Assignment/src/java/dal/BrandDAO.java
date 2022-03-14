package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Brand;

/**
 *
 * @author Duy Phuong
 */
public class BrandDAO extends DBContext {
    /* Brand DAO */
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
    public Brand getBrand(int brandID) {
        String sql = "select * from Brands where BrandID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, brandID);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Brand b = new Brand(rs.getInt("BrandID"),
                        rs.getString("BrandName"),
                        rs.getString("BrandLogo"));
                return b;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public int addBrand(Brand b) {
        int brandID = getLastBrand().getBrandID()+1;
        String sql = "insert into Brands (BrandID, BrandName, BrandLogo) values (?, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, brandID);
            st.setString(2, b.getBrandName());
            st.setString(3, b.getBrandLogo());
            st.executeUpdate();
            return brandID;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return -1;
    }
    public boolean updateBrand(Brand b) {
        String sql = "update Brands set BrandName=?, BrandLogo=? where BrandID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, b.getBrandName());
            st.setString(2, b.getBrandLogo());
            st.setInt(3, b.getBrandID());
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    public boolean deleteBrand(int brandID) {
        String sql = "delete Brands where BrandID=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, brandID);
            st.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
    
    public Brand getLastBrand() {
        String sql = "select * from Brands order by BrandID desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Brand b = new Brand(
                        rs.getInt("BrandID"),
                        rs.getString("BrandName"),
                        rs.getString("BrandLogo"));
                return b;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
}
