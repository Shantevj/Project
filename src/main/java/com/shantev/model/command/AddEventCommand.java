package com.shantev.model.command;

import com.shantev.exception.DBException;
import com.shantev.model.command.utility.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddEventCommand extends Command {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException {

        return "index.jsp";
    }
}
