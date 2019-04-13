package com.portfolio.util;

import com.portfolio.vo.FundWeight;

/**
 * Utility for displaying values in the console
 * @author Joed Cueto
 *
 */
public class OutputUtil {
	
	
	/**
	 * Output the details of fund weight
	 * @param fundWeight
	 */
	public static  void output(FundWeight fundWeight) {
		System.out.println(fundWeight.getParent() + " " + fundWeight.getChild() + " " + fundWeight.getMarketValue());
	}
	
	/**
	 * Output the details with the market value formatted to the number of decimal places provided
	 * @param fundWeight
	 * @param decimalPlaces
	 */
	public static  void output(FundWeight fundWeight, int decimalPlaces) {
		System.out.println(fundWeight.getParent() + "," + fundWeight.getChild() + "," + NumberUtil.format(fundWeight.getMarketValue(), decimalPlaces));
	}
	
	
	public static void output(String value) {
		System.out.println(value);
	}
	
	public static void outputError(Exception ex) {
		System.out.println(ex.getMessage());
	}

}
