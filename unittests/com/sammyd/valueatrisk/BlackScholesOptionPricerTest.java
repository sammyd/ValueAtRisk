package com.sammyd.valueatrisk;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class BlackScholesOptionPricerTest {
	
	private BlackScholesOptionPricer op;
	
	@Before
	public void setUp() throws Exception {
		op = new BlackScholesOptionPricer();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testCalculatePrice_withNilParameters_shouldThrowException() {
		exception.expect(java.lang.NullPointerException.class);
		op.calculatePrice(null);
	}
	
}
