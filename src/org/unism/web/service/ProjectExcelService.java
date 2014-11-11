package org.unism.web.service;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.unism.gm.dao.Gm_ChannelDao;
import org.unism.gm.dao.Gm_DevChannelDao;
import org.unism.gm.dao.Gm_DevCtrlDao;
import org.unism.gm.dao.Gm_DevCtrlOperateDao;
import org.unism.gm.dao.Gm_DevCtrlStsDao;
import org.unism.gm.dao.Gm_DevNetDao;
import org.unism.gm.dao.Gm_DeviceDao;
import org.unism.gm.dao.Gm_DevstsDao;
import org.unism.gm.dao.Gm_LatestdataDao;
import org.unism.gm.domain.Gm_Channel;
import org.unism.gm.domain.Gm_DevChannel;
import org.unism.gm.domain.Gm_DevCtrl;
import org.unism.gm.domain.Gm_DevCtrlOperate;
import org.unism.gm.domain.Gm_DevCtrlSts;
import org.unism.gm.domain.Gm_DevNet;
import org.unism.gm.domain.Gm_Device;
import org.unism.gm.domain.Gm_Devsts;
import org.unism.gm.domain.Gm_Latestdata;
import org.unism.op.dao.Op_AreasDao;
import org.unism.op.dao.Op_ChannelTypeDao;
import org.unism.op.dao.Op_DevCtrlBtnDao;
import org.unism.op.dao.Op_DevCtrlStsDao;
import org.unism.op.dao.Op_DevCtrlTypeDao;
import org.unism.op.dao.Op_SceneDao;
import org.unism.op.dao.Op_SupplierDao;
import org.unism.op.dao.Op_UserInfo_SceneDao;
import org.unism.op.dao.Op_scene_gtypeDao;
import org.unism.op.domain.Op_Areas;
import org.unism.op.domain.Op_ChannelType;
import org.unism.op.domain.Op_DevCtrlBtn;
import org.unism.op.domain.Op_DevCtrlSts;
import org.unism.op.domain.Op_DevCtrlType;
import org.unism.op.domain.Op_Scene;
import org.unism.op.domain.Op_Supplier;
import org.unism.op.domain.Op_UserInfo;
import org.unism.op.domain.Op_UserInfo_Scene;
import org.unism.pro.dao.Pro_FisheriesDao;
import org.unism.util.Ch_offerSer;
import org.unism.util.Ch_procesStyle;
import org.unism.util.Ch_state;
import org.unism.util.DateUtil;
import org.unism.util.Dect_ctlType;
import org.unism.util.Dev_state;
import org.unism.util.Gm_DevCtrl_Ch_offerSer;
import org.unism.util.Gm_DevCtrl_Dect_state;
import org.unism.util.Net_linkSts;
import org.unism.util.Net_role;
import org.unism.util.Net_state;
import org.unism.util.Op_DevCtrlBtn_Deco_type;
import org.unism.util.Op_DevCtrlSts_Dect_state;
import org.unism.util.Scene_ctype;
import org.unism.util.Scene_gtype;
import org.unism.util.Scene_type;
import org.unism.util.Sup_type;
import org.wangzz.core.search.Search;
import org.wangzz.core.service.ServiceException;
import org.wangzz.core.web.struts2.Struts2Utils;

/**
 * 项目导入处理逻辑
 * @author wxhwzz
 */
@Service
public class ProjectExcelService {

