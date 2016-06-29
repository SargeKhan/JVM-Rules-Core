package com.jvmrules.rule;


import com.jvmrules.action.ActionDispatcher;
import com.jvmrules.expression.Expression;

public class Rule {
    private Expression expression;
    private ActionDispatcher dispatcher;

    public Rule(Expression expression, ActionDispatcher dispatcher) {
        this.expression = expression;
        this.dispatcher = dispatcher;
    }

    public Expression getExpression() {
        return expression;
    }

    public void setExpression(Expression expression) {
        this.expression = expression;
    }

    public ActionDispatcher getDispatcher() {
        return dispatcher;
    }

    public void setDispatcher(ActionDispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }
}
