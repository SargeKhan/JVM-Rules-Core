package com.jvmrules.expression;

import com.jvmrules.exceptions.InterpretException;

import java.util.Map;


public interface Expression {
    public boolean interpret(final Map<String, ?> bindings) throws InterpretException;
}
