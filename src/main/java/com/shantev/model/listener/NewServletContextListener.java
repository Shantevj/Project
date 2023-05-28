package com.shantev.model.listener;

import com.shantev.model.db.dao.DAOFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class NewServletContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        ServletContext st = event.getServletContext();
        String daoFQN = st.getInitParameter("daoFQN");
        DAOFactory.setDAOFactoryFQN(daoFQN);
    }
}
