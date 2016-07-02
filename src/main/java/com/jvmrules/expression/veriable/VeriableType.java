package com.jvmrules.expression.veriable;


import com.jvmrules.exceptions.IllegalPropertyException;
import com.jvmrules.exceptions.IllegalPropertyValueException;
import com.jvmrules.expression.Expression;
import com.jvmrules.expression.NamedExpression;
import com.jvmrules.util.ValueDataType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class VeriableType {
    static Logger logger = LoggerFactory.getLogger(VeriableType.class);
    static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
    static SimpleDateFormat datTimeFormat = new SimpleDateFormat("dd-MM-yyyy-HH:mm:ss");

    public static VariableExpression getVariableType(String name,Class type ) throws IllegalPropertyException {
        VariableExpression variableExpression = null;

        if (name == null)
            throw new IllegalPropertyException("The provided string must not be null");

        if (type == null) {
            throw new IllegalPropertyException("The provided type must not be null");
        }

        if (type.equals(BooleanVeriable.class)) {
            variableExpression = new BooleanVeriable(name);
        } else if (type.equals(FloatVeriable.class)) {
            variableExpression = new FloatVeriable(name);
        } else if (type.equals(IntegerVeriable.class)) {
            variableExpression = new IntegerVeriable(name);
        } else if (type.equals(StringVeriable.class)) {
            variableExpression = new StringVeriable(name);
        } else if (type.equals(DateTimeVeriable.class)) {
            variableExpression = new DateTimeVeriable(name);
        } else if (type.equals(DateVeriable.class)) {
            variableExpression = new DateVeriable(name);
        } else if (type.equals(RegexVeriable.class)) {
            variableExpression = new StringVeriable(name);
        } else {
            String message = String.format("Type of Variable Expression {} not found", name);
            logger.error(message);
            throw new IllegalPropertyException(message);
        }

        logger.debug("Variable  Expression : {} [{}]", variableExpression.getClass().getSimpleName(), variableExpression.getName());
        return variableExpression;
    }


    public static VariableExpression getVariableType(NamedExpression namedExpression, Map<String, ?> bindings) throws IllegalPropertyValueException {

        Object object = bindings.get(namedExpression.getName());
        if (object == null)
            return null;

        String value = (String) object;
        Class type = ValueDataType.computeType(value);


        if (type.equals(BooleanVeriable.class)) {
            return new BooleanVeriable(namedExpression.getName(), Boolean.parseBoolean(value));
        } else if (type.equals(FloatVeriable.class)) {
            return new FloatVeriable(namedExpression.getName(), Float.parseFloat(value));
        } else if (type.equals(IntegerVeriable.class)) {
            try {
                return new FloatVeriable(namedExpression.getName(), Float.parseFloat(value));
            } catch (NumberFormatException e) {
                String message = String.format("Unable to Parse value %s to Float", value);
                logger.error(message);
                throw new IllegalPropertyValueException(message);
            }
        } else if (type.equals(StringVeriable.class)) {
            try {
                return new IntegerVeriable(namedExpression.getName(), Integer.parseInt(value));
            } catch (NumberFormatException e) {
                String message = String.format("Unable to Parse value %s to Integer", value);
                logger.error(message);
                throw new IllegalPropertyValueException(message);
            }
        }

        return null;

    }

    public static VariableExpression setVeriableValue(VariableExpression variable, Map<String, ?> bindings) throws IllegalPropertyValueException {

        Object object = bindings.get(variable.getName());
        if (object == null)
            return null;
        String value = (String) object;

        if (variable.getType().equals(BooleanVeriable.class)) {
            variable.setValue(Boolean.parseBoolean(value));
        } else if (variable.getType().equals(FloatVeriable.class)) {
            try {
                variable.setValue(Float.parseFloat(value));

            } catch (NumberFormatException e) {
                String message = String.format("Unable to Parse value %s to Float", value);
                logger.error(message);
                throw new IllegalPropertyValueException(message);
            }
        } else if (variable.getType().equals(IntegerVeriable.class)) {
            try {
                variable.setValue(Integer.parseInt(value));
            } catch (NumberFormatException e) {
                String message = String.format("Unable to Parse value %s to Integer", value);
                logger.error(message);
                throw new IllegalPropertyValueException(message);
            }
        } else if (variable.getType().equals(StringVeriable.class)) {
            variable.setValue(value);
        } else if (variable.getType().equals(DateVeriable.class)) {

            value = value.trim();
            String dateString = "\\[\\s*(0[1-9]|1[0-9]|2[0-9]|3[0-1])-(0[1-9]|1[0-2])-([1-9][0-9][0-9][0-9])\\s*\\]";
            String dateString2 = "\\s*(0[1-9]|1[0-9]|2[0-9]|3[0-1])-(0[1-9]|1[0-2])-([1-9][0-9][0-9][0-9])\\s*";
            String valueString = null;
            if (value.matches(dateString)) {
                valueString = value.substring(1, value.length() - 1).trim();
            } else if (value.matches(dateString2)) {
                valueString = value;
            }
            Date date = null;
            try {
                date = dateFormat.parse(valueString);
            } catch (ParseException e) {
                String message = String.format("Unable to Parse value %s to Date", valueString);
                logger.error(message);
                throw new IllegalPropertyValueException(message);

            }
            variable.setValue(date);
        } else if (variable.getType().equals(DateTimeVeriable.class)) {
            String valueString = value.substring(1, value.length() - 1).trim();

            Date date = null;
            try {
                date = datTimeFormat.parse(valueString);
            } catch (ParseException e) {
                String message = String.format("Unable to Parse value %s to Date Time", valueString);
                logger.error(message);
                throw new IllegalPropertyValueException(message);
            }
            variable.setValue(date);
        }


        return variable;
    }


}
