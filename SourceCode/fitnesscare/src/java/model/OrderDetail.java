package model;

import lombok.Builder;
import lombok.Setter;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString

public class OrderDetail {
    private int od_id;
    private int order_id;
    private int product_id;
    private String product_name;
    private int product_price;
    private int quantity;
    private int total_cost;
}
