package com.shantev.model.command;

import com.shantev.exception.DBException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Command {
    public abstract String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException;
}
