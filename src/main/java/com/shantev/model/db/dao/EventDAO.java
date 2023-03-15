package com.shantev.model.db.dao;

import com.shantev.exception.DBException;
import com.shantev.model.db.entity.Event;

import java.util.List;

public interface EventDAO {
    void insertEvent(Event newEvent) throws DBException;
    boolean updateEvent(int id) throws DBException;
    List<String> getAllEvents() throws DBException;
    Event getEventById(int id) throws DBException;
    boolean deleteEvent(int id) throws DBException;
}