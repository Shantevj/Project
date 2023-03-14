package com.shantev.exception;

public class DBException extends Exception{
    public DBException() {
        super();
    }
    public DBException(String message, Throwable ex) {
        super(message, ex);
    }
}
