package com.shantev.model.command;

import com.shantev.exception.DBException;
import com.shantev.model.command.utility.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowAddEventPageCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {
        return "WEB-INF/manager/manager_add_new_event_page.jsp";
    }
}
