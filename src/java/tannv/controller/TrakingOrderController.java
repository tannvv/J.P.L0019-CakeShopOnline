/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tannv.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import tannv.order.OrderDAO;
import tannv.order.OrderDTO;
import tannv.orderDetail.OrderDetailDAO;
import tannv.orderDetail.OrderDetailDTO;
import tannv.user.UserDTO;

/**
 *
 * @author TanNV
 */
@WebServlet(name = "TrakingOrderController", urlPatterns = {"/TrakingOrderController"})
public class TrakingOrderController extends HttpServlet {

    private final String TRACKING_PAGE = "trackingOrder.jsp";
        private final String LOGIN_PAGE = "login.jsp";
    static final Logger LOGGER = Logger.getLogger(TrakingOrderController.class);

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        UserDTO userData = (UserDTO) request.getSession().getAttribute("userData");
        if (userData == null) {
            request.setAttribute("error", "You not have permission to to this fucntion");
            request.getRequestDispatcher(LOGIN_PAGE).forward(request, response);
        }  
        request.getRequestDispatcher(TRACKING_PAGE).forward(request, response);
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
        String orderID = request.getParameter("orderID");
        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        OrderDAO orderDAO = new OrderDAO();
        UserDTO userData = (UserDTO) request.getSession().getAttribute("userData");

        try {

            OrderDTO order = orderDAO.getOrderByID(Integer.parseInt(orderID));

            if (order != null) {
                if (userData.getRole() == 1) { // user
                    if (order.getUserID().equalsIgnoreCase(userData.getUserID())) { // order of this user                   
                        ArrayList<OrderDetailDTO> listOrderDetail = orderDetailDAO.getOrderDetailByOrderID(Integer.parseInt(orderID));
                        request.setAttribute("listOrderDetail", listOrderDetail);
                        request.setAttribute("order", order);
                    } else {        // order not for this user
                        request.setAttribute("error", "Cannot find any order ID");
                    }
                }else{
                    ArrayList<OrderDetailDTO> listOrderDetail = orderDetailDAO.getOrderDetailByOrderID(Integer.parseInt(orderID));
                    request.setAttribute("listOrderDetail", listOrderDetail);
                    request.setAttribute("order", order);
                }
            } else {
                request.setAttribute("error", "Cannot find any order ID");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            request.setAttribute("error", "Cannot find any order ");
        } finally {
            request.getRequestDispatcher(TRACKING_PAGE).forward(request, response);
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
