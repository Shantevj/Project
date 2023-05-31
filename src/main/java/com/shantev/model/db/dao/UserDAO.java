package com.shantev.model.db.dao;

import com.shantev.model.db.entity.User;
import com.shantev.exception.DBException;

import java.util.List;

public interface UserDAO {
    User getUserById(int id) throws DBException;
    List<User> getAllUsers() throws DBException;
    void addNewUser(User user) throws DBException;
    void updateUser(User user) throws DBException;
    void deleteUser(User user) throws DBException;
    public User getUserByLoginAndPassword(String login, String password) throws DBException;
}
