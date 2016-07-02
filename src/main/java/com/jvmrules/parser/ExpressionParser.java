package com.jvmrules.parser;

import com.jvmrules.exceptions.ExpressionParseException;
import com.jvmrules.expression.Expression;
import com.jvmrules.expression.Operation;
import com.jvmrules.operations.Operations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Map;
import java.util.Stack;

public class ExpressionParser {

    static Logger logger = LoggerFactory.getLogger(ExpressionParser.class);

    private static final Operations operations = Operations.INSTANCE;


    public static Expression fromString(String expr, Map<String, Class> types) throws ExpressionParseException {

        if (expr != null && !expr.isEmpty()) {
            logger.debug("Parsing the expression : {}", expr);

            Stack<Expression> stack = new Stack<>();

            String splits[] = {"\\s", "\\(", "\\)"};

            StringBuilder builder = new StringBuilder("");
            for (String split : splits) {
                builder.append(split);
                builder.append("|");
            }

            String regex = builder.toString().trim();
            if (regex.endsWith("|")) {
                regex = regex.substring(0, regex.length() - 1);
            }

            logger.debug(regex);

            String[] tokens = expr.split(regex);

            logger.debug("tokens: {}", Arrays.asList(tokens));
            for (int i = 0; i < tokens.length - 1; i++) {

                Operation op = operations.getOperation(tokens[i]);
                if (op != null) {
                    // create a new instance
                    op = op.copy();
                    i = op.parse(tokens, i, stack, types);
                }
            }

            return stack.pop();
        } else {
            String message = String.format("Expression is null or Empty");
            logger.error(message);
            throw new ExpressionParseException(message);
        }


    }
}