package org.unism.web.service;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.unism.op.dao.Op_ChannelTypeDao;
import org.unism.op.dao.Op_DevCtrlTypeDao;
import org.unism.op.dao.Op_SupplierDao;
import org.unism.op.domain.Op_ChannelType;
import org.unism.op.domain.Op_DevCtrlType;
import org.unism.op.domain.Op_Supplier;
import org.wangzz.core.service.ServiceException;

/**
 * 项目导入处理逻辑
 * @author wxhwzz
 */
@Service
@Transactional
public class BasicExcelService {

	@Autowired Op_SupplierDao supplierDao;
	@Autowired Op_ChannelTypeDao channelTypeDao;
	@Autowired Op_DevCtrlTypeDao devCtrlTypeDao;
	
	public String parseExcel(File file, String contentType) throws ServiceException {
		StringBuffer sb = new StringBuffer();
		try {
			Workbook workbook = (Workbook) new HSSFWorkbook(new FileInputStream(file));//改的
			sb.append(generalSupplier(workbook.getSheetAt(7))).append("<br>");
			sb.append(generalChannelType(workbook.getSheetAt(8))).append("<br>");
			sb.append(generalDevCtrlType(workbook.getSheetAt(9))).append("<br>");
		} catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}
		return sb.toString();
	}
	
	/**
	 * 生成供应商信息
	 */
	public String generalSupplier(Sheet sheet) {
		int maxRow = sheet.getLastRowNum();
		for (int i = 1; i <= maxRow; i++) {
			Row row = sheet.getRow(i);
			
			Cell cellCode = row.getCell(0);//供应商编号
			String code = getCellStr(cellCode);
			if("".equals(code)) {
				continue;
			}
			
			Op_Supplier supplier = supplierDao.findUniqueByProperty("sup_no", code);
			if(supplier == null) {
				supplier = new Op_Supplier();
			}
			supplier.setSup_no(code);
			
			Cell cellName = row.getCell(1);//供应商名称
			supplier.setSup_name(getCellStr(cellName));
			
			Cell cellType = row.getCell(2);//供应商类型
			String type = getCellStr(cellType);
			if("销售商".equals(type)) {
				supplier.setSup_type(0);
			}
			if("服务商".equals(type)) {
				supplier.setSup_type(1);
			}
			
			Cell cellCountry = row.getCell(3);//供应商国家
			supplier.setSup_country(getCellStr(cellCountry));
			
			Cell cellAddress = row.getCell(4);//供应商地址
			supplier.setSup_address(getCellStr(cellAddress));
			
			Cell cellZip = row.getCell(5);//供应商邮编
			String zip = getCellStr(cellZip);
			supplier.setSup_zip(zip.equals("") ? null : Integer.parseInt(zip));
			
			Cell cellPhone = row.getCell(6);//供应商固定电话
			supplier.setSup_phone(getCellStr(cellPhone));
			
			Cell cellFax = row.getCell(7);//供应商传真
			supplier.setSup_fax(getCellStr(cellFax));
			
			Cell cellConName = row.getCell(8);//联系人姓名
			supplier.setSup_contactName(getCellStr(cellConName));
			
			Cell cellConPhone = row.getCell(9);//联系人电话
			supplier.setSup_contactPhone(getCellStr(cellConPhone));
			
			Cell cellConMobile = row.getCell(10);//联系人手机
			supplier.setSup_contactMobile(getCellStr(cellConMobile));
			
			Cell cellConEmail = row.getCell(11);//联系人邮箱
			supplier.setSup_contactEmail(getCellStr(cellConEmail));
			
			supplierDao.saveOrUpdate(supplier);
			
		}
		supplierDao.flush();

		return "导入供应商信息成功.";
	}
	
	
	/**
	 * 生成采集通道应用类型
	 */
	public String generalChannelType(Sheet sheet) {
		int maxRow = sheet.getLastRowNum();
		for (int i = 1; i <= maxRow; i++) {
			Row row = sheet.getRow(i);
			
			Cell cellCode = row.getCell(0);//类型编号
			String code = getCellStr(cellCode);
			if("".equals(code)) {
				continue;
			}
		
			Op_ChannelType channelType = channelTypeDao.findUniqueByProperty("chtype_no", code);
			if(channelType == null) {
				channelType = new Op_ChannelType();
			}
			channelType.setChtype_no(code);
			
			Cell cellName = row.getCell(1);//类型名称
			channelType.setChtype_displayName(getCellStr(cellName));
			
			Cell cellModel = row.getCell(2);//设备型号
			channelType.setDev_model(getCellStr(cellModel));
			
			Cell cellDectDig = row.getCell(3);//小数位数
			String dectDigStr = getCellStr(cellDectDig);
			channelType.setCh_dectDig("".equals(dectDigStr) ? null : Integer.parseInt(dectDigStr));
			
			Cell cellUnit = row.getCell(4);//原数据单位
			channelType.setCh_unit(getCellStr(cellUnit));
			
			Cell cellCorrUnit = row.getCell(5);//原数据单位
			channelType.setCh_corrUnit(getCellStr(cellCorrUnit));
			
			Cell cellMax = row.getCell(6);//量程上限
			String chMax = getCellStr(cellMax);
			channelType.setCh_max("".equals(chMax) ? null : Float.parseFloat(chMax));
			
			Cell cellMin = row.getCell(7);//量程下限
			String chMin = getCellStr(cellMin);
			channelType.setCh_min("".equals(chMin) ? null : Float.parseFloat(chMin));
			
			Cell cellCrateMax = row.getCell(8);//变化率上限
			String crateMax = getCellStr(cellCrateMax);
			channelType.setCh_crateMax("".equals(crateMax) ? null : Double.parseDouble(crateMax));
			
			Cell cellCorrCyc = row.getCell(9);//校准周期
			String corrCyc = getCellStr(cellCorrCyc);
			channelType.setCh_corrCyc("".equals(corrCyc) ? null : Integer.parseInt(corrCyc));
			
			Cell cellCorrFormula = row.getCell(10);//校正公式
			channelType.setCh_corrFormula(getCellStr(cellCorrFormula));
			
			channelTypeDao.saveOrUpdate(channelType);
			
		}
		channelTypeDao.flush();
		
		return "导入采集通道应用类型信息成功.";
	}
	
	/**
	 * 生成控制设备类型信息
	 */
	public String generalDevCtrlType(Sheet sheet) {
		int maxRow = sheet.getLastRowNum();
		for (int i = 1; i <= maxRow; i++) {
			Row row = sheet.getRow(i);
			
			Cell cellCode = row.getCell(0);//类型编号
			String code = getCellStr(cellCode);
			if("".equals(code)) {
				continue;
			}
			Op_DevCtrlType devCtrlType = devCtrlTypeDao.findUniqueByProperty("decttype_no", code);
			if(devCtrlType == null) {
				devCtrlType = new Op_DevCtrlType();
			}
			devCtrlType.setDecttype_no(code);
			
			Cell cellName = row.getCell(1);//类型名称
			devCtrlType.setDecttype_displayName(getCellStr(cellName));
			
			Cell cellModel = row.getCell(2);//类型型号
			devCtrlType.setDecttype_typeNo(getCellStr(cellModel));
			
			Cell cellPower = row.getCell(3);//类型功率
			String power = getCellStr(cellPower);
			devCtrlType.setDecttype_power("".equals(power) ? null : Double.parseDouble(power));
			
			Cell cellVoltage = row.getCell(4);//类型供电电压
			String voltage = getCellStr(cellVoltage);
			devCtrlType.setDecttype_voltage("".equals(voltage) ? null : Double.parseDouble(voltage));
			
			Cell cellDesc = row.getCell(5);//类型说明
			devCtrlType.setDecttype_decription(getCellStr(cellDesc));
			
			Cell cellBtnNum = row.getCell(6);//类型按钮数量
			String btnNum = getCellStr(cellBtnNum);
			devCtrlType.setDecttype_btnNum("".equals(btnNum) ? null : Integer.parseInt(btnNum));
			
			Cell cellStsNum = row.getCell(7);//通道状态反馈数量
			String stsNum = getCellStr(cellStsNum);
			devCtrlType.setDecttype_chlStsNum("".equals(stsNum) ? null : Integer.parseInt(stsNum));
			
			
			devCtrlTypeDao.saveOrUpdate(devCtrlType);
		}
		devCtrlTypeDao.flush();
		
		return "导入控制设备类型信息成功.";
	}
	
	
	public String getCellStr(Cell cell) {
		String str = "";
		if(cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				double strNumer = cell.getNumericCellValue();
				DecimalFormat df = new DecimalFormat("0");
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
}
