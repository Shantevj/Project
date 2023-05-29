package com.shantev.model.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountInfoCommand extends Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        if(req.getParameter("type").equals("view")) return "WEB-INF/shared/my_account.jsp";
        return "WEB-INF/shared/change_my_info_page.jsp";
    }
}
