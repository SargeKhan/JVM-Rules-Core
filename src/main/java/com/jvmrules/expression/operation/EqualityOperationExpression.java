package com.jvmrules.expression.operation;

import com.jvmrules.exceptions.ExpressionParseException;
import com.jvmrules.exceptions.IllegalOperationException;
import com.jvmrules.exceptions.InterpretException;
import com.jvmrules.expression.Expression;
import com.jvmrules.expression.NamedExpression;
import com.jvmrules.expression.value.ValueExpression;
import com.jvmrules.expression.value.ValueType;
import com.jvmrules.expression.veriable.VariableExpression;
import com.jvmrules.expression.veriable.VeriableType;
import com.jvmrules.util.TokenString;
import com.jvmrules.util.ValueDataType;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public abstract class EqualityOperationExpression extends OperationExpression {


    public EqualityOperationExpression(String symbol) {
        this.symbols.add(symbol);
    }

    public EqualityOperationExpression(List<String> symbols) {
        this.symbols.addAll(symbols);
    }

    public EqualityOperationExpression(String... symbols) {
        this.symbols.addAll(Arrays.asList(symbols));
    }

    @Override
    public int parse(final String[] tokens, int pos, Stack<Expression> stack, Map<String, Class> types) throws ExpressionParseException {
        if (pos - 1 >= 0 && tokens.length >= pos + 1) {
            String variableName = tokens[pos - 1];

            Class type = types.get(variableName);
            String valueString = tokens[pos + 1];
            if (type == null) {
                type = type = ValueDataType.computeType(valueString);
            }
            VariableExpression left = VeriableType.getVariableType(variableName, type);
            ValueExpression right = ValueType.getValueType(valueString, variableName, type);


            this.leftOperand = left;

            this.rightOperand = right;


            if (!left.isLegalOperations(this.getClass())) {
                String message = String.format("Operation %s not possible on type %s at %s", this.getClass().getSimpleName(), right.getType().getClass().getSimpleName(), TokenString.tokenToString(tokens, pos));
                logger.error(message);
                throw new IllegalOperationException(message);
            }

            logger.debug("Operation Call Expression : {}", getClass().getSimpleName());

            stack.push(this);
            return pos + 1;
        }
        throw new ExpressionParseException("Cannot assign value to variable");
    }

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
        return variable.interpret(this, valueExpression);
    }
}