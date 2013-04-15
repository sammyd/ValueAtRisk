package com.sammyd.valueatrisk;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class OptionParametersGeneratorTest {

	private OptionParameters op;
	private OptionParametersGenerator opg;
	
	@Before
	public void setUp() throws Exception {
		op = new OptionParameters(0.5, 100, 100, 1.05, 0.05);
		opg = new OptionParametersGenerator(op, 0.5, 0.5, 0.5);
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	/* Constructor Tests */
	@Test
	public void test_constructionWithNullParamters_shouldThrow() {
		exception.expect(NullPointerException.class);
		opg = new OptionParametersGenerator(null, 0.1, 0.1, 0.1);
	}
	
	@Test
	public void test_constructorWithValidParameters_shouldPopulateFieldsCorrectly() {
		assertSame(opg.getInitialParameters(), op);
		assertEquals(opg.getImplicitVolatiltyVariance(), 0.5, 1e-10);
		assertEquals(opg.getInterestRateVariance(), 0.5, 1e-10);
		assertEquals(opg.getUnderlyingHistoricalVariance(), 0.5, 1e-10);
	}
	
	/* getNewRandomOptionParameters Tests */
	@Test
	public void test_randomParamGen_withZeroVariance_shouldNotPerturbValues() {
		opg = new OptionParametersGenerator(op, 0, 0.1, 0.1);
		assertEquals(opg.getNewRandomOptionParameters().getInterestRate(), 
				op.getInterestRate(), 1e-6);
		opg = new OptionParametersGenerator(op, 0.1, 0, 0.1);
		assertEquals(opg.getNewRandomOptionParameters().getSpotPrice(),
				op.getSpotPrice(), 1e-6);
		opg = new OptionParametersGenerator(op, 0.1, 0.1, 0);
		assertEquals(opg.getNewRandomOptionParameters().getVolatility(),
				op.getVolatility(), 1e-6);
	}
	
	@Test
	public void test_randomParamGen_withNonZeroVariance_shouldPerturbValues() {
		/* Warning - this test case is a bit flakey. The generator creates random
		 * values, and so there's a possibility that we could end up with equality
		 * when we don't expect it. We also get different values each time we run
		 * the test. What I guess we should do is mock a random value generator
		 * and provide it at construction time. Let's think about doing that later.
		 */
		
		OptionParameters perturbed = opg.getNewRandomOptionParameters();
		assertFalse(Math.abs(perturbed.getInterestRate() - op.getInterestRate()) < 1e-10);
		assertFalse(Math.abs(perturbed.getSpotPrice()    - op.getSpotPrice()   ) < 1e-10);
		assertFalse(Math.abs(perturbed.getVolatility()   - op.getVolatility()  ) < 1e-10);
	}
	
}
