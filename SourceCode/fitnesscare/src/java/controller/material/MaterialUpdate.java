package controller.material;

import dao.inter.IMaterialDAO;
import dao.impl.MaterialDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Material;

public class MaterialUpdate extends HttpServlet {

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
        String mId_str = request.getParameter("mId");
        String mName = request.getParameter("mName");
        String mQuantity_str = request.getParameter("mQuantity");
        String mBroken_str = request.getParameter("mBrokenQuantity");
        int mId = Integer.parseInt(mId_str);
        int mQuantity = Integer.parseInt(mQuantity_str);
        int mBrokenQuantity = Integer.parseInt(mBroken_str);
        IMaterialDAO dAO = new MaterialDAOImpl();
        Material material = Material.builder()
                .material_id(mId)
                .material_name(mName)
                .quantity(mQuantity)
                .broken_quantity(mBrokenQuantity).build();
        dAO.update(material);
        response.sendRedirect("managemateriallist");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
