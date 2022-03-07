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
}
