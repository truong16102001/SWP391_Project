package model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
public class DateObject {

    private java.sql.Date start;
    private java.sql.Date end;
}
