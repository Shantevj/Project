package com.shantev.model.command;

import com.shantev.model.command.utility.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IncorrectCommand extends Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        throw new RuntimeException("No command was found! Please try again!");
    }
}
