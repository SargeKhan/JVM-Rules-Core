package com.jvmrules.expression.veriable;


import com.jvmrules.exceptions.IllegalPropertyException;
import com.jvmrules.exceptions.IllegalPropertyValueException;
import com.jvmrules.expression.Expression;
import com.jvmrules.expression.NamedExpression;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class VeriableType {
    static Logger logger = LoggerFactory.getLogger(VeriableType.class);

    public static Expression getVariableType(String name, Map<String, Class> types) throws IllegalPropertyException {
        VariableExpression variableExpression = null;

        if (name == null)
            throw new IllegalPropertyException("The provided string must not be null");
        Class type = types.get(name);
        if (type == null) {
            NamedExpression namedExpression = new NamedExpression(name);
            logger.debug("Named Expression : {} [{}]", namedExpression.getClass().getSimpleName(), namedExpression.getName());
            return namedExpression;
        }

        if (type.equals(BooleanVeriable.class)) {
            variableExpression = new BooleanVeriable(name);
        } else if (type.equals(FloatVeriable.class)) {
            variableExpression = new FloatVeriable(name);
        } else if (type.equals(IntegerVeriable.class)) {
            variableExpression = new IntegerVeriable(name);
        } else if (type.equals(StringVeriable.class)) {
            variableExpression = new StringVeriable(name);
        }

        logger.debug("Variable  Expression : {} [{}]", variableExpression.getClass().getSimpleName(), variableExpression.getName());
        return variableExpression;
    }


    public static VariableExpression getVariableType(NamedExpression namedExpression, Map<String, ?> bindings) throws IllegalPropertyValueException{

        Object object = bindings.get(namedExpression.getName());
        if (object == null)
            return null;

        String value = (String) object;
        Class type = computeType(value);


        if (type.equals(BooleanVeriable.class)) {
            return new BooleanVeriable(namedExpression.getName(), Boolean.getBoolean(value));
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
            variable.setValue(Boolean.getBoolean(value));
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
        }


        return variable;
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
