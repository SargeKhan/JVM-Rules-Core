package com.jvmrules.test;

import com.jvmrules.action.ActionDispatcher;
import com.jvmrules.action.NullActionDispatcher;
import com.jvmrules.api.RuleFactory;
import com.jvmrules.exceptions.ExpressionParseException;
import com.jvmrules.expression.Expression;
import com.jvmrules.expression.veriable.StringVeriable;
import com.jvmrules.parser.ExpressionParser;
import com.jvmrules.rule.Rule;
import com.jvmrules.rule.builder.RuleBuilder;
import com.jvmrules.rule.evaluator.RuleEvaluator;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class ParenthesisTest extends TestCase {
    @Test
    public void testApp() throws ExpressionParseException {

        RuleFactory factory = RuleFactory.INSTANCE;

        Map<String, Class> types = new HashMap<>();
        types.put("V1", StringVeriable.class);
        types.put("V2", StringVeriable.class);
        types.put("V3", StringVeriable.class);
        types.put("V4", StringVeriable.class);
        types.put("V5", StringVeriable.class);

        Expression expression = ExpressionParser.fromString("V1 = A AND ( ( V2 = 'O' AND V3 = 'R' ) OR ( V4 = 'G' AND V5 = 'M' ) )", types);


        ActionDispatcher actionDispatcher = new NullActionDispatcher();


        Rule rule1 = new RuleBuilder()
                .withExpression(expression)
                .withDispatcher(actionDispatcher)
                .build();


        Map<String, String> bindings = new HashMap<>();
        bindings.put("V1", "'A'");
        bindings.put("V2", "'O'");
        bindings.put("V3", "'T'");
        bindings.put("V4", "'K'");
        bindings.put("V5", "'M'");


        RuleEvaluator evaluator = factory.Evaluator;
        boolean triggered = evaluator.evaluate(rule1, bindings);

        assertFalse(triggered);
    }

}
