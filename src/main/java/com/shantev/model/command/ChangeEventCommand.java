package com.shantev.model.command;

import com.shantev.exception.DBException;
import com.shantev.model.command.utility.Command;
import com.shantev.model.db.dao.EventDAO;
import com.shantev.model.db.entity.Event;
import com.shantev.model.utility.Validator;
import com.shantev.useful.Status;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

public class ChangeEventCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        //find out id event to be changed
        int eventId = Integer.parseInt(req.getParameter("eventId"));

        //attempt to obtain EventDAO instance
        EventDAO eventDAO = daoFactory.getEventDAO();

        //from database obtain old event by id
        Event event = eventDAO.getEventById(eventId).orElseThrow();

        //get new event info from request
        Status status = Status.valueOf(req.getParameter("status").replace(" ", "_").toUpperCase());
        String theme = req.getParameter("theme");
        String description = req.getParameter("description");
        String address = req.getParameter("address");
        Timestamp date = Timestamp.valueOf(req.getParameter("datetime").replace("T", " ") + ":00");

        //check if new info is valid
        boolean isNewEventInfo = Validator.validateEventInfo(theme, address, date);

        //if new info is not valid return previous page with warning field
        if(!isNewEventInfo) {
            req.getSession().setAttribute("is_new_event_info_valid", "not_valid");
            return String.format("main?command=show_change_event_info_page&eventId=%s&event_category=%s", event.getId(), event.getCategory());
        }

        //update event object information
        event.setStatus(status);
        event.setTheme(theme);
        event.setDescription(description);
        event.setAddress(address);
        event.setDate(date);

        //attempt to update event information in database
        eventDAO.updateEvent(event);

        //remove unnecessary attribute from session
        req.getSession().removeAttribute("is_new_event_information_valid");

        return "index.jsp";
    }
}
