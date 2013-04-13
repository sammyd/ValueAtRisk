package com.sammyd.valueatrisk;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	OptionParametersTest.class,
	BlackScholesOptionPricerTest.class
})
public class AllTests {

}
