package com.shantev.model.command;

import com.shantev.exception.DBException;
import com.shantev.model.db.dao.DAOFactory;
import com.shantev.model.db.dao.UserDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlockUserCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String from = req.getParameter("from");
        int id = Integer.parseInt(req.getParameter("userId"));
        DAOFactory daoFactory;
        try {
            daoFactory = DAOFactory.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        UserDAO userDAO = daoFactory.getUserDAO();
        try {
            boolean wasUserBlocked = userDAO.blockUser(id);
        } catch (DBException e) {
            throw new RuntimeException(e);
        }
        //add path`s if user.role is admin --> admin main page if user.role is manager --> manager main page
        return "main?command=select_users&type=" + from;
    }
}
