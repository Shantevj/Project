package com.shantev.model.command;

import com.shantev.exception.DBException;
import com.shantev.model.db.dao.DAOFactory;
import com.shantev.model.db.dao.UserDAO;
import com.shantev.model.db.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        DAOFactory daoFactory;
        try {
            daoFactory = DAOFactory.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        UserDAO userDAO = daoFactory.getUserDAO();
        try {
            User user = userDAO.getUserByLoginAndPassword(login, password);
            if(user == null) {
                req.getSession().setAttribute("login_failed", "failed");
                return "log_in.jsp";
            }
            req.getSession().removeAttribute("login_failed");
            req.getSession().setAttribute("user", user);
        } catch (DBException e) {
            throw new RuntimeException(e);
        }
        //add path`s if user.role is admin --> admin main page if user.role is manager --> manager main page
        return "index.jsp";
    }
}
