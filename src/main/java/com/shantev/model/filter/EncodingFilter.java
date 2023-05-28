package com.shantev.model.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EncodingFilter implements Filter {
    private String requestEncoding;
    private String responseEncoding;
    @Override
    public void init(FilterConfig filterConfig) {
        this.requestEncoding = filterConfig.getInitParameter("requestEncoding");
        this.responseEncoding = filterConfig.getInitParameter("responseEncoding");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        req.setCharacterEncoding(requestEncoding);
        resp.setHeader("content-type", responseEncoding);
        filterChain.doFilter(servletRequest, resp);
    }
}
