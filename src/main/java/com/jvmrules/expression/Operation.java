package com.jvmrules.expression;


import com.jvmrules.exceptions.ExpressionParseException;

import java.util.Map;
import java.util.Stack;

public interface Operation extends Expression {

    public Operation copy();

    public int parse(String[] tokens, int pos, Stack<Expression> stack, Map<String, Class> types) throws ExpressionParseException;

    public Integer findNextExpression(String[] tokens, int pos, Stack<Expression> stack, Map<String, Class> types) throws ExpressionParseException;
}
