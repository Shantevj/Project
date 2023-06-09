package com.shantev.model.db.dao.mysql;

import com.shantev.exception.DBException;
import com.shantev.exception.Messages;
import com.shantev.model.db.dao.EventDAO;
import com.shantev.model.db.dao.Utility;
import com.shantev.model.db.entity.Event;
import com.shantev.model.db.entity.Report;
import com.shantev.useful.Category;
import com.shantev.useful.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            throw new DBException(Messages.CANNOT_OBTAIN_ALL_EVENTS, ex);
        } finally {
            Utility.close(rs);
            Utility.close(stmt);
            Utility.close(con);
        }
    }

    @Override
    public List<Event> getEventsByCategory(Category category) throws DBException {
        return null;
    }

    @Override
    public List<String> getAllEvents() throws DBException {
        return null;
    }

    @Override
    public void addNewEvent(Event event) throws DBException {

    }

    @Override
    public boolean updateEvent(Event event) throws DBException {
        return false;
    }

    @Override
    public void addNewReportToEvent(int id, Report report) {

    }

    @Override
    public boolean deleteEventById(int id) throws DBException {
        return false;
    }
}
