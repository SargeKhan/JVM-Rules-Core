## JVM Rules
JVM Rules is a light wight a Rule engine that can execute one or more text based rules in a runtime.

### Summary
JVM Rules API has two parts one is Rule Registry and other is Rule Evaluator. Rule registry keeps all expressions that can apply on data. Rule registry provides the interface to register and unregister expressions at runtime. Rule Evaluator validate dataSet against the Rule Registry and return the result against every expression.


### DataTypes Syntax

| Value Expression      | String    | Integer   | Float     | Boolean       | Regex           | Date                           | DateTime                                        |
| -----------------     | --------- | --------- | --------- | ------------- | ----------------|------------------------------- | ----------------------------------------------- |
| Type                  |    'A'    |    4      |    4.2    | true,false    |  [R'*']         |[15-01-12016]                   | [15-01-12016 10:20:56]                          |
| List                  | {'A','B'} |   {2,5}   | {3.1,5.5} | {true,false}  | {[R'*'],[R'*']} | {[15-01-2016],[20-02-2016]}    | {[15-01-12016 15:20:56],[20-02-12016 23:20:56]} |


### Supported Operations
JVM Rules supports all common used operations bellow table explain about operations

| Operation Name    | Syntax                             | DataTypes                                              |Value Expression    |
| ----------------- | ---------------------------------- | ------------------------------------------------------ | ------------------ |
| EQUAL             |  Equal, equal, =                   | Integer, Float, String, Boolean, Regex, Date, DateTime | Type               |
| NOT EQUAL         | NotEqual, "notequal, !=, <>        | Integer, Float, String, Boolean, Regex, Date, DateTime | Type               |
| LESS              | Less, less, <                      | Integer, Float, String, Date, DateTime                 | Type               |
| LESS EQUAL        | LessEqual, lessequal, <=           | Integer, Float, String, Date, DateTime                 | Type               |
| GERATER           | Greater, greater, >                | Integer, Float, String, Date, DateTime                 | Type               |
| GERATER EQUAL     | GreaterEqual, greaterequal, >=     | Integer, Float, String, Date, DateTime                 | Type               |
| INL               | IN,In ,in                          | Integer, Float, String, Boolean,Regex, Date, DateTime  | List               |
| BETWEENL          | Between, between                   | Integer, Float, Date, DateTime                         | List {size=2}      |
| AND               | AND, And, and                      | Boolean                                                | Type               |
| OR                | OR, Or, or                         | Boolean                                                | Type               |
| IMPLIES           | Implies, implies, =>               | Boolean                                                | Type               |
| NOT               | NOT, Not, not                      | Boolean                                                | Type               |

### Basic Syntax

```java

import com.jvmrules.api.RuleFactory;
import com.jvmrules.exceptions.ExpressionParseException;
import com.jvmrules.expression.Expression;
import com.jvmrules.expression.veriable.StringVeriable;
import com.jvmrules.parser.ExpressionParser;

public class MyApp {

    public static void main (String[] args) {
        RuleFactory factory = RuleFactory.INSTANCE;
               Map<String, Class> types = new HashMap<>();
               types.put("V1", StringVeriable.class);
               types.put("V2", IntegerVeriable.class);
               types.put("V3", FloatVeriable.class);
               types.put("V4", BooleanVeriable.class);

               String exp="V1 = A AND ( ( V2 = 'O' AND V3 = 'R' ) OR ( V4 = 'G' AND V5 in {'M','N'} ) )";
               Expression expression = ExpressionParser.fromString(exp, types);

               RuleRegistry rules = new RuleRegistry(expression);

               Map<String, String> bindings = new HashMap<>();
               bindings.put("V1", "'A'");
               bindings.put("V2", "2");
               bindings.put("V3", "3.2");
               bindings.put("V4", "false");
               RuleEvaluator evaluator = factory.Evaluator;
               HashMap<Expression,Boolean> results = evaluator.evaluate(rules, bindings);

    }
}
```

## License
JVM Rules is released under the [![MIT license](http://img.shields.io/badge/license-MIT-brightgreen.svg?style=flat)](http://opensource.org/licenses/MIT).

```
The MIT License (MIT)

Copyright (c) 2016 Muhammad Hammad

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
```


