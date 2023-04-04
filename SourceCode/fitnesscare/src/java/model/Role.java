package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
/**
 *
 * @author ThinkPro
 */
@Builder
@Getter
@Setter
@ToString
public class Role {
    private int role_id;
    private String role_name;
    private boolean status;
   
    
}
