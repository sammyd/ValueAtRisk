package com.sammyd.valueatrisk;

import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.lang3.Validate;

public class ValueAtRiskComputation {
	
	private final OptionPricer optionPricer;
	private final OptionParametersGenerator parameterGenerator;

	/**
	 * Create a computation object
	 * @param pricer The required option pricer
	 * @param generator The parameter generator for generating different pricing strategies
	 */
	public ValueAtRiskComputation(final OptionPricer pricer, final OptionParametersGenerator generator){
		Validate.notNull(pricer);
		Validate.notNull(generator);
		this.optionPricer = pricer;
		this.parameterGenerator = generator;
	}
	
	/**
	 * Return a list of the lowest samples of prices
	 * @param sampleSize Number of samples this operation should perform
	 * @param totalNumberOfSamples The total number of samples the entire system
	 * will be performing. This is used to calculate how many samples we need to
	 * retain to obtain the required precision.
	 * @param precisionVAR The value of precision we wish to obtain. Typically
	 * 0.01 or 0.05 (1% or 5%)
	 * @return A sorted list of the minimum values obtained from price sampling
	 */
	public SortedSet<Double> computeVAR(final int sampleSize, final int totalNumberOfSamples,
			final double precisionVAR) {
		final SortedSet<Double> smallestPrices = new TreeSet<Double>();
		final double numberOfSamplesToKeep = totalNumberOfSamples * (1 - precisionVAR);
		
		for(int i=0; i<sampleSize; i++) {
			OptionParameters parameters = parameterGenerator.getNewRandomOptionParameters();
			double price = optionPricer.calculatePrice(parameters);
			
			smallestPrices.add(price);
			if(smallestPrices.size() > numberOfSamplesToKeep) {
				// We can throw away the highest one
				smallestPrices.remove(smallestPrices.last());
			}
		}
		return smallestPrices;
	}
}
