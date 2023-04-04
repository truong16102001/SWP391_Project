package dao.impl;

import context.DBContext;
import dao.inter.IBlogDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Blog;
import model.User;

/**
 *
 * @author ThinkPro
 */
public class BlogDAOImpl extends DBContext implements IBlogDAO {

    Connection connection = null;
    DBContext dBContext = new DBContext();

    @Override
    public int getTotalBlog(String searchKey, String cb_idStr, String status, String author) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "Select count(*) from Blog "
                + "where cb_id " + cb_idStr + " and [status] " + status + " and author_id " + author + " and title like N'%" + searchKey + "%'\n";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(BlogDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return 0;
    }

    @Override
    public List<Blog> getBlogWithPaging(int page, int PAGE_SIZE, String searchKey, String cb_idStr, String type, String value, String status, String author) {

        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Blog> list = new ArrayList<>();
        String sql = "select * from Blog\n"
                + "where cb_id " + cb_idStr + " and [status] " + status + " and author_id " + author + " and [title] like N'%" + searchKey + "%'\n"
                + " order by " + value + " " + type + " offset ? rows fetch next ? rows only;";

        System.out.println(sql);
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, (page - 1) * PAGE_SIZE);
            st.setInt(2, PAGE_SIZE);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Blog c = Blog.builder()
                        .blog_id(rs.getInt(1))
                        .title(rs.getString(2))
                        .content(rs.getString(3))
                        .thumbnail(rs.getString(4))
                        .brief_info(rs.getString(5))
                        .author_id(rs.getInt(6))
                        .cb_id(rs.getInt(7))
                        .status(rs.getBoolean(8))
                        .updated_date(rs.getDate(9))
                        .build();
                list.add(c);
            }
            return list;
        } catch (SQLException e) {
            Logger.getLogger(BlogDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return null;
    }

    @Override
    public List<User> getAllAuthor() {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<User> list = new ArrayList<>();
        String sql = "select * from [User] where role_id = 2 or role_id = 1";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                User u = User.builder()
                        .user_id(rs.getInt(1))
                        .fullName(rs.getString(2))
                        .password(rs.getString(3))
                        .avatar(rs.getString(4))
                        .gender(rs.getBoolean(5))
                        .email(rs.getString(6))
                        .phone(rs.getString(7))
                        .address(rs.getString(8))
                        .status(rs.getBoolean(9))
                        .role_id(rs.getInt(10))
                        .build();
                list.add(u);
            }
        } catch (SQLException e) {
            Logger.getLogger(BlogDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return list;
    }

    @Override
    public Blog getBlogNew() {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Blog> list = new ArrayList<>();
        String sql = "select top 1 * from Blog where status = 1\n"
                + "order by updated_date desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Blog c = Blog.builder()
                        .blog_id(rs.getInt(1))
                        .title(rs.getString(2))
                        .content(rs.getString(3))
                        .thumbnail(rs.getString(4))
                        .brief_info(rs.getString(5))
                        .author_id(rs.getInt(6))
                        .cb_id(rs.getInt(7))
                        .status(rs.getBoolean(8))
                        .updated_date(rs.getDate(9))
                        .build();
                return c;
            }
        } catch (SQLException e) {
            Logger.getLogger(BlogDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return null;
    }

    @Override
    public void addNewBlog(String title, int user_id, String content, String brief_infor, int category_id, boolean status, String url_thumbnail) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String sql = "INSERT INTO [dbo].[Blog]\n"
                    + "           ([title]\n"
                    + "           ,[author_id]\n"
                    + "           ,[content]\n"
                    + "           ,[thumbnail]\n"
                    + "           ,[brief_info]\n"
                    + "           ,[cb_id]\n"
                    + "           ,[status], [updated_date])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?,?, getdate())";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, title);
            st.setInt(2, user_id);
            st.setString(3, content);
            st.setString(4, url_thumbnail);
            st.setString(5, brief_infor);
            st.setInt(6, category_id);
            st.setBoolean(7, status);

            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public void updateStatusBlog(int blog_id, boolean status) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String sql = "UPDATE Blog\n"
                    + "SET [status] = ?\n"
                    + "WHERE blog_id = ?;";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setBoolean(1, status);
            ps.setInt(2, blog_id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public Blog getBlogByBlogId(int blog_id) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String sql = "select * from Blog where blog_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, blog_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Blog c = Blog.builder()
                        .blog_id(rs.getInt(1))
                        .title(rs.getString(2))
                        .content(rs.getString(3))
                        .thumbnail(rs.getString(4))
                        .brief_info(rs.getString(5))
                        .author_id(rs.getInt(6))
                        .cb_id(rs.getInt(7))
                        .status(rs.getBoolean(8))
                        .updated_date(rs.getDate(9))
                        .build();
                return c;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return null;
    }

    @Override
    public void updateBlog(int blog_id, String title, int user_id, String content, String brief_infor, int category_id, boolean status, String url_Thumbnail) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String sql = "UPDATE [dbo].[Blog]\n"
                    + "   SET [title] = ?\n"
                    + "      ,[author_id] = ?\n"
                    + "      ,[updated_date] = getdate()\n"
                    + "      ,[content] = ?\n"
                    + "      ,[thumbnail] = ?\n"
                    + "      ,[brief_info] = ?\n"
                    + "      ,[cb_id] = ?\n"
                    + "      ,[status] = ?\n"
                    + " WHERE blog_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, title);
            st.setInt(2, user_id);
            st.setString(3, content);
            st.setString(4, url_Thumbnail);
            st.setString(5, brief_infor);
            st.setInt(6, category_id);
            st.setBoolean(7, status);
            st.setInt(8, blog_id);

            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }

    }

    @Override
    public List<Blog> getBLogByCategory(int cb_id) {
        List<Blog> list = new ArrayList();
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String sql = "select top 5 * from Blog where cb_id = ? and status = 1 order by updated_date";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, cb_id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Blog c = Blog.builder()
                        .blog_id(rs.getInt(1))
                        .title(rs.getString(2))
                        .content(rs.getString(3))
                        .thumbnail(rs.getString(4))
                        .brief_info(rs.getString(5))
                        .author_id(rs.getInt(6))
                        .cb_id(rs.getInt(7))
                        .status(rs.getBoolean(8))
                        .updated_date(rs.getDate(9))
                        .build();

                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BlogDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return list;
    }

}
