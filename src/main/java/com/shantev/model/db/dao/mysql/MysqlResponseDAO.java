package com.shantev.model.db.dao.mysql;

import com.shantev.exception.DBException;
import com.shantev.model.db.dao.ResponseDAO;
import com.shantev.model.db.entity.Response;

import java.util.List;

public class MysqlResponseDAO implements ResponseDAO {

    @Override
    public void insertResponse(Response newResponse) throws DBException {

    }

    @Override
    public boolean updateResponse(int id) throws DBException {
        return false;
    }

    @Override
    public List<String> getAllResponses() throws DBException {
        return null;
    }

    @Override
    public Response getResponseById(int id) throws DBException {
        return null;
    }

    @Override
    public boolean deleteResponse(int id) throws DBException {
        return false;
    }
}
