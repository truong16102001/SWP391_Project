/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.management;

import dao.impl.BlogDAOImpl;
import dao.impl.CategoryBlogDAOImpl;
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
import java.util.List;
import model.Blog;
import model.CategoryBlog;
import model.User;

public class BlogListController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet BlogListController</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BlogListController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    final int PAGE_SIZE = 6;  // Set total product each page

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // processRequest(request, response);
        HttpSession session = request.getSession();
        IBlogDAO bd = new BlogDAOImpl();

        int page = 1;
        String strPage = request.getParameter("index");
        if (strPage != null) {
            page = Integer.parseInt(strPage);
        }

        // Set key for search 
        String searchKey = "";
        String strSearchKey = request.getParameter("key");
        if (strSearchKey != null && strSearchKey.length() > 0) {
            searchKey = strSearchKey.trim();
        }

        // Set category
        String cb_idStr = " != -1 ";
        String strCategoryId = request.getParameter("cb_id");
        if (strCategoryId != null && strCategoryId.length() > 0) {
            cb_idStr = "= " + strCategoryId;
        }

        // Set sort 
        String value = " updated_date ";
        String type = "";
        String strType = request.getParameter("type");
        String strValue = request.getParameter("value");
        if (strType != null && strType.length() > 0) {
            type = strType;
        }
        if (strValue != null && strValue.length() > 0) {
            value = strValue;
        }

        // Set status
        String status = " != -1 ";
        String strStatus = request.getParameter("status");
        if (strStatus != null && strStatus.length() > 0) {
            status = "= " + strStatus;
        }

        // Set author
        String author = " != -1";
        String strAuthor = request.getParameter("author_id");
        if (strAuthor != null && strAuthor.length() > 0) {
            author = "= " + strAuthor;
        }

        // Set total page 
        int totalPage = bd.getTotalBlog(searchKey, cb_idStr, status, author);
        int endPage = totalPage / PAGE_SIZE;
        if (totalPage % PAGE_SIZE != 0) {
            endPage += 1;
        }

        // Get list product, new, category
        List<Blog> listBlog = bd.getBlogWithPaging(page, PAGE_SIZE, searchKey, cb_idStr, type, value, status, author);
        List<CategoryBlog> listCategoryBlog = new CategoryBlogDAOImpl().getAllCategoryBlog();
        session.setAttribute("listCategoryBlog", listCategoryBlog);
        IUserDAO ud = new UserDAOImpl();
        List<User> listAuthor = bd.getAllAuthor();
        session.setAttribute("listAuthor", listAuthor);
        Blog newBlog = bd.getBlogNew();
        session.setAttribute("newBlog", newBlog);

        // Set param request to jsp page
        String history = "blog-list?index=" + page;
        if (strSearchKey != null) {
            history = history + "&key=" + strSearchKey;
            request.setAttribute("historyKey", "&key=" + strSearchKey);
            request.setAttribute("key", strSearchKey);
        }
        if (strCategoryId != null) {
            history = history + "&cb_id=" + strCategoryId;
            request.setAttribute("historyCategoryId", "&cb_id=" + strCategoryId);
            request.setAttribute("cb_id", strCategoryId);
        }
        if (strValue != null) {
            history = history + "&value=" + strValue;
            request.setAttribute("historyValue", "&value=" + strValue);
            request.setAttribute("value", strValue);
        }
        if (strType != null) {
            history = history + "&type=" + strType;
            request.setAttribute("historyType", "&type=" + strType);
            request.setAttribute("type", strType);
        }
        if (strStatus != null) {
            history = history + "&status=" + strStatus;
            request.setAttribute("historyStatus", "&status=" + strStatus);
            request.setAttribute("status", strStatus);
        }
        if (strAuthor != null) {
            history = history + "&author_id=" + strAuthor;
            request.setAttribute("historyAuthor", "&author_id=" + strAuthor);
            request.setAttribute("author_id", strAuthor);
        }

        request.setAttribute("pageIndex", page);
        request.setAttribute("endPage", endPage);
        session.setAttribute("historyUrl", history);
        request.setAttribute("listBlog", listBlog);

        // Request
        request.getRequestDispatcher("./view/blogList-Manage.jsp").forward(request, response);
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
