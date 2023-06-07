package com.shantev.model.command.utility;

import com.shantev.exception.DBException;
import com.shantev.model.db.dao.DAOFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Command {
    public static DAOFactory daoFactory;

    static {
        try {
            daoFactory = DAOFactory.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public abstract String execute(HttpServletRequest req, HttpServletResponse resp) throws DBException;
}
