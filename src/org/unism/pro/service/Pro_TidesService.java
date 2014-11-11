package org.unism.pro.service;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unism.op.domain.Op_Areas;
import org.unism.op.domain.Op_Scene;
import org.unism.pro.dao.Pro_forecastDoDao;
import org.unism.pro.dao.Pro_tidesDao;
import org.unism.pro.domain.Pro_Tides;
import org.unism.pro.domain.Pro_forecastDo;
import org.wangzz.core.service.ServiceException;

@Service
@Transactional
public class Pro_TidesService {
	@Autowired
	Pro_tidesDao pro_tidesDao;
	@Autowired
	Pro_forecastDoDao pro_forecastDoDao;

	public String parseExcel(File file, String contentType)
			throws ServiceException {
		StringBuffer sb = new StringBuffer();
		try {
			Workbook workbook = new HSSFWorkbook(new FileInputStream(file));// 改的
			sb.append(generalTides(workbook.getSheetAt(0))).append("<br>");
		} catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}
		return sb.toString();
	}

	public String getCellStr(Cell cell) {
		String str = "";
		if (cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				double strNumer = cell.getNumericCellValue();
				DecimalFormat df = new DecimalFormat("0.00");
				str = df.format(strNumer);
				break;
			case Cell.CELL_TYPE_STRING:
				str = cell.getStringCellValue();
				break;
			default:
				str = cell.toString();
				break;
			}
		}
		return str;
	}
	
	/**
	 * 导入预测的数据
	 */
	@SuppressWarnings("unchecked")
	public String generalTides(Sheet sheet) {
		try{
			int maxRow = sheet.getLastRowNum();
			for (int i = 3; i <= maxRow; i++) {
				Pro_forecastDo pro_forecastDo=new Pro_forecastDo();
				Row row = sheet.getRow(i);
				Cell cellCode = null;
				try {
					cellCode = row.getCell(0);
				} catch (java.lang.NullPointerException e) {
					continue;
				}
				String fcd_dataSts = getCellStr(cellCode);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date fcd_data=sdf.parse(fcd_dataSts);//预测时间
				pro_forecastDo.setFcd_data(fcd_data);
				
				Cell cell1 = row.getCell(1);
				String fcd_dayWeather = getCellStr(cell1);//白天气象
				pro_forecastDo.setFcd_dayWeather(fcd_dayWeather);
				
				Cell cell2 = row.getCell(2);
				String fcd_nightWeather = getCellStr(cell2);//夜间气象
				pro_forecastDo.setFcd_nightWeather(fcd_nightWeather);
				
				Cell cell3 = row.getCell(3);
				String fcd_maxTempSts = getCellStr(cell3);
				Double fcd_maxTemp=Double.parseDouble(fcd_maxTempSts);//最高温度
				pro_forecastDo.setFcd_maxTemp(fcd_maxTemp);
				
				Cell cell4 = row.getCell(4);
				String fcd_minTempSts = getCellStr(cell4);
				Double fcd_minTemp=Double.parseDouble(fcd_minTempSts);//最低温度
				pro_forecastDo.setFcd_minTemp(fcd_minTemp);
				
				Cell cell5 = row.getCell(5);
				String fcd_maxDoSts = getCellStr(cell5);
				double fcd_maxDo=Double.parseDouble(fcd_maxDoSts);//溶氧最高
				pro_forecastDo.setFcd_maxDo(fcd_maxDo);
				
				Cell cell7 = row.getCell(7);
				String fcd_pmWaterTempSts = getCellStr(cell7);
				Double fcd_pmWaterTemp=Double.parseDouble(fcd_pmWaterTempSts);//下午水温
				pro_forecastDo.setFcd_pmWaterTemp(fcd_pmWaterTemp);
				
				Cell cell8 = row.getCell(8);
				String fcd_windDirect = getCellStr(cell8);//风向
				pro_forecastDo.setFcd_windDirect(fcd_windDirect);
				
				Cell cell9 = row.getCell(9);
				String fcd_windSpeed = getCellStr(cell9);//风力
				pro_forecastDo.setFcd_windSpeed(fcd_windSpeed);
				
				Cell cell14 = row.getCell(14);
				String fcd_valueSts = getCellStr(cell14);
				Double fcd_value=Double.parseDouble(fcd_valueSts);//预测最低溶氧值
				pro_forecastDo.setFcd_value(fcd_value);
				
				Cell cell15 = row.getCell(15);
				String fcd_realValueSts = getCellStr(cell15);
				Double fcd_realValue=Double.parseDouble(fcd_realValueSts);//实际最低溶氧值
				pro_forecastDo.setFcd_realValue(fcd_realValue);
				
				pro_forecastDoDao.save(pro_forecastDo);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "导入成功.";
	}

//	/**
//	 * 导入潮汐信息
//	 */
//	@SuppressWarnings("unchecked")
//	public String generalTides(Sheet sheet) {
//		double max = 0;
//		double min = 0;
//		double val1=0;
//		double val2=0;
//		double val3=0;
//		double val4=0;		
//		int maxRow = sheet.getLastRowNum();
//		for (int i = 1; i <= maxRow; i++) {
//			boolean bol1=false;
//			boolean bol2=false;
//			boolean bol3=false;
//			boolean bol4=false;
//			Row row = sheet.getRow(i);
//			Cell cellCode = null;
//			try {
//				cellCode = row.getCell(0);//
//			} catch (java.lang.NullPointerException e) {
//				continue;
//			}
//			String date = getCellStr(cellCode);
//			Cell celltime1 = row.getCell(1);// 时分1
//			String time1 = getCellStr(celltime1);// 时分1
//			Cell celltime2 = row.getCell(3);// 时分2
//			String time2 = getCellStr(celltime2);// 时分2
//			Cell celltime3 = row.getCell(5);// 时分3
//			String time3 = getCellStr(celltime3);// 时分3
//			Cell celltime4 = row.getCell(7);// 时分4
//			String time4 = getCellStr(celltime4);// 时分4
//			if (!"".equals(time1) && time1 != null) {
//				try {
//					String datetimeStr = date + " " + time1.substring(0, 2)
//							+ ":" + time1.substring(2, 4) + ":00";
//					SimpleDateFormat sdf = new SimpleDateFormat(
//							"yyyy-MM-dd HH:mm:ss");
//					Date datetime = sdf.parse(datetimeStr);
//					Cell cellvalues1 = row.getCell(2);// 潮高1
//					Double values1 = null;
//					if (!"".equals(cellvalues1) && cellvalues1 != null) {
//						values1 = Double.valueOf(getCellStr(cellvalues1));
//					}
//					String zao = date + " 05:00:00";
//					Date zaoDate = sdf.parse(zao);
//					String wan = date + " 19:00:00";
//					Date wanDate = sdf.parse(wan);
//					boolean flag1 = datetime.before(zaoDate);
//					boolean flag2 = datetime.after(wanDate);
//					val1=values1;
//					if(!flag1 && !flag2){
//						bol1=true;
//						max=val1;
//						min=val1;
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//			if (!"".equals(time2) && time2 != null) {
//				try {
//					String datetimeStr = date + " " + time2.substring(0, 2)
//							+ ":" + time2.substring(2, 4) + ":00";
//					SimpleDateFormat sdf = new SimpleDateFormat(
//							"yyyy-MM-dd HH:mm");
//					Date datetime = sdf.parse(datetimeStr);
//					Cell cellvalues2 = row.getCell(4);// 潮高2
//					Double values2 = null;
//					if (!"".equals(cellvalues2) && cellvalues2 != null) {
//						values2 = Double.valueOf(getCellStr(cellvalues2));						
//					}
//					String zao = date + " 05:00:00";
//					Date zaoDate = sdf.parse(zao);
//					String wan = date + " 19:00:00";
//					Date wanDate = sdf.parse(wan);
//					boolean flag1 = datetime.before(zaoDate);
//					boolean flag2 = datetime.after(wanDate);
//					val2=values2;
//					if(!flag1 && !flag2){						
//						bol2=true;
//						max=val2;
//						min=val2;
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//			if (!"".equals(time3) && time3 != null) {
//				try {
//					String datetimeStr = date + " " + time3.substring(0, 2)
//							+ ":" + time3.substring(2, 4) + ":00";
//					SimpleDateFormat sdf = new SimpleDateFormat(
//							"yyyy-MM-dd HH:mm");
//					Date datetime = sdf.parse(datetimeStr);
//					Cell cellvalues3 = row.getCell(6);// 潮高3
//					Double values3 = null;
//					if (!"".equals(cellvalues3) && cellvalues3 != null) {
//						values3 = Double.valueOf(getCellStr(cellvalues3));						
//					}
//					String zao = date + " 05:00:00";
//					Date zaoDate = sdf.parse(zao);
//					String wan = date + " 19:00:00";
//					Date wanDate = sdf.parse(wan);
//					boolean flag1 = datetime.before(zaoDate);
//					boolean flag2 = datetime.after(wanDate);
//					val3=values3;
//					if (!flag1 && !flag2) {						
//						bol3=true;
//						max=val3;
//						min=val3;
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//			if (!"".equals(time4) && time4 != null) {
//				try {
//					String datetimeStr = date + " " + time4.substring(0, 2)
//							+ ":" + time4.substring(2, 4) + ":00";
//					SimpleDateFormat sdf = new SimpleDateFormat(
//							"yyyy-MM-dd HH:mm:ss");
//					Date datetime = new Date();
//					datetime = sdf.parse(datetimeStr);
//					Cell cellvalues4 = row.getCell(8);// 潮高4
//					Double values4 = null;
//					if (!"".equals(cellvalues4) && cellvalues4 != null) {
//						values4 = Double.valueOf(getCellStr(cellvalues4));						
//					}
//					String zao = date + " 05:00:00";
//					Date zaoDate = sdf.parse(zao);
//					String wan = date + " 19:00:00";
//					Date wanDate = sdf.parse(wan);
//					boolean flag1 = datetime.before(zaoDate);
//					boolean flag2 = datetime.after(wanDate);
//					val4=values4;
//					if (!flag1 && !flag2) {						
//						bol4=true;
//						max=val4;
//						min=val4;
//					}
//				} catch (Exception e) {
//					// TODO: handle exception
//				}
//			}
//			
//			
//			if(bol1){
//				if(val1>max){
//					max=val1;
//				}
//				if(val1<min){
//					min=val1;
//				}
//			}
//			if(bol2){
//				if(val2>max){
//					max=val2;
//				}
//				if(val2<min){
//					min=val2;
//				}
//			}
//			if(bol3){
//				if(val3>max){
//					max=val3;
//				}
//				if(val3<min){
//					min=val3;
//				}
//			}
//			if(bol4){
//				if(val4>max){
//					max=val4;
//				}
//				if(val4<min){
//					min=val4;
//				}
//			}
//
//			if (!"".equals(time1) && time1 != null) {
//				try {
//					String datetimeStr = date + " " + time1.substring(0, 2)
//							+ ":" + time1.substring(2, 4) + ":00";
//					SimpleDateFormat sdf = new SimpleDateFormat(
//							"yyyy-MM-dd HH:mm:ss");
//					Date datetime = sdf.parse(datetimeStr);
//					Cell cellvalues1 = row.getCell(2);// 潮高1
//					Double values1 = null;
//					if (!"".equals(cellvalues1) && cellvalues1 != null) {
//						values1 = Double.valueOf(getCellStr(cellvalues1));
//					}
//					Pro_Tides pro_Tides = pro_tidesDao.findUniqueByProperty(
//							"tide_dateTime", datetime);
//					if (pro_Tides == null) {
//						pro_Tides = new Pro_Tides();
//					}
//					pro_Tides.setTide_dateTime(datetime);
//					pro_Tides.setTide_values(values1);
//					
//					String zao = date + " 05:00:00";
//					Date zaoDate = sdf.parse(zao);
//					String wan = date + " 19:00:00";
//					Date wanDate = sdf.parse(wan);
//					boolean flag1 = datetime.before(zaoDate);
//					boolean flag2 = datetime.after(wanDate);
//					if(values1 == max && !flag1 && !flag2){
//						pro_Tides.setTide_type(1);
//					}else if(values1 == min && !flag1 && !flag2){
//						pro_Tides.setTide_type(0);
//					}else {
//						pro_Tides.setTide_type(-1);
//					}
//					pro_tidesDao.saveOrUpdate(pro_Tides);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//
//			if (!"".equals(time2) && time2 != null) {
//				try {
//
//					String datetimeStr = date + " " + time2.substring(0, 2)
//							+ ":" + time2.substring(2, 4) + ":00";
//					SimpleDateFormat sdf = new SimpleDateFormat(
//							"yyyy-MM-dd HH:mm");
//					Date datetime = sdf.parse(datetimeStr);
//					Cell cellvalues2 = row.getCell(4);// 潮高2
//					Double values2 = null;
//					if (!"".equals(cellvalues2) && cellvalues2 != null) {
//						values2 = Double.valueOf(getCellStr(cellvalues2));
//					}
//					Pro_Tides pro_Tides = pro_tidesDao.findUniqueByProperty(
//							"tide_dateTime", datetime);
//					if (pro_Tides == null) {
//						pro_Tides = new Pro_Tides();
//					}
//					pro_Tides.setTide_dateTime(datetime);
//					pro_Tides.setTide_values(values2);
//					String zao = date + " 05:00:00";
//					Date zaoDate = sdf.parse(zao);
//					String wan = date + " 19:00:00";
//					Date wanDate = sdf.parse(wan);
//					boolean flag1 = datetime.before(zaoDate);
//					boolean flag2 = datetime.after(wanDate);
//					if(values2 == max && !flag1 && !flag2){
//						pro_Tides.setTide_type(1);
//					}else if (values2 == min && !flag1 && !flag2) {
//						pro_Tides.setTide_type(0);
//					} else {
//						pro_Tides.setTide_type(-1);
//					}
//					pro_tidesDao.saveOrUpdate(pro_Tides);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//
//			if (!"".equals(time3) && time3 != null) {
//				try {
//					String datetimeStr = date + " " + time3.substring(0, 2)
//							+ ":" + time3.substring(2, 4) + ":00";
//					SimpleDateFormat sdf = new SimpleDateFormat(
//							"yyyy-MM-dd HH:mm");
//					Date datetime = sdf.parse(datetimeStr);
//					Cell cellvalues3 = row.getCell(6);// 潮高3
//					Double values3 = null;
//					if (!"".equals(cellvalues3) && cellvalues3 != null) {
//						values3 = Double.valueOf(getCellStr(cellvalues3));
//					}
//					Pro_Tides pro_Tides = pro_tidesDao.findUniqueByProperty(
//							"tide_dateTime", datetime);
//					if (pro_Tides == null) {
//						pro_Tides = new Pro_Tides();
//					}
//					pro_Tides.setTide_dateTime(datetime);
//					pro_Tides.setTide_values(values3);
//					String zao = date + " 05:00:00";
//					Date zaoDate = sdf.parse(zao);
//					String wan = date + " 19:00:00";
//					Date wanDate = sdf.parse(wan);
//					boolean flag1 = datetime.before(zaoDate);
//					boolean flag2 = datetime.after(wanDate);
//					if (values3 == max && !flag1 && !flag2) {
//						pro_Tides.setTide_type(1);
//					}else if (values3 == min && !flag1 && !flag2) {
//						pro_Tides.setTide_type(0);
//					} else {
//						pro_Tides.setTide_type(-1);
//					}
//					pro_tidesDao.saveOrUpdate(pro_Tides);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//
//			if (!"".equals(time4) && time4 != null) {
//				try {
//
//					String datetimeStr = date + " " + time4.substring(0, 2)
//							+ ":" + time4.substring(2, 4) + ":00";
//					SimpleDateFormat sdf = new SimpleDateFormat(
//							"yyyy-MM-dd HH:mm:ss");
//					Date datetime = new Date();
//					datetime = sdf.parse(datetimeStr);
//					Cell cellvalues4 = row.getCell(8);// 潮高4
//					Double values4 = null;
//					if (!"".equals(cellvalues4) && cellvalues4 != null) {
//						values4 = Double.valueOf(getCellStr(cellvalues4));
//					}
//					Pro_Tides pro_Tides = pro_tidesDao.findUniqueByProperty(
//							"tide_dateTime", datetime);
//					if (pro_Tides == null) {
//						pro_Tides = new Pro_Tides();
//					}
//					pro_Tides.setTide_dateTime(datetime);
//					pro_Tides.setTide_values(values4);
//					String zao = date + " 05:00:00";
//					Date zaoDate = sdf.parse(zao);
//					String wan = date + " 19:00:00";
//					Date wanDate = sdf.parse(wan);
//					boolean flag1 = datetime.before(zaoDate);
//					boolean flag2 = datetime.after(wanDate);
//					if (values4 == max && !flag1 && !flag2) {
//						pro_Tides.setTide_type(1);
//					}else if (values4 == min && !flag1 && !flag2) {
//						pro_Tides.setTide_type(0);
//					} else {
//						pro_Tides.setTide_type(-1);
//					}
//					pro_tidesDao.saveOrUpdate(pro_Tides);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//
//		}
//		return "导入成功.";
//	}

}
