package com.jvmrules.expression.veriable;

public class IntegerVeriable extends VariableExpression {

    public IntegerVeriable(String name) {
        super(name, IntegerVeriable.class);
    }

    public IntegerVeriable(String name, Object value) {
        super(name, IntegerVeriable.class, value);
    }

    @Override
    public Integer getValue() {
        return (Integer) value;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }


}