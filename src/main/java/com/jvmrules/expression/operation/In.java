package com.jvmrules.expression.operation;

import com.jvmrules.exceptions.ExpressionParseException;
import com.jvmrules.exceptions.IllegalOperationException;
import com.jvmrules.exceptions.InterpretException;
import com.jvmrules.expression.Expression;
import com.jvmrules.expression.NamedExpression;
import com.jvmrules.expression.Operation;
import com.jvmrules.expression.value.ValueExpression;
import com.jvmrules.expression.value.ValueType;
import com.jvmrules.expression.veriable.*;
import com.jvmrules.util.TokenString;
import com.jvmrules.util.TrimString;

import java.util.*;

public class In extends OperationExpression implements Operation {
    public In() {
        super("IN", "In", "in");
    }

    public In copy() {
        return new In();
    }

    @Override
    public int parse(final String[] tokens, int pos, Stack<Expression> stack, Map<String, Class> types) throws ExpressionParseException {
        if (pos - 1 >= 0 && tokens.length >= pos + 1) {
            String variableName = tokens[pos - 1];

            Expression left = VeriableType.getVariableType(variableName, types);
            ValueExpression right = ValueType.getValueType(tokens[pos + 1], variableName, types);

            if (right == null || !(right.getValue() instanceof Collection)) {

                String message = String.format("List Operation %s not possible on type %s at %s", this.getClass().getSimpleName(), right.getValue().getClass().getSimpleName(), TokenString.tokenToString(tokens, pos));
                logger.error(message);
                throw new IllegalOperationException(message);

            }

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
            } else if (variable.getType().equals(IntegerVeriable.class)) {
                Integer left = (Integer) variable.getValue();
                List<Integer> values = (List<Integer>) valueExpression.getValue();
                for (Integer right : values) {
                    if (left.equals(right)) {
                        return true;
                    }
                }
            } else if (variable.getType().equals(FloatVeriable.class)) {
                Float left = (Float) variable.getValue();
                List<Float> values = (List<Float>) valueExpression.getValue();
                for (Float right : values) {
                    if (left.equals(right)) {
                        return true;
                    }
                }
            } else if (variable.getType().equals(BooleanVeriable.class)) {
                Boolean left = (Boolean) variable.getValue();
                List<Boolean> values = (List<Boolean>) valueExpression.getValue();
                for (Boolean right : values) {
                    if (left.equals(right)) {
                        return true;
                    }
                }
            } else if (variable.getType().equals(DateTimeVeriable.class) || variable.getType().equals(DateVeriable.class)) {
                Date left = (Date) variable.getValue();
                List<Date> values = (List<Date>) valueExpression.getValue();
                for (Date right : values) {
                    if (left.compareTo(right) == 0) {
                        return true;
                    }
                }

            }
            

        return false;
    }

}