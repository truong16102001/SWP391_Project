package dao.impl;

import context.DBContext;
import dao.inter.IDateDAO;
import java.sql.Connection;
import java.sql.Date;
import model.DateObject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DateDAOImpl extends DBContext implements IDateDAO {

    Connection connection = null;
    DBContext dBContext = new DBContext();

    @Override
    public DateObject get7day() {
        DateObject date;
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DateDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String sql = "select GETDATE(), GETDATE()-6";

            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                date = DateObject.builder()
                        .start(rs.getDate(2))
                        .end(rs.getDate(1))
                        .build();
                return date;
            }

        } catch (SQLException ex) {
            Logger.getLogger(DateDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return null;
    }

    @Override
    public int countDayByStartEnd(String start, String end) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(DateDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "SELECT DATEDIFF(day, ?, ?)";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, start);
            st.setString(2, end);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) + 1;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DateDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return 0;
    }
}
