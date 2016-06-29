package com.jvmrules.exceptions;


public class IllegalPropertyValueException extends InterpretException {
    public IllegalPropertyValueException() {
    }

    public IllegalPropertyValueException(String message) {
        super(message);
    }

    public IllegalPropertyValueException(Throwable cause) {
        super(cause);
    }

    public IllegalPropertyValueException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalPropertyValueException(String message, Throwable cause,
                                         boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
