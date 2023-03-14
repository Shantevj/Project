package com.shantev.db.dao;

import com.shantev.exception.DBException;

import java.util.List;

public interface UserDAO {
    List<String> getAllUsers() throws DBException;
}
