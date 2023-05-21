package com.shantev.model.db.dao.mysql;

import com.shantev.exception.DBException;
import com.shantev.model.db.dao.ReportDAO;
import com.shantev.model.db.entity.Report;

import java.util.List;

public class MysqlReportDAO implements ReportDAO {

    @Override
    public void insertReport(Report newReport) throws DBException {

    }

    @Override
    public boolean updateReport(int id) throws DBException {
        return false;
    }

    @Override
    public List<String> getAllReports() throws DBException {
        return null;
    }

    @Override
    public Report getReportById(int id) throws DBException {
        return null;
    }

    @Override
    public boolean deleteReport(int id) throws DBException {
        return false;
    }
}