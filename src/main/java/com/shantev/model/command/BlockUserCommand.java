package com.shantev.model.command;

import com.shantev.exception.DBException;
import com.shantev.model.db.dao.DAOFactory;
import com.shantev.model.db.dao.UserDAO;
import com.shantev.model.db.entity.User;
import com.shantev.useful.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BlockUserCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        //get from parameter from request to determine to which page make redirect
        String from = req.getParameter("from");

        //get id of user to block
        int id = Integer.parseInt(req.getParameter("userId"));

        //attempt to get DAOFactory instance
        DAOFactory daoFactory;
        try {
            daoFactory = DAOFactory.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //attempt to get user data from database, and change it
        UserDAO userDAO = daoFactory.getUserDAO();
        User user = userDAO.getUserById(id);
        user.setRole(Role.BANNED);
        userDAO.updateUser(user);

        //return page from which request has come
        return "main?command=get_users&type=" + from;
    }
}
