package controller.common;

import dao.inter.IUserDAO;
import dao.impl.UserDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.impl.SendMail;
import model.User;

public class ResetPasswordController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ResetPasswordController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ResetPasswordController at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String email = request.getParameter("email").trim();
        IUserDAO dao = new UserDAOImpl();

        User user = dao.getUserByEmail(email);
        
        if (user == null) {
            request.setAttribute("notification", "Email không tồn tại");
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } else {
            try {
                String smtpServer = "smtp.gmail.com";
                String to = user.getEmail();
                String from = "truongndhe150878@fpt.edu.vn";
                String subject = "Re-issue old password\n";
                String body
                        = "<!DOCTYPE html>\n"
                        + "<html lang=\"en\">\n"
                        + "\n"
                        + "<head>\n"
                        + "</head>\n"
                        + "\n"
                        + "<body>\n"
                        + "    <h3 style=\"color: blue;\">Hello " + user.getFullName() + ",</h3>\n"
                        + "    <div>Chúng tôi nhận được yêu cầu đặt lại mật khẩu của bạn tại http://localhost:8080/fashion-shop-online/home.</div>\n"
                        + "    <h4 style=\"color: green;\">Mật khẩu cũ của bạn là :" + user.getPassword() + "</h4>\n"
                        + "    <div>Nếu bạn không yêu cầu, bạn có thể bỏ qua email này. Nếu thực sự bạn quên mật khẩu, hãy click ngay vào nút bên trên , đăng nhập lại vào FitnessCare để đổi mật khẩu </div>\n"
                        + "    <h3 style=\"color: blue;\">Cảm ơn bạn !</h3>\n"
                        + "\n"
                        + "</body>\n"
                        + "\n"
                        + "</html>";
                String password = "233200716102001";
                SendMail.send(smtpServer, to, from, password, subject, body);

            } catch (Exception ex) {
                System.out.println("Usage: " + ex.getMessage());
            }
            request.setAttribute("notification", "Hãy kiểm tra hòm thư của bạn");
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
