package com.jvmrules.rule.evaluator;


import com.jvmrules.action.NullActionDispatcher;
import com.jvmrules.exceptions.InterpretException;
import com.jvmrules.expression.Expression;
import com.jvmrules.rule.Rule;
import com.jvmrules.rule.RuleRegistry;

import java.util.HashMap;
import java.util.Map;

public class RuleEvaluator {

    public boolean evaluate(Rule rule, Map<String, ?> bindings) {
        boolean result = false;
        try {
            result = rule.getExpression().interpret(bindings);
            if (result)
                rule.getDispatcher().fire();
        } catch (InterpretException e) {
            e.printStackTrace();
        }
        return result;
    }

    public boolean evaluate(Expression expression, Map<String, ?> bindings) {
        boolean result = false;
        try {
            result = result = expression.interpret(bindings);
            if (result)
                new NullActionDispatcher().fire();
        } catch (InterpretException e) {
            e.printStackTrace();
        }
        return result;
    }

    public HashMap<Expression, Boolean> evaluate(RuleRegistry factory, Map<String, ?> bindings) {

        HashMap<Expression, Boolean> results = new HashMap<>();

        for (Expression expression : factory.getExpressions()) {
            try {
                boolean result = expression.interpret(bindings);
                if (result)
                    factory.getDispatcher().fire();
                results.put(expression, result);
            } catch (InterpretException e) {
                e.printStackTrace();
            }
        }
        return results;
    }

}
