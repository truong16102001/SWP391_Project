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

public class Order {

    private int order_id;
    private Date order_date;
    private int customer_id;
    private String customer_name;
    private String phone;
    private String address;
    private int seller_id;
    private int total_cost;
    private int status_order_id;
    private String note;
    private String email;
    private String paymentmethod;
}
