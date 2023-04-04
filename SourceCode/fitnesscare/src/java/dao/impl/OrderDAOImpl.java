package dao.impl;

import dao.inter.IOrderDAO;
import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Chart;
import model.Order;
import model.StatusOrder;

public class OrderDAOImpl extends context.DBContext implements IOrderDAO {

    Connection connection = null;
    DBContext dBContext = new DBContext();

    @Override
    public Order checkProductOrderByUser(int user_id, int product_id) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "select * from [Order] o join [OrderDetail] od on o.order_id = od.order_id\n"
                + "where o.customer_id = ? and od.product_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, user_id);
            st.setInt(2, product_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Order c = Order.builder()
                        .order_id(rs.getInt(1))
                        .build();
                return c;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return null;
    }

    @Override
    public int createNewOrder(int sum, String fullname, String email, String phone, String address, int user_id, String note, String payment_method) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "INSERT INTO [dbo].[Order]\n"
                + "           (order_Date \n"
                + "           ,[total_cost]\n"
                + "           ,[customer_name]\n"
                + "           ,[email]\n"
                + "           ,[phone]\n"
                + "           ,[address]\n"
                + "           ,[customer_id]\n"
                + "           ,[note], payment_method, status_order_id)\n"
                + "     VALUES\n"
                + "           (GETDATE(),?,?,?,?,?,?,?,?,'4') ";
        try {
            PreparedStatement st = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setInt(1, sum);
            st.setString(2, fullname);
            st.setString(3, email);
            st.setString(4, phone);
            st.setString(5, address);
            st.setInt(6, user_id);
            st.setString(7, note);
            st.setString(8, payment_method);
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return 0;
    }

    @Override
    public void updatePaymentMethod(String method, int order_id) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String sql = "update [Order] set payment_method = ? where order_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, method);
            st.setInt(2, order_id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public Order getOrderByID(int order_id) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            String sql = "select * from [Order] where order_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, order_id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Order o = Order.builder()
                        .order_id(rs.getInt(1))
                        .order_date(rs.getDate(2))
                        .customer_id(rs.getInt(3))
                        .customer_name(rs.getString(4))
                        .phone(rs.getString(5))
                        .address(rs.getString(6))
                        .seller_id(rs.getInt(7))
                        .total_cost(rs.getInt(8))
                        .status_order_id(rs.getInt(9))
                        .note(rs.getString(10))
                        .email(rs.getString(11))
                        .paymentmethod(rs.getString(12)).build();
                return o;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return null;
    }

    @Override
    public List<Order> getAllOrder() {
        try {
            connection = dBContext.openConnection();
        } catch (ClassNotFoundException | SQLException e) {
        }
        List<Order> list = new ArrayList();
        String sql = "select  * from [Order]";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order o = Order.builder().order_id(rs.getInt(1)).order_date(rs.getDate(2)).customer_id(rs.getInt(3)).customer_name(rs.getString(4)).phone(rs.getString(5))
                        .address(rs.getString(6)).seller_id(rs.getInt(7)).total_cost(rs.getInt(8)).status_order_id(rs.getInt(9)).note(rs.getString(10))
                        .email(rs.getString(11)).paymentmethod(rs.getString(12)).build();
                list.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return list;
    }

