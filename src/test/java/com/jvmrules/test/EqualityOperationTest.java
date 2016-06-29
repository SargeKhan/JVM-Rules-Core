package com.jvmrules.test;

import com.jvmrules.action.ActionDispatcher;
import com.jvmrules.action.NullActionDispatcher;
import com.jvmrules.api.RuleFactory;
import com.jvmrules.exceptions.ExpressionParseException;
import com.jvmrules.expression.Expression;
import com.jvmrules.expression.veriable.IntegerVeriable;
import com.jvmrules.expression.veriable.StringVeriable;
import com.jvmrules.parser.ExpressionParser;
import com.jvmrules.rule.Rule;
import com.jvmrules.rule.builder.RuleBuilder;
import com.jvmrules.rule.evaluator.RuleEvaluator;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class EqualityOperationTest extends TestCase {
    @Test
    public void testApp() throws ExpressionParseException {

        RuleFactory factory = RuleFactory.INSTANCE;

        Map<String, Class> types = new HashMap<>();
        types.put("V1", StringVeriable.class);
        types.put("V2", IntegerVeriable.class);

        Expression ex1 = ExpressionParser.fromString("V1 in {'A','B'} AND V2 in {3,4}", types);


        ActionDispatcher actionDispatcher = new NullActionDispatcher();


        Rule rule1 = new RuleBuilder()
                .withExpression(ex1)
                .withDispatcher(actionDispatcher)
                .build();


        Map<String, String> bindings = new HashMap<>();
        bindings.put("V1", "'A'");
        bindings.put("V2", "4");

        RuleEvaluator evaluator = factory.Evaluator;
        boolean triggered = evaluator.evaluate(rule1, bindings);

        assertTrue(triggered);
    }

}
