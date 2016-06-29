package com.jvmrules.expression.veriable;


public class BooleanVeriable extends VariableExpression {

    public BooleanVeriable(String name) {
        super(name, BooleanVeriable.class);
    }

    public BooleanVeriable(String name, Object value) {
        super(name, BooleanVeriable.class, value);
    }

    @Override
    public Boolean getValue() {
        return (Boolean) value;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }


}
