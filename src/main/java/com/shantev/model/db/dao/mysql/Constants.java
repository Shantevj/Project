package com.shantev.model.db.dao.mysql;

public class Constants {
    public static String GET_USER_BY_ID = "SELECT * FROM Users WHERE id=?";
    public static String GET_ALL_USERS = "SELECT * FROM Users";
    public static String ADD_NEW_USER = "INSERT INTO Users VALUES(DEFAULT, ?, ?, ?, ?, ?)";
    public static String UPDATE_USER = "UPDATE Users " +
            "SET first_name=?, last_name=?, login=?, password=?, role_id=? " +
            "WHERE id=?";
    public static String DELETE_USER = "DELETE FROM Users WHERE id=?";
    public static String GET_USER_BY_LOGIN_AND_PASSWORD = "SElECT * FROM Users " +
            "WHERE login=? AND password=?";
}
