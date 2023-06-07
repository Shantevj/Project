package com.shantev.model.command;

import com.shantev.model.command.utility.Command;
import com.shantev.exception.DBException;
import com.shantev.model.db.dao.UserDAO;
import com.shantev.model.db.entity.User;
import com.shantev.model.validator.HashEncryptor;
import com.shantev.model.validator.Validator;
import com.shantev.useful.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        //get sign up data from request
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String login = req.getParameter("email");
        String password1 = req.getParameter("password1");
        String password2 = req.getParameter("password2");

        //check if this data is valid
        boolean isSignupDataValid = Validator.validateSignupData(firstName, lastName, login, password1, password2);

        //if data is not valid return sign up page with warning field
        if (!isSignupDataValid) {
            req.getSession().setAttribute("is_signup_data_valid", "not_valid");
            return "sign_up.jsp";
        }

        //attempt to get UserDAO instance, hash password and create new user object
        UserDAO userDAO = daoFactory.getUserDAO();
        String hashedPassword = HashEncryptor.getPasswordHash(password1, HashEncryptor.DEFAULT_SALT);
        User user = new User(firstName, lastName, login, hashedPassword, Role.USER);

        //attempt to add new user to the database
        userDAO.addNewUser(user);

        //remove unnecessary attributes and set user obtained from database as attribute
        req.getSession().setAttribute("user", user);
        req.getSession().removeAttribute("is_signup_data_valid");

        //if user has signed up successfully, return index.jsp (main page)
        return "index.jsp";
    }
}
