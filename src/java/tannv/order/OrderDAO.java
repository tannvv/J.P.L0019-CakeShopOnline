/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tannv.order;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import tannv.utils.DBHelper;

/**
 *
 * @author TanNV
 */
public class OrderDAO {
    private Connection conn = null;
    private PreparedStatement ptsm = null;
    private ResultSet rs = null;

    private void cleanConnect() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (ptsm != null) {
            ptsm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public boolean createOrder(OrderDTO order) throws SQLException {
        boolean result = false;
        try {
            String sql = "INSERT INTO Orders(orderDate,method,statusPayment,address,totalCost,userID,phoneNumber,nameCustomer) VALUES (?,?,?,?,?,?,?,?)";
            conn = DBHelper.getConnect();
            ptsm = conn.prepareStatement(sql);
            ptsm.setString(1, order.getOrderDate());
            ptsm.setInt(2, order.getMethod());
            ptsm.setInt(3, order.getStatusPayment());
            ptsm.setString(4, order.getAddress());
            ptsm.setInt(5, order.getTotalCost());
            ptsm.setString(6, order.getUserID());
            ptsm.setString(7, order.getPhone());
            ptsm.setString(8, order.getCustomerName());
            int check = ptsm.executeUpdate();
            if (check == 1) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            cleanConnect();
        }
        return result;
    }
    
     public OrderDTO getOrderByID(int orderID) throws SQLException{
        OrderDTO order = null;
        try {
            String sql = "SELECT orderDate,method,statusPayment,address,totalCost,userID,phoneNumber,nameCustomer FROM Orders \n"
                    + "WHERE OrderID = ?";
            conn = DBHelper.getConnect();
            ptsm = conn.prepareStatement(sql);
            ptsm.setInt(1, orderID);
            rs = ptsm.executeQuery();
            if (rs.next()) {
                String orderDate = rs.getString("orderDate");
                int method = rs.getInt("method");
                int statusPayment = rs.getInt("statusPayment");
                String address = rs.getString("address");
                int totalCost = rs.getInt("totalCost");
                String userID = rs.getString("userID");
                String phone = rs.getString("phoneNumber");
                String nameCustomer = rs.getString("nameCustomer");
                order = new OrderDTO(orderID, statusPayment, totalCost, method, address, userID, orderDate, nameCustomer, phone);
            }
        } catch (Exception e) {
        }finally{
            cleanConnect();
        }
        return order;
    }
    
    public int getOrderID(String name, String orderDate) throws SQLException{
        int orderID = -1;
        try {
            String sql = "SELECT orderID FROM Orders \n"
                    + "WHERE nameCustomer = ? AND orderDate = ?";
            conn = DBHelper.getConnect();
            ptsm = conn.prepareStatement(sql);
            ptsm.setString(1, name);
            ptsm.setString(2, orderDate);
            rs = ptsm.executeQuery();
            if (rs.next()) {
                orderID = rs.getInt("orderID");
            }
        } catch (Exception e) {
        }finally{
            cleanConnect();
        }
        return orderID;
    }
    
    public boolean paymentOrder(int orderID) throws SQLException{
        boolean result = false;
        try {
            String sql = "UPDATE Orders SET statusPayment = 'true' \n"
                    + "WHERE orderID = ?";
            conn = DBHelper.getConnect();
            ptsm = conn.prepareStatement(sql);
            ptsm.setInt(1, orderID);
            if (ptsm.executeUpdate() == 1) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            cleanConnect();
        }
        return result;
    }
    
    
}
