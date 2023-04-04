package controller.common;

import dao.impl.CartDAOImpl;
import dao.inter.IUserDAO;
import dao.impl.UserDAOImpl;
import dao.inter.ICartDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

public class LoginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        HttpSession session = request.getSession();
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();
        String historyUrl = (String) session.getAttribute("historyUrl");
        IUserDAO dao = new UserDAOImpl();
        User u = dao.login(email, password);
        if (u == null) {
            request.setAttribute("notification", "Sai email hoặc mật khẩu");
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } else {
            ICartDAO cd = new CartDAOImpl();
            int totalItem = cd.getTotalItemInCart(u.getUser_id());
            session.setAttribute("totalItem", totalItem);
            session.setAttribute("us", u);
            System.out.println(historyUrl);   
            response.sendRedirect(historyUrl);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
