package model;

import java.sql.Date;
import lombok.Builder;
import lombok.Setter;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class Blog {

    private int blog_id;
    private String title;
    private String content;
    private String thumbnail;
    private String brief_info;
    private int author_id;
    private int cb_id;
    private boolean status;
    private Date updated_date;
}
