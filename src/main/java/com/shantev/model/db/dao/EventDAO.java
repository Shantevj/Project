package com.shantev.model.db.dao;

import com.shantev.exception.DBException;
import com.shantev.model.db.entity.Event;
import com.shantev.model.db.entity.Report;
import com.shantev.useful.Category;

import java.util.List;
import java.util.Optional;

public interface EventDAO {
    Optional<Event> getEventById(int id) throws DBException;
    List<Event> getEventsByCategory(Category category) throws DBException;
    List<String> getAllEvents() throws DBException;
    void addNewEvent(Event event) throws DBException;
    boolean updateEvent(Event event) throws DBException;
    void addNewReportToEvent(int id, Report report);
    boolean deleteEventById(int id) throws DBException;
}