package controller.common;

import dao.inter.IUserDAO;
import dao.impl.UserDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.User;

public class RegisterController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RegisterController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterController at " + request.getContextPath() + "</h1>");
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
        String fullName = request.getParameter("fullName").trim();
        String email = request.getParameter("email").trim();
        String password = request.getParameter("password").trim();
        String phone = request.getParameter("phone").trim();
        String gender = request.getParameter("gender");
        String address = request.getParameter("address").trim();

        IUserDAO dao = new UserDAOImpl();
        User u = dao.getUserByEmail(email);
        if (u == null) {
            //dang ky thanh cong
            dao.register(fullName, password, gender, email, phone, address);
            request.setAttribute("notification", "Đăng kí thành công");
        } else {
            request.setAttribute("notification", "Email đã tồn tại");
        }
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
