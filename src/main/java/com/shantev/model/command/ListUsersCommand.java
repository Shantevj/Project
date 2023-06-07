package com.shantev.model.command;

import com.shantev.exception.DBException;
import com.shantev.model.db.dao.DAOFactory;
import com.shantev.model.db.dao.UserDAO;
import com.shantev.model.db.entity.User;
import com.shantev.useful.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ListUsersCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        DAOFactory daoFactory;
        try {
            daoFactory = DAOFactory.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Role userRole = ((User) req.getSession().getAttribute("user")).getRole();
        UserDAO userDAO = daoFactory.getUserDAO();
        List<User> userList;
        try {
            userList = userDAO.getAllUsers();
        } catch (DBException e) {
            throw new RuntimeException(e);
        }
        List<User> filteredUserList = null;
        String userType = req.getParameter("type");
        if (userRole == Role.ADMIN) {
            switch (userType) {
                case "admin":
                    filteredUserList = filterUsers(userList, Role.ADMIN);
                    break;
                case "manager":
                    filteredUserList = filterUsers(userList, Role.MANAGER);
                    break;
                case "speaker":
                    filteredUserList = filterUsers(userList, Role.SPEAKER);
                    break;
                case "regular_user":
                    filteredUserList = filterUsers(userList, Role.USER);
                    break;
                case "banned_user":
                    filteredUserList = filterUsers(userList, Role.BANNED);
                    break;
            }
            req.setAttribute("filteredUserList", filteredUserList);
            return "WEB-INF/admin/admin_user_list.jsp";
        }
        if (userRole == Role.MANAGER) return "WEB-INF/manager/manager_user_list.jsp";
        return "error_page.jsp";
    }

    private List<User> filterUsers(List<User> userList, Role role) {
        Stream<User> stream = userList.stream();
        Stream<User> filteredStream = stream.filter(
                user -> user.getRole() == role
        );
        return filteredStream.collect(Collectors.toList());
    }
}
