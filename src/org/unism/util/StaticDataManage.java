package org.unism.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StaticDataManage {
	// 返回场景大类集合
	public static List<Scene_type> getScene_types() {
		List<Scene_type> scene_types = new ArrayList<Scene_type>();
		scene_types.add(new Scene_type("1","设施园艺"));
		scene_types.add(new Scene_type("2","水产养殖"));
		scene_types.add(new Scene_type("3","大田种植"));
		scene_types.add(new Scene_type("4","禽畜养殖"));
		return scene_types;
	}
	
	// 返回场景子类集合
	public static List<Scene_ctype> getScene_ctypes() {
		List<Scene_ctype> scene_ctypes = new ArrayList<Scene_ctype>();
		scene_ctypes.add(new Scene_ctype("11","设施蔬菜"));
		scene_ctypes.add(new Scene_ctype("12","设施花卉"));
		scene_ctypes.add(new Scene_ctype("21","池塘水产养殖"));
		scene_ctypes.add(new Scene_ctype("22","设施水产养殖"));
		scene_ctypes.add(new Scene_ctype("31","井灌"));
		scene_ctypes.add(new Scene_ctype("32","低压管道"));
		scene_ctypes.add(new Scene_ctype("33","明渠"));
		scene_ctypes.add(new Scene_ctype("34","田间滴灌"));
		return scene_ctypes;
	}
	
	// 返回场景细类集合
	public static List<Scene_gtype> getScene_gtypes() {
		List<Scene_gtype> scene_gtypes = new ArrayList<Scene_gtype>();
		scene_gtypes.add(new Scene_gtype("1","蟹池塘"));
		scene_gtypes.add(new Scene_gtype("2","池塘组"));
		scene_gtypes.add(new Scene_gtype("3","基地"));
		scene_gtypes.add(new Scene_gtype("4","企业"));
		scene_gtypes.add(new Scene_gtype("5","项目"));
		
		scene_gtypes.add(new Scene_gtype("101","日光温室"));
		scene_gtypes.add(new Scene_gtype("102","连栋温室"));
		scene_gtypes.add(new Scene_gtype("111","大田茶叶"));
		scene_gtypes.add(new Scene_gtype("112","武汉茶叶基地"));
		
		scene_gtypes.add(new Scene_gtype("201","养殖虾池塘"));//养殖虾
		scene_gtypes.add(new Scene_gtype("202","鱼池塘"));
		scene_gtypes.add(new Scene_gtype("203","育苗虾池塘"));//育苗虾
		scene_gtypes.add(new Scene_gtype("204","循环水"));
		scene_gtypes.add(new Scene_gtype("205","混水池"));
		scene_gtypes.add(new Scene_gtype("206","出水口"));
		scene_gtypes.add(new Scene_gtype("207","天津鱼池塘"));
		scene_gtypes.add(new Scene_gtype("251","海水平台"));
		scene_gtypes.add(new Scene_gtype("252","鲍鱼预热池"));
		scene_gtypes.add(new Scene_gtype("253","鲍鱼车间"));
		scene_gtypes.add(new Scene_gtype("254","海参预热池"));
		scene_gtypes.add(new Scene_gtype("255","海参车间"));
		scene_gtypes.add(new Scene_gtype("256","养参池"));
		scene_gtypes.add(new Scene_gtype("257","贝类预热池"));
		scene_gtypes.add(new Scene_gtype("258","贝类育苗"));
		scene_gtypes.add(new Scene_gtype("259","贝类饵料育苗"));
		
		scene_gtypes.add(new Scene_gtype("301","水分监测站"));
		scene_gtypes.add(new Scene_gtype("302","水文监测站"));
		scene_gtypes.add(new Scene_gtype("303","水质监测站"));
		
		scene_gtypes.add(new Scene_gtype("401","猪舍"));
		scene_gtypes.add(new Scene_gtype("402","梵龙畜禽"));
		scene_gtypes.add(new Scene_gtype("411","鸡舍"));
		
		scene_gtypes.add(new Scene_gtype("97","视频点"));
		scene_gtypes.add(new Scene_gtype("98","气象站"));
		scene_gtypes.add(new Scene_gtype("99","移动场景"));
		return scene_gtypes;
	}
	
	//返回供电方式
	public static List<String> getDev_powerTypes(){
		List<String> dev_powerTypes = new ArrayList<String>();
		dev_powerTypes.add("干电池");
		dev_powerTypes.add("高能电池");
		dev_powerTypes.add("锂电池");
		dev_powerTypes.add("纽扣电池");
		dev_powerTypes.add("Uni-SAR15-BAT2");
		dev_powerTypes.add("Uni-SAR15-BTA4");
		dev_powerTypes.add("220VAC");
		dev_powerTypes.add("380VAC");
		dev_powerTypes.add("220VAC+BAT");
		dev_powerTypes.add("220VAC+SAR");
		dev_powerTypes.add("380VAC+BAT");
		dev_powerTypes.add("380VAC+SAR");
		return dev_powerTypes;
	}
	
	//返回连接方式
	public static List<Net_linkSts> getNet_linkStss(){
		List<Net_linkSts> net_linkStss = new ArrayList<Net_linkSts>();
//		net_linkStss.add(new Net_linkSts("0","无效")); //据《基础平台数据库设计v0.95.1完整版.doc》更新 2011-11-02 wang_yuliang
		net_linkStss.add(new Net_linkSts("1","长连接"));
		net_linkStss.add(new Net_linkSts("2","短连接"));
		net_linkStss.add(new Net_linkSts("3","无连接"));
		return net_linkStss;
	}
	
	//返回网络类型
	public static List<Net_type> getNet_types(){
		List<Net_type> net_types = new ArrayList<Net_type>();
		net_types.add(new Net_type("0","远程网 M2M"));
		net_types.add(new Net_type("1","无线传感网络 wsn"));
		net_types.add(new Net_type("2","有线传感网 智能传感器"));
		return net_types;
	}
	
	//返回通道数据处理方式
	public static List<Ch_procesStyle> getCh_procesStyles(){
		List<Ch_procesStyle> ch_procesStyles = new ArrayList<Ch_procesStyle>();
		ch_procesStyles.add(new Ch_procesStyle("0","表示不处理（无效通道）"));
		ch_procesStyles.add(new Ch_procesStyle("1","校正后存储（一般采集，数据上报-存储模式）"));
		ch_procesStyles.add(new Ch_procesStyle("2","校正后定时存储（实时采集数据，平台变化触发或定时存储）"));
		ch_procesStyles.add(new Ch_procesStyle("3","校正后定时存储-设备能量状态显示"));
		ch_procesStyles.add(new Ch_procesStyle("4","校正后状态为停不存储，其它定时存储-控制设备状态返回显示"));
		return ch_procesStyles;
	}
	
	//返回运行状态
	public static List<Dest_workSts> getDest_workStss(){
		List<Dest_workSts> dest_workStss = new ArrayList<Dest_workSts>();
		dest_workStss.add(new Dest_workSts("0","离线"));
		dest_workStss.add(new Dest_workSts("1","在线"));
		dest_workStss.add(new Dest_workSts("2","网关小无线模块故障"));
		dest_workStss.add(new Dest_workSts("3","小无线能量故障"));
		dest_workStss.add(new Dest_workSts("4","小无线通讯故障"));
		dest_workStss.add(new Dest_workSts("5","传感器故障"));
		dest_workStss.add(new Dest_workSts("6","传感器超量程"));
		dest_workStss.add(new Dest_workSts("7","传感器超变化率"));
		return dest_workStss;
	}
	
	//返回注册身份
	public static List<Dest_regSts> getDest_regStss(){
		List<Dest_regSts> dest_regStss = new ArrayList<Dest_regSts>();
		dest_regStss.add(new Dest_regSts("0","未注册"));
		dest_regStss.add(new Dest_regSts("1","合法注册"));
		dest_regStss.add(new Dest_regSts("2","非法注册"));
		return dest_regStss;
	}
	
	//返回返回结果
	public static List<Deco_result> getDeco_results(){
		List<Deco_result> deco_results = new ArrayList<Deco_result>();
		deco_results.add(new Deco_result("0","无效"));
		deco_results.add(new Deco_result("1","成功"));
		deco_results.add(new Deco_result("2","失败"));
		return deco_results;
	}
	
	//返回表20.	控制设备操作表操作类型
	public static List<Deco_type> getDeco_types(){
		List<Deco_type> deco_types = new ArrayList<Deco_type>();
		deco_types.add(new Deco_type("1","开(正向)"));
		deco_types.add(new Deco_type("2","停"));
		deco_types.add(new Deco_type("3","关(反向)"));
		return deco_types;
	}
	
	//返回表16.	控制设备按钮配置表操作类型
	public static List<Op_DevCtrlBtn_Deco_type> getOp_DevCtrlBtn_Deco_types(){
		List<Op_DevCtrlBtn_Deco_type> op_DevCtrlBtn_Deco_type_list = new ArrayList<Op_DevCtrlBtn_Deco_type>();
		op_DevCtrlBtn_Deco_type_list.add(new Op_DevCtrlBtn_Deco_type("1","开(正向)"));
		op_DevCtrlBtn_Deco_type_list.add(new Op_DevCtrlBtn_Deco_type("2","停"));
		op_DevCtrlBtn_Deco_type_list.add(new Op_DevCtrlBtn_Deco_type("3","关(反向)"));
		return op_DevCtrlBtn_Deco_type_list;
	}
	
	//返回返回错误码
	public static List<Deco_errorCode> getDeco_errorCodes(){
		List<Deco_errorCode> deco_errorCodes = new ArrayList<Deco_errorCode>();
		deco_errorCodes.add(new Deco_errorCode("28","控制失败，没有接电流互感器"));
		deco_errorCodes.add(new Deco_errorCode("21","控制失败，电源缺项"));
		deco_errorCodes.add(new Deco_errorCode("19","控制失败，电流异常"));
		deco_errorCodes.add(new Deco_errorCode("5","控制失败，参数错误"));
		return deco_errorCodes;
	}
	
	//返回用户类型
	public static List<Deco_userType> getDeco_userTypes(){
		List<Deco_userType> deco_userTypes = new ArrayList<Deco_userType>();
		deco_userTypes.add(new Deco_userType("0","运维"));
		deco_userTypes.add(new Deco_userType("1","应用(固定)"));
		return deco_userTypes;
	}
	
	//返回 表19.	控制设备状态历史表-控制设备状态
	public static List<Dect_state> getDect_states(){
		List<Dect_state> dect_states = new ArrayList<Dect_state>();
		dect_states.add(new Dect_state("0","未知"));
		dect_states.add(new Dect_state("1","开"));
		dect_states.add(new Dect_state("2","停"));
		dect_states.add(new Dect_state("3","关"));
		return dect_states;
	}
	
	//返回 表19.	控制设备状态历史表-有效性
	public static List<Decst_valid> getDecst_valids(){
		List<Decst_valid> decst_valids = new ArrayList<Decst_valid>();
		decst_valids.add(new Decst_valid("0","无效"));
		decst_valids.add(new Decst_valid("1","有效"));
		return decst_valids;
	}
	
	//返回数值类型
	public static List<Cho_datetype> getCho_datetypes(){
		List<Cho_datetype> cho_datetypes = new ArrayList<Cho_datetype>();
		cho_datetypes.add(new Cho_datetype("1","平均值"));
		cho_datetypes.add(new Cho_datetype("2","最大值"));
		cho_datetypes.add(new Cho_datetype("3","最小值"));
		return cho_datetypes;
	}
	
	//返回 表5.智能设备状态历史表-连接方式
	public static List<Dest_linkSts> getDest_linkStss(){
		List<Dest_linkSts> dest_linkStss = new ArrayList<Dest_linkSts>();
		dest_linkStss.add(new Dest_linkSts("1","长连接"));
		dest_linkStss.add(new Dest_linkSts("2","短连接"));
		dest_linkStss.add(new Dest_linkSts("3","临时长连接"));
		return dest_linkStss;
	}
	
	//返回设备时间状态
	public static List<Dest_timeSts> getDest_timeStss(){
		List<Dest_timeSts> dest_timeStss = new ArrayList<Dest_timeSts>();
		dest_timeStss.add(new Dest_timeSts("0","异步"));
		dest_timeStss.add(new Dest_timeSts("1","同步"));
		return dest_timeStss;
	}
	
	//返回设备大类信息
	public static List<Dev_btype> getDev_btypes(){
		List<Dev_btype> dev_btypes = new ArrayList<Dev_btype>(); 
		dev_btypes.add(new Dev_btype("0","M2M"));
		dev_btypes.add(new Dev_btype("1","WSN"));
		dev_btypes.add(new Dev_btype("2","智能单元"));
		dev_btypes.add(new Dev_btype("3","执行器(可控设备)"));
		dev_btypes.add(new Dev_btype("4","传感器"));
		dev_btypes.add(new Dev_btype("5","非智能传感器"));
		return dev_btypes;
	}
	
	//返回表12.	采集通道信息表数值类型信息
	public static List<Ch_dateType> getCh_dateTypes(){
		List<Ch_dateType> ch_dateType_list = new ArrayList<Ch_dateType>();
		ch_dateType_list.add(new Ch_dateType("0","表示一般值或为NULL"));
		ch_dateType_list.add(new Ch_dateType("1","平均值"));
		ch_dateType_list.add(new Ch_dateType("2","最大值"));
		ch_dateType_list.add(new Ch_dateType("3","最小值"));
		return ch_dateType_list;
	}
	
	//返回设备使用状态信息
	public static List<Dev_state> getDev_states(){
		List<Dev_state> dev_state_list = new ArrayList<Dev_state>();
		dev_state_list.add(new Dev_state("0","未用"));
		dev_state_list.add(new Dev_state("1","在用"));
		return dev_state_list;
	}
	
	//返回网络使用状态信息
	public static List<Net_state> getNet_states(){
		List<Net_state> net_state_list = new ArrayList<Net_state>();
		net_state_list.add(new Net_state("0","未用"));
		net_state_list.add(new Net_state("1","在用"));
		return net_state_list;
	}
	
	//返回网内角色信息
	public static List<Net_role> getNet_roles(){
		List<Net_role> net_role_list = new ArrayList<Net_role>();
		net_role_list.add(new Net_role("01","网关"));
		net_role_list.add(new Net_role("02","独立设备"));
		net_role_list.add(new Net_role("03","移动设备"));
		net_role_list.add(new Net_role("11","中继设备RD"));
		net_role_list.add(new Net_role("12","叶设备ED"));
		net_role_list.add(new Net_role("13","协调器CD"));
		net_role_list.add(new Net_role("14","接入设备LD"));
		net_role_list.add(new Net_role("21","智能传感器"));
		net_role_list.add(new Net_role("22","智能变送器"));
		return net_role_list;
	}
	
	//返回表12.	采集通道信息表是否对外提供服务信息
	public static List<Ch_offerSer> getCh_offerSers(){
		List<Ch_offerSer> ch_offerSer_list = new ArrayList<Ch_offerSer>();
		ch_offerSer_list.add(new Ch_offerSer("0","否"));
		ch_offerSer_list.add(new Ch_offerSer("1","是"));
		return ch_offerSer_list;
	}
	
	//返回采集通道使用状态信息
	public static List<Ch_state> getCh_states(){
		List<Ch_state> ch_state_list = new ArrayList<Ch_state>();
		ch_state_list.add(new Ch_state("0","未用"));
		ch_state_list.add(new Ch_state("1","在用"));
		return ch_state_list;
	}
	
	//返回表14.	控制设备信息表控制设备使用状态信息
	public static List<Gm_DevCtrl_Dect_state> getGm_DevCtrl_Dect_states(){
		List<Gm_DevCtrl_Dect_state> gm_DevCtrl_Dect_state_list = new ArrayList<Gm_DevCtrl_Dect_state>();
		gm_DevCtrl_Dect_state_list.add(new Gm_DevCtrl_Dect_state("0","未用"));
		gm_DevCtrl_Dect_state_list.add(new Gm_DevCtrl_Dect_state("1","在用"));
		return gm_DevCtrl_Dect_state_list;
	}
	
	//返回表14.	控制设备信息表是否对外提供服务信息
	public static List<Gm_DevCtrl_Ch_offerSer> getGm_DevCtrl_Ch_offerSers(){
		List<Gm_DevCtrl_Ch_offerSer> gm_DevCtrl_Ch_offerSer_list = new ArrayList<Gm_DevCtrl_Ch_offerSer>();
		gm_DevCtrl_Ch_offerSer_list.add(new Gm_DevCtrl_Ch_offerSer("0","否"));
		gm_DevCtrl_Ch_offerSer_list.add(new Gm_DevCtrl_Ch_offerSer("1","是"));
		return gm_DevCtrl_Ch_offerSer_list;
	}
	
	//返回控制类型信息
	public static List<Dect_ctlType> getDect_ctlTypes(){
		List<Dect_ctlType> dect_ctlType_list = new ArrayList<Dect_ctlType>();
		dect_ctlType_list.add(new Dect_ctlType("1","正向脉冲"));
		dect_ctlType_list.add(new Dect_ctlType("2","反向脉冲"));
		dect_ctlType_list.add(new Dect_ctlType("3","高电平"));
		dect_ctlType_list.add(new Dect_ctlType("4","低电平"));
		return dect_ctlType_list;
	}
	
	//返回表17.	控制设备状态配置表状态类型信息
	public static List<Op_DevCtrlSts_Dect_state> getOp_DevCtrlSts_Dect_states(){
		List<Op_DevCtrlSts_Dect_state> op_DevCtrlSts_Dect_state_list = new ArrayList<Op_DevCtrlSts_Dect_state>();
		op_DevCtrlSts_Dect_state_list.add(new Op_DevCtrlSts_Dect_state("1","开（正向）"));
		op_DevCtrlSts_Dect_state_list.add(new Op_DevCtrlSts_Dect_state("2","停"));
		op_DevCtrlSts_Dect_state_list.add(new Op_DevCtrlSts_Dect_state("3","关（反向）"));
		op_DevCtrlSts_Dect_state_list.add(new Op_DevCtrlSts_Dect_state("4","运行"));
		return op_DevCtrlSts_Dect_state_list;
	}
	
	//返回供应商类型信息
	public static List<Sup_type> getSup_types(){
		List<Sup_type> sup_type_list = new ArrayList<Sup_type>();
		sup_type_list.add(new Sup_type("0","销售商"));
		sup_type_list.add(new Sup_type("1","服务商"));
		return sup_type_list;
	}
	
	//返回故障对象信息
	public static List<Def_object> getDef_objects(){
		List<Def_object> def_object_list = new ArrayList<Def_object>();
		def_object_list.add(new Def_object("0","平台"));
		def_object_list.add(new Def_object("1","GPRS"));
		def_object_list.add(new Def_object("2","WSN"));
		def_object_list.add(new Def_object("3","传感器"));
		def_object_list.add(new Def_object("4","控制设备"));
		return def_object_list;
	}	
	
	//返回故障类型信息
	public static List<Def_type> getDef_types(){
		List<Def_type> def_type_list = new ArrayList<Def_type>();
		def_type_list.add(new Def_type("1","通讯状况"));
		def_type_list.add(new Def_type("2","设备运行状况"));
		def_type_list.add(new Def_type("3","数据时间连续性和完整性"));
		def_type_list.add(new Def_type("4","数据合理性"));
		return def_type_list;
	}
	
	/**
	 * def_object故障对象
	 * def_type故障类型 
	 * return 返回故障症状list
	 * weixiaohua
	 */
	public static List<Def_symptom> getDef_symptomList(int def_object,int def_type){
		List<Def_symptom> list = new ArrayList<Def_symptom>();
		if(def_object==0){//平台
			if(def_type==1){//通讯状况
				list.add(new Def_symptom(0,"非法注册"));
			}
			if(def_type==2){//设备运行状况
				list.add(new Def_symptom(0,"数据库异常"));
			}
			if(def_type==4){//数据合理性
				list.add(new Def_symptom(0,"配置错误"));
			}
		}//平台结束符
		
		if(def_object==1){//GPRS
			if(def_type==1){//通讯状况
				list.add(new Def_symptom(0,"心跳不健康"));
				list.add(new Def_symptom(1,"信号强度低"));
				list.add(new Def_symptom(2,"更新不及时"));
				list.add(new Def_symptom(3,"离线"));
				list.add(new Def_symptom(4,"SIM卡余额低"));
			}
			if(def_type==2){//设备运行状况
				list.add(new Def_symptom(0,"市电断电"));
				list.add(new Def_symptom(1,"设备能量超上限"));
				list.add(new Def_symptom(2,"设备能量超下限"));
				list.add(new Def_symptom(3,"设备能量超变化率"));
				list.add(new Def_symptom(4,"M2M设备复位"));
				list.add(new Def_symptom(5,"存储空间不足"));
				list.add(new Def_symptom(6,"时间不同步"));
			}
			if(def_type==3){//数据时间连续性和完整性
				list.add(new Def_symptom(0,"数据不完整"));
				list.add(new Def_symptom(1,"采集时间连续性"));
				list.add(new Def_symptom(2,"采集时间不单调"));
				list.add(new Def_symptom(3,"采集时间大于当前系统时间"));
			}
			if(def_type==4){//数据合理性
				list.add(new Def_symptom(0,"M2M设备通道数据为0"));
				list.add(new Def_symptom(1,"数据超上限"));
				list.add(new Def_symptom(2,"数据超下限"));
				list.add(new Def_symptom(3,"数据超变化率"));
				list.add(new Def_symptom(4,"变化趋势不合理"));
				list.add(new Def_symptom(5,"超过校准周期"));
			}
		}//GPRS结束符
		
		if(def_object==2){//WSN
			if(def_type==1){//通讯状况
				list.add(new Def_symptom(0,"WSN数据不变"));
				list.add(new Def_symptom(1,"信号强度低"));
			}
			if(def_type==2){//设备运行状况
				list.add(new Def_symptom(0,"市电断电"));
				list.add(new Def_symptom(1,"设备能量超上限"));
				list.add(new Def_symptom(2,"设备能量超下限"));
				list.add(new Def_symptom(3,"设备能量超变化率"));
				list.add(new Def_symptom(4,"WSN设备复位"));
				list.add(new Def_symptom(5,"存储空间不足"));
			}
			if(def_type==3){//数据时间连续性和完整性
				list.add(new Def_symptom(0,"数据不完整"));
			}
			if(def_type==4){//数据合理性
				list.add(new Def_symptom(0,"WSN设备通道数据为0"));
				list.add(new Def_symptom(1,"设备电压数据超上限"));
				list.add(new Def_symptom(2,"设备电压数据超下限"));
				list.add(new Def_symptom(3,"设备电压数据超变化率"));
				list.add(new Def_symptom(4,"变化趋势不合理"));
			}
		}//WSN结束符
		
		if(def_object==3){//传感器
			if(def_type==1){//通讯状况
				list.add(new Def_symptom(0,"传感器数据不变"));
			}
			if(def_type==3){//数据时间连续性和完整性
				list.add(new Def_symptom(0,"数据不完整"));
			}
			if(def_type==4){//数据合理性
				list.add(new Def_symptom(0,"传感器通道数据为0"));
				list.add(new Def_symptom(1,"数据超上限"));
				list.add(new Def_symptom(2,"数据超下限"));
				list.add(new Def_symptom(3,"数据变化超变化率"));
				list.add(new Def_symptom(4,"变化趋势不合理"));
				list.add(new Def_symptom(5,"超过校准周期"));
			}
		}//传感器结束符
		if(def_object==4){//控制设备
			if(def_type==2){//设备运行状况
				list.add(new Def_symptom(0,"控制设备状态异常"));
			}
		}//控制设备结束符
		return list;
	}
	
	
	/**
	 * def_object故障对象
	 * def_type故障类型
	 * def_symptom故障症状
	 * return 返回故障信息
	 * weixiaohua
	 */
	public static String getDevFaultInfo(Integer def_object,Integer def_type,Integer def_symptom){
		String devFaultInfo="";
		if(def_object==0){//平台
			if(def_type==1){//通讯状况
				if(def_symptom==0){
					devFaultInfo="非法注册";
				}
			}
			if(def_type==2){//设备运行状况
				if(def_symptom==0){
					devFaultInfo="数据库异常";
				}
			}
			if(def_type==4){//数据合理性
				if(def_symptom==0){
					devFaultInfo="配置错误";
				}
			}
		}//平台结束符
		if(def_object==1){//GPRS
			if(def_type==1){//通讯状况
				if(def_symptom==0){
					devFaultInfo="心跳不健康";
				}
				if(def_symptom==1){
					devFaultInfo="信号强度低";
				}
				if(def_symptom==2){
					devFaultInfo="更新不及时";
				}
				if(def_symptom==3){
					devFaultInfo="离线";
				}
				if(def_symptom==4){
					devFaultInfo="SIM卡余额低";
				}
			}
			if(def_type==2){//设备运行状况
				if(def_symptom==0){
					devFaultInfo="市电断电";
				}
				if(def_symptom==1){
					devFaultInfo="设备能量超上限";
				}
				if(def_symptom==2){
					devFaultInfo="设备能量超下限";
				}
				if(def_symptom==3){
					devFaultInfo="设备能量超变化率";
				}
				if(def_symptom==4){
					devFaultInfo="M2M设备复位";
				}
				if(def_symptom==5){
					devFaultInfo="存储空间不足";
				}
				if(def_symptom==6){
					devFaultInfo="时间不同步";
				}
			}
			if(def_type==3){//数据时间连续性和完整性
				if(def_symptom==0){
					devFaultInfo="数据不完整";
				}
				if(def_symptom==1){
					devFaultInfo="采集时间连续性";
				}
				if(def_symptom==2){
					devFaultInfo="采集时间不单调";
				}
				if(def_symptom==3){
					devFaultInfo="采集时间大于当前系统时间";
				}
			}
			if(def_type==4){//数据合理性
				if(def_symptom==0){
					devFaultInfo="M2M设备通道数据为0";
				}
				if(def_symptom==1){
					devFaultInfo="数据超上限";
				}
				if(def_symptom==2){
					devFaultInfo="数据超下限";
				}
				if(def_symptom==3){
					devFaultInfo="数据超变化率";
				}
				if(def_symptom==4){
					devFaultInfo="变化趋势不合理";
				}
				if(def_symptom==5){
					devFaultInfo="超过校准周期";
				}
			}
		}//GPRS结束符
		if(def_object==2){//WSN
			if(def_type==1){//通讯状况
				if(def_symptom==0){
					devFaultInfo="WSN数据不变";
				}
				if(def_symptom==1){
					devFaultInfo="信号强度低";
				}
			}
			if(def_type==2){//设备运行状况
				if(def_symptom==0){
					devFaultInfo="市电断电";
				}
				if(def_symptom==1){
					devFaultInfo="设备能量超上限";
				}
				if(def_symptom==2){
					devFaultInfo="设备能量超下限";
				}
				if(def_symptom==3){
					devFaultInfo="设备能量超变化率";
				}
				if(def_symptom==4){
					devFaultInfo="WSN设备复位";
				}
				if(def_symptom==5){
					devFaultInfo="存储空间不足";
				}
			}
			if(def_type==3){//数据时间连续性和完整性
				if(def_symptom==0){
					devFaultInfo="数据不完整";
				}
			}
			if(def_type==4){//数据合理性
				if(def_symptom==0){
					devFaultInfo="WSN设备通道数据为0";
				}
				if(def_symptom==1){
					devFaultInfo="设备电压数据超上限";
				}
				if(def_symptom==2){
					devFaultInfo="设备电压数据超下限";
				}
				if(def_symptom==3){
					devFaultInfo="设备电压数据超变化率";
				}
				if(def_symptom==4){
					devFaultInfo="变化趋势不合理";
				}
			}
		}//WSN结束符
		if(def_object==3){//传感器
			if(def_type==1){//通讯状况
				if(def_symptom==0){
					devFaultInfo="传感器数据不变";
				}
			}
			if(def_type==3){//数据时间连续性和完整性
				if(def_symptom==0){
					devFaultInfo="数据不完整";
				}
			}
			if(def_type==4){//数据合理性
				if(def_symptom==0){
					devFaultInfo="传感器通道数据为0";
				}
				if(def_symptom==1){
					devFaultInfo="数据超上限";
				}
				if(def_symptom==2){
					devFaultInfo="数据超下限";
				}
				if(def_symptom==3){
					devFaultInfo="数据变化超变化率";
				}
				if(def_symptom==4){
					devFaultInfo="变化趋势不合理";
				}
				if(def_symptom==5){
					devFaultInfo="超过校准周期";
				}
			}
		}//传感器结束符
		if(def_object==4){//控制设备
			if(def_type==1){//通讯状况
				
			}
			if(def_type==2){//设备运行状况
				if(def_symptom==0){
					devFaultInfo="控制设备状态异常";
				}
			}
			if(def_type==3){//数据时间连续性和完整性
				
			}
			if(def_type==4){//数据合理性
				
			}
		}//控制设备结束符
		return devFaultInfo;
	}
	
	//返回智能设备状态表是否清除历史数据
	public static List<Dest_ifClearData> getDest_ifClearDatas(){
		List<Dest_ifClearData> dest_ifClearData_list = new ArrayList<Dest_ifClearData>();
		dest_ifClearData_list.add(new Dest_ifClearData("0","否"));
		dest_ifClearData_list.add(new Dest_ifClearData("1","是"));
		return dest_ifClearData_list;
	}
	
	//返回智能设备状态表是否重启
	public static List<Dest_ifReset> getDest_ifResets(){
		List<Dest_ifReset> dest_ifReset_list = new ArrayList<Dest_ifReset>();
		dest_ifReset_list.add(new Dest_ifReset("0","否"));
		dest_ifReset_list.add(new Dest_ifReset("1","是"));
		return dest_ifReset_list;
	}
	
	//返回智能设备状态表是否临时在线
	public static List<Dest_ifOnlineTmp> getDest_ifOnlineTmps(){
		List<Dest_ifOnlineTmp> dest_ifOnlineTmp_list = new ArrayList<Dest_ifOnlineTmp>();
		dest_ifOnlineTmp_list.add(new Dest_ifOnlineTmp("0","否"));
		dest_ifOnlineTmp_list.add(new Dest_ifOnlineTmp("1","是"));
		return dest_ifOnlineTmp_list;
	}
	
	//返回智能设备状态表电源类型
	public static List<Dest_powerType> getDest_powerTypes(){
		List<Dest_powerType> dest_powerType_list = new ArrayList<Dest_powerType>();
		dest_powerType_list.add(new Dest_powerType("0","电池"));
		dest_powerType_list.add(new Dest_powerType("1","市电"));
		return dest_powerType_list;
	}
	
	//
	public static String getDest_workSts(Integer dest_workSts){
		String Dest_workSts="离线";
		if(dest_workSts==0){
			Dest_workSts="离线";
		}
		if(dest_workSts==1){
			Dest_workSts="在线";
		}
		if(dest_workSts==2){
			Dest_workSts="网关小无线模块故障";
		}
		if(dest_workSts==3){
			Dest_workSts="小无线能量故障";
		}
		if(dest_workSts==4){
			Dest_workSts="小无线通讯故障";
		}
		if(dest_workSts==5){
			Dest_workSts="传感器故障";
		}
		if(dest_workSts==6){
			Dest_workSts="传感器超量程";
		}
		if(dest_workSts==7){
			Dest_workSts="传感器超变化率";
		}
		return Dest_workSts;
	}
	//返回建议维护信息
	public static Map<Integer, String> getMaintenanceOperationMessage(){
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(0, "建议农户先给设备市电供电，市电供电后如果检测到故障，再按照相应的故障处理；");
		map.put(1, "");
		map.put(2, "建议更换网关的小无线天线，如果问题仍未解决，则更换网关；");
		map.put(3, "建议更换太阳能板和电池；");
		map.put(4, "建议更换小无线设备；");
		map.put(5, "建议更换传感器；");
		map.put(6, "建议检查并清理溶解氧传感器周围环境，清洗传感器探头，观察一断时候后，如果仍未解决，则更换传感器；");
		map.put(7, "建议检查并清理溶解氧传感器周围环境，清洗传感器探头，观察一断时候后，如果仍未解决，则更换传感器；");
		return map;
	}
}
