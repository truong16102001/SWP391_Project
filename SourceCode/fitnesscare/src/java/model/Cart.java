package model;

import lombok.Builder;
import lombok.Setter;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString

public class Cart {

    private int cart_id;
    private int product_id;
    private String product_name;
    private int  product_price;
    private int quantity;
    private int total_cost;
    private int customer_id;
    private String product_image;
}
