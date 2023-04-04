package controller.product;

import dao.impl.ProductDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Date;
import dao.inter.IProductDAO;

@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2 MB
        maxFileSize = 1024 * 1024 * 10, // 10 MB
        maxRequestSize = 1024 * 1024 * 50 // 50 MB
)
public class ProductAddController extends HttpServlet {

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
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        IProductDAO dAO = new ProductDAOImpl();

        String pName = request.getParameter("pName").trim();

        String oPrice = request.getParameter("pOPrice").trim();

        int origin_price = Integer.parseInt(oPrice);

        String pPrice = request.getParameter("pPrice").trim();

        int price = Integer.parseInt(pPrice);

        String pDes = request.getParameter("pDescription").trim();

        String pBrief = request.getParameter("pBrief_infor").trim();

        String pQuantity = request.getParameter("pQuantity").trim();

        int quantity = Integer.parseInt(pQuantity);

        boolean status = request.getParameter("pStatus").equals("true");

        String pCategory = request.getParameter("pCategory").trim();

        int category = Integer.parseInt(pCategory);

        String pUpdateDate = request.getParameter("pDate");

        Date date = Date.valueOf(pUpdateDate);

        Part filePart = request.getPart("image");
        String filename = filePart.getSubmittedFileName();
        String uploadPath = "C:/Users/ACER/Desktop/SWP391/fitnesscare/web/images/product/" + filename;
        try {

            FileOutputStream fileOutputStream = new FileOutputStream(uploadPath);

            InputStream is = filePart.getInputStream();

            byte[] data = new byte[is.available()];
            is.read(data);
            fileOutputStream.write(data);
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] words = uploadPath.split("/", 8);
        dAO.addImg(words[7]);
        dAO.addProduct(pName, origin_price, price, pDes, pBrief, quantity, status, category, date);
        int product_id = dAO.searchIdProduct(pName, origin_price, price, quantity, status, category, date);
        int img_id = dAO.searchIdImg(words[7]);
        dAO.addProductImg(product_id, img_id);
        response.sendRedirect("manageproductlist");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
