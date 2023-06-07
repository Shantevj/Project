package com.shantev.model.command;

import com.shantev.model.command.utility.Command;
import com.shantev.exception.DBException;
import com.shantev.model.db.dao.UserDAO;
import com.shantev.model.db.entity.User;
import com.shantev.useful.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListUsersCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        //find out who can access the user list
        Role userRole = ((User) req.getSession().getAttribute("user")).getRole();

        //attempt to get UserDAO instance, and obtain list of all users
        UserDAO userDAO = daoFactory.getUserDAO();
        List<User> fullUserList;
        fullUserList = userDAO.getAllUsers();

        //find out what type of users was requested
        String userType = req.getParameter("type");

        //get only requested user
        List<User> filteredUserList = getFilteredUserList(userType, fullUserList);
        req.setAttribute("filteredUserList", filteredUserList);

        //base on user type return appropriate page
        if (userRole == Role.ADMIN) {
            return "WEB-INF/admin/admin_user_list.jsp";
        } else if (userRole == Role.MANAGER) return "WEB-INF/manager/manager_user_list.jsp";
        return "error_page.jsp";
    }

    private List<User> filterUsers(List<User> userList, Role role) {
        Stream<User> stream = userList.stream();
        Stream<User> filteredStream = stream.filter(
                user -> user.getRole() == role
        );
        return filteredStream.collect(Collectors.toList());
    }

    private List<User> getFilteredUserList(String userType, List<User> fullUserList) {
        List<User> filteredUserList = new ArrayList<>();
        switch (userType) {
            case "admin":
                filteredUserList = filterUsers(fullUserList, Role.ADMIN);
                break;
            case "manager":
                filteredUserList = filterUsers(fullUserList, Role.MANAGER);
                break;
            case "speaker":
                filteredUserList = filterUsers(fullUserList, Role.SPEAKER);
                break;
            case "regular_user":
                filteredUserList = filterUsers(fullUserList, Role.USER);
                break;
            case "banned_user":
                filteredUserList = filterUsers(fullUserList, Role.BANNED);
                break;
        }
        return filteredUserList;
    }
}
