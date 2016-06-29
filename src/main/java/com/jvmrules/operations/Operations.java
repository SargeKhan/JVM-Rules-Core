package com.jvmrules.operations;

import com.jvmrules.expression.operation.OperationExpression;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public enum Operations {
    /**
     * Application of the Singleton pattern using enum
     **/
    INSTANCE;

    private final Map<String, OperationExpression> operations = new HashMap<String, OperationExpression>();

    public void registerOperation(OperationExpression op, String symbol) {
        if (!operations.containsKey(symbol))
            operations.put(symbol, op);
    }

    public void registerOperation(OperationExpression op) {
        for (String symbol : op.getSymbols()) {
            if (!operations.containsKey(symbol))
                operations.put(symbol, op);
        }
    }

    public OperationExpression getOperation(String symbol) {
        return this.operations.get(symbol);
    }

    public Set<String> getDefinedSymbols() {
        return this.operations.keySet();
    }
}
