package com.jvmrules.expression.value;

import com.jvmrules.exceptions.IllegalValueException;
import com.jvmrules.expression.veriable.BooleanVeriable;
import com.jvmrules.expression.veriable.FloatVeriable;
import com.jvmrules.expression.veriable.IntegerVeriable;
import com.jvmrules.expression.veriable.StringVeriable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ValueType {
    static Logger logger = LoggerFactory.getLogger(ValueType.class);

    public static ValueExpression<?> getValueType(String valueString, String name, Map<String, Class> types) throws IllegalValueException{
        if (valueString == null)
            throw new IllegalValueException("The provided string must not be null");

        Class type = null;

        if (name != null && !name.isEmpty()) {
            type = types.get(name);
            if (type == null) {
                type = computeType(valueString);
            }
        } else {
            type = computeType(valueString);
        }

        ValueExpression valueExpression =null;

        if (valueString.contains("{") && valueString.contains("}")) {
            valueString = valueString.substring(valueString.indexOf("{")+1, valueString.indexOf("}")).trim();
            if (!valueString.isEmpty()) {
                String values[] = valueString.split(",");


                if (type.equals(BooleanVeriable.class)) {

                    List<Boolean> list = new ArrayList<>();
                    for (String value : values) {
                        list.add(Boolean.getBoolean(value));
                    }
                    valueExpression = new ValueExpression<List<Boolean>>(list, BooleanVeriable.class);
                } else if (type.equals(FloatVeriable.class)) {
                    List<Float> list = new ArrayList<>();
                    try {


                        for (String value : values) {
                            list.add(Float.parseFloat(value));
                        }
                    } catch (NumberFormatException e) {
                    }
                    valueExpression = new ValueExpression<List<Float>>(list, FloatVeriable.class);

                } else if (type.equals(IntegerVeriable.class)) {
                    List<Integer> list = new ArrayList<>();
                    try {
                        for (String value : values) {
                            list.add(Integer.parseInt(value));
                        }
                    } catch (NumberFormatException e) {
                    }
                    valueExpression = new ValueExpression<List<Integer>>(list, IntegerVeriable.class);
                } else if (type.equals(StringVeriable.class)) {

                    List<String> list = new ArrayList<>();
                    for (String value : values) {
                        list.add(value);
                    }
                    valueExpression = new ValueExpression<List<String>>(list, StringVeriable.class);
                }
            }
            logger.debug("List Value Expression : {} [{}]", valueExpression.getType().getSimpleName(),valueExpression.getValue());
        } else {


            if (type.equals(BooleanVeriable.class)) {
                valueExpression = new ValueExpression<Boolean>(Boolean.getBoolean(valueString), BooleanVeriable.class);
            } else if (type.equals(FloatVeriable.class)) {
                try {
                    valueExpression = new ValueExpression<Float>(Float.parseFloat(valueString), FloatVeriable.class);
                } catch (NumberFormatException e) {
                }
            } else if (type.equals(IntegerVeriable.class)) {

                try {
                    valueExpression = new ValueExpression<Integer>(Integer.parseInt(valueString), IntegerVeriable.class);
                } catch (NumberFormatException e) {
                }
            } else if (type.equals(StringVeriable.class)) {

                valueExpression = new ValueExpression<String>(valueString, StringVeriable.class);
            }


             logger.debug("Value Expression : {} [{}]",valueExpression.getType().getSimpleName(),valueExpression.getValue());
        }

        return valueExpression;
    }


    private static Class computeType(String value) {
        if ("true".equals(value) || "false".equals(value))
            return BooleanVeriable.class;
        else if (value.startsWith("'"))
            return StringVeriable.class;
        else if (value.contains(".")) {
            try {
                return FloatVeriable.class;
            } catch (NumberFormatException e) {
            }
        } else {
            try {
                return IntegerVeriable.class;
            } catch (NumberFormatException e) {
            }
        }

        return StringVeriable.class;
    }
}
