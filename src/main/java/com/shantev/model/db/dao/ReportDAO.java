package com.shantev.model.db.dao;

import com.shantev.exception.DBException;
import com.shantev.model.db.entity.Report;

import java.util.List;

public interface ReportDAO {
    void addNewReport(Report newReport) throws DBException;
    void updateReport(Report report) throws DBException;
    List<String> getAllReports() throws DBException;
    Report getReportById(int id) throws DBException;
    boolean deleteReport(int id) throws DBException;
}