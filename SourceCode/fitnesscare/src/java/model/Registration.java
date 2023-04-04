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

public class Registration {
    private int regist_id;
    private Date regist_date;
    private Date start_date;
    private Date end_date;
    private User customer;
    private Service service;
    private int status_id;
    private boolean status_registration;
    private User seller;
    private String note;
    private User technicalStaff;
    private Timeslot timeslot;

    public Registration() {
    }

    public Registration(int regist_id, Date regist_date, Date start_date, Date end_date, User customer, Service service, int status_id, boolean status_registration, User seller, String note, User technicalStaff, Timeslot timeslot) {
        this.regist_id = regist_id;
        this.regist_date = regist_date;
        this.start_date = start_date;
        this.end_date = end_date;
        this.customer = customer;
        this.service = service;
        this.status_id = status_id;
        this.status_registration = status_registration;
        this.seller = seller;
        this.note = note;
        this.technicalStaff = technicalStaff;
        this.timeslot = timeslot;
    }

    
    
}
