package com.shantev.model.db.entity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class Report {
    private String topic;
    private Timestamp startTime;
    private Timestamp endTime;
    private String description;
    private List<User> narrators = new ArrayList<>();

    public Report() {}
    public Report(String topic, Timestamp startTime, Timestamp endTime, String description) {
        this.topic = topic;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
    }

    public Report(String topic, Timestamp startTime, Timestamp endTime, String description, List<User> narrators) {
        this.topic = topic;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
        this.narrators = narrators;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getNarrators() {
        return narrators;
    }

    public void setNarrators(List<User> narrators) {
        this.narrators = narrators;
    }

    @Override
    public String toString() {
        return "Report{" +
                "topic='" + topic + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", description='" + description + '\'' +
                '}';
    }
}
