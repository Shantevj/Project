package com.shantev.model.db.dao.mysql;

import com.shantev.model.db.dao.Utility;
import com.shantev.model.db.dao.UserDAO;
import com.shantev.model.db.entity.User;
import com.shantev.exception.DBException;
import com.shantev.exception.Messages;
import com.shantev.useful.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MysqlUserDAO implements UserDAO {
    public Optional<User> getUserById(int id) throws DBException {
        User user = null;
        Connection con = Utility.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(Constants.GET_USER_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setRole(Role.getRole(rs.getInt("role_id")));
            }
            return Optional.ofNullable(user);
        } catch (SQLException ex) {
            throw new DBException(Messages.CANNOT_OBTAIN_USER, ex);
        } finally {
            Utility.close(rs);
            Utility.close(stmt);
            Utility.close(con);
        }
    }

    @Override
    public List<User> getAllUsers() throws DBException {
        List<User> userList = new ArrayList<>();
        Connection con = Utility.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(Constants.GET_ALL_USERS);
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getString("first_name"));
                user.setLastName(rs.getString("last_name"));
                user.setLogin(rs.getString("login"));
                user.setPassword(rs.getString("password"));
                user.setRole(Role.getRole(rs.getInt("role_id")));
                userList.add(user);
            }
            return userList;
        } catch (SQLException ex) {
            //Add logging
            throw new DBException(Messages.CANNOT_OBTAIN_ALL_USERS, ex);
        } finally {
            Utility.close(rs);
            Utility.close(stmt);
            Utility.close(con);
        }
    }

    @Override
    public void addNewUser(User user) throws DBException {
        Connection con = Utility.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(Constants.ADD_NEW_USER, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getLogin());
            stmt.setString(4, user.getPassword());
            stmt.setInt(5, user.getRole().getRoleId());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        user.setId(id);
                    }
                }
            }

        } catch (SQLException ex) {
            throw new DBException(Messages.CANNOT_ADD_USER, ex);
        } finally {
            Utility.close(stmt);
            Utility.close(con);
        }

    }

    @Override
    public void updateUser(User user) throws DBException {
        Connection con = Utility.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(Constants.UPDATE_USER);
            stmt.setString(1, user.getFirstName());
            stmt.setString(2, user.getLastName());
            stmt.setString(3, user.getLogin());
            stmt.setString(4, user.getPassword());
            stmt.setInt(5, user.getRole().getRoleId());
            stmt.setInt(6, user.getId());
            int rowsAffected = stmt.executeUpdate();

        } catch (SQLException ex) {
            throw new DBException(Messages.CANNOT_UPDATE_USER, ex);
        } finally {
            Utility.close(stmt);
            Utility.close(con);
        }
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) throws DBException {
        User user = null;
        Connection con = Utility.getConnection();
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
            Utility.close(rs);
            Utility.close(stmt);
            Utility.close(con);
        }
    }

    @Override
    public void deleteUserById(int id) throws DBException {
        Connection con = Utility.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(Constants.DELETE_USER);
            stmt.setInt(1, id);
            int rowAffected = stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new DBException(Messages.CANNOT_DELETE_USER, ex);
        } finally {
            Utility.close(stmt);
            Utility.close(con);
        }
    }

    @Override
    public void registerUserForEvent(int eventId, int userId) throws DBException {
        Connection con = Utility.getConnection();
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
            Utility.close(stmt);
            Utility.close(con);
        }
    }

}
