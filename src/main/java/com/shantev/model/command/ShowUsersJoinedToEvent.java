package com.shantev.model.command;

import com.shantev.exception.DBException;
import com.shantev.model.command.utility.Command;
import com.shantev.model.db.dao.EventDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowUsersJoinedToEvent extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        //find out id of event, to get all users joined to it
        int eventId = Integer.parseInt(req.getParameter("eventId"));

        //attempt to obtain EventDAO instance
        EventDAO eventDAO = daoFactory.getEventDAO();

        //attempt ot obtain users joined to the event
        List<String> userList = eventDAO.getAllUsersJoinedToEvent(eventId);

        //put user list to the session attr
        req.getSession().setAttribute("users_joined_to_event", userList);

        //return page with users names
        return "WEB-INF/shared/users_joined_to_event.jsp";
    }
}
