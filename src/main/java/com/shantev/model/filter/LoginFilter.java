package com.shantev.model.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        if(!req.getParameter("command").equals("log_in") && !req.getParameter("command").equals("sign_up") && req.getSession().getAttribute("user") == null) {
            resp.sendRedirect("log_in.jsp");
        }
        else filterChain.doFilter(req, resp);
    }
}
