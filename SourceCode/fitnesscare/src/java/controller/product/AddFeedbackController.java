package controller.product;

import dao.impl.FeedbackDAOImpl;
import dao.inter.IFeedbackDAO;
import org.apache.commons.io.IOUtils;
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

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 50 // 50 MB
)
public class AddFeedbackController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet FeedbackController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet FeedbackController at " + request.getContextPath() + "</h1>");
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
        //  processRequest(request, response);
        HttpSession session = request.getSession();
        IFeedbackDAO fed = new FeedbackDAOImpl();
        User u = (User) session.getAttribute("us");
        int product_id = Integer.parseInt(request.getParameter("product_id"));
        String feedback_content = request.getParameter("feedback_content").trim();
        String rating = request.getParameter("rating");
        // Lấy tệp hình ảnh từ yêu cầu
        Part filePart = request.getPart("image");
        String base64Image = "";
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Lấy tên tệp

        int star = Integer.parseInt(rating);

        InputStream imageStream = filePart.getInputStream();
        byte[] imageBytes = IOUtils.toByteArray(imageStream);

        if (fileName != null && !fileName.isEmpty()) {
            base64Image = Base64.getEncoder().encodeToString(imageBytes);
        }

        fed.addNewFeedback(u.getFullName(), star, feedback_content, base64Image.length() > 0 ? "data:image/png;base64," + base64Image : "",
                product_id, u.getUser_id(), u.getAvatar());
        String historyUrl = (String) session.getAttribute("historyUrl");
        response.sendRedirect(historyUrl);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
