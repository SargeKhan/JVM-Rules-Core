package com.jvmrules.api;


import com.jvmrules.expression.operation.*;
import com.jvmrules.operations.Operations;
import com.jvmrules.rule.evaluator.RuleEvaluator;

public class RuleFactory {

    private static RuleFactory instance;
    public static RuleFactory INSTANCE = instance();
    public RuleEvaluator Evaluator = new RuleEvaluator();
    private Operations operations = Operations.INSTANCE;

    public RuleFactory() {
        initilize();
    }

    private static RuleFactory instance() {
        if (instance == null) {
            instance = new RuleFactory();
        }
        return instance;
    }

    private void initilize() {


        operations.registerOperation(new And());
        operations.registerOperation(new OR());
        operations.registerOperation(new Not());
        operations.registerOperation(new Implies());

        operations.registerOperation(new Equals());
        operations.registerOperation(new NotEquals());
        operations.registerOperation(new Less());
        operations.registerOperation(new LessEqual());
        operations.registerOperation(new Greater());
        operations.registerOperation(new GreaterEqual());

        operations.registerOperation(new In());
        operations.registerOperation(new Between());

        operations.registerOperation(new LeftParentheses());
        operations.registerOperation(new RightParentheses());
    }

    public void registerOperations(OperationExpression operation) {
        operations.registerOperation(operation);
    }

}
