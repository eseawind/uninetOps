package org.unism.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DateTools {
	/**
	 * 获得最后24小时
	 * @return String yyyy-MM-dd HH:mm:ss List
	 * @author Wang_Yuliang
	 */
	public static List<String> find_24hours(){
		List<String> _24hours = new ArrayList<String>();
		_24hours.add("1970-01-01 00:00:00");
		_24hours.add("1970-01-01 01:00:00");
		_24hours.add("1970-01-01 02:00:00");
		_24hours.add("1970-01-01 03:00:00");
		_24hours.add("1970-01-01 04:00:00");
		_24hours.add("1970-01-01 05:00:00");
		
		_24hours.add("1970-01-01 06:00:00");
		_24hours.add("1970-01-01 07:00:00");
		_24hours.add("1970-01-01 08:00:00");
		_24hours.add("1970-01-01 09:00:00");
		_24hours.add("1970-01-01 10:00:00");
		_24hours.add("1970-01-01 11:00:00");
		
		_24hours.add("1970-01-01 12:00:00");
		_24hours.add("1970-01-01 13:00:00");
		_24hours.add("1970-01-01 14:00:00");
		_24hours.add("1970-01-01 15:00:00");
		_24hours.add("1970-01-01 16:00:00");
		_24hours.add("1970-01-01 17:00:00");
		
		_24hours.add("1970-01-01 18:00:00");
		_24hours.add("1970-01-01 19:00:00");
		_24hours.add("1970-01-01 20:00:00");
		_24hours.add("1970-01-01 21:00:00");
		_24hours.add("1970-01-01 22:00:00");
		_24hours.add("1970-01-01 23:00:00");
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:00:00");
			Date date_org = new Date();
			String date_str = df.format(date_org);		
			Long date_long = df.parse(date_str).getTime();			
			_24hours.set(0, df.format(new Date().getTime()-1000*60*60*23));
			_24hours.set(1, df.format(new Date().getTime()-1000*60*60*22));
			_24hours.set(2, df.format(new Date().getTime()-1000*60*60*21));
			_24hours.set(3, df.format(new Date().getTime()-1000*60*60*20));
			_24hours.set(4, df.format(new Date().getTime()-1000*60*60*19));
			_24hours.set(5, df.format(new Date().getTime()-1000*60*60*18));
			
			_24hours.set(6, df.format(new Date().getTime()-1000*60*60*17));
			_24hours.set(7, df.format(new Date().getTime()-1000*60*60*16));
			_24hours.set(8, df.format(new Date().getTime()-1000*60*60*15));
			_24hours.set(9, df.format(new Date().getTime()-1000*60*60*14));
			_24hours.set(10, df.format(new Date().getTime()-1000*60*60*13));
			_24hours.set(11, df.format(new Date().getTime()-1000*60*60*12));
			
			_24hours.set(12, df.format(new Date().getTime()-1000*60*60*11));
			_24hours.set(13, df.format(new Date().getTime()-1000*60*60*10));
			_24hours.set(14, df.format(new Date().getTime()-1000*60*60*9));
			_24hours.set(15, df.format(new Date().getTime()-1000*60*60*8));
			_24hours.set(16, df.format(new Date().getTime()-1000*60*60*7));
			_24hours.set(17, df.format(new Date().getTime()-1000*60*60*6));
			
			_24hours.set(18, df.format(new Date().getTime()-1000*60*60*5));
			_24hours.set(19, df.format(new Date().getTime()-1000*60*60*4));
			_24hours.set(20, df.format(new Date().getTime()-1000*60*60*3));
			_24hours.set(21, df.format(new Date().getTime()-1000*60*60*2));
			_24hours.set(22, df.format(new Date().getTime()-1000*60*60*1));
			_24hours.set(23, df.format(new Date().getTime()-1000*60*60*0));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return _24hours;
		}
		return _24hours;
	}
}
