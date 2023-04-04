package dao.impl;

import context.DBContext;
import dao.inter.IOrderDetailDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cart;
import model.OrderDetail;

public class OrderDetailDAOImpl extends DBContext implements IOrderDetailDAO {

    Connection connection = null;
    DBContext dBContext = new DBContext();

    @Override
    public void addCartToOrder(List<Cart> listCart, int order_id) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(OrderDetailDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            for (Cart cart : listCart) {
                String sql = "INSERT INTO [dbo].[OrderDetail] values (?,?,?,?,?,?)";
                PreparedStatement st = connection.prepareStatement(sql);

                st.setInt(1, order_id);
                st.setInt(2, cart.getProduct_id());
                st.setString(3, cart.getProduct_name());
                st.setFloat(4, cart.getProduct_price());
                st.setInt(5, cart.getQuantity());
                st.setFloat(6, cart.getTotal_cost());

                st.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDetailDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public List<OrderDetail> getOrderDetailByOrder(int orderId) {

        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(OrderDetailDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<OrderDetail> list = new ArrayList<>();
        try {
            String sql = "select * from OrderDetail where order_id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail od = OrderDetail.builder().od_id(rs.getInt(1)).order_id(rs.getInt(2)).product_id(rs.getInt(3)).product_name(rs.getString(4))
                        .product_price(rs.getInt(5)).quantity(rs.getInt(6)).total_cost(rs.getInt(7)).build();
                list.add(od);
            }
        } catch (SQLException ex) {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return list;
    }

}
