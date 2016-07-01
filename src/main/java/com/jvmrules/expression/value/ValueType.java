package com.jvmrules.expression.value;

import com.jvmrules.exceptions.IllegalValueException;
import com.jvmrules.expression.veriable.*;
import com.jvmrules.util.ValueDataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class ValueType {
    static Logger logger = LoggerFactory.getLogger(ValueType.class);

    public static ValueExpression<?> getValueType(String valueString, String name, Map<String, Class> types) throws IllegalValueException {
        if (valueString == null)
            throw new IllegalValueException("The provided string must not be null");

        Class type = null;

        if (name != null && !name.isEmpty()) {
            type = types.get(name);
            if (type == null) {
                type = ValueDataType.computeType(valueString);
            }
        } else {
            type = ValueDataType.computeType(valueString);
        }

        ValueExpression valueExpression = null;

        if (valueString.contains("{") && valueString.contains("}")) {
            valueString = valueString.substring(valueString.indexOf("{") + 1, valueString.indexOf("}")).trim();
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


                    for (String value : values) {
                        try {
                            list.add(Float.parseFloat(value));
                        } catch (NumberFormatException e) {
                            String message = String.format("Unable to Parse value %s to Float", value);
                            logger.error(message);
                            throw new IllegalValueException(message);
                        }
                    }

                    valueExpression = new ValueExpression<List<Float>>(list, FloatVeriable.class);

                } else if (type.equals(IntegerVeriable.class)) {
                    List<Integer> list = new ArrayList<>();
                    for (String value : values) {
                        try {
                            list.add(Integer.parseInt(value));
                        } catch (NumberFormatException e) {
                            String message = String.format("Unable to Parse value %s to Integer", value);
                            logger.error(message);
                            throw new IllegalValueException(message);
                        }
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
            logger.debug("List Value Expression : {} [{}]", valueExpression.getType().getSimpleName(), valueExpression.getValue());
        } else {


            if (type.equals(BooleanVeriable.class)) {
                valueExpression = new ValueExpression<Boolean>(Boolean.getBoolean(valueString), BooleanVeriable.class);
            } else if (type.equals(FloatVeriable.class)) {
                try {
                    valueExpression = new ValueExpression<Float>(Float.parseFloat(valueString), FloatVeriable.class);
                } catch (NumberFormatException e) {
                    String message = String.format("Unable to Parse value %s to Float", valueString);
                    logger.error(message);
                    throw new IllegalValueException(message);
                }
            } else if (type.equals(IntegerVeriable.class)) {

                try {
                    valueExpression = new ValueExpression<Integer>(Integer.parseInt(valueString), IntegerVeriable.class);
                } catch (NumberFormatException e) {
                    String message = String.format("Unable to Parse value %s to Integer", valueString);
                    logger.error(message);
                    throw new IllegalValueException(message);
                }
            } else if (type.equals(StringVeriable.class)) {

                valueExpression = new ValueExpression<String>(valueString, StringVeriable.class);
            }else if (type.equals(RegexVeriable.class)) {

                valueExpression = new ValueExpression<String>(valueString, RegexVeriable.class);
            } else if (type.equals(DateVeriable.class)) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                String value = valueString.substring(2, valueString.length() - 1).trim();
                Date date = null;
                try {
                    date = dateFormat.parse(value);
                } catch (ParseException e) {
                    String message = String.format("Unable to Parse value %s to Date", valueString);
                    logger.error(message);
                    throw new IllegalValueException(message);

                }
                valueExpression = new ValueExpression<Date>(date, DateVeriable.class);
            } else if (type.equals(DateTimeVeriable.class)) {
                String value = valueString.substring(2, valueString.length() - 1).trim();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                Date date = null;
                try {
                    date = dateFormat.parse(value);
                } catch (ParseException e) {
                    String message = String.format("Unable to Parse value %s to Date", valueString);
                    logger.error(message);
                    throw new IllegalValueException(message);
                }
                valueExpression = new ValueExpression<Date>(null, DateTimeVeriable.class);
            }


            logger.debug("Value Expression : {} [{}]", valueExpression.getType().getSimpleName(), valueExpression.getValue());
        }

        return valueExpression;
    }


}