	@Autowired Op_AreasDao areasDao;
	@Autowired Op_SupplierDao supplierDao;
	@Autowired Op_SceneDao sceneDao;
	@Autowired Gm_DeviceDao deviceDao;
	@Autowired Gm_DevNetDao devNetDao;
	@Autowired Gm_DevstsDao devstsDao;
	@Autowired Op_ChannelTypeDao channelTypeDao;
	@Autowired Gm_ChannelDao channelDao;
	@Autowired Gm_DevChannelDao devChannelDao;
	@Autowired Gm_LatestdataDao latestdataDao;
	@Autowired Op_DevCtrlTypeDao devCtrlTypeDao;
	@Autowired Gm_DevCtrlDao devCtrlDao;
	@Autowired Gm_DevCtrlStsDao devCtrlStsDao;
	@Autowired Gm_DevCtrlOperateDao devCtrlOperateDao;
	@Autowired Op_DevCtrlBtnDao devCtrlBtnDao;
	@Autowired Op_DevCtrlStsDao op_DevCtrlStsDao;
	@Autowired Op_UserInfo_SceneDao op_UserInfo_SceneDao;
	@Autowired Pro_FisheriesDao pro_FisheriesDao;	
	@Autowired Op_scene_gtypeDao scene_gtypeDao;
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,isolation = Isolation.READ_UNCOMMITTED,rollbackFor = Exception.class)
	public String parseExcel(File file, String contentType) throws Exception {
		StringBuffer sb = new StringBuffer();
		Workbook workbook = new HSSFWorkbook(new FileInputStream(file));//改的
		sb.append(generalScene(workbook.getSheetAt(0))).append("<br>");
		sb.append(generalDevice(workbook.getSheetAt(1))).append("<br>");
		sb.append(generalDevNet(workbook.getSheetAt(2))).append("<br>");
		sb.append(generalChannel(workbook.getSheetAt(3))).append("<br>");
		sb.append(generalDevCtrl(workbook.getSheetAt(4))).append("<br>");
		sb.append(generalDevCtrlBtn(workbook.getSheetAt(5))).append("<br>");
		sb.append(generalDevCtrlSts(workbook.getSheetAt(6))).append("<br>");
		return sb.toString();
	}
	
	
	/**
	 * 生成场景信息
	 */
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,isolation = Isolation.READ_UNCOMMITTED,rollbackFor = Exception.class)
	public String generalScene(Sheet sheet) {
		int maxRow = sheet.getLastRowNum();
		for (int i = 1; i <= maxRow; i++) {
			Row row = sheet.getRow(i);
			
			Cell cellCode = null;
			try {
				cellCode = row.getCell(0);//场景编号
			} catch (java.lang.NullPointerException e) {
				continue;
			}
			
			String code = getCellStr(cellCode);
			code=code.trim().replaceAll("—", "-");
			String[] str=code.split("-");
			int count=str.length-1;//获得横杠的个数
			if("".equals(code)) {
				continue;
			}
			
			Op_Scene scene = sceneDao.findUniqueByProperty("scene_no", code);
			if(scene == null) {
				scene = new Op_Scene();
			}
			scene.setScene_no(code);//设置场景编号
			scene.setScene_rank(count);//设置所属等级
			
			String pSceneCode = null;
			if(code.contains("-")){
				int pos=code.lastIndexOf("-");
				pSceneCode=code.substring(0,pos);
			}
			if(!"".equals(pSceneCode)&& pSceneCode!=null) {
				Op_Scene pScene = sceneDao.findUniqueByProperty("scene_no", pSceneCode);
				if(pScene == null) {
					throw new ServiceException("场景信息表里第" + (i+1) + "行中的父节点编号:" + pSceneCode + "不存在.");
				}
				scene.setScene_pid(pScene.getScene_id());//设置父场景Id
			}
			
			Cell cellName = row.getCell(1);//场景名称
			scene.setScene_name(getCellStr(cellName));
			//蟹池塘,场景组,基地,企业,项目,日光温室,连栋温室,养殖虾池塘,鱼池塘,育苗虾池塘,水分监测站,水文监测站,水质监测站,猪舍,视频点,气象站,移动场景
			Cell cellGtype = row.getCell(2);//场景类型细类
			String gtype = getCellStr(cellGtype);
			Map<String, Integer> map = scene_gtypeDao.getAll();
			Integer _gtype = map.get(gtype);
			if(_gtype == null){
				throw new ServiceException("场景类型细类  (" + gtype + ") 不存在，请联系管理员！");
			}
			scene.setScene_gtype(_gtype);
			/*
			if("蟹池塘".equals(gtype)||"养殖池塘".equals(gtype)) {
				scene.setScene_gtype(1);
			}
			if("场景组".equals(gtype)) {
				scene.setScene_gtype(2);
			}
			if("基地".equals(gtype)) {
				scene.setScene_gtype(3);
			}
			if("企业".equals(gtype)) {
				scene.setScene_gtype(4);
			}
			if("项目".equals(gtype)) {
				scene.setScene_gtype(5);
			}
			if("日光温室".equals(gtype)) {
				scene.setScene_gtype(101);
			}
			if("连栋温室".equals(gtype)) {
				scene.setScene_gtype(102);
			}
			if("大田茶叶".equals(gtype)) {
				scene.setScene_gtype(111);
			}
			if("养殖虾池塘".equals(gtype)) {
				scene.setScene_gtype(201);
			}
			if("鱼池塘".equals(gtype)) {
				scene.setScene_gtype(202);
			}
			if("育苗虾池塘".equals(gtype)) {
				scene.setScene_gtype(203);
			}
			if("循环水".equals(gtype)) {
				scene.setScene_gtype(204);
			}
			if("混水池".equals(gtype)) {
				scene.setScene_gtype(205);
			}
			if("出水口".equals(gtype)) {
				scene.setScene_gtype(206);
			}
			if("海水平台".equals(gtype)) {
				scene.setScene_gtype(251);
			}
			if("鲍鱼预热池".equals(gtype)) {
				scene.setScene_gtype(252);
			}
			if("鲍鱼车间".equals(gtype)) {
				scene.setScene_gtype(253);
			}
			if("海参预热池".equals(gtype)) {
				scene.setScene_gtype(254);
			}
			if("海参车间".equals(gtype)) {
				scene.setScene_gtype(255);
			}
			if("养参池".equals(gtype)) {
				scene.setScene_gtype(256);
			}
			if("贝类预热池".equals(gtype)) {
				scene.setScene_gtype(257);
			}
			if("贝类育苗".equals(gtype)) {
				scene.setScene_gtype(258);
			}
			if("贝类饵料育苗".equals(gtype)) {
				scene.setScene_gtype(259);
			}
			if("水分监测站".equals(gtype)) {
				scene.setScene_gtype(301);
			}
			if("水文监测站".equals(gtype)) {
				scene.setScene_gtype(302);
			}
			if("水质监测站".equals(gtype)) {
				scene.setScene_gtype(303);
			}
			if("猪舍".equals(gtype)) {
				scene.setScene_gtype(401);
			}
			if("鸡舍".equals(gtype)) {
				scene.setScene_gtype(411);
			}
			if("视频点".equals(gtype)) {
				scene.setScene_gtype(97);
			}
			if("气象站".equals(gtype)) {
				scene.setScene_gtype(98);
			}
			if("移动场景".equals(gtype)) {
				scene.setScene_gtype(99);
			}
			*/
			
			Cell cellAddr = row.getCell(3);//场景所在地
			scene.setScene_addr(getCellStr(cellAddr));
			
			Cell cellDesc = row.getCell(4);//场景说明
			scene.setScene_desc(getCellStr(cellDesc));
			
			Cell cellCreater = row.getCell(5);//创建者
			scene.setScene_creater(getCellStr(cellCreater));			

			Cell cellType = row.getCell(6);//场景类型
			String type = getCellStr(cellType);
			if("设施园艺".equals(type)) {
				scene.setScene_type(1);
			}
			if("水产养殖".equals(type)) {
				scene.setScene_type(2);
			}
			if("大田种植".equals(type)) {
				scene.setScene_type(3);
			}
			if("畜禽养殖".equals(type)) {
				scene.setScene_type(4);
			}		
						
			Cell cellCtype = row.getCell(7);//场景类型子类
			String ctype = getCellStr(cellCtype);
			if("设施蔬菜".equals(ctype)) {
				scene.setScene_ctype(11);
			}
			if("设施花卉".equals(ctype)) {
				scene.setScene_ctype(12);
			}
			if("池塘水产养殖".equals(ctype)) {
				scene.setScene_ctype(21);
			}
			if("设施水产养殖".equals(ctype)) {
				scene.setScene_ctype(22);
			}
			if("井灌".equals(ctype)) {
				scene.setScene_ctype(31);
			}
			if("低压管灌".equals(ctype)) {
				scene.setScene_ctype(32);
			}
			if("明渠".equals(ctype)) {
				scene.setScene_ctype(33);
			}
			if("田间滴灌".equals(ctype)) {
				scene.setScene_ctype(34);
			}
			
			Cell cellArea = row.getCell(8);//区划编码
			String areaCode = getCellStr(cellArea);
			if("".equals(areaCode)) {
				scene.setArea_id(null);
			} else {
				Op_Areas areas = areasDao.findUniqueByProperty("area_id", areaCode);
				if(areas == null) {
					throw new ServiceException("场景信息表里第" + (i+1) + "行中的区划编码:" + areaCode + "不存在.");
				}
				scene.setArea_id(areas);
			}
			
			Cell cellKeyWord = row.getCell(9);//关键字
			scene.setScene_keyWord(getCellStr(cellKeyWord));
			
			Cell cellscene_order = row.getCell(10);//排序号
			if(cellscene_order != null && !"".equals(cellscene_order.toString())){
			scene.setScene_order(Integer.parseInt(getCellStr(cellscene_order)));
			}
			
			Cell cellscene_longitude = row.getCell(11);//经度
			String lng = getCellLngLatStr(cellscene_longitude);
			try {
				double d = Double.parseDouble(lng);
				lng = Double.toString(d);
			} catch (Exception e) {
				lng = "";
			}
			scene.setScene_longitude(lng);
			
			Cell cellscene_latitude = row.getCell(12);//纬度
			String lat = getCellLngLatStr(cellscene_latitude);
			try {
				double d = Double.parseDouble(lat);
				lat = Double.toString(d);
			} catch (Exception e) {
				lat = "";
			}
			scene.setScene_latitude(lat);
			
			scene.setScene_createDate(new Date());
			scene.setScene_state(1);
			
			sceneDao.saveOrUpdate(scene);
			
			Op_UserInfo op_UserInfo=(Op_UserInfo)Struts2Utils.getRequest().getSession().getAttribute("user");
			List<Op_UserInfo_Scene> list = op_UserInfo_SceneDao.findAllByUserAndScene(op_UserInfo,scene);
			for (Op_UserInfo_Scene op_UserInfo_Scene : list) {
				op_UserInfo_SceneDao.delete(op_UserInfo_Scene);
			}
			Op_UserInfo_Scene op_UserInfo_Scene = new Op_UserInfo_Scene();
			op_UserInfo_Scene.setScene_id(scene);
			op_UserInfo_Scene.setUser_id(op_UserInfo);
			op_UserInfo_SceneDao.save(op_UserInfo_Scene);
			//添加一条数据到用户场景关联表
			
//			Op_UserInfo_Scene op_UserInfo_Scene=new Op_UserInfo_Scene();
//			Op_UserInfo op_UserInfo=(Op_UserInfo)Struts2Utils.getRequest().getSession().getAttribute("user");
//			op_UserInfo_Scene.setScene_id(scene);
//			op_UserInfo_Scene.setUser_id(op_UserInfo);
//			op_UserInfo_SceneDao.saveOrUpdate(op_UserInfo_Scene);
			
			
			
//			//wxh新加，添加一条数据到水产应用配置表
//			if("养殖池塘".equals(gtype)) {
//				Pro_Fisheries pro_Fisheries=pro_FisheriesDao.findUniqueByProperty("scene_id", scene.getScene_id());
//				if(pro_Fisheries==null){
//					pro_Fisheries=new Pro_Fisheries();
//					pro_Fisheries.setScene(scene);
//					pro_FisheriesDao.saveOrUpdate(pro_Fisheries);
//				}
//			}
			
		}
//		sceneDao.flush();
		return "导入场景信息成功.";
	}
	
	/**
	 * 生成设备信息
	 */
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,isolation = Isolation.READ_UNCOMMITTED,rollbackFor = Exception.class)
	public String generalDevice(Sheet sheet) {
		int maxRow = sheet.getLastRowNum();
		for (int i = 1; i <= maxRow; i++) {
			Row row = sheet.getRow(i);
			Cell cellCode = null;
			try {
				cellCode = row.getCell(0);//设备编号
			} catch (java.lang.NullPointerException e) {
				continue;
			}
			String code = getCellStr(cellCode);
			if("".equals(code)) {
				continue;
			}
			Gm_Device device = deviceDao.findUniqueByProperty("dev_no", code);
			if(device != null) {
				int dev_btype=device.getDev_btype();
				if(dev_btype==0){
					throw new ServiceException("设备信息表里第" + (i+1) + "行中的设备编号已存在,不允许导入重复设备,请更换设备地址重新导入.");
				}				
			} else {
				device = new Gm_Device();
			}
			device.setDev_no(code);
			
			Cell cellName = row.getCell(1);//设备名称
			device.setDev_name(getCellStr(cellName));
			
			Cell cellSerial = row.getCell(2);//设备序列号
			String serial = getCellStr(cellSerial);
			device.setDev_serial(serial);			
			String[] strs = serial.split("-");
			if(strs.length != 4)
				throw new ServiceException("序列号不合法");
			String btypeStr = strs[0];
			btypeStr = btypeStr.substring(0,1);//序列号-设备类型定义
			device.setDev_btype(Integer.parseInt(btypeStr));
			
			String modelStr = strs[1];//序列号-设备型号定义
			device.setDev_model(modelStr);
			
			Cell cellScene = row.getCell(3);//所属场景
			String sceneCode = getCellStr(cellScene);
			if("".equals(sceneCode)) {
				throw new ServiceException("设备信息表里第" + (i+1) + "行中的场景编码为空.");
			}
			Op_Scene scene = sceneDao.findUniqueByProperty("scene_no", sceneCode);
			if(scene == null) {
				throw new ServiceException("设备信息表里第" + (i+1) + "行中的场景编码:" + sceneCode + "在数据库的场景信息表中不存在.");
			}
			device.setScene_id(scene);
			
			Cell cellPowerType = row.getCell(4);//供电方式
			device.setDev_powerType(getCellStr(cellPowerType));
			
			Cell cellImsi = row.getCell(5);//设备IMSI/说明
			device.setDev_imsi(getCellStr(cellImsi));
			
			Cell cellBuyDate = row.getCell(6);//购买时间
			Date buyDate = null;
			if(cellBuyDate != null){
				switch (cellBuyDate.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					String str = cellBuyDate.getStringCellValue();
					if(!"".equals(str)){
						buyDate = DateUtil.parseDate(str, "yyyy-mm-dd");
					}
					break;
				default:
					buyDate = cellBuyDate.getDateCellValue();
					break;
				}
			}
			device.setDev_buyDate(buyDate);
			
			Cell cellCustSal = row.getCell(7);//设备厂家编号
			String custSal = getCellStr(cellCustSal);
			if(!custSal.equals("")) {
				Op_Supplier supplierSal = supplierDao.findUniqueByProperty("sup_no", custSal);
				if(supplierSal == null) {
					throw new ServiceException("设备信息表里第" + (i+1) + "行中的设备厂家编号:" + custSal + "不存在.");
				}
				device.setCust_saleId(supplierSal);
			} else {
				device.setCust_saleId(null);
			}
			
			Cell cellCustServ = row.getCell(8);//设备服务商编号
			String custServ = getCellStr(cellCustServ);
			if("".equals(custServ)) {
				device.setCust_serviceId(null);
			} else {
				if(custServ.equals(custSal)) {
					device.setCust_serviceId(device.getCust_saleId());
				} else {
					Op_Supplier supplierServ = supplierDao.findUniqueByProperty("sup_no", custServ);
					if(supplierServ == null) {
						throw new ServiceException("设备信息表里第" + (i+1) + "行中的设备服务商编号:" + custServ + "不存在.");
					}
					device.setCust_serviceId(supplierServ);
				}
				
			}			
			device.setDev_state(1);
			
//			Cell cellState = row.getCell(9);//设备使用状态
//			String state = getCellStr(cellState);
//			if("在用".equals(state)) {
//				device.setDev_state(1);
//			}
//			if("未用".equals(state)) {
//				device.setDev_state(0);
//			}
			
//			Cell cellBtype = row.getCell(4);//设备大类
//			String btype = getCellStr(cellBtype);
//			if("M2M".equals(btype)) {
//				device.setDev_btype(0);
//			}
//			if("WSN".equals(btype)) {
//				device.setDev_btype(1);
//			}
//			if("智能单元".equals(btype)) {
//				device.setDev_btype(2);
//			}
//			if("执行器".equals(btype)) {
//				device.setDev_btype(3);
//			}
//			if("传感器".equals(btype)) {
//				device.setDev_btype(4);
//			}
//			
//			Cell cellModel = row.getCell(6);//设备型号
//			device.setDev_model(getCellStr(cellModel));
			
			device.setDev_effectTime(new Date());//生效时间,默认当天
			
			deviceDao.saveOrUpdate(device);
		}
//		deviceDao.flush();
		return "导入设备信息成功.";
	}
	
	/**
	 * 生成网络信息
	 */
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,isolation = Isolation.READ_UNCOMMITTED,rollbackFor = Exception.class)
	public String generalDevNet(Sheet sheet) {
		int maxRow = sheet.getLastRowNum();
		for (int i = 1; i <= maxRow; i++) {
			Row row = sheet.getRow(i);
			
			Cell cellCode = null;
			try {
				cellCode = row.getCell(0);//网络编号
			} catch (java.lang.NullPointerException e) {
				continue;
			}
			
			String code = getCellStr(cellCode);
			code = code.trim().replaceAll("—", "-");
			if("".equals(code)) {
				continue;
			}
			
			Gm_DevNet devnet = devNetDao.findUniqueByProperty("net_no", code);
			if(devnet == null) {
				devnet = new Gm_DevNet();
			}
			devnet.setNet_no(code);
			
			Cell cellDevCode = row.getCell(1);//设备编号
			String devCode = getCellStr(cellDevCode);
			if("".equals(devCode)) {
				throw new ServiceException("网络信息表里第" + (i+1) + "行中的设备编号为空.");
			} else {
				Gm_Device device = deviceDao.findUniqueByProperty("dev_no", devCode);
				if(device == null) {
					throw new ServiceException("网络信息表里第" + (i+1) + "行中的设备编号:" + devCode + "在数据库的设备信息表中不存在.");
				}
				devnet.setDev_id(device);
				devnet.setNet_type(device.getDev_btype());
			}
			
			String[] addrStrs = code.split("-");
			int addrLength = addrStrs.length;
			String address = addrStrs[addrLength - 1].toLowerCase().replaceAll("s", "").replaceAll("t", "").replaceAll("e", "").replaceAll("st", "");
			if("".equals(address)) {
				throw new ServiceException("网络信息表里第" + (i+1) + "行中的网络地址为空.");
			}
			devnet.setNet_addr(address);
			
			if(!code.contains("-")) {//网内深度   网络类型
				devnet.setNet_depth(0);
				
				devnet.setNet_type(0);
			} else {//有几个横杠,深度就是几
				devnet.setNet_depth(addrLength - 1);
//				
//				if(code.toLowerCase().contains("st"))
//					devnet.setNet_depth(2);
//				else 
//					devnet.setNet_depth(1);
			}
			//网关,独立设备,移动设备,中继设备RD,叶设备ED,协调器CD,接入设备LD,智能传感器,智能变送器
			Cell cellRole = row.getCell(2);//网内角色
			String role = getCellStr(cellRole);
			if("网关".equals(role)) {
				devnet.setNet_role("01");
			}
			if("独立设备".equals(role)) {
				devnet.setNet_role("02");
			}
			if("移动设备".equals(role)) {
				devnet.setNet_role("03");
			}
			if("中继设备RD".equals(role)) {
				devnet.setNet_role("11");
			}
			if("叶设备ED".equals(role)) {
				devnet.setNet_role("12");
			}
			if("协调器CD".equals(role)) {
				devnet.setNet_role("13");
			}
			if("接入设备LD".equals(role)) {
				devnet.setNet_role("14");
			}
			if("智能传感器".equals(role)) {
				devnet.setNet_role("21");
			}
			if("智能变送器".equals(role)) {
				devnet.setNet_role("22");
			}
			
			Cell cellLinkSts = row.getCell(3);//连接方式
			String linkSts = getCellStr(cellLinkSts);
			if("长连接".equals(linkSts)) {
				devnet.setNet_linkSts(1);
			}
			if("短连接".equals(linkSts)) {
				devnet.setNet_linkSts(2);
			}
			if("无连接".equals(linkSts)) {
				devnet.setNet_linkSts(3);
			}
			if("无效".equals(linkSts)) {
				devnet.setNet_linkSts(0);
			}
			
			String pNetCode=null;//父节点编号
			if(code.contains("-")){
				int pos=code.lastIndexOf("-");
				pNetCode=code.substring(0,pos);
			}
			if(!"".equals(pNetCode)&& pNetCode!=null){
				Gm_DevNet pdevnet = devNetDao.findUniqueByProperty("net_no", pNetCode);
				if(pdevnet == null) {
					throw new ServiceException("网络信息表里第" + (i+1) + "行中的父节点编号:" + pNetCode + "不存在.");
				}
				devnet.setNet_pid(pdevnet.getNet_id());
			}
			
			Cell cellSim = row.getCell(4);//SIM卡号
			devnet.setNet_sim(getCellStr(cellSim));
			
			devnet.setNet_state(1);//网络使用状态
//			Cell cellState = row.getCell(5);//网络使用状态
//			String state = getCellStr(cellState);
//			if("在用".equals(state)) {
//				devnet.setNet_state(1);
//			}
//			if("未用".equals(state)) {
//				devnet.setNet_state(0);
//			}
			
			devnet.setNet_pltType(5);//协议类型
			
			Cell cellSmsc = row.getCell(6);//短信中心号
			devnet.setNet_smsc(getCellStr(cellSmsc));
			
			devNetDao.saveOrUpdate(devnet);
			
			if(devnet.getNet_type() == 0) {
				List<Gm_Devsts> list = devstsDao.findAllEq("dev_addr", address);
				if(list != null && list.size() > 1){
					for (Gm_Devsts gm_Devsts : list) {
						devstsDao.delete(gm_Devsts);
					}
				}
				Gm_Devsts devsts = devstsDao.findUniqueByProperty("dev_addr", address);
				if(devsts == null) {
					devsts = new Gm_Devsts();
					devsts.setDev_addr(address);
					devsts.setDest_regSts(0);
					devsts.setDest_linkSts(devnet.getNet_linkSts());
					devsts.setDest_workSts(0);
				}
				devsts.setDest_linkSts(devnet.getNet_linkSts());
				devsts.setDest_regSts(1);
				devsts.setDev_id(devnet.getDev_id());
				devstsDao.saveOrUpdate(devsts);
			}
			
		}
//		devNetDao.flush();
		return "导入网络信息成功.";
	}
	
	
	/**
	 * 生成采集通道信息
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,isolation = Isolation.READ_UNCOMMITTED,rollbackFor = Exception.class)
	public String generalChannel(Sheet sheet) {
		int maxRow = sheet.getLastRowNum();
		for (int i = 1; i <= maxRow; i++) {
			Row row = sheet.getRow(i);
			
			Cell cellCode = null;
			try {
				cellCode = row.getCell(0);//通道编号
			} catch (java.lang.NullPointerException e) {
				continue;
			}
			
			String code = getCellStr(cellCode);
			if("".equals(code)) {
				continue;
			}
			
			if(code.indexOf("-") < 0){
				throw new ServiceException("通道信息表里第" + (i+1) + "行中的通道编号格式不正确！");
			}
			
			Gm_Channel channel = channelDao.findUniqueByProperty("ch_no", code);
			if(channel == null) {
				channel = new Gm_Channel();
			}
			channel.setCh_no(code);
			
			Cell cellName = row.getCell(1);//通道名称
			channel.setCh_name(getCellStr(cellName));
			
			
			Cell cellSceneCode = row.getCell(2);//所属场景
			String sceneCode = getCellStr(cellSceneCode);
			if("".equals(sceneCode)) {
				throw new ServiceException("通道信息表里第" + (i+1) + "行中的所属场景编号为空.");
			} else {
				Op_Scene scene = sceneDao.findUniqueByProperty("scene_no", sceneCode);
				if(scene == null) {
					throw new ServiceException("通道信息表里第" + (i+1) + "行中的所属场景编号:" + sceneCode + "不存在.");
				}
				channel.setScene_id(scene);
			}
			
			Cell cellCollectCode = row.getCell(3);//采集设备编号
			String collectCode = getCellStr(cellCollectCode);
			if("".equals(collectCode)) {
				throw new ServiceException("通道信息表里第" + (i+1) + "行中的采集设备编号为空.");
			} else {
				Gm_Device device = deviceDao.findUniqueByProperty("dev_no", collectCode);
				if(device == null) {
					throw new ServiceException("通道信息表里第" + (i+1) + "行中的采集设备编号:" + collectCode + "不存在.");
				}
				channel.setDev_collectId(device);
			}
			
			Cell cellChlNo = row.getCell(4);//设备通道号
			String chlNo = getCellStr(cellChlNo);
			channel.setCh_chlNo("".equals(chlNo) ? null : Integer.parseInt(chlNo));
			
			Cell cellSensorCode = row.getCell(5);//传感设备编号
			String sensorCode = getCellStr(cellSensorCode);
			if("".equals(sensorCode)) {
				throw new ServiceException("通道信息表里第" + (i+1) + "行中的传感设备编号为空.");
			} else {
				Gm_Device device = deviceDao.findUniqueByProperty("dev_no", sensorCode);//通过序列号来查询？？？需要问马老师
				if(device == null) {
					throw new ServiceException("通道信息表里第" + (i+1) + "行中的传感设备编号:" + sensorCode + "不存在.");
				}
				channel.setDev_sensorId(device);
			}
			
			Cell cellChlType = row.getCell(6);//应用类型编号
			String chlTypeCode = getCellStr(cellChlType);
			if("".equals(chlTypeCode)) {
				throw new ServiceException("通道信息表里第" + (i+1) + "行中的应用类型编号为空.");
			}
			Op_ChannelType channelType = channelTypeDao.findUniqueByProperty("chtype_no", chlTypeCode);
			if(channelType == null) {
				throw new ServiceException("通道信息表里第" + (i+1) + "行中的应用类型编号:" + chlTypeCode + "不存在.");
			}
			channel.setChtype_id(channelType);
			
			Cell cellProcesStyle = row.getCell(7);//通道数据处理方式
			String procesStyle = getCellStr(cellProcesStyle);
			if("不处理".equals(procesStyle)) {
				channel.setCh_procesStyle(0);
			}
			if("校正后存储".equals(procesStyle)) {
				channel.setCh_procesStyle(1);
			}
			if("校正后定时存储".equals(procesStyle)) {
				channel.setCh_procesStyle(2);
			}
			if("设备能量状态显示".equals(procesStyle)) {
				channel.setCh_procesStyle(3);
			}
			if("控制设备状态返回显示".equals(procesStyle)) {
				channel.setCh_procesStyle(4);
			}
			
			Cell cellMemoryPeriod = row.getCell(8);//存储周期
			String memoryPeriod = getCellStr(cellMemoryPeriod);
			channel.setCh_memoryPeriod("".equals(memoryPeriod) ? null : Integer.parseInt(memoryPeriod));
			
			Cell cellOfferSer = row.getCell(9);//是否对外提供服务
			String offerSer = getCellStr(cellOfferSer);
			if("是".equals(offerSer)) {
				channel.setCh_offerSer(1);
			}
			if("否".equals(offerSer)) {
				channel.setCh_offerSer(0);
			}
			
			channel.setCh_state(1);//通道状态
//			Cell cellState = row.getCell(10);//通道状态
//			String state = getCellStr(cellState);
//			if("在用".equals(state)) {
//				channel.setCh_state(1);
//			}
//			if("未用".equals(state)) {
//				channel.setCh_state(0);
//			}
			
			Cell cellSeatNo = row.getCell(10);//位置编号
			channel.setCh_seat_no(getCellStr(cellSeatNo));
			
			Cell cellDepth = row.getCell(11);//通道深度
			channel.setCh_depth(getCellStr(cellDepth));
			
			channel.setCh_corrCyc(channelType.getCh_corrCyc());//校正周期
			channel.setCh_corrFormula(channelType.getCh_corrFormula());//校正公式
			//小数位数
			Integer dectDig=channelType.getCh_dectDig();
			if(dectDig==null){
				dectDig=2;
			}
			channel.setCh_dectDig(dectDig);//channel.setCh_dectDig(channelType.getCh_dectDig());//小数位数
			channel.setCh_unit(channelType.getCh_unit());//原数据单位
			channel.setCh_corrUnit(channelType.getCh_corrUnit());//校正后单位
			channel.setCh_max(channelType.getCh_max());//量程上限
			channel.setCh_min(channelType.getCh_min());//量程下限
			channel.setCh_crateMax(channelType.getCh_crateMax());//变化率上限
			
			channelDao.saveOrUpdate(channel);
			
			//生成最新数据信息
			Gm_Latestdata latestdata = latestdataDao.findUniqueByProperty("ch_id", channel);
			if(latestdata == null) {
				latestdata = new Gm_Latestdata();
				latestdata.setCh_id(channel);
				latestdataDao.save(latestdata);
			}
			
			//生成设备通道配置信息
			String[] devChlStrs = code.split("-");
			String devAddr = devChlStrs[0];
			int orderStr = Integer.parseInt(devChlStrs[1]);
			Search search = new Search();
			search.addFilterEqual("dev_addr", devAddr);
			search.addFilterEqual("dch_order", orderStr);
			List<Gm_DevChannel> devChannelList = devChannelDao.search(search);
			if(devChannelList.isEmpty()) {
				List<Gm_DevNet> devNetList = devNetDao.search(new Search().addFilterEqual("net_addr", devAddr).addFilterEqual("net_depth", 0));
				if(devNetList.isEmpty())
					throw new ServiceException("通道信息表里第" + (i+1) + "行中的通道编号不正确,不存在地址:" + devAddr);
				else if(devNetList.size() > 1)
					throw new ServiceException("通道信息表里第" + (i+1) + "行中的通道编号不正确,地址:" + devAddr + "有多个");
				Gm_DevNet devNet = devNetList.get(0);
				Gm_DevChannel devChannel = new Gm_DevChannel();
				devChannel.setCh_id(channel);
				devChannel.setDev_id(devNet.getDev_id());
				devChannel.setCh_memoryPeriod(channel.getCh_memoryPeriod());
				devChannel.setCh_procesStyle(channel.getCh_procesStyle());
				devChannel.setDch_order(orderStr);
				devChannel.setDev_addr(devAddr);
				devChannelDao.save(devChannel);
			}
			
		}
//		channelDao.flush();
		
		return "导入采集通道信息成功.";
	}
	
	/**
	 * 生成控制设备信息
	 */
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,isolation = Isolation.READ_UNCOMMITTED,rollbackFor = Exception.class)
	public String generalDevCtrl(Sheet sheet) {
		int maxRow = sheet.getLastRowNum();
		for (int i = 1; i <= maxRow; i++) {
			Row row = sheet.getRow(i);
			
			Cell cellCode = null;
			try {
				cellCode = row.getCell(0);//控制设备编号
			} catch (java.lang.NullPointerException e) {
				continue;
			}
			
			String code = getCellStr(cellCode);
			if("".equals(code)) {
				continue;
			}
		
			Gm_DevCtrl devCtrl = devCtrlDao.findUniqueByProperty("dect_no", code);
			if(devCtrl == null) {
				devCtrl = new Gm_DevCtrl();
				devCtrl.setDect_no(code);
			}
			
			Cell cellName = row.getCell(1);//设备名称
			devCtrl.setDect_name(getCellStr(cellName));
			
//			Cell cellSerial = row.getCell(2);//设备序列号
//			devCtrl.setDect_serial(getCellStr(cellSerial));
			
			Cell cellSceneCode = row.getCell(2);//所属场景
			String sceneCode = getCellStr(cellSceneCode);
			if("".equals(sceneCode)) {
				throw new ServiceException("控制设备信息表里第" + (i+1) + "行中的所属场景编号为空.");
			} else {
				Op_Scene scene = sceneDao.findUniqueByProperty("scene_no", sceneCode);
				if(scene == null) {
					throw new ServiceException("控制设备信息表里第" + (i+1) + "行中的所属场景编号:" + sceneCode + "不存在.");
				}
				devCtrl.setScene_id(scene);
			}
			
			Cell cellDevCode = row.getCell(3);//所属设备编号
			String devCode = getCellStr(cellDevCode);
			if("".equals(devCode)) {
				throw new ServiceException("控制设备信息表里第" + (i+1) + "行中的所属设备编号为空.");
			} else {
				Gm_Device device = deviceDao.findUniqueByProperty("dev_no", devCode);
				if(device == null) {
					throw new ServiceException("控制设备信息表里第" + (i+1) + "行中的所属设备编号:" + devCode + "不存在.");
				}
				devCtrl.setDev_id(device);
			}
			
			Cell cellDevCtrlType = row.getCell(4);//类型编号
			String devCtrlTypeCode = getCellStr(cellDevCtrlType);
			if("".equals(devCtrlTypeCode)) {
				throw new ServiceException("控制设备信息表里第" + (i+1) + "行中的类型编号为空.");
			} else {
				Op_DevCtrlType devCtrlType = devCtrlTypeDao.findUniqueByProperty("decttype_no", devCtrlTypeCode);
				if(devCtrlType == null) {
					throw new ServiceException("控制设备信息表里第" + (i+1) + "行中的类型编号:" + devCtrlTypeCode + "不存在.");
				}
				devCtrl.setDecttype_id(devCtrlType);
			}
			
			Cell cellBtnNum = row.getCell(5);//类型按钮数量
			String btnNum = getCellStr(cellBtnNum);
			devCtrl.setDecttype_btnNum("".equals(btnNum) ? null : Integer.parseInt(btnNum));
			
			Cell cellBuyDate = row.getCell(6);//购买时间
			Date buyDate = null;
			if(cellBuyDate != null){
				switch (cellBuyDate.getCellType()) {
				case Cell.CELL_TYPE_STRING:
					String str = cellBuyDate.getStringCellValue();
					buyDate = DateUtil.parseDate(str, "yyyy-mm-dd");
					break;
				default:
					buyDate = cellBuyDate.getDateCellValue();
					break;
				}
			}
			devCtrl.setDect_buyDate(buyDate);
			
			devCtrl.setDect_state(1);//使用状态
			
//			Cell cellState = row.getCell(8);//使用状态
//			String state = getCellStr(cellState);
//			if("在用".equals(state)) {
//				devCtrl.setDect_state(1);
//			}
//			if("未用".equals(state)) {
//				devCtrl.setDect_state(0);
//			}
			
			Cell cellOfferSer = row.getCell(7);//是否对外提供服务
			String offerSer = getCellStr(cellOfferSer);
			if("是".equals(offerSer)) {
				devCtrl.setCh_offerSer(1);
			}
			if("否".equals(offerSer)) {
				devCtrl.setCh_offerSer(0);
			}
			
			Cell cellDesc = row.getCell(8);//设备说明
			devCtrl.setDect_decsription(getCellStr(cellDesc));
			
			devCtrlDao.saveOrUpdate(devCtrl);
			
			Gm_DevCtrlSts devCtrlSts = devCtrlStsDao.findUniqueByProperty("dect_id", devCtrl);
			if(devCtrlSts == null) {
				devCtrlSts = new Gm_DevCtrlSts();
				devCtrlSts.setDect_id(devCtrl);
				devCtrlSts.setDect_state(0);
				devCtrlSts.setDecst_valid(1);
				devCtrlStsDao.save(devCtrlSts);
			}
			
			Gm_DevCtrlOperate devCtrlOperate = devCtrlOperateDao.findUniqueByProperty("dect_id", devCtrl);
			if(devCtrlOperate == null) {
				devCtrlOperate = new Gm_DevCtrlOperate();
				devCtrlOperate.setPla_id(null);
				devCtrlOperate.setDect_id(devCtrl);
				devCtrlOperate.setDeco_state(0);
				devCtrlOperate.setDeco_result(0);
				devCtrlOperateDao.save(devCtrlOperate);
			}
		}
		
		return "导入控制设备信息成功.";
	}
	
	/**
	 * 生成控制设备按钮信息
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,isolation = Isolation.READ_UNCOMMITTED,rollbackFor = Exception.class)
	public String generalDevCtrlBtn(Sheet sheet) {
		int maxRow = sheet.getLastRowNum();
		for (int i = 1; i <= maxRow; i++) {
			Row row = sheet.getRow(i);
			
			Cell cellCode = null;
			try {
				cellCode = row.getCell(0);//控制设备编号
			} catch (java.lang.NullPointerException e) {
				continue;
			}
			
			String code = getCellStr(cellCode);
			if("".equals(code)) {
				continue;
			}
			Gm_DevCtrl devCtrl = devCtrlDao.findUniqueByProperty("dect_no", code);
			if(devCtrl == null) {
				throw new ServiceException("控制设备按钮信息表里第" + (i+1) + "行中的控制设备编号:" + code + "不存在.");
			}
			
			Cell cellName = row.getCell(1);	 //控制设备名称
			Cell cellOpType = row.getCell(2);//操作类型
			String opTypeStr = getCellStr(cellOpType);
			int opType = 0;
			if("开".equals(opTypeStr)) {
				opType = 1;
			}
			if("停".equals(opTypeStr)) {
				opType = 2;
			}
			if("关".equals(opTypeStr)) {
				opType = 3;
			}
			Search search = new Search();
			search.addFilterEqual("dect_id", devCtrl.getDect_id());
			search.addFilterEqual("deco_type", opType);
			Op_DevCtrlBtn devCtrlBtn = null;
			List<Op_DevCtrlBtn> devCtrlBtns = devCtrlBtnDao.search(search);
			if(devCtrlBtns.isEmpty()) {
				devCtrlBtn = new Op_DevCtrlBtn();
			} else {
				devCtrlBtn = devCtrlBtns.get(0);
			}
			devCtrlBtn.setDect_id(devCtrl.getDect_id());
			devCtrlBtn.setDectbtn_name(getCellStr(cellName));
			devCtrlBtn.setDeco_type(opType);
			
			Cell cellDevCode = row.getCell(3);//所属设备编号
			String devCode = getCellStr(cellDevCode);
			if("".equals(devCode)) {
				throw new ServiceException("控制设备按钮信息表里第" + (i+1) + "行中的所属设备编号为空.");
			} else {
				Gm_Device device = deviceDao.findUniqueByProperty("dev_no", devCode);
				if(device == null) {
					throw new ServiceException("控制设备按钮信息表里第" + (i+1) + "行中的所属设备编号:" + devCode + "不存在.");
				}
				devCtrlBtn.setDev_id(device);
			}
			
			Cell cellChlNo = row.getCell(4);//控制通道
			String chlNo = getCellStr(cellChlNo);
			devCtrlBtn.setDect_chlNo("".equals(chlNo) ? null : Integer.parseInt(chlNo));
			
			Cell cellCtlType = row.getCell(5);//控制类型
			String ctlTypeStr = getCellStr(cellCtlType);
			if("正向脉冲".equals(ctlTypeStr)) {
				devCtrlBtn.setDect_ctlType(1);
			}
			if("反向脉冲".equals(ctlTypeStr)) {
				devCtrlBtn.setDect_ctlType(2);
			}
			if("高电平".equals(ctlTypeStr)) {
				devCtrlBtn.setDect_ctlType(3);
			}
			if("低电平".equals(ctlTypeStr)) {
				devCtrlBtn.setDect_ctlType(4);
			}
			
			Cell cellCtlDelay = row.getCell(6);//操作延时
			String ctlDelay = getCellStr(cellCtlDelay);
			devCtrlBtn.setDect_ctlDelay("".equals(ctlDelay) ? null : Integer.parseInt(ctlDelay));
			
			devCtrlBtnDao.saveOrUpdate(devCtrlBtn);
			
		}
//		devCtrlBtnDao.flush();
		return "导入控制设备按钮信息成功.";
	}
	
	/**
	 * 生成控制设备状态配置信息
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = false,propagation = Propagation.REQUIRED,isolation = Isolation.READ_UNCOMMITTED,rollbackFor = Exception.class)
	public String generalDevCtrlSts(Sheet sheet) throws Exception {
		int maxRow = sheet.getLastRowNum();
		for (int i = 1; i <= maxRow; i++) {
			Row row = sheet.getRow(i);
			
			Cell cellCode = null;
			try {
				cellCode = row.getCell(0);//控制设备编号
			} catch (java.lang.NullPointerException e) {
				continue;
			}
			
			String code = getCellStr(cellCode);
			if("".equals(code)) {
				continue;
			}
			
			Gm_DevCtrl devCtrl = devCtrlDao.findUniqueByProperty("dect_no", code);
			if(devCtrl == null) {
				throw new ServiceException("控制设备状态配置信息表里第" + (i+1) + "行中的控制设备编号:" + code + "不存在.");
			}
			Cell cellName = row.getCell(1);	 //状态名称
			Cell cellType = row.getCell(2);//状态类型
			Cell cellChlCode = row.getCell(3);//状态通道编号
			Cell cellMax = row.getCell(4);//范围上限
			String max = getCellStr(cellMax);
			Cell cellMin = row.getCell(5);//范围下限
			String min = getCellStr(cellMin);
			
			String channelCode = getCellStr(cellChlCode);
			Gm_Channel channel = channelDao.findUniqueByProperty("ch_no", channelCode);
			if(channel == null) {
				throw new ServiceException("控制设备状态配置信息表里第" + (i+1) + "行中的状态通道编号为空.");
			}
			
			String stateStr = getCellStr(cellType);
			int state = 0;
			if("".equals(stateStr)) {
				throw new ServiceException("控制设备状态配置信息表里第" + (i+1) + "行中的状态类型为空.");
			}
			if("开".equals(stateStr)) {
				state = 1;
			}
			if("停".equals(stateStr)) {
				state = 2;
			}
			if("关".equals(stateStr)) {
				state = 3;
			}
			if("运行".equals(stateStr)) {
				state = 4;
			}
			
			Search search = new Search();
			search.addFilterEqual("dect_id", devCtrl);
			search.addFilterEqual("dect_state", state);
			Op_DevCtrlSts devCtrlSts = null;
			List<Op_DevCtrlSts> devCtrlStsList = op_DevCtrlStsDao.search(search);
			if(devCtrlStsList.isEmpty()) {
				devCtrlSts = new Op_DevCtrlSts();
			} else {
				devCtrlSts = devCtrlStsList.get(0);
			}
			
			devCtrlSts.setDect_id(devCtrl);
			devCtrlSts.setDectSts_name(getCellStr(cellName));
			devCtrlSts.setCh_id(channel);
			devCtrlSts.setDect_state(state);
			devCtrlSts.setCh_max("".equals(max) ? null : Float.parseFloat(max));
			devCtrlSts.setCh_min("".equals(min) ? null : Float.parseFloat(min));
			
			op_DevCtrlStsDao.saveOrUpdate(devCtrlSts);
		}
		
//		op_DevCtrlStsDao.flush();
		throw new  Exception("测试");
//		return "导入控制设备状态配置成功.";
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
	
	public String getCellLngLatStr(Cell cell){
		String str = "";
		if(cell != null) {
			switch (cell.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				String strNumer = cell.getNumericCellValue()+"";
				str = strNumer.trim();
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
	 * 项目导出
	 * @return
	 * @author Wang_Yuliang
	 */
	public HSSFWorkbook daochuProjectExcel(HSSFWorkbook wb, String scene_id_arr){
		if(wb==null)
			wb = new HSSFWorkbook();		
		//场景信息表
		//daochuOpScene(wb);
		//设备信息表
		//daochuGmDevice(wb);
		//设备信息表
		//daochuGmDevNet(wb);
		//采集通道信息表
		//daochuGmChannel(wb);
		//控制设备信息表
		//daochuGmDevCtrl(wb);
		//控制设备按钮配置表
		//daochuOpDevCtrlBtn(wb);
		//控制设备状态配置表
		//daochuOpDevCtrlSts(wb);
		//供应商信息表
		//daochuOpSupplier(wb);
		//采集通道应用类型信息表
		//daochuOpChannelType(wb);
		//控制设备类型信息表
		//daochuOp_DevCtrlType(wb);
		//改为根据条件导出了 2012-05-28 Wang_Yuliang
		//场景信息表
		daochuOpScene(wb, scene_id_arr);
		//设备信息表
		List<Gm_Device> gm_Device_list = daochuGmDevice(wb, scene_id_arr);
		//网络信息表
		daochuGmDevNet(wb, gm_Device_list);
		//采集通道信息表
		daochuGmChannel(wb, scene_id_arr);
		//控制设备信息表
		List<Gm_DevCtrl> gm_DevCtrl_list =  daochuGmDevCtrl(wb, scene_id_arr);
		//控制设备按钮配置表
		daochuOpDevCtrlBtn(wb, gm_DevCtrl_list);
		//控制设备状态配置表
		daochuOpDevCtrlSts(wb, gm_DevCtrl_list);
		//供应商信息表
		daochuOpSupplier(wb);
		//采集通道应用类型信息表
		daochuOpChannelType(wb);
		//控制设备类型信息表
		daochuOp_DevCtrlType(wb);
		return wb;
	}
	
	/**
	 * 导出场景信息
	 * @param wb
	 * @return
	 * @author Wang_Yuliang
	 */
	public void daochuOpScene(HSSFWorkbook wb){
		HSSFSheet sheet = wb.createSheet("场景信息表");
	    sheet.setDefaultColumnWidth((short)20);
	    
	    HSSFCellStyle cs_head = wb.createCellStyle();
	    cs_head.setWrapText(true);     
	    cs_head.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	    cs_head.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFRow row = sheet.createRow((short) 0);
        HSSFCell cell;
        cell = row.createCell((short)0);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("场景编号\r\n(唯一,必填)"));
        cell = row.createCell((short)1);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("场景名称\r\n(必填)"));
        cell = row.createCell((short)2);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("场景类型细类\r\n(下拉选择)"));
        cell = row.createCell((short)3);
        cell.setCellStyle(cs_head);
        cell.setCellValue("场景所在地");
        cell = row.createCell((short)4);
        cell.setCellStyle(cs_head);
        cell.setCellValue("场景说明");
        cell = row.createCell((short)5);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("创建者\r\n(必填)"));
        cell = row.createCell((short)6);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("场景类型\r\n(下拉选择)"));
        cell = row.createCell((short)7);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("场景类型子类\r\n(下拉选择)"));
        cell = row.createCell((short)8);
        cell.setCellStyle(cs_head);
        cell.setCellValue("区划编码");
        cell = row.createCell((short)9);
        cell.setCellStyle(cs_head);
        cell.setCellValue("关键字");
        cell = row.createCell((short)10);
        cell.setCellStyle(cs_head);
        cell.setCellValue("排序号");
        
        cell = row.createCell((short)11);
        cell.setCellStyle(cs_head);
        cell.setCellValue("经度");
        
        cell = row.createCell((short)12);
        cell.setCellStyle(cs_head);
        cell.setCellValue("纬度");
        
	    HSSFCellStyle cs_data = wb.createCellStyle();
	    cs_data.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        List<Op_Scene> op_Scene_list = this.sceneDao.findAll();
        int i = 1;
        Map op_Scene_map = new HashMap();        	
        for(Op_Scene op_Scene : op_Scene_list){
        	op_Scene_map.put(op_Scene.getScene_id(), op_Scene);
        }
        
        for(Op_Scene op_Scene : op_Scene_list){
        	row = sheet.createRow((short) i);i++;
            cell = row.createCell((short)0);//场景编号
            cell.setCellStyle(cs_data);            
            cell.setCellValue(op_Scene.getScene_no()!=null?op_Scene.getScene_no():"");
            cell = row.createCell((short)1);//场景名称
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_Scene.getScene_name()!=null?op_Scene.getScene_name():"");
            cell = row.createCell((short)2);//场景类型细类
            cell.setCellStyle(cs_data);
            String scene_gtype_name = Scene_gtype.findNameById(op_Scene.getScene_gtype());
            cell.setCellValue(scene_gtype_name!=null?scene_gtype_name:"");
            cell = row.createCell((short)3);//场景所在地
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_Scene.getScene_addr()!=null?op_Scene.getScene_addr():"");
            cell = row.createCell((short)4);//场景说明
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_Scene.getScene_desc()!=null?op_Scene.getScene_desc():"");
            cell = row.createCell((short)5);//创建者
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_Scene.getScene_creater()!=null?op_Scene.getScene_creater():"");
            cell = row.createCell((short)6);//场景类型
            cell.setCellStyle(cs_data);
            String scene_type_name = Scene_type.findNameById(op_Scene.getScene_type());
            cell.setCellValue(scene_type_name!=null?scene_type_name:"");
            cell = row.createCell((short)7);//场景类型子类
            cell.setCellStyle(cs_data); 
            String scene_ctype_name = Scene_ctype.findNameById(op_Scene.getScene_ctype());
            cell.setCellValue(scene_ctype_name!=null?scene_ctype_name:"");
            cell = row.createCell((short)8);//区划编码
            cell.setCellStyle(cs_data);
            cell.setCellValue((op_Scene.getArea_id()!=null&&op_Scene.getArea_id().getArea_id()!=null)?op_Scene.getArea_id().getArea_id():"");
            cell = row.createCell((short)9);//关键字
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_Scene.getScene_keyWord()!=null?op_Scene.getScene_keyWord():"");
            cell = row.createCell((short)10);//排序号
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_Scene.getScene_order()!=null?op_Scene.getScene_order():0);
        }
        
	}
	

	/**
	 * 导出场景信息
	 * @param wb
	 * @param scene_id_arr String类型 以逗号隔开如 -1,id1,id2,..idx， 里面有默认的-1避免报错
	 * @return
	 * @author Wang_Yuliang
	 */
	public void daochuOpScene(HSSFWorkbook wb, String scene_id_arr){
		HSSFSheet sheet = wb.createSheet("场景信息表");
	    sheet.setDefaultColumnWidth((short)20);
	    
	    HSSFCellStyle cs_head = wb.createCellStyle();
	    cs_head.setWrapText(true);     
	    cs_head.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	    cs_head.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFRow row = sheet.createRow((short) 0);
        HSSFCell cell;
        cell = row.createCell((short)0);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("场景编号\r\n(唯一,必填)"));
        cell = row.createCell((short)1);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("场景名称\r\n(必填)"));
        cell = row.createCell((short)2);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("场景类型细类\r\n(下拉选择)"));
        cell = row.createCell((short)3);
        cell.setCellStyle(cs_head);
        cell.setCellValue("场景所在地");
        cell = row.createCell((short)4);
        cell.setCellStyle(cs_head);
        cell.setCellValue("场景说明");
        cell = row.createCell((short)5);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("创建者\r\n(必填)"));
        cell = row.createCell((short)6);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("场景类型\r\n(下拉选择)"));
        cell = row.createCell((short)7);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("场景类型子类\r\n(下拉选择)"));
        cell = row.createCell((short)8);
        cell.setCellStyle(cs_head);
        cell.setCellValue("区划编码");
        cell = row.createCell((short)9);
        cell.setCellStyle(cs_head);
        cell.setCellValue("关键字");
        cell = row.createCell((short)10);
        cell.setCellStyle(cs_head);
        cell.setCellValue("排序号");
        cell = row.createCell((short)11);
        cell.setCellStyle(cs_head);
        cell.setCellValue("经度");
        cell = row.createCell((short)12);
        cell.setCellStyle(cs_head);
        cell.setCellValue("纬度");
        
	    HSSFCellStyle cs_data = wb.createCellStyle();
	    cs_data.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	    
	    String[] sceneIds = scene_id_arr.split(",");
        List<Op_Scene> op_Scene_list = this.sceneDao.search(new Search().addFilterIn("scene_id", sceneIds));
        int i = 1;
        Map op_Scene_map = new HashMap();        	
        for(Op_Scene op_Scene : op_Scene_list){
        	op_Scene_map.put(op_Scene.getScene_id(), op_Scene);
        }
        
        for(Op_Scene op_Scene : op_Scene_list){
        	row = sheet.createRow((short) i);i++;
            cell = row.createCell((short)0);//场景编号
            cell.setCellStyle(cs_data);            
            cell.setCellValue(op_Scene.getScene_no()!=null?op_Scene.getScene_no():"");
            cell = row.createCell((short)1);//场景名称
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_Scene.getScene_name()!=null?op_Scene.getScene_name():"");
            cell = row.createCell((short)2);//场景类型细类
            cell.setCellStyle(cs_data);
            String scene_gtype_name = Scene_gtype.findNameById(op_Scene.getScene_gtype());
            cell.setCellValue(scene_gtype_name!=null?scene_gtype_name:"");
            cell = row.createCell((short)3);//场景所在地
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_Scene.getScene_addr()!=null?op_Scene.getScene_addr():"");
            cell = row.createCell((short)4);//场景说明
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_Scene.getScene_desc()!=null?op_Scene.getScene_desc():"");
            cell = row.createCell((short)5);//创建者
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_Scene.getScene_creater()!=null?op_Scene.getScene_creater():"");
            cell = row.createCell((short)6);//场景类型
            cell.setCellStyle(cs_data);
            String scene_type_name = Scene_type.findNameById(op_Scene.getScene_type());
            cell.setCellValue(scene_type_name!=null?scene_type_name:"");
            cell = row.createCell((short)7);//场景类型子类
            cell.setCellStyle(cs_data); 
            String scene_ctype_name = Scene_ctype.findNameById(op_Scene.getScene_ctype());
            cell.setCellValue(scene_ctype_name!=null?scene_ctype_name:"");
            cell = row.createCell((short)8);//区划编码
            cell.setCellStyle(cs_data);
            cell.setCellValue((op_Scene.getArea_id()!=null&&op_Scene.getArea_id().getArea_id()!=null)?op_Scene.getArea_id().getArea_id():"");
            cell = row.createCell((short)9);//关键字
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_Scene.getScene_keyWord()!=null?op_Scene.getScene_keyWord():"");
            
            cell = row.createCell((short)10);//排序号
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_Scene.getScene_order()!=null?op_Scene.getScene_order():0);
            
            cell = row.createCell((short)11);//经度
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_Scene.getScene_longitude()!=null?op_Scene.getScene_longitude():"");
            
            cell = row.createCell((short)12);//纬度
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_Scene.getScene_latitude()!=null?op_Scene.getScene_latitude():"");
        }
        
	}
		

	/**
	 * 导出设备信息
	 * @param wb
	 * @return
	 * @author Wang_Yuliang
	 */
	public void daochuGmDevice(HSSFWorkbook wb){
		HSSFSheet sheet = wb.createSheet("设备信息表");
	    sheet.setDefaultColumnWidth((short)20);
	    
	    HSSFCellStyle cs_head = wb.createCellStyle();
	    cs_head.setWrapText(true);     
	    cs_head.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	    cs_head.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFRow row = sheet.createRow((short) 0);
        HSSFCell cell;
        cell = row.createCell((short)0);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("设备编号\r\n(唯一,必填)"));
        cell = row.createCell((short)1);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("设备名称\r\n(必填)"));
        cell = row.createCell((short)2);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("设备序列号\r\n(唯一,必填,为14位数字)"));
        cell = row.createCell((short)3);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("所属场景编号\r\n(下拉选择，必填)"));
        cell = row.createCell((short)4);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("供电方式"));
        cell = row.createCell((short)5);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("设备IMSI/说明"));
        cell = row.createCell((short)6);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("购买时间\r\n(日期)"));
        cell = row.createCell((short)7);
        cell.setCellStyle(cs_head);
        cell.setCellValue("设备厂家编号");
        cell = row.createCell((short)8);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("设备服务商编号"));
        cell = row.createCell((short)9);
        cell.setCellStyle(cs_head);
        cell.setCellValue("设备使用状态(下拉选择)");
        
	    HSSFCellStyle cs_data = wb.createCellStyle();
	    cs_data.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        List<Gm_Device> gm_Device_list = this.deviceDao.findAll();
        int i = 1;
        for(Gm_Device gm_Device : gm_Device_list){
            row = sheet.createRow((short) i);i++;
            cell = row.createCell((short)0);//设备编号
            cell.setCellStyle(cs_data);            
            cell.setCellValue(gm_Device.getDev_no()!=null?gm_Device.getDev_no():"");
            cell = row.createCell((short)1);//设备名称
            cell.setCellStyle(cs_data);
            cell.setCellValue(gm_Device.getDev_name()!=null?gm_Device.getDev_name():"");
            cell = row.createCell((short)2);//设备序列号
            cell.setCellStyle(cs_data);
            cell.setCellValue(gm_Device.getDev_serial()!=null?gm_Device.getDev_serial():"");            
            cell = row.createCell((short)3);//所属场景编号
            cell.setCellStyle(cs_data);
            cell.setCellValue((gm_Device.getScene_id()!=null&&gm_Device.getScene_id().getScene_id()!=null&&gm_Device.getScene_id().getScene_no()!=null)?gm_Device.getScene_id().getScene_no():"");
            cell = row.createCell((short)4);//供电方式
            cell.setCellStyle(cs_data);
            cell.setCellValue(gm_Device.getDev_powerType()!=null?gm_Device.getDev_powerType():"");
            cell = row.createCell((short)5);//设备IMSI
            cell.setCellStyle(cs_data);
            cell.setCellValue(gm_Device.getDev_imsi()!=null?gm_Device.getDev_imsi():"");
            cell = row.createCell((short)6);//购买时间
            cell.setCellStyle(cs_data);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            cell.setCellValue(gm_Device.getDev_buyDate()!=null?df.format(gm_Device.getDev_buyDate()):"");
            cell = row.createCell((short)7);//设备厂家编号
            cell.setCellStyle(cs_data);           
            cell.setCellValue((gm_Device.getCust_saleId()!=null&&gm_Device.getCust_saleId().getSup_id()!=null&&gm_Device.getCust_saleId().getSup_no()!=null)?gm_Device.getCust_saleId().getSup_no():"");
            cell = row.createCell((short)8);//设备服务商编号
            cell.setCellStyle(cs_data);
            cell.setCellValue((gm_Device.getCust_serviceId()!=null&&gm_Device.getCust_serviceId().getSup_id()!=null&&gm_Device.getCust_serviceId().getSup_no()!=null)?gm_Device.getCust_serviceId().getSup_no():"");
            cell = row.createCell((short)9);//设备使用状态
            cell.setCellStyle(cs_data);
            String dev_state_name = Dev_state.findNameById(gm_Device.getDev_state());
            cell.setCellValue(dev_state_name!=null?dev_state_name:"");
        }
        		
	}
	

	/**
	 * 导出设备信息
	 * @param wb
	 * @param scene_id_arr String类型 以逗号隔开如 -1,id1,id2,..idx， 里面有默认的-1避免报错
	 * @return List<Gm_Device> 为导出子表信息提供参数
	 * @author Wang_Yuliang
	 */
	public List<Gm_Device> daochuGmDevice(HSSFWorkbook wb, String scene_id_arr){
		HSSFSheet sheet = wb.createSheet("设备信息表");
	    sheet.setDefaultColumnWidth((short)20);
	    
	    HSSFCellStyle cs_head = wb.createCellStyle();
	    cs_head.setWrapText(true);     
	    cs_head.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	    cs_head.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFRow row = sheet.createRow((short) 0);
        HSSFCell cell;
        cell = row.createCell((short)0);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("设备编号\r\n(唯一,必填)"));
        cell = row.createCell((short)1);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("设备名称\r\n(必填)"));
        cell = row.createCell((short)2);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("设备序列号\r\n(唯一,必填,为14位数字)"));
        cell = row.createCell((short)3);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("所属场景编号\r\n(下拉选择，必填)"));
        cell = row.createCell((short)4);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("供电方式"));
        cell = row.createCell((short)5);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("设备IMSI/说明"));
        cell = row.createCell((short)6);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("购买时间\r\n(日期)"));
        cell = row.createCell((short)7);
        cell.setCellStyle(cs_head);
        cell.setCellValue("设备厂家编号");
        cell = row.createCell((short)8);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("设备服务商编号"));
