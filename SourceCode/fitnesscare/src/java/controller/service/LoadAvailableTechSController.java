package controller.service;

import dao.impl.RegistrationDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.User;
import dao.inter.IRegistrationDAO;

public class LoadAvailableTechSController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        IRegistrationDAO techS = new RegistrationDAOImpl();
        int ts_id = Integer.parseInt(request.getParameter("timeslot"));
        java.sql.Date start_date = java.sql.Date.valueOf(request.getParameter("startdate"));
        ArrayList<User> listTechS = techS.getAvailableTechnicalStaff(ts_id, start_date);
        System.out.println(listTechS);
        PrintWriter out = response.getWriter();
        out.println("<select id=\"contentTechS\" name=\"techS_id\">\n");
        for (User t : listTechS) {
            out.println("<option \n"
                    + "value=\"" + t.getUser_id() + "\">" + t.getFullName() + "</option>");
        }
        out.println("</select>");
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
