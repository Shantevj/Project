package com.shantev.model.command;

import com.shantev.model.db.entity.User;
import com.shantev.useful.Role;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListUsersCommand extends Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Role userRole = ((User) req.getSession().getAttribute("user")).getRole();
        if(userRole == Role.ADMIN) return "WEB-INF/admin/admin_user_list.jsp";
        if(userRole == Role.MANAGER) return "WEB-INF/manager/manager_user_list.jsp";
        return "error_page.jsp";
    }
}