//        cell = row.createCell((short)9);
//        cell.setCellStyle(cs_head);
//        cell.setCellValue("设备使用状态(下拉选择)");
        
	    HSSFCellStyle cs_data = wb.createCellStyle();
	    cs_data.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	    String[] sceneIds = scene_id_arr.split(",");
        List<Gm_Device> gm_Device_list = this.deviceDao.search(new Search().addFilterIn("scene_id.scene_id", sceneIds));
        int i = 1;
        for(Gm_Device gm_Device : gm_Device_list){
            row = sheet.createRow((short) i);i++;
            cell = row.createCell((short)0);//设备编号
            cell.setCellStyle(cs_data);            
            cell.setCellValue(gm_Device.getDev_no()!=null?gm_Device.getDev_no():"");
            cell = row.createCell((short)1);//设备名称
            cell.setCellStyle(cs_data);
            cell.setCellValue(gm_Device.getDev_name()!=null?gm_Device.getDev_name():"");
            cell = row.createCell((short)2);//设备序列号
            cell.setCellStyle(cs_data);
            cell.setCellValue(gm_Device.getDev_serial()!=null?gm_Device.getDev_serial():"");            
            cell = row.createCell((short)3);//所属场景编号
            cell.setCellStyle(cs_data);
            cell.setCellValue((gm_Device.getScene_id()!=null&&gm_Device.getScene_id().getScene_id()!=null&&gm_Device.getScene_id().getScene_no()!=null)?gm_Device.getScene_id().getScene_no():"");
            cell = row.createCell((short)4);//供电方式
            cell.setCellStyle(cs_data);
            cell.setCellValue(gm_Device.getDev_powerType()!=null?gm_Device.getDev_powerType():"");
            cell = row.createCell((short)5);//设备IMSI
            cell.setCellStyle(cs_data);
            cell.setCellValue(gm_Device.getDev_imsi()!=null?gm_Device.getDev_imsi():"");
            cell = row.createCell((short)6);//购买时间
            cell.setCellStyle(cs_data);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            cell.setCellValue(gm_Device.getDev_buyDate()!=null?df.format(gm_Device.getDev_buyDate()):"");
            cell = row.createCell((short)7);//设备厂家编号
            cell.setCellStyle(cs_data);           
            cell.setCellValue((gm_Device.getCust_saleId()!=null&&gm_Device.getCust_saleId().getSup_id()!=null&&gm_Device.getCust_saleId().getSup_no()!=null)?gm_Device.getCust_saleId().getSup_no():"");
            cell = row.createCell((short)8);//设备服务商编号
            cell.setCellStyle(cs_data);
            cell.setCellValue((gm_Device.getCust_serviceId()!=null&&gm_Device.getCust_serviceId().getSup_id()!=null&&gm_Device.getCust_serviceId().getSup_no()!=null)?gm_Device.getCust_serviceId().getSup_no():"");
//            cell = row.createCell((short)9);//设备使用状态
//            cell.setCellStyle(cs_data);
//            String dev_state_name = Dev_state.findNameById(gm_Device.getDev_state());
//            cell.setCellValue(dev_state_name!=null?dev_state_name:"");
        }
        
        return gm_Device_list;
	}
	


	/**
	 * 导出网络信息
	 * @param wb
	 * @return
	 * @author Wang_Yuliang
	 */
	public void daochuGmDevNet(HSSFWorkbook wb){
		HSSFSheet sheet = wb.createSheet("网络信息表");
	    sheet.setDefaultColumnWidth((short)20);
	    
	    HSSFCellStyle cs_head = wb.createCellStyle();
	    cs_head.setWrapText(true);     
	    cs_head.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	    cs_head.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFRow row = sheet.createRow((short) 0);
        HSSFCell cell;
        cell = row.createCell((short)0);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("网络编号\r\n(必填)"));
        cell = row.createCell((short)1);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("设备编号\r\n(必填)"));
        cell = row.createCell((short)2);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("网内角色\r\n(下拉选择)"));
        cell = row.createCell((short)3);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("连接方式\r\n(下拉选择)"));
        cell = row.createCell((short)4);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("SIM卡号/PAM卡号/通讯波特率"));
        cell = row.createCell((short)5);
        cell.setCellStyle(cs_head);
        cell.setCellValue("网络使用状态");
        cell = row.createCell((short)6);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("协议类型"));
        cell = row.createCell((short)7);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("短信中心号"));
        
	    HSSFCellStyle cs_data = wb.createCellStyle();
	    cs_data.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        List<Gm_DevNet> gm_DevNet_list = this.devNetDao.findAll();
        Map gm_DevNet_map = new HashMap();        	
        for(Gm_DevNet gm_DevNet : gm_DevNet_list){
        	gm_DevNet_map.put(gm_DevNet.getNet_id(), gm_DevNet);
        }
        int i = 1;
        for(Gm_DevNet gm_DevNet : gm_DevNet_list){
            row = sheet.createRow((short) i);i++;
            cell = row.createCell((short)0);//网络编号
            cell.setCellStyle(cs_data);            
            cell.setCellValue(gm_DevNet.getNet_no()!=null?gm_DevNet.getNet_no():"");
            cell = row.createCell((short)1);//设备编号
            cell.setCellStyle(cs_data);
            cell.setCellValue((gm_DevNet.getDev_id()!=null&&gm_DevNet.getDev_id().getDev_id()!=null&&gm_DevNet.getDev_id().getDev_no()!=null)?gm_DevNet.getDev_id().getDev_no():"");
            cell = row.createCell((short)2);//网内角色
            cell.setCellStyle(cs_data);
            String net_role_name = Net_role.findNameById(gm_DevNet.getNet_role());
            cell.setCellValue(net_role_name!=null?net_role_name:"");
            cell = row.createCell((short)3);//"连接方式
            cell.setCellStyle(cs_data);
            String net_linkSts_name = Net_linkSts.findNameById(gm_DevNet.getNet_linkSts());
            cell.setCellValue(net_linkSts_name!=null?net_linkSts_name:"");
            cell = row.createCell((short)4);//SIM卡号
            cell.setCellStyle(cs_data);
            cell.setCellValue(gm_DevNet.getNet_sim()!=null?gm_DevNet.getNet_sim():"");
            cell = row.createCell((short)5);//网络使用状态
            cell.setCellStyle(cs_data);
            String net_state_name = Net_state.findNameById(gm_DevNet.getNet_state());
            cell.setCellValue(net_state_name!=null?net_state_name:"");
            cell = row.createCell((short)6);//协议类型
            cell.setCellStyle(cs_data);
            cell.setCellValue(gm_DevNet.getNet_pltType()!=null?gm_DevNet.getNet_pltType()+"":"");
            cell = row.createCell((short)7);//短信中心号
            cell.setCellStyle(cs_data); 
            cell.setCellValue(gm_DevNet.getNet_smsc()!=null?gm_DevNet.getNet_smsc():"");
        }
        
	}
	

	/**
	 * 导出网络信息
	 * @param wb
	 * @param gm_Device_list
	 * @return
	 * @author Wang_Yuliang
	 */
	public void daochuGmDevNet(HSSFWorkbook wb, List<Gm_Device> gm_Device_list){
		HSSFSheet sheet = wb.createSheet("网络信息表");
	    sheet.setDefaultColumnWidth((short)20);
	    
	    HSSFCellStyle cs_head = wb.createCellStyle();
	    cs_head.setWrapText(true);     
	    cs_head.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	    cs_head.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFRow row = sheet.createRow((short) 0);
        HSSFCell cell;
        cell = row.createCell((short)0);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("网络编号\r\n(必填)"));
        cell = row.createCell((short)1);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("设备编号\r\n(必填)"));
        cell = row.createCell((short)2);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("网内角色\r\n(下拉选择)"));
        cell = row.createCell((short)3);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("连接方式\r\n(下拉选择)"));
        cell = row.createCell((short)4);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("SIM卡号/PAM卡号/通讯波特率"));
