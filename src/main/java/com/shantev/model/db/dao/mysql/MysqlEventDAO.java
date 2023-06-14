package com.shantev.model.db.dao.mysql;

import com.shantev.exception.DBException;
import com.shantev.exception.Messages;
import com.shantev.model.db.dao.EventDAO;
import com.shantev.model.db.dao.Utility;
import com.shantev.model.db.entity.Event;
import com.shantev.model.db.entity.Report;
import com.shantev.useful.Category;
import com.shantev.useful.Status;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MysqlEventDAO implements EventDAO {


    @Override
    public Optional<Event> getEventById(int id) throws DBException {
        Event event = null;
        Connection con = Utility.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(Constants.GET_EVENT_BY_ID);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                event = new Event();
                event.setId(rs.getInt("id"));
                event.setTheme(rs.getString("theme"));
                event.setCategory(Category.valueOf(rs.getString("category").toUpperCase()));
                event.setDate(rs.getTimestamp("date"));
                event.setAddress(rs.getString("address"));
                event.setDescription(rs.getString("description"));
                event.setStatus(Status.valueOf(rs.getString("status").toUpperCase()));
            }
            return Optional.ofNullable(event);
        } catch (SQLException ex) {
            throw new DBException(Messages.CANNOT_OBTAIN_EVENTS_BY_ID, ex);
        } finally {
            Utility.close(rs);
            Utility.close(stmt);
            Utility.close(con);
        }
    }

    @Override
    public List<Event> getEventsByCategory(Category category) throws DBException {
        List<Event> eventList = new ArrayList<>();
        Connection con = Utility.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement(Constants.GET_EVENTS_BY_CATEGORY);
            stmt.setInt(1, category.getCategoryId());
            rs = stmt.executeQuery();
            while (rs.next()) {
                Event event = new Event();
                event.setId(rs.getInt("id"));
                event.setTheme(rs.getString("theme"));
                event.setCategory(Category.valueOf(rs.getString("category").toUpperCase()));
                event.setDate(rs.getTimestamp("date"));
                event.setAddress(rs.getString("address"));
                event.setDescription(rs.getString("description"));
                event.setStatus(Status.valueOf(rs.getString("status").toUpperCase()));
                eventList.add(event);
            }
            return eventList;
        } catch (SQLException ex) {
            throw new DBException(Messages.CANNOT_OBTAIN_EVENTS_BY_CATEGORY, ex);
        } finally {
            Utility.close(rs);
            Utility.close(stmt);
            Utility.close(con);
        }
    }

    @Override
    public List<Event> getAllEvents() throws DBException {
        List<Event> eventList = new ArrayList<>();
        Connection con = Utility.getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(Constants.GET_ALL_EVENTS);
            while (rs.next()) {
                Event event = new Event();
                event.setId(rs.getInt("id"));
                event.setTheme(rs.getString("theme"));
                event.setCategory(Category.valueOf(rs.getString("category").toUpperCase()));
                event.setDate(rs.getTimestamp("date"));
                event.setAddress(rs.getString("address"));
                event.setDescription(rs.getString("description"));
                event.setStatus(Status.valueOf(rs.getString("status").toUpperCase()));
                eventList.add(event);
            }
            return eventList;
        } catch (SQLException ex) {
            throw new DBException(Messages.CANNOT_OBTAIN_ALL_EVENTS, ex);
        } finally {
            Utility.close(rs);
            Utility.close(stmt);
            Utility.close(con);
        }
    }

    @Override
    public void addNewEvent(Event event) throws DBException {
        Connection con = Utility.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(Constants.ADD_NEW_EVENT, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, event.getTheme());
            stmt.setInt(2, event.getCategory().getCategoryId());
            stmt.setInt(3, event.getStatus().getStatusId());
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        event.setId(id);
                        addEventDetails(event);
                    }
                }
            }

        } catch (SQLException ex) {
            throw new DBException(Messages.CANNOT_ADD_EVENT, ex);
        } finally {
            Utility.close(stmt);
            Utility.close(con);
        }
    }

    private void addEventDetails(Event event) throws DBException {
        Connection con = Utility.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(Constants.ADD_EVENT_DETAIL);
            stmt.setInt(1, event.getId());
            stmt.setTimestamp(2, event.getDate());
            stmt.setString(3, event.getAddress());
            stmt.setString(4, event.getDescription());
            int rowsAffected = stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new DBException(Messages.CANNOT_ADD_EVENT_DETAIL, ex);
        } finally {
            Utility.close(stmt);
            Utility.close(con);
        }
    }

    @Override
    public void updateEvent(Event event) throws DBException {
        Connection con = Utility.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(Constants.UPDATE_EVENT);
            stmt.setString(1, event.getTheme());
            stmt.setInt(2, event.getStatus().getStatusId());
            stmt.setInt(3, event.getId());
            int rowsAffected = stmt.executeUpdate();
            updateEventDetails(event);
        } catch (SQLException ex) {
            throw new DBException(Messages.CANNOT_UPDATE_EVENT, ex);
        } finally {
            Utility.close(stmt);
            Utility.close(con);
        }
    }

    private void updateEventDetails(Event event) throws DBException {
        Connection con = Utility.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(Constants.UPDATE_EVENT_DETAIL);
            stmt.setTimestamp(1, event.getDate());
            stmt.setString(2, event.getAddress());
            stmt.setString(3, event.getDescription());
            stmt.setInt(4, event.getId());
            int rowsAffected = stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new DBException(Messages.CANNOT_UPDATE_EVENT_DETAIL, ex);
        } finally {
            Utility.close(stmt);
            Utility.close(con);
        }
    }

    @Override
    public void addNewReportToEvent(int id, Report report) throws DBException {

    }

    @Override
    public void deleteEventById(int id) throws DBException {
        Connection con = Utility.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(Constants.DELETE_EVENT);
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
        } catch (SQLException ex) {
            throw new DBException(Messages.CANNOT_DELETE_EVENT, ex);
        }
    }
}
