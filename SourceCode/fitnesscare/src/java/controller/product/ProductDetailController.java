package controller.product;

import dao.impl.FeedbackDAOImpl;
import dao.inter.IFeedbackDAO;
import dao.inter.IProductDAO;
import dao.impl.OrderDAOImpl;
import dao.impl.ProductDAOImpl;
import dao.inter.IOrderDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Feedback;
import model.Order;
import model.Product;
import model.User;

public class ProductDetailController extends HttpServlet {

    private final int record_per_page = 3;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        IFeedbackDAO fd = new FeedbackDAOImpl();
        HttpSession session = request.getSession();
        IProductDAO pd = new ProductDAOImpl();
        IOrderDAO od = new OrderDAOImpl();
        String productidStr = request.getParameter("product_id");
        String categoryidStr = request.getParameter("cp_id");
        int product_id = Integer.parseInt(productidStr);
        int category_id = Integer.parseInt(categoryidStr);
        User user = (User) session.getAttribute("us");
        // get index of page
        String indexPage = request.getParameter("index");
        if (indexPage == null) {
            indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);

        // get total number of feedback
        List<Feedback> feedbackList = fd.getAllFeedbackByProductId(product_id);
        int endPage = (feedbackList.size() / record_per_page);
        if (feedbackList.size() % record_per_page != 0) {
            endPage++;
        }
        List<Feedback> feedbacks = fd.pagingFeedback(product_id, index, record_per_page);
        Order accept = null;
        if (user != null) {
            accept = od.checkProductOrderByUser(user.getUser_id(), product_id);
        }
        Product p = pd.getProductByID(product_id);
        List<Product> relatedProducts = pd.getRelatedProduct(product_id, category_id);

        String history = "product-detail?product_id=" + productidStr + "&cp_id=" + categoryidStr + "&index=" + indexPage;
        session.setAttribute("historyUrl", history);
        request.setAttribute("productFeedbacks", feedbacks);
        request.setAttribute("relatedProducts", relatedProducts);
        request.setAttribute("product", p);

        request.setAttribute("accept", accept);
        request.setAttribute("endPage", endPage);
        request.setAttribute("pageIndex", index);
        request.setAttribute("totalFeedback", feedbackList.size());
        request.getRequestDispatcher("/view/product-detail.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
