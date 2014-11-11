package org.unism.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringToDate
{
	public Date toDate(String str){
		try
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.parse(str);
		} catch (Exception e)
		{
			// TODO: handle exception
		}
		return null;
	}
}
