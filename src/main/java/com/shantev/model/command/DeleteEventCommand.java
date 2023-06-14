package com.shantev.model.command;

import com.shantev.exception.DBException;
import com.shantev.model.command.utility.Command;
import com.shantev.model.db.dao.EventDAO;
import com.shantev.model.db.entity.Event;
import com.shantev.useful.Category;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DeleteEventCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        //find out id of event to be deleted
        int eventId = Integer.parseInt(req.getParameter("eventId"));

        //find out which category events will be returned
        Category category = Category.valueOf(req.getParameter("event_category").toUpperCase());

        //attempt to obtain EventDAO instance
        EventDAO eventDAO = daoFactory.getEventDAO();

        //attempt to delete event with this id from database;
        eventDAO.deleteEventById(eventId);

        //attempt ot obtain new list of events by chosen category
        List<Event> events = eventDAO.getEventsByCategory(category);

        //put events in session
        req.getSession().setAttribute("eventList", events);

        //return page with events
        return "main?command=get_events";
    }
}
