/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tannv.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tannv.user.UserDTO;

/**
 *
 * @author TanNV
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private static final String LOGIN_PAGE = "login.jsp";
    private static final String LOADLIST_CONTROLLER = "LoadListController";
    private static final String LOGIN_CONTROLLER = "LoginController";
    private static final String SEARCH_NAME_CONTROLLER = "SearchNameController";
    private static final String SEARCH_CATEGORY_CONTROLLER = "SearchCategoryController";
    private static final String LIST_CART_CONTROLLER = "ListCartController";
    private static final String LOGOUT_CONTROLLER = "LogoutController";
    private static final String TRACKING_CONTROLLER = "TrakingOrderController";
    private static final String PAY_CONTROLLER = "PayController";
    private static final String CREATE_CONTROLLER = "CreateController";
    private static final String UPDATE_CONTROLLER = "UpdateCakeController";
     private static final String LISTCART_CONTROLLER = "ListCartController";
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String action = request.getParameter("action");
        String url = LOGIN_PAGE;
        try {

            UserDTO user = (UserDTO) (request.getSession()).getAttribute("userData");

            if (user == null) {
                if (action == null) {
                    url = LOADLIST_CONTROLLER;
                } else if (action.equalsIgnoreCase("login")) {
                    url = LOGIN_CONTROLLER;
                } else if (action.equalsIgnoreCase("searchName")) {
                    url = SEARCH_NAME_CONTROLLER;
                } else if (action.equalsIgnoreCase("searchCategory")) {
                    url = SEARCH_CATEGORY_CONTROLLER;
                } else if (action.equalsIgnoreCase("cart")){
                    url = LISTCART_CONTROLLER;
                } else if (action.equalsIgnoreCase("pay")){
                    url = PAY_CONTROLLER;
                } else if(action.equalsIgnoreCase("loadList")){
                    url = LOADLIST_CONTROLLER;
                }
            } else {
                if (action == null) {
                    url = LOGIN_PAGE;
                } else if (action.equalsIgnoreCase("logout")) {
                    url = LOGOUT_CONTROLLER;
                } else if (action.equalsIgnoreCase("loadList")) {
                    url = LOADLIST_CONTROLLER;
                } else if (action.equalsIgnoreCase("listCart")) {
                    url = LIST_CART_CONTROLLER;
                } else if (action.equalsIgnoreCase("searchName")) {
                    url = SEARCH_NAME_CONTROLLER;
                } else if (action.equalsIgnoreCase("searchCategory")) {
                    url = SEARCH_CATEGORY_CONTROLLER;
                } else if (action.equals("tracking")) {
                    url = TRACKING_CONTROLLER;
                } else if (action.equalsIgnoreCase("pay")) {
                    url = PAY_CONTROLLER;
                } else if (action.equalsIgnoreCase("create")) {
                    url = CREATE_CONTROLLER;
                } else if (action.equals("update")) {
                    url = UPDATE_CONTROLLER;
                } 
            }
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
            
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
