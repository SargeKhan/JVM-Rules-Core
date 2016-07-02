package com.jvmrules.expression.operation;

import com.jvmrules.exceptions.InterpretException;

import java.util.Map;

public class OR extends OperationExpression {
    public OR() {
        super("OR", "or", "||");
    }

    public OR copy() {

        return new OR();
    }


    @Override
    public boolean interpret(Map<String, ?> bindings) throws InterpretException {
        boolean left = leftOperand.interpret(bindings);
        boolean right = rightOperand.interpret(bindings);

        return left | right;
    }
}