//        cell = row.createCell((short)5);
//        cell.setCellStyle(cs_head);
//        cell.setCellValue("网络使用状态");
        cell = row.createCell((short)5);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("协议类型"));
        cell = row.createCell((short)6);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("短信中心号"));
        
	    HSSFCellStyle cs_data = wb.createCellStyle();
	    cs_data.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	    
        List<Gm_DevNet> gm_DevNet_list = new ArrayList<Gm_DevNet>();
        if(gm_Device_list.size()>0)
        	gm_DevNet_list = this.devNetDao.search(new Search().addFilterIn("dev_id", gm_Device_list));
        Map gm_DevNet_map = new HashMap();        	
        for(Gm_DevNet gm_DevNet : gm_DevNet_list){
        	gm_DevNet_map.put(gm_DevNet.getNet_id(), gm_DevNet);
        }
        int i = 1;
        for(Gm_DevNet gm_DevNet : gm_DevNet_list){
            row = sheet.createRow((short) i);i++;
            cell = row.createCell((short)0);//网络编号
            cell.setCellStyle(cs_data);            
            cell.setCellValue(gm_DevNet.getNet_no()!=null?gm_DevNet.getNet_no():"");
            cell = row.createCell((short)1);//设备编号
            cell.setCellStyle(cs_data);
            cell.setCellValue((gm_DevNet.getDev_id()!=null&&gm_DevNet.getDev_id().getDev_id()!=null&&gm_DevNet.getDev_id().getDev_no()!=null)?gm_DevNet.getDev_id().getDev_no():"");
            cell = row.createCell((short)2);//网内角色
            cell.setCellStyle(cs_data);
            String net_role_name = Net_role.findNameById(gm_DevNet.getNet_role());
            cell.setCellValue(net_role_name!=null?net_role_name:"");
            cell = row.createCell((short)3);//"连接方式
            cell.setCellStyle(cs_data);
            String net_linkSts_name = Net_linkSts.findNameById(gm_DevNet.getNet_linkSts());
            cell.setCellValue(net_linkSts_name!=null?net_linkSts_name:"");
            cell = row.createCell((short)4);//SIM卡号
            cell.setCellStyle(cs_data);
            cell.setCellValue(gm_DevNet.getNet_sim()!=null?gm_DevNet.getNet_sim():"");
//            cell = row.createCell((short)5);//网络使用状态
//            cell.setCellStyle(cs_data);
//            String net_state_name = Net_state.findNameById(gm_DevNet.getNet_state());
//            cell.setCellValue(net_state_name!=null?net_state_name:"");
            cell = row.createCell((short)5);//协议类型
            cell.setCellStyle(cs_data);
            cell.setCellValue(gm_DevNet.getNet_pltType()!=null?gm_DevNet.getNet_pltType()+"":"");
            cell = row.createCell((short)6);//短信中心号
            cell.setCellStyle(cs_data); 
            cell.setCellValue(gm_DevNet.getNet_smsc()!=null?gm_DevNet.getNet_smsc():"");
        }
        
	}
	

	/**
	 * 导出采集通道信息
	 * @param wb
	 * @return
	 * @author Wang_Yuliang
	 */
	public void daochuGmChannel(HSSFWorkbook wb){
		HSSFSheet sheet = wb.createSheet("采集通道信息表");
	    sheet.setDefaultColumnWidth((short)20);
	    
	    HSSFCellStyle cs_head = wb.createCellStyle();
	    cs_head.setWrapText(true);     
	    cs_head.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	    cs_head.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFRow row = sheet.createRow((short) 0);
        HSSFCell cell;
        cell = row.createCell((short)0);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("通道编号\r\n(不能为空)"));
        cell = row.createCell((short)1);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("通到名称\r\n(不能为空)"));
        cell = row.createCell((short)2);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("所属场景编号\r\n(下拉选择)"));
        cell = row.createCell((short)3);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("采集设备编号\r\n(下拉选择)"));
        cell = row.createCell((short)4);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("设备通道号\r\n(数字)"));
        cell = row.createCell((short)5);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("传感设备编号\r\n(下拉选择)"));
        cell = row.createCell((short)6);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("传感器通道类型编号"));
        cell = row.createCell((short)7);
        cell.setCellStyle(cs_head);
        cell.setCellValue("通道数据处理方式\r\n(下拉选择，必填)");
        cell = row.createCell((short)8);
        cell.setCellStyle(cs_head);
        cell.setCellValue("存储周期\r\n(整数，单位：秒)");
        cell = row.createCell((short)9);
        cell.setCellStyle(cs_head);
        cell.setCellValue("是否对外提供服务\r\n(下拉选择，必填)");
        cell = row.createCell((short)10);
        cell.setCellStyle(cs_head);
        cell.setCellValue("采集通道使用状态\r\n(下拉选择)");
        cell = row.createCell((short)11);
        cell.setCellStyle(cs_head);
        cell.setCellValue("位置编号");
        cell = row.createCell((short)12);
        cell.setCellStyle(cs_head);
        cell.setCellValue("通道深度");
        
	    HSSFCellStyle cs_data = wb.createCellStyle();
	    cs_data.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        List<Gm_Channel> gm_Channel_list = this.channelDao.findAll();
        int i = 1;
        for(Gm_Channel gm_Channel : gm_Channel_list){
            row = sheet.createRow((short) i);i++;
            cell = row.createCell((short)0);
            cell.setCellStyle(cs_data);            
            cell.setCellValue(gm_Channel.getCh_no()!=null?gm_Channel.getCh_no():"");
            cell = row.createCell((short)1);
            cell.setCellStyle(cs_data);
            cell.setCellValue(gm_Channel.getCh_name()!=null?gm_Channel.getCh_name():"");
            cell = row.createCell((short)2);
            cell.setCellStyle(cs_data);
            cell.setCellValue((gm_Channel.getScene_id()!=null&&gm_Channel.getScene_id().getScene_id()!=null&&gm_Channel.getScene_id().getScene_no()!=null)?gm_Channel.getScene_id().getScene_no():"");
            cell = row.createCell((short)3);
            cell.setCellStyle(cs_data);
            cell.setCellValue((gm_Channel.getDev_collectId()!=null&&gm_Channel.getDev_collectId().getDev_id()!=null&&gm_Channel.getDev_collectId().getDev_no()!=null)?gm_Channel.getDev_collectId().getDev_no():"");
            cell = row.createCell((short)4);
            cell.setCellStyle(cs_data);
            cell.setCellValue(gm_Channel.getCh_chlNo()!=null?gm_Channel.getCh_chlNo()+"":"");
            cell = row.createCell((short)5);
            cell.setCellStyle(cs_data);
            cell.setCellValue((gm_Channel.getDev_sensorId()!=null&&gm_Channel.getDev_sensorId().getDev_id()!=null&&gm_Channel.getDev_sensorId().getDev_no()!=null)?gm_Channel.getDev_sensorId().getDev_no():"");
            cell = row.createCell((short)6);
            cell.setCellStyle(cs_data);
            cell.setCellValue((gm_Channel.getChtype_id()!=null&&gm_Channel.getChtype_id().getChtype_id()!=null&&gm_Channel.getChtype_id().getChtype_no()!=null)?gm_Channel.getChtype_id().getChtype_no():"");
            cell = row.createCell((short)7);
            cell.setCellStyle(cs_data); 
            String ch_procesStyle_name = Ch_procesStyle.findNameById(gm_Channel.getCh_procesStyle());
            cell.setCellValue(ch_procesStyle_name!=null?ch_procesStyle_name:"");            
            cell = row.createCell((short)8);
            cell.setCellStyle(cs_data);
            cell.setCellValue(gm_Channel.getCh_memoryPeriod()!=null?gm_Channel.getCh_memoryPeriod()+"":"");
            cell = row.createCell((short)9);
            cell.setCellStyle(cs_data);
            String ch_offerSer_name = Ch_offerSer.findNameById(gm_Channel.getCh_offerSer());
            cell.setCellValue(ch_offerSer_name!=null?ch_offerSer_name:"");
            cell = row.createCell((short)10);
            cell.setCellStyle(cs_data);
            String ch_state_name = Ch_state.findNameById(gm_Channel.getCh_state());
            cell.setCellValue(ch_state_name!=null?ch_state_name:"");
            cell = row.createCell((short)11);
            cell.setCellStyle(cs_data);
            cell.setCellValue(gm_Channel.getCh_seat_no()!=null?gm_Channel.getCh_seat_no():"");
            cell = row.createCell((short)12);
            cell.setCellStyle(cs_data);
            cell.setCellValue(gm_Channel.getCh_depth()!=null?gm_Channel.getCh_depth():"");
        }
        
	}
	

	/**
	 * 导出采集通道信息
	 * @param wb
	 * @param scene_id_arr String类型 以逗号隔开如 -1,id1,id2,..idx， 里面有默认的-1避免报错
	 * @return
	 * @author Wang_Yuliang
	 */
	public void daochuGmChannel(HSSFWorkbook wb, String scene_id_arr){
		HSSFSheet sheet = wb.createSheet("采集通道信息表");
	    sheet.setDefaultColumnWidth((short)20);
	    
	    HSSFCellStyle cs_head = wb.createCellStyle();
	    cs_head.setWrapText(true);     
	    cs_head.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	    cs_head.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFRow row = sheet.createRow((short) 0);
        HSSFCell cell;
        cell = row.createCell((short)0);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("通道编号\r\n(不能为空)"));
        cell = row.createCell((short)1);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("通到名称\r\n(不能为空)"));
        cell = row.createCell((short)2);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("所属场景编号\r\n(下拉选择)"));
        cell = row.createCell((short)3);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("采集设备编号\r\n(下拉选择)"));
        cell = row.createCell((short)4);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("设备通道号\r\n(数字)"));
        cell = row.createCell((short)5);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("传感设备编号\r\n(下拉选择)"));
        cell = row.createCell((short)6);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("传感器通道类型编号"));
        cell = row.createCell((short)7);
        cell.setCellStyle(cs_head);
        cell.setCellValue("通道数据处理方式\r\n(下拉选择，必填)");
        cell = row.createCell((short)8);
        cell.setCellStyle(cs_head);
        cell.setCellValue("存储周期\r\n(整数，单位：秒)");
        cell = row.createCell((short)9);
        cell.setCellStyle(cs_head);
        cell.setCellValue("是否对外提供服务\r\n(下拉选择，必填)");
