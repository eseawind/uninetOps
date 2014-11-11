package org.unism.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateUtil {
 
	private static Logger logger = Logger.getLogger(DateUtil.class);
	
	public static String formatDate(Date date,String fmt){
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		return sdf.format(date);
	}
	
	public static Date parseDate(String date,String fmt){
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			logger.error("日期  "+ date +"  格式化异常", e);
			return null;
		}
	}
	
	/**
	 * 
	 * @param date1
	 * @param date2
	 * @return 如果date1大于date2返回true
	 */
	public static boolean dateCompare(Date date1,Date date2){
		if(date1.getTime()-date2.getTime() > 0){
			return true;
		}
		return false;
	}
}
