package com.shantev.model.command;

import com.shantev.exception.DBException;
import com.shantev.model.db.dao.DAOFactory;
import com.shantev.model.db.dao.UserDAO;
import com.shantev.model.db.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeAccountInfoCommand extends Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
//        String firstName = req.getParameter("firstName");
//        String lastName = req.getParameter("lastName");
//        String password = req.getParameter("password");
//        User user = (User) req.getSession().getAttribute("user");
//        int id = user.getId();
//        DAOFactory daoFactory;
//        try {
//            daoFactory = DAOFactory.getInstance();
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//        UserDAO userDAO = daoFactory.getUserDAO();
//        try {
//            boolean isFirstNameChanged = userDAO.changeFirstName(firstName, id);
//            boolean isLastNameChanged = userDAO.changeLastName(lastName, id);
//            boolean isPasswordChanged = userDAO.changePassword(password, id);
//            if(isFirstNameChanged && isLastNameChanged && isPasswordChanged) {
//                user.setFirstName(firstName);
//                user.setLastName(lastName);
//                user.setPassword(password);
//                return "main?command=my_account&type=view";
//            }
//        } catch (DBException e) {
//            throw new RuntimeException(e);
//        }
        return "main?command=my_account&type=change";
    }
}
