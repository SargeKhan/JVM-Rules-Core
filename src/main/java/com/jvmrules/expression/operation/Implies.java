package com.jvmrules.expression.operation;

import com.jvmrules.exceptions.InterpretException;
import com.jvmrules.expression.Operation;

import java.util.Map;

public class Implies extends OperationExpression implements Operation {
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