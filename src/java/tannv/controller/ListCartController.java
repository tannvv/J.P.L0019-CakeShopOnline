/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tannv.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import tannv.cake.CakeDAO;
import tannv.cake.CakeDTO;
import tannv.orderDetail.OrderDetailDTO;

/**
 *
 * @author TanNV
 */
@WebServlet(name = "ListCartController", urlPatterns = {"/ListCartController"})
public class ListCartController extends HttpServlet {

    private final String VIEW_CART = "viewCart.jsp";
    static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(ListCartController.class);
    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");

        try {
            //String id = request.getParameter("id"); // watch id to do action
            if (action.equals("view")) { // view
                ArrayList<OrderDetailDTO> listCart = null;
                listCart = (ArrayList<OrderDetailDTO>) request.getSession().getAttribute("listCart");
                if (listCart == null) {
                    listCart = new ArrayList<>();
                    request.getSession().setAttribute("listCart", listCart);
                }
                request.setAttribute("totalCost", getTotalCost(listCart));
                request.getRequestDispatcher(VIEW_CART).forward(request, response);
            }
            if (action.equals("buy")) { // add to cart
                buyCart(request, response);
            }
            if (action.equals("remove")) { // remove cart
                removeCart(request, response);
            }
            if (action.equals("update")) {
                updateCart(request, response);
            }
        } catch (Exception e) {
            LOGGER.error("Liscart is error" + e.getMessage());
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
        } catch (SQLException ex) {
            Logger.getLogger(ListCartController.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (SQLException ex) {
            Logger.getLogger(ListCartController.class.getName()).log(Level.SEVERE, null, ex);
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

    private void buyCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        // Flow 
        // Step 1 : l???y gi??? h??ng t??? tr??n session xu???ng ????? x??? l??
        // Step 2 : l???y ID c???a s???n ph???m ng?????i d??ng mu???n th??m v??o gi??? h??ng
        // Step 3 : ki???m tra xem gi??? h??ng ???? t???n t???i s???n ph???m ng?????i d??ng mu???n mua ch??a
        // - n???u s???n ph???m ???? c?? trong gi??? h??ng => th??m m???i s???n ph???m
        // - n???u s???n ph???m ch??? c?? trongn gi??? h??ng => c???p nh???t s??? l?????ng c???a s???n ph???m += 1
        // Step 4 : c???p nh???t l???i gi??? h??ng tr??n session

        // l???y gi??? h??ng t??? session
        ArrayList<OrderDetailDTO> listCart = (ArrayList<OrderDetailDTO>) request.getSession().getAttribute("listCart");
        if (listCart == null) {
            listCart = new ArrayList<>();
        }
        // l???y ID c???a s???n ph???m m?? kh??ch h??ng mu???n th??m v??o gi??? h??ng
        String id = request.getParameter("id");
        // ki???m tra s???n ph???m ???? t???n t???i trong gi??? h??ng hay ch??a
        int indexCart = checkExistedCart(listCart, id);
        if (indexCart >= 0) { // tr?????ng h???p ???? t???n t???i t???i th??m t??ng s??? l?????ng l??n 1
            int newQuantity = listCart.get(indexCart).getQuantity() + 1;
            listCart.get(indexCart).setQuantity(newQuantity);
        } else {    // tr?????ng h???p ch??a t???n t???i th?? th??m m???i s???n ph???m
            CakeDAO cakeDAO = new CakeDAO();
            CakeDTO cake = cakeDAO.getCakeByID(id);
            listCart.add(new OrderDetailDTO(cake, 1));
        }
        // l???y t???ng gi?? tr??? c???a gi??? h??ng
        request.setAttribute("totalCost", getTotalCost(listCart));
        // c???p nh???t l???i gi??? h??ng tr??n session
        request.getSession().setAttribute("listCart", listCart);
        request.getRequestDispatcher(VIEW_CART).forward(request, response);
    }

    private int checkExistedCart(ArrayList<OrderDetailDTO> listCart, String cakeID) {
        int size = listCart.size();
        for (int i = 0; i < size; i++) {
            if (listCart.get(i).getCakeID().getCakeID().equals(cakeID)) {
                return i;
            }
        }
        return -1;
    }

    private void removeCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        ArrayList<OrderDetailDTO> listCart = (ArrayList<OrderDetailDTO>) request.getSession().getAttribute("listCart");
        int index = checkExistedCart(listCart, id);
        listCart.remove(index);
        request.setAttribute("totalCost", getTotalCost(listCart));
        request.getSession().setAttribute("listCart", listCart);
        request.getRequestDispatcher(VIEW_CART).forward(request, response);
    }

    private int getTotalCost(ArrayList<OrderDetailDTO> listCart) {
        int result = 0;
        int size = listCart.size();
        for (int i = 0; i < size; i++) {
            if (listCart.get(i).getStatus()) {
                result += listCart.get(i).getCakeID().getPrice() * listCart.get(i).getQuantity();
            }
        }
        return result;
    }

    private void updateCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<OrderDetailDTO> listCart = (ArrayList<OrderDetailDTO>) request.getSession().getAttribute("listCart");
        String cakeID = request.getParameter("cakeID");

        // get value in listcart need to change
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String sts = request.getParameter("status");
        boolean status = Boolean.parseBoolean(sts);

        int index = checkExistedCart(listCart, cakeID);

        // set value
        listCart.get(index).setQuantity(quantity);
        listCart.get(index).setStatus(status);
        request.setAttribute("totalCost", getTotalCost(listCart));
        request.setAttribute("listCart", listCart);
        request.getRequestDispatcher(VIEW_CART).forward(request, response);

    }
}
