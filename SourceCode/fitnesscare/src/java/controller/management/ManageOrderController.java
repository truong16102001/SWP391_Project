package controller.management;

import dao.impl.OrderDAOImpl;
import dao.inter.IOrderDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import model.Order;
import model.StatusOrder;

public class ManageOrderController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ManageOrderController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageOrderController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    private final int RECORD_PER_PAGE = 12;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        IOrderDAO od = new OrderDAOImpl();
        List<Order> orders = od.getAllOrder();
        List<StatusOrder> statusOrders = od.getAllStatusOrder();
        String indexPageStr = request.getParameter("index");
        if (indexPageStr == null) {
            indexPageStr = "1";
        }
        int index = Integer.parseInt(indexPageStr);

        String statusStr = "!= -1";
        int status = 0;
        String statusOrderStr = request.getParameter("status");
        if (!statusOrderStr.equals("0")) {
            status = Integer.parseInt(statusOrderStr);
            statusStr = "= " + statusOrderStr;
        }

        String key = "";
        String keyStr = request.getParameter("key");
        if (keyStr != null) {
            key = keyStr.trim();
        }

        // using banana approach
        List<Order> listOfPage = od.pagingOrder(index, RECORD_PER_PAGE, key, statusStr);
        int count = od.countOrderByStatus(key, statusStr);
        int endPage = count / RECORD_PER_PAGE;
        if ((count % RECORD_PER_PAGE) != 0) {
            endPage++;
        }
        // using jquery datatable approach
//        List<Order> listByCondition = od.filterOrderByStatus(statusStr);
//        int endPage = listByCondition.size() / RECORD_PER_PAGE;
//        if ((listByCondition.size() % RECORD_PER_PAGE) != 0) {
//            endPage++;
//        }

        HttpSession session = request.getSession();
        session.setAttribute("orderList", orders);
        session.setAttribute("historyUrl", "manageorder");
        String history = "manageorder?index=" + index;
        if (statusOrderStr != null) {
            history = history + "&status=" + statusOrderStr;
            request.setAttribute("historyStatusOrder", "&status=" + statusOrderStr);
        }

        request.setAttribute("statusList", statusOrders);
        session.setAttribute("historyUrl", history);
        request.setAttribute("status", status);
        request.setAttribute("key", key);
        String historyKey = "key=" + key;
        request.setAttribute("historyKey", historyKey);
        request.setAttribute("endPage", endPage);
        request.setAttribute("listOfPage", listOfPage);
        request.setAttribute("pageIndex", index);
        //request.setAttribute("listByCond", listByCondition);
        request.getRequestDispatcher("./view/orderList.jsp").forward(request, response);
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
