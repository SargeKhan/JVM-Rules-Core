package com.jvmrules.expression.operation;

import com.jvmrules.exceptions.ExpressionParseException;
import com.jvmrules.exceptions.InterpretException;
import com.jvmrules.expression.Expression;
import com.jvmrules.expression.NamedExpression;
import com.jvmrules.expression.value.ValueExpression;
import com.jvmrules.expression.value.ValueType;
import com.jvmrules.expression.veriable.VariableExpression;
import com.jvmrules.expression.veriable.VeriableType;

import java.util.Map;
import java.util.Stack;

public class Equals extends EqualityOperationExpression {
    public Equals() {
        super("Equal", "equal", "=");
    }

    public Equals copy() {
        return new Equals();
    }

}