package com.sammyd.valueatrisk;

import org.apache.commons.math3.distribution.NormalDistribution;

public class BlackScholesOptionPricer implements OptionPricer {

	/**
	 *  Option parameters
	 */
	private final OptionParameters initialParameters;
	private OptionParameters parameters;
	
	/**
	 * A normal distribution
	 */
	private NormalDistribution normalDistribution = new NormalDistribution(0, 1);
	
	/**
	 * @param parameters The pricing parameters
	 */
	public BlackScholesOptionPricer(final OptionParameters p) {
		super();
		this.initialParameters = p;
		this.setParameters(new OptionParameters(p));
	}

	/** OptionPricer methods */
	@Override
	public double calculatePrice() {
		final double s0 = parameters.getSpotPrice();
		final double d1 = this.d1(s0);
		final double d2 = this.d2(d1);
		
		double price = s0 * normalDistribution.cumulativeProbability(d1);
		price -= normalDistribution.cumulativeProbability(d2) * parameters.getStrikePrice()
				* Math.exp(-parameters.getInterestRate() * parameters.getTimeToMaturity());
		
		return price;
	}
	
	private double d1(double spotPrice) {
		double retVal = Math.log(spotPrice / parameters.getStrikePrice());
		retVal += (parameters.getInterestRate() - parameters.getVolatility() / 2.f)
				* parameters.getTimeToMaturity();
		retVal /= parameters.getVolatility() * parameters.getTimeToMaturity();
		return retVal;
	}
	
	private double d2(double d1) {
		return (d1 - parameters.getVolatility() * Math.sqrt(parameters.getTimeToMaturity()));
	}

	/** More getters and setters */
	public OptionParameters getInitialParameters() {
		return initialParameters;
	}

	public OptionParameters getParameters() {
		return parameters;
	}

	public void setParameters(OptionParameters parameters) {
		this.parameters = parameters;
	}

}
