package com.shantev.model.command;

import com.shantev.exception.DBException;
import com.shantev.model.command.utility.Command;
import com.shantev.model.db.dao.EventDAO;
import com.shantev.model.db.entity.Event;
import com.shantev.useful.Category;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetEventsCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        //find out what category events user has requested
        Category category = Category.valueOf(req.getParameter("event_category").toUpperCase());

        //attempt to obtain EventDAO instance
        EventDAO eventDAO = daoFactory.getEventDAO();
        List<Event> events = eventDAO.getEventsByCategory(category);

        //put events in session
        req.getSession().setAttribute("eventList", events);

        //return page with events
        return "WEB-INF/shared/events_list.jsp";
    }
}
