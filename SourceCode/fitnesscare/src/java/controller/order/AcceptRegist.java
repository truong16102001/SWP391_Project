package controller.order;

import dao.impl.RegistrationDAOImpl;
import dao.impl.ScheduleDAOImpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import model.Registration;
import model.Schedule;
import model.User;
import utils.DateTimeHelper;
import dao.inter.IRegistrationDAO;
import dao.inter.IScheduleDAO;

public class AcceptRegist extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int regist_id = Integer.parseInt(request.getParameter("regist_id"));
        IRegistrationDAO registdao = new RegistrationDAOImpl();
        Registration registration = registdao.getRegistByID(regist_id);
        registration.setSeller((User) session.getAttribute("us"));
        System.out.println(registration);
        registdao.acceptRegist(registration);
        ArrayList<java.sql.Date> dates = DateTimeHelper.getDateList(registration.getStart_date(), registration.getEnd_date());
                for (Date date : dates) {
                    IScheduleDAO scheduledao = new ScheduleDAOImpl();
                    Schedule schedule = new Schedule();
                    schedule.setCustomer(registration.getCustomer());
                    schedule.setService(registration.getService());
                    schedule.setTimeslot(registration.getTimeslot());
                    schedule.setDate(date);

                    if (registration.getTechnicalStaff().getUser_id() == 0) {
                        scheduledao.insertSchedule(schedule);

                    } else {
                        schedule.setTechS(registration.getTechnicalStaff());
                        scheduledao.insertSchedulePT(schedule);
                    }
                }
        response.sendRedirect("registedService");
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
