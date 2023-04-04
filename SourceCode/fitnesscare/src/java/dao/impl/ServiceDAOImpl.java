/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.inter.IServiceDAO;
import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Service;

/**
 *
 * @author dell
 */
public class ServiceDAOImpl extends DBContext implements IServiceDAO {

    Connection connection = null;
    DBContext dBContext = new DBContext();

    @Override
    public Service getServiceByID(int service_id) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ServiceDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select * from Service where service_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, service_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Service s = Service.builder().service_id(rs.getInt(1))
                        .service_name(rs.getString(2))
                        .price(rs.getInt(3))
                        .status(rs.getBoolean(4))
                        .service_Description(rs.getString(5))
                        .s_image(rs.getString(6))
                        .build();
                return s;
            }

        } catch (SQLException e) {
            Logger.getLogger(ServiceDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return null;
    }

    @Override
    public ArrayList<Service> getAllService() {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ServiceDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Service> list = new ArrayList<>();
        String sql = "SELECT * "
                + "  FROM Service";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Service s = Service.builder().service_id(rs.getInt(1))
                        .service_name(rs.getString(2))
                        .price(rs.getInt(3))
                        .status(rs.getBoolean(4))
                        .service_Description(rs.getString(5))
                        .s_image(rs.getString(6))
                        .build();
                list.add(s);
            }
        } catch (SQLException e) {
            Logger.getLogger(ServiceDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return list;
    }

    @Override
    public int getTotalService() {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ServiceDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select COUNT(service_id) from Service";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(ServiceDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return 0;
    }

    @Override
    public int getTotalPublishedService() {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ServiceDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select COUNT(service_id) from Service where status = 1";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(ServiceDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return 0;
    }

    @Override
    public ArrayList<Service> search(String keyword) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ServiceDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Service> list = new ArrayList();
        String sql = "SELECT *"
                + "  FROM Service "
                + "where 1=1";
        if (keyword != null && !keyword.equals("")) {// khac null và có ký tự nhập vào
            sql += " and service_name like '%" + keyword + "%' ";
        }

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Service s = Service.builder().service_id(rs.getInt(1))
                        .service_name(rs.getString(2))
                        .price(rs.getInt(3))
                        .status(rs.getBoolean(4))
                        .service_Description(rs.getString(5))
                        .s_image(rs.getString(6))
                        .build();

                list.add(s);
            }
        } catch (SQLException e) {
            Logger.getLogger(ServiceDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return list;
    }

    @Override
    public ArrayList<Service> pagingService(int index, int recordPerPage, String searchKey, String type, String value) { // index: trang click
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ServiceDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Service> list = new ArrayList<>();
        //String query = "select * from Product order by product_id OFFSET ? ROWs fetch next ? rows only;"; // bat dau tu dong index, moi lan in ra recourdPerpage ban ghi
        String query = "select * from Service where service_name like '%" + searchKey + "%' "
                + "order by " + value + " " + type + " offset ? rows fetch next ? rows only;";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, (index - 1) * recordPerPage);
            ps.setInt(2, recordPerPage);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Service s = Service.builder().service_id(rs.getInt(1))
                        .service_name(rs.getString(2))
                        .price(rs.getInt(3))
                        .status(rs.getBoolean(4))
                        .service_Description(rs.getString(5))
                        .s_image(rs.getString(6))
                        .build();

                list.add(s);
            }
        } catch (SQLException e) {
            Logger.getLogger(ServiceDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return list;
    }
}
