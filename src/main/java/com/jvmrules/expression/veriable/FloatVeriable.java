package com.jvmrules.expression.veriable;


import com.jvmrules.expression.operation.*;
import com.jvmrules.expression.value.ValueExpression;

import java.util.List;

public class FloatVeriable extends VariableExpression {

    public FloatVeriable(String name) {
        this(name, null);

    }

    public FloatVeriable(String name, Object value) {
        super(name, FloatVeriable.class, value);
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
    public Float getValue() {
        return (Float) value;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }


    public boolean equal(ValueExpression valueExpression) {
        Float value = (Float) valueExpression.getValue();
        return (Float) this.value == value;
    }

    public boolean notEqual(ValueExpression valueExpression) {
        Float value = (Float) valueExpression.getValue();
        return (Float) this.value != value;
    }


    public boolean less(ValueExpression valueExpression) {
        Float value = (Float) valueExpression.getValue();
        return (Float) this.value < value;
    }

    public boolean lessEqual(ValueExpression valueExpression) {
        Float value = (Float) valueExpression.getValue();
        return (Float) this.value <= value;
    }

    public boolean greater(ValueExpression valueExpression) {
        Float value = (Float) valueExpression.getValue();
        return (Float) this.value > value;
    }

    public boolean greaterEqual(ValueExpression valueExpression) {
        Float value = (Float) valueExpression.getValue();
        return (Float) this.value >= value;
    }

    public boolean in(ValueExpression valueExpression) {
        List<Float> values = (List<Float>) valueExpression.getValue();
        for (Float value : values) {
            return (Float) this.value == value;
        }
        return false;
    }

    public boolean between(ValueExpression valueExpression) {
        List<Float> values = (List<Float>) valueExpression.getValue();
        Float lower = values.get(0);
        Float upper = values.get(1);

        if (lower <= (Float) this.value && (Float) this.value <= upper) {
            {
                return true;
            }
        }
        return false;
    }

    public Float sum(ValueExpression valueExpression) {
        Float value = (Float) valueExpression.getValue();
        return (Float) this.value + value;
    }

    public Float min(ValueExpression valueExpression) {
        Float value = (Float) valueExpression.getValue();
        return (Float) this.value > value ? (Float) this.value : value;
    }

    public Float max(ValueExpression valueExpression) {
        Float value = (Float) valueExpression.getValue();
        return (Float) this.value < value ? (Float) this.value : value;
    }
}