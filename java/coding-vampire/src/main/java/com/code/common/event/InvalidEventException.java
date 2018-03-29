package com.code.common.event;

public class InvalidEventException extends RuntimeException {
    public InvalidEventException() {
    }

    public InvalidEventException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidEventException(String message) {
        super(message);
    }

    public InvalidEventException(Throwable cause) {
        super(cause);
    }
}
