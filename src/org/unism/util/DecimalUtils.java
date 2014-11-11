package org.unism.util;

import java.text.DecimalFormat;

public class DecimalUtils {

	/**
	 * 指定double数值,小数位数（>0整数） 返回格式化字符串 (四舍五入)
	 * @param number
	 * @param ff
	 * @return
	 * @author Wang_Yuliang
	 */
	public static String subDecimal(Double number,Integer ff){
		String result = null;
		String formatString = "0.00";
		if(ff!=null){
			if(ff>0){
				formatString = formatString.substring(0,2);
				for(int i=0;i<ff;i++){
					formatString += "0";
				}
			}else if(ff.equals(0)){
				formatString = "0";
			}
		}		
		DecimalFormat df = new DecimalFormat(formatString);
		if(number!=null){
			result = df.format(number);
		}
		return result;
	}
	
	/**
	 * 指定double数值,保留两位小数 返回格式化字符串 (四舍五入)
	 * @param number
	 * @param ff
	 * @return
	 * @author Wang_Yuliang
	 */
	public static String subDecimal(Double number){
		String result = null;
		String formatString = "0.00";	
		DecimalFormat df = new DecimalFormat(formatString);
		if(number!=null){
			result = df.format(number);
		}
		return result;
	}
}
