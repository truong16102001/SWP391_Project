
package model;

import lombok.Builder;
import lombok.Setter;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class CategoryBlog {

    private int cb_id;
    private String cb_name;
    private boolean status;
}