//        cell = row.createCell((short)10);
//        cell.setCellStyle(cs_head);
//        cell.setCellValue("采集通道使用状态\r\n(下拉选择)");
        cell = row.createCell((short)10);
        cell.setCellStyle(cs_head);
        cell.setCellValue("位置编号");
        cell = row.createCell((short)11);
        cell.setCellStyle(cs_head);
        cell.setCellValue("通道深度");
        
	    HSSFCellStyle cs_data = wb.createCellStyle();
	    cs_data.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	    String[] sceneIds = scene_id_arr.split(",");
        List<Gm_Channel> gm_Channel_list = this.channelDao.search(new Search().addFilterIn("scene_id.scene_id", sceneIds));
        int i = 1;
        for(Gm_Channel gm_Channel : gm_Channel_list){
            row = sheet.createRow((short) i);i++;
            cell = row.createCell((short)0);
            cell.setCellStyle(cs_data);            
            cell.setCellValue(gm_Channel.getCh_no()!=null?gm_Channel.getCh_no():"");
            cell = row.createCell((short)1);
            cell.setCellStyle(cs_data);
            cell.setCellValue(gm_Channel.getCh_name()!=null?gm_Channel.getCh_name():"");
            cell = row.createCell((short)2);
            cell.setCellStyle(cs_data);
            cell.setCellValue((gm_Channel.getScene_id()!=null&&gm_Channel.getScene_id().getScene_id()!=null&&gm_Channel.getScene_id().getScene_no()!=null)?gm_Channel.getScene_id().getScene_no():"");
            cell = row.createCell((short)3);
            cell.setCellStyle(cs_data);
            cell.setCellValue((gm_Channel.getDev_collectId()!=null&&gm_Channel.getDev_collectId().getDev_id()!=null&&gm_Channel.getDev_collectId().getDev_no()!=null)?gm_Channel.getDev_collectId().getDev_no():"");
            cell = row.createCell((short)4);
            cell.setCellStyle(cs_data);
            cell.setCellValue(gm_Channel.getCh_chlNo()!=null?gm_Channel.getCh_chlNo()+"":"");
            cell = row.createCell((short)5);
            cell.setCellStyle(cs_data);
            cell.setCellValue((gm_Channel.getDev_sensorId()!=null&&gm_Channel.getDev_sensorId().getDev_id()!=null&&gm_Channel.getDev_sensorId().getDev_no()!=null)?gm_Channel.getDev_sensorId().getDev_no():"");
            cell = row.createCell((short)6);
            cell.setCellStyle(cs_data);
            cell.setCellValue((gm_Channel.getChtype_id()!=null&&gm_Channel.getChtype_id().getChtype_id()!=null&&gm_Channel.getChtype_id().getChtype_no()!=null)?gm_Channel.getChtype_id().getChtype_no():"");
            cell = row.createCell((short)7);
            cell.setCellStyle(cs_data); 
            String ch_procesStyle_name = Ch_procesStyle.findNameById(gm_Channel.getCh_procesStyle());
            cell.setCellValue(ch_procesStyle_name!=null?ch_procesStyle_name:"");            
            cell = row.createCell((short)8);
            cell.setCellStyle(cs_data);
            cell.setCellValue(gm_Channel.getCh_memoryPeriod()!=null?gm_Channel.getCh_memoryPeriod()+"":"");
            cell = row.createCell((short)9);
            cell.setCellStyle(cs_data);
            String ch_offerSer_name = Ch_offerSer.findNameById(gm_Channel.getCh_offerSer());
            cell.setCellValue(ch_offerSer_name!=null?ch_offerSer_name:"");
//            cell = row.createCell((short)10);
//            cell.setCellStyle(cs_data);
//            String ch_state_name = Ch_state.findNameById(gm_Channel.getCh_state());
//            cell.setCellValue(ch_state_name!=null?ch_state_name:"");
            cell = row.createCell((short)10);
            cell.setCellStyle(cs_data);
            cell.setCellValue(gm_Channel.getCh_seat_no()!=null?gm_Channel.getCh_seat_no():"");
            cell = row.createCell((short)11);
            cell.setCellStyle(cs_data);
            cell.setCellValue(gm_Channel.getCh_depth()!=null?gm_Channel.getCh_depth():"");
        }
        
	}
	

	/**
	 * 导出控制设备信息
	 * @param wb
	 * @return
	 * @author Wang_Yuliang
	 */
	public void daochuGmDevCtrl(HSSFWorkbook wb){
		HSSFSheet sheet = wb.createSheet("控制设备信息表");
	    sheet.setDefaultColumnWidth((short)20);
	    
	    HSSFCellStyle cs_head = wb.createCellStyle();
	    cs_head.setWrapText(true);     
	    cs_head.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	    cs_head.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFRow row = sheet.createRow((short) 0);
        HSSFCell cell;
        cell = row.createCell((short)0);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("控制设备编号\r\n(必填)"));
        cell = row.createCell((short)1);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("设备名称\r\n(必填)"));
