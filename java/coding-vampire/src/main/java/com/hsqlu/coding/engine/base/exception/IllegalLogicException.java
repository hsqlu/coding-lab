package com.hsqlu.coding.engine.base.exception;

/**
 * Created: 2016/7/7.
 * Author: Qiannan Lu
 */
public class IllegalLogicException extends RuntimeException {

    public IllegalLogicException() {
    }

    public IllegalLogicException(String message) {
        super(message);
    }

    public IllegalLogicException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalLogicException(Throwable cause) {
        super(cause);
    }

    public IllegalLogicException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
