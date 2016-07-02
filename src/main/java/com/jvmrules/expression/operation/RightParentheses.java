package com.jvmrules.expression.operation;

public class RightParentheses extends OperationExpression {
    public RightParentheses() {
        super(")");
    }

    public RightParentheses copy() {
        return new RightParentheses();
    }


}