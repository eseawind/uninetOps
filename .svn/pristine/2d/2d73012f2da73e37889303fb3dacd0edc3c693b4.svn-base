package org.unism.cau;

/**
 * Author:  
 * Rev:  
 * Date:: #:
 *
 * Copyright (C) 2008 GWSSI, Inc. All rights reserved.
 *
 * This software is the proprietary information of GWSSI, Inc.
 * Use is subject to license terms.
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class JcxxImpCommBS {

	public static StringBuffer impMsg = new StringBuffer();

	/**
	 * @方法名：println
	 * @行为： 记录输出Debug log
	 * 
	 * @返回值：void
	 */
	public static void println(String str) {
		// log = LogFactory.getLog(ImpCommBS.class);
		//
		// log.debug(str);
		// }
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		impMsg.append("[" + (sdf.format(new Date())) + "] " + str + "\r\n");
	}

	public static List transGetEchoDataInUnqiue(List allCellUnqiueList) {

		List newList = new ArrayList();

		int i = 0;
		for (Object obj1 : allCellUnqiueList) {

			if ("".equals(obj1)) {
				continue;
			} else {
				if ("".equals(obj1.toString().trim())) {
					continue;
				}
			}

			for (Object obj2 : allCellUnqiueList) {

				if (obj1.equals(obj2) && i == 0) {
					i = 1;
				} else if (obj1.equals(obj2) && i == 1) {
					newList.add(obj1);
				}

			}
			i = 0;
		}

		return newList;
	}

	/**
	 * @方法名：transGetExcelData
	 * @行为： 获取指定Excel的全部数据
	 * 
	 * @返回值：List
	 */
	public static List transGetExcelData(HSSFSheet sheet, String xlsName) {

		List allCellList = new ArrayList();
		String[] cellCNNameArray = null;

		// 获取该Excel 行数
		int rowSum = sheet.getLastRowNum();

		// 获取该Excel 列数
		int cellSum = 0;

		// 起始行
		int rowIndex = 0;

		// 供应信息表

		if (xlsName.equals(BaseInformation.NAME_Supplier)) {
			rowIndex = 1;
			cellSum = 12;

			cellCNNameArray = BaseInformation.CNNAME_Supplier;
		}
		// 采集通道应用类型信息表
		if (xlsName.equals(BaseInformation.NAME_ChannelType)) {
			rowIndex = 1;
			cellSum = 12;

			cellCNNameArray = BaseInformation.CNNAME_ChannelType;
		}
		// 控制设备类型信息表
		if (xlsName.equals(BaseInformation.NAME_DevCtrlType)) {
			rowIndex = 1;
			cellSum = 9;

			cellCNNameArray = BaseInformation.CNNAME_DevCtrlType;
		}
		// 场景信息表
		if (xlsName.equals(BaseInformation.NAME_Scene)) {
			rowIndex = 1;
			cellSum = 14;

			cellCNNameArray = BaseInformation.CNNAME_Scene;
		}
		// 设备信息表
		if (xlsName.equals(BaseInformation.NAME_Device)) {
			rowIndex = 1;
			cellSum = 15;

			cellCNNameArray = BaseInformation.CNNAME_Device;
		}

		// 网络信息表
		if (xlsName.equals(BaseInformation.NAME_DevNet)) {
			rowIndex = 1;
			cellSum = 13;

			cellCNNameArray = BaseInformation.CNNAME_DevNet;
		}
		// 控制设备信息表
		if (xlsName.equals(BaseInformation.NAME_DevCtrl)) {
			rowIndex = 1;
			cellSum = 13;

			cellCNNameArray = BaseInformation.CNNAME_DevCtrl;
		}

		// 采集通道信息表
		if (xlsName.equals(BaseInformation.NAME_Channel)) {
			rowIndex = 1;
			cellSum = 12;

			cellCNNameArray = BaseInformation.CNNAME_Channel;
		}

		// 控制设备按钮配置表
		if (xlsName.equals(BaseInformation.NAME_DevCtrlBtn)) {
			rowIndex = 1;
			cellSum = 7;

			cellCNNameArray = BaseInformation.CNNAME_DevCtrlBtn;
		}
		
		// 行政区划表
		if (xlsName.equals(BaseInformation.NAME_Areas)) {
			rowIndex = 1;
			cellSum = 3;

			cellCNNameArray = BaseInformation.CNNAME_Areas;
		}
		
		// 控制设备状态配置表
		if (xlsName.equals(BaseInformation.NAME_OpDevCtrlSts)) {
			rowIndex = 1;
			cellSum = 6;

			cellCNNameArray = BaseInformation.CNNAME_OpDevCtrlSts;
		}

		HSSFRow row = null;
		HSSFCell cell = null;

		Map rowMap = null;
		/***********************************************************************
		 * 问题描述：如果del最后几条数据(内容删空)，而不是删除行，则行数依然不变 最后几行数据，如果内容全部为空，则将行数相应减少
		 * 修改日期:2010-07-27 修改人:Roky
		 */
		int k = 0;
		boolean b = false;// 结束循环标志，为true时表示该行数据有效，并且结束循环
		for (int m = rowSum; m > rowIndex; m--, k++) {
			row = sheet.getRow(m);
			if (row == null) {
				continue;
			}
			for (int n = 0; n < cellSum; n++) {
				cell = row.getCell((short) k);
				if (cell != null) {
					b = true;
					break;
				}
			}
			if (b) {
				break;
			}
		}
		rowSum -= k;
		/** ********************** */
		for (int i = rowIndex; i <= rowSum; i++) {
			row = sheet.getRow(i);

			if (row == null) {
				continue;
			}

			rowMap = new HashMap();

			rowMap.put("ROWID", row.getRowNum());
			for (int j = 0; j < cellSum; j++) {

				cell = row.getCell((short) j);

				if (cell == null) {
					rowMap.put(cellCNNameArray[j], "");
					continue;
				}


                if (cell.getCellType() == cell.CELL_TYPE_STRING) {
                    rowMap.put(cellCNNameArray[j], cell.getStringCellValue()
                            .trim());
//                }else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
////                	if (HSSFDateUtil.isCellDateFormatted(cell)) {
////	                    Date d = cell.getDateCellValue();    
////	                    DateFormat formater = new SimpleDateFormat("yyyy-MM-dd");    
////	                    rowMap.put(cellCNNameArray[j],formater.format(d));
////                  }
////                	int s = cell.getCellStyle().getDataFormat();
////                	if(HSSFDateUtil.isInternalDateFormat(s)){
////                	     Date d = cell.getDateCellValue();    
//// 	                    DateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
//// 	                    rowMap.put(cellCNNameArray[j],formater.format(d));
////                	}else{
//                		 rowMap.put(cellCNNameArray[j], cell.getNumericCellValue());
////                	}                   
                }else {
                    rowMap.put(cellCNNameArray[j], "");
                }

			}
			allCellList.add(rowMap);

		}

		return allCellList;
	}

	/**
	 * @方法名：transGetExcelSheet
	 * @行为： 获取Excel工作表
	 * 
	 * @返回值：HSSFSheet
	 */
	public static HSSFSheet transGetExcelSheet(String fileUrl) {
		HSSFSheet sheet = null;

		POIFSFileSystem poifs;
		try {
			poifs = new POIFSFileSystem(new FileInputStream(fileUrl));

			HSSFWorkbook workBook = new HSSFWorkbook(poifs);
			sheet = workBook.getSheetAt(0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sheet;
	}

	/**
	 * @方法名：transGetExcelSheetByOutpS
	 * @行为： 获取Excel工作表
	 * 
	 * @返回值：HSSFSheet
	 */
	public static HSSFSheet transGetExcelSheetByIntpS(FileInputStream fis) {
		HSSFSheet sheet = null;

		POIFSFileSystem poifs;
		try {
			poifs = new POIFSFileSystem(fis);

			HSSFWorkbook workBook = new HSSFWorkbook(poifs);
			sheet = workBook.getSheetAt(0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sheet;
	}

	/**
	 * @方法名：transGetExcelWorkbook
	 * @行为： 获取Excel Workbook
	 * 
	 * @返回值：HSSFWorkbook
	 */
	public static HSSFWorkbook transGetExcelWorkbook(String fileUrl) {

		POIFSFileSystem poifs;
		HSSFWorkbook workBook = null;
		try {
			poifs = new POIFSFileSystem(new FileInputStream(fileUrl));

			workBook = new HSSFWorkbook(poifs);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return workBook;
	}
}
