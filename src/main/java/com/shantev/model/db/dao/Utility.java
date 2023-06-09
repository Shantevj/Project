package com.shantev.model.db.dao;

import com.shantev.exception.DBException;
import com.shantev.exception.Messages;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;

public class Utility {

    public static Connection getConnection() throws DBException {
        Connection con;
        try {
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource) envContext.lookup("jdbc/ConferenceDB");
            con = ds.getConnection();
        } catch (Exception e) {
            throw new DBException(Messages.CANNOT_OBTAIN_CONNECTION, e);
        }
        return con;
    }

    public static void close(AutoCloseable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
