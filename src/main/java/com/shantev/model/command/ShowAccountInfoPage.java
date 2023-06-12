package com.shantev.model.command;

import com.shantev.model.command.utility.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowAccountInfoPage extends Command{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        return "WEB-INF/shared/my_account.jsp";
    }
}
