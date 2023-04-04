package model;

import java.sql.Date;
import java.util.ArrayList;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString

public class Product {

    private int product_id;
    private String product_name;
    private int original_price;
    private int sale_price;
    private String pDescription;
    private String brief_info;
    private int quantity;
    private boolean status;
    private int cp_id;
    private Date update_date;
    private ArrayList<String> images;

   

}
