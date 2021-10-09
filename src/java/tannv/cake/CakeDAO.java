/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tannv.cake;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import tannv.utils.DBHelper;

/**
 *
 * @author TanNV
 */
public class CakeDAO {

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

    public boolean createCake(CakeDTO cake) throws SQLException {
        boolean result = false;
        try {

            String sql = "INSERT INTO Cakes(cakeID,cakeName,image,description,price,amount,createDate,expDate,categoryID,status) \n"
                    + "VALUES (?,?,?,?,?,?,?,?,?,?)";
            conn = DBHelper.getConnect();
            ptsm = conn.prepareStatement(sql);
            ptsm.setString(1, cake.getCakeID());
            ptsm.setString(2, cake.getCakeName());
            ptsm.setString(3, cake.getImage());
            ptsm.setString(4, cake.getDescription());
            ptsm.setInt(5, cake.getPrice());
            ptsm.setInt(6, cake.getAmount());
            ptsm.setDate(7, cake.getCreateDate());
            ptsm.setDate(8, cake.getExpDate());
            ptsm.setString(9, cake.getCategoryID());
            ptsm.setBoolean(10, cake.isStatus());

            int check = ptsm.executeUpdate();
            if (check == 1) {
                result = true;
            }
        } catch (Exception e) {

        } finally {
            cleanConnect();
        }

        return result;
    }

    public boolean updateCake(CakeDTO cake) throws SQLException {
        boolean result = false;
        try {

            String sql = "UPDATE Cakes \n"
                    + "SET cakeName=?,image=?,description=?,price=?,amount=?,createDate=?,expDate=?,categoryID=?,status=? \n"
                    + "WHERE cakeID=?";
            conn = DBHelper.getConnect();
            ptsm = conn.prepareStatement(sql);

            ptsm.setString(1, cake.getCakeName());
            ptsm.setString(2, cake.getImage());
            ptsm.setString(3, cake.getDescription());
            ptsm.setInt(4, cake.getPrice());
            ptsm.setInt(5, cake.getAmount());
            ptsm.setDate(6, cake.getCreateDate());
            ptsm.setDate(7, cake.getExpDate());
            ptsm.setString(8, cake.getCategoryID());
            ptsm.setBoolean(9, cake.isStatus());
            ptsm.setString(10, cake.getCakeID());

            int check = ptsm.executeUpdate();
            if (check == 1) {
                result = true;
            }
        } catch (Exception e) {

        } finally {
            cleanConnect();
        }

        return result;
    }

    public ArrayList<CakeDTO> getPageCake(int indexPage) throws SQLException {
        ArrayList<CakeDTO> list = null;
        try {
            String sql = "SELECT cakeID,cakeName,image,description,price,amount,createDate,expDate,categoryID,status \n"
                    + "FROM Cakes WHERE amount > 0 AND status = 'true' \n"
                    + "ORDER BY expDate \n"
                    + "OFFSET ? ROWS FETCH NEXT 20 ROWS ONLY";
            conn = DBHelper.getConnect();
            ptsm = conn.prepareStatement(sql);
            ptsm.setInt(1, ((indexPage - 1) * 20));
            rs = ptsm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                String cakeID = rs.getString("cakeID");
                String cakeName = rs.getString("cakeName");
                String image = rs.getString("image");
                String description = rs.getString("description");
                int price = rs.getInt("price");
                int amount = rs.getInt("amount");
                Date createDate = rs.getDate("createDate");
                Date expDate = rs.getDate("expDate");
                String categoryID = rs.getString("categoryID");
                boolean status = rs.getBoolean("status");
                CakeDTO cake = new CakeDTO(cakeID, cakeName, image, description, categoryID, status, createDate, expDate, price, amount);
                list.add(cake);
            }
        } catch (Exception e) {
        } finally {
            cleanConnect();
        }

