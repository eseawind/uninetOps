package org.unism.gm.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeCompare
{
	public static boolean compare(String beginTime,String endTime){
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date begin = sdf.parse(beginTime);
			Date end = sdf.parse(endTime);
			if(begin.getTime() < end.getTime()){
				return true;
			}else {
				return false;
			}
			
		} catch (Exception e)
		{
			return false;
			// TODO: handle exception
		}
	}
}
