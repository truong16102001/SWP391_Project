package dao.inter;

import java.util.List;
import model.Feedback;

public interface IFeedbackDAO {

    List<Feedback> getAllFeedbackByProductId(int product_id);

    List<Feedback> getNext2Feedback(int product_id, int numOfDisplayed);

    void addNewFeedback(String fullName, int rated_star, String feedback_content,String image, int product_id, int user_id, String avatar_user);

     void deleteFeedbacktById(int id);

    

    public List<Feedback> pagingFeedback(int product_id,int index, int record_per_page);
}
