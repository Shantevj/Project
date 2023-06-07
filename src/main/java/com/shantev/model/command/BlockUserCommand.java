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
            User user = userDAO.getUserById(id);
            user.setRole(Role.BANNED);
            userDAO.updateUser(user);
        } catch (DBException e) {
            throw new RuntimeException(e);
        }
        return "main?command=get_users&type="+ from;
    }
}
