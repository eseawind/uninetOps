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
package org.unism.cau;

public class BaseInformation {
	/** EXCEL名称 供应商信息表 * */
	public final static String NAME_Supplier = "供应商信息表";

	/** EXCEL名称 采集通道应用类型信息表 * */
	public final static String NAME_ChannelType = "采集通道应用类型信息表";

	/** EXCEL名称 控制设备类型信息表 * */
	public final static String NAME_DevCtrlType = "控制设备类型信息表";

	/** EXCEL名称 场景信息表 * */
	public final static String NAME_Scene = "场景信息表";

	/** EXCEL名称 设备信息表 * */
	public final static String NAME_Device = "设备信息表";

	/** EXCEL名称 网络信息表 * */
	public final static String NAME_DevNet = "网络信息表";

	/** EXCEL名称 控制设备信息表 * */
	public final static String NAME_DevCtrl = "控制设备信息表";

	/** EXCEL名称 采集通道信息表 * */
	public final static String NAME_Channel = "采集通道信息表";

	/** EXCEL名称 控制设备按钮配置表 * */
	public final static String NAME_DevCtrlBtn = "控制设备按钮配置表";

	/** EXCEL名称 行政区划表 * */
	public final static String NAME_Areas = "行政区划表";

	/** EXCEL名称 智能设备状态表 * */
	public final static String NAME_Devsts = "智能设备状态表";

	/** EXCEL名称 控制设备状态表 * */
	public final static String NAME_GmDevCtrlSts = "控制设备状态表";

	/** EXCEL名称 控制设备状态配置表 * */
	public final static String NAME_OpDevCtrlSts = "控制设备状态配置表";

	/** EXCEL中文列名 供应商信息表 * */
	public final static String[] CNNAME_Supplier = { "供应商编号", "供应商名称", "供应商类型",
			"供应商国家", "供应商地址", "供应商邮编", "供应商固定电话", "供应商传真", "联系人姓名", "联系人电话",
			"联系人手机", "联系人邮箱" };

	/** EXCEL中文列名 采集通道应用类型信息表 * */
	public final static String[] CNNAME_ChannelType = { "类型编号", "类型名称", "设备型号",
			"小数位数", "元数据单位", "量程上限", "量程下限", "变化率上限", "校准周期", "校正公式", "显示样式",
			"采集量显示的图片" };

	/** EXCEL中文列名 控制设备类型信息表 * */
	public final static String[] CNNAME_DevCtrlType = { "类型编号", "类型名称", "类型型号",
			"类型功率", "类型供电电压", "类型说明", "类型图片资源", "类型按钮数量", "通道状态反馈数量" };

	/** EXCEL中文列名 场景信息表 * */
	public final static String[] CNNAME_Scene = { "场景编号", "场景所在地", "创建者",
			"场景的说明", "关键字", "场景名称", "所属场景父编号", "场景类型", "场景类型子类", "所属等级",
			"场景的图片", "定制路径", "区划ID", "场景使用状态" };

	/** EXCEL中文列名 设备信息表 * */
	public final static String[] CNNAME_Device = { "设备编号", "设备名称", "设备序列号",
			"设备大类", "设备小类", "设备型号", "供电方式", "设备IMSI", "购买时间", "服务到期时间", "生效时间",
			"设备厂家编号", "设备服务商编号", "设备使用状态", "所属场景编号" };

	/** EXCEL中文列名 网络信息表 * */
	public final static String[] CNNAME_DevNet = { "网络编号", "设备编号", "网络地址",
			"网络类型", "网内角色", "网内深度", "连接方式", "应用类型", "父节点编号", "SIM卡号", "短信中心号",
			"协议类型", "网络使用状态" };

	/** EXCEL中文列名 控制设备信息表 * */
	public final static String[] CNNAME_DevCtrl = { "设备编号", "设备名称", "设备序列号",
			"类型编号", "类型按钮数量", "购买时间", "服务到期时间", "生效时间", "设备使用状态", "场景编号",
			"是否对外提供服务", "设备说明", "所属设备编号" };

	/** EXCEL中文列名 采集通道信息表 * */
	public final static String[] CNNAME_Channel = { "通道编号", "通道名称", "采集设备编号",
			"设备通道号", "传感设备编号", "应用类型编号", "通道数据处理方式", "存储周期", "校正后的单位", "场景编号",
			"是否对外提供服务", "采集通道使用状态" };

	/** EXCEL中文列名 控制设备按钮配置表 * */
	public final static String[] CNNAME_DevCtrlBtn = { "按钮名称", "操作类型",
			"控制设备编号", "所属设备编号", "控制通道", "控制类型", "操作延时" };

	/** EXCEL中文列名 行政区划表 * */
	public final static String[] CNNAME_Areas = { "代码", "名称", "说明" };

