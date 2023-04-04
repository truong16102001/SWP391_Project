package controller.common;

import dao.inter.IUserDAO;
import dao.impl.UserDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Base64;
import model.User;

import org.apache.commons.io.IOUtils;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 50 // 50 MB
)
public class EditProfileController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EditProfileController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditProfileController at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession();
        IUserDAO ud = new UserDAOImpl();
        String uId_raw = request.getParameter("user_id");
        int uid = Integer.parseInt(uId_raw);
        String uName = request.getParameter("fullName").trim();
        String uPhone = request.getParameter("phone").trim();
        String uAddress = request.getParameter("address").trim();
        String uGender = request.getParameter("gender");
        String uEmail = request.getParameter("email").trim();
        String uPassword = request.getParameter("password").trim();
        String base64Image = "";
        User u = ud.checkEmailExisted(Integer.parseInt(uId_raw), uEmail);
        if (u == null) {

            // Lấy tệp hình ảnh từ yêu cầu
            Part filePart = request.getPart("avatar");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Lấy tên tệp
            if (fileName != null && !fileName.isEmpty()) {
                InputStream imageStream = filePart.getInputStream();
                byte[] imageBytes = IOUtils.toByteArray(imageStream);
                base64Image = Base64.getEncoder().encodeToString(imageBytes);
                ud.editUserProfile(uName, "data:image/png;base64," + base64Image, uGender, uPhone, uAddress, uid);
            } else {

                User user = ud.getUserById(uid);
                base64Image = user.getAvatar();
                ud.editUserProfile(uName, base64Image, uGender, uPhone, uAddress, uid);
            }

            User user = ud.login(uEmail, uPassword);
            session.setAttribute("us", user);
            session.setAttribute("message", "Cập nhật thông tin thành công!");
        } else {
            session.setAttribute("notification", "Email đã tồn tại");
        }

        response.sendRedirect("home");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