        return list;
    }

    public CakeDTO getCakeByID(String id) throws SQLException {
        CakeDTO cake = null;
        try {
            String sql = "SELECT cakeName,image,description,price,amount,createDate,expDate,categoryID,status \n"
                    + "FROM Cakes \n"
                    + "WHERE cakeID = ?";

            conn = DBHelper.getConnect();
            ptsm = conn.prepareStatement(sql);
            ptsm.setString(1, id);
            rs = ptsm.executeQuery();
            if (rs.next()) {

                String cakeName = rs.getString("cakeName");
                String image = rs.getString("image");
                String description = rs.getString("description");
                int price = rs.getInt("price");
                int amount = rs.getInt("amount");
                Date createDate = rs.getDate("createDate");
                Date expDate = rs.getDate("expDate");
                String categoryID = rs.getString("categoryID");
                boolean status = rs.getBoolean("status");
                cake = new CakeDTO(id, cakeName, image, description, categoryID, status, createDate, expDate, price, amount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cleanConnect();
        }

        return cake;
    }

    public ArrayList<CakeDTO> getPageCakeAdmin(int indexPage) throws SQLException {
        ArrayList<CakeDTO> list = null;
        try {
            String sql = "SELECT cakeID,cakeName,image,description,price,amount,createDate,expDate,categoryID,status \n"
                    + "FROM Cakes \n"
                    + "ORDER BY expDate \n"
                    + "OFFSET ? ROWS FETCH NEXT 20 ROWS ONLY";
            conn = DBHelper.getConnect();
            ptsm = conn.prepareStatement(sql);
            ptsm.setInt(1, ((indexPage - 1) * 20));
            rs = ptsm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                String cakeID = rs.getString("cakeID");
                String cakeName = rs.getString("cakeName");
                String image = rs.getString("image");
                String description = rs.getString("description");
                int price = rs.getInt("price");
                int amount = rs.getInt("amount");
                Date createDate = rs.getDate("createDate");
                Date expDate = rs.getDate("expDate");
                String categoryID = rs.getString("categoryID");
                boolean status = rs.getBoolean("status");
                CakeDTO cake = new CakeDTO(cakeID, cakeName, image, description, categoryID, status, createDate, expDate, price, amount);
                list.add(cake);
            }
        } catch (Exception e) {
        } finally {
            cleanConnect();
        }

        return list;
    }

    public ArrayList<CakeDTO> getPriceRange(int min, int max, int indexPage) throws SQLException {
        ArrayList<CakeDTO> list = null;
        try {
            String sql = "SELECT cakeID,cakeName,image,description,price,amount,createDate,expDate,categoryID,status \n"
                    + "FROM Cakes WHERE amount > 0 AND status = 'true' AND price >= ? AND price <= ?\n"
                    + "ORDER BY expDate \n"
                    + "OFFSET ? ROWS FETCH NEXT 20 ROWS ONLY";
            conn = DBHelper.getConnect();
            ptsm = conn.prepareStatement(sql);
            ptsm.setInt(1, min);
            ptsm.setInt(2, max);
            ptsm.setInt(3, ((indexPage - 1) * 20));
            rs = ptsm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                String cakeID = rs.getString("cakeID");
                String cakeName = rs.getString("cakeName");
                String image = rs.getString("image");
                String description = rs.getString("description");
                int price = rs.getInt("price");
                int amount = rs.getInt("amount");
                Date createDate = rs.getDate("createDate");
                Date expDate = rs.getDate("expDate");
                String categoryID = rs.getString("categoryID");
                boolean status = rs.getBoolean("status");
                CakeDTO cake = new CakeDTO(cakeID, cakeName, image, description, categoryID, status, createDate, expDate, price, amount);
                list.add(cake);
            }
        } catch (Exception e) {
        } finally {
            cleanConnect();
        }

        return list;
    }

    public ArrayList<CakeDTO> getByCategory(String category, int indexPage) throws SQLException {
        ArrayList<CakeDTO> list = null;
        try {
            String sql = "SELECT cakeID,cakeName,image,description,price,amount,createDate,expDate,categoryID,status \n"
                    + "FROM Cakes WHERE amount > 0 AND status = 'true' AND categoryID = ?\n"
                    + "ORDER BY expDate \n"
                    + "OFFSET ? ROWS FETCH NEXT 20 ROWS ONLY";
            conn = DBHelper.getConnect();
            ptsm = conn.prepareStatement(sql);
            ptsm.setString(1, category);
            ptsm.setInt(2, ((indexPage - 1) * 20));
            rs = ptsm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                String cakeID = rs.getString("cakeID");
                String cakeName = rs.getString("cakeName");
                String image = rs.getString("image");
                String description = rs.getString("description");
                int price = rs.getInt("price");
                int amount = rs.getInt("amount");
                Date createDate = rs.getDate("createDate");
                Date expDate = rs.getDate("expDate");
                String categoryID = rs.getString("categoryID");
                boolean status = rs.getBoolean("status");
                CakeDTO cake = new CakeDTO(cakeID, cakeName, image, description, categoryID, status, createDate, expDate, price, amount);
                list.add(cake);
            }
        } catch (Exception e) {
        } finally {
            cleanConnect();
        }

        return list;
    }

    public ArrayList<CakeDTO> getByName(String name, int indexPage) throws SQLException {
        ArrayList<CakeDTO> list = null;
        try {
            String sql = "SELECT cakeID,cakeName,image,description,price,amount,createDate,expDate,categoryID,status \n"
                    + "FROM Cakes WHERE amount > 0 AND status = 'true' AND cakeName LIKE ?\n"
                    + "ORDER BY expDate \n"
                    + "OFFSET ? ROWS FETCH NEXT 20 ROWS ONLY";
            conn = DBHelper.getConnect();
            ptsm = conn.prepareStatement(sql);
            ptsm.setString(1, "%" + name + "%");
            ptsm.setInt(2, ((indexPage - 1) * 20));
            rs = ptsm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                String cakeID = rs.getString("cakeID");
                String cakeName = rs.getString("cakeName");
                String image = rs.getString("image");
                String description = rs.getString("description");
                int price = rs.getInt("price");
                int amount = rs.getInt("amount");
                Date createDate = rs.getDate("createDate");
                Date expDate = rs.getDate("expDate");
                String categoryID = rs.getString("categoryID");
                boolean status = rs.getBoolean("status");
                CakeDTO cake = new CakeDTO(cakeID, cakeName, image, description, categoryID, status, createDate, expDate, price, amount);
                list.add(cake);
            }
        } catch (Exception e) {
        } finally {
            cleanConnect();
        }

        return list;
    }

    public boolean validQuantity(String cakeID, int quantity) throws SQLException {
        boolean result = false;
        try {

            String sql = "SELECT amount FROM Cakes \n"
                    + "WHERE cakeID= ?";
            conn = DBHelper.getConnect();
            ptsm = conn.prepareStatement(sql);

            ptsm.setString(1, cakeID);

            rs = ptsm.executeQuery();
            rs.next();
            int amount = rs.getInt("amount"); // amount of cake existed in system
            if ((amount - quantity) >= 0) {
                result = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cleanConnect();
        }

        return result;
    }

    public int getNumberPage() throws SQLException {
        int numberPage = 0;
        try {

            String sql = "select count(cakeID) as numberCake\n"
                    + "from Cakes";
            conn = DBHelper.getConnect();
            ptsm = conn.prepareStatement(sql);

            rs = ptsm.executeQuery();
            rs.next();
            int numberCakes = rs.getInt("numberCake"); // amount of cake existed in system
            numberPage = numberCakes / 20;
            if (numberCakes % 20 != 0) {
                numberPage = numberPage + 1 ;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cleanConnect();
        }

        return numberPage;
    }

}
