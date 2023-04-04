package controller.product;

import dao.impl.CartDAOImpl;
import dao.impl.OrderDAOImpl;
import dao.impl.OrderDetailDAOImpl;
import dao.inter.ICartDAO;
import dao.inter.IOrderDAO;
import dao.inter.IOrderDetailDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Cart;
import model.User;

public class AddOrderController extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        String fullname = request.getParameter("fullName");
        String phone = request.getParameter("phone").trim();
        String email = request.getParameter("email").trim();
        String address = request.getParameter("address").trim();
        String note = request.getParameter("note").trim();
        String paymt = request.getParameter("paym-method");
        ICartDAO cd = new CartDAOImpl();
        IOrderDAO od = new OrderDAOImpl();
        IOrderDetailDAO odd = new OrderDetailDAOImpl();
        
        List<Cart> listCart = (List<Cart>) session.getAttribute("listCart");
        if (listCart == null || listCart.isEmpty()) { // trường hợp click vào history browser
            //session.setAttribute("notification", "Giỏ hàng rỗng");
            response.sendRedirect("list-product");
        } else {
            int sum = (int) session.getAttribute("sum");
            User u = (User) session.getAttribute("us");
            int user_id = u.getUser_id();
            if (paymt.equals("vnpay")) {
                session.setAttribute("note", note);
                response.sendRedirect("/SWP391_Fitnesscare/checkout?flag=0");
            } else {
                int order_id = od.createNewOrder(sum, fullname, email, phone, address, user_id, note, "cod");
                odd.addCartToOrder(listCart, order_id);
                cd.deleteCartByUserId(user_id);
                session.setAttribute("totalItem", 0);                
                response.sendRedirect("/SWP391_Fitnesscare/thankyou?order_id=" + order_id + "&fullName=" + fullname + "&email=" + email + "&phone=" + phone + "&address=" + address
                        + "&note=" + note + "&vnp_OrderInfo=-1");
            }
        }
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
