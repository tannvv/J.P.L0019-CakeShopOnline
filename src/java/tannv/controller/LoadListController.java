/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tannv.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import tannv.cake.CakeDAO;
import tannv.category.CategoryDAO;
import tannv.user.UserDTO;

/**
 *
 * @author TanNV
 */
@WebServlet(name = "LoadListController", urlPatterns = {"/LoadListController"})
public class LoadListController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    static final Logger LOGGER = Logger.getLogger(LoadListController.class);
    private final String INDEX_USER_PAGE = "indexUser.jsp";
    private final String INDEX_ADMIN_PAGE = "indexAdmin.jsp";
     private final String INDEX_CUSTOMER_PAGE = "indexCustomer.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        
        UserDTO userData = (UserDTO)request.getSession().getAttribute("userData");
        int indexPage = 1;
        try{
            indexPage = Integer.parseInt(request.getParameter("indexPage"));
        }catch(Exception e){
            LOGGER.error("Page index = 1");
             indexPage = 1;
        }
        CakeDAO cakeDao = new CakeDAO();
        CategoryDAO catDao = new CategoryDAO();
        request.setAttribute("listCake", cakeDao.getPageCake(indexPage));
        request.setAttribute("listCategory", catDao.getAllCategories());
        request.setAttribute("numberPage", cakeDao.getNumberPage());
        if (userData == null) {
            request.getRequestDispatcher(INDEX_CUSTOMER_PAGE).forward(request, response);
        }else{
            if (userData.getRole() == 1) {      // member
                request.getRequestDispatcher(INDEX_USER_PAGE).forward(request, response);
            }else{                              // admin
                request.getRequestDispatcher(INDEX_ADMIN_PAGE).forward(request, response);
            }
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
        
        try {
            processRequest(request, response);
        } catch (Exception e) {
        }
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
                   processRequest(request, response);
        } catch (Exception e) {
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
