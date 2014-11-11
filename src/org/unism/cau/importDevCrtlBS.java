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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.unism.cau.util.DataBaseException;
import org.unism.cau.util.GetUUID;
import org.unism.cau.util.JDBConnection;

public class importDevCrtlBS {

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

		boolean flag = false;
		String valNullMsg = "";

		for (Object obj : allCellList) {
			Map rowMap = (Map) obj;
			valNullMsg += foreignkey(rowMap);
			transFindLogoOrdID(rowMap, true);
		}
		if (!valNullMsg.equals("")) {
			JcxxImpCommBS.println(valNullMsg);
			// if (updateflag == 0) {
			// ExcelToMySql.isproject = true;
			// int i = 0, j = 0;
			// for (i = 0; i < alreadyinsert.size(); i++) {
			// deletezuixin(alreadyinsert.get(i).toString());
			// deleteDevCtrlSts(alreadyinsert.get(i).toString());
			// delete(alreadyinsert.get(i).toString());
			// }
			// importDevnetBS devnet = new importDevnetBS();
			// for (j = 0; j < devnet.alreadyinsert.size(); j++) {
			// devnet.deleteDevsts(devnet.alreadyinsert.get(i).toString());
			// devnet.delete(devnet.alreadyinsert.get(i).toString());
			// }
			// importDeviceBS device = new importDeviceBS();
			// for (j = 0; j < device.alreadyinsert.size(); j++) {
			// device.delete(device.alreadyinsert.get(i).toString());
			// }
			// importSceneBS sence = new importSceneBS();
			// for (j = 0; j < sence.alreadyinsert.size(); j++) {
			// sence.delete(sence.alreadyinsert.get(j).toString());
			// }
			// }
		} else {

			JcxxImpCommBS.println("---------------控制设备信息表导入开始--------------");
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
					ExcelToMySql.isproject = true;
					int i = 0, j = 0;
					for (i = 0; i < alreadyinsert.size(); i++) {
						try {
							deletezuixin(alreadyinsert.get(i).toString());
							deleteDevCtrlSts(alreadyinsert.get(i).toString());
							delete(alreadyinsert.get(i).toString());
						} catch (Exception e) {

						}
					}
					// importDevnetBS devnet = new importDevnetBS();
					// for (j = 0; j < devnet.alreadyinsert.size(); j++) {
					// devnet.deleteDevsts(devnet.alreadyinsert.get(i)
					// .toString());
					// devnet.delete(devnet.alreadyinsert.get(i).toString());
					// }
					// importDeviceBS device = new importDeviceBS();
					// for (j = 0; j < device.alreadyinsert.size(); j++) {
					// device.delete(device.alreadyinsert.get(j).toString());
					// }
					// importSceneBS sence = new importSceneBS();
					// for (j = 0; j < sence.alreadyinsert.size(); j++) {
					// sence.delete(sence.alreadyinsert.get(j).toString());
					// }
					JcxxImpCommBS.println("exceL第" + (i + 2)
							+ "行数据错误，取消excel导入");
					break;
				}
			}

			long endTime = System.currentTimeMillis(); // 获取结束时间
			JcxxImpCommBS.println("程序运行时间： " + (endTime - startTime)
					+ " ms\r\n总处理数据: " + dataSum + " 条\r\n成功数据: " + successSum
					+ " 条\r\n错误数据: " + errorSum + " 条");
			JcxxImpCommBS.println("---------------控制设备信息表导入结束--------------");

		}
	}

	private String foreignkey(Map rowMap) throws SQLException,
			DataBaseException {
		// TODO Auto-generated method stub
		String readyRes = "";
		String areaid = String.valueOf(rowMap.get("类型编号"));
		if (areaid.equals("") || areaid == null || areaid.equals("null")
				|| areaid == "") {
			return readyRes;
		} else {
			importDevCtrlTypeBS supid = new importDevCtrlTypeBS();
			areaid = supid.transFindLogoOrdIDByConds(areaid);
			if (areaid == "") {
				readyRes = "【控制设备信息表】中Excel 行号为"
						+ (Integer.parseInt(rowMap.get("ROWID").toString()) + 1)
						+ " 的数据【类型编号】不存在 !\r\n";
				return readyRes;
			}
		}
		areaid = String.valueOf(rowMap.get("场景编号"));
		if (areaid.equals("") || areaid == null || areaid.equals("null")
				|| areaid == "") {
			return readyRes;
		} else {
			importSceneBS seid = new importSceneBS();
			areaid = seid.transFindLogoOrdIDByConds(areaid);
			if (areaid == "") {
				readyRes += "【控制设备信息表】中Excel 行号为"
						+ (Integer.parseInt(rowMap.get("ROWID").toString()) + 1)
						+ " 的数据【场景编号】不存在 !\r\n";
				return readyRes;
			}
		}
		areaid = String.valueOf(rowMap.get("所属设备编号"));
		if (areaid.equals("") || areaid == null || areaid.equals("null")
				|| areaid == "") {
			return readyRes;
		} else {
			importDeviceBS seid = new importDeviceBS();
			areaid = seid.transFindLogoOrdIDByConds(areaid);
			if (areaid == "") {
				readyRes += "【控制设备信息表】中Excel 行号为"
						+ (Integer.parseInt(rowMap.get("ROWID").toString()) + 1)
						+ " 的数据【所属设备编号】不存在 !\r\n";
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
		if (transIsValNullCell(rowMap.get("设备编号"))) {
			msg.append("【设备编号】数据为空,");
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
		if (transIsValNullCell(rowMap.get("设备编号"))) {
			msg.append("【设备编号】为空,");
		}

		if (!msg.toString().equals("")) {
			returnMsg = msg.toString();
			msg = new StringBuffer();
			return returnMsg;
		}

		String applicantName = String.valueOf(rowMap.get("设备编号"));

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
		String sql = "SELECT dect_id FROM Gm_DevCtrl where dect_no='"
				+ applicantName + "'";
		ResultSet rs = JDBConnection.getResultSet(sql);
		while (rs.next()) {
			logoOrdID = rs.getString("dect_id");
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
			params.add(rowMap.get("设备编号"));
			params.add(rowMap.get("设备名称"));
			params.add(rowMap.get("设备序列号"));

			String leixing = String.valueOf(rowMap.get("类型编号"));
			importDevCtrlTypeBS cccScene = new importDevCtrlTypeBS();
			String qd = cccScene.transFindLogoOrdIDByConds(leixing);
			if (qd == "") {
				params.add("kong");
			} else {
				params.add(qd);
			}

			String applicantName = String.valueOf(rowMap.get("类型按钮数量"));
			if (applicantName.equals("") || applicantName == null
					|| applicantName.equals("null")
					|| applicantName.equals(null)) {
				params.add("kong");
			} else {
				params.add(rowMap.get("类型按钮数量"));
			}
			String xiaolei = String.valueOf(rowMap.get("购买时间"));
			if (xiaolei.equals("")) {
				xiaolei = "kong";
				params.add(xiaolei);
			} else {
				params.add(String.valueOf((rowMap.get("购买时间"))));
			}
			xiaolei = String.valueOf(rowMap.get("服务到期时间"));
			if (xiaolei.equals("")) {
				xiaolei = "kong";
				params.add(xiaolei);
			} else {
				params.add(String.valueOf(rowMap.get("服务到期时间")));
			}
			xiaolei = String.valueOf(rowMap.get("生效时间"));
			if (xiaolei.equals("")) {
				xiaolei = "kong";
				params.add(xiaolei);
			} else {
				params.add(String.valueOf(rowMap.get("生效时间")));
			}
			int state = 1;
			String stateString = String.valueOf(rowMap.get("设备使用状态"));
			if (stateString.equals("未用")) {
				state = 0;
			} else if (stateString.equals("在用")) {
				state = 1;
			}
			params.add(state);
			String applicantName1 = String.valueOf(rowMap.get("场景编号"));
			importSceneBS cccSceneBS = new importSceneBS();
			String seid = cccSceneBS.transFindLogoOrdIDByConds(applicantName1);
			if (seid == "") {
				params.add("kong");
			} else {
				params.add(seid);
			}
			int offerstate = 0;
			String stateString1 = String.valueOf(rowMap.get("是否对外提供服务"));
			if (stateString1.equals("否")) {
				offerstate = 0;
				params.add(offerstate);
			} else if (stateString1.equals("是")) {
				offerstate = 1;
				params.add(offerstate);
			} else if (stateString1 == "") {
				params.add("kong");
			}

			params.add(rowMap.get("设备说明"));
			String applicantName2 = String.valueOf(rowMap.get("所属设备编号"));
			if (applicantName2 == "") {
				params.add("kong");
			} else {
				importDeviceBS cccSceneBS2 = new importDeviceBS();
				String seid2 = cccSceneBS2
						.transFindLogoOrdIDByConds(applicantName2);
				params.add(seid2);
			}
			if (updateflag == 0 && errorflag == 0) {
				Insert(params);
				alreadyinsert.add(String.valueOf(rowMap.get("设备编号")));

				List params1 = new ArrayList();
				String applicantNameq = String.valueOf(rowMap.get("设备编号"));

				String logoOrdID = transFindLogoOrdIDByConds(applicantNameq);
				params1.add(logoOrdID);
				params1.add(0);
				params1.add(1);
				params1.add(null);
				InsertDevCtrlSts(params1);

				List zuixin = new ArrayList();
				String id = transFindLogoOrdIDByConds(String.valueOf(rowMap
						.get("设备编号")));
				zuixin.add(id);
				Insertzuixin(zuixin);
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

		String sqlSentence = "INSERT INTO Gm_DevCtrl(dect_id,"
				+ BaseInformation.ENNAME_XLS_DevCtrl + ") VALUES ('"
				+ GetUUID.getUUID();
		String[] cellArray = BaseInformation.ENNAME_XLS_DevCtrl.split(",");
		for (int i = 0; i < cellArray.length; i++) {
			sqlSentence += "','" + list.get(i);
		}
		sqlSentence += "')";
		sqlSentence = sqlSentence.replaceAll("'kong'", "null");
		JDBConnection.executeUpdate(sqlSentence);
	}

	public void InsertDevCtrlSts(List list) throws SQLException,
			DataBaseException {

		String sqlSentence = "INSERT INTO Gm_DevCtrlSts(decst_id,"
				+ BaseInformation.ENNAME_XLS_GmDevCtrlSts + ") VALUES ('"
				+ GetUUID.getUUID();
		String[] cellArray = BaseInformation.ENNAME_XLS_GmDevCtrlSts.split(",");
		for (int i = 0; i < cellArray.length - 1; i++) {
			sqlSentence += "','" + list.get(i);
		}
		sqlSentence += "',null)";
		sqlSentence = sqlSentence.replaceAll("'kong'", "null");
		JDBConnection.executeUpdate(sqlSentence);
	}

	public void deleteDevCtrlSts(String string) throws SQLException,
			DataBaseException {
		// TODO Auto-generated method stub
		string = transFindLogoOrdIDByConds(string);
		String sqlSentence = "delete form Gm_DevCtrlSts where dect_id='"
				+ string + "'";
		JDBConnection.executeUpdate(sqlSentence);
	}

	public void Insertzuixin(List list) throws SQLException, DataBaseException {

		String sqlSentence = "INSERT INTO Gm_DevCtrlOperate(deco_id,dect_id) VALUES ('"
				+ GetUUID.getUUID() + "','" + list.get(0) + "')";
		JDBConnection.executeUpdate(sqlSentence);
	}

	public void deletezuixin(String string) throws SQLException,
			DataBaseException {
		// TODO Auto-generated method stub
		string = transFindLogoOrdIDByConds(string);
		String sqlSentence = "delete form Gm_DevCtrlOperate where dect_id='"
				+ string + "'";
		JDBConnection.executeUpdate(sqlSentence);
	}

	public void delete(String string) throws SQLException, DataBaseException {
		// TODO Auto-generated method stub
		String sqlSentence = "delete form Gm_DevCtrl where dect_no='" + string
				+ "'";
		JDBConnection.executeUpdate(sqlSentence);
	}

	private void update(List params, String updateid2) throws SQLException,
			DataBaseException {
		// TODO Auto-generated method stub
		String sqlSentence = "update Gm_DevCtrl set ";
		String[] cellArray = BaseInformation.ENNAME_XLS_DevCtrl.split(",");
		for (int i = 0; i < cellArray.length; i++) {
			sqlSentence += cellArray[i] + "='" + params.get(i) + "',";
		}
		sqlSentence = sqlSentence.substring(0, sqlSentence.length() - 1);
		sqlSentence += " where dect_id='" + updateid2 + "'";
		sqlSentence = sqlSentence.replaceAll("'kong'", "null");
		sqlSentence = sqlSentence.replaceAll("\"", "");
		JDBConnection.executeUpdate(sqlSentence);
	}

	public static void main(String[] args) throws SQLException,
			DataBaseException {
		importDevCrtlBS cc = new importDevCrtlBS();
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
		HSSFSheet sheet = wb.getSheetAt(3);
		List allCellList = JcxxImpCommBS.transGetExcelData(sheet, "控制设备信息表");
		cc.execLogo(allCellList);
	}
}
