/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tannv.utils;


/**
 *
 * @author TanNV
 */
public class Convert {
    public static java.sql.Date utilDateToSqlDate(java.util.Date utilDate){
        java.sql.Date sqlDate = null;
        if (utilDate != null) {
            sqlDate = new java.sql.Date(utilDate.getTime());
        }
        return sqlDate;
    }
    
    public static java.util.Date sqlDateToUtilDate(java.sql.Date sqlDate){
        java.util.Date utilDate = null;
        if (sqlDate != null) {
            utilDate = new java.util.Date(sqlDate.getTime());
        }
        return utilDate;
    }
    
}
