package com.jvmrules.expression;


import java.util.Map;

public class NamedExpression implements Expression {
    private String name;

    public NamedExpression(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean interpret(Map<String, ?> bindings) {
        return true;
    }
}
