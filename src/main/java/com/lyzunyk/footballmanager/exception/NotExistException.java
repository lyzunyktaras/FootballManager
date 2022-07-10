package com.lyzunyk.footballmanager.exception;

public class NotExistException extends IllegalArgumentException {
    private static final String NOT_EXIST_EXCEPTION = "Not exist";

    public NotExistException(String message) {
        super(message.isEmpty() ? NOT_EXIST_EXCEPTION : message);
    }

    public NotExistException() {
        super(NOT_EXIST_EXCEPTION);
    }
}