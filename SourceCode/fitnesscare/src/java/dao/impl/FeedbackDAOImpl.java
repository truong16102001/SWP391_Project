package dao.impl;

import dao.inter.IFeedbackDAO;
import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Feedback;

public class FeedbackDAOImpl extends context.DBContext implements IFeedbackDAO {

    Connection connection = null;
    DBContext dBContext = new DBContext();

    @Override
    public List<Feedback> getAllFeedbackByProductId(int product_id) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FeedbackDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Feedback> list = new ArrayList<>();
        String sql = "select * from Feedback where product_id = ? order by date desc";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, product_id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Feedback f = Feedback.builder()
                        .fback_id(rs.getInt(1))
                        .fullName(rs.getString(2))
                        .rated_star(rs.getInt(3))
                        .feedback_content(rs.getString(4))
                        .feedback_image(rs.getString(5))
                        .product_id(rs.getInt(6))
                        .user_id(rs.getInt(7))
                        .date(rs.getTimestamp(8))
                        .avatar_user(rs.getString(9))
                        .build();

                list.add(f);
            }
        } catch (SQLException e) {
            Logger.getLogger(FeedbackDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }

        return list;
    }

    @Override
    public List<Feedback> getNext2Feedback(int product_id, int numOfDisplayed) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FeedbackDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Feedback> list = new ArrayList();
        String sql = "select * from Feedback where product_id = ? order by fback_id  offset ? rows fetch next 2 rows only";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, product_id);
            ps.setInt(2, numOfDisplayed);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(Feedback.builder().fback_id(rs.getInt(1))
                        .fullName(rs.getString(2)).rated_star(rs.getInt(3)).feedback_content(rs.getString(4)).feedback_image(rs.getString(5)).product_id(rs.getInt(6))
                        .user_id(rs.getInt(7)).date(rs.getTimestamp(8)).avatar_user(rs.getString(9)).build());
            }
        } catch (SQLException e) {
            Logger.getLogger(FeedbackDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return list;
    }

    @Override
    public void addNewFeedback(String fullName, int rated_star, String feedback_content, String image, int product_id, int user_id, String avatar_user) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FeedbackDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String sql = "INSERT INTO [Feedback] values(?,?,?,?,?,?,GETDATE(),?);";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, fullName);
            st.setInt(2, rated_star);
            st.setString(3, feedback_content);
            st.setString(4, image);
            st.setInt(5, product_id);
            st.setInt(6, user_id);
            st.setString(7, avatar_user);
            st.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(FeedbackDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public void deleteFeedbacktById(int id) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(FeedbackDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "DELETE FROM [dbo].[Feedback]\n"
                + "      WHERE fback_id = ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            st.executeUpdate();

        } catch (SQLException e) {
            Logger.getLogger(FeedbackDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public List<Feedback> pagingFeedback(int product_id, int index, int record_per_page) {
        List<Feedback> list = getAllFeedbackByProductId(product_id);
        int begin = (index - 1) * record_per_page;
        List<Feedback> feedbacks = new ArrayList();
        int size = list.size() > (begin + record_per_page) ? (begin + record_per_page) : list.size();
        for (int i = begin; i < size; i++) {
            feedbacks.add(list.get(i));
        }
        return feedbacks;
    }

}
