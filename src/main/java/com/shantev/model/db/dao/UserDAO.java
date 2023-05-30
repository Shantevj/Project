package com.shantev.model.db.dao;

import com.shantev.model.db.entity.User;
import com.shantev.exception.DBException;

import java.util.List;

public interface UserDAO {

    void registerUserForEvent(int eventID, int userId) throws DBException;
    User insertUser(String firstName, String lastName, String login, String password) throws DBException;
    boolean updateUser(int id) throws DBException;
    List<String> getAllUsers() throws DBException;
    User getUserById(int id) throws DBException;
    User getUserByLoginAndPassword(String login, String password) throws DBException;
    boolean blockUser(int id) throws DBException;
    boolean unblockUser(int id) throws DBException;
    boolean changeFirstName(String firstName, int id) throws DBException;
    boolean changeLastName(String lastName, int id) throws DBException;
    boolean changePassword(String password, int id) throws DBException;
    boolean setAdmin(int id) throws DBException;
    boolean deleteAdmin(int id) throws DBException;
    boolean setManager(int id) throws DBException;
    boolean deleteManager(int id) throws DBException;
    boolean setSpeaker(int id) throws DBException;
    boolean deleteSpeaker(int id) throws DBException;
    boolean deleteUser(int id) throws DBException;

}
