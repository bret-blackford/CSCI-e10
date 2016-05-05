package CSCI_e10.unit_9;
import java.util.Date;

/**
 * M BRET BLACKFORD
 * 
 * Data class used to manage the specific data items for one
 * option transaction. Is intended to be used as a request and
 * response structure to pass back and forth between calls.
 * 
 * @author mblackford mBret Bret Blackford
 * ID: 20849347
 * CSCI e-10b Spring 2016
 * Term Project
 */
public class OptionDetails {

	private Boolean callOption;
	private double spotPriceOfUnderlying;
	private double strikePrice;
	private double riskFreeInterestRate;
	private double timeToExpire;
	private double volatility;
	private Date valuationDate;
	private Date ExpDate;

	private double optionValue;
	
	//Below are the "greeks" 
	private double delta;
	private double theta;
	private double rho;
	private double gamma;
	private double vega;

	/**
	 * Constructor requires the basic input values for the Black-Scholes model
	 * @param call	- is it a PUT or CALL option
	 * @param s		- the price of the underlying
	 * @param k		- the strike price
	 * @param r		- the annual interest rate
	 * @param t		- the time to maturity 
	 * @param v		- the volatility 
	 */
	public OptionDetails(Boolean call, double s, double k, double r, double t,
			double v) {

			callOption = call;
			spotPriceOfUnderlying = s;
			strikePrice = k;
			riskFreeInterestRate = r;
			timeToExpire = t;
			volatility = v;
	}
	
	/**
	 * Method provides a clean and easy way to output the contents
	 * of the class.
	 */
	public String toString() {
		String out = "spot price [" + spotPriceOfUnderlying + "] strike [";
		out += strikePrice + "] int rate [" + riskFreeInterestRate + "] expire [";
		out += timeToExpire + "] vol [" + volatility + "] \n";
		
		out += "option value-[" + optionValue + "] delta-[" + delta + "] theta-[";
		out += theta + "] rho-[" + rho + "] gamma-[" + gamma + "] vega-[" + vega + "]";
		
		return out;
	}
	
	/**
	 * NOTE: I am not in favor of using getters and setters with request and response
	 * structures.  Getters and setters added here in keeping with class requirements.
	 * 
	 * @return
	 */
	public Boolean getCallOption() {
		return callOption;
	}

	public void setCallOption(Boolean callOption) {
		this.callOption = callOption;
	}

	public double getSpotPriceOfUnderlying() {
		return spotPriceOfUnderlying;
	}

	public void setSpotPriceOfUnderlying(double spotPriceOfUnderlying) {
		this.spotPriceOfUnderlying = spotPriceOfUnderlying;
	}

	public double getStrikePrice() {
		return strikePrice;
	}

	public void setStrikePrice(double strikePrice) {
		this.strikePrice = strikePrice;
	}

	public double getRiskFreeInterestRate() {
		return riskFreeInterestRate;
	}

	public void setRiskFreeInterestRate(double riskFreeInterestRate) {
		this.riskFreeInterestRate = riskFreeInterestRate;
	}

	public double getTimeToExpire() {
		return timeToExpire;
	}

	public void setTimeToExpire(double timeToExpire) {
		this.timeToExpire = timeToExpire;
	}

	public double getVolatility() {
		return volatility;
	}

	public void setVolatility(double volatility) {
		this.volatility = volatility;
	}

	public Date getValuationDate() {
		return valuationDate;
	}

	public void setValuationDate(Date valuationDate) {
		this.valuationDate = valuationDate;
	}

	public Date getExpDate() {
		return ExpDate;
	}

	public void setExpDate(Date expDate) {
		ExpDate = expDate;
	}

	public double getOptionValue() {
		return optionValue;
	}

	public void setOptionValue(double optionValue) {
		this.optionValue = optionValue;
	}

	public double getDelta() {
		return delta;
	}

	public void setDelta(double delta) {
		this.delta = delta;
	}

	public double getTheta() {
		return theta;
	}

	public void setTheta(double theta) {
		this.theta = theta;
	}

	public double getRho() {
		return rho;
	}

	public void setRho(double rho) {
		this.rho = rho;
	}

	public double getGamma() {
		return gamma;
	}

	public void setGamma(double gamma) {
		this.gamma = gamma;
	}

	public double getVega() {
		return vega;
	}

	public void setVega(double vega) {
		this.vega = vega;
	}
}
