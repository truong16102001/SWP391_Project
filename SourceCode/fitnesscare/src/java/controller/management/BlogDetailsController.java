/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.management;

import dao.impl.BlogDAOImpl;
import dao.impl.UserDAOImpl;
import dao.inter.IBlogDAO;
import dao.inter.IUserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Blog;
import model.User;


public class BlogDetailsController extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BlogDetailsController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BlogDetailsController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        int blog_id = Integer.parseInt(request.getParameter("blog_id"));
        IBlogDAO dao = new BlogDAOImpl();
        Blog blog = dao.getBlogByBlogId(blog_id);
        request.setAttribute("blog", blog);

        IUserDAO ud = new UserDAOImpl();
        User author = ud.getUserById(blog.getAuthor_id());
        request.setAttribute("author", author);

        request.getRequestDispatcher("./view/blogdetails-manage.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
