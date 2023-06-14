package com.shantev.model.command;

import com.shantev.exception.DBException;
import com.shantev.model.command.utility.Command;
import com.shantev.model.db.dao.EventDAO;
import com.shantev.model.db.entity.Event;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowChangeEventInfoPage extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        //find out id event to be changed
        int eventId = Integer.parseInt(req.getParameter("eventId"));

        //attempt to obtain EventDAO instance
        EventDAO eventDAO = daoFactory.getEventDAO();

        //from database by id obtain old event information
        Event event = eventDAO.getEventById(eventId).orElseThrow();

        //put event to the request
        req.getSession().setAttribute("event_to_be_changed", event);
        return "WEB-INF/manager/manager_change_event_info_page.jsp";
    }
}
