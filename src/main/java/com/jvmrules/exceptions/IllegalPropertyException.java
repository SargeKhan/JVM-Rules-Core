package com.jvmrules.exceptions;


public class IllegalPropertyException extends ExpressionParseException {
    public IllegalPropertyException() {
    }

    public IllegalPropertyException(String message) {
        super(message);
    }

    public IllegalPropertyException(Throwable cause) {
        super(cause);
    }

    public IllegalPropertyException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalPropertyException(String message, Throwable cause,
                                    boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
