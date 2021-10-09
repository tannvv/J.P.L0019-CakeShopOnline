/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tannv.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import tannv.utils.DBHelper;

/**
 *
 * @author TanNV
 */
public class UserDAO {
    private Connection conn = null;
    private PreparedStatement ptsm = null;
    private ResultSet rs = null;
    
    private void cleanConnect() throws SQLException{
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
    
    public UserDTO checkLogin(String userID, String password) throws SQLException{
        UserDTO user = null;
        try{
            String sql = "SELECT userID, password, fullName, role FROM Users \n"
                    + "WHERE userID = ? AND password = ?";
            conn = DBHelper.getConnect();
            ptsm = conn.prepareStatement(sql);
            ptsm.setString(1, userID);
            ptsm.setString(2, password);
            rs = ptsm.executeQuery();
            if (rs.next()) {
                user = new UserDTO(rs.getString("userID"),rs.getString("password"), rs.getString("fullName"), rs.getInt("role"));
            }
        }catch(Exception ex){
            
        }finally{
            cleanConnect();
        }
        return user;
    }
    
}
