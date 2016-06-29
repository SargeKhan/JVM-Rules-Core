package com.jvmrules.expression.operation;

import com.jvmrules.exceptions.ExpressionParseException;
import com.jvmrules.exceptions.InterpretException;
import com.jvmrules.expression.Expression;
import com.jvmrules.expression.Operation;

import java.util.Map;
import java.util.Stack;

public class Not extends OperationExpression implements Operation {
    public Not() {
        super("NOT","Not","not");
    }

    public Not copy() {
        return new Not();
    }

    @Override
    public int parse(String[] tokens, int pos, Stack<Expression> stack,Map<String, Class> types) throws ExpressionParseException
    {
        int i = findNextExpression(tokens, pos+1, stack,types);
        Expression right = stack.pop();

        this.rightOperand = right;
        stack.push(this);

        return i;
    }

    @Override
    public boolean interpret(final Map<String, ?> bindings) throws InterpretException {
        return !this.rightOperand.interpret(bindings);
    }
}