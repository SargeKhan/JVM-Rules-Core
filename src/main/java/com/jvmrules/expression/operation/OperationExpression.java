package com.jvmrules.expression.operation;

import com.jvmrules.exceptions.ExpressionParseException;
import com.jvmrules.exceptions.InterpretException;
import com.jvmrules.expression.Expression;
import com.jvmrules.expression.NamedExpression;
import com.jvmrules.expression.value.ValueExpression;
import com.jvmrules.expression.veriable.VariableExpression;
import com.jvmrules.expression.veriable.VeriableType;
import com.jvmrules.operations.Operations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public abstract class OperationExpression extends Operation {
    static Logger logger = LoggerFactory.getLogger(OperationExpression.class);

    protected List<String> symbols = new ArrayList<>();
    protected Expression leftOperand = null;
    protected Expression rightOperand = null;


    public OperationExpression(String symbol) {
        this.symbols.add(symbol);
    }

    public OperationExpression(List<String> symbols) {
        this.symbols.addAll(symbols);
    }

    public OperationExpression(String... symbols) {
        this.symbols.addAll(Arrays.asList(symbols));
    }

    public List<String> getSymbols() {
        return this.symbols;
    }

    public int parse(String[] tokens, int pos, Stack<Expression> stack, Map<String, Class> types) throws ExpressionParseException {
        Expression left = stack.pop();
        int i = findNextExpression(tokens, pos + 1, stack, types);
        Expression right = stack.pop();

        this.leftOperand = left;
        this.rightOperand = right;

        logger.debug("Operation Call Expression : {}", getClass().getSimpleName());

        stack.push(this);

        return i;
    }


    public Integer findNextExpression(String[] tokens, int pos, Stack<Expression> stack, Map<String, Class> types) throws ExpressionParseException {
        Operations operations = Operations.INSTANCE;

        for (int i = pos; i < tokens.length; i++) {

            Operation op = operations.getOperation(tokens[i]);
            if (op != null) {
                op = op.copy();


                // we found an operation
                i = op.parse(tokens, i, stack, types);


                return i;
            }
        }
        return null;
    }

    public boolean interpret(Map<String, ?> bindings) throws InterpretException {
       return false;
    }


}