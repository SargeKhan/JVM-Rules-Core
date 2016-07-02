package com.jvmrules.test;

import com.jvmrules.api.RuleFactory;
import com.jvmrules.exceptions.ExpressionParseException;
import com.jvmrules.expression.Expression;
import com.jvmrules.expression.veriable.*;
import com.jvmrules.parser.ExpressionParser;
import com.jvmrules.rule.RuleRegistry;
import com.jvmrules.rule.evaluator.RuleEvaluator;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class InOperationTest extends TestCase {
    @Test
    public void testApp() throws ExpressionParseException {

        RuleFactory factory = RuleFactory.INSTANCE;

        Map<String, Class> types = new HashMap<>();
        types.put("V1", StringVeriable.class);
        types.put("V2", IntegerVeriable.class);
        types.put("V3", FloatVeriable.class);
        types.put("V4", BooleanVeriable.class);
        types.put("V5", DateVeriable.class);
        types.put("V6", DateTimeVeriable.class);
        types.put("V7", RegexVeriable.class);

        String exp = "V1 in {'A','B'} And V2 in {2,3} And V3 = {3.1,3.2} And V4 in {true,false} And V5 in {[12-05-2016],[13-05-2016]} And V6 in {[12-05-2016-15:30:00],[13-05-2016-15:30:00]} And V7 in {[R'B1.*'],[R'A1.*']}";

        Expression expression = ExpressionParser.fromString(exp, types);

        RuleRegistry rules = new RuleRegistry(expression);

        Map<String, String> bindings = new HashMap<>();
        bindings.put("V1", "'A'");
        bindings.put("V2", "2");
        bindings.put("V3", "3.2");
        bindings.put("V4", "false");
        bindings.put("V5", "[12-05-2016]");
        bindings.put("V6", "[12-05-2016-15:30:00]");
        bindings.put("V7", "A1B2");

        RuleEvaluator evaluator = factory.Evaluator;
        HashMap<Expression, Boolean> results = evaluator.evaluate(rules, bindings);

        boolean triggered = results.get(expression);

        assertTrue(triggered);
    }

}
