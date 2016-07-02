package com.jvmrules;

import com.jvmrules.test.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        EqualOperationTest.class,
        NotEqualOperationTest.class, InOperationTest.class})
public class AppTest {

}
