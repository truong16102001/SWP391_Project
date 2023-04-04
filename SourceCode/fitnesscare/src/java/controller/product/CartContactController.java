package controller.product;

import dao.impl.CartDAOImpl;
import dao.impl.UserDAOImpl;
import dao.inter.ICartDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Cart;
import model.User;

public class CartContactController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ICartDAO c = new CartDAOImpl();
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("us");
        int user_id = u.getUser_id();
        List<Cart> listCart = c.getCartInfoByUserId(user_id);
        if (!listCart.isEmpty()) {
            int sum = 0;
            for (Cart o : listCart) {
                sum += o.getTotal_cost();
            }
            session.setAttribute("sum", sum);
            session.setAttribute("historyUrl", "cartinfo");
            session.setAttribute("listCart", listCart);
            request.getRequestDispatcher("./view/cartcontact.jsp").forward(request, response);
        } else {
            response.sendRedirect("cartinfo");
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
