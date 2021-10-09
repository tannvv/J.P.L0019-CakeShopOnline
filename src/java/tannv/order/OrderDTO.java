/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tannv.order;

import java.io.Serializable;

/**
 *
 * @author TanNV
 */
public class OrderDTO implements Serializable{
    private int orderID,statusPayment,totalCost,method;
    private String address,userID,orderDate,customerName,phone;

    public OrderDTO() {
    }

    public OrderDTO(int statusPayment, int totalCost, int method, String address, String userID, String orderDate, String customerName, String phone) {
        this.statusPayment = statusPayment;
        this.totalCost = totalCost;
        this.method = method;
        this.address = address;
        this.userID = userID;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.phone = phone;
    }

    public OrderDTO(int statusPayment, int totalCost, int method, String address, String orderDate, String customerName, String phone) {
        this.statusPayment = statusPayment;
        this.totalCost = totalCost;
        this.method = method;
        this.address = address;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.phone = phone;
    }

    public OrderDTO(int orderID, int statusPayment, int totalCost, int method, String address, String userID, String orderDate, String customerName, String phone) {
        this.orderID = orderID;
        this.statusPayment = statusPayment;
        this.totalCost = totalCost;
        this.method = method;
        this.address = address;
        this.userID = userID;
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.phone = phone;
    }

    


    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getStatusPayment() {
        return statusPayment;
    }

    public void setStatusPayment(int statusPayment) {
        this.statusPayment = statusPayment;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    

    public int getMethod() {
        return method;
    }

    public void setMethod(int method) {
        this.method = method;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    

    @Override
    public String toString() {
        return "OrderDTO{" + "orderID=" + orderID + ", statusPayment=" + statusPayment + ", totalCost=" + totalCost + ", orderDate=" + orderDate + ", method=" + method + ", address=" + address + ", userID=" + userID + '}';
    }
    
}
