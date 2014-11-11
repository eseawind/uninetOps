/**
 * @project : import
 * @author: ZhuYang 
 * @date: 6:47:21 PM Apr 13, 2011
 * TODO
 */
package org.unism.cau;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.unism.cau.util.DataBaseException;
import org.unism.cau.util.JDBConnection;


public class importAreasBS {

	public void execLogo(List allCellList) throws SQLException,
			DataBaseException {

		long startTime = System.currentTimeMillis(); // 获取开始时间
		int dataSum = 0;
		int errorSum = 0;
		int successSum = 0;

		// String xlsName = WghBaseImpConstant.NAME_WGHLOGO;

		JcxxImpCommBS.println("---------------行政区划表导入开始--------------");
		StringBuffer errorMsg = new StringBuffer("");
		importAreasBS importbs = new importAreasBS();

		boolean flag = false;
		String valNullMsg = "";

		for (Object obj : allCellList) {

			dataSum++;
			Map rowMap = (Map) obj;

			String readyRes = importbs.transReadyImportWghLogo(rowMap);
			if (!readyRes.equals("")) {
				errorMsg.append(readyRes);
			} else {
				if (!"".equals(errorMsg.toString())) {
					errorMsg.deleteCharAt(errorMsg.length() - 1);
				}
			}

			if (!errorMsg.toString().equals("")) {
				JcxxImpCommBS
						.println("Excel 行号为"
								+ (Integer.parseInt(rowMap.get("ROWID")
										.toString()) + 1) + " 的数据出现以下问题:\r\n"
								+ errorMsg + "\r\n");
				errorMsg = new StringBuffer("");

				errorSum++;
				continue;
			} else if (1 == 1) {
				if (importbs.transImportWghLogoData(rowMap)) {
					JcxxImpCommBS
							.println("Excel 行号为"
									+ (Integer.parseInt(rowMap.get("ROWID")
											.toString()) + 1) + " 的数据: 导入成功 !");

					successSum++;
				} else {
					JcxxImpCommBS
							.println("Excel 行号为"
									+ (Integer.parseInt(rowMap.get("ROWID")
											.toString()) + 1) + " 的数据: 导入失败 !");
					errorSum++;
				}
			}

		}

		long endTime = System.currentTimeMillis(); // 获取结束时间
		JcxxImpCommBS.println("程序运行时间： " + (endTime - startTime)
				+ " ms\r\n总处理数据: " + dataSum + " 条\r\n成功数据: " + successSum
				+ " 条\r\n错误数据: " + errorSum + " 条");
		JcxxImpCommBS.println("---------------行政区划表导入结束--------------");
	}

	/**
	 * @throws DataBaseException
	 * @throws SQLException
	 * @方法名：transReadyImportWghLogo
	 * @行为： 导入Excel之前的检查准备
	 * 
	 * @返回值：String
	 */
	public String transReadyImportWghLogo(Map rowMap) throws SQLException,
			DataBaseException {

		String msg = "";

		msg = transReadyImportWghLogoProd(rowMap);
		if (msg.equals("")) {
			msg = transFindLogoOrdID(rowMap, true);

			if (msg.indexOf("【") <= -1) {
				msg = "";
			}
		}

		if (!msg.equals("")) {
			msg = msg.substring(0, msg.length() - 1);
		}

		return msg;
	}

	/**
	 * @方法名：transReadyImportWghLogoProd
	 * @行为： 导入Excel之前的检查准备
	 * 
	 * @返回值：String
	 */
	public String transReadyImportWghLogoProd(Map rowMap) {

		StringBuffer msg = new StringBuffer("");

		// prod_cer_no

		// 数据的非空验证
		if (transIsValNullCell(rowMap.get("代码"))) {
			msg.append("【代码】数据为空,");
			return msg.toString();
		}
		return msg.toString();
	}

	public boolean transIsValNullCell(Object obj) {

		boolean isError = false;

		if (obj == null || "null".equals(obj)) {
			isError = true;
		} else if (String.valueOf(obj).trim().equals("")) {
			isError = true;
		}

		return isError;
	}

