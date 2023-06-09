package com.shantev.useful;

public enum Status {
    SCHEDULED(1), HELD_ON(2), FINISHED(3), CANCELED(4);
    private int statusId;
    Status(int statusId) {
        this.statusId = statusId;
    }

    public int getStatusId() {
        return statusId;
    }
}