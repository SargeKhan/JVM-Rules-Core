package com.jvmrules.rule;


import com.jvmrules.action.ActionDispatcher;
import com.jvmrules.action.NullActionDispatcher;
import com.jvmrules.expression.Expression;

import java.util.ArrayList;
import java.util.List;

public class RuleRegistry {
    private List<Expression> expressions = new ArrayList<>();
    private ActionDispatcher dispatcher = new NullActionDispatcher();


    public RuleRegistry(Expression expression, ActionDispatcher dispatcher) {
        this.expressions.add(expression);
        this.dispatcher = dispatcher;
    }

    public RuleRegistry(Expression expression) {
        this.expressions.add(expression);
    }


    public RuleRegistry(List<Expression> expressions, ActionDispatcher dispatcher) {
        this.expressions = expressions;
        this.dispatcher = dispatcher;
    }


    public RuleRegistry(ActionDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }


    public void addExpression(Expression expression) {
        this.expressions.add(expression);
    }


    public List<Expression> getExpressions() {
        return expressions;
    }

    public void setExpressions(List<Expression> expressions) {
        this.expressions = expressions;
    }

    public ActionDispatcher getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(ActionDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }
}
