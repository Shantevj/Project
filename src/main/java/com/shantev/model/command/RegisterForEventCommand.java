package com.shantev.model.command;

import com.shantev.exception.DBException;
import com.shantev.model.db.dao.DAOFactory;
import com.shantev.model.db.dao.UserDAO;
import com.shantev.model.db.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterForEventCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int eventId = Integer.parseInt(req.getParameter("eventId"));
        User user = (User) req.getSession().getAttribute("user");
        int userId = user.getId();

        DAOFactory daoFactory;
        try {
            daoFactory = DAOFactory.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        UserDAO userDAO = daoFactory.getUserDAO();
        try {
            userDAO.registerUserForEvent(eventId, userId);
        } catch (DBException e) {
            throw new RuntimeException(e);
        }
        return "index.jsp";
    }
}
