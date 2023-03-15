package com.shantev.model.db.dao.mysql;

import com.shantev.model.db.dao.UserDAO;
import com.shantev.model.db.entity.User;
import com.shantev.exception.DBException;
import com.shantev.exception.Messages;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class MysqlUserDAO implements UserDAO {
    public List<String> getAllUsers() throws DBException {
        List<String> userList = new ArrayList<>();
        Connection con;
        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("jdbc/ConferenceDB");
            con = ds.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from Users");
            while (rs.next()) {
                String user = String.format("First name %s, last name %s <a href=\"editUser?userId=%d\">Edit</a>", rs.getString("first_name"), rs.getString("last_name"), rs.getInt(1));
                userList.add(user);
            }
            return userList;
        } catch (SQLException ex) {
            //Add logging
            throw new DBException(Messages.CANNOT_OBTAIN_ALL_USERS, ex);

        }
        finally {
            close(rs);
            close(stmt);
            close(con);
        }
    }

    @Override
    public void insertUser(User newUser) throws DBException {

    }

    @Override
    public boolean updateUser(int id) throws DBException {
        return false;
    }

    @Override
    public User getUserById(int id) throws DBException {
        return null;
    }

    @Override
    public boolean deleteUser(int id) throws DBException {
        return false;
    }

    private static void close(AutoCloseable resource) {
        if(resource != null) {
            try {
                resource.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
