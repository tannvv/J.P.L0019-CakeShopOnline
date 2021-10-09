/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tannv.user;

import java.io.Serializable;

/**
 *
 * @author TanNV
 */
public class UserDTO implements Serializable{
    private String userID, password, fullName;
    private int role;

    public UserDTO() {
    }

    public UserDTO(String userID, String password, String fullName, int role) {
        this.userID = userID;
        this.password = password;
        this.fullName = fullName;
        this.role = role;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
    
    

    @Override
    public String toString() {
        return "Tbl_User_DTO{" + "userID=" + userID + ", password=" + password + ", fullName=" + fullName + ", role=" + role + '}';
    }
    
}
