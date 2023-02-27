package com.shantev.controller;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/main")

public class Controller extends HttpServlet {
    private static List<String> userList = new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.getSession().setAttribute("attribute", null);
//        req.getSession().getAttribute("attribute");
//        req.getSession().removeAttribute("attribute");
//        String name = req.getParameter("name");
//        req.setAttribute("userRole", "admin");
        String command = req.getParameter("command");
        switch (command) {
            case "log_in" : req.getRequestDispatcher("log_in.jsp").forward(req, resp); break;
            case "sing_up" : req.getRequestDispatcher("sing_up.jsp").forward(req, resp); break;
        }
//        req.getRequestDispatcher("data.jsp").forward(req, resp);
//        try {
//            getAllUsers();
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//        resp.setContentType("text/html");
//        PrintWriter writer = resp.getWriter();
//        for (String user : userList) {
//           writer.format("<h3>%s</h3>", user);
//        }
//        userList.clear();
    }

    private void getAllUsers() throws SQLException {
        Connection con = null;
        try {
            Context initContext = new InitialContext();
            Context envContext  = (Context)initContext.lookup("java:/comp/env");
            DataSource ds = (DataSource)envContext.lookup("jdbc/ConferenceDB");
            con = ds.getConnection();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from Users");
            while (rs.next()) {
                String user = String.format("First name %s, last name %s <a href=\"editUser?userId=%d\">Edit</a>", rs.getString("first_name"), rs.getString("last_name"), rs.getInt(1));
                userList.add(user);
            }
        } finally {
            close(rs);
            close(stmt);
            close(con);
        }
    }

    private static void close(AutoCloseable resource) {
        if(resource != null) {
            try {
                resource.close();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
