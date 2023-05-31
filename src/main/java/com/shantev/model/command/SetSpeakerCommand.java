package com.shantev.model.command;

import com.shantev.exception.DBException;
import com.shantev.model.db.dao.DAOFactory;
import com.shantev.model.db.dao.UserDAO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetSpeakerCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
//        int id = Integer.parseInt(req.getParameter("userId"));
//        DAOFactory daoFactory;
//        try {
//            daoFactory = DAOFactory.getInstance();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        UserDAO userDAO = daoFactory.getUserDAO();
//        try {
//            userDAO.setSpeaker(id);
//        } catch (DBException e) {
//            throw new RuntimeException(e);
//        }
        return "main?command=select_users&type=regular_user";
    }
}
