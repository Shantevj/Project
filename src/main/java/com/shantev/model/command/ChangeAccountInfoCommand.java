package com.shantev.model.command;

import com.shantev.model.command.utility.Command;
import com.shantev.exception.DBException;
import com.shantev.model.db.dao.UserDAO;
import com.shantev.model.db.entity.User;
import com.shantev.model.utility.HashEncryptor;
import com.shantev.model.utility.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeAccountInfoCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        //get user from session
        User user = (User) req.getSession().getAttribute("user");

        //get new user data
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String formPassword = req.getParameter("password");

        //check whether password was changed
        String password = formPassword.equals("") ?
                user.getPassword() : req.getParameter("password");

        //validate new user data
        //if data is not valid return previous page with warning field
        if (firstName.isBlank() || lastName.isBlank() || !Validator.validatePassword(password)) {
            req.getSession().setAttribute("is_changed_data_valid", "not_valid");
            return "main?command=show_account_info_page";
        }

        //hash password and create user
        String hashedPassword = HashEncryptor.getPasswordHash(password, HashEncryptor.DEFAULT_SALT);
        User changedUser = new User(user.getId(), firstName, lastName, user.getLogin(), hashedPassword, user.getRole());

        //attempt to update user data
        UserDAO userDAO = daoFactory.getUserDAO();
        userDAO.updateUser(changedUser);

        //set new user in attribute
        req.getSession().setAttribute("user", changedUser);

        //delete unnecessary attribute from session
        req.getSession().removeAttribute("is_changed_data_valid");

        //return page with account data
        return "main?command=show_my_account_info_page";
    }
}
