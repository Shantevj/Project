package com.shantev.model.db.dao.mysql;

public class Constants {
    /****************************USER QUERIES********************************/
    public static String GET_USER_BY_ID = "SELECT * FROM Users WHERE id=?";
    public static String GET_ALL_USERS = "SELECT * FROM Users";
    public static String ADD_NEW_USER = "INSERT INTO Users VALUES(DEFAULT, ?, ?, ?, ?, ?)";
    public static String UPDATE_USER = "UPDATE Users " +
            "SET first_name=?, last_name=?, login=?, password=?, role_id=? " +
            "WHERE id=?";
    public static String DELETE_USER = "DELETE FROM Users WHERE id=?";
    public static String GET_USER_BY_LOGIN_AND_PASSWORD = "SElECT * FROM Users " +
            "WHERE login=? AND password=?";

    /****************************EVENT QUERIES********************************/
//    public static String GET_EVENT_BY_ID = "SELECT * FROM Events WHERE id=?";
    public static String GET_EVENT_BY_ID = "SELECT e.id AS id" +
            ", e.theme AS theme" +
            ", c.name AS category" +
            ", ed.date AS date" +
            ", ed.address AS address" +
            ", ed.description AS description" +
            ", es.description AS status" +
            " FROM Events AS e, Categories AS c, Event_statuses AS es, Event_details AS ed " +
            "WHERE e.id=? AND ed.event_id=e.id AND c.id=e.category_id AND es.id=e.event_status_id";
    public static String GET_EVENTS_BY_CATEGORY = "SELECT e.id AS id" +
            ", e.theme AS theme" +
            ", c.name AS category" +
            ", ed.date AS date" +
            ", ed.address AS address" +
            ", ed.description AS description" +
            ", es.description AS status" +
            " FROM Events AS e, Categories AS c, Event_statuses AS es, Event_details AS ed " +
            "WHERE category_id=? AND ed.event_id=e.id AND c.id=e.category_id AND es.id=e.event_status_id";
    public static String GET_ALL_EVENTS = "SELECT e.id AS id" +
            ", e.theme AS theme" +
            ", c.name AS category" +
            ", ed.date AS date" +
            ", ed.address AS address" +
            ", ed.description AS description" +
            ", es.description AS status" +
            " FROM Events AS e, Categories AS c, Event_statuses AS es, Event_details AS ed " +
            "WHERE ed.event_id=e.id AND c.id=e.category_id AND es.id=e.event_status_id";
    public static String ADD_NEW_EVENT = "INSERT INTO `Events` VALUES(DEFAULT, ?, ?, ?)";
    public static String ADD_EVENT_DETAIL = "INSERT INTO Event_details VALUES(?, ?, ?, ?)";
    public static String UPDATE_EVENT = "UPDATE Events SET theme=?, event_status_id=? WHERE id=?";
    public static String UPDATE_EVENT_DETAIL = "UPDATE Event_details SET date=?, address=?, description=? WHERE event_id=?";
    public static String DELETE_EVENT = "DELETE FROM Events WHERE id=?";

}
