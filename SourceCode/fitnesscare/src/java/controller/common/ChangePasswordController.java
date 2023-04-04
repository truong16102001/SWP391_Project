package controller.common;

import dao.inter.IUserDAO;
import dao.impl.UserDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

public class ChangePasswordController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ChangePasswordController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ChangePasswordController at " + request.getContextPath() + "</h1>");
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
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        String old_pass = request.getParameter("old_pass").trim();
        String new_pass1 = request.getParameter("new_pass1").trim();
        String historyUrl = (String) session.getAttribute("historyUrl");
        User user = new UserDAOImpl().getUserByPassword(user_id, old_pass);
        String noti = "";
        if (user == null) {
            //request.setAttribute("notification", "Mật khẩu cũ sai");
            noti = "Mật khẩu cũ sai";
        } else {
            IUserDAO dao = new UserDAOImpl();
            dao.changePassword(user_id, new_pass1);
            //request.setAttribute("notification", "Thay đổi mật khẩu thành công");
            noti = "Thay đổi mật khẩu thành công";
            session.removeAttribute("us");
        }
        //request.getRequestDispatcher("home.jsp").forward(request, response);
        response.sendRedirect("home");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
