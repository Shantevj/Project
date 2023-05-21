package com.shantev.controller;

import com.shantev.model.db.dao.DAOFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.shantev.model.db.dao.UserDAO;
import com.shantev.exception.DBException;
import com.shantev.model.db.entity.User;

//@WebServlet("/main")

public class Controller extends HttpServlet {
    private static final List<String> userList = new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String supportEmail = getServletConfig().getInitParameter("supportEmail");
//        User user = new User("sdfsdf", "sdfdsfsdfdf", "s", "pas", "sdf");

//        req.getSession().setAttribute("user", user);
//        req.getSession().getAttribute("attribute");
//        req.getSession().removeAttribute("attribute");
//        String name = req.getParameter("name");
//        req.setAttribute("userRole", "admin");
//        String command = req.getParameter("command");
//        switch (command) {
//            case "log_in" : resp.getWriter().format("%s, %s, %s", req.getParameter("login"), req.getParameter("password")); break;
//            case "sing_up" : req.getRequestDispatcher("sing_up.jsp").forward(req, resp); break;
//        }

        DAOFactory.setDAOFactoryFQN("com.shantev.model.db.dao.mysql.MysqlDAOFactory");
        DAOFactory daoFactory;
        try {
            daoFactory = DAOFactory.getInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        UserDAO userDAO = daoFactory.getUserDAO();
        try {
             resp.getWriter().println(userDAO.getUserByLogin("admin@gmail.com", "admin"));
        } catch (DBException e) {
            throw new RuntimeException(e);
        }
//        req.getRequestDispatcher("data.jsp").forward(req, resp);
//        resp.setContentType("text/html");
//        PrintWriter writer = resp.getWriter();
//        String siteName = (String) getServletContext().getAttribute("siteName");
//        writer.println(siteName);
//        writer.println(supportEmail);
//        writer.println("Event id --->" + req.getParameter("eventID"));
//        for (String user : userList) {
//           writer.format("<h3>%s</h3>", user);
//        }
//        userList.clear();
    }

    @Override protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("Event id --->" + req.getParameter("eventID"));
        String command = req.getParameter("command");
        switch (command) {
            case "log_in" :
                String login = req.getParameter("login");
                String password = req.getParameter("password");
                DAOFactory.setDAOFactoryFQN("com.shantev.model.db.dao.mysql.MysqlDAOFactory");
                DAOFactory daoFactory;
                try {
                    daoFactory = DAOFactory.getInstance();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                UserDAO userDAO = daoFactory.getUserDAO();
                try {
                    User user = userDAO.getUserByLogin(login, password);
                    req.getSession().setAttribute("user", user);
                } catch (DBException e) {
                    throw new RuntimeException(e);
                } req.getRequestDispatcher("index.jsp").forward(req, resp); break;
            case "sing_up" : req.getRequestDispatcher("sing_up.jsp").forward(req, resp); break;
        }
    }


}
