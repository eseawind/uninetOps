package org.unism.cau;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.unism.cau.util.DataBaseException;

public class ExcelToMySql {

	public static boolean isbase = false;

	public static boolean isproject = false;

	private void execLDevCtrl(List allCellList3) throws SQLException,
			DataBaseException {
		// TODO Auto-generated method stub
		importDevCrtlBS cc = new importDevCrtlBS();
		cc.execLogo(allCellList3);
	}

	private void execLSupplier(List allCellList) throws SQLException,
			DataBaseException {
		// TODO Auto-generated method stub
		importSupplierBS cc = new importSupplierBS();
		cc.execLogo(allCellList);
	}

	private void execLDevCtrlBtn(List allCellList5) throws SQLException,
			DataBaseException {
		// TODO Auto-generated method stub
		importDevCtrlBtnBS cc = new importDevCtrlBtnBS();
		cc.execLogo(allCellList5);
	}

	private void execLDevCtrlType(List allCellList2) throws SQLException,
			DataBaseException {
		// TODO Auto-generated method stub
		importDevCtrlTypeBS cc = new importDevCtrlTypeBS();
		cc.execLogo(allCellList2);
	}

	private void execLChannel(List allCellList4) throws SQLException,
			DataBaseException {
		// TODO Auto-generated method stub
		importChannelBS cc = new importChannelBS();
		cc.execLogo(allCellList4);
	}

	private void execLDevNet(List allCellList2) throws SQLException,
			DataBaseException {
		// TODO Auto-generated method stub
		importDevnetBS cc = new importDevnetBS();
		cc.execLogo(allCellList2);
	}

	private void execLDevice(List allCellList1) throws SQLException,
			DataBaseException {
		// TODO Auto-generated method stub
		importDeviceBS cc = new importDeviceBS();
		cc.execLogo(allCellList1);
	}

	private void execLsence(List allCellList) throws SQLException,
			DataBaseException {
		// TODO Auto-generated method stub
		importSceneBS cc = new importSceneBS();
		cc.execLogo(allCellList);
	}

	private void execLChanneltype(List allCellList1) throws SQLException,
			DataBaseException {
		importChannelTypeBS cc = new importChannelTypeBS();
		cc.execLogo(allCellList1);
	}

	public void dobase(FileInputStream stream) throws SQLException,
			DataBaseException {
		ExcelToMySql cc = new ExcelToMySql();
		InputStream myxls = null;
		HSSFWorkbook wb = null;
		try {
			wb = new HSSFWorkbook(stream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFSheet sheet1 = wb.getSheetAt(1);
		HSSFSheet sheet2 = wb.getSheetAt(2);
		List allCellList = JcxxImpCommBS.transGetExcelData(sheet, "供应商信息表");
		List allCellList1 = JcxxImpCommBS.transGetExcelData(sheet1,
				"采集通道应用类型信息表");
		List allCellList2 = JcxxImpCommBS
				.transGetExcelData(sheet2, "控制设备类型信息表");
		cc.execLSupplier(allCellList);
		if (isbase == false) {
			cc.execLChanneltype(allCellList1);
		}
		if (isbase == false) {
			cc.execLDevCtrlType(allCellList2);
		}
	}

	public void doproject(FileInputStream stream) throws SQLException,
			DataBaseException {
		ExcelToMySql cc = new ExcelToMySql();
		HSSFWorkbook wb = null;
		try {
			wb = new HSSFWorkbook(stream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFSheet sheet1 = wb.getSheetAt(1);
		HSSFSheet sheet2 = wb.getSheetAt(2);
		HSSFSheet sheet3 = wb.getSheetAt(3);
		HSSFSheet sheet4 = wb.getSheetAt(4);
		HSSFSheet sheet5 = wb.getSheetAt(5);
		HSSFSheet sheet6 = wb.getSheetAt(6);
		List allCellList = JcxxImpCommBS.transGetExcelData(sheet, "场景信息表");
		List allCellList1 = JcxxImpCommBS.transGetExcelData(sheet1, "设备信息表");
		List allCellList2 = JcxxImpCommBS.transGetExcelData(sheet2, "网络信息表");
		List allCellList3 = JcxxImpCommBS.transGetExcelData(sheet3, "控制设备信息表");
		List allCellList4 = JcxxImpCommBS.transGetExcelData(sheet4, "采集通道信息表");
		List allCellList5 = JcxxImpCommBS
				.transGetExcelData(sheet5, "控制设备按钮配置表");
		List allCellList6 = JcxxImpCommBS
				.transGetExcelData(sheet6, "控制设备状态配置表");
		cc.execLsence(allCellList);
		if (isproject == false) {
			cc.execLDevice(allCellList1);
		}
		if (isproject == false) {
			cc.execLDevNet(allCellList2);
		}
		if (isproject == false) {
			cc.execLDevCtrl(allCellList3);
		}
		if (isproject == false) {
			cc.execLChannel(allCellList4);
		}
		if (isproject == false) {
			cc.execLDevCtrlBtn(allCellList5);
		}
		if (isproject == false) {
			cc.execLOpDevCtrlSts(allCellList6);
		}
	}

	private void execLOpDevCtrlSts(List allCellList6) throws SQLException,
			DataBaseException {
		// TODO Auto-generated method stub
		importOpDevCtrlStsBS cc = new importOpDevCtrlStsBS();
		cc.execLogo(allCellList6);
	}

	public static void main(String[] args) throws SQLException,
			DataBaseException, FileNotFoundException {
		ExcelToMySql cc = new ExcelToMySql();
		FileInputStream stream = new FileInputStream("F:\\2011\\基本表1.xls");
		cc.dobase(stream);
		FileInputStream myxls = new FileInputStream("F:\\2011\\数据表1.xls");
		cc.doproject(myxls);
		System.out.println(JcxxImpCommBS.impMsg);
	}
}
