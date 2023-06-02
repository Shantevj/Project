package com.shantev.model.command;

import com.shantev.exception.DBException;
import com.shantev.model.db.dao.DAOFactory;
import com.shantev.model.db.dao.UserDAO;
import com.shantev.model.db.entity.User;
import com.shantev.model.validator.HashEncryptor;
import com.shantev.model.validator.Validator;
import com.shantev.useful.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String login = req.getParameter("email");
        String password1 = req.getParameter("password1");
        String password2 = req.getParameter("password2");
        boolean isSignupDataValid = Validator.validateSignupData(firstName, lastName, login, password1, password2);
        if (!isSignupDataValid) {
            req.getSession().setAttribute("is_signup_data_valid", "not_valid");
            return "sign_up.jsp";
        }
        String hashedPassword = HashEncryptor.getPasswordHash(password1, HashEncryptor.DEFAULT_SALT);
        User user = new User(firstName, lastName, login, hashedPassword, Role.USER);
        DAOFactory daoFactory;
        try {
            daoFactory = DAOFactory.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        UserDAO userDAO = daoFactory.getUserDAO();
        try {
            userDAO.addNewUser(user);
            req.getSession().setAttribute("user", user);
        } catch (DBException e) {
            throw new RuntimeException(e);
        }
        req.getSession().removeAttribute("is_signup_data_valid");
        return "index.jsp";
    }
}
