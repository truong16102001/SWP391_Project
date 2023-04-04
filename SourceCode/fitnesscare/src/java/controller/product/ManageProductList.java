package controller.product;

import dao.impl.CategoryProductDAOImpl;
import dao.impl.ProductDAOImpl;
import dao.inter.ICategoryProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.CategoryProduct;
import model.Product;
import java.sql.Date;
import dao.inter.IProductDAO;

public class ManageProductList extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            IProductDAO productDAO = new ProductDAOImpl();
            ICategoryProductDAO categoryProductDAO = new CategoryProductDAOImpl();
            ArrayList<Product> products = productDAO.getAllProduct();
            ArrayList<CategoryProduct> categoryProducts = categoryProductDAO.getAllCategory();
            Date date = productDAO.getCurrentDate();
            request.setAttribute("products", products);
            request.setAttribute("categoryProducts", categoryProducts);
            request.setAttribute("date", date);
            request.getRequestDispatcher("/view/adminProductList.jsp").forward(request, response);
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
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
