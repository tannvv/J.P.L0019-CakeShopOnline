/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tannv.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import tannv.cake.CakeDAO;
import tannv.cake.CakeDTO;
import tannv.category.CategoryDAO;
import tannv.user.UserDTO;
import tannv.utils.Utils;

/**
 *
 * @author TanNV
 */
@WebServlet(name = "SearchNameController", urlPatterns = {"/SearchNameController"})
public class SearchNameController extends HttpServlet {

    
    static final Logger LOGGER = Logger.getLogger(SearchNameController.class);
    private final String INDEX_USER_PAGE = "indexUser.jsp";
    private final String INDEX_PAGE = "indexCustomer.jsp";
    private final String INDEX_ADMIN_PAGE = "indexAdmin.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
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
        try {
            String name = request.getParameter("name");
            CakeDAO dao = new CakeDAO();
            CategoryDAO categoryDAO = new CategoryDAO();
            ArrayList<CakeDTO> listCake = dao.getByName(name, 1);
            if (listCake.isEmpty()) {
                request.setAttribute("errorName", "Cannot find any name cake");
            }
            request.setAttribute("listCake", listCake);
            request.setAttribute("listCategory", categoryDAO.getAllCategories());
            request.setAttribute("numberPage", Utils.numberPage(listCake.size()));
        } catch (Exception e) {
            LOGGER.error("Search name is failed : " + e);
        }finally{
            UserDTO userData = (UserDTO)request.getSession().getAttribute("userData");
            if (userData != null) {
                if (userData.getRole() == 2) { // admin
                    request.getRequestDispatcher(INDEX_ADMIN_PAGE).forward(request, response);
                }else{
                    request.getRequestDispatcher(INDEX_USER_PAGE).forward(request, response);
                }
            }else{
                   request.getRequestDispatcher(INDEX_PAGE).forward(request, response);
            }
        }
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
