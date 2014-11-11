/**
 * @project : import
 * @author: ZhuYang 
 * @date: 9:41:41 AM Apr 14, 2011
 * TODO
 */
package org.unism.cau;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.unism.cau.util.DataBaseException;
import org.unism.cau.util.GetUUID;
import org.unism.cau.util.JDBConnection;

public class importSceneBS {

	public static int chongfuflag = 0;

	public List alreadyinsert = new ArrayList();

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

		boolean flag = false;
		String valNullMsg = "";

		for (Object obj : allCellList) {
			Map rowMap = (Map) obj;
			valNullMsg += foreignkey(rowMap);
			transFindLogoOrdID(rowMap, true);
		}
		if (!valNullMsg.equals("")) {
			JcxxImpCommBS.println(valNullMsg);
			ExcelToMySql.isproject=true;
		} else {
			JcxxImpCommBS.println("---------------场景信息表导入开始--------------");
			StringBuffer errorMsg = new StringBuffer("");
			importSupplierBS importbs = new importSupplierBS();
			for (Object obj : allCellList) {
				if (errorflag == 0) {

					dataSum++;
					Map rowMap = (Map) obj;

					String readyRes = transReadyImportWghLogo(rowMap);
					if (!readyRes.equals("")) {
						errorMsg.append(readyRes);
					} else {
						if (!"".equals(errorMsg.toString())) {
							errorMsg.deleteCharAt(errorMsg.length() - 1);
						}
					}

					if (!errorMsg.toString().equals("")) {
						JcxxImpCommBS.println("Excel 行号为"
								+ (Integer.parseInt(rowMap.get("ROWID")
										.toString()) + 1) + " 的数据出现以下问题:\r\n"
								+ errorMsg + "\r\n");
						errorMsg = new StringBuffer("");

						errorSum++;
						continue;
					} else if (1 == 1) {
						if (transImportWghLogoData(rowMap)) {
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
					ExcelToMySql.isproject=true;
					int i = 0;
					for (i = 0; i < alreadyinsert.size(); i++) {
						delete(alreadyinsert.get(i).toString());
					}
					JcxxImpCommBS.println("exceL第" + (i+2) + "行数据错误，取消excel导入");
					break;
				}
			}

			long endTime = System.currentTimeMillis(); // 获取结束时间
			JcxxImpCommBS.println("程序运行时间： " + (endTime - startTime)
					+ " ms\r\n总处理数据: " + dataSum + " 条\r\n成功数据: " + successSum
					+ " 条\r\n错误数据: " + errorSum + " 条");
			JcxxImpCommBS.println("---------------场景信息表导入结束--------------");
			chongfuflag++;
			if (chongfuflag == 1) {
				execLogo(allCellList);
			}
		}
	}

	private String foreignkey(Map rowMap) throws SQLException,
			DataBaseException {
		// TODO Auto-generated method stub
		String readyRes = "";
		String areaid = String.valueOf(rowMap.get("区划ID"));
		if (areaid.equals("") || areaid == null || areaid.equals("null")
				|| areaid == "") {
			return readyRes;
		} else {
			areaid = findareasid(areaid);
			if (areaid == "") {
				readyRes = "【场景信息表】中Excel 行号为"
						+ (Integer.parseInt(rowMap.get("ROWID").toString()) + 1)
						+ " 的数据【区划ID】不存在 !\r\n";
				return readyRes;
			}
		}
		return readyRes;
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
		if (transIsValNullCell(rowMap.get("场景编号"))) {
			msg.append("【场景编号】数据为空,");
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
		if (transIsValNullCell(rowMap.get("场景编号"))) {
			msg.append("【场景编号】为空,");
		}

		if (!msg.toString().equals("")) {
			returnMsg = msg.toString();
			msg = new StringBuffer();
			return returnMsg;
		}

		String applicantName = String.valueOf(rowMap.get("场景编号"));

		String logoOrdID = transFindLogoOrdIDByConds(applicantName);

		// 是否为验证 ?
		// 1. 数据验证情况
		if (chongfuflag == 1) {
			returnMsg = logoOrdID;
		} else {
			if (!"".equals(logoOrdID) && isVal) {
				returnMsg = "";
				updateflag = 1;
				updateid = logoOrdID;
			}
			// 2. 数据插入情况
			else {
				returnMsg = logoOrdID;
			}
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
		String sql = "SELECT scene_id FROM Op_Scene where scene_no='"
				+ applicantName + "'";
		ResultSet rs = JDBConnection.getResultSet(sql);
		while (rs.next()) {
			logoOrdID = rs.getString("scene_id");
		}
		return logoOrdID;
	}

	public String transFindLogoOrdIDByConds1(String applicantName)
			throws SQLException, DataBaseException {

		String logoOrdID = "";
		String sql = "SELECT scene_id FROM Op_Scene where scene_no='"
				+ applicantName + "'";
		ResultSet rs = JDBConnection.getResultSet(sql);
		while (rs.next()) {
			logoOrdID = rs.getString("scene_id");
		}
		return logoOrdID;
	}

	public String findareasid(String applicantName) throws SQLException,
			DataBaseException {

		String logoOrdID = "";
		String sql = "SELECT area_id FROM Op_Areas where area_id='"
				+ applicantName + "'";
		ResultSet rs = JDBConnection.getResultSet(sql);
		while (rs.next()) {
			logoOrdID = rs.getString("area_id");
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		try {
			if (chongfuflag == 1) {
				String applicantName = String.valueOf(rowMap.get("所属场景父编号"));
				if (applicantName.equals("") || applicantName == "") {

				} else {
					String logoOrdID = transFindLogoOrdIDByConds(applicantName);
					update1(logoOrdID, String.valueOf(rowMap.get("场景编号")));
					isOK = true;
				}
			} else {
				List params = new ArrayList();
				params.add(rowMap.get("场景编号"));
				params.add(rowMap.get("场景所在地"));
				SimpleDateFormat sdf1 = new SimpleDateFormat(
						"yyyy-MM-dd hh:mm:ss");
				sdf1.format(new Date());
				params.add(sdf1.format(new Date()));
				params.add(rowMap.get("创建者"));
				params.add(rowMap.get("场景的说明"));
				params.add(rowMap.get("关键字"));
				params.add(rowMap.get("场景名称"));
				params.add("FF");
				int typeint = 0;
				String typeString = String.valueOf(rowMap.get("场景类型"));
				if (typeString.equals("设施园艺")) {
					typeint = 1;
					params.add(typeint);
				} else if (typeString.equals("水产养殖")) {
					typeint = 2;
					params.add(typeint);
				} else if (typeString.equals("大田种植")) {
					typeint = 3;
					params.add(typeint);
				} else if (typeString.equals("畜禽养殖")) {
					typeint = 4;
					params.add(typeint);
				} else if (typeString == "") {
					params.add("kong");
				}
				int subtypeint = 0;
				String subtypeString = String.valueOf(rowMap.get("场景类型子类"));
				if (subtypeString.equals("设施蔬菜")) {
					subtypeint = 11;
					params.add(subtypeint);
				} else if (subtypeString.equals("设施花卉")) {
					subtypeint = 12;
					params.add(subtypeint);
				} else if (subtypeString.equals("池塘水产养殖")) {
					subtypeint = 21;
					params.add(subtypeint);
				} else if (subtypeString.equals("设施水产养殖")) {
					subtypeint = 22;
					params.add(subtypeint);
				} else if (subtypeString == "") {
					params.add("kong");
				}
				params.add(rowMap.get("所属等级"));
				params.add(rowMap.get("场景的图片"));
				params.add(rowMap.get("定制路径"));
				String areaid = String.valueOf(rowMap.get("区划ID"));
				if (areaid.equals("")||areaid=="") {
					params.add("kong");
				} else {
					importAreasBS area = new importAreasBS();
					String a = area.transFindLogoOrdIDByConds(areaid);
					params.add(a);
				}
				int state = 0;
				String stateString = String.valueOf(rowMap.get("场景使用状态"));
				if (stateString.equals("未用")) {
					state = 0;
				} else if (stateString.equals("在用")) {
					state = 1;
				}
				params.add(state);
				if (updateflag == 0 && errorflag == 0) {
					Insert(params);
					alreadyinsert.add(String.valueOf(rowMap.get("场景编号")));
				} else if (updateflag == 1 && errorflag == 0) {
					update(params, updateid);
					updateflag=0;
				}
			}
			isOK = true;
		} catch (Exception e) {
			JcxxImpCommBS.println("[Import Error] :" + e);
			errorflag=1;
		}

		return isOK;
	}

	public void Insert(List list) throws SQLException, DataBaseException {

		String sqlSentence = "INSERT INTO Op_Scene(scene_id,"
				+ BaseInformation.ENNAME_XLS_Scene + ") VALUES ('"
				+ GetUUID.getUUID();
		String[] cellArray = BaseInformation.ENNAME_XLS_Scene.split(",");
		for (int i = 0; i < cellArray.length; i++) {
			if (list.get(i).equals("")) {
				sqlSentence += "',null";
			} else {
				sqlSentence += "','" + list.get(i);
			}
		}
		sqlSentence = sqlSentence.replace("null'", "null");
		sqlSentence = sqlSentence.replace("'kong'", "null");
		sqlSentence += "')";
		JDBConnection.executeUpdate(sqlSentence);
	}

	public void update(List list, String id) throws SQLException,
			DataBaseException {

		String sqlSentence = "update Op_Scene set ";
		String[] cellArray = BaseInformation.ENNAME_XLS_Scene.split(",");
		for (int i = 0; i < cellArray.length; i++) {
			sqlSentence += cellArray[i] + "='" + list.get(i) + "',";
		}
		sqlSentence = sqlSentence.substring(0, sqlSentence.length() - 1);
		sqlSentence += " where scene_id='" + id + "'";
		sqlSentence = sqlSentence.replaceAll("'kong'", "null");
		JDBConnection.executeUpdate(sqlSentence);
	}

	public void delete(String id) throws SQLException, DataBaseException {

		String sqlSentence = "delete form Op_Scene where scene_no='" + id + "'";
		JDBConnection.executeUpdate(sqlSentence);
	}

	public void update1(String list, String aa) throws SQLException,
			DataBaseException {

		String sqlSentence = "update Op_Scene set scene_pid='" + list
				+ "' where scene_no='" + aa + "'";
		JDBConnection.executeUpdate(sqlSentence);
	}

	public static void main(String[] args) throws SQLException,
			DataBaseException {
		importSceneBS cc = new importSceneBS();
		InputStream myxls = null;
		HSSFWorkbook wb = null;
		try {
			myxls = new FileInputStream("F:\\2011\\数据表1.xls");
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
		List allCellList = JcxxImpCommBS.transGetExcelData(sheet, "场景信息表");
		cc.execLogo(allCellList);
	}

}
