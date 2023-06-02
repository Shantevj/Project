package com.shantev.model.command;

import com.shantev.exception.DBException;
import com.shantev.model.db.dao.DAOFactory;
import com.shantev.model.db.dao.UserDAO;
import com.shantev.model.db.entity.User;
import com.shantev.model.validator.HashEncryptor;
import com.shantev.model.validator.Validator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeAccountInfoCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = (User) req.getSession().getAttribute("user");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String formPassword = req.getParameter("password");
        String password = formPassword.equals("") ?
                 user.getPassword() : req.getParameter("password");
        if(firstName.isBlank() || lastName.isBlank() || !Validator.validatePassword(password)) {
            req.getSession().setAttribute("is_changed_data_valid", "not_valid");
            return "main?command=my_account&type=change";
        }
        String hashedPassword = HashEncryptor.getPasswordHash(password, HashEncryptor.DEFAULT_SALT);
        User changedUser = new User(user.getId(), firstName, lastName, user.getLogin(), hashedPassword, user.getRole());
        DAOFactory daoFactory;
        try {
            daoFactory = DAOFactory.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        UserDAO userDAO = daoFactory.getUserDAO();
        try {
            userDAO.updateUser(changedUser);
            req.getSession().setAttribute("user", changedUser);
        } catch (DBException e) {
            throw new RuntimeException(e);
        }
        req.getSession().removeAttribute("is_changed_data_valid");
        return "main?command=my_account&type=view";
    }
}
