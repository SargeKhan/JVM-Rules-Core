package com.jvmrules.test;

import com.jvmrules.api.RuleFactory;
import com.jvmrules.exceptions.ExpressionParseException;
import com.jvmrules.expression.Expression;
import com.jvmrules.expression.veriable.StringVeriable;
import com.jvmrules.parser.ExpressionParser;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;


public class ListValueTest extends TestCase {
    @Test
    public void testApp() throws ExpressionParseException {

        RuleFactory factory = RuleFactory.INSTANCE;
        Map<String, Class> types = new HashMap<>();
        types.put("V1", StringVeriable.class);
        Expression ex1 = ExpressionParser.fromString("V1 = {'A','B'} ", types);

        assertTrue(true);
    }

}
