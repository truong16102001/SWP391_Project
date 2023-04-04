package controller.product;

import dao.impl.CartDAOImpl;
import dao.impl.ProductDAOImpl;
import dao.inter.ICartDAO;
import dao.inter.IProductDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.Product;
import model.User;

public class AddCartController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String productId_raw = request.getParameter("product_id");
        int product_id = Integer.parseInt(productId_raw);
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("us");
        int user_id = u.getUser_id();
        ICartDAO cd = new CartDAOImpl();
        Cart c = cd.checkCart(user_id, product_id);

        int quantity = 1;
        String quantity_raw = request.getParameter("quantity");
        if (quantity_raw != null) {
            quantity = Integer.parseInt(quantity_raw);
        }

        int total_cost;
        if (c == null) {
            IProductDAO pd = new ProductDAOImpl();
            Product p = pd.getProductById(product_id);
            int price = p.getOriginal_price();
            if (p.getSale_price() != 0) {
                price = p.getSale_price();
            }
            total_cost = quantity * price;
            cd.addToCart(product_id, p.getProduct_name(), price, quantity, total_cost, user_id);
        } else {
            quantity += c.getQuantity();
            total_cost = quantity * c.getProduct_price();
            cd.updateQuantityProductInCart(product_id, quantity, total_cost, user_id);
        }
        int totalItem = cd.getTotalItemInCart(u.getUser_id());

        session.setAttribute("totalItem", totalItem);
        String historyUrl = (String) session.getAttribute("historyUrl");
        // System.out.println(historyUrl);
        response.sendRedirect(historyUrl);

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
