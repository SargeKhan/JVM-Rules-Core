package com.jvmrules.expression.operation;

import com.jvmrules.exceptions.InterpretException;
import com.jvmrules.expression.Operation;

import java.util.Map;

public class And extends OperationExpression implements Operation {
    public And() {
        super("AND", "and", "&&");
    }

    public And copy() {
        return new And();
    }


    @Override
    public boolean interpret(Map<String, ?> bindings) throws InterpretException {
        boolean left = leftOperand.interpret(bindings);
        boolean right = rightOperand.interpret(bindings);

        return left & right;
    }
}