package com.jvmrules.expression.veriable;

import com.jvmrules.exceptions.InterpretException;
import com.jvmrules.expression.Expression;
import com.jvmrules.expression.operation.OperationExpression;
import com.jvmrules.expression.value.ValueExpression;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class VariableExpression<T> extends Expression {
    protected final Map<Class, String> legalOperations = new HashMap<Class, String>();
    protected String name;
    protected Class<T> type;
    protected T value;

    public VariableExpression(String name, Class<T> type, T value) {
        this.name = name;
        this.type = type;
        this.value = value;
    }

    public VariableExpression(String name, Class<T> type) {
        this(name, type, null);
    }

    public String getName() {
        return this.name;
    }

    public Class<T> getType() {
        return type;
    }

    public abstract T getValue();

    public abstract void setValue(T value);

    public boolean interpret(Map<String, ?> bindings) {
        return true;
    }

    public List<?> legalOperations() {

        return Arrays.asList(legalOperations.keySet().toArray());
    }

    public boolean isLegalOperations(Class operation) {
        if (operation == null) {
            return false;
        }
        return legalOperations.containsKey(operation);
    }

    public Boolean interpret(OperationExpression operationExpression, ValueExpression valueExpression) throws InterpretException {

        String operation = legalOperations.get(operationExpression.getClass());
        try {

            for ( Method method:this.getClass().getMethods())
            {
                if (method.getName().equals(operation))
                {
                    return (Boolean) method.invoke(this, valueExpression);
                }
            }

           // Method method = this.getClass().getMethod(operation, ValueExpression.class);

       // } catch (NoSuchMethodException m) {
       //     throw new InterpretException(m);
        } catch (IllegalAccessException m) {
            throw new InterpretException(m);
        } catch (InvocationTargetException m) {
            throw new InterpretException(m);
        }

        return false;
    }



}
