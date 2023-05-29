package com.shantev.model.db.dao.mysql;

import com.shantev.model.db.dao.UserDAO;
import com.shantev.model.db.entity.User;
import com.shantev.exception.DBException;
import com.shantev.exception.Messages;
import com.shantev.useful.Role;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlUserDAO implements UserDAO {
    public List<String> getAllUsers() throws DBException {
        List<String> userList = new ArrayList<>();
        Connection con = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(Constants.GET_ALL_USERS);
            while (rs.next()) {
                String user = String.format("First name %s, last name %s <a href=\"editUser?userId=%d\">Edit</a>", rs.getString("first_name"), rs.getString("last_name"), rs.getInt(1));
                userList.add(user);
            }
            return userList;
        } catch (SQLException ex) {
            //Add logging
            throw new DBException(Messages.CANNOT_OBTAIN_ALL_USERS, ex);

        } finally {
            close(rs);
            close(stmt);
            close(con);
        }
    }
    @Override
    public void registerUserForEvent(int eventId, int userId) throws DBException {
        Connection con = getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(Constants.REGISTER_USER_FOR_EVENT);
            stmt.setInt(1, eventId);
            stmt.setInt(2, userId);
            stmt.executeUpdate();
        } catch (SQLException ex) {
//            Add logging
            throw new DBException(Messages.CANNOT_REGISTER_USER_FOR_EVENT, ex);
        } finally {
            close(stmt);
            close(con);
        }
    }
    @Override
    public User insertUser(String firstName, String lastName, String login, String password) throws DBException {
        User user = null;
        Connection con = getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(Constants.ADD_NEW_USER, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, login);
            stmt.setString(4, password);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        user = new User(id, firstName, lastName, login, password, Role.USER);
                    }
                }
            }
            return user;
        } catch (SQLException ex) {
            //Add logging
            throw new DBException(Messages.CANNOT_ADD_USER, ex);
        } finally {
            close(stmt);
            close(con);
        }

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
    public User getUserByLoginAndPassword(String login, String password) throws DBException {
        User user = null;
        Connection con = getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(Constants.GET_USER_BY_LOGIN_AND_PASSWORD);
            stmt.setString(1, login);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt(1);
                String firstName = rs.getString(2);
                String lastName = rs.getString(3);
                String logIn = rs.getString(4);
                String passwd = rs.getString(5);
                int role = rs.getInt(6);
                user = new User(id, firstName, lastName, logIn, passwd, Role.getRole(role));
            }
            return user;
        } catch (SQLException ex) {
            //Add logging
            throw new DBException(Messages.CANNOT_OBTAIN_ALL_USERS, ex);

        } finally {
            close(rs);
            close(stmt);
            close(con);
        }
    }
    @Override
    public boolean deleteUser(int id) throws DBException {
        return false;
    }
    @Override
    public boolean blockUser(int id) throws DBException {
        Connection con = getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(Constants.BLOCK_USER);
            stmt.setInt(1, id);
            int rowAffected = stmt.executeUpdate();
            return (rowAffected > 0);
        } catch (SQLException ex) {
            throw new DBException(Messages.CANNOT_BLOCK_USER, ex);
        } finally {
            close(stmt);
            close(con);
        }
    }
    public boolean unblockUser(int id) throws DBException {
        Connection con = getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(Constants.UNBLOCK_USER);
            stmt.setInt(1, id);
            int rowAffected = stmt.executeUpdate();
            return (rowAffected > 0);
        } catch (SQLException ex) {
            throw new DBException(Messages.CANNOT_UNBLOCK_USER, ex);
        } finally {
            close(stmt);
            close(con);
        }
    }
    @Override
    public boolean changeFirstName(String firstName, int id) throws DBException {
        Connection con = getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(Constants.CHANGE_USER_FIRST_NAME);
            stmt.setString(1, firstName);
            stmt.setInt(2, id);
            int rowsAffected = stmt.executeUpdate();
            return (rowsAffected > 0);

        } catch (SQLException ex) {
            throw new DBException(Messages.CANNOT_CHANGE_USER_FIRST_NAME,ex);
        } finally {
            close(stmt);
            close(con);
        }
    }
    @Override
    public boolean changeLastName(String firstName, int id) throws DBException {
        Connection con = getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(Constants.CHANGE_USER_LAST_NAME);
            stmt.setString(1, firstName);
            stmt.setInt(2, id);
            int rowsAffected = stmt.executeUpdate();
            return (rowsAffected > 0);

        } catch (SQLException ex) {
            throw new DBException(Messages.CANNOT_CHANGE_USER_LAST_NAME,ex);
        } finally {
            close(stmt);
            close(con);
        }
    }
    @Override
    public boolean changePassword(String firstName, int id) throws DBException {
        Connection con = getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(Constants.CHANGE_USER_PASSWORD);
            stmt.setString(1, firstName);
            stmt.setInt(2, id);
            int rowsAffected = stmt.executeUpdate();
            return (rowsAffected > 0);

        } catch (SQLException ex) {
            throw new DBException(Messages.CANNOT_CHANGE_USER_PASSWORD,ex);
        } finally {
            close(stmt);
            close(con);
        }
    }
    @Override
    public boolean setAdmin(int id) throws DBException {
        Connection con = getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(Constants.SET_ADMIN);
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return (rowsAffected > 0);
        } catch (SQLException ex) {
            throw new DBException(Messages.CANNOT_SET_ADMIN, ex);
        } finally {
            close(stmt);
            close(con);
        }
    }

    @Override
    public boolean deleteAdmin(int id) throws DBException {
        Connection con = getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(Constants.DELETE_ADMIN);
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return (rowsAffected > 0);
        } catch (SQLException ex) {
            throw new DBException(Messages.CANNOT_DELETE_ADMIN, ex);
        } finally {
            close(stmt);
            close(con);
        }
    }

    private static Connection getConnection() throws DBException {
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
    private static void close(AutoCloseable resource) {
        if (resource != null) {
            try {
                resource.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
