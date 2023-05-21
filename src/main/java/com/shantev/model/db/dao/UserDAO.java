package com.shantev.model.db.dao;

import com.shantev.model.db.entity.User;
import com.shantev.exception.DBException;

import java.util.List;

public interface UserDAO {

    void insertUser(User newUser) throws DBException;
    boolean updateUser(int id) throws DBException;
    List<String> getAllUsers() throws DBException;
    User getUserById(int id) throws DBException;
    User getUserByLogin(String login, String password) throws DBException;
    boolean deleteUser(int id) throws DBException;
}
