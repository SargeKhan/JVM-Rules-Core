package com.jvmrules.rule.builder;


import com.jvmrules.action.ActionDispatcher;
import com.jvmrules.action.NullActionDispatcher;
import com.jvmrules.expression.Expression;
import com.jvmrules.rule.Rule;

public class RuleBuilder {

    private Expression expression;
    private ActionDispatcher dispatcher = new NullActionDispatcher();

    public RuleBuilder withExpression(Expression expression) {
        this.expression = expression;
        return this;
    }

    public RuleBuilder withDispatcher(ActionDispatcher dispatcher) {
        this.dispatcher = dispatcher;
        return this;
    }

    public Rule build() {
        return new Rule(expression, dispatcher);
    }
}
