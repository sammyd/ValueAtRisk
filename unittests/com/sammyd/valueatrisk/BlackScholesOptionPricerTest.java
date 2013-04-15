package com.sammyd.valueatrisk;

import static org.junit.Assert.*;

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
	
	@Test
	public void testCalculatePrice_withSampleParameters_shouldReturnCorrectValue() {
		/**
		 * This test is quite brittle, but it is used to establish whether or not
		 * the OptionPricer is working as expected
		 */
		OptionParameters params = new OptionParameters(4, 120, 80, 0.05, 0.2);
		assertEquals(55.494, op.calculatePrice(params), 1e-3);
	}
}