	/** EXCEL中文列名 智能设备状态表 * */
	public final static String[] CNNAME_Devsts = { "设备编号", "设备地址", "注册身份",
			"连接方式", "运行状态", "心跳时间(秒)", "上报周期(短连接)", "信号强度", "通信质量", "设备当前时间",
			"设备时间状态", "最近通信时间", "设备能量", "能量状态", "设备复位次数", "数据记录总数", "数据未报数量",
			"休眠时间(秒)" };

	/** EXCEL中文列名 控制设备状态表 * */
	public final static String[] CNNAME_GmDevCtrlSts = { "控制设备编号", "设备状态",
			"有效性", "状态变更时间" };

	/** EXCEL中文列名 控制设备状态配置表 * */
	public final static String[] CNNAME_OpDevCtrlSts = { "状态名称", "状态类型",
			"控制设备编号", "状态通道编号", "范围上限", "范围下限" };

	/** EXCEL英文列名 供应商信息表 * */
	public final static String ENNAME_XLS_Supplier = "Sup_no, sup_name, sup_type, sup_country, sup_address, sup_zip, sup_phone, sup_fax, sup_contactName, sup_contactPhone, sup_contactMobile, sup_contactEmail";

	/** EXCEL英文列名 采集通道应用类型信息表 * */
	public final static String ENNAME_XLS_ChannelType = "Chtype_No, chtype_displayName, dev_model, ch_dectDig, ch_unit, ch_max, ch_min, ch_crateMax, ch_corrCyc, ch_corrFormula, Ch_ClassName, typeImg";

	/** EXCEL英文列名 控制设备类型信息表 * */
	public final static String ENNAME_XLS_DevCtrlType = "decttype_No, decttype_displayName, Decttype_typeNo, Decttype_Power, decttype_Voltage, decttype_decription,decttype_Img, decttype_BtnNum, decttype_ChlStsNum";

	/** EXCEL英文列名 场景信息表 * */
	public final static String ENNAME_XLS_Scene = "scene_no, scene_addr, scene_createDate, scene_creater, scene_desc, scene_keyWord, scene_name,scene_pid,scene_type,scene_ctype,scene_rank,scene_image,scene_url,area_id,scene_state";

	/** EXCEL英文列名 设备信息表* */
	public final static String ENNAME_XLS_Device = "dev_no, dev_name,dev_serial,dev_btype,dev_stype,dev_model,dev_powerType,dev_imsi,dev_buyDate,dev_overDate,dev_effectTime,cust_saleId,cust_serviceId,dev_state,scene_id";

	/** EXCEL英文列名 网络信息表 * */
	public final static String ENNAME_XLS_DevNet = "net_no,dev_id,net_addr,net_type,net_role,net_depth,net_linkSts,net_appType,net_pid,net_sim,net_smsc,net_pltType,net_state";

	/** EXCEL英文列名 控制设备信息表 * */
	public final static String ENNAME_XLS_DevCtrl = "dect_no,dect_name,dect_serial,decttype_id,decttype_BtnNum,dect_buyDate,dect_overDate,dect_effectTime,dect_state,scene_id,ch_offerSer,dect_decsription,dev_id";

	/** EXCEL英文列名 采集通道信息表 * */
	public final static String ENNAME_XLS_Channel = "ch_no,ch_name,dev_collectId,ch_chlNo,dev_sensorId,Chtype_id,ch_procesStyle,Ch_MemoryPeriod,ch_dectDig,ch_unit,ch_max,ch_min,ch_crateMax,ch_corrCyc,ch_corrFormula,ch_corrUnit,scene_id,ch_offerSer,ch_state";

	/** EXCEL英文列名 设备上报通道配置表 * */
	public final static String ENNAME_XLS_DevChannel = "ch_id,dev_id,dev_addr,dch_order,ch_procesStyle,Ch_MemoryPeriod";

	/** EXCEL英文列名 控制设备按钮配置表 * */
	public final static String ENNAME_XLS_DevCtrlBtn = "Dectbtn_Name,deco_type,dect_id,dev_id,dect_ChlNo,dect_ctlType,dect_ctlDelay";

	/** EXCEL英文列名 行政区划表 * */
	public final static String ENNAME_XLS_Areas = "area_id,area_name,area_desc";

	/** EXCEL英文列名 智能设备状态表 * */
	public final static String ENNAME_XLS_Devsts = "dev_id,dev_addr,dest_regSts,dest_linkSts,dest_workSts";

	/** EXCEL英文列名 控制设备状态表 * */
	public final static String ENNAME_XLS_GmDevCtrlSts = "dect_id,dect_state,decst_valid,decst_time";

	/** EXCEL英文列名 控制设备状态配置表 * */
	public final static String ENNAME_XLS_OpDevCtrlSts = "DectSts_Name,dect_state,dect_id,ch_id,ch_max,ch_min";
}