    @Override
    public List<Order> pagingOrder(int index, int RECORD_PER_PAGE, String key, String status) {
        try {
            connection = dBContext.openConnection();
        } catch (ClassNotFoundException | SQLException e) {

        }
        List<Order> list = new ArrayList();
        String sql = "select * from [Order] where status_order_id " + status + " and ((order_id like N'%" + key + "%') or (order_Date like N'%" + key + "%') or"
                + " (customer_name like N'%" + key + "%') or (phone like N'%" + key + "%'))  order by order_id  offset ? rows fetch next ? rows only;";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, (index - 1) * RECORD_PER_PAGE);
            ps.setInt(2, RECORD_PER_PAGE);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order o = Order.builder().order_id(rs.getInt(1)).order_date(rs.getDate(2)).customer_id(rs.getInt(3)).customer_name(rs.getString(4)).phone(rs.getString(5))
                        .address(rs.getString(6)).seller_id(rs.getInt(7)).total_cost(rs.getInt(8)).status_order_id(rs.getInt(9)).note(rs.getString(10))
                        .email(rs.getString(11)).paymentmethod(rs.getString(12)).build();
                list.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return list;
    }

    @Override
    public List<StatusOrder> getAllStatusOrder() {
        try {
            connection = dBContext.openConnection();
        } catch (ClassNotFoundException | SQLException e) {
        }
        List<StatusOrder> list = new ArrayList();
        String sql = "select  * from StatusOrder";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                StatusOrder so = StatusOrder.builder().status_order_id(rs.getInt(1)).status_order_name(rs.getString(2)).status(rs.getBoolean(3)).build();
                list.add(so);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return list;
    }

    @Override
    public int getNextOrderId() {
        try {
            connection = dBContext.openConnection();
        } catch (ClassNotFoundException | SQLException e) {
        }
        int lastIndex = 0;
        String sql = "SELECT TOP 1 * FROM [Order] order by order_id desc";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Order o = Order.builder().order_id(rs.getInt(1)).order_date(rs.getDate(2)).customer_id(rs.getInt(3)).customer_name(rs.getString(4)).phone(rs.getString(5))
                        .address(rs.getString(6)).seller_id(rs.getInt(7)).total_cost(rs.getInt(8)).status_order_id(rs.getInt(9)).note(rs.getString(10))
                        .email(rs.getString(11)).paymentmethod(rs.getString(12)).build();
                lastIndex = o.getOrder_id() + 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return lastIndex;
    }

    @Override
    public List<Integer> getStatisticIncome() {
        // Map<Integer, Integer> map = new HashMap<>();
        //int[] arr = new int[12];
        List<Integer> list = new ArrayList();
        try {
            connection = dBContext.openConnection();
        } catch (ClassNotFoundException | SQLException e) {
        }
        try {
            for (int i = 1; i < 12; i++) {
                String sql = "select sum(total_cost) from [Order] where month(order_Date)  = ? and year(order_Date) = 2023";
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setInt(1, i);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    list.add(rs.getInt((1)));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return list;
    }

    @Override
    public int countOrderByStatus(String key, String status) {
        try {
            connection = dBContext.openConnection();
        } catch (ClassNotFoundException | SQLException e) {
        }
        try {

            String sql = "select count(*) from [Order] where status_order_id " + status + " and ((order_id like N'%" + key + "%') or (order_Date like N'%" + key + "%') or"
                    + " (customer_name like N'%" + key + "%') or (phone like N'%" + key + "%'))";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return -1;
    }

    @Override
    public void updateStatusOrder(int user_id, int status_order_id, int order_id) {
        try {
            connection = dBContext.openConnection();
        } catch (ClassNotFoundException | SQLException e) {
        }
        try {

            String sql = "UPDATE [Order]\n"
                    + "SET seller_id = ?, status_order_id = ?\n"
                    + "WHERE order_id = ?; ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, user_id);
            ps.setInt(2, status_order_id);
            ps.setInt(3, order_id);
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public List<Order> filterOrderByStatus(String statusStr) {
        try {
            connection = dBContext.openConnection();
        } catch (ClassNotFoundException | SQLException e) {

        }
        List<Order> list = new ArrayList();
        String sql = "select * from [Order] where status_order_id " + statusStr;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order o = Order.builder().order_id(rs.getInt(1)).order_date(rs.getDate(2)).customer_id(rs.getInt(3)).customer_name(rs.getString(4)).phone(rs.getString(5))
                        .address(rs.getString(6)).seller_id(rs.getInt(7)).total_cost(rs.getInt(8)).status_order_id(rs.getInt(9)).note(rs.getString(10))
                        .email(rs.getString(11)).paymentmethod(rs.getString(12)).build();
                list.add(o);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return list;
    }

    @Override
    public List<Chart> getChartRevenueArea(String start, int day) {
        try {
            connection = dBContext.openConnection();
        } catch (ClassNotFoundException | SQLException e) {

        }
        List<Chart> list = new ArrayList<>();
        for (int i = 0; i < day; i++) {
            int value = 0;
            try {
                String sql = "SELECT SUM(total_cost)\n"
                        + "FROM (\n"
                        + "  SELECT *\n"
                        + "  FROM [Order]\n"
                        + "  WHERE status_order_id = 1 \n"
                        + ") AS orders_with_status\n"
                        + "WHERE order_Date BETWEEN ? AND DATEADD(DAY,?,?)";
                PreparedStatement st = connection.prepareStatement(sql);
                st.setString(1, start);
                st.setInt(2, i);
                st.setString(3, start);
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    value = rs.getInt(1);
                }
                sql = "select  DATEADD(DAY, ?, ?)";
                st = connection.prepareStatement(sql);
                st.setInt(1, i);
                st.setString(2, start);
                rs = st.executeQuery();
                if (rs.next()) {
                    Chart c = Chart.builder()
                            .date(rs.getDate(1))
                            .value(value)
                            .build();
                    list.add(c);
                }
            } catch (SQLException ex) {
                Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            dBContext.closeConnection(connection);
        } catch (SQLException e) {
        }

        return list;
    }

    @Override
    public int gettotalOrderByStatus(int status_order_id, String start, int numberOfDay) {
        try {
            connection = dBContext.openConnection();
        } catch (ClassNotFoundException | SQLException e) {
        }
        int sum = 0;
        for (int i = 0; i < numberOfDay; i++) {
            try {
                String sql = "select count(order_id) from [Order] where status_order_id = ? and order_Date between ? and DATEADD(DAY, ?,?)";
                PreparedStatement st = connection.prepareStatement(sql);
                st.setInt(1, status_order_id);
                st.setString(2, start);
                st.setInt(3, i);
                st.setString(4, start);
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    sum += rs.getInt(1);
                }
            } catch (SQLException ex) {
                Logger.getLogger(OrderDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        try {
            dBContext.closeConnection(connection);
        } catch (SQLException e) {
        }
        return sum;
    }

}
