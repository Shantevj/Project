package com.shantev.controller;

import com.shantev.exception.DBException;
import com.shantev.model.command.utility.*;

import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
//import java.io.PrintWriter;
//import java.sql.SQLException;

//@WebServlet("/main")

public class Controller extends HttpServlet {
    //GET and POST do the same thing
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }

    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //obtain HTTP method
        String httpMethod = req.getMethod();

        //obtain command name from request
        String commandName = req.getParameter("command");

        //obtain appropriate command
        Command command = CommandContainer.getCommand(commandName);

        //execute command and get further path
        String furtherPath = null;
        try {
            furtherPath = command.execute(req, resp);
        } catch (DBException e) {
            throw new RuntimeException(e);
        }

        //GET -> forward, POST -> sendRedirect
        if (furtherPath != null) {
            if (httpMethod.equals("GET")) req.getRequestDispatcher(furtherPath).forward(req, resp);
            else if (httpMethod.equals("POST")) resp.sendRedirect(furtherPath);
            else throw new RuntimeException();
        }
    }

}
