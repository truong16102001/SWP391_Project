package dao.impl;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Timeslot;
import dao.inter.ITimeslotDAO;

public class TimeslotDAOImpl extends context.DBContext implements ITimeslotDAO {

    Connection connection = null;
    DBContext dBContext = new DBContext();

    @Override
    public ArrayList<Timeslot> getAllTimeslot() {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(TimeslotDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Timeslot> list = new ArrayList<>();
        String sql = "SELECT * FROM [dbo].[Timeslot]";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Timeslot t = Timeslot.builder()
                        .ts_id(rs.getInt(1))
                        .ts_description(rs.getString(2))
                        .build();
                list.add(t);
            }
        } catch (SQLException e) {
            Logger.getLogger(TimeslotDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return list;
    }

}
