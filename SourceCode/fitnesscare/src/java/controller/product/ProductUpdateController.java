package controller.product;

import com.google.gson.Gson;
import dao.impl.ProductDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.text.SimpleDateFormat;
import model.Product;
import dao.inter.IProductDAO;

public class ProductUpdateController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.println("");

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        int id = Integer.parseInt(request.getParameter("id"));
        IProductDAO productDAO = new ProductDAOImpl();
        Product product = productDAO.getProductByID(id);
        Gson gson = new Gson();
        PrintWriter writer = response.getWriter();
        writer.print(gson.toJson(product));
        writer.flush();
        writer.close();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        IProductDAO dAO = new ProductDAOImpl();

        String pName = request.getParameter("product_name").trim();

        String oPrice = request.getParameter("original_price").trim();

        int origin_price = Integer.parseInt(oPrice);

        String pPrice = request.getParameter("sale_price").trim();

        int price = Integer.parseInt(pPrice);

        String pDes = request.getParameter("pDescription").trim();

        String pBrief = request.getParameter("brief_info").trim();

        String pQuantity = request.getParameter("quantity").trim();

        int quantity = Integer.parseInt(pQuantity);

        boolean status = request.getParameter("status").equals("true");

        String pCategory = request.getParameter("pCategory").trim();

        int category = Integer.parseInt(pCategory);

        String pUpdateDate = request.getParameter("update_date");
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        Date date = Date.valueOf(pUpdateDate);
        
        int productId = Integer.parseInt(request.getParameter("product_id"));
        
        dAO.updateProduct(productId, pName, origin_price, price, pDes, pBrief, quantity, status, price, date);
        
        response.sendRedirect("manageproductlist");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
