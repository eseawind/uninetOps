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
import org.unism.cau.util.GetUUID;
import org.unism.cau.util.JDBConnection;

public class importDevCtrlTypeBS {

	public static List alreadyinsert = new ArrayList();

	public static int updateflag = 0;

	public static int errorflag = 0;

	public static String updateid;

	public void execLogo(List allCellList) throws SQLException,
			DataBaseException {

		long startTime = System.currentTimeMillis(); // 获取开始时间
		int dataSum = 0;
		int errorSum = 0;
		int successSum = 0;

		// String xlsName = WghBaseImpConstant.NAME_WGHLOGO;

		JcxxImpCommBS.println("---------------控制设备类型信息表导入开始--------------");
		StringBuffer errorMsg = new StringBuffer("");
		importDevCtrlTypeBS importbs = new importDevCtrlTypeBS();

		boolean flag = false;
		String valNullMsg = "";

		for (Object obj : allCellList) {
			if (errorflag == 0) {
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
											.toString()) + 1)
									+ " 的数据出现以下问题:\r\n" + errorMsg + "\r\n");
					errorMsg = new StringBuffer("");

					errorSum++;
					continue;
				} else if (1 == 1) {
					if (importbs.transImportWghLogoData(rowMap)) {
						JcxxImpCommBS.println("Excel 行号为"
								+ (Integer.parseInt(rowMap.get("ROWID")
										.toString()) + 1) + " 的数据: 导入成功 !");

						successSum++;
					} else {
						JcxxImpCommBS.println("Excel 行号为"
								+ (Integer.parseInt(rowMap.get("ROWID")
										.toString()) + 1) + " 的数据: 导入失败 !");
						errorSum++;
					}
				}
			} else if (errorflag == 1 && updateflag == 0) {
				ExcelToMySql.isbase = true;
				int i = 0, j = 0;
				for (i = 0; i < alreadyinsert.size(); i++) {
					delete(alreadyinsert.get(i).toString());
				}
//				importChannelTypeBS channel =new importChannelTypeBS();
//				for (j = 0; j < channel.alreadyinsert.size(); j++) {
//					channel.delete(channel.alreadyinsert.get(j).toString());
//				}
//				importSupplierBS sup=new importSupplierBS();
//				for (j = 0; j < sup.alreadyinsert.size(); j++) {
//					sup.delete(sup.alreadyinsert.get(j).toString());
//				}
				JcxxImpCommBS.println("exceL第" + (i + 2) + "行数据错误，取消excel导入");
				break;
			}
		}

		long endTime = System.currentTimeMillis(); // 获取结束时间
		JcxxImpCommBS.println("程序运行时间： " + (endTime - startTime)
				+ " ms\r\n总处理数据: " + dataSum + " 条\r\n成功数据: " + successSum
				+ " 条\r\n错误数据: " + errorSum + " 条");
		JcxxImpCommBS.println("---------------控制设备类型信息表导入结束--------------");
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
		if (transIsValNullCell(rowMap.get("类型编号"))) {
			msg.append("【类型编号】数据为空,");
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
		if (transIsValNullCell(rowMap.get("类型编号"))) {
			msg.append("【类型编号】为空,");
		}

		if (!msg.toString().equals("")) {
			returnMsg = msg.toString();
			msg = new StringBuffer();
			return returnMsg;
		}

		String applicantName = String.valueOf(rowMap.get("类型编号"));

		String logoOrdID = transFindLogoOrdIDByConds(applicantName);

		// 是否为验证 ?
		// 1. 数据验证情况
		if (!"".equals(logoOrdID) && isVal) {
			returnMsg = "";
			updateflag = 1;
			updateid = logoOrdID;
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
		String sql = "SELECT decttype_id FROM Op_DevCtrlType where decttype_No='"
				+ applicantName + "'";
		ResultSet rs = JDBConnection.getResultSet(sql);
		while (rs.next()) {
			logoOrdID = rs.getString("decttype_id");
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
			params.add(rowMap.get("类型编号"));
			params.add(rowMap.get("类型名称"));
			params.add(rowMap.get("类型型号"));
			String gonglv = String.valueOf(rowMap.get("类型功率"));
			if (gonglv.equals("")||gonglv=="") {
				gonglv = "kong";
			}
			params.add(gonglv);
			String gonglv1 = String.valueOf(rowMap.get("类型供电电压"));
			if (gonglv1.equals("")||gonglv1=="") {
				gonglv1 = "kong";
			}
			params.add(gonglv1);
			params.add(rowMap.get("类型说明"));
			params.add(rowMap.get("类型图片资源"));
			gonglv = String.valueOf(rowMap.get("类型按钮数量"));
			if (gonglv.equals("")) {
				gonglv = "kong";
			}
			params.add(gonglv);
			gonglv = String.valueOf(rowMap.get("通道状态反馈数量"));
			if (gonglv.equals("")) {
				gonglv = "kong";
			}
			params.add(gonglv);
			if (updateflag == 0 && errorflag == 0) {
				Insert(params);
				alreadyinsert.add(String.valueOf(rowMap.get("类型编号")));
			} else if (updateflag == 1 && errorflag == 0) {
				update(params, updateid);
				updateflag = 0;
			}
			isOK = true;
		} catch (Exception e) {
			JcxxImpCommBS.println("[Import Error] :" + e);
			errorflag = 1;
		}

		return isOK;
	}

	public void Insert(List list) throws SQLException, DataBaseException {

		String sqlSentence = "INSERT INTO Op_DevCtrlType(decttype_id,"
				+ BaseInformation.ENNAME_XLS_DevCtrlType + ") VALUES ('"
				+ GetUUID.getUUID();
		String[] cellArray = BaseInformation.ENNAME_XLS_DevCtrlType.split(",");
		for (int i = 0; i < cellArray.length; i++) {
			sqlSentence += "','" + list.get(i);
		}
		sqlSentence += "')";
		sqlSentence = sqlSentence.replaceAll("'kong'", "null");
		JDBConnection.executeUpdate(sqlSentence);
	}

	public void update(List list, String id) throws SQLException,
			DataBaseException {

		String sqlSentence = "update Op_DevCtrlType set ";
		String[] cellArray = BaseInformation.ENNAME_XLS_DevCtrlType.split(",");
		for (int i = 0; i < cellArray.length; i++) {
			sqlSentence += cellArray[i] + "='" + list.get(i) + "',";
		}
		sqlSentence = sqlSentence.substring(0, sqlSentence.length() - 1);
		sqlSentence += " where decttype_id='" + id + "'";
		sqlSentence = sqlSentence.replaceAll("'kong'", "null");
		JDBConnection.executeUpdate(sqlSentence);
	}

	public void delete(String id) throws SQLException, DataBaseException {

		String sqlSentence = "delete form Op_DevCtrlType where decttype_no='"
				+ id + "'";
		JDBConnection.executeUpdate(sqlSentence);
	}

	public static void main(String[] args) throws SQLException,
			DataBaseException {
		importDevCtrlTypeBS cc = new importDevCtrlTypeBS();
		InputStream myxls = null;
		HSSFWorkbook wb = null;
		try {
			myxls = new FileInputStream("F:\\2011\\基本表1.xls");
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
		HSSFSheet sheet = wb.getSheetAt(2);
		List allCellList = JcxxImpCommBS.transGetExcelData(sheet, "控制设备类型信息表");
		cc.execLogo(allCellList);
	}
}
