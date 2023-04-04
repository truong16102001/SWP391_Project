package controller.product;

import dao.impl.CartDAOImpl;
import dao.impl.OrderDAOImpl;
import dao.impl.OrderDetailDAOImpl;
import dao.inter.ICartDAO;
import dao.inter.IOrderDAO;
import dao.inter.IOrderDetailDAO;
import vnpay.Config;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import model.Cart;
import model.User;

public class CheckoutController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();

        int flag = Integer.parseInt(req.getParameter("flag"));
        if (flag == 0) { // chua thanh toan
            IOrderDAO od = new OrderDAOImpl();
            int nextOrderId = od.getNextOrderId();

            int total_cost = (int) session.getAttribute("sum");

            String vnp_Version = "2.0.0";
            String vnp_Command = "pay";

            String vnp_OrderInfo = "" + nextOrderId;
            String orderType = "billpayment";
            String vnp_TxnRef = nextOrderId + "";

            String vnp_IpAddr = Config.getIpAddress(req);
            String vnp_TmnCode = Config.vnp_TmnCode;

            int amount = Math.round(total_cost) * 100;
            Map<String, String> vnp_Params = new HashMap<>();
            vnp_Params.put("vnp_Version", vnp_Version);
            vnp_Params.put("vnp_Command", vnp_Command);
            vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
            vnp_Params.put("vnp_Amount", String.valueOf(amount));
            vnp_Params.put("vnp_CurrCode", "VND");
            String bank_code = "";
            if (bank_code != null && bank_code.isEmpty()) {
                vnp_Params.put("vnp_BankCode", bank_code);
            }
            vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
            vnp_Params.put("vnp_OrderInfo", vnp_OrderInfo);
            vnp_Params.put("vnp_OrderType", orderType);

            String locate = "vi";
            if (locate != null && !locate.isEmpty()) {
                vnp_Params.put("vnp_Locale", locate);
            } else {
                vnp_Params.put("vnp_Locale", "vn");
            }
            vnp_Params.put("vnp_ReturnUrl", Config.vnp_Returnurl);
            vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

            Date dt = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String dateString = formatter.format(dt);
            String vnp_CreateDate = dateString;
            String vnp_TransDate = vnp_CreateDate;
            vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

            //Build data to hash and querystring
            List fieldNames = new ArrayList(vnp_Params.keySet());
            Collections.sort(fieldNames);
            StringBuilder hashData = new StringBuilder();
            StringBuilder query = new StringBuilder();
            Iterator itr = fieldNames.iterator();
            while (itr.hasNext()) {
                String fieldName = (String) itr.next();
                String fieldValue = (String) vnp_Params.get(fieldName);
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    //Build hash data
                    hashData.append(fieldName);
                    hashData.append('=');
                    hashData.append(fieldValue);
                    //Build query
                    query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                    query.append('=');
                    query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                    if (itr.hasNext()) {
                        query.append('&');
                        hashData.append('&');
                    }
                }
            }
            String queryUrl = query.toString();
            String vnp_SecureHash = Config.hmacSHA512(Config.vnp_HashSecret, hashData.toString());
            queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
            String paymentUrl = Config.vnp_PayUrl + "?" + queryUrl;
            req.setAttribute("code", "00");
            req.setAttribute("message", "success");
            req.setAttribute("data", paymentUrl);
            resp.sendRedirect(paymentUrl);
        } else { // da thanh toan
            List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
            ICartDAO cd = new CartDAOImpl();
            IOrderDAO od = new OrderDAOImpl();
            IOrderDetailDAO odd = new OrderDetailDAOImpl();
            int sum = (int) session.getAttribute("sum");
            User u = (User) session.getAttribute("us");
            String note = (String) session.getAttribute("note");
            int order_id = od.createNewOrder(sum, u.getFullName(), u.getEmail(), u.getPhone(), u.getAddress(), u.getUser_id(), note, "pay");
            odd.addCartToOrder(listCart, order_id);
            cd.deleteCartByUserId(u.getUser_id());
            session.setAttribute("totalItem", 0);
            session.removeAttribute("note");
            resp.sendRedirect("/SWP391_Fitnesscare/thankyou?vnp_OrderInfo=" + order_id);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
