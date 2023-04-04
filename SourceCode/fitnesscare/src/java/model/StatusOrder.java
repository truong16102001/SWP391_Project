
package model;

import lombok.Builder;
import lombok.Setter;
import lombok.Getter;
import lombok.ToString;

@Builder
@Setter
@Getter
@ToString
public class StatusOrder {
    private int status_order_id;
    private String status_order_name;
    private boolean status;
}
