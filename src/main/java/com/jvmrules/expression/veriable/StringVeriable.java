package com.jvmrules.expression.veriable;


import com.jvmrules.exceptions.InterpretException;
import com.jvmrules.expression.operation.*;
import com.jvmrules.expression.value.ValueExpression;
import com.jvmrules.util.TrimString;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class StringVeriable extends VariableExpression {

    public StringVeriable(String name) {
        this(name, null);
    }

    public StringVeriable(String name, Object value) {
        super(name, StringVeriable.class, value);
        legalOperations.put(Equals.class, "equal");
        legalOperations.put(NotEquals.class, "notEqual");
        legalOperations.put(Less.class, "less");
        legalOperations.put(LessEqual.class, "lessEqual");
        legalOperations.put(Greater.class, "greater");
        legalOperations.put(GreaterEqual.class, "greaterEqual");
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

        if (valueExpression.getType().equals(StringVeriable.class)) {
            if (left.equals(right)) {
                return true;
            }
        } else if (valueExpression.getType().equals(RegexVeriable.class)) {
            if (left.matches(right)) {
                return true;
            }
        }

        return false;
    }

    public boolean notEqual(ValueExpression valueExpression) {
        String left = TrimString.trim((String) this.getValue());
        String right = TrimString.trim((String) valueExpression.getValue());

        if (valueExpression.getType().equals(StringVeriable.class)) {
            if (!left.equals(right)) {
                return true;
            }
        } else if (valueExpression.getType().equals(RegexVeriable.class)) {
            if (!left.matches(right)) {
                return true;
            }
        }

        return false;
    }

    public boolean less(ValueExpression valueExpression) {
        String value = (String) valueExpression.getValue();
        return ((String) this.value).length() < value.length();
    }

    public boolean lessEqual(ValueExpression valueExpression) {
        String value = (String) valueExpression.getValue();
        return ((String) this.value).length() < value.length();
    }

    public boolean greater(ValueExpression valueExpression) {
        String value = (String) valueExpression.getValue();
        return ((String) this.value).length() < value.length();
    }

    public boolean greaterEqual(ValueExpression valueExpression) {
        String value = (String) valueExpression.getValue();
        return ((String) this.value).length() < value.length();
    }

    public boolean in(ValueExpression valueExpression) {
        String left = TrimString.trim((String) this.getValue());
        List<String> values = (List<String>) valueExpression.getValue();
        for (String value : values) {

            String right = TrimString.trim(value);
            if (valueExpression.getType().equals(StringVeriable.class)) {

                if (left.equals(right)) {
                    return true;
                }
            } else if (valueExpression.getType().equals(RegexVeriable.class)) {
                if (left.matches(right)) {
                    return true;
                }
            }
        }
        return false;
    }

}
