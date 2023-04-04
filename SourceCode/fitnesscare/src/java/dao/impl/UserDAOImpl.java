package dao.impl;

import dao.inter.IUserDAO;
import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Chart;
import model.User;

/**
 *
 * @author ThinkPro
 */
public class UserDAOImpl extends context.DBContext implements IUserDAO {

    Connection connection = null;
    DBContext dBContext = new DBContext();

    @Override
    public User checkUserExist(String email) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "select * from [User]  where email = ?  ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

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
                return u;
            }
        } catch (SQLException e) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return null;
    }

    @Override
    public ArrayList<User> getAllTechnicalStaff() {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<User> list = new ArrayList<>();
        String sql = "select * from [User] where role_id = 3";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User u = User.builder().user_id(rs.getInt("user_id"))
                        .fullName(rs.getString("fullName"))
                        .gender(rs.getBoolean("gender"))
                        .email(rs.getString("email"))
                        .phone(rs.getString("phone"))
                        .address(rs.getString("address"))
                        .build();
                list.add(u);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return list;
    }

    @Override
    public void register(String fullName, String password, String gender, String email, String phone, String address) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "insert into [User]\n"
                + "values (?,?,'',?,?,?,?,'1','4', getdate())";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, fullName);
            st.setString(2, password);
            st.setBoolean(3, gender.equals("True"));
            st.setString(4, email);
            st.setString(5, phone);
            st.setString(6, address);
            st.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public User login(String email, String password) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "select * from [User]  where email = ? and [password]= ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

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
                return u;
            }
        } catch (SQLException e) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return null;
    }

    @Override
    public User getUserByPassword(int user_id, String old_pass) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select * from [User] where user_id = ? and password = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, user_id);
            ps.setString(2, old_pass);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = User.builder()
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
                return user;
            }
        } catch (SQLException e) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return null;
    }

    @Override
    public void changePassword(int user_id, String new_pass1) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "UPDATE [dbo].[User]\n"
                + "   SET \n"
                + "      [password] = ?\n"
                + " WHERE user_id = ?\n";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, new_pass1);
            ps.setInt(2, user_id);
            ps.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public String getUrlImageById(int user_id) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "SELECT [avatar]\n"
                + "  FROM [Fashion_Shop_Online].[dbo].[User]\n"
                + "  Where user_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, user_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getString(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return null;
    }

    @Override
    public void editUserProfile(String fullName, String url_avatar, String gender, String phone, String address, int uid) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "update dbo.[User]\n"
                + "set [fullName] = ?,\n"
                + "avatar = ?,\n"
                + "gender = ?,\n"
                + "phone = ?,\n"
                + "[address] = ?\n"
                + "where user_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, fullName);
            st.setString(2, url_avatar);
            st.setString(3, gender);
            st.setString(4, phone);
            st.setString(5, address);
            st.setInt(6, uid);
            st.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public User getUserById(int uid) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select * from [User] where user_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, uid);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = User.builder()
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
                return user;
            }
        } catch (SQLException e) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return null;
    }

    @Override
    public User getUserByEmail(String email) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select * from [User] where email = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = User.builder()
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
                return user;
            }
        } catch (SQLException e) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return null;
    }

    @Override
    public User checkEmailExisted(int parseInt, String uEmail) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "select * from [User]  where user_id != ? and  email = ?  ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, parseInt);
            ps.setString(2, uEmail);
            ResultSet rs = ps.executeQuery();

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
                return u;
            }
        } catch (SQLException e) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return null;
    }

    public List<User> getSellers() {
        List<User> sellers = new ArrayList();
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select * from [User]  where role_id = 1 or role_id = 2  ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
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
                sellers.add(u);
            }
        } catch (SQLException e) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return sellers;
    }

    @Override
    public List<Chart> getChartCustomerArea(String start, int day) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Chart> list = new ArrayList<>();
        for (int i = 0; i < day; i++) {
            int value = 0;
            String sql = "select count(*) from [User] where role_id = 4 and  updated_date <= DATEADD(DAY, ?, ?)";
            try {
                PreparedStatement st = connection.prepareStatement(sql);
                st.setInt(1, i);
                st.setString(2, start);
                ResultSet rs = st.executeQuery();
                while (rs.next()) {
                    value = rs.getInt(1);
                }
                sql = "select  DATEADD(DAY, ?, ?)";
                st = connection.prepareStatement(sql);
                st.setInt(1, i);
                st.setString(2, start);
                rs = st.executeQuery();
                while (rs.next()) {
                    Chart c = Chart.builder()
                            .date(rs.getDate(1))
                            .value(value)
                            .build();
                    list.add(c);
                }

            } catch (SQLException e) {
                Logger.getLogger(UserDAOImpl.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        try {
            dBContext.closeConnection(connection);
        } catch (SQLException e) {
        }
        return list;
    }

    @Override
    public int CountCustomer() {
        try {
            connection = dBContext.openConnection();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDAOImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        int value = 0;
        String sql = "select count(*) from [User] where role_id = 4 ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                value = rs.getInt(1);

            }
        } catch (SQLException e) {
            Logger.getLogger(UserDAOImpl.class
                    .getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return value;
    }

    @Override
    public int countStaff() {
        try {
            connection = dBContext.openConnection();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(UserDAOImpl.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        int value = 0;
        String sql = "select count(*) from [User] where role_id = 2 or role_id = 3 ";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                value = rs.getInt(1);

            }
        } catch (SQLException e) {
            Logger.getLogger(UserDAOImpl.class
                    .getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return value;
    }
}
