package com.shantev.model.db.dao;

import com.shantev.exception.DBException;
import com.shantev.model.db.entity.Report;

import java.util.List;

public interface ReportDAO {
    void insertReport(Report newReport) throws DBException;
    boolean updateReport(int id) throws DBException;
    List<String> getAllReports() throws DBException;
    Report getReportById(int id) throws DBException;
    boolean deleteReport(int id) throws DBException;
}