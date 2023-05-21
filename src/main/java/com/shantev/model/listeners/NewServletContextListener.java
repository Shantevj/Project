package com.shantev.model.listeners;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class NewServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext st = event.getServletContext();
        String siteName = st.getInitParameter("siteName");
        st.setAttribute("siteName", siteName);
    }
}
