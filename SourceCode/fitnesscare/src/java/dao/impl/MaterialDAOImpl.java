package dao.impl;

import context.DBContext;
import dao.inter.IMaterialDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Material;

public class MaterialDAOImpl extends context.DBContext implements IMaterialDAO {

    Connection connection = null;
    DBContext dBContext = new DBContext();

    @Override
    public ArrayList<Material> list() {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException ex) {
            Logger.getLogger(MaterialDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MaterialDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Material> materials = new ArrayList<>();
        try {
            String sql = "select material_id, material_name, quantity, broken_quantity from Material";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Material material = Material.builder()
                        .material_id(rs.getInt("material_id"))
                        .material_name(rs.getString("material_name"))
                        .quantity(rs.getInt("quantity"))
                        .broken_quantity(rs.getInt("broken_quantity"))
                        .build();
                materials.add(material);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MaterialDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(MaterialDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return materials;
    }

    @Override
    public Material getMaterialById(int id) {
        Material material = null;
        try {
            connection = dBContext.openConnection();

        } catch (SQLException ex) {
            Logger.getLogger(MaterialDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MaterialDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String sql = "Select material_id, material_name, quantity, broken_quantity from Material where material_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                material = Material.builder()
                        .material_id(id)
                        .material_name(rs.getString("material_name"))
                        .quantity(rs.getInt("quantity"))
                        .broken_quantity(rs.getInt("broken_quantity"))
                        .build();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MaterialDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(MaterialDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            return material;
        }
    }

    @Override
    public void update(Material model) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException ex) {
            Logger.getLogger(MaterialDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MaterialDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String sql = "UPDATE [Material]\n"
                    + "   SET material_name = ?\n"
                    + "      ,quantity = ?\n"
                    + "      ,broken_quantity = ?\n"
                    + " WHERE material_id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, model.getMaterial_name());
            stm.setInt(2, model.getQuantity());
            stm.setInt(3, model.getBroken_quantity());
            stm.setInt(4, model.getMaterial_id());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MaterialDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(MaterialDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void delete(int id) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException ex) {
            Logger.getLogger(MaterialDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MaterialDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String sql = "DELETE FROM Material WHERE material_id = ?;";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MaterialDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(MaterialDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ArrayList<Material> searchByName(String name) {
        ArrayList<Material> list = new ArrayList<>();
        try {
            connection = dBContext.openConnection();
        } catch (SQLException ex) {
            Logger.getLogger(MaterialDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MaterialDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String sql = "Select material_id, material_name, quantity, broken_quantity from Material where material_name like ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, "%" + name + "%");
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Material material = Material.builder()
                        .material_id(rs.getInt("material_id"))
                        .material_name(rs.getString("material_name"))
                        .quantity(rs.getInt("quantity"))
                        .broken_quantity(rs.getInt("broken_quantity"))
                        .build();

                list.add(material);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MaterialDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(MaterialDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }

    @Override
    public void add(String name, int quantity, int broken_quantity) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException ex) {
            Logger.getLogger(MaterialDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MaterialDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String sql = "INSERT INTO Material values (?,?,?)"; 
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, name);
            stm.setInt(2, quantity);
            stm.setInt(3, broken_quantity);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MaterialDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException ex) {
                Logger.getLogger(MaterialDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
