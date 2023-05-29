package com.shantev.model.db.dao.mysql;

public class Constants {
    public static String GET_ALL_USERS = "SELECT * FROM Users";
    public static String ADD_NEW_USER = "INSERT INTO Users VALUES(DEFAULT, ?, ?, ?, ?, 4)";
    public static String GET_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM Users WHERE login=? AND password=?";
    public static String REGISTER_USER_FOR_EVENT = "INSERT INTO Events_has_Users VALUES(?, ?)";
    public static String BLOCK_USER = "UPDATE Users SET role_id=5 WHERE id=?";
    public static String UNBLOCK_USER = "UPDATE Users SET role_id=4 WHERE id=?";
    public static String CHANGE_USER_FIRST_NAME = "UPDATE Users SET first_name=? WHERE id=?";
    public static String CHANGE_USER_LAST_NAME = "UPDATE Users SET last_name=? WHERE id=?";
    public static String CHANGE_USER_PASSWORD = "UPDATE Users SET password=? WHERE id=?";
    public static String SET_ADMIN = "UPDATE Users SET role_id=1 WHERE id=?";
    public static String DELETE_ADMIN = "UPDATE Users SET role_id=4 WHERE id=?";
}
