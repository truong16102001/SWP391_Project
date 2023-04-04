package controller.common;

import java.io.IOException;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.User;

@WebFilter(filterName = "AdminFilter", urlPatterns = {"/admin-manage", "/blog-list", "/add-blog",
    "/blog-details", "/manageorder", "/order-detail"})
public class AdminFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("us");

        if (user != null && (user.getRole_id() == 1 || user.getRole_id() == 2)) {
            chain.doFilter(request, response);
            return;
        }

        req.setAttribute("notification", "Rất tiêc bạn không có quyền truy cập đường dẫn này!");
        request.getRequestDispatcher("home.jsp").forward(request, response);

    }

    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }
}
