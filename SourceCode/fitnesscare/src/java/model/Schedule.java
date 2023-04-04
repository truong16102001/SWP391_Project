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
public class Schedule {
    private int schedule_id;
    private User customer;
    private User techS;
    private Service service;
    private Timeslot timeslot;
    private Date date;

    public Schedule() {
    }

    public Schedule(int schedule_id, User customer, User techS, Service service, Timeslot timeslot, Date date) {
        this.schedule_id = schedule_id;
        this.customer = customer;
        this.techS = techS;
        this.service = service;
        this.timeslot = timeslot;
        this.date = date;
    }
    
}
