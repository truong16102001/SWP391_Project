package dao.inter;

import java.util.ArrayList;
import model.Registration;
import model.User;

public interface IRegistrationDAO {
    void insertRegistrationPT(Registration registration);
    void insertRegistration(Registration registration);
    ArrayList<User> getAvailableTechnicalStaff(int ts_id, java.sql.Date start_date);
    Registration checkRegistrationExist(int user_id, int ts_id, java.sql.Date start_date);
    ArrayList<Registration> getAllRegistration(int type, String searchKey);
    void acceptRegist(Registration registration);
    void denyRegist(Registration registration);
    Registration getRegistByID(int regist_id);
    ArrayList<Registration> getRegistrationNotAccept();
    int getTotalRegistedService();
    ArrayList<Registration> pagingRegistedService(int index, int recordPerPage, int type, String searchKey);
}
