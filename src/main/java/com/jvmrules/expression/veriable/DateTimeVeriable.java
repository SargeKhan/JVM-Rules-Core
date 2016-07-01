package com.jvmrules.expression.veriable;


public class DateTimeVeriable extends VariableExpression {

    public DateTimeVeriable(String name) {
        super(name, DateTimeVeriable.class);
    }

    public DateTimeVeriable(String name, Object value) {
        super(name, DateTimeVeriable.class, value);
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
