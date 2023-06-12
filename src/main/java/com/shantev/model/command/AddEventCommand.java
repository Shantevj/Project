package com.shantev.model.command;

import com.shantev.exception.DBException;
import com.shantev.model.command.utility.Command;
import com.shantev.model.db.dao.EventDAO;
import com.shantev.model.db.entity.Event;
import com.shantev.model.utility.Validator;
import com.shantev.useful.Category;
import com.shantev.useful.Status;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

public class AddEventCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        //obtain new event date from request
        Category category = Category.valueOf(req.getParameter("category").toUpperCase());
        String theme = req.getParameter("theme");
        String address = req.getParameter("address");
        String description = req.getParameter("description");
        Timestamp date = Timestamp.valueOf(req.getParameter("datetime").replace("T", " ") + ":00");

        //check whether obtained date is valid
        boolean isEventInfoValid = Validator.validateEventInfo(theme, address, date);

        //if event info is no valid return previous page with warning field
        if(!isEventInfoValid) {
            req.getSession().setAttribute("is_event_info_valid", "not_valid");
            return "WEB-INF/manager/manager_add_new_event_page.jsp";
        }

        //attempt to obtain EventDAO instance and create new event object
        EventDAO eventDAO = daoFactory.getEventDAO();
        Event event = new Event(category,theme, Status.SCHEDULED, address, description, date);

        //attempt to add new event to the database
        eventDAO.addNewEvent(event);
        req.getSession().removeAttribute("is_event_info_valid");

        //return main page
        return "index.jsp";
    }
}
