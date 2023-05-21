package com.shantev.model.db.dao.mysql;

import com.shantev.model.db.dao.UserDAO;
import com.shantev.model.db.entity.User;
import com.shantev.exception.DBException;
import com.shantev.exception.Messages;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
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
     public User getUserByLogin(String login, String password) throws DBException {
         User user = null;
         Connection con;
         try {
             Context initContext = new InitialContext();
             Context envContext  = (Context)initContext.lookup("java:/comp/env");
             DataSource ds = (DataSource)envContext.lookup("jdbc/ConferenceDB");
             con = ds.getConnection();
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
         PreparedStatement stmt = null;
         ResultSet rs = null;
         try {
             stmt = con.prepareStatement("SELECT * FROM Users WHERE login=? AND password=?");
             stmt.setString(1, login);
             stmt.setString(2, password);
             rs = stmt.executeQuery();
             while (rs.next()) {
                 String firstName = rs.getString(2);
                 String lastName = rs.getString(3);
                 String logIn = rs.getString(4);
                 String passwd = rs.getString(5);
                 String role = rs.getString(6);
                 user = new User(firstName, lastName, logIn, passwd, role);
             }
             return user;
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
