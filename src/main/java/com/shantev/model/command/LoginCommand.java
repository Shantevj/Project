package com.shantev.model.command;

import com.shantev.exception.DBException;
import com.shantev.model.db.dao.DAOFactory;
import com.shantev.model.db.dao.UserDAO;
import com.shantev.model.db.entity.User;
import com.shantev.model.validator.HashEncryptor;
import com.shantev.model.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        boolean isLoginDataValid = Validator.validateLoginData(login, password);
        if(!isLoginDataValid) {
            req.getSession().setAttribute("is_login_data_valid", "not_valid");
            return "log_in.jsp";
        }
        DAOFactory daoFactory;
        try {
            daoFactory = DAOFactory.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        UserDAO userDAO = daoFactory.getUserDAO();
        try {
            String hashedPassword = HashEncryptor.getPasswordHash(password, HashEncryptor.DEFAULT_SALT);
            User user = userDAO.getUserByLoginAndPassword(login, hashedPassword);
            if(user == null) {
                req.getSession().setAttribute("login_failed", "failed");
                return "log_in.jsp";
            }
            req.getSession().removeAttribute("is_login_data_valid");
            req.getSession().removeAttribute("login_failed");
            req.getSession().setAttribute("user", user);
        } catch (DBException e) {
            throw new RuntimeException(e);
        }
        return "index.jsp";
    }
}