//        cell = row.createCell((short)2);
//        cell.setCellStyle(cs_head);
//        cell.setCellValue(new HSSFRichTextString("设备序列号\r\n(必填)"));
        cell = row.createCell((short)2);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("所属场景编号\r\n(下拉选择)"));
        cell = row.createCell((short)3);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("所属设备编号\r\n(下拉选择)"));
        cell = row.createCell((short)4);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("类型编号\r\n(下拉选择)"));
        cell = row.createCell((short)5);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("类型按钮数量\r\n(整数)"));        
        cell = row.createCell((short)6);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("购买时间\r\n(日期)"));        
//        cell = row.createCell((short)8);
//        cell.setCellStyle(cs_head);
//        cell.setCellValue(new HSSFRichTextString("设备使用状态\r\n(下拉选择)"));        
        cell = row.createCell((short)7);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("是否对外提供服务\r\n(下拉选择)"));        
        cell = row.createCell((short)8);
        cell.setCellStyle(cs_head);
        cell.setCellValue("设备说明");
        
	    HSSFCellStyle cs_data = wb.createCellStyle();
	    cs_data.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        List<Gm_DevCtrl> gm_DevCtrl_list = this.devCtrlDao.findAll();
        int i = 1;
        for(Gm_DevCtrl gm_DevCtrl : gm_DevCtrl_list){
            row = sheet.createRow((short) i);i++;
            cell = row.createCell((short)0);//控制设备编号
            cell.setCellStyle(cs_data);            
            cell.setCellValue(gm_DevCtrl.getDect_no()!=null?gm_DevCtrl.getDect_no():"");
            cell = row.createCell((short)1);//设备名称
            cell.setCellStyle(cs_data);
            cell.setCellValue(gm_DevCtrl.getDect_name()!=null?gm_DevCtrl.getDect_name():"");
            cell = row.createCell((short)2);//设备序列号
            cell.setCellStyle(cs_data);
            cell.setCellValue(gm_DevCtrl.getDect_serial()!=null?gm_DevCtrl.getDect_serial():"");
            cell = row.createCell((short)3);//所属场景编号
            cell.setCellStyle(cs_data);
            cell.setCellValue((gm_DevCtrl.getScene_id()!=null&&gm_DevCtrl.getScene_id().getScene_id()!=null&&gm_DevCtrl.getScene_id().getScene_no()!=null)?gm_DevCtrl.getScene_id().getScene_no():"");
            cell = row.createCell((short)4);//所属设备编号
            cell.setCellStyle(cs_data);
            cell.setCellValue((gm_DevCtrl.getDev_id()!=null&&gm_DevCtrl.getDev_id().getDev_id()!=null&&gm_DevCtrl.getDev_id().getDev_no()!=null)?gm_DevCtrl.getDev_id().getDev_no():"");
            cell = row.createCell((short)5);//类型编号
            cell.setCellStyle(cs_data);
            cell.setCellValue((gm_DevCtrl.getDecttype_id()!=null&&gm_DevCtrl.getDecttype_id().getDecttype_id()!=null&&gm_DevCtrl.getDecttype_id().getDecttype_no()!=null)?gm_DevCtrl.getDecttype_id().getDecttype_no():"");
            cell = row.createCell((short)6);//类型按钮数量
            cell.setCellStyle(cs_data);
            cell.setCellValue(gm_DevCtrl.getDecttype_btnNum()!=null?gm_DevCtrl.getDecttype_btnNum()+"":"");            
            cell = row.createCell((short)7);//购买时间
            cell.setCellStyle(cs_data); 
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            cell.setCellValue(gm_DevCtrl.getDect_buyDate()!=null?df.format(gm_DevCtrl.getDect_buyDate()):"");                        
            cell = row.createCell((short)8);//设备使用状态
            cell.setCellStyle(cs_data);
            String gm_DevCtrl_Dect_state_name = Gm_DevCtrl_Dect_state.findNameById(gm_DevCtrl.getDect_state());
            cell.setCellValue(gm_DevCtrl_Dect_state_name!=null?gm_DevCtrl_Dect_state_name:"");            
            cell = row.createCell((short)9);//是否对外提供服务
            cell.setCellStyle(cs_data);
            String gm_DevCtrl_Ch_offerSer_name = Gm_DevCtrl_Ch_offerSer.findNameById(gm_DevCtrl.getCh_offerSer());
            cell.setCellValue(gm_DevCtrl_Ch_offerSer_name!=null?gm_DevCtrl_Ch_offerSer_name:"");            
            cell = row.createCell((short)10);//设备说明
            cell.setCellStyle(cs_data);
            cell.setCellValue(gm_DevCtrl.getDect_decsription()!=null?gm_DevCtrl.getDect_decsription():"");
        }
        
	}


	/**
	 * 导出控制设备信息
	 * @param wb
	 * @param scene_id_arr String类型 以逗号隔开如 -1,id1,id2,..idx， 里面有默认的-1避免报错
	 * @return List<Gm_DevCtrl> 为到处子表信息提供参数
	 * @author Wang_Yuliang
	 */
	public List<Gm_DevCtrl> daochuGmDevCtrl(HSSFWorkbook wb, String scene_id_arr){
		HSSFSheet sheet = wb.createSheet("控制设备信息表");
	    sheet.setDefaultColumnWidth((short)20);
	    
	    HSSFCellStyle cs_head = wb.createCellStyle();
	    cs_head.setWrapText(true);     
	    cs_head.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	    cs_head.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFRow row = sheet.createRow((short) 0);
        HSSFCell cell;
        cell = row.createCell((short)0);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("控制设备编号\r\n(必填)"));
        cell = row.createCell((short)1);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("设备名称\r\n(必填)"));
//        cell = row.createCell((short)2);
//        cell.setCellStyle(cs_head);
//        cell.setCellValue(new HSSFRichTextString("设备序列号\r\n(必填)"));
        cell = row.createCell((short)2);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("所属场景编号\r\n(下拉选择)"));
        cell = row.createCell((short)3);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("所属设备编号\r\n(下拉选择)"));
        cell = row.createCell((short)4);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("类型编号\r\n(下拉选择)"));
        cell = row.createCell((short)5);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("类型按钮数量\r\n(整数)"));        
        cell = row.createCell((short)6);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("购买时间\r\n(日期)"));        
