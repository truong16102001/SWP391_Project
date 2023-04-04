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

public class MaterialDelete extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        IMaterialDAO dAO = new MaterialDAOImpl();
        String string_id = request.getParameter("id");

        int id = Integer.parseInt(string_id);

        Material material = dAO.getMaterialById(id);
        try ( PrintWriter out = response.getWriter()) {
            
            out.println("<div class=\"modal fade\" id=\"DeleteModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"DeleteModalLabel\" aria-hidden=\"true\">\n"
                    + "            <div class=\"modal-dialog\" role=\"document\">\n"
                    + "                <div class=\"modal-content\">\n"
                    + "                    <div class=\"modal-header\">\n"
                    + "                        <h5 class=\"modal-title\" id=\"DeleteModalLabel\">Alert</h5>\n"
                    + "                        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n"
                    + "                            <span aria-hidden=\"true\">&times;</span>\n"
                    + "                        </button>\n"
                    + "                    </div>\n"
                    + "                    <form action=\"MaterialDelete\" method=\"post\">\n"
                    + "                        <div class=\"modal-body\">\n"
                    + "                        Bạn chắc chắn muốn xóa ?"
                    + "                            <input type=\"hidden\" value=" + material.getMaterial_id() + " name=\"mId\"> <br>\n"
                    + "                        </div>\n"
                    + "                        <div class=\"modal-footer\">\n"
                    + "                            <button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>\n"
                    + "                            <button type=\"submit\" class=\"btn btn-primary\">Delete</button>\n"
                    + "                        </div>\n"
                    + "                    </form>\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "        </div>");
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
        int mId = Integer.parseInt(mId_str);
        IMaterialDAO dAO = new MaterialDAOImpl();
        dAO.delete(mId);
        response.sendRedirect("managemateriallist");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
