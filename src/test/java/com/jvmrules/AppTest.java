package com.jvmrules;

import com.jvmrules.test.ListValueTest;
import com.jvmrules.test.ParenthesisTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses({
        ParenthesisTest.class,
        ListValueTest.class })
public class AppTest {

}