//        cell = row.createCell((short)8);
//        cell.setCellStyle(cs_head);
//        cell.setCellValue(new HSSFRichTextString("设备使用状态\r\n(下拉选择)"));        
        cell = row.createCell((short)7);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("是否对外提供服务\r\n(下拉选择)"));        
        cell = row.createCell((short)8);
        cell.setCellStyle(cs_head);
        cell.setCellValue("设备说明");
        
	    HSSFCellStyle cs_data = wb.createCellStyle();
	    cs_data.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	    String[] sceneIds = scene_id_arr.split(",");
        List<Gm_DevCtrl> gm_DevCtrl_list = this.devCtrlDao.search(new Search().addFilterIn("scene_id.scene_id", sceneIds));
        int i = 1;
        for(Gm_DevCtrl gm_DevCtrl : gm_DevCtrl_list){
            row = sheet.createRow((short) i);i++;
            cell = row.createCell((short)0);//控制设备编号
            cell.setCellStyle(cs_data);            
            cell.setCellValue(gm_DevCtrl.getDect_no()!=null?gm_DevCtrl.getDect_no():"");
            cell = row.createCell((short)1);//设备名称
            cell.setCellStyle(cs_data);
            cell.setCellValue(gm_DevCtrl.getDect_name()!=null?gm_DevCtrl.getDect_name():"");
//            cell = row.createCell((short)2);//设备序列号
//            cell.setCellStyle(cs_data);
//            cell.setCellValue(gm_DevCtrl.getDect_serial()!=null?gm_DevCtrl.getDect_serial():"");
            cell = row.createCell((short)2);//所属场景编号
            cell.setCellStyle(cs_data);
            cell.setCellValue((gm_DevCtrl.getScene_id()!=null&&gm_DevCtrl.getScene_id().getScene_id()!=null&&gm_DevCtrl.getScene_id().getScene_no()!=null)?gm_DevCtrl.getScene_id().getScene_no():"");
            cell = row.createCell((short)3);//所属设备编号
            cell.setCellStyle(cs_data);
            cell.setCellValue((gm_DevCtrl.getDev_id()!=null&&gm_DevCtrl.getDev_id().getDev_id()!=null&&gm_DevCtrl.getDev_id().getDev_no()!=null)?gm_DevCtrl.getDev_id().getDev_no():"");
            cell = row.createCell((short)4);//类型编号
            cell.setCellStyle(cs_data);
            cell.setCellValue((gm_DevCtrl.getDecttype_id()!=null&&gm_DevCtrl.getDecttype_id().getDecttype_id()!=null&&gm_DevCtrl.getDecttype_id().getDecttype_no()!=null)?gm_DevCtrl.getDecttype_id().getDecttype_no():"");
            cell = row.createCell((short)5);//类型按钮数量
            cell.setCellStyle(cs_data);
            cell.setCellValue(gm_DevCtrl.getDecttype_btnNum()!=null?gm_DevCtrl.getDecttype_btnNum()+"":"");            
            cell = row.createCell((short)6);//购买时间
            cell.setCellStyle(cs_data); 
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            cell.setCellValue(gm_DevCtrl.getDect_buyDate()!=null?df.format(gm_DevCtrl.getDect_buyDate()):"");                        
//            cell = row.createCell((short)8);//设备使用状态
//            cell.setCellStyle(cs_data);
//            String gm_DevCtrl_Dect_state_name = Gm_DevCtrl_Dect_state.findNameById(gm_DevCtrl.getDect_state());
//            cell.setCellValue(gm_DevCtrl_Dect_state_name!=null?gm_DevCtrl_Dect_state_name:"");            
            cell = row.createCell((short)7);//是否对外提供服务
            cell.setCellStyle(cs_data);
            String gm_DevCtrl_Ch_offerSer_name = Gm_DevCtrl_Ch_offerSer.findNameById(gm_DevCtrl.getCh_offerSer());
            cell.setCellValue(gm_DevCtrl_Ch_offerSer_name!=null?gm_DevCtrl_Ch_offerSer_name:"");            
            cell = row.createCell((short)8);//设备说明
            cell.setCellStyle(cs_data);
            cell.setCellValue(gm_DevCtrl.getDect_decsription()!=null?gm_DevCtrl.getDect_decsription():"");
        }
        
        return gm_DevCtrl_list;
	}
	

	/**
	 * 导出控制设备按钮配置
	 * @param wb
	 * @return
	 * @author Wang_Yuliang
	 */
	public void daochuOpDevCtrlBtn(HSSFWorkbook wb){
		HSSFSheet sheet = wb.createSheet("控制设备按钮配置表");
	    sheet.setDefaultColumnWidth((short)20);
	    
	    HSSFCellStyle cs_head = wb.createCellStyle();
	    cs_head.setWrapText(true);     
	    cs_head.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	    cs_head.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFRow row = sheet.createRow((short) 0);
        HSSFCell cell;
        cell = row.createCell((short)0);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("控制设备编号\r\n(下拉选择)"));
        cell = row.createCell((short)1);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("按钮名称\r\n(不能为空)"));
        cell = row.createCell((short)2);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("操作类型\r\n(下拉选择)"));
        cell = row.createCell((short)3);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("所属设备编号\r\n(下拉选择)"));
        cell = row.createCell((short)4);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("控制通道\r\n(整数)"));
        cell = row.createCell((short)5);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("控制类型\r\n(下拉选择)"));
        cell = row.createCell((short)6);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("操作延时\r\n(整数：秒)"));
   
	    HSSFCellStyle cs_data = wb.createCellStyle();
	    cs_data.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        List<Op_DevCtrlBtn> op_DevCtrlBtn_list = this.devCtrlBtnDao.findAll();
        int i = 1;
        List<Gm_DevCtrl> gm_DevCtrl_list = this.devCtrlDao.findAll();
        Map gm_DevCtrl_map = new HashMap();
        for(Gm_DevCtrl gm_DevCtrl : gm_DevCtrl_list){
        	gm_DevCtrl_map.put(gm_DevCtrl.getDect_id(), gm_DevCtrl);
        }        
        for(Op_DevCtrlBtn op_DevCtrlBtn : op_DevCtrlBtn_list){
            row = sheet.createRow((short) i);i++;
            cell = row.createCell((short)0);
            cell.setCellStyle(cs_data);      
            Gm_DevCtrl gm_DevCtrl = op_DevCtrlBtn.getDect_id()!=null?(Gm_DevCtrl)gm_DevCtrl_map.get(op_DevCtrlBtn.getDect_id()):null;
            cell.setCellValue((gm_DevCtrl!=null&&gm_DevCtrl.getDect_no()!=null)?gm_DevCtrl.getDect_no():"");
            cell = row.createCell((short)1);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_DevCtrlBtn.getDectbtn_name()!=null?op_DevCtrlBtn.getDectbtn_name():"");
            cell = row.createCell((short)2);
            cell.setCellStyle(cs_data);
            String op_DevCtrlBtn_Deco_type_name = Op_DevCtrlBtn_Deco_type.findNameById(op_DevCtrlBtn.getDeco_type());
            cell.setCellValue(op_DevCtrlBtn_Deco_type_name!=null?op_DevCtrlBtn_Deco_type_name:"");
            cell = row.createCell((short)3);
            cell.setCellStyle(cs_data);
            cell.setCellValue((op_DevCtrlBtn.getDev_id()!=null&&op_DevCtrlBtn.getDev_id().getDev_id()!=null&&op_DevCtrlBtn.getDev_id().getDev_no()!=null)?op_DevCtrlBtn.getDev_id().getDev_no():"");
            cell = row.createCell((short)4);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_DevCtrlBtn.getDect_chlNo()!=null?op_DevCtrlBtn.getDect_chlNo()+"":"");
            cell = row.createCell((short)5);
            cell.setCellStyle(cs_data);
            String dect_ctlType_name = Dect_ctlType.findNameById(op_DevCtrlBtn.getDect_ctlType());
            cell.setCellValue(dect_ctlType_name!=null?dect_ctlType_name:"");
            cell = row.createCell((short)6);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_DevCtrlBtn.getDect_ctlDelay()!=null?op_DevCtrlBtn.getDect_ctlDelay()+"":"");
        }
        
	}	
	
	/**
	 * 导出控制设备按钮配置
	 * @param wb
	 * @param gm_DevCtrl_list
	 * @return
	 * @author Wang_Yuliang
	 */
	public void daochuOpDevCtrlBtn(HSSFWorkbook wb, List<Gm_DevCtrl> gm_DevCtrl_list){
		HSSFSheet sheet = wb.createSheet("控制设备按钮配置表");
	    sheet.setDefaultColumnWidth((short)20);
	    
	    HSSFCellStyle cs_head = wb.createCellStyle();
	    cs_head.setWrapText(true);     
	    cs_head.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	    cs_head.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFRow row = sheet.createRow((short) 0);
        HSSFCell cell;
        cell = row.createCell((short)0);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("控制设备编号\r\n(下拉选择)"));
        cell = row.createCell((short)1);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("按钮名称\r\n(不能为空)"));
        cell = row.createCell((short)2);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("操作类型\r\n(下拉选择)"));
        cell = row.createCell((short)3);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("所属设备编号\r\n(下拉选择)"));
        cell = row.createCell((short)4);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("控制通道\r\n(整数)"));
        cell = row.createCell((short)5);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("控制类型\r\n(下拉选择)"));
        cell = row.createCell((short)6);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("操作延时\r\n(整数：秒)"));
        
	    HSSFCellStyle cs_data = wb.createCellStyle();
	    cs_data.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        List<Op_DevCtrlBtn> op_DevCtrlBtn_list = new ArrayList<Op_DevCtrlBtn>();
        Map gm_DevCtrl_map = new HashMap();
        for(Gm_DevCtrl gm_DevCtrl : gm_DevCtrl_list){
        	gm_DevCtrl_map.put(gm_DevCtrl.getDect_id(), gm_DevCtrl);
        }   
        if(gm_DevCtrl_map.keySet().size()>0)
        	op_DevCtrlBtn_list = this.devCtrlBtnDao.search(new Search().addFilterIn("dect_id", gm_DevCtrl_map.keySet()));
        int i = 1;     
        for(Op_DevCtrlBtn op_DevCtrlBtn : op_DevCtrlBtn_list){
            row = sheet.createRow((short) i);i++;
            cell = row.createCell((short)0);
            cell.setCellStyle(cs_data);      
            Gm_DevCtrl gm_DevCtrl = op_DevCtrlBtn.getDect_id()!=null?(Gm_DevCtrl)gm_DevCtrl_map.get(op_DevCtrlBtn.getDect_id()):null;
            cell.setCellValue((gm_DevCtrl!=null&&gm_DevCtrl.getDect_no()!=null)?gm_DevCtrl.getDect_no():"");
            cell = row.createCell((short)1);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_DevCtrlBtn.getDectbtn_name()!=null?op_DevCtrlBtn.getDectbtn_name():"");
            cell = row.createCell((short)2);
            cell.setCellStyle(cs_data);
            String op_DevCtrlBtn_Deco_type_name = Op_DevCtrlBtn_Deco_type.findNameById(op_DevCtrlBtn.getDeco_type());
            cell.setCellValue(op_DevCtrlBtn_Deco_type_name!=null?op_DevCtrlBtn_Deco_type_name:"");
            cell = row.createCell((short)3);
            cell.setCellStyle(cs_data);
            cell.setCellValue((op_DevCtrlBtn.getDev_id()!=null&&op_DevCtrlBtn.getDev_id().getDev_id()!=null&&op_DevCtrlBtn.getDev_id().getDev_no()!=null)?op_DevCtrlBtn.getDev_id().getDev_no():"");
            cell = row.createCell((short)4);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_DevCtrlBtn.getDect_chlNo()!=null?op_DevCtrlBtn.getDect_chlNo()+"":"");
            cell = row.createCell((short)5);
            cell.setCellStyle(cs_data);
            String dect_ctlType_name = Dect_ctlType.findNameById(op_DevCtrlBtn.getDect_ctlType());
            cell.setCellValue(dect_ctlType_name!=null?dect_ctlType_name:"");
            cell = row.createCell((short)6);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_DevCtrlBtn.getDect_ctlDelay()!=null?op_DevCtrlBtn.getDect_ctlDelay()+"":"");
        }
        
	}
	
	
	/**
	 * 导出控制设备状态配置
	 * @param wb
	 * @return
	 * @author Wang_Yuliang
	 * @param gm_DevCtrl_list 
	 */
	public void daochuOpDevCtrlSts(HSSFWorkbook wb, List<Gm_DevCtrl> gm_DevCtrl_list){
		HSSFSheet sheet = wb.createSheet("控制设备状态配置表");
	    sheet.setDefaultColumnWidth((short)20);
	    
	    HSSFCellStyle cs_head = wb.createCellStyle();
	    cs_head.setWrapText(true);     
	    cs_head.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	    cs_head.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFRow row = sheet.createRow((short) 0);
        HSSFCell cell;
        cell = row.createCell((short)0);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("控制设备编号\r\n(下拉选择)"));
        cell = row.createCell((short)1);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("状态名称\r\n(不能为空)"));
        cell = row.createCell((short)2);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("状态类型\r\n(下拉选择)"));
        cell = row.createCell((short)3);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("状态通道编号"));
        cell = row.createCell((short)4);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("范围上限"));
        cell = row.createCell((short)5);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("范围下限"));
        
	    HSSFCellStyle cs_data = wb.createCellStyle();
	    cs_data.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        List<Op_DevCtrlSts> op_DevCtrlSts_list = this.op_DevCtrlStsDao.findByDevCtrl(gm_DevCtrl_list);
        int i = 1;     
        for(Op_DevCtrlSts op_DevCtrlSts : op_DevCtrlSts_list){
            row = sheet.createRow((short) i);i++;
            cell = row.createCell((short)0);
            cell.setCellStyle(cs_data);
            cell.setCellValue((op_DevCtrlSts.getDect_id()!=null&&op_DevCtrlSts.getDect_id().getDect_id()!=null&&op_DevCtrlSts.getDect_id().getDect_no()!=null)?op_DevCtrlSts.getDect_id().getDect_no():"");
            cell = row.createCell((short)1);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_DevCtrlSts.getDectSts_name()!=null?op_DevCtrlSts.getDectSts_name():"");
            cell = row.createCell((short)2);
            cell.setCellStyle(cs_data);
            String op_DevCtrlSts_Dect_state_name = Op_DevCtrlSts_Dect_state.findNameById(op_DevCtrlSts.getDect_state());
            cell.setCellValue(op_DevCtrlSts_Dect_state_name!=null?op_DevCtrlSts_Dect_state_name:"");
            cell = row.createCell((short)3);
            cell.setCellStyle(cs_data);
            cell.setCellValue((op_DevCtrlSts.getCh_id()!=null&&op_DevCtrlSts.getCh_id().getCh_id()!=null&&op_DevCtrlSts.getCh_id().getCh_no()!=null)?op_DevCtrlSts.getCh_id().getCh_no():"");
            cell = row.createCell((short)4);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_DevCtrlSts.getCh_max()!=null?op_DevCtrlSts.getCh_max()+"":"");
            cell = row.createCell((short)5);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_DevCtrlSts.getCh_min()!=null?op_DevCtrlSts.getCh_min()+"":"");
        }
        
	}
	
	/**
	 * 导出供应商信息
	 * @param wb
	 * @return
	 * @author Wang_Yuliang
	 */
	public void daochuOpSupplier(HSSFWorkbook wb){
		HSSFSheet sheet = wb.createSheet("供应商信息表");
	    sheet.setDefaultColumnWidth((short)20);
	    
	    HSSFCellStyle cs_head = wb.createCellStyle();
	    cs_head.setWrapText(true);     
	    cs_head.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	    cs_head.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFRow row = sheet.createRow((short) 0);
        HSSFCell cell;
        cell = row.createCell((short)0);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("供应商编号\r\n(唯一,必填)"));
        cell = row.createCell((short)1);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("供应商名称\r\n(不能为空,必填)"));
        cell = row.createCell((short)2);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("供应商类型\r\n(下拉选择)"));
        cell = row.createCell((short)3);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("供应商国家"));
        cell = row.createCell((short)4);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("供应商地址"));
        cell = row.createCell((short)5);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("供应商邮编\r\n(整数)"));
        cell = row.createCell((short)6);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("供应商固定电话"));
        cell = row.createCell((short)7);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("供应商传真"));
        cell = row.createCell((short)8);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("联系人姓名"));
        cell = row.createCell((short)9);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("联系人电话"));
        cell = row.createCell((short)10);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("联系人手机"));
        cell = row.createCell((short)11);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("联系人邮箱"));
        
	    HSSFCellStyle cs_data = wb.createCellStyle();
	    cs_data.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        List<Op_Supplier> op_Supplier_list = this.supplierDao.findAll();
        int i = 1;     
        for(Op_Supplier op_Supplier : op_Supplier_list){
            row = sheet.createRow((short) i);i++;
            cell = row.createCell((short)0);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_Supplier.getSup_no()!=null?op_Supplier.getSup_no():"");
            cell = row.createCell((short)1);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_Supplier.getSup_name()!=null?op_Supplier.getSup_name():"");
            cell = row.createCell((short)2);
            cell.setCellStyle(cs_data);
            String sup_type_name = Sup_type.findNameById(op_Supplier.getSup_type());
            cell.setCellValue(sup_type_name!=null?sup_type_name:"");
            cell = row.createCell((short)3);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_Supplier.getSup_country()!=null?op_Supplier.getSup_country():"");
            cell = row.createCell((short)4);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_Supplier.getSup_address()!=null?op_Supplier.getSup_address():"");
            cell = row.createCell((short)5);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_Supplier.getSup_zip()!=null?op_Supplier.getSup_zip()+"":"");
            cell = row.createCell((short)6);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_Supplier.getSup_phone()!=null?op_Supplier.getSup_phone():"");
            cell = row.createCell((short)7);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_Supplier.getSup_fax()!=null?op_Supplier.getSup_fax():"");
            cell = row.createCell((short)8);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_Supplier.getSup_contactName()!=null?op_Supplier.getSup_contactName():"");
            cell = row.createCell((short)9);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_Supplier.getSup_contactPhone()!=null?op_Supplier.getSup_contactPhone():"");
            cell = row.createCell((short)10);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_Supplier.getSup_contactMobile()!=null?op_Supplier.getSup_contactMobile():"");
            cell = row.createCell((short)11);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_Supplier.getSup_contactEmail()!=null?op_Supplier.getSup_contactEmail():"");
        }
        
	}
	
	/**
	 * 导出采集通道应用类型信息
	 * @param wb
	 * @return
	 * @author Wang_Yuliang
	 */
	public void daochuOpChannelType(HSSFWorkbook wb){
		HSSFSheet sheet = wb.createSheet("采集通道应用类型信息表");
	    sheet.setDefaultColumnWidth((short)20);
	    
	    HSSFCellStyle cs_head = wb.createCellStyle();
	    cs_head.setWrapText(true);     
	    cs_head.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	    cs_head.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFRow row = sheet.createRow((short) 0);
        HSSFCell cell;
        cell = row.createCell((short)0);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("类型编号\r\n(唯一,必填)"));
        cell = row.createCell((short)1);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("类型名称\r\n(不能为空)"));
        cell = row.createCell((short)2);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("设备型号"));
        cell = row.createCell((short)3);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("小数位数\r\n(整数)"));
        cell = row.createCell((short)4);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("原数据单位"));
        cell = row.createCell((short)5);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("校正后的单位"));
        cell = row.createCell((short)6);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("量程上限\r\n(数值)"));
        cell = row.createCell((short)7);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("量程下限\r\n(数值)"));
        cell = row.createCell((short)8);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("变化率上限\r\n(数值>0)"));
        cell = row.createCell((short)9);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("校准周期\r\n(整数)"));
        cell = row.createCell((short)10);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("校正公式"));
        
	    HSSFCellStyle cs_data = wb.createCellStyle();
	    cs_data.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        List<Op_ChannelType> op_ChannelType_list = this.channelTypeDao.findAll();
        int i = 1;     
        for(Op_ChannelType op_ChannelType : op_ChannelType_list){
            row = sheet.createRow((short) i);i++;
            cell = row.createCell((short)0);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_ChannelType.getChtype_no()!=null?op_ChannelType.getChtype_no():"");
            cell = row.createCell((short)1);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_ChannelType.getChtype_displayName()!=null?op_ChannelType.getChtype_displayName():"");
            cell = row.createCell((short)2);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_ChannelType.getDev_model()!=null?op_ChannelType.getDev_model():"");
            cell = row.createCell((short)3);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_ChannelType.getCh_dectDig()!=null?op_ChannelType.getCh_dectDig()+"":"");
            cell = row.createCell((short)4);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_ChannelType.getCh_unit()!=null?op_ChannelType.getCh_unit():"");
            cell = row.createCell((short)5);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_ChannelType.getCh_corrUnit()!=null?op_ChannelType.getCh_corrUnit():"");
            cell = row.createCell((short)6);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_ChannelType.getCh_max()!=null?op_ChannelType.getCh_max()+"":"");
            cell = row.createCell((short)7);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_ChannelType.getCh_min()!=null?op_ChannelType.getCh_min()+"":"");
            cell = row.createCell((short)8);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_ChannelType.getCh_crateMax()!=null?op_ChannelType.getCh_crateMax()+"":"");
            cell = row.createCell((short)9);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_ChannelType.getCh_corrCyc()!=null?op_ChannelType.getCh_corrCyc()+"":"");
            cell = row.createCell((short)10);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_ChannelType.getCh_corrFormula()!=null?op_ChannelType.getCh_corrFormula():"");
        }
        
	}
		
	/**
	 * 导出控制设备类型信息
	 * @param wb
	 * @return
	 * @author Wang_Yuliang
	 */
	public void daochuOp_DevCtrlType(HSSFWorkbook wb){
		HSSFSheet sheet = wb.createSheet("控制设备类型信息表");
	    sheet.setDefaultColumnWidth((short)20);
	    
	    HSSFCellStyle cs_head = wb.createCellStyle();
	    cs_head.setWrapText(true);     
	    cs_head.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	    cs_head.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
        HSSFRow row = sheet.createRow((short) 0);
        HSSFCell cell;
        cell = row.createCell((short)0);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("类型编号\r\n(唯一,必填)"));
        cell = row.createCell((short)1);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("类型名称\r\n(不能为空)"));
        cell = row.createCell((short)2);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("类型型号"));
        cell = row.createCell((short)3);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("类型功率\r\n(数值)"));
        cell = row.createCell((short)4);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("类型供电电压\r\n(数值)"));
        cell = row.createCell((short)5);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("类型说明"));
        cell = row.createCell((short)6);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("类型按钮数量\r\n(整数)"));
        cell = row.createCell((short)7);
        cell.setCellStyle(cs_head);
        cell.setCellValue(new HSSFRichTextString("通道状态反馈数量\r\n(整数)"));
        
	    HSSFCellStyle cs_data = wb.createCellStyle();
	    cs_data.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        List<Op_DevCtrlType> op_DevCtrlType_list = this.devCtrlTypeDao.findAll();
        int i = 1;     
        for(Op_DevCtrlType op_DevCtrlType : op_DevCtrlType_list){
            row = sheet.createRow((short) i);i++;
            cell = row.createCell((short)0);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_DevCtrlType.getDecttype_no()!=null?op_DevCtrlType.getDecttype_no():"");
            cell = row.createCell((short)1);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_DevCtrlType.getDecttype_displayName()!=null?op_DevCtrlType.getDecttype_displayName():"");
            cell = row.createCell((short)2);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_DevCtrlType.getDecttype_typeNo()!=null?op_DevCtrlType.getDecttype_typeNo():"");
            cell = row.createCell((short)3);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_DevCtrlType.getDecttype_power()!=null?op_DevCtrlType.getDecttype_power()+"":"");
            cell = row.createCell((short)4);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_DevCtrlType.getDecttype_voltage()!=null?op_DevCtrlType.getDecttype_voltage()+"":"");
            cell = row.createCell((short)5);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_DevCtrlType.getDecttype_decription()!=null?op_DevCtrlType.getDecttype_decription():"");
            cell = row.createCell((short)6);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_DevCtrlType.getDecttype_btnNum()!=null?op_DevCtrlType.getDecttype_btnNum()+"":"");
            cell = row.createCell((short)7);
            cell.setCellStyle(cs_data);
            cell.setCellValue(op_DevCtrlType.getDecttype_chlStsNum()!=null?op_DevCtrlType.getDecttype_chlStsNum()+"":"");
        }
        
	}
	
