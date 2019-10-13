////////////////////////////////////////////////////////////////////////////////
// Copyright (c) 2019. Arley Henostroza Mazmela
////////////////////////////////////////////////////////////////////////////////

package CalculateCheckpay;

public class Validator {
	public static boolean isValid(String str) {
		if (str.isEmpty()) {
			return false;
		} else if (str.endsWith(".") || str.charAt(0) == '.') {
			return false;
		} else if (str.contains(".")) {
			return str.lastIndexOf('.') == str.indexOf('.');
		}
		return true;
	}
}
