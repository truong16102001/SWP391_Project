package controller.product;

import dao.impl.CartDAOImpl;
import dao.inter.ICartDAO;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.User;

public class UpdateCartController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        response.setContentType("text/html;charset=UTF-8");
        String productId_raw = request.getParameter("product_id");
        int product_id = Integer.parseInt(productId_raw);
        String quantity_raw = request.getParameter("quantity");
        int quantity = Integer.parseInt(quantity_raw);
        String cartId_raw = request.getParameter("cart_id");
        int cart_id = Integer.parseInt(cartId_raw);
        int total = 0;

        ICartDAO cd = new CartDAOImpl();
        Cart c = cd.getCartByCartID_ProductID(cart_id, product_id);
        if (c != null) {
            int sum = quantity * c.getProduct_price();
            cd.updateQuantityCart(quantity, cart_id, product_id, sum);
        }
        User u = (User) session.getAttribute("us");
        int totalItem = cd.getTotalItemInCart(u.getUser_id());
        session.setAttribute("totalItem", totalItem);
        response.sendRedirect("cartinfo");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
