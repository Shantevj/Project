package com.shantev.model.command;

import com.shantev.model.command.utility.Command;
import com.shantev.exception.DBException;
import com.shantev.model.db.dao.UserDAO;
import com.shantev.model.db.entity.User;
import com.shantev.model.validator.HashEncryptor;
import com.shantev.model.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        //get login data from request
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        //check if this data is valid
        boolean isLoginDataValid = Validator.validateLoginData(login, password);

        //if login data is not valid return login page with error field
        if (!isLoginDataValid) {
            req.getSession().setAttribute("is_login_data_valid", "not_valid");
            return "log_in.jsp";
        }

        //attempt to get UserDAO instance, hash password and try to find same user in database
        UserDAO userDAO = daoFactory.getUserDAO();
        String hashedPassword = HashEncryptor.getPasswordHash(password, HashEncryptor.DEFAULT_SALT);
        User user = userDAO.getUserByLoginAndPassword(login, hashedPassword);

        //if login data is not correct return login page with error field
        if (user == null) {
            req.getSession().setAttribute("login_failed", "failed");
            return "log_in.jsp";
        }

        //remove unnecessary attributes and set user obtained from database as attribute
        req.getSession().removeAttribute("is_login_data_valid");
        req.getSession().removeAttribute("login_failed");
        req.getSession().setAttribute("user", user);

        //if user has logged in successfully, return index.jsp (main page)
        return "index.jsp";
    }
}
