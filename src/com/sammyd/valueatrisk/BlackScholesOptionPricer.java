package com.sammyd.valueatrisk;

import org.apache.commons.lang3.Validate;
import org.apache.commons.math3.distribution.NormalDistribution;

/**
 * This class prices a European call option. I guess it should be renamed.
 * @author sam
 *
 */

public class BlackScholesOptionPricer implements OptionPricer {

	/**
	 * A normal distribution. We'll reuse this across different calculations.
	 */
	private NormalDistribution normalDistribution = new NormalDistribution(0, 1);

	/**
	 * OptionPricer interface methods
	 */
	@Override
	public double calculatePrice(OptionParameters parameters) {
		Validate.notNull(parameters);
		
		final double s0 = parameters.getSpotPrice();
		final double d1 = this.d1(parameters);
		final double d2 = this.d2(parameters, d1);
		
		double price = s0 * normalDistribution.cumulativeProbability(d1);
		price -= normalDistribution.cumulativeProbability(d2) * parameters.getStrikePrice()
				* Math.exp(-parameters.getInterestRate() * parameters.getTimeToMaturity());
		
		return price;
	}
	
	/**
	 * Part of the BS calculation
	 * @param parameters The parameters for this calculation
	 * @return The d1 part of the Black-Scholes formula
	 */
	private double d1(OptionParameters parameters) {
		double retVal = Math.log(parameters.getSpotPrice() / parameters.getStrikePrice());
		retVal += (parameters.getInterestRate() + parameters.getVolatility() * parameters.getVolatility() / 2.f)
				* parameters.getTimeToMaturity();
		retVal /= parameters.getVolatility() * Math.sqrt(parameters.getTimeToMaturity());
		return retVal;
	}
	
	/**
	 * Part of the BS calculation
	 * @param parameters The parameters for this calculation
	 * @param d1 The d1 part of the Black-Scholes formula
	 * @return The d2 part of the Black-Scholes formula
	 */
	private double d2(OptionParameters parameters, double d1) {
		return (d1 - parameters.getVolatility() * Math.sqrt(parameters.getTimeToMaturity()));
	}

}
