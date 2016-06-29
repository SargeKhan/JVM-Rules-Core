package com.jvmrules.expression.operation;

import com.jvmrules.expression.Operation;

public class RightParentheses extends OperationExpression implements Operation {
    public RightParentheses() {
        super(")");
    }

    public RightParentheses copy() {
        return new RightParentheses();
    }


}