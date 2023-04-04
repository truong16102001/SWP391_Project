package model;

import java.sql.Date;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString

public class Chart {
    private Date date;
    private int value;
}
