package CSCI_e10.unit_9;

/**
 * M BRET BLACKFORD
 * 
 * The Black-Scholes formula, which gives a theoretical estimate of 
 * the price of European-style options.
 * 
 * The model was first articulated by Fischer Black and Myron Scholes in 
 * their 1973 paper, "The Pricing of Options and Corporate Liabilities", 
 * published in the Journal of Political Economy. They derived a stochastic 
 * partial differential equation, now called the Black-Scholes equation, 
 * which governs the price of the option over time. 
 * 
 * 
 * The Black-Scholes model is widely excepted and frequently used.  The code
 * herein is derivative of work by others, such as: Robert Sedgewick,
 * Kevin Wayne, Jerry Kovse, and Dhruba Bandopadhyay.
 * 
 * @author mblackford - M. Bret Blackford 
 * ID: 20849347
 * CSCI e-10b  Spring 2016
 * 
 */
public class BlackScholesFormula {

	// The Abramowitz & Stegun (1964) numerical approximation
	// below uses six constant values in its formula.
	private static final double P = 0.2316419;
	private static final double B1 = 0.319381530;
	private static final double B2 = -0.356563782;
	private static final double B3 = 1.781477937;
	private static final double B4 = -1.821255978;
	private static final double B5 = 1.330274429;

	/**
	 * 
	 * @param callOption
	 *            boolean true/false
	 * @param s
	 *            = Spot price of underlying stock/asset
	 * @param k
	 *            = Strike price
	 * @param r
	 *            = Risk free annual interest rate continuously compounded
	 * @param t
	 *            = Time in years until option expiration (maturity)
	 * @param v
	 *            = Implied volatility of returns of underlying stock/asset
	 * @return OptionDetails
	 * 			  = OptionDetails is a class returned the key Black-Scholes fields
	 */
	public  OptionDetails calculate(boolean callOption, double s, double k,
			double r, double t, double v) {
	
		//System.out.println("    ----- ");
		//System.out.println(" in BlackScholesFormula:calculate(" + callOption + "," + s + "," + k + "," + r + "," + t + "," + v);
		
		double blackScholesOptionPrice = 0.0;
				
		double d1 = d1(s, k, r, t, v);
		double d2 = d2(s, k, r, t, v);
		double sd1 = standardNormalDistribution(d1);
		double cd1 = cumulativeDistribution(d1, sd1);
		double cd2 = cumulativeDistribution( d2(s, k, r, t, v) );
		double delta = 0.0;
		
		if (callOption) {
			cd2 = cumulativeDistribution( d2 );
			
			delta = cd1;
			blackScholesOptionPrice = s * cd1 - k * Math.exp(-r * t) * cd2;
		} else {
			 double pcd1 = cumulativeDistribution( -d1 );
			 double pcd2 = cumulativeDistribution( -d2 );
			 
			delta = cd1 - 1;
			blackScholesOptionPrice = k * Math.exp(-r * t) * pcd2 - s * pcd1;
		}
		
		OptionDetails resp = new OptionDetails(callOption, s, k, r, t, v);
		resp.setOptionValue( blackScholesOptionPrice );
		resp.setDelta( delta );
		return resp;
	}
	
	
	/**
	 * Method uses the OptionDetails request and response class
	 * as input and output.  This obfuscates the required inputs
	 * but is useful for many applications.
	 * @param req
	 * @return OptionDetails
	 * 				= OptionDetails is a class returned the key Black-Scholes fields
	 */
	public OptionDetails calculate(OptionDetails req) {

		OptionDetails resp = new OptionDetails(req.getCallOption(),
				req.getSpotPriceOfUnderlying(), req.getStrikePrice(),
				req.getRiskFreeInterestRate(), req.getTimeToExpire(),
				req.getVolatility());

		resp = calculate(req.getCallOption(), req.getSpotPriceOfUnderlying(),
				req.getStrikePrice(), req.getRiskFreeInterestRate(),
				req.getTimeToExpire(), req.getVolatility());

		//double response = resp.getOptionValue();
		return resp;
	}
	
	

	/**
	 * 
	 * @param s
	 *            = Spot price of underlying stock/asset
	 * @param k
	 *            = Strike price
	 * @param r
	 *            = Risk free annual interest rate continuously compounded
	 * @param t
	 *            = Time in years until option expiration (maturity)
	 * @param v
	 *            = Implied volatility of returns of underlying stock/asset
	 * @return double
	 * 			  = returns the 1st derivative
	 */	
	private static double d1(double s, double k, double r, double t, double v) {
		
		double top = Math.log(s / k) + (r + Math.pow(v, 2) / 2) * t;
		double bottom = v * Math.sqrt(t);
		double d1 = top / bottom;
		
		//System.out.println("in d1() returning [" + d1 + "]");
		return d1;
	}

	/**
	 * 
	 * @param s
	 *            = Spot price of underlying stock/asset
	 * @param k
	 *            = Strike price
	 * @param r
	 *            = Risk free annual interest rate continuously compounded
	 * @param t
	 *            = Time in years until option expiration (maturity)
	 * @param v
	 *            = Implied volatility of returns of underlying stock/asset
	 * @return double
	 *  		  = returns the second derivative
	 */
	private static double d2(double s, double k, double r, double t, double v) {
		return d1(s, k, r, t, v) - v * Math.sqrt(t);
	}

	public static double cumulativeDistribution(double x) {
		
		//System.out.println(" in BlackScholesFormula:cumulativeDitibution(" + x + ")");
		
		double t = 1 / (1 + P * Math.abs(x));
		double t1 = B1 * Math.pow(t, 1);
		double t2 = B2 * Math.pow(t, 2);
		double t3 = B3 * Math.pow(t, 3);
		double t4 = B4 * Math.pow(t, 4);
		double t5 = B5 * Math.pow(t, 5);
		double b = t1 + t2 + t3 + t4 + t5;
		
		double snd = standardNormalDistribution(x); //for testing
		double cd = 1 - (snd * b);
		
		double resp = 0.0;
		if( x < 0 ) {
			resp = 1 - cd;
		} else {
			resp = cd;
		}
		
		return resp;
	}

	/**
	 * The Abramowitz & Stegun numerical approximation above uses six constant
	 * values in its formula. However it also relies on another function in turn
	 * the standard normal probability density function (PDF)
	 * 
	 * @param x = the 1st derivative
	 * @return double = the standard normal distribution
	 */
	public static double standardNormalDistribution(double x) {
		
		//System.out.println(" in BlackScholesFormula:standardNormalDistribution(" + x + ")");
		double top = Math.exp(-0.5 * Math.pow(x, 2));
		double bottom = Math.sqrt(2 * Math.PI);
		double resp = top / bottom;		
		
		return resp;
	}
	

	/**
	 * 
	 * @param x
	 * @param sdx = teh standard distribution 
	 * @return double = the cumulative distribution
	 */
	public static double cumulativeDistribution(double x, double sdx) {

		double t = 1 / (1 + P * Math.abs(x));
		double t1 = B1 * Math.pow(t, 1);
		double t2 = B2 * Math.pow(t, 2);
		double t3 = B3 * Math.pow(t, 3);
		double t4 = B4 * Math.pow(t, 4);
		double t5 = B5 * Math.pow(t, 5);
		double b = t1 + t2 + t3 + t4 + t5;
		double cd = 1 - sdx * b;

		return x < 0 ? 1 - cd : cd;
	}

}
