package com.jvmrules.expression.veriable;


public class StringVeriable extends VariableExpression {

    public StringVeriable(String name) {
        super(name, StringVeriable.class);
    }

    public StringVeriable(String name, Object value) {
        super(name, StringVeriable.class, value);
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
