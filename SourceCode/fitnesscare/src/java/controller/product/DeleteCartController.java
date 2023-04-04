package controller.product;

import dao.impl.CartDAOImpl;
import dao.inter.ICartDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

public class DeleteCartController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String productId_raw = request.getParameter("product_id");
        int product_id = Integer.parseInt(productId_raw);
        String userId_raw = request.getParameter("customer_id");
        int user_id = Integer.parseInt(userId_raw);

        HttpSession session = request.getSession();
        ICartDAO cd = new CartDAOImpl();
        User u = (User) session.getAttribute("us");
        cd.deleteCart(product_id, user_id);
        int totalItem = cd.getTotalItemInCart(u.getUser_id());
        session.setAttribute("totalItem", totalItem);
        // String historyUrl = (String) session.getAttribute("historyUrl");
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
