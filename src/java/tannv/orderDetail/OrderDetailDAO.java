/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tannv.orderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import tannv.cake.CakeDAO;
import tannv.cake.CakeDTO;
import tannv.utils.DBHelper;

/**
 *
 * @author TanNV
 */
public class OrderDetailDAO {
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

    public boolean createOrderDetail(OrderDetailDTO orderDetail) throws SQLException {
        boolean result = false;
        try {
            String sql = "INSERT INTO OrderDetails(orderID, cakeID, quantity) VALUES (?,?,?)";
            conn = DBHelper.getConnect();
            ptsm = conn.prepareStatement(sql);
            ptsm.setInt(1, orderDetail.getOrderID());
            ptsm.setString(2, orderDetail.getCakeID().getCakeID());
            ptsm.setInt(3, orderDetail.getQuantity());
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
    
     public ArrayList<OrderDetailDTO> getOrderDetailByOrderID(int orderID) throws SQLException {
         ArrayList<OrderDetailDTO> list = null;
         CakeDAO cakeDAO = new CakeDAO();
        try {
            String sql = "SELECT cakeID, quantity FROM OrderDetails \n"
                    + "WHERE orderID = ?";
            conn = DBHelper.getConnect();
            ptsm = conn.prepareStatement(sql);
            ptsm.setInt(1, orderID);
            rs = ptsm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) { 
                CakeDTO cake = cakeDAO.getCakeByID(rs.getString("cakeID"));
                OrderDetailDTO orderDetail = new OrderDetailDTO(cake, rs.getInt("quantity"),orderID);
                list.add(orderDetail);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            cleanConnect();
        }

        return list;
    }
    
    public boolean validListCartToPay(ArrayList<OrderDetailDTO> listCart){
        CakeDAO dao = new CakeDAO();
        try {
            int size = listCart.size();
            for (int i = 0; i < size; i++) {
                // check quantity for all cake
                boolean check = dao.validQuantity(listCart.get(i).getCakeID().getCakeID(),listCart.get(i).getQuantity() );
                if (check == false) {
                    return false;
                }
            }
        } catch (Exception e) {
        }
        return true;
    }
    
    public boolean updateAmountCake(OrderDetailDTO orderDetail){
        boolean result = false;
        CakeDAO cakeDAO = new CakeDAO();
        try {
            CakeDTO cake = cakeDAO.getCakeByID(orderDetail.getCakeID().getCakeID());
            int newAmount = cake.getAmount() - orderDetail.getQuantity();
            String sql = "UPDATE Cakes \n"
                    + "SET amount = ? \n"
                    + "WHERE cakeID = ?";
            conn = DBHelper.getConnect();
            ptsm = conn.prepareStatement(sql);
            ptsm.setInt(1, newAmount);
            ptsm.setString(2, orderDetail.getCakeID().getCakeID());
            if (ptsm.executeUpdate() == 1) {
                result = true;
            }        
        } catch (Exception e) {
        }
        return result;
    }
    
    public boolean storeListCart(ArrayList<OrderDetailDTO> listCart){
        boolean result = false;
        
        try {
            // store list cart to database
            int size = listCart.size();
            for (int i = 0; i < size; i++) {
                createOrderDetail(listCart.get(i));
            }
            // update amount for cakes
            for (int i = 0; i < 10; i++) {
                updateAmountCake(listCart.get(i));
            }
            result = true;
        } catch (Exception e) {
        }
        
        return result;
    }
    
    
}
