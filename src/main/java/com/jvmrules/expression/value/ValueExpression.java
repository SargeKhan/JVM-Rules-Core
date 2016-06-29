package com.jvmrules.expression.value;

import com.jvmrules.expression.Expression;

import java.util.Map;

public class ValueExpression<T> implements Expression {


    public T value;
    public Class<T> type;

    public ValueExpression(T value, Class type) {
        this.value = value;
        this.type = type;
    }


    public T getValue() {
        return this.value;
    }

    public Class<T> getType() {
        return this.type;
    }

    public boolean interpret(Map<String, ?> bindings) {
        return true;
    }
}