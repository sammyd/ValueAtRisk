package com.sammyd.valueatrisk;

import java.security.SecureRandom;

import org.apache.commons.lang3.Validate;
import org.apache.commons.math3.random.RandomDataGenerator;

public class OptionParametersGenerator {

	private final double interestRateVariance;
	private final double underlyingHistoricalVariance;
	private final double implicitVolatilityVariance;
	private final OptionParameters initialParameters;
	
	// Cheap generator used to create the samples
	private RandomDataGenerator randomGenerator = new RandomDataGenerator();
	// Used to seed the cheaper generator
	private SecureRandom secureRandomGenerator = new SecureRandom();
	
	/**
	 * Create an OptionParameterGenerator which will generate random perturbations
	 * around an OptionParameter object which is provided at construction time.
	 * The perturbations are Gaussian in nature, and the other construction arguments
	 * provide the variances of the perturbed parameters.
	 * @param initialParameters The initial OptionParamter about which we will perturb
	 * @param interestRateVariance Variance of the InterestRate used for random sampling
	 * @param underlyingHistoricalVariance Variance of the spot price, used for random sampling
	 * @param implicitVolatilityVariance Variance of the volatility, used for random sampling
	 */
	public OptionParametersGenerator(OptionParameters initialParameters,
			double interestRateVariance,
			double underlyingHistoricalVariance,
			double implicitVolatilityVariance) {
		// Validate the input
		Validate.notNull(initialParameters);
		// Save off the initial properties
		this.initialParameters = initialParameters;
		this.interestRateVariance = interestRateVariance;
		this.implicitVolatilityVariance = implicitVolatilityVariance;
		this.underlyingHistoricalVariance = underlyingHistoricalVariance;
		// Seed the generator
		randomGenerator.reSeed(secureRandomGenerator.nextLong());
	}

	/**
	 * Pull a new random sample, based on the initial parameters.
	 * @return A new OptionParameters object, with randomised parameters.
	 */
	public OptionParameters getNewRandomOptionParameters() {
		// Create new parameters based on the initial ones
		OptionParameters op = new OptionParameters(this.initialParameters);
		
		// Now we perturb the relevant values
		if(this.interestRateVariance != 0) {
			op.setInterestRate(this.initialParameters.getInterestRate() +
					randomGenerator.nextGaussian(0, this.interestRateVariance));
		}
		
		if(this.implicitVolatilityVariance != 0) {
			op.setVolatility(randomGenerator.nextGaussian(this.initialParameters.getVolatility(),
					this.implicitVolatilityVariance));
		}
		
		if(this.underlyingHistoricalVariance != 0) {
			op.setSpotPrice(this.initialParameters.getSpotPrice() *
					(1 + randomGenerator.nextGaussian(0, this.underlyingHistoricalVariance)));
		}
		
		// Return our newly perturbed parameter object
		return op;
	}
	
	/* Property getters */
	public OptionParameters getInitialParameters() {
		return this.initialParameters;
	}
	
	public double getInterestRateVariance() {
		return this.interestRateVariance;
	}
	
	public double getUnderlyingHistoricalVariance() {
		return this.underlyingHistoricalVariance;
	}
	
	public double getImplicitVolatiltyVariance() {
		return this.implicitVolatilityVariance;
	}
}
