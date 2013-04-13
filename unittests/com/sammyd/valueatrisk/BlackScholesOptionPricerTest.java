package com.sammyd.valueatrisk;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class BlackScholesOptionPricerTest {

	private BlackScholesOptionPricer op;
	
	@Before
	public void setUp() throws Exception {
		op = new BlackScholesOptionPricer();
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test(expected=NullPointerException.class)
	public void testCalculatePrice_withNilParameters_shouldThrowException() {
		op.calculatePrice(null);
	}
	
}
