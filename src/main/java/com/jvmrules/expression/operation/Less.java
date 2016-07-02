package com.jvmrules.expression.operation;

import com.jvmrules.exceptions.ExpressionParseException;
import com.jvmrules.exceptions.IllegalOperationException;
import com.jvmrules.expression.Expression;
import com.jvmrules.expression.value.ValueExpression;
import com.jvmrules.expression.value.ValueType;
import com.jvmrules.expression.veriable.VariableExpression;
import com.jvmrules.expression.veriable.VeriableType;
import com.jvmrules.util.TokenString;
import com.jvmrules.util.ValueDataType;

import java.util.Map;
import java.util.Stack;


public class Less extends EqualityOperationExpression {
    public Less() {
        super("Less", "less", "<");
    }

    public Less copy() {
        return new Less();
    }



}