package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
/**
/**
 *
 * @author ThinkPro
 */
public class TechnicalStaff {
    private int techS_id;
    private String techS_name;
    private String techS_email;
    private String techS_phone;
    private int user_id;
}

