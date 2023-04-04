package controller.service;

import dao.impl.RegistrationDAOImpl;
import dao.impl.ServiceDAOImpl;
import dao.impl.TimeslotDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import model.Registration;
import model.Service;
import model.Timeslot;
import model.User;
import utils.DateTimeHelper;
import dao.inter.IRegistrationDAO;
import dao.inter.IServiceDAO;
import dao.inter.ITimeslotDAO;

public class RegistrationController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegistrationController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistrationController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        IServiceDAO sd = new ServiceDAOImpl();
        int service_id = Integer.parseInt(request.getParameter("service_id"));
        Service s = sd.getServiceByID(service_id);
        session.setAttribute("service", s);

        ITimeslotDAO ts = new TimeslotDAOImpl();
        ArrayList<Timeslot> listTimeslot = ts.getAllTimeslot();
        request.setAttribute("listTimeslot", listTimeslot);

        request.getRequestDispatcher("/view/registerService.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User customer = (User) session.getAttribute("us");
        Service service = (Service) session.getAttribute("service");
        try {
            IRegistrationDAO registdao = new RegistrationDAOImpl();
            Registration r = new Registration();
            java.sql.Date start_date = java.sql.Date.valueOf(request.getParameter("start_date"));
            r.setStart_date(start_date);

            r.setCustomer(customer);

            r.setService(service);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(start_date);
            calendar.add(Calendar.MONTH, service.getPracticeTime());
            java.sql.Date end_date = DateTimeHelper.toDateSql(calendar.getTime());
            r.setEnd_date(end_date);

            r.setNote(request.getParameter("note"));

            Timeslot ts = new Timeslot();
            ts.setTs_id(Integer.parseInt(request.getParameter("ts_id")));
            r.setTimeslot(ts);

            Registration registExist = registdao.checkRegistrationExist(customer.getUser_id(), ts.getTs_id(), start_date);
            session.setAttribute("registExist", registExist);
            String raw_techSid = request.getParameter("techS_id");
            if (registExist == null) {
                if (raw_techSid == null || raw_techSid.length() == 0) {
                    registdao.insertRegistration(r);
                } else {
                    User techS = new User();
                    techS.setUser_id(Integer.parseInt(raw_techSid));
                    r.setTechnicalStaff(techS);
                    registdao.insertRegistrationPT(r);
                }
                request.getRequestDispatcher("/view/registerCompletion.jsp").forward(request, response);
            } else {
                response.sendRedirect("register_service?service_id=" + service.getService_id());
            }
        } catch (NullPointerException | IllegalArgumentException e) {
            response.sendRedirect("register_service?service_id=" + service.getService_id());
        }

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
