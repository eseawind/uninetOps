package org.unism.gm.util;

import java.text.DecimalFormat;

public class NumUtils {

	public static double format(double num,int digits){
		DecimalFormat df = new DecimalFormat();
		String style = "0.";
		for (int i = 0; i < digits; i++) {
			style += "0";
		}
		df.applyPattern(style);
		return Double.parseDouble(df.format(num));
	}
}
