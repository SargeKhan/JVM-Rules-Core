package com.jvmrules.driver;


import com.jvmrules.action.ActionDispatcher;
import com.jvmrules.action.NullActionDispatcher;
import com.jvmrules.api.RuleFactory;
import com.jvmrules.exceptions.ExpressionParseException;
import com.jvmrules.expression.Expression;
import com.jvmrules.expression.veriable.StringVeriable;
import com.jvmrules.parser.ExpressionParser;
import com.jvmrules.rule.Rule;
import com.jvmrules.rule.builder.RuleBuilder;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String args[]) throws ExpressionParseException {

        RuleFactory factory = RuleFactory.INSTANCE;

        Map<String, Class> types = new HashMap<>();
        types.put("V1", StringVeriable.class);
        types.put("V2", StringVeriable.class);
        types.put("V3", StringVeriable.class);
        types.put("V4", StringVeriable.class);
        types.put("V5", StringVeriable.class);

        Expression ex1 = ExpressionParser.fromString("V1 = {'A','B'} AND ( ( V2 = 'O' AND V3 = 'R' ) OR ( V4 = 'G' AND V5 = 'M' ) )", types);


        //  Expression ex1 = ExpressionParser.fromString("( V2 = 'O' AND V3 = 'R') AND  V4 = 'G'");

//        Expression ex2 = ExpressionParser.fromString("PATIENT_TYPE = 'B'");
//        Expression ex3 = ExpressionParser.fromString("PATIENT_TYPE = 'A' AND NOT ADMISSION_TYPE = 'O'");

        ActionDispatcher actionDispatcher = new NullActionDispatcher();


        Rule rule1 = new RuleBuilder()
                .withExpression(ex1)
                .withDispatcher(actionDispatcher)
                .build();

//        Rule rule2 = new RuleBuilder()
//                .withExpression(ex2)
//                .withDispatcher(actionDispatcher)
//                .build();


        Map<String, String> bindings = new HashMap<>();
        bindings.put("V1", "'A'");
        bindings.put("V2", "'O'");
        bindings.put("V3", "'T'");
        bindings.put("V4", "'K'");
        bindings.put("V5", "'M'");


       /* RuleEvaluator evaluator = factory.Evaluator;
        boolean triggered = evaluator.evaluate(rule1, bindings);
        System.out.println("Action triggered: " + triggered);*/

    }


}
