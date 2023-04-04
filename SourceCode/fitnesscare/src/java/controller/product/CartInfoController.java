package controller.product;

import dao.impl.CartDAOImpl;
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

public class CartInfoController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ICartDAO c = new CartDAOImpl();
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("us");
        int user_id = u.getUser_id();
        List<Cart> listCart = c.getCartInfoByUserId(user_id);
        int sum = 0;
        for (Cart o : listCart) {
            sum += o.getTotal_cost();
        }
        request.setAttribute("sum", sum);
        session.setAttribute("historyUrl", "cartinfo");
        request.setAttribute("listCart", listCart);
        ICartDAO cd = new CartDAOImpl();
        int totalItem = cd.getTotalItemInCart(u.getUser_id());
        System.out.println(totalItem + "b");
        session.setAttribute("totalItem", totalItem);
        request.getRequestDispatcher("./view/cartinfo.jsp").forward(request, response);
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
