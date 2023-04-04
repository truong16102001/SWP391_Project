package dao.inter;

import java.util.ArrayList;
import java.util.List;
import model.Chart;
import model.User;

/**
 *
 * @author ThinkPro
 */
public interface IUserDAO {

    void register(String fullName, String password, String gender, String email, String phone, String address);

    User login(String email, String password);

    User getUserByPassword(int user_id, String old_pass);

    void changePassword(int user_id, String new_pass1);

    String getUrlImageById(int user_id);

    void editUserProfile(String fullName, String url_avatar, String gender, String phone, String address, int uid);

    User getUserById(int uid);

    User getUserByEmail(String email);

    User checkEmailExisted(int parseInt, String uEmail);

    List<Chart> getChartCustomerArea(String start, int day);

    int CountCustomer();

    int countStaff();

    User checkUserExist(String email);

    ArrayList<User> getAllTechnicalStaff();

}
