package dao.impl;

import context.DBContext;
import dao.inter.ICartDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Cart;

public class CartDAOImpl extends DBContext implements ICartDAO {

    Connection connection = null;
    DBContext dBContext = new DBContext();

    @Override
    public Cart checkCart(int user_id, int product_id) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select * from Cart where [customer_id] = ? and product_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, user_id);
            st.setInt(2, product_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Cart c = Cart.builder()
                        .cart_id(rs.getInt(1))
                        .product_id(rs.getInt(2))
                        .product_name(rs.getString(3))
                        .product_price(rs.getInt(4))
                        .quantity(rs.getInt(5))
                        .total_cost(rs.getInt(6))
                        .customer_id(rs.getInt(7))
                        .product_image(rs.getString(8))
                        .build();

                return c;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return null;
    }

    @Override
    public void addToCart(int product_id, String product_name, int price, int quantity, int total_cost, int user_id) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String sql = "insert into Cart values(?,?,?,?,?,?,?);";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, product_id);
            st.setString(2, product_name);
            st.setInt(3, price);
            st.setInt(4, quantity);
            st.setInt(5, total_cost);
            st.setInt(6, user_id);
            st.setString(7, getImageProduct(product_id).get(0));
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public void updateQuantityProductInCart(int product_id, int quantity, float total_cost, int user_id) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String sql = "UPDATE [dbo].[Cart]\n"
                    + "   SET [quantity] = ? \n"
                    + "      ,[total_cost] = ? \n"
                    + " WHERE [product_id] = ? and [customer_id] = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, quantity);
            st.setFloat(2, total_cost);
            st.setInt(3, product_id);
            st.setInt(4, user_id);
            st.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public List<Cart> getCartInfoByUserId(int user_id) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Cart> list = new ArrayList<>();
        String sql = "select * from Cart where [customer_id] = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, user_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Cart c = Cart.builder()
                        .cart_id(rs.getInt(1))
                        .product_id(rs.getInt(2))
                        .product_name(rs.getString(3))
                        .product_price(rs.getInt(4))
                        .quantity(rs.getInt(5))
                        .total_cost(rs.getInt(6))
                        .customer_id(rs.getInt(7))
                        .product_image(rs.getString(8))
                        .build();

                list.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return list;
    }

    @Override
    public List<String> getImageProduct(int product_id) {
        List<String> list = new ArrayList();
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            String sql = "select i.image_url from Image i, Product_Image pi where pi.product_id = ? and pi.image_id = i.image_id";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, product_id);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                list.add(rs.getString("image_url"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return list;
    }

    @Override
    public void updateQuantityCart(int quantity, int cart_id, int product_id, int sum) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String sql = "UPDATE [dbo].[Cart]\n"
                    + "   SET [quantity] = ?, total_cost = ?\n"
                    + " WHERE cart_id = ? and product_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, quantity);
            st.setInt(2, sum);
            st.setInt(3, cart_id);
            st.setInt(4, product_id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public void deleteCart(int product_id, int user_id) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String sql = "DELETE FROM [dbo].[Cart]\n"
                    + "      WHERE [product_id] = ? and [customer_id] = ? ";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, product_id);
            st.setInt(2, user_id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public int getTotalItemInCart(int user_id) {
        int total = 0;
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select sum(quantity) as totalItem from Cart where customer_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, user_id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                total = rs.getInt("totalItem");
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return total;
    }

    @Override
    public void deleteCartByUserId(int user_id) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String sql = "DELETE FROM [dbo].[Cart]\n"
                    + "      WHERE [customer_id] = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, user_id);
            st.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public Cart getCartByCartID_ProductID(int cart_id, int product_id) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select * from Cart where cart_id = ? and product_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cart_id);
            st.setInt(2, product_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Cart c = Cart.builder()
                        .cart_id(rs.getInt(1))
                        .product_id(rs.getInt(2))
                        .product_name(rs.getString(3))
                        .product_price(rs.getInt(4))
                        .quantity(rs.getInt(5))
                        .total_cost(rs.getInt(6))
                        .customer_id(rs.getInt(7))
                        .product_image(rs.getString(8))
                        .build();

                return c;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CartDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return null;
    }

}
