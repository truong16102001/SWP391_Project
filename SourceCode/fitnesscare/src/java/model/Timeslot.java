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
public class Timeslot {
    private int ts_id;
    private String ts_description;
    
      public Timeslot() {
    }

    public Timeslot(int ts_id, String ts_description) {
        this.ts_id = ts_id;
        this.ts_description = ts_description;
    }
}
