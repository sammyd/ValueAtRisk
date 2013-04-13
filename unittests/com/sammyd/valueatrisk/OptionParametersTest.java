package com.sammyd.valueatrisk;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OptionParametersTest {

	private OptionParameters p;
	
	@Before
	public void setUp() throws Exception {
		p = new OptionParameters(0.5, 100, 200, 1.05, 0.5);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testOptionParameterIndividualPropertyConstructor() {
		// Should have created an appropriate object
		assertEquals(p.getTimeToMaturity(), 0.5, 1e-6);
		assertEquals(p.getSpotPrice(), 100, 1e-6);
		assertEquals(p.getStrikePrice(), 200, 1e-6);
		assertEquals(p.getInterestRate(), 1.05, 1e-6);
		assertEquals(p.getVolatility(), 0.5, 1e-6);
	}

	@Test
	public void testOptionParametersOptionParameters() {
		OptionParameters q = new OptionParameters(p);
		// Should have made a deep copy
		assertEquals(p.getTimeToMaturity(), q.getTimeToMaturity(), 1e-6);
		assertEquals(p.getSpotPrice(), q.getSpotPrice(), 1e-6);
		assertEquals(p.getStrikePrice(), q.getStrikePrice(), 1e-6);
		assertEquals(p.getInterestRate(), q.getInterestRate(), 1e-6);
		assertEquals(p.getVolatility(), q.getVolatility(), 1e-6);
		// But they aren't the same objects
		assertNotSame(p, q);
	}

}
