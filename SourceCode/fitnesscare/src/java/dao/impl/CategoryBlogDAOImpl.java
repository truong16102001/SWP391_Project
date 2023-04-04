/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CategoryBlog;

/**
 *
 * @author ThinkPro
 */
public class CategoryBlogDAOImpl {

    Connection connection = null;
    DBContext dBContext = new DBContext();

    public List<CategoryBlog> getAllCategoryBlog() {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<CategoryBlog> list = new ArrayList<>();
        String sql = "Select * from CategoryBlog where status = 1";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CategoryBlog c = CategoryBlog.builder()
                        .cb_id(rs.getInt(1))
                        .cb_name(rs.getString(2))
                        .status(rs.getBoolean(3))
                        .build();
                list.add(c);
            }
        } catch (SQLException e) {
            Logger.getLogger(CategoryBlogDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }

        return list;
    }

}
