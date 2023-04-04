package dao.impl;

import dao.inter.IProductDAO;
import context.DBContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;

public class ProductDAOImpl extends context.DBContext implements IProductDAO {

    Connection connection = null;
    DBContext dBContext = new DBContext();

    @Override
    public ArrayList<Product> searchByName(String name) {
        ArrayList<Product> list = new ArrayList<>();
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(IProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "SELECT * FROM [dbo].[Product] where product_name like '%" + name + "%' ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ArrayList<String> l = getProductImage(rs.getInt("product_id"));
                Product p = Product.builder()
                        .product_id(rs.getInt(1))
                        .product_name(rs.getString(2))
                        .original_price(rs.getInt(3))
                        .sale_price(rs.getInt(4))
                        .pDescription(rs.getString(5))
                        .brief_info(rs.getString(6))
                        .status(rs.getBoolean(7))
                        .quantity(rs.getInt(8))
                        .cp_id(rs.getInt(9))
                        .update_date(rs.getDate(10))
                        .images(l)
                        .build();

                list.add(p);
            }
        } catch (SQLException e) {
            Logger.getLogger(IProductDAO.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return list;
    }

    @Override
    public Product getProductByID(int product_id) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select * from Product where product_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, product_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Product p = Product.builder()
                        .product_id(rs.getInt(1))
                        .product_name(rs.getString(2))
                        .original_price(rs.getInt(3))
                        .sale_price(rs.getInt(4))
                        .pDescription(rs.getString(5))
                        .brief_info(rs.getString(6))
                        .quantity(rs.getInt(7))
                        .status(rs.getBoolean(8))
                        .cp_id(rs.getInt(9))
                        .update_date(rs.getDate(10))
                        .images(getProductImage(rs.getInt(1))).build();
                return p;
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
    public ArrayList<String> getProductImage(int product_id) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<String> list = new ArrayList();
        String sql = "select image_url from  Product_Image pi  join Image i on i.image_id = pi.image_id where pi.product_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, product_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("image_url"));
            }
        } catch (SQLException e) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return list;
    }

    @Override
    public ArrayList<Product> getAllProduct() {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[Product]";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = Product.builder()
                        .product_id(rs.getInt(1))
                        .product_name(rs.getString(2))
                        .original_price(rs.getInt(3))
                        .sale_price(rs.getInt(4))
                        .pDescription(rs.getString(5))
                        .brief_info(rs.getString(6))
                        .quantity(rs.getInt(7))
                        .status(rs.getBoolean(8))
                        .cp_id(rs.getInt(9))
                        .update_date(rs.getDate(10))
                        .images(getProductImage(rs.getInt(1)))
                        .build();
                list.add(p);
            }
        } catch (SQLException e) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return list;
    }

    @Override
    public int getTotalProduct() {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select COUNT(product_id) from Product";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return 0;
    }

    @Override
    public int getTotalPublishedProduct() {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select COUNT(product_id) from Product where status = 1";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return 0;
    }

    @Override
    public ArrayList<Product> search(String keyword, int cid) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Product> list = new ArrayList();
        String sql = "SELECT * FROM [dbo].[Product] where 1=1";
        if (keyword != null && !keyword.equals("")) {// khac null và có ký tự nhập vào
            sql += " and product_name like '%" + keyword + "%' ";
        }

        if (cid != 0) {
            sql += " and cp_id=" + cid;
        }
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ArrayList<String> l = getProductImage(rs.getInt("product_id"));
                Product p = Product.builder()
                        .product_id(rs.getInt(1))
                        .product_name(rs.getString(2))
                        .original_price(rs.getInt(3))
                        .sale_price(rs.getInt(4))
                        .pDescription(rs.getString(5))
                        .brief_info(rs.getString(6))
                        .status(rs.getBoolean(7))
                        .quantity(rs.getInt(8))
                        .cp_id(rs.getInt(9))
                        .update_date(rs.getDate(10))
                        .images(l)
                        .build();

                list.add(p);
            }
        } catch (SQLException e) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return list;
    }

    @Override
    public List<Product> pagingProduct(int index, int recordPerPage, String searchKey, String cp_id, String type, String value) {
        try {
            // index: trang click
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Product> list = new ArrayList<>();
        //String query = "select * from Product order by product_id OFFSET ? ROWs fetch next ? rows only;"; 
        // bat dau tu trang index, moi trang in ra recourdPerpage ban ghi
        // bat dau tu row (index-1)*record_per_page , lay recordperpage tiep theo
        String query = "select * from Product where cp_id " + cp_id + " and product_name like N'%" + searchKey + "%' "
                + "order by " + value + " " + type + " offset ? rows fetch next ? rows only;";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, (index - 1) * recordPerPage);
            ps.setInt(2, recordPerPage);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Product p = Product.builder()
                        .product_id(rs.getInt(1))
                        .product_name(rs.getString(2))
                        .original_price(rs.getInt(3))
                        .sale_price(rs.getInt(4))
                        .pDescription(rs.getString(5))
                        .brief_info(rs.getString(6))
                        .status(rs.getBoolean(7))
                        .quantity(rs.getInt(8))
                        .cp_id(rs.getInt(9))
                        .update_date(rs.getDate(10))
                        .images(getProductImage(rs.getInt(1)))
                        .build();

                list.add(p);
            }
        } catch (SQLException e) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }

        return list;
    }

    @Override
    public Product getProductNew() {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select top 1 * from Product order by update_date desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                Product p = Product.builder()
                        .product_id(rs.getInt(1))
                        .product_name(rs.getString(2))
                        .original_price(rs.getInt(3))
                        .sale_price(rs.getInt(4))
                        .pDescription(rs.getString(5))
                        .brief_info(rs.getString(6))
                        .status(rs.getBoolean(7))
                        .quantity(rs.getInt(8))
                        .cp_id(rs.getInt(9))
                        .update_date(rs.getDate(10))
                        .images(getProductImage(rs.getInt(1)))
                        .build();

                return p;
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
    public List<Product> getRelatedProduct(int product_id, int category_id) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Product where product_id != ? and cp_id  = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, product_id);
            st.setInt(2, category_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = Product.builder()
                        .product_id(rs.getInt(1))
                        .product_name(rs.getString(2))
                        .original_price(rs.getInt(3))
                        .sale_price(rs.getInt(4))
                        .pDescription(rs.getString(5))
                        .brief_info(rs.getString(6))
                        .quantity(rs.getInt(7))
                        .status(rs.getBoolean(8))
                        .cp_id(rs.getInt(9))
                        .update_date(rs.getDate(10))
                        .images(getProductImage(rs.getInt(1)))
                        .build();

                list.add(p);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return list;
    }

    @Override
    public Product getProductById(int product_id) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select * from Product where product_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, product_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Product p = Product.builder()
                        .product_id(rs.getInt(1))
                        .product_name(rs.getString(2))
                        .original_price(rs.getInt(3))
                        .sale_price(rs.getInt(4))
                        .pDescription(rs.getString(5))
                        .brief_info(rs.getString(6))
                        .quantity(rs.getInt(7))
                        .status(rs.getBoolean(8))
                        .cp_id(rs.getInt(9))
                        .update_date(rs.getDate(10))
                        .images(getProductImage(rs.getInt(1)))
                        .build();

                return p;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return null;
    }

    @Override
    public int countProductByCondition(String searchKey, String cp_id) {
        try {
            // index: trang click
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        String query = "select count(*) from Product where cp_id " + cp_id + "  and product_name like N'%" + searchKey + "%' ";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return -1;
    }

    @Override
    public int getTotalProduct(String end) {
        try {
            // index: trang click
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select COUNT(*) from Product where update_date < ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, end);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return 0;
    }

    @Override
    public int getTotalProductByCategory(int i, String end) {
        try {
            // index: trang click
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select COUNT(*) from Product where cp_id = ? and update_date < ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, i);
            st.setString(2, end);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(ProductDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return 0;
    }

    @Override
    public void addImg(String img) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(IProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            String sql = "INSERT INTO [Image]([image_url]) VALUES (?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, img);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(IProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(IProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void addProduct(String product_name, int original_price, int sale_price, String pDescription, String brief_info, int quantity, boolean status, int cp_id, java.sql.Date updte_date) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(IProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            String sql = "INSERT INTO [Product] VALUES (? ,? ,? ,? ,?, ?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, product_name);
            stm.setInt(2, original_price);
            stm.setInt(3, sale_price);
            stm.setString(4, pDescription);
            stm.setString(5, brief_info);
            stm.setInt(6, quantity);
            stm.setBoolean(7, status);
            stm.setInt(8, cp_id);
            stm.setDate(9, updte_date);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(IProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(IProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void deleteProduct(int product_id) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException ex) {
            Logger.getLogger(IProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String sql = "DELETE FROM Product WHERE product_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, product_id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(IProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(IProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void deleteProductImg(int product_id) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException ex) {
            Logger.getLogger(IProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String sql = "DELETE FROM Product_Image WHERE product_id = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, product_id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(IProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(IProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void updateProduct(Product model) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException ex) {
            Logger.getLogger(IProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String sql = "UPDATE [Product]\n"
                    + "   SET [product_name] = ?\n"
                    + "      ,[original_price] = ?\n"
                    + "      ,[sale_price] = ?\n"
                    + "      ,[pDescription] = ?\n"
                    + "      ,[brief_info] = ?\n"
                    + "      ,[quantity] = ?\n"
                    + "      ,[status] = ?\n"
                    + "      ,[cp_id] = ?\n"
                    + "      ,[update_date] = ?\n"
                    + " WHERE product_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, model.getProduct_name());
            stm.setFloat(2, model.getOriginal_price());
            stm.setFloat(3, model.getSale_price());
            stm.setString(4, model.getPDescription());
            stm.setString(5, model.getBrief_info());
            stm.setInt(6, model.getQuantity());
            stm.setBoolean(7, model.isStatus());
            stm.setInt(8, model.getCp_id());
            stm.setDate(9, model.getUpdate_date());
            stm.setInt(10, model.getProduct_id());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(IProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(IProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public java.sql.Date getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = Date.valueOf(dateFormat.format(calendar.getTime()));
        return date;
    }

    @Override
    public int searchIdProduct(String product_name, int original_price, int sale_price, int quantity, boolean status, int cp_id, Date updte_date) {
        int product_id = -1;
        try {
            connection = dBContext.openConnection();
        } catch (SQLException ex) {
            Logger.getLogger(IProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String sql = "select *\n"
                    + "from Product\n"
                    + "where product_name like ? and original_price = ? and sale_price = ? and quantity = ? and [status] = ? and cp_id = ? and update_date = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + product_name + "%");
            stm.setInt(2, original_price);
            stm.setInt(3, sale_price);
            stm.setInt(4, quantity);
            stm.setBoolean(5, status);
            stm.setInt(6, cp_id);
            stm.setDate(7, updte_date);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                product_id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(IProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(IProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return product_id;
    }

    @Override
    public int searchIdImg(String img_url) {
        int img_id = -1;
        try {
            connection = dBContext.openConnection();
        } catch (SQLException ex) {
            Logger.getLogger(IProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String sql = "select *\n"
                    + "from Image\n"
                    + "where image_url like ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + img_url + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                img_id = rs.getInt(1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(IProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(IProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return img_id;
    }

    @Override
    public void addProductImg(int product_id, int img_id) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(IProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            String sql = "INSERT INTO [Product_Image] VALUES (? ,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, product_id);
            stm.setInt(2, img_id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(IProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(IProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void updateProduct(int product_id, String product_name, int original_price, int sale_price, String pDescription, String brief_info, int quantity, boolean status, int cp_id, java.sql.Date updte_date) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException ex) {
            Logger.getLogger(IProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String sql = "UPDATE [dbo].[Product]\n"
                    + "   SET [product_name] = ?\n"
                    + "      ,[original_price] = ?\n"
                    + "      ,[sale_price] = ?\n"
                    + "      ,[pDescription] = ?\n"
                    + "      ,[brief_info] = ?\n"
                    + "      ,[quantity] = ?\n"
                    + "      ,[status] = ?\n"
                    + "      ,[cp_id] = ?\n"
                    + "      ,[update_date] = ?\n"
                    + " WHERE product_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, product_name);
            stm.setInt(2, original_price);
            stm.setInt(3, sale_price);
            stm.setString(4, pDescription);
            stm.setString(5, brief_info);
            stm.setInt(6, quantity);
            stm.setBoolean(7, status);
            stm.setInt(8, cp_id);
            stm.setDate(9, updte_date);
            stm.setInt(10, product_id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(IProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(IProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
