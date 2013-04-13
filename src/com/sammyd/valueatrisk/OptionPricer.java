package com.sammyd.valueatrisk;

public interface OptionPricer {
	/**
	 * Return the price estimate for an option given the specified parameters
	 * object.
	 * @param parameters The calculation parameters
	 * @return The estimeted price
	 */
	public double calculatePrice(OptionParameters parameters);
}
