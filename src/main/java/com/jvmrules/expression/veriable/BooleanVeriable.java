package com.jvmrules.expression.veriable;


import com.jvmrules.expression.operation.Equals;
import com.jvmrules.expression.operation.In;
import com.jvmrules.expression.operation.NotEquals;
import com.jvmrules.expression.value.ValueExpression;

import java.util.List;

public class BooleanVeriable extends VariableExpression {

    public BooleanVeriable(String name) {
        super(name, BooleanVeriable.class);
        legalOperations.put(Equals.class, "equal");
        legalOperations.put(NotEquals.class, "notEqual");
        legalOperations.put(In.class, "in");
    }

    public BooleanVeriable(String name, Object value) {
        super(name, BooleanVeriable.class, value);
        legalOperations.put(Equals.class, "equal");
        legalOperations.put(NotEquals.class, "notEqual");
        legalOperations.put(In.class, "in");
    }

    @Override
    public Boolean getValue() {
        return (Boolean) value;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }


    public boolean equal(ValueExpression valueExpression) {
        Boolean value = (Boolean) valueExpression.getValue();
        return (Boolean) this.value == value;
    }

    public boolean notEqual(ValueExpression valueExpression) {
        Boolean value = (Boolean) valueExpression.getValue();
        return (Boolean) this.value != value;
    }

    public boolean in(ValueExpression valueExpression) {
        List<Boolean> values = (List<Boolean>) valueExpression.getValue();
        for (Boolean value : values) {
            return (Boolean) this.value == value;
        }
        return false;
    }
}
