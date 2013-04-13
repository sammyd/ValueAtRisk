package com.sammyd.valueatrisk;

public class OptionParameters {
    
    /** Instance Variables */
    /** Time to maturity in years */
    private double timeToMaturity;
    
    /** Spot (current) price of the underlying */
    private double spotPrice;
    
    /** Strike (target) price of the underlying */
    private double strikePrice;
    
    /** Risk free interest rate */
    private double interestRate;
    
    /** Implicit volatility of the equity */
    private double volatility;

    /** Constructors */
    
    /**
	 * @param timeToMaturity
	 * @param spotPrice
	 * @param strikePrice
	 * @param interestRate
	 * @param volatility
	 */
	public OptionParameters(double timeToMaturity, double spotPrice,
			double strikePrice, double interestRate, double volatility) {
		super();
		this.timeToMaturity = timeToMaturity;
		this.spotPrice = spotPrice;
		this.strikePrice = strikePrice;
		this.interestRate = interestRate;
		this.volatility = volatility;
	}
	
	/** Copy constructor */
	public OptionParameters(final OptionParameters p) {
		this(p.timeToMaturity, p.spotPrice, p.strikePrice, p.interestRate, p.volatility);
	}
	
    /** Getters and setters (??surely we don't have to explicitly define these??) */
    public double getTimeToMaturity() {
        return timeToMaturity;
    }

	public void setTimeToMaturity(double timeToMaturity) {
        this.timeToMaturity = timeToMaturity;
    }

    public double getSpotPrice() {
        return spotPrice;
    }

    public void setSpotPrice(double spotPrice) {
        this.spotPrice = spotPrice;
    }

    public double getStrikePrice() {
        return strikePrice;
    }

    public void setStrikePrice(double strikePrice) {
        this.strikePrice = strikePrice;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getVolatility() {
        return volatility;
    }

    public void setVolatility(double volatility) {
        this.volatility = volatility;
    }

}
