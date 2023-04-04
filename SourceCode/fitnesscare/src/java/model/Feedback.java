package model;


import java.sql.Timestamp;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class Feedback {

    private int fback_id;
    private String fullName;
    private int rated_star;
    private String feedback_content;
    private String feedback_image;
    private int product_id;
    private int user_id;
    private Timestamp date;
    private String avatar_user;
}
