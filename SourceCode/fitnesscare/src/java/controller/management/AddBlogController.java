/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.management;

import dao.impl.BlogDAOImpl;
import dao.inter.IBlogDAO;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Base64;

import org.apache.commons.io.IOUtils;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 50 // 50 MB
)
public class AddBlogController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AddBlogController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddBlogController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("addBlog.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // processRequest(request, response);
        HttpSession session = request.getSession();
        User u = (User) session.getAttribute("us");
        int user_id = u.getUser_id();
        String url_Thumbnail = "";
        Part filePart = request.getPart("thumbnail");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Lấy tên tệp
        if (fileName != null && !fileName.isEmpty()) {
            InputStream imageStream = filePart.getInputStream();
            byte[] imageBytes = IOUtils.toByteArray(imageStream);
            url_Thumbnail = "data:image/png;base64," + Base64.getEncoder().encodeToString(imageBytes);
        }

        String title = request.getParameter("title").trim();

        String content = request.getParameter("content").trim();
        String brief_infor = request.getParameter("brief_info").trim();
        int category_id = Integer.parseInt(request.getParameter("cb_id"));
        boolean status = Boolean.parseBoolean(request.getParameter("status"));
        IBlogDAO bd = new BlogDAOImpl();
        bd.addNewBlog(title, user_id, content, brief_infor, category_id, status, url_Thumbnail);
        response.sendRedirect("blog-list");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
