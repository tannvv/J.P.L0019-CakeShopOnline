/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tannv.updateDetail;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author TanNV
 */
public class UpdateDetailDTO implements Serializable{
    private String userID, cakeID;
    private Date updateDate;

    public UpdateDetailDTO() {
    }

    
    public UpdateDetailDTO(String userID, String cakeID, Date updateDate) {
        this.userID = userID;
        this.cakeID = cakeID;
        this.updateDate = updateDate;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getCakeID() {
        return cakeID;
    }

    public void setCakeID(String cakeID) {
        this.cakeID = cakeID;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    
    
}
