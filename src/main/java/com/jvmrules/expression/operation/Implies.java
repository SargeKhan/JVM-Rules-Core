package com.jvmrules.expression.operation;

import com.jvmrules.exceptions.InterpretException;

import java.util.Map;

public class Implies extends OperationExpression {
    public Implies() {
        super("Implies", "implies", "=>");
    }

    public Implies copy() {
        return new Implies();
    }


    @Override
    public boolean interpret(Map<String, ?> bindings) throws InterpretException {


        boolean left = leftOperand.interpret(bindings);
        boolean right = rightOperand.interpret(bindings);

        return !right | left;
    }
}