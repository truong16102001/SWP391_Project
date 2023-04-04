package controller.material;

import dao.inter.IMaterialDAO;
import dao.impl.MaterialDAOImpl;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import model.Material;

public class SearchMaterial extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String txtSearch = request.getParameter("search").trim();
        IMaterialDAO dAO = new MaterialDAOImpl();
        ArrayList<Material> materials = dAO.searchByName(txtSearch);
        
        request.setAttribute("materials", materials);
        request.setAttribute("txtSearch", txtSearch);
        request.getRequestDispatcher("./view/materialList.jsp").forward(request, response);
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
