package com.jvmrules.parser;

import com.jvmrules.exceptions.ExpressionParseException;
import com.jvmrules.expression.Expression;
import com.jvmrules.expression.Operation;
import com.jvmrules.operations.Operations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Stack;

public class ExpressionParser {

    static Logger logger = LoggerFactory.getLogger(ExpressionParser.class);

    private static final Operations operations = Operations.INSTANCE;

    public static Expression fromString(String expr, Map<String, Class> types) throws ExpressionParseException {

        logger.debug("Parsing the expression : {}",expr);

        Stack<Expression> stack = new Stack<>();

        String[] tokens = expr.split("\\s");
        for (int i = 0; i < tokens.length - 1; i++) {

            Operation op = operations.getOperation(tokens[i]);
            if (op != null) {
                // create a new instance
                op = op.copy();
                i = op.parse(tokens, i, stack, types);
            }
        }

        return stack.pop();
    }
}