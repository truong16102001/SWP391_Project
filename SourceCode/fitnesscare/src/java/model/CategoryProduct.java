package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class CategoryProduct {
    private int cp_id;
    private String cp_name;
    private boolean status;   
}
