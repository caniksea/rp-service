/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.rp.caniksea.db;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author caniksea
 */
public class DBConnection {
    
    private static Context initCtx;
    
    static {
        try {
            initCtx = new InitialContext();
            //envCtx = (Context)initCtx.lookup("java:comp/env");
        } catch (NamingException ex) {
            //System.out.println("Error initailizing the context environment...");
            ex.printStackTrace();
        }
    }
    
    public static Connection getMySQLConnection(){ //
        Connection con = null;
        try {
            DataSource ds = (DataSource) initCtx.lookup("java:/RemitpalsDS"); //java:jboss/datasources/RemitpalsDS
            con = ds.getConnection();
        } catch (SQLException | NamingException ce) {
            System.out.println("WARNING::Error occured trying to connect to REMITPALS (MySQL) database " + ce.getMessage());
            ce.printStackTrace();
        }
        return con;
    }
    
}
