package com.jvmrules.expression.veriable;


public class DateVeriable extends VariableExpression {

    public DateVeriable(String name) {
        super(name, DateVeriable.class);
    }

    public DateVeriable(String name, Object value) {
        super(name, DateVeriable.class, value);
    }


    @Override
    public String getValue() {
        return (String) value;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }


}
