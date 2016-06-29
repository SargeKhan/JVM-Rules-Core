package com.jvmrules.expression;


public interface Variable<T> extends Expression {
    public String getName();

    public Class<T> getType();

    public T getValue();

    public void setValue(T value);
}
