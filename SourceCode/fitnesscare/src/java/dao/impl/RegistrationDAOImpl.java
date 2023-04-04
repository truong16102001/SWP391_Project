package dao.impl;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Registration;
import model.User;
import model.Service;
import model.Timeslot;
import dao.inter.IRegistrationDAO;

public class RegistrationDAOImpl extends context.DBContext implements IRegistrationDAO {

    Connection connection = null;
    DBContext dBContext = new DBContext();

    @Override
    public ArrayList<Registration> getAllRegistration(int type, String searchKey) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RegistrationDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Registration> list = new ArrayList<>();
        String sql = "select regist_id,r.customer_id, u1.fullName as Customer, r.techS_id, u2.fullName as TechnicalStaff,s.service_id, s.service_name, sr.status_regist_id , sr.status, r.seller_id, u3.fullName as Seller,\n"
                + "t.ts_id ,t.ts_description, regist_date, start_date, end_date, r.note  from Registration r join [User] u1 on r.customer_id=u1.user_id \n"
                + "left join [User] u2 on r.techS_id=u2.user_id\n"
                + "left join [User] u3 on r.seller_id=u3.user_id\n"
                + "join status_Registration sr on r.status_regist_id=sr.status_regist_id\n"
                + "join Timeslot t on t.ts_id=r.ts_id\n"
                + "join [Service] s on r.service_id=s.service_id where 1=1";
        if (type != 1) {
            sql += " and sr.status_regist_id = "+type;
        }
        if(!"".equals(searchKey)){
            sql += " and u1.fullName like '%" + searchKey + "%'";
        }
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User customer = User.builder().user_id(rs.getInt(2))
                        .fullName(rs.getString(3)).build();
                User techS = User.builder().user_id(rs.getInt(4))
                        .fullName(rs.getString(5)).build();
                Service service = Service.builder().service_id(rs.getInt(6))
                        .service_name(rs.getString(7)).build();
                User seller = User.builder().user_id(rs.getInt(10))
                        .fullName(rs.getString(11)).build();
                Timeslot ts = Timeslot.builder().ts_id(rs.getInt(12))
                        .ts_description(rs.getString(13)).build();
                Registration r = Registration.builder().regist_id(rs.getInt(1))
                        .customer(customer)
                        .technicalStaff(techS)
                        .service(service)
                        .status_id(rs.getInt(8))
                        .status_registration(rs.getBoolean(9))
                        .seller(seller)
                        .timeslot(ts)
                        .regist_date(rs.getDate(14))
                        .start_date(rs.getDate(15))
                        .end_date(rs.getDate(16))
                        .note(rs.getString(17))
                        .build();
                list.add(r);
            }
        } catch (SQLException e) {
            Logger.getLogger(RegistrationDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return list;
    }

    @Override
    public ArrayList<Registration> getRegistrationNotAccept() {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RegistrationDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Registration> list = new ArrayList<>();
        String sql = "select regist_id,r.customer_id, u1.fullName as Customer, r.techS_id, u2.fullName as TechnicalStaff,s.service_id, s.service_name, sr.status, r.seller_id, u3.fullName as Seller,\n"
                + "                t.ts_id ,t.ts_description, regist_date, start_date, end_date, r.note  from Registration r join [User] u1 on r.customer_id=u1.user_id \n"
                + "                 left join [User] u2 on r.techS_id=u2.user_id\n"
                + "                 left join [User] u3 on r.seller_id=u3.user_id\n"
                + "                  join status_Registration sr on r.status_regist_id=sr.status_regist_id\n"
                + "                  join Timeslot t on t.ts_id=r.ts_id\n"
                + "                  join [Service] s on r.service_id=s.service_id where r.status_regist_id=2";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User customer = User.builder().user_id(rs.getInt(2))
                        .fullName(rs.getString(3)).build();
                User techS = User.builder().user_id(rs.getInt(4))
                        .fullName(rs.getString(5)).build();
                Service service = Service.builder().service_id(rs.getInt(6))
                        .service_name(rs.getString(7)).build();
                User seller = User.builder().user_id(rs.getInt(9))
                        .fullName(rs.getString(10)).build();
                Timeslot ts = Timeslot.builder().ts_id(rs.getInt(11))
                        .ts_description(rs.getString(12)).build();
                Registration r = Registration.builder().regist_id(rs.getInt(1))
                        .customer(customer)
                        .technicalStaff(techS)
                        .service(service)
                        .status_registration(rs.getBoolean(8))
                        .seller(seller)
                        .timeslot(ts)
                        .regist_date(rs.getDate(13))
                        .start_date(rs.getDate(14))
                        .end_date(rs.getDate(15))
                        .note(rs.getString(16))
                        .build();
                list.add(r);
            }
        } catch (SQLException e) {
            Logger.getLogger(RegistrationDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return list;
    }

    @Override
    public Registration getRegistByID(int regist_id) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RegistrationDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select * from [Registration] where regist_id=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, regist_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User customer = User.builder().user_id(rs.getInt(2)).build();
                User techS = User.builder().user_id(rs.getInt(3)).build();
                Service service = Service.builder().service_id(rs.getInt(4)).build();
                User seller = User.builder().user_id(rs.getInt(6)).build();
                Timeslot ts = Timeslot.builder().ts_id(rs.getInt(7)).build();
                Registration r = Registration.builder().regist_id(rs.getInt(1))
                        .customer(customer)
                        .technicalStaff(techS)
                        .seller(seller)
                        .service(service)
                        .timeslot(ts)
                        .regist_date(rs.getDate(8))
                        .start_date(rs.getDate(9))
                        .end_date(rs.getDate(10))
                        .note(rs.getString(11))
                        .build();
                return r;
            }

        } catch (SQLException e) {
            Logger.getLogger(RegistrationDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return null;
    }

    @Override
    public int getTotalRegistedService() {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RegistrationDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select COUNT(regist_id) from Registration";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            Logger.getLogger(RegistrationDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return 0;
    }

    @Override
    public ArrayList<Registration> pagingRegistedService(int index, int recordPerPage, int type, String searchKey) { // index: trang click
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RegistrationDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<Registration> list = new ArrayList<>();
        String query = "select regist_id,r.customer_id, u1.fullName as Customer, r.techS_id, u2.fullName as TechnicalStaff,s.service_id, s.service_name, sr.status_regist_id , sr.status, r.seller_id, u3.fullName as Seller,\n"
                    + "t.ts_id ,t.ts_description, regist_date, start_date, end_date, r.note  from Registration r join [User] u1 on r.customer_id=u1.user_id \n"
                    + "left join [User] u2 on r.techS_id=u2.user_id\n"
                    + "left join [User] u3 on r.seller_id=u3.user_id\n"
                    + "join status_Registration sr on r.status_regist_id=sr.status_regist_id\n"
                    + "join Timeslot t on t.ts_id=r.ts_id\n"
                    + "join [Service] s on r.service_id=s.service_id where 1=1";
        if (type != 1) {
            query += " and sr.status_regist_id = "+type;
        }
        if(!"".equals(searchKey)){
            query += " and u1.fullName like '%" + searchKey + "%'";
        }
        query += " order by regist_id offset ? rows fetch next ? rows only";
        try {
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, (index - 1) * recordPerPage);
            ps.setInt(2, recordPerPage);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User customer = User.builder().user_id(rs.getInt(2))
                        .fullName(rs.getString(3)).build();
                User techS = User.builder().user_id(rs.getInt(4))
                        .fullName(rs.getString(5)).build();
                Service service = Service.builder().service_id(rs.getInt(6))
                        .service_name(rs.getString(7)).build();
                User seller = User.builder().user_id(rs.getInt(10))
                        .fullName(rs.getString(11)).build();
                Timeslot ts = Timeslot.builder().ts_id(rs.getInt(12))
                        .ts_description(rs.getString(13)).build();
                Registration r = Registration.builder().regist_id(rs.getInt(1))
                        .customer(customer)
                        .technicalStaff(techS)
                        .service(service)
                        .status_id(rs.getInt(8))
                        .status_registration(rs.getBoolean(9))
                        .seller(seller)
                        .timeslot(ts)
                        .regist_date(rs.getDate(14))
                        .start_date(rs.getDate(15))
                        .end_date(rs.getDate(16))
                        .note(rs.getString(17))
                        .build();
                list.add(r);
            }
        } catch (SQLException e) {
            Logger.getLogger(RegistrationDAOImpl.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return list;
    }

    @Override
    public void acceptRegist(Registration registration) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RegistrationDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "update Registration set status_regist_id = 3, seller_id = ? where regist_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, registration.getSeller().getUser_id());
            ps.setInt(2, registration.getRegist_id());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public void denyRegist(Registration registration) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RegistrationDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "update Registration set status_regist_id = 4, seller_id = ? where regist_id = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, registration.getSeller().getUser_id());
            ps.setInt(2, registration.getRegist_id());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public void insertRegistrationPT(Registration registration) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RegistrationDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "INSERT INTO [dbo].[Registration]\n"
                + "           ([customer_id]\n"
                + "           ,[techS_id]\n"
                + "           ,[service_id]\n"
                + "           ,[status_regist_id]\n"
                + "           ,[ts_id]\n"
                + "           ,[regist_date]\n"
                + "           ,[start_date]\n"
                + "           ,[end_date]\n"
                + "           ,[note])\n"
                + "     VALUES\n"
                + "           (?"
                + "           ,?"
                + "           ,?"
                + "           ,'2'"
                + "           ,?"
                + "           ,GETDATE()"
                + "           ,?"
                + "           ,?"
                + "           ,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, registration.getCustomer().getUser_id());
            ps.setInt(2, registration.getTechnicalStaff().getUser_id());
            ps.setInt(3, registration.getService().getService_id());
            ps.setInt(4, registration.getTimeslot().getTs_id());
            ps.setDate(5, registration.getStart_date());
            ps.setDate(6, registration.getEnd_date());
            ps.setString(7, registration.getNote());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public void insertRegistration(Registration registration) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RegistrationDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "INSERT INTO [dbo].[Registration]\n"
                + "           ([customer_id]\n"
                + "           ,[service_id]\n"
                + "           ,[status_regist_id]\n"
                + "           ,[ts_id]\n"
                + "           ,[regist_date]\n"
                + "           ,[start_date]\n"
                + "           ,[end_date]\n"
                + "           ,[note])\n"
                + "     VALUES\n"
                + "           (?"
                + "           ,?"
                + "           ,'2'"
                + "           ,?"
                + "           ,GETDATE()"
                + "           ,?"
                + "           ,?"
                + "           ,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, registration.getCustomer().getUser_id());
            ps.setInt(2, registration.getService().getService_id());
            ps.setString(6, registration.getNote());
            ps.setInt(3, registration.getTimeslot().getTs_id());
            ps.setDate(4, registration.getStart_date());
            ps.setDate(5, registration.getEnd_date());
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
    }

    @Override
    public ArrayList<User> getAvailableTechnicalStaff(int ts_id, java.sql.Date start_date) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RegistrationDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        ArrayList<User> list = new ArrayList<>();
        String sql = "select user_id, fullName from [User] where role_id=3\n"
                + "except\n"
                + "select techS_id, u.fullName from Registration r join [User] u on r.techS_id=u.user_id \n"
                + "where status_regist_id=3 and ts_id=? and DATEDIFF(day, ?, end_date) > 0";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, ts_id);
            ps.setDate(2, start_date);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User techS = new User();
                techS.setUser_id(rs.getInt(1));
                techS.setFullName(rs.getString(2));
                list.add(techS);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dBContext.closeConnection(connection);
            } catch (SQLException e) {
            }
        }
        return list;
    }

    @Override
    public Registration checkRegistrationExist(int user_id, int ts_id, java.sql.Date start_date) {
        try {
            connection = dBContext.openConnection();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(RegistrationDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String sql = "select * from Registration where customer_id=? and ts_id=? and DATEDIFF(day, ?, end_date) > 0";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, user_id);
            ps.setInt(2, ts_id);
            ps.setDate(3, start_date);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {

                Registration r = Registration.builder().regist_id(rs.getInt(1))
                        .build();
                return r;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrationDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
