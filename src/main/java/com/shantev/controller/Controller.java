package com.shantev.controller;

import com.shantev.model.command.*;

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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        process(req, resp);
    }
    private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String httpMethod = req.getMethod();
        String commandName = req.getParameter("command");
        Command command = CommandContainer.getCommand(commandName);
        String furtherPath = command.execute(req, resp);
        if(httpMethod.equals("GET")) req.getRequestDispatcher(furtherPath).forward(req, resp);
        else if (httpMethod.equals("POST")) resp.sendRedirect(furtherPath);
        else throw new RuntimeException();
    }

}
