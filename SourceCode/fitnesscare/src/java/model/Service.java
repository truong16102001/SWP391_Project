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
 * /**
 *
 * @author ThinkPro
 */
public class Service {

    private int service_id;
    private String service_name;
    private int price;
    private boolean status;
    private String service_Description;
    private String s_image;
    private int practiceTime;

    public Service() {
    }

    public Service(int service_id, String service_name, int price, boolean status, String service_Description, String s_image, int practiceTime) {
        this.service_id = service_id;
        this.service_name = service_name;
        this.price = price;
        this.status = status;
        this.service_Description = service_Description;
        this.s_image = s_image;
        this.practiceTime = practiceTime;
    }
}
