package com.jvmrules.expression.veriable;


public class FloatVeriable extends VariableExpression {

    public FloatVeriable(String name) {
        super(name, FloatVeriable.class);
    }

    public FloatVeriable(String name, Object value) {
        super(name, FloatVeriable.class, value);
    }


    @Override
    public Float getValue() {
        return (Float) value;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }


}