	/**
	 * @throws DataBaseException
	 * @throws SQLException
	 * @方法名：transFindLogoOrdID
	 * @行为： 查找LOGO_ORD_ID
	 * 
	 * @返回值：WghLogoImpBSImpl
	 */
	public String transFindLogoOrdID(Map rowMap, boolean isVal)
			throws SQLException, DataBaseException {

		StringBuffer msg = new StringBuffer("");
		String returnMsg = "";

		// 数据的非空验证
		if (transIsValNullCell(rowMap.get("代码"))) {
			msg.append("【代码】为空,");
		}

		if (!msg.toString().equals("")) {
			returnMsg = msg.toString();
			msg = new StringBuffer();
			return returnMsg;
		}

		String applicantName = String.valueOf(rowMap.get("代码"));
		String logoOrdID = transFindLogoOrdIDByConds(applicantName);

		// 是否为验证 ?
		// 1. 数据验证情况
		if (!"".equals(logoOrdID) && isVal) {
			returnMsg = "【该项数据已经在数据库中存在】,";
		}
		// 2. 数据插入情况
		else {
			returnMsg = logoOrdID;
		}

		return returnMsg;
	}

	/**
	 * @throws DataBaseException
	 * @throws SQLException
	 * @方法名：transFindLogoOrdIDByConds
	 * @行为： 获取已导入的标志征订单项目ID
	 * 
	 * @返回值：String
	 */
	public String transFindLogoOrdIDByConds(String applicantName)
			throws SQLException, DataBaseException {

		String logoOrdID = "";
		String sql = "SELECT area_id FROM Op_Areas where area_id='"
				+ applicantName+"'";
		try {
			ResultSet rs = JDBConnection.getResultSet(sql);
			while (rs.next()) {
				logoOrdID = rs.getString("area_id");
			}
		} catch (NullPointerException e) {
			logoOrdID = "";
		}

		return logoOrdID;
	}

	/*
	 * 导入数据
	 */
	public boolean transImportWghLogoData(Map rowMap) {

		boolean isOK = false;

		if (transImportWghLogoOrdData(rowMap)) {
			isOK = true;
		}

		return isOK;
	}

	/**
	 * @方法名：transImportWghLogoOrdData
	 * @行为： 在读取的列表中获取与之相关的数据,并导入
	 * 
	 * @返回值：boolean
	 */
	public boolean transImportWghLogoOrdData(Map rowMap) {

		boolean isOK = false;

		try {
			List params = new ArrayList();
			params.add(rowMap.get("代码"));
			params.add(rowMap.get("名称"));
			params.add(rowMap.get("说明"));
			Insert(params);
			isOK = true;
		} catch (Exception e) {
			JcxxImpCommBS.println("[Import Error] :" + e);
		}

		return isOK;
	}

	public void Insert(List list) throws SQLException, DataBaseException {

		String sqlSentence = "INSERT INTO Op_Areas("
				+ BaseInformation.ENNAME_XLS_Areas + ") VALUES ('"+list.get(0)+"','"+list.get(1)+"','"+list.get(2);
		sqlSentence += "')";
		JcxxImpCommBS.println(sqlSentence);
		JDBConnection.executeUpdate(sqlSentence);
	}

	public static void main(String[] args) throws SQLException,
			DataBaseException {
		importAreasBS cc = new importAreasBS();
		InputStream myxls = null;
		HSSFWorkbook wb = null;
		try {

			myxls = new FileInputStream(
					"F:\\全国行政区划国标编码.xls");

			//myxls = new FileInputStream("F:\\2011\\行政区划表.xls");

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			wb = new HSSFWorkbook(myxls);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HSSFSheet sheet = wb.getSheetAt(0);
		List allCellList = JcxxImpCommBS.transGetExcelData(sheet, "行政区划表");
		cc.execLogo(allCellList);
	}
}
