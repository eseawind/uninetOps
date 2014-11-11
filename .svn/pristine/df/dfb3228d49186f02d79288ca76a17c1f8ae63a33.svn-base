package org.unism.gm.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTool {
	public static String getDateTime(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if(date != null){
			return sdf.format(date);
		}
		return "";
	}
	
	public static float dateToDate(String begin,String end){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date beginTime = sdf.parse(begin);
			Date endTime = sdf.parse(end);
			long l = endTime.getTime() - beginTime.getTime();
			float s = l/1000/60;
			return s;
		} catch (Exception e) {
			return 0;
		}
	}
	
	public static String getDate(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(date != null){
			return sdf.format(date);
		}
		return "";
	}
	
	public static String getYear(long millisecond){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String ymd = sdf.format(millisecond);
		if(ymd.length()>4){
			ymd = ymd.substring(0,4);
		}
		return ymd;
	}
	
	public static Date getDate(String date,String fmt){
		SimpleDateFormat sdf = new SimpleDateFormat(fmt);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
