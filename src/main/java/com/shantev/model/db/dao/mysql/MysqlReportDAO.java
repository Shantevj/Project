package com.shantev.model.db.dao.mysql;

import com.shantev.exception.DBException;
import com.shantev.exception.Messages;
import com.shantev.model.db.dao.ReportDAO;
import com.shantev.model.db.dao.Utility;
import com.shantev.model.db.entity.Report;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MysqlReportDAO implements ReportDAO {

    @Override
    public void addNewReport(Report report) throws DBException {
        Connection con = Utility.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(Constants.ADD_NEW_REPORT, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, report.getTopic());
            stmt.setTimestamp(2, report.getStartTime());
            stmt.setTimestamp(3, report.getEndTime());
            stmt.setString(4, report.getDescription());
            stmt.setInt(5, report.getEventId());
            int rowsAffected = stmt.executeUpdate();
            if(rowsAffected > 0) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    if(rs.next()) {
                        report.setId(rs.getInt(1));
                    }
                }
            }

        } catch (SQLException ex) {
            throw new DBException (Messages.CANNOT_ADD_REPORT, ex);
        } finally {
            Utility.close(stmt);
            Utility.close(con);
        }
    }

    @Override
    public void updateReport(Report report) throws DBException {
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