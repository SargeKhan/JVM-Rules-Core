package com.jvmrules.expression.operation;

import com.jvmrules.exceptions.ExpressionParseException;
import com.jvmrules.exceptions.IllegalOperationException;
import com.jvmrules.exceptions.InterpretException;
import com.jvmrules.expression.Expression;
import com.jvmrules.expression.NamedExpression;
import com.jvmrules.expression.value.ValueExpression;
import com.jvmrules.expression.value.ValueType;
import com.jvmrules.expression.veriable.*;
import com.jvmrules.util.TokenString;
import com.jvmrules.util.TrimString;

import java.util.Date;
import java.util.Map;
import java.util.Stack;


public class LessEqual extends EqualityOperationExpression {
    public LessEqual() {
        super("LessEqual", "lessequal", "<=");
    }

    public LessEqual copy() {
        return new LessEqual();
    }



}