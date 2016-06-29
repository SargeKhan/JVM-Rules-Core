package com.jvmrules.expression.veriable;

import com.jvmrules.expression.Variable;

import java.util.Map;

public abstract class VariableExpression<T> implements Variable {

    protected String name;
    protected Class<T> type;
    protected T value;

    public VariableExpression(String name, Class<T> type, T value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public VariableExpression(String name, Class<T> type) {
        this(name, type, null);
    }

    public String getName() {
        return this.name;
    }

    @Override
    public Class<T> getType() {
        return type;
    }


    public boolean interpret(Map<String, ?> bindings) {
        return true;
    }


}
