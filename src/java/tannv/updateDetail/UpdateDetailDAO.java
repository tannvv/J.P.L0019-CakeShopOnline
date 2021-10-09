/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tannv.updateDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import tannv.utils.DBHelper;

/**
 *
 * @author TanNV
 */
public class UpdateDetailDAO {

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

    public boolean createUpdateDetail(UpdateDetailDTO upDetail) throws SQLException {
        boolean result = false;
        try {
            // check cakeID is existed or not existed in updateDetail
            boolean checkCakeID = checkCake(upDetail.getCakeID());

            if (checkCakeID == true) { //just change information about userID in updateDetail table
                update(upDetail);
            } else {            // create a new update detail record
                create(upDetail);
            }
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
    //check cakeID is existed in db or not existed in updateDetail table

    private boolean checkCake(String cakeID) throws SQLException {
        boolean result = false;
        String sql = "SELECT userID FROM UpdateDetails \n"
                + "WHERE cakeID = ?";
        try {
            conn = DBHelper.getConnect();
            ptsm = conn.prepareStatement(sql);
            ptsm.setString(1, cakeID);
            rs = ptsm.executeQuery();
            if (rs.next()) {
                result = true;
            }
        } catch (Exception e) {
        } finally {
            cleanConnect();
        }

        return result;
    }

    private boolean update(UpdateDetailDTO upDetail) throws SQLException {
        boolean result = false;
        try {
            String sql = "UPDATE UpdateDetails \n"
                    + "SET userID = ? , updateDate = ? \n"
                    + "WHERE cakeID = ?";
              conn = DBHelper.getConnect();
            ptsm = conn.prepareStatement(sql);
            ptsm.setString(1, upDetail.getUserID());
            ptsm.setDate(2, upDetail.getUpdateDate());
            ptsm.setString(3, upDetail.getCakeID());
            int check = ptsm.executeUpdate();
            if (check == 1) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cleanConnect();
        }

        return result;
    }

    private boolean create(UpdateDetailDTO upDetail) throws SQLException {
        boolean result = false;
        try {
            String sql = "INSERT INTO UpdateDetails(userID,cakeID,updateDate) \n"
                    + "VALUES (?,?,?)";
              conn = DBHelper.getConnect();
            ptsm = conn.prepareStatement(sql);

            ptsm.setString(1, upDetail.getUserID());
            ptsm.setString(2, upDetail.getCakeID());
            ptsm.setDate(3, upDetail.getUpdateDate());       

            int check = ptsm.executeUpdate();
            if (check == 1) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cleanConnect();
        }

        return result;
    }
}
