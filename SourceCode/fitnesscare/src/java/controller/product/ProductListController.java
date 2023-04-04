package controller.product;

import dao.impl.CategoryProductDAOImpl;
import dao.inter.IProductDAO;
import dao.impl.ProductDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import model.CategoryProduct;
import model.Product;

public class ProductListController extends HttpServlet {

    private final int record_per_page = 8;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            IProductDAO pd = new ProductDAOImpl();
            // get all category
            ArrayList<CategoryProduct> listcp = new CategoryProductDAOImpl().getAllCategory();
            // get all product
            ArrayList<Product> list = pd.getAllProduct();
            // get index of page
            String indexPage = request.getParameter("index");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);

            // Set category
            int cp_id = 0;
            String categoryId = "!= -1";
            String strCategoryId = request.getParameter("cp_id");
            if (strCategoryId != null) {
                cp_id = Integer.parseInt(strCategoryId);
                categoryId = "= " + strCategoryId;
            }

            // Set key for search 
            String searchKey = "";
            String strSearchKey = request.getParameter("key");
            if (strSearchKey != null) {
                searchKey = strSearchKey.trim();
            }

            // Set sort 
            String value = "update_date";
            String type = "";
            String strType = request.getParameter("type");
            String strValue = request.getParameter("value");
            if (strType != null) {
                type = strType;
            }
            if (strValue != null) {
                value = strValue;
            }

            // System.out.println(searchKey);
            // filter
            List<Product> listOfPage = pd.pagingProduct(index, record_per_page, searchKey, categoryId, type, value);
            int count = pd.countProductByCondition(searchKey, categoryId);
          
            //paging
            int endPage = (count / record_per_page);
            if (count % record_per_page != 0) {
                endPage++;
            }
            // Set param request to jsp page    
            session.setAttribute("listProduct", list);
            session.setAttribute("listcp", listcp);
            session.setAttribute("historyUrl", "list-product");
            String history = "list-product?index=" + indexPage;
            if (strCategoryId != null) {
                //set cp_id
                history = history + "&cp_id=" + strCategoryId;
                request.setAttribute("historyCategoryId", "&cp_id=" + strCategoryId);
                request.setAttribute("cp_id", cp_id);
            }
            if (strSearchKey != null) {
                history = history + "&key=" + strSearchKey;
                request.setAttribute("historyKey", "&key=" + strSearchKey);
            }
            request.setAttribute("key", searchKey);
            //set value
            if (strValue != null) {
                history = history + "&value=" + strValue;
                request.setAttribute("historyValue", "&value=" + strValue);
            }
            request.setAttribute("value", value);

            if (strType != null) {
                history = history + "&type=" + strType;
                request.setAttribute("historyType", "&type=" + strType);
            }
            request.setAttribute("type", type);

            request.setAttribute("active2", 2);
            session.setAttribute("historyUrl", history);
            request.setAttribute("cp_id", cp_id);

            request.setAttribute("endPage", endPage);
            request.setAttribute("listOfPage", listOfPage);
            request.setAttribute("pageIndex", index);

            request.getRequestDispatcher("./view/productList.jsp").forward(request, response);
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
