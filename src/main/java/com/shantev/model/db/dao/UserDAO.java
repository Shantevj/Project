package com.shantev.model.db.dao;

import com.shantev.model.db.entity.User;
import com.shantev.exception.DBException;

import java.util.List;
import java.util.Optional;

public interface UserDAO {
    Optional<User> getUserById(int id) throws DBException;
    List<User> getAllUsers() throws DBException;
    void addNewUser(User user) throws DBException;
    void updateUser(User user) throws DBException;
    void deleteUserById(int id) throws DBException;
    public User getUserByLoginAndPassword(String login, String password) throws DBException;
    public void registerUserForEvent(int eventId, int userId) throws DBException;
}
