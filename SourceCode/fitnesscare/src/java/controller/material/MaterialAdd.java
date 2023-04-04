package controller.material;

import dao.inter.IMaterialDAO;
import dao.impl.MaterialDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MaterialAdd extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            
            out.println(" <div class=\"modal fade\" id=\"AddModal\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"AddModalLabel\" aria-hidden=\"true\">\n"
                    + "            <div class=\"modal-dialog\" role=\"document\">\n"
                    + "                <div class=\"modal-content\">\n"
                    + "                    <div class=\"modal-header\">\n"
                    + "                        <h5 class=\"modal-title\" id=\"AddModalLabel\">Thêm vật tư</h5>\n"
                    + "                        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\n"
                    + "                            <span aria-hidden=\"true\">&times;</span>\n"
                    + "                        </button>\n"
                    + "                    </div>\n"
                    + "                    <form action=\"MaterialAdd\" method=\"post\">\n"
                    + "                        <div class=\"modal-body\">\n"
                    + "                            Tên vật tư: <input type=\"text\" value=\"\" name=\"mName\"> <br>\n"
                    + "                            Số lượng: <input type=\"text\" value=\"\" name=\"mQuantity\"> <br>\n"
                    + "                            Số lượng hỏng <input type=\"text\" value=\"\" name=\"mBrokenQuantity\"> <br>\n"
                    + "                        </div>\n"
                    + "                        <div class=\"modal-footer\">\n"
                    + "                            <button type=\"button\" class=\"btn btn-secondary\" data-dismiss=\"modal\">Close</button>\n"
                    + "                            <button type=\"submit\" class=\"btn btn-primary\">Add</button>\n"
                    + "                        </div>\n"
                    + "                    </form>\n"
                    + "                </div>\n"
                    + "            </div>");

        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String mName = request.getParameter("mName");
        String mQuantity_str = request.getParameter("mQuantity");
        String mBroken_str = request.getParameter("mBrokenQuantity");
        int mQuantity = Integer.parseInt(mQuantity_str);
        int mBrokenQuantity = Integer.parseInt(mBroken_str);
        IMaterialDAO dAO = new MaterialDAOImpl();
        dAO.add(mName, mQuantity, mBrokenQuantity);
        response.sendRedirect("managemateriallist");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
