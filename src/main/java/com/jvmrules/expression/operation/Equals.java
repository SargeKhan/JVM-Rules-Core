package com.jvmrules.expression.operation;

import com.jvmrules.exceptions.ExpressionParseException;
import com.jvmrules.exceptions.InterpretException;
import com.jvmrules.expression.Expression;
import com.jvmrules.expression.NamedExpression;
import com.jvmrules.expression.Operation;
import com.jvmrules.expression.value.ValueExpression;
import com.jvmrules.expression.value.ValueType;
import com.jvmrules.expression.veriable.*;
import com.jvmrules.util.TrimString;

import java.util.Date;
import java.util.Map;
import java.util.Stack;

public class Equals extends OperationExpression implements Operation {
    public Equals() {
        super("Equal", "equal", "=");
    }

    public Equals copy() {
        return new Equals();
    }

    @Override
    public int parse(final String[] tokens, int pos, Stack<Expression> stack, Map<String, Class> types) throws ExpressionParseException {
        if (pos - 1 >= 0 && tokens.length >= pos + 1) {
            String variableName = tokens[pos - 1];

            Expression left = VeriableType.getVariableType(variableName, types);
            Expression right = ValueType.getValueType(tokens[pos + 1], variableName, types);


            this.leftOperand = left;

            this.rightOperand = right;

            logger.debug("Operation Call Expression : {}", getClass().getSimpleName());

            stack.push(this);
            return pos + 1;
        }
        throw new ExpressionParseException("Cannot assign value to variable");
    }

    @Override
    public boolean interpret(Map<String, ?> bindings) throws InterpretException {


        VariableExpression variable = null;

        if (this.leftOperand instanceof NamedExpression) {
            variable = VeriableType.getVariableType((NamedExpression) this.leftOperand, bindings);
        } else if (this.leftOperand instanceof VariableExpression) {
            variable = (VariableExpression) this.leftOperand;
            variable = VeriableType.setVeriableValue(variable, bindings);
        }

        if (variable == null)
            return false;

        ValueExpression<?> valueExpression = (ValueExpression<?>) this.rightOperand;


        if (variable.getType().equals(StringVeriable.class)) {
            String left = TrimString.trim((String) variable.getValue());
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

        } else if (variable.getType().equals(IntegerVeriable.class)) {
            Integer left = (Integer) variable.getValue();
            Integer right = (Integer) valueExpression.getValue();
            if (left.equals(right)) {
                return true;
            }
        } else if (variable.getType().equals(FloatVeriable.class)) {
            Float left = (Float) variable.getValue();
            Float right = (Float) valueExpression.getValue();
            if (left.equals(right)) {
                return true;
            }
        } else if (variable.getType().equals(BooleanVeriable.class)) {
            Boolean left = (Boolean) variable.getValue();
            Boolean right = (Boolean) valueExpression.getValue();
            if (left.equals(right)) {
                return true;
            }
        }else if (variable.getType().equals(DateTimeVeriable.class)||variable.getType().equals(DateVeriable.class)){
            Date left = (Date) variable.getValue();
            Date right = (Date) valueExpression.getValue();
            if (left.compareTo(right)==0) {
                return true;
            }
        }

        return false;
    }
}