package controller.admin;

import com.google.gson.Gson;
import dao.impl.CategoryProductDAOImpl;
import dao.impl.DateDAOImpl;
import dao.impl.OrderDAOImpl;
import dao.impl.ProductDAOImpl;
import dao.impl.ServiceDAOImpl;
import dao.impl.UserDAOImpl;
import dao.inter.ICategoryProductDAO;
import dao.inter.IDateDAO;
import dao.inter.IOrderDAO;
import dao.inter.IProductDAO;
import dao.inter.IServiceDAO;
import dao.inter.IUserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.CategoryProduct;
import model.Chart;
import model.DateObject;
import model.StatusOrder;

public class AdminManagementController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        IProductDAO pd = new ProductDAOImpl();
        ICategoryProductDAO cp = new CategoryProductDAOImpl();
        IUserDAO ud = new UserDAOImpl();
        IOrderDAO od = new OrderDAOImpl();
        IDateDAO dd = new DateDAOImpl();
        IServiceDAO sd = new ServiceDAOImpl();

        int countCustomer = ud.CountCustomer();
        int countProduct = pd.getTotalProduct();
        int countStaff = ud.countStaff();
        int countService = sd.getTotalService();

        DateObject date = dd.get7day();
        String start = date.getStart().toString();
        String end = date.getEnd().toString();
        String start_raw = request.getParameter("start");
        String end_raw = request.getParameter("end");
        if (start_raw != null) {
            start = start_raw;
            end = end_raw;
        }

        int numberOfDay = dd.countDayByStartEnd(start, end);

        // set chart revenue
        List<Chart> listChartRevenueArea = od.getChartRevenueArea(start, numberOfDay);
        int maxListChartRevenueArea = -1;
        for (Chart o : listChartRevenueArea) {
            if (o.getValue() > maxListChartRevenueArea) {
                maxListChartRevenueArea = o.getValue();
            }
        }
        maxListChartRevenueArea = (maxListChartRevenueArea / 1000000 + 2) * 1000000;

        // set chart customer
//        List<Chart> listChartCustomer = ud.getChartCustomerArea(start, numberOfDay);
//        int maxListChartCustomerArea = -1;
//        for (Chart o : listChartCustomer) {
//            if (o.getValue() > maxListChartCustomerArea) {
//                maxListChartCustomerArea = o.getValue();
//            }
//        }
//        maxListChartCustomerArea = (maxListChartCustomerArea / 10 + 1) * 10;

        // set char Order 
        List<StatusOrder> listStatusOrder = od.getAllStatusOrder();
        int totalOrderByStatus1 = od.gettotalOrderByStatus(1, start, numberOfDay);
        int totalOrderByStatus2 = od.gettotalOrderByStatus(2, start, numberOfDay);
        int totalOrderByStatus3 = od.gettotalOrderByStatus(3, start, numberOfDay);
        int totalOrderByStatus4 = od.gettotalOrderByStatus(4, start, numberOfDay);
        request.setAttribute("listStatusOrder", listStatusOrder);
        request.setAttribute("totalOrder1", totalOrderByStatus1);
        request.setAttribute("totalOrder2", totalOrderByStatus2);
        request.setAttribute("totalOrder3", totalOrderByStatus3);
        request.setAttribute("totalOrder4", totalOrderByStatus4);
        request.setAttribute("noCustomer", countCustomer);
        request.setAttribute("noProduct", countProduct);
        request.setAttribute("noStaff", countStaff);
        request.setAttribute("noService", countService);

        request.setAttribute("listChartRevenueArea", listChartRevenueArea);
        request.setAttribute("maxListChartRevenueArea", maxListChartRevenueArea);

        //request.setAttribute("listChartCustomer", listChartCustomer);
        //request.setAttribute("maxListChartCustomerArea", maxListChartCustomerArea);
        request.setAttribute("start", start);
        request.setAttribute("end", end);
        request.getRequestDispatcher("./view/admin-manage.jsp").forward(request, response);

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
