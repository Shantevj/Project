package com.shantev.model.db.dao.mysql;

import com.shantev.model.db.dao.*;

public class MysqlDAOFactory extends DAOFactory {
    @Override
    public EventDAO getEventDAO() {
        return new MysqlEventDAO();
    }

    @Override
    public ReportDAO getReportDAO() {
        return new MysqlReportDAO();
    }

    @Override
    public ResponseDAO getResponseDAO() {
        return new MysqlResponseDAO();
    }

    @Override
    public UserDAO getUserDAO() { return new MysqlUserDAO(); }
}