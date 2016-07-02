package com.jvmrules.expression.veriable;


import com.jvmrules.expression.operation.Equals;
import com.jvmrules.expression.operation.In;
import com.jvmrules.expression.operation.NotEquals;
import com.jvmrules.expression.value.ValueExpression;
import com.jvmrules.util.TrimString;

import java.util.List;

public class RegexVeriable extends VariableExpression {

    public RegexVeriable(String name) {
        this(name, null);
    }

    public RegexVeriable(String name, Object value) {
        super(name, IntegerVeriable.class, value);
        legalOperations.put(Equals.class, "equal");
        legalOperations.put(NotEquals.class, "notEqual");
        legalOperations.put(In.class, "in");
    }

    @Override
    public String getValue() {
        return (String) value;
    }

    @Override
    public void setValue(Object value) {
        this.value = value;
    }


    public boolean equal(ValueExpression valueExpression) {

        String left = TrimString.trim((String) this.getValue());
        String right = TrimString.trim((String) valueExpression.getValue());

        if (!left.matches(right)) {
            return true;
        }

        return false;
    }

    public boolean notEqual(ValueExpression valueExpression) {
        String left = TrimString.trim((String) this.getValue());
        String right = TrimString.trim((String) valueExpression.getValue());

        if (!left.matches(right)) {
            return true;
        }

        return false;
    }

    public boolean in(ValueExpression valueExpression) {
        String left = TrimString.trim((String) this.getValue());
        List<String> values = (List<String>) valueExpression.getValue();
        for (String value : values) {
            String right = TrimString.trim(value);
            if (left.matches(right)) {
                return true;
            }
        }
        return false;
    }

}