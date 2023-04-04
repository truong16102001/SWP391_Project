package dao.impl;

import dao.inter.ICategoryProductDAO;
import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CategoryProduct;

/**
 *
 * @author ThinkPro
 */
public class CategoryProductDAOImpl extends DBContext implements ICategoryProductDAO{

    Connection connection = null;
    DBContext dBContext = new DBContext();

    @Override
    public ArrayList<CategoryProduct> getProductCategories() {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CategoryProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<CategoryProduct> list = new ArrayList();
        String sql = "SELECT [cp_id]\n"
                + "      ,[cp_name]\n"
                + "      ,[status]\n"
                + "  FROM [dbo].[CategoryProduct]";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                CategoryProduct c = CategoryProduct.builder().cp_id(rs.getInt((1)))
                        .cp_name(rs.getString(2))
                        .status(rs.getBoolean(3)).build();
                list.add(c);
            }
        } catch (SQLException e) {
            Logger.getLogger(CategoryProductDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return list;
    }

    @Override
    public CategoryProduct getCategoryByID(int cid) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CategoryProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "SELECT * "
                + "  FROM [dbo].[CategoryProduct]\n"
                + "  where cp_id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, cid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                CategoryProduct c = CategoryProduct.builder()
                        .cp_id(rs.getInt(1))
                        .cp_name(rs.getString(2))
                        .status(rs.getBoolean(3))
                        .build();
                return c;
            }
        } catch (SQLException e) {
            Logger.getLogger(CategoryProductDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return null;
    }

    @Override
    public ArrayList<CategoryProduct> getAllCategory() {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CategoryProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<CategoryProduct> list = new ArrayList<>();
        String sql = "Select * from CategoryProduct where status = 1";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CategoryProduct c = CategoryProduct.builder()
                        .cp_id(rs.getInt(1))
                        .cp_name(rs.getString(2))
                        .status(rs.getBoolean(3))
                        .build();

                list.add(c);
            }
        } catch (SQLException e) {
            Logger.getLogger(CategoryProductDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return list;
    }

}
