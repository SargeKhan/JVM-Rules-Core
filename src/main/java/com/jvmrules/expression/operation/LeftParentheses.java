package com.jvmrules.expression.operation;

import com.jvmrules.exceptions.ExpressionParseException;
import com.jvmrules.expression.Expression;
import com.jvmrules.expression.Operation;
import com.jvmrules.operations.Operations;

import java.util.Map;
import java.util.Stack;

public class LeftParentheses extends OperationExpression implements Operation {
    public LeftParentheses() {
        super("(");
    }

    public LeftParentheses copy() {
        return new LeftParentheses();
    }


    @Override
    public int parse(String[] tokens, int pos, Stack<Expression> stack, Map<String, Class> types) throws ExpressionParseException{

        int i = findNextExpression(tokens, pos + 1, stack, types);

        while (!stack.peek().getClass().equals(RightParentheses.class)) {
            i = findNextExpression(tokens, i, stack, types);
        }

        if (stack.peek().getClass().equals(RightParentheses.class)) {
            stack.pop();
        }

        return i;
    }

    @Override
    public Integer findNextExpression(String[] tokens, int pos, Stack<Expression> stack, Map<String, Class> types) throws ExpressionParseException {
        Operations operations = Operations.INSTANCE;

        for (int i = pos; i < tokens.length; i++) {

            Operation op = operations.getOperation(tokens[i]);
            if (op != null) {
                op = op.copy();
                // we found an operation

                if (!op.getClass().equals(RightParentheses.class)) {
                    i = op.parse(tokens, i, stack, types);
                } else {
                    stack.push(op);
                    i++;
                }

                return i;
            }
        }
        return null;
    }

}