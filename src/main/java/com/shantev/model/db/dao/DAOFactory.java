package com.shantev.model.db.dao;

public abstract class DAOFactory {
    private static DAOFactory instance;
    private static String daoFactoryFQN;

    public static synchronized DAOFactory getInstance() throws Exception {
        if(instance == null) {
            Class<?> clazz = Class.forName(DAOFactory.daoFactoryFQN);
            instance = (DAOFactory) clazz.getDeclaredConstructor().newInstance();
        }
        return instance;
    }

    protected DAOFactory() {

    }

    public static void setDAOFactoryFQN(String daoFactoryFQN) {
        instance = null;
        DAOFactory.daoFactoryFQN = daoFactoryFQN;
    }

    public abstract EventDAO getEventDAO();
    public abstract ReportDAO getReportDAO();
    public abstract ResponseDAO getResponseDAO();
    public abstract UserDAO getUserDAO();

}
