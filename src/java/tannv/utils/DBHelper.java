/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tannv.utils;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author TanNV
 */
public class DBHelper {
    public static Connection getConnect() throws NamingException, SQLException{
        Context context = new InitialContext();
        Context tomcatContext = (Context)context.lookup("java:comp/env");
        DataSource ds = (DataSource)tomcatContext.lookup("DBCon");
        return ds.getConnection();
    }
    
   
   
}
