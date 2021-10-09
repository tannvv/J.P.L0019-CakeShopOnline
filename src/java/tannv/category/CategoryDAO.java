/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tannv.category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import tannv.utils.DBHelper;

/**
 *
 * @author TanNV
 */
public class CategoryDAO {
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
    
    public ArrayList<CategoryDTO> getAllCategories() throws SQLException{
        ArrayList<CategoryDTO> list = null;
        try {
            String sql = "SELECT categoryID,catName FROM Categories";
            conn = DBHelper.getConnect();
            ptsm = conn.prepareStatement(sql);
            rs = ptsm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {   
                String id = rs.getString("categoryID");
                String name = rs.getString("catName");
                CategoryDTO cate = new CategoryDTO(id,name);
                list.add(cate);
            }
        } catch (Exception e) {
        }finally{
            cleanConnect();
        }
        
        return list;
    }
}
