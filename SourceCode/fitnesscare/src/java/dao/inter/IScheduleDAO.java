package dao.inter;

import model.Schedule;

public interface IScheduleDAO {
    void insertSchedulePT(Schedule schedule);
    void insertSchedule(Schedule schedule);
    void delete(int customer_id, int service_id, int timeslot_id);
}
