/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.service;

import dao.inter.IServiceDAO;
import dao.impl.ServiceDAOImpl;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Service;

/**
 *
 * @author dell
 */
public class ServiceListController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private int record_per_page = 4;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession();
            IServiceDAO sd = new ServiceDAOImpl();
            // get all service
            ArrayList<Service> list = sd.getAllService();
            // get index of page
            String indexPage = request.getParameter("index");
            if (indexPage == null) {
                indexPage = "1";
            }
            int index = Integer.parseInt(indexPage);

            // Set key for search 
            String searchKey = "";
            String strSearchKey = request.getParameter("key");
            if (strSearchKey != null) {
                searchKey = strSearchKey;
            }
            // Set sort 
            String value = "service_name";
            String type = "";
            String strType = request.getParameter("type");
            String strValue = request.getParameter("value");
            if (strType != null) {
                type = strType;
            }
            if (strValue != null) {
                value = strValue;
            }

            // get total number of product
            int endPage = (sd.getTotalService()/ record_per_page);
            if (sd.getTotalService()% record_per_page != 0) {
                endPage++;
            }

            // filter
            ArrayList<Service> listOfPage = sd.pagingService(index, record_per_page, searchKey, type, value);

            // Set param request to jsp page    
            session.setAttribute("listService", list);
            session.setAttribute("historyUrl", "list-service");
            String history = "list-service?index=" + indexPage;
            
            if (strSearchKey != null) {
                history = history + "&key=" + strSearchKey;
                request.setAttribute("historyKey", "&key=" + strSearchKey);
                request.setAttribute("key", strSearchKey);
            }
            if (strValue != null) {
                history = history + "&value=" + strValue;
                request.setAttribute("historyValue", "&value=" + strValue);
                request.setAttribute("value", strValue);
            }
            if (strType != null) {
                history = history + "&type=" + strType;
                request.setAttribute("historyType", "&type=" + strType);
                request.setAttribute("type", strType);
            }

            session.setAttribute("historyUrl", history);

            request.setAttribute("endPage", endPage);
            request.setAttribute("listOfPage", listOfPage);
            request.setAttribute("pageIndex", index);

            request.getRequestDispatcher("/view/serviceList.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
