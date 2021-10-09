/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tannv.orderDetail;

import java.io.Serializable;
import tannv.cake.CakeDTO;

/**
 *
 * @author TanNV
 */
public class OrderDetailDTO implements Serializable{
    private CakeDTO cakeID;
    private int quantity, orderID;
    private Boolean status;

    public OrderDetailDTO() {
    }

    public OrderDetailDTO(CakeDTO cakeID, int quantity) {
        this.cakeID = cakeID;
        this.quantity = quantity;
         this.status = true;
    }

    public OrderDetailDTO(CakeDTO cakeID, int quantity, int orderID) {
        this.cakeID = cakeID;
        this.quantity = quantity;
        this.orderID = orderID;
    
    }

    public OrderDetailDTO(CakeDTO cakeID, int quantity, int orderID, Boolean status) {
        this.cakeID = cakeID;
        this.quantity = quantity;
        this.orderID = orderID;
        this.status = status;
    }
    

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public CakeDTO getCakeID() {
        return cakeID;
    }

    public void setCakeID(CakeDTO cakeID) {
        this.cakeID = cakeID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }
    

    

    @Override
    public String toString() {
        return "OrderDetailDTO{" + "orderID=" + orderID + ", cakeID=" + cakeID + ", quantity=" + quantity + '}';
    }

    
}
