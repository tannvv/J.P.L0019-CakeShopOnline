/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tannv.cake;

import java.io.Serializable;
import java.sql.Date;



/**
 *
 * @author TanNV
 */
public class CakeDTO implements Serializable{
    private String cakeID,cakeName,image,description,categoryID;
    private boolean status;
    private Date createDate,expDate;
    private int price,amount;

    public CakeDTO() {
    }

    public CakeDTO(String cakeID, String cakeName, String image, String description, String categoryID, boolean status, Date createDate, Date expDate, int price, int amount) {
        this.cakeID = cakeID;
        this.cakeName = cakeName;
        this.image = image;
        this.description = description;
        this.categoryID = categoryID;
        this.status = status;
        this.createDate = createDate;
        this.expDate = expDate;
        this.price = price;
        this.amount = amount;
    }

    public String getCakeID() {
        return cakeID;
    }

    public void setCakeID(String cakeID) {
        this.cakeID = cakeID;
    }

    public String getCakeName() {
        return cakeName;
    }

    public void setCakeName(String cakeName) {
        this.cakeName = cakeName;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "CakeDTO{" + "cakeID=" + cakeID + ", cakeName=" + cakeName + ", image=" + image + ", description=" + description + ", categoryID=" + categoryID + ", status=" + status + ", createDate=" + createDate + ", expDate=" + expDate + ", price=" + price + ", amount=" + amount + '}' + "\n";
    }

    
}
