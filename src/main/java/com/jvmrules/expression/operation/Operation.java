package com.jvmrules.expression.operation;


import com.jvmrules.exceptions.ExpressionParseException;
import com.jvmrules.expression.Expression;

import java.util.Map;
import java.util.Stack;

public abstract class Operation extends Expression {

    public abstract Operation copy();

    public abstract int parse(String[] tokens, int pos, Stack<Expression> stack, Map<String, Class> types) throws ExpressionParseException;

    public abstract Integer findNextExpression(String[] tokens, int pos, Stack<Expression> stack, Map<String, Class> types) throws ExpressionParseException;
}
