package com.shantev.model.db.entity;

import com.shantev.useful.Status;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Event {
    private String theme;
    private Status status;
    private String address;
    private String description;
    private Timestamp date;
    private List<Report> reportList = new ArrayList<>();
    private List<User> participants = new ArrayList<>();

    public Event() {}
    public Event(String theme, Status status, String address, String description, Timestamp date) {
        this.theme = theme;
        this.status = status;
        this.address = address;
        this.description = description;
        this.date = date;
    }

    public Event(String theme, Status status, String address, String description, Timestamp date, List<Report> reportList, List<User> participants) {
        this.theme = theme;
        this.status = status;
        this.address = address;
        this.description = description;
        this.date = date;
        this.reportList = reportList;
        this.participants = participants;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public List<Report> getReportList() {
        return reportList;
    }

    public void setReportList(List<Report> reportList) {
        this.reportList = reportList;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    @Override
    public String toString() {
        return "Event{" +
                "theme='" + theme + '\'' +
                ", status='" + status + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                '}';
    }
}
