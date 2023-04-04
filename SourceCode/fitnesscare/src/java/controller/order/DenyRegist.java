package controller.order;

import dao.impl.RegistrationDAOImpl;
import dao.impl.ScheduleDAOImpl;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Registration;
import model.User;
import dao.inter.IRegistrationDAO;
import dao.inter.IScheduleDAO;

public class DenyRegist extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int regist_id = Integer.parseInt(request.getParameter("regist_id"));
        IRegistrationDAO registdao = new RegistrationDAOImpl();
        IScheduleDAO scheduledao = new ScheduleDAOImpl();
        Registration registration = registdao.getRegistByID(regist_id);
        registration.setSeller((User) session.getAttribute("us"));
        System.out.println(registration);
        scheduledao.delete(registration.getCustomer().getUser_id(), registration.getService().getService_id(), registration.getTimeslot().getTs_id());
        registdao.denyRegist(registration);
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
