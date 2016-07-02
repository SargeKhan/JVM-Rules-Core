package com.jvmrules.test;

import com.jvmrules.api.RuleFactory;
import com.jvmrules.exceptions.ExpressionParseException;
import com.jvmrules.expression.Expression;
import com.jvmrules.expression.veriable.DateTimeVeriable;
import com.jvmrules.expression.veriable.DateVeriable;
import com.jvmrules.expression.veriable.FloatVeriable;
import com.jvmrules.expression.veriable.IntegerVeriable;
import com.jvmrules.parser.ExpressionParser;
import com.jvmrules.rule.RuleRegistry;
import com.jvmrules.rule.evaluator.RuleEvaluator;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class BetweenOperationTest extends TestCase {
    @Test
    public void testApp() throws ExpressionParseException {

        RuleFactory factory = RuleFactory.INSTANCE;

        Map<String, Class> types = new HashMap<>();
        types.put("V1", IntegerVeriable.class);
        types.put("V2", FloatVeriable.class);
        types.put("V3", DateVeriable.class);
        types.put("V4", DateTimeVeriable.class);

        String exp = "V1 between {2,4} And V2 between {3.1,3.3} V3 between {[12-05-2016],[15-06-2016]} ";

        Expression expression = ExpressionParser.fromString(exp, types);

        RuleRegistry rules = new RuleRegistry(expression);

        Map<String, String> bindings = new HashMap<>();
        bindings.put("V1", "3");
        bindings.put("V2", "3.2");
        bindings.put("V3", "[28-05-2016]");
        bindings.put("V4", "[28-05-2016-15:30:00]");

        RuleEvaluator evaluator = factory.Evaluator;
        HashMap<Expression, Boolean> results = evaluator.evaluate(rules, bindings);

        boolean triggered = results.get(expression);

        assertTrue(triggered);
    }

}
