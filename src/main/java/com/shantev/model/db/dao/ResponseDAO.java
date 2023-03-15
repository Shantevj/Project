package com.shantev.model.db.dao;

import com.shantev.exception.DBException;
import com.shantev.model.db.entity.Response;

import java.util.List;

public interface ResponseDAO {
    void insertResponse(Response newResponse) throws DBException;
    boolean updateResponse(int id) throws DBException;
    List<String> getAllResponses() throws DBException;
    Response getResponseById(int id) throws DBException;
    boolean deleteResponse(int id) throws DBException;
}