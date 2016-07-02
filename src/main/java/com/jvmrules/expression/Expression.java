package com.jvmrules.expression;

import com.jvmrules.exceptions.InterpretException;

import java.util.Map;


public abstract class Expression {
    private int priority = 0;

    public abstract boolean interpret(final Map<String, ?> bindings) throws InterpretException;

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
