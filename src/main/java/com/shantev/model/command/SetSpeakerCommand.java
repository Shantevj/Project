package com.shantev.model.command;

import com.shantev.model.command.utility.Command;
import com.shantev.exception.DBException;
import com.shantev.model.db.dao.UserDAO;
import com.shantev.model.db.entity.User;
import com.shantev.useful.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetSpeakerCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        //get id of user to block
        int id = Integer.parseInt(req.getParameter("userId"));

        //attempt to get user data from database, and change it
        UserDAO userDAO = daoFactory.getUserDAO();
        User user = userDAO.getUserById(id);
        user.setRole(Role.SPEAKER);
        userDAO.updateUser(user);

        //return page from which request has come
        return "main?command=get_users&type=regular_user";
    }
}