//	/**
//	 * 项目导出
//	 * @return
//	 * @author Wang_Yuliang
//	 * 不用了 Wang_Yuliang
//	 */
//	public String daochuProjectExcel(OutputStream outputStream){
//		String result = "操作失败!未知错误";		
//		try {
//			//WritableWorkbook wwb = jxl.Workbook.createWorkbook(outputStream);// 建立Excel文件
//			
//			WritableWorkbook wwb = jxl.Workbook.createWorkbook(outputStream);// 建立Excel文件
//			WritableSheet sheet = wwb.createSheet("通道历史数据", 0);// Excel文件名
//			WritableFont wf_head = new WritableFont(WritableFont.ARIAL,16,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);// 设置字体
//			WritableCellFormat wcf_head = new WritableCellFormat(wf_head);
//			wcf_head.setAlignment(Alignment.CENTRE);// 表头文本居中
//			wcf_head.setVerticalAlignment(VerticalAlignment.CENTRE);
//			
//			WritableFont wf_body = new WritableFont(WritableFont.ARIAL,12,WritableFont.NO_BOLD,false,UnderlineStyle.NO_UNDERLINE,Colour.BLACK);// 设置字体
//			WritableCellFormat wcf_body = new WritableCellFormat(wf_body);
//			wcf_body.setAlignment(Alignment.CENTRE);
//			wcf_body.setVerticalAlignment(VerticalAlignment.CENTRE);
//			wcf_body.setWrap(true);
//			
//			sheet.setRowView(0, 700);
//			sheet.setColumnView(0, 15);
//			sheet.setColumnView(1, 35);
//			sheet.setColumnView(2, 20);
//			sheet.setColumnView(3, 20);
//			String title[] = {"设备通道号","上报时间","原数据值","处理后数据值"};
//			for (int i = 0; i < title.length; i++) {
//				Label label = new Label(i,0,title[i],wcf_head);
//				sheet.addCell(label);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return result;
//	}
//	
//	/**
//	 * 导出场景信息
//	 * @param wb
//	 * @return
//	 * @author Wang_Yuliang
//   * 不用了 Wang_Yuliang
//	 */
//	public WritableWorkbook daochuOpScene(WritableWorkbook wwb) throws WriteException{
//		WritableSheet sheet = wwb.createSheet("场景信息表", 0);
//		sheet.setColumnView(0,20);
//		sheet.setColumnView(1,20);
//		sheet.setColumnView(2,20);
//		sheet.setColumnView(3,20);
//		sheet.setColumnView(4,20);
//		sheet.setColumnView(5,20);
//		sheet.setColumnView(6,20);
//		sheet.setColumnView(7,20);
//		sheet.setColumnView(8,20);
//		sheet.setColumnView(9,20);
//		sheet.setColumnView(10,20);
//		sheet.setColumnView(11,20);
//		
//		WritableFont wf_head = new WritableFont(WritableFont.ARIAL, 16 ,WritableFont.NO_BOLD);// 设置字体
//		WritableCellFormat wcf_head = new WritableCellFormat(wf_head);
//		wcf_head.setAlignment(Alignment.CENTRE);
//		wcf_head.setVerticalAlignment(VerticalAlignment.CENTRE);   
//		sheet.addCell(new jxl.write.Label(0,0,"场景编号\r\n(唯一,必填)",wcf_head));
//		sheet.addCell(new jxl.write.Label(0,1,"场景名称\r\n(必填)",wcf_head));
//
//        /**
//	    HSSFCellStyle cs_data = wb.createCellStyle();
//	    cs_data.setAlignment(HSSFCellStyle.ALIGN_LEFT);
//        List<Op_Scene> op_Scene_list = this.sceneDao.findAll();
//        int i = 1;
//        Map op_Scene_map = new HashMap();        	
//        for(Op_Scene op_Scene : op_Scene_list){
//        	op_Scene_map.put(op_Scene.getScene_id(), op_Scene);
//        }
//        */
//        /**
//        for(Op_Scene op_Scene : op_Scene_list){
//            row = sheet.createRow((short) i);i++;
//            cell = row.createCell((short)0);
//            cell.setCellStyle(cs_data);            
//            cell.setCellValue(op_Scene.getScene_no()!=null?op_Scene.getScene_no():"");
//            cell = row.createCell((short)1);
//            cell.setCellStyle(cs_data);
//            cell.setCellValue(op_Scene.getScene_name()!=null?op_Scene.getScene_name():"");
//            cell = row.createCell((short)2);
//            cell.setCellStyle(cs_data);
//            Op_Scene op_Scene_p = op_Scene.getScene_pid()!=null?(Op_Scene)op_Scene_map.get(op_Scene.getScene_pid()):null;
//            cell.setCellValue((op_Scene_p!=null&&op_Scene_p.getScene_no()!=null)?op_Scene_p.getScene_no():"");
//            cell = row.createCell((short)3);
//            cell.setCellStyle(cs_data);
//            String scene_type_name = Scene_type.findNameById(op_Scene.getScene_type());
//            cell.setCellValue(scene_type_name!=null?scene_type_name:"");
//            cell = row.createCell((short)4);
//            cell.setCellStyle(cs_data);
//            String scene_gtype_name = Scene_gtype.findNameById(op_Scene.getScene_gtype());
//            cell.setCellValue(scene_gtype_name!=null?scene_gtype_name:"");
//            cell = row.createCell((short)5);
//            cell.setCellStyle(cs_data);
//            String scene_ctype_name = Scene_ctype.findNameById(op_Scene.getScene_ctype());
//            cell.setCellValue(scene_ctype_name!=null?scene_ctype_name:"");
//            cell = row.createCell((short)6);
//            cell.setCellStyle(cs_data);
//            cell.setCellValue(op_Scene.getScene_rank()!=null?op_Scene.getScene_rank()+"":"");
//            cell = row.createCell((short)7);
//            cell.setCellStyle(cs_data);           
//            cell.setCellValue((op_Scene.getArea_id()!=null&&op_Scene.getArea_id().getArea_id()!=null)?op_Scene.getArea_id().getArea_id():"");
//            cell = row.createCell((short)8);
//            cell.setCellStyle(cs_data);
//            cell.setCellValue(op_Scene.getScene_creater()!=null?op_Scene.getScene_creater():"");
//            cell = row.createCell((short)9);
//            cell.setCellStyle(cs_data);
//            cell.setCellValue(op_Scene.getScene_desc()!=null?op_Scene.getScene_desc():"");
//            cell = row.createCell((short)10);
//            cell.setCellStyle(cs_data);
//            cell.setCellValue(op_Scene.getScene_keyWord()!=null?op_Scene.getScene_keyWord():"");
//            cell = row.createCell((short)11);
//            cell.setCellStyle(cs_data);
//            cell.setCellValue(op_Scene.getScene_addr()!=null?op_Scene.getScene_addr():"");
//        }
//        */
//		return wwb;
//	}
}
