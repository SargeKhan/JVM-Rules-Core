package com.jvmrules.exceptions;


public class InterpretException extends Exception {
    public InterpretException() {
    }

    public InterpretException(String message) {
        super(message);
    }

    public InterpretException(Throwable cause) {
        super(cause);
    }

    public InterpretException(String message, Throwable cause) {
        super(message, cause);
    }

    public InterpretException(String message, Throwable cause,
                              boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
