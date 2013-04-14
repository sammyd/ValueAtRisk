package com.sammyd.valueatrisk;

import java.util.SortedSet;
import java.util.logging.Logger;

public class VARRunner {

	private final static Logger LOGGER = Logger.getLogger(VARRunner.class.getName());
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LOGGER.info("== Running Trial Single-Pass Pricing Strategy ==");
		OptionPricer pricer = new BlackScholesOptionPricer();
		
		// We'd probably read all these parameters from a more sensible source
		OptionParameters params = new OptionParameters(1.0, 120.0, 100.0, 0.05, 0.2);
		OptionParametersGenerator generator = new OptionParametersGenerator(params, 0.2, 0.15, 0.05);
		
		// Now create a task
		ValueAtRiskComputation varComp = new ValueAtRiskComputation(pricer, generator);
		
		SortedSet<Double> result = varComp.computeVAR(100000, 100000, 0.99);
		
		//for(Double value : result) {
			LOGGER.info(String.format("%.6f",result.last()));
		//}
		
	}

}
