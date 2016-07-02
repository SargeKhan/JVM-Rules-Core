package com.jvmrules.expression.veriable;


import java.util.Date;

public class DateTimeVeriable extends VariableExpression {

    public DateTimeVeriable(String name) {
        super(name, DateTimeVeriable.class);
    }

    public DateTimeVeriable(String name, Object value) {
        super(name, DateTimeVeriable.class, value);
    }


    @Override
    public Date getValue() {
        return (Date) value;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }


}
