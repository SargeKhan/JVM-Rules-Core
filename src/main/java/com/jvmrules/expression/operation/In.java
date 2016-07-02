package com.jvmrules.expression.operation;

import com.jvmrules.exceptions.ExpressionParseException;
import com.jvmrules.exceptions.IllegalOperationException;
import com.jvmrules.expression.Expression;
import com.jvmrules.expression.value.ValueExpression;
import com.jvmrules.expression.value.ValueType;
import com.jvmrules.expression.veriable.VariableExpression;
import com.jvmrules.expression.veriable.VeriableType;
import com.jvmrules.util.TokenString;
import com.jvmrules.util.ValueDataType;

import java.util.Collection;
import java.util.Map;
import java.util.Stack;

public class In extends EqualityOperationExpression {
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

            Class type = types.get(variableName);
            String valueString = tokens[pos + 1];
            if (type == null) {
                type = type = ValueDataType.computeType(valueString);
            }
            VariableExpression left = VeriableType.getVariableType(variableName, type);
            ValueExpression right = ValueType.getValueType(valueString, variableName, type);


            this.leftOperand = left;

            this.rightOperand = right;


            if (left.isLegalOperations(this.getClass())) {
                if (right == null || !(right.getValue() instanceof Collection)) {

                    String message = String.format("List Operation %s not possible on type %s at %s", this.getClass().getSimpleName(), right.getValue().getClass().getSimpleName(), TokenString.tokenToString(tokens, pos));
                    logger.error(message);
                    throw new IllegalOperationException(message);

                }
            } else {
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

}