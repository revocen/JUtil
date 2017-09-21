package org.revocen.util.common;

public class DoubleUtil {

	public static double getValue(Double target){
		if (target == null) {
			return 0d;
		}
		return (double)target;
	}
}
