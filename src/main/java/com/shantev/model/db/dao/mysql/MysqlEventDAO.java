package com.shantev.model.db.dao.mysql;

import com.shantev.exception.DBException;
import com.shantev.model.db.dao.EventDAO;
import com.shantev.model.db.entity.Event;

import java.util.List;

public class MysqlEventDAO implements EventDAO {

    @Override
    public void insertEvent(Event newEvent) throws DBException {

    }

    @Override
    public boolean updateEvent(int id) throws DBException {
        return false;
    }

    @Override
    public List<String> getAllEvents() throws DBException {
        return null;
    }

    @Override
    public Event getEventById(int id) throws DBException {
        return null;
    }

    @Override
    public boolean deleteEvent(int id) throws DBException {
        return false;
    }
}
