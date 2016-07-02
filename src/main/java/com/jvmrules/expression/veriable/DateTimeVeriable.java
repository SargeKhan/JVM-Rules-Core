package com.jvmrules.expression.veriable;


import com.jvmrules.expression.operation.*;
import com.jvmrules.expression.value.ValueExpression;

import java.util.Date;
import java.util.List;

public class DateTimeVeriable extends VariableExpression {

    public DateTimeVeriable(String name) {
        this(name, null);
    }

    public DateTimeVeriable(String name, Object value) {
        super(name, DateTimeVeriable.class, value);
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
    public Date getValue() {
        return (Date) value;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }

    public boolean equal(ValueExpression valueExpression) {
        Date value = (Date) valueExpression.getValue();
        return ((Date) this.value).compareTo(value) == 0;
    }

    public boolean notEqual(ValueExpression valueExpression) {
        Date value = (Date) valueExpression.getValue();
        return ((Date) this.value).compareTo(value) != 0;
    }


    public boolean less(ValueExpression valueExpression) {
        Date value = (Date) valueExpression.getValue();
        return ((Date) this.value).compareTo(value) < 0;
    }

    public boolean lessEqual(ValueExpression valueExpression) {
        Date value = (Date) valueExpression.getValue();
        return ((Date) this.value).compareTo(value) <= 0;
    }

    public boolean greater(ValueExpression valueExpression) {
        Date value = (Date) valueExpression.getValue();
        return ((Date) this.value).compareTo(value) > 0;
    }

    public boolean greaterEqual(ValueExpression valueExpression) {
        Date value = (Date) valueExpression.getValue();
        return ((Date) this.value).compareTo(value) >= 0;
    }

    public boolean in(ValueExpression valueExpression) {
        List<Date> values = (List<Date>) valueExpression.getValue();
        for (Date value : values) {
            return ((Date) this.value).compareTo(value) == 0;
        }
        return false;
    }

    public boolean between(ValueExpression valueExpression) {
        List<Date> values = (List<Date>) valueExpression.getValue();
        Date lower = values.get(0);
        Date upper = values.get(1);

        if (lower.compareTo(((Date) this.value)) <= 0 && ((Date) this.value).compareTo(upper) <= 0) {
            {
                return true;
            }
        }
        return false;
    }

}
