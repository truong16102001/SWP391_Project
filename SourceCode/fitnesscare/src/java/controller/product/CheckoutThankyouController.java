package controller.product;

import dao.impl.OrderDAOImpl;
import dao.impl.SendMail;
import dao.inter.IOrderDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Cart;
import model.Order;

public class CheckoutThankyouController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        String orderInfoStr = request.getParameter("vnp_OrderInfo");
        int orderinfo = Integer.parseInt(orderInfoStr);
        String paymt = "", fullName = "", email = "", phone = "", address = "", note = "";
        IOrderDAO od = new OrderDAOImpl();
        int order_id = -1;

        if (orderinfo == -1) {
            order_id = Integer.parseInt(request.getParameter("order_id"));
            request.setAttribute("order_id", order_id);
            Order order = od.getOrderByID(order_id);
            paymt = order.getPaymentmethod();
            fullName = request.getParameter("fullName");
            email = request.getParameter("email");
            phone = request.getParameter("phone");
            address = request.getParameter("address");
            note = request.getParameter("note");
        } else {
            Order order = od.getOrderByID(orderinfo);
            paymt = order.getPaymentmethod();
            fullName = order.getCustomer_name();
            email = order.getEmail();
            phone = order.getPhone();
            address = order.getAddress();
            note = order.getNote();
            request.setAttribute("order_id", orderinfo);
        }

        request.setAttribute("payment", paymt);
        request.setAttribute("fullName", fullName);
        request.setAttribute("email", email);
        request.setAttribute("phone", phone);
        request.setAttribute("address", address);
        request.setAttribute("note", note);
        List<Cart> copyListCart = (List<Cart>) session.getAttribute("listCart");
        session.setAttribute("copyListCart", copyListCart);
        try {
            String smtpServer = "smtp.gmail.com";
            String to = email;

            String from = "truongndhe150878@fpt.edu.vn";
            String subject = "Checkout For Order\n";

            String body
                    = "<!DOCTYPE html>\n"
                    + "<html lang=\"en\">\n"
                    + "\n"
                    + "<head>\n"
                    + "</head>\n"
                    + "\n"
                    + "<body>\n"
                    + "    <h3 style=\"color: blue;\">Hello " + fullName + ",</h3>\n"
                    + "    <div>Chúng tôi nhận được yêu cầu đặt hàng của bạn tại http://localhost:8080/fashion-shop-online/</div>\n"
                    + "    <h4 style=\"color: green;\">Giá trị đơn hàng của bạn là :" + session.getAttribute("sum") + " VNĐ</h4>\n"
                    + "    <div> <a onclick=\"removeSession()\" href=\"http://localhost:8080/SWP391_Fitnesscare/thankyou?order_id=" + order_id + "&payment=" + paymt + "&fullName=" + fullName + "&email=" + email + "&phone=" + phone + "&address=" + address + "&note=" + note + "\" class=\"btn btn-primary\">Click để xác nhận</a> </div>\n"
                    + "    <h3 style=\"color: blue;\">Cảm ơn bạn !</h3>\n"
                    + "\n"
                    + "</body>\n"
                    + "\n"
                    + "</html>";
            String password = "233200716102001";
            SendMail.send(smtpServer, to, from, password, subject, body);
        } catch (Exception e) {
            System.out.println("Usage: " + e.getMessage());
        }
        session.removeAttribute("listCart");
        request.getRequestDispatcher("./view/thankyou.jsp").forward(request, response);
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
    }

}
