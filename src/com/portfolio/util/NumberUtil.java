package com.portfolio.util;

/**
 * Utility for Number Format
 * 
 * @author Joed Cueto
 *
 */
public class NumberUtil {

	public static double round(double value, int places) {

		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		return (double) tmp / factor;
	}

	public static String format(double value, int places) {

		if (places < 0)
			throw new IllegalArgumentException();

		long factor = (long) Math.pow(10, places);
		value = value * factor;
		long tmp = Math.round(value);
		String val = "" + ((double) tmp / factor);
		String[] splitted = val.split("\\.");
		if (splitted[1].length()<places) {
			for (int a=0; a<(places-splitted[1].length()); a++) {
				val = val + "0";
			}
		}
		
		return val;
	}

}
