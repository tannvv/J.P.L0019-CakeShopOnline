/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tannv.controller;

import java.io.IOException;
import java.time.LocalDateTime;
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
@WebServlet(name = "PayController", urlPatterns = {"/PayController"})
public class PayController extends HttpServlet {

    static final Logger LOGGER = Logger.getLogger(PayController.class);
    private final String VIEW_CART_PAGE = "viewCart.jsp";
    private final String INFOR_PAY = "inforPay.jsp";
    private final String ERROR_PAY = "error.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            OrderDetailDAO detailDAO = new OrderDetailDAO();
            ArrayList<OrderDetailDTO> listCart = (ArrayList<OrderDetailDTO>) request.getSession().getAttribute("listCart");
            boolean checkAmount = detailDAO.validListCartToPay(listCart);
            if (checkAmount) {
                int totalCost = getTotalCost((ArrayList<OrderDetailDTO>) request.getSession().getAttribute("listCart"));
                request.setAttribute("totalCost", totalCost);
                request.getRequestDispatcher(INFOR_PAY).forward(request, response);
            } else {
                request.setAttribute("error", "Out of stock, cannot buy");
                request.getRequestDispatcher(VIEW_CART_PAGE).forward(request, response);
            }
        } catch (Exception e) {
            LOGGER.error("Pay is error " + e.getMessage());
            request.getRequestDispatcher(ERROR_PAY).forward(request, response);
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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            OrderDetailDAO detailDAO = new OrderDetailDAO();
            OrderDAO orderDAO = new OrderDAO();
            UserDTO userData = (UserDTO) request.getSession().getAttribute("userData");

            String nameCustomer = request.getParameter("nameCustomer");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");
            int totalCost = getTotalCost((ArrayList<OrderDetailDTO>) request.getSession().getAttribute("listCart"));;
            ArrayList<OrderDetailDTO> listCartSession = (ArrayList<OrderDetailDTO>) request.getSession().getAttribute("listCart");
            // get cakes have status is true in the listcart
            ArrayList<OrderDetailDTO> listCart = getListCartValid(listCartSession);
            boolean checkAmount = detailDAO.validListCartToPay(listCart);
            if (checkAmount) {
                String today = LocalDateTime.now().toString();
                OrderDTO order = null;
                if (userData == null) {
                    order = new OrderDTO(1, totalCost, 1, address, today, nameCustomer, phone);
                } else {
                    order = new OrderDTO(1, totalCost, 1, address, userData.getUserID(), today, nameCustomer, phone);;
                }
                orderDAO.createOrder(order);
                int orderID = orderDAO.getOrderID(nameCustomer, today);
                setOrderIdToListCart(listCart, orderID);
                detailDAO.storeListCart(listCart);
                request.setAttribute("notifi", "ID new order : " + orderID);
                request.getSession().setAttribute("listCart", getListCartInValid(listCartSession));
            } else {
                request.setAttribute("error", "Out of stock, cannot buy");
                request.getRequestDispatcher(VIEW_CART_PAGE).forward(request, response);
            }
        } catch (Exception e) {
            LOGGER.error("Pay is error " + e.getMessage());
        } finally {
            request.getRequestDispatcher("LoadListController").forward(request, response);
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

    private void setOrderIdToListCart(ArrayList<OrderDetailDTO> listCart, int orderID) {
        int size = listCart.size();
        for (int i = 0; i < size; i++) {
            listCart.get(i).setOrderID(orderID);
        }
    }

    private int getTotalCost(ArrayList<OrderDetailDTO> listCart) {
        int result = 0;
        int size = listCart.size();
        for (int i = 0; i < size; i++) {
            if (listCart.get(i).getStatus() == true) {
                result += listCart.get(i).getCakeID().getPrice() * listCart.get(i).getQuantity();
            }
        }
        return result;
    }
    private ArrayList<OrderDetailDTO> getListCartValid(ArrayList<OrderDetailDTO> listCart){
        ArrayList<OrderDetailDTO> result = new ArrayList<>();
        int size = listCart.size();
        for (int i = 0; i < size; i++) {
            if (listCart.get(i).getStatus() == true) {
                result.add(listCart.get(i));
            }
        }
        return result ;
    }
    private ArrayList<OrderDetailDTO> getListCartInValid(ArrayList<OrderDetailDTO> listCart){
        ArrayList<OrderDetailDTO> result = new ArrayList<>();
        int size = listCart.size();
        for (int i = 0; i < size; i++) {
            if (listCart.get(i).getStatus() == false) {
                result.add(listCart.get(i));
            }
        }
        return result ;
    }
}
