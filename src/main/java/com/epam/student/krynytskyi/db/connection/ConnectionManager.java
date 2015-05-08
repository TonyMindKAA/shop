package com.epam.student.krynytskyi.db.connection;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class ConnectionManager {
    private static final Logger log = Logger.getLogger(ConnectionManager.class);
    private static DataSource dataSource;

    private static void init() throws NamingException {
       try {
           InitialContext initContext = new InitialContext();
           Context envContext = (Context) initContext.lookup("java:/comp/env");
            dataSource = (DataSource) (envContext.lookup("jdbc/krynytskyiSop"));
        } catch (NamingException ex) {
            log.error("Cannot init pool of connection", ex);
            throw ex;
        }
    }

    public static synchronized Connection getConnection() throws IOException, SQLException, PropertyVetoException {
    	try{
            if(dataSource == null)
                init();
            Connection connection = dataSource.getConnection();
            return connection;
        }catch(Exception e){
            log.error("Cannot create connection", e);
            throw new IOException(e);
        }
    }
}
