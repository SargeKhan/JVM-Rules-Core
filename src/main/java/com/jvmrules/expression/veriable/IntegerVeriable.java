package com.jvmrules.expression.veriable;

import com.jvmrules.expression.operation.*;
import com.jvmrules.expression.value.ValueExpression;

import java.util.List;

public class IntegerVeriable extends VariableExpression {


    public IntegerVeriable(String name) {
        this(name, null);
    }

    public IntegerVeriable(String name, Object value) {
        super(name, IntegerVeriable.class, value);
        legalOperations.put(Equals.class, "equal");
        legalOperations.put(NotEquals.class, "notEqual");
        legalOperations.put(Less.class, "less");
        legalOperations.put(LessEqual.class, "lessEqual");
        legalOperations.put(Greater.class, "greater");
        legalOperations.put(GreaterEqual.class, "greaterEqual");
        legalOperations.put(In.class, "in");
        legalOperations.put(Between.class, "between");
    }

    @Override
    public Integer getValue() {
        return (Integer) value;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }


    public boolean equal(ValueExpression valueExpression) {
        Integer value = (Integer) valueExpression.getValue();
        return (Integer) this.value == value;
    }

    public boolean notEqual(ValueExpression valueExpression) {
        Integer value = (Integer) valueExpression.getValue();
        return (Integer) this.value != value;
    }


    public boolean less(ValueExpression valueExpression) {
        Integer value = (Integer) valueExpression.getValue();
        return (Integer) this.value < value;
    }

    public boolean lessEqual(ValueExpression valueExpression) {
        Integer value = (Integer) valueExpression.getValue();
        return (Integer) this.value <= value;
    }

    public boolean greater(ValueExpression valueExpression) {
        Integer value = (Integer) valueExpression.getValue();
        return (Integer) this.value > value;
    }

    public boolean greaterEqual(ValueExpression valueExpression) {
        Integer value = (Integer) valueExpression.getValue();
        return (Integer) this.value >= value;
    }

    public boolean in(ValueExpression valueExpression) {
        List<Integer> values = (List<Integer>) valueExpression.getValue();
        for (Integer value : values) {
            return (Integer) this.value == value;
        }
        return false;
    }

    public boolean between(ValueExpression valueExpression) {
        List<Integer> values = (List<Integer>) valueExpression.getValue();
        Integer lower = values.get(0);
        Integer upper = values.get(1);

        if (lower <= (Integer) this.value && (Integer) this.value <= upper) {
            {
                return true;
            }
        }
        return false;
    }

    public Integer sum(ValueExpression valueExpression) {
        Integer value = (Integer) valueExpression.getValue();
        return (Integer) this.value + value;
    }

    public Integer min(ValueExpression valueExpression) {
        Integer value = (Integer) valueExpression.getValue();
        return (Integer) this.value > value ? (Integer) this.value : value;
    }

    public Integer max(ValueExpression valueExpression) {
        Integer value = (Integer) valueExpression.getValue();
        return (Integer) this.value < value ? (Integer) this.value : value;
    }
}