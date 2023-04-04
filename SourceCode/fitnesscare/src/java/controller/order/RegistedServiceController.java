package controller.order;

import dao.impl.RegistrationDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import model.Registration;
import dao.inter.IRegistrationDAO;

public class RegistedServiceController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegistedServiceController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegistedServiceController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    private final int record_per_page = 5;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        IRegistrationDAO registdao = new RegistrationDAOImpl();

        // get index of page
        String indexPage = request.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }

        int index = Integer.parseInt(indexPage);

        // Set key for search 
        String searchKey = "";
        String strSearchKey = request.getParameter("key");
        if (strSearchKey != null) {
            searchKey = strSearchKey.trim();
        }
        // Set filter 
        int type = 1;
        String strType = request.getParameter("type");
        if (strType != null) {
            type = Integer.parseInt(strType);
        }

        //get all registed service
        ArrayList<Registration> registedList = registdao.getAllRegistration(type, searchKey);

        // get total number of registed service
        int endPage = (registdao.getAllRegistration(type, searchKey).size() / record_per_page);
        if (registdao.getAllRegistration(type, searchKey).size() % record_per_page != 0) {
            endPage++;
        }

        // filter
        ArrayList<Registration> listOfPage = registdao.pagingRegistedService(index, record_per_page, type, searchKey);

        // Set param request to jsp page    
        session.setAttribute("registedList", registedList);
        session.setAttribute("historyUrl", "registedService");
        String history = "registedService?index=" + indexPage;

        if (strSearchKey != null) {
            history = history + "&key=" + strSearchKey;
            request.setAttribute("historyKey", "&key=" + strSearchKey);
            request.setAttribute("key", strSearchKey);
        }
        if (strType != null) {
            history = history + "&type=" + strType;
            request.setAttribute("historyType", "&type=" + strType);
            request.setAttribute("type", strType);
        }

        session.setAttribute("historyUrl", history);

        request.setAttribute("endPage", endPage);
        request.setAttribute("listOfPage", listOfPage);
        request.setAttribute("pageIndex", index);

        request.getRequestDispatcher("/view/registedService.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
