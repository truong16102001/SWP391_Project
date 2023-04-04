package dao.impl;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Schedule;
import dao.inter.IScheduleDAO;

public class ScheduleDAOImpl extends context.DBContext implements IScheduleDAO {

    Connection connection = null;
    DBContext dBContext = new DBContext();
    
    @Override
    public void delete(int customer_id, int service_id, int timeslot_id) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ScheduleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "delete Schedule where customer_id=? and service_id=? and timeslot_id=?";
        try {
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, customer_id);
            stm.setInt(2, service_id);
            stm.setInt(3, timeslot_id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public void insertSchedulePT(Schedule schedule) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ScheduleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "INSERT INTO [dbo].[Schedule]\n"
                + "           ([customer_id]\n"
                + "           ,[techS_id]\n"
                + "           ,[service_id]\n"
                + "           ,[timeslot_id]\n"
                + "           ,[date])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";

        try {
            PreparedStatement stm_insert = connection.prepareStatement(sql);
            stm_insert.setInt(1, schedule.getCustomer().getUser_id());
            stm_insert.setInt(2, schedule.getTechS().getUser_id());
            stm_insert.setInt(3, schedule.getService().getService_id());
            stm_insert.setInt(4, schedule.getTimeslot().getTs_id());
            stm_insert.setDate(5, schedule.getDate());
            stm_insert.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public void insertSchedule(Schedule schedule) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ScheduleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "INSERT INTO [dbo].[Schedule]\n"
                + "           ([customer_id]\n"
                + "           ,[service_id]\n"
                + "           ,[timeslot_id]\n"
                + "           ,[date])\n"
                + "     VALUES\n"
                + "           (?\n"
                + "           ,?\n"
                + "           ,?\n"
                + "           ,?)";

        try {
            PreparedStatement stm_insert = connection.prepareStatement(sql);
            stm_insert.setInt(1, schedule.getCustomer().getUser_id());
            stm_insert.setInt(2, schedule.getService().getService_id());
            stm_insert.setInt(3, schedule.getTimeslot().getTs_id());
            stm_insert.setDate(4, schedule.getDate());
            stm_insert.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ScheduleDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
    }

}
