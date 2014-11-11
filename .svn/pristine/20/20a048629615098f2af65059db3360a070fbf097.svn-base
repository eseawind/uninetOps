package org.unism.op.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.unism.gm.domain.Gm_Latestdata;
import org.unism.gm.service.Gm_LatestdataService;
import org.unism.op.domain.Op_ChannelType;
import org.unism.op.domain.Op_ChtypeOperate;
import org.unism.op.domain.Op_scene_gtype;
import org.unism.op.service.Op_ChannelTypeService;
import org.unism.op.service.Op_ChtypeOperateService;
import org.unism.op.service.Op_SceneService;
import org.unism.op.service.Op_UserInfo_SceneService;
import org.unism.op.service.Op_scene_gtypeService;
import org.unism.util.DateUtil;
import org.unism.util.DecimalUtils;
import org.wangzz.core.orm.Page;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.web.struts2.Struts2Utils;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class Op_ChtypeOperateAction extends ActionSupport{
	
	/**
	 * 分页查询
	 * @return
	 * @author Wang_Yuliang
	 */
	public String page(){
		Search search = new Search();
		if(!this.hid_condition.equals("-1")){
			Filter filter = Filter.equal(this.hid_condition, Integer.parseInt(this.hid_value));
			search.addFilter(filter);
		}			
		search.addSortAsc("cho_sequence");
		this.page = this.op_ChtypeOperateService.search(page, search);
		List<Op_scene_gtype> list = scene_gtypeService.findAll();
    	Struts2Utils.getRequest().setAttribute("sceneGtype", list);
		return "page";
	}
	
	/**
	 * 到添加
	 * @return
	 * @author Wang_Yuliang
	 */
	public String toSave(){
		this.op_ChannelType_list = this.op_ChannelTypeService.findAll();
		List<Op_scene_gtype> list = scene_gtypeService.findAll();
    	Struts2Utils.getRequest().setAttribute("sceneGtype", list);
		return "save";
	}
	
	/**
	 * 添加
	 * @return
	 * @author Wang_Yuliang
	 */
	public String save(){
		try{
			this.op_ChtypeOperateService.save(this.op_ChtypeOperate);
			addActionMessage("操作成功");
		}catch(Exception ex){
			ex.printStackTrace();
			addActionMessage("操作失败!位置错误");
		}
		return "operationResult";
	}
	
	/**
	 * 到编辑
	 * @return
	 * @author Wang_Yuliang
	 */
	public String toEdit(){
		this.op_ChtypeOperate = this.op_ChtypeOperateService.findById(this.op_ChtypeOperate.getCho_id());
		this.op_ChannelType_list = this.op_ChannelTypeService.findAll();
		List<Op_scene_gtype> list = scene_gtypeService.findAll();
    	Struts2Utils.getRequest().setAttribute("sceneGtype", list);
		return "edit";
	}
	
	/**
	 * 编辑
	 * @return
	 * @author Wang_Yuliang
	 */
	public String edit(){
		try{
			this.op_ChtypeOperateService.update(this.op_ChtypeOperate);
			addActionMessage("操作成功!");
		}catch(Exception ex){
			ex.printStackTrace();
			addActionMessage("操作失败!");
		}
		return "operationResult";
	}
	
	/**
	 * 删除
	 * @return
	 * @author Wang_Yuliang
	 */
	public String delete(){
		try{
			this.op_ChtypeOperateService.deleteById(this.op_ChtypeOperate.getCho_id());
			addActionMessage("操作成功!");
		}catch(Exception ex){
			ex.printStackTrace();
			addActionMessage("操作失败!");
		}
		return "operationResult";
	}
	
	/**
	 * 到数据汇总
	 * @return
	 * @author Wang_Yuliang
	 */
	public String toData_collect(){
		return "data_collect";
	}
	
	/**
	 * 到数据汇总 0708
	 * @return
	 * @author Wang_Yuliang
	 */
	public String toData_collect_0708(){
		return "data_collect_0708";
	}
	
	/**
	 * 数据汇总
	 * @return json
	 * 			[	
	 * 				{
	 * 					scene_gtype:'',
	 * 					head:
	 * 						[
	 * 							'xx','xxx','xxxx'
	 * 						],
	 * 					scenes:
	 * 						[ 		
	 * 							null,				
	 * 							{
	 * 								scene_name:'',
	 * 								time:'yyyy-MM-dd HH:mm:ss',
	 * 								data:
	 * 									[
	 * 										'21','23','','23'
	 * 									]
	 * 							},
	 * 							...
	 * 						]
	 * 				},
	 *				...
	 * 			]
	 * @author Wang_Yuliang
	 */
	public String data_collect(){
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			List<String> scene_id_arr = new ArrayList<String>();
			scene_id_arr.add(this.scene_id);		
			List<String> scene_id_list = this.op_UserInfo_SceneService.findScene_idByUser_id(this.user_id);
			scene_id_arr = this.op_SceneService.findSceneTree_wangyuliang(scene_id_arr, scene_id_list);			
			String json = this.op_ChtypeOperateService.data_collect(scene_id_arr);
			//System.out.println("数据汇总"+json);
			out.print(json);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("[]");
			}catch(Exception ex){ex.printStackTrace();}	
		}
		return null;
	}
	
	/**
	 * 数据汇总 0708
	 * @return json
	 * 			[	
	 * 				{
	 * 					scene_gtype:'',
	 * 					head:
	 * 						[
	 * 							'xx','xxx','xxxx'
	 * 						],
	 * 					scenes:
	 * 						[ 		
	 * 							null,				
	 * 							{
	 * 								scene_name:'',
	 * 								time:'yyyy-MM-dd HH:mm:ss',
	 * 								data:
	 * 									[
	 * 										'21','23','','23'
	 * 									]
	 * 							},
	 * 							...
	 * 						]
	 * 				},
	 *				...
	 * 			]
	 * @author Wang_Yuliang
	 */
	public String data_collect_0708(){
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			List<String> scene_id_arr = new ArrayList<String>();
			scene_id_arr.add(this.scene_id);		
			List<String> scene_id_list = this.op_UserInfo_SceneService.findScene_idByUser_id(this.user_id);
			scene_id_arr = this.op_SceneService.findSceneTree_wangyuliang(scene_id_arr, scene_id_list);			
			String json = this.op_ChtypeOperateService.data_collect_0708(scene_id_arr);
			//System.out.println("数据汇总"+json);
			out.print(json);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("[]");
			}catch(Exception ex){ex.printStackTrace();}	
		}
		return null;
	}
	
//	public String dataCollect(){
//		try {
//			PrintWriter out = Struts2Utils.getResponse().getWriter();
//			if(StringUtils.hasText(scene_id)){
//				//获取场景id
//				String[] sceneIdArr = scene_id.split(",");
//				//通过场景id查询出所有的最新数据
//				List<Gm_Latestdata> latestdatas = gm_LatestdataService.findBySceneIds(sceneIdArr);
//				/**
//				 * key:场景id
//				 * value：场景下所有对外提供服务，在用的通道的最新数据
//				 */
//				Map<String, List<Gm_Latestdata>> map = new HashMap<String, List<Gm_Latestdata>>();
//				/**
//				 * key：通道类型编号
//				 * value：通道类型名
//				 */
//				Map<String, String> chtypeMap = new LinkedHashMap<String, String>();
//				/**
//				 * key：场景id
//				 * value：场景名
//				 */
//				Map<String, String> sceneMap = new HashMap<String, String>();
//				
//				Map<String, String> unitMap = new HashMap<String, String>();
//				/**
//				 * 循环所有最新数据
//				 */
//				for (Gm_Latestdata gm_Latestdata : latestdatas) {
//					/**
//					 * 获取到通道类型
//					 */
//					Op_ChannelType channelType = gm_Latestdata.getCh_id().getChtype_id();
//					/**
//					 * 以通道类型编号为key，通道类型名为value，存入map
//					 */
//					chtypeMap.put(channelType.getChtype_no(),channelType.getChtype_displayName());
//					unitMap.put(channelType.getChtype_no(), channelType.getCh_corrUnit());
//					/**
//					 * 拿到场景id
//					 */
//					String sceneId = gm_Latestdata.getCh_id().getScene_id().getScene_id();
//					/**
//					 * 把最新数据按场景分类
//					 */
//					if(map.get(sceneId)==null){
//						List<Gm_Latestdata> gm_Latestdatas = new ArrayList<Gm_Latestdata>();
//						gm_Latestdatas.add(gm_Latestdata);
//						map.put(gm_Latestdata.getCh_id().getScene_id().getScene_id(), gm_Latestdatas);
//						sceneMap.put(gm_Latestdata.getCh_id().getScene_id().getScene_id(), gm_Latestdata.getCh_id().getScene_id().getScene_name());
//					}else{
//						map.get(sceneId).add(gm_Latestdata);
//					}
//				}
//				/**
//				 * key：场景id
//				 * valu：map：key：recordTime（采集时间）value：时间值
//				 * 			 key：reportTime （上报时间）valu：时间值
//				 * 距离现在时间最近的
//				 */
//				Map<String, Map<String, String>> dateMap = new HashMap<String, Map<String,String>>();
//				/**
//				 * key：场景id
//				 * value：Map
//				 * 	key：通道编号,value：平均值，保留两位小数。
//				 */
//				Map<String, Map<String, String>> sceneAvgChtypeMap = new HashMap<String, Map<String,String>>();
//				/**
//				 * 循环按场景分类的map,并且如果场景下有类型通道为一样的通道，求平均值
//				 */
//				for(Map.Entry<String, List<Gm_Latestdata>> entry:map.entrySet()){
//					/**
//					 * 场景id
//					 */
//					String sceneKey = entry.getKey();
//					
//					Map<String, Double> doubleMap = new HashMap<String, Double>();
//					Map<String, Integer> numMap = new HashMap<String, Integer>();
//					Map<String, String> avgMap = new HashMap<String, String>();
//					
//					String recordTime = "";
//					String reportTime = "";
//					Date recordDate = null;
//					Date reportDate = null;
//					List<Gm_Latestdata> latestdatasList = entry.getValue();
//					for (Gm_Latestdata gm_Latestdata : latestdatasList) {
//						Date record_time = gm_Latestdata.getHida_record_time();
//						Date report_time = gm_Latestdata.getHida_reportTime();
//						if(recordDate == null && record_time!=null){
//							recordDate = record_time;
//							recordTime = DateUtil.formatDate(recordDate, "yyyy-MM-dd HH:mm:ss");
//						}else{
//							if(record_time!=null){
//								if(DateUtil.dateCompare(record_time, recordDate)){
//									recordDate = record_time;
//									recordTime = DateUtil.formatDate(recordDate, "yyyy-MM-dd HH:mm:ss");
//								}
//							}
//						}
//						if(reportDate == null && report_time!=null){
//							reportDate = report_time;
//							reportTime = DateUtil.formatDate(reportDate, "yyyy-MM-dd HH:mm:ss");
//						}else{
//							if(report_time!=null){
//								if(DateUtil.dateCompare(report_time, reportDate)){
//									reportDate = report_time;
//									reportTime = DateUtil.formatDate(reportDate, "yyyy-MM-dd HH:mm:ss");
//								}
//							}
//						}
//						String chtypeNo = gm_Latestdata.getCh_id().getChtype_id().getChtype_no();
//						Double corrValue = gm_Latestdata.getHida_corrValue();
//						if(corrValue!=null){
//							if(doubleMap.get(chtypeNo) == null){
//								doubleMap.put(chtypeNo, corrValue);
//								numMap.put(chtypeNo, 1);
//							}else{
//								if(corrValue!=null){
//									Double d = doubleMap.get(chtypeNo);
//									Double sum = corrValue+d;
//									doubleMap.put(chtypeNo, sum);
//									numMap.put(chtypeNo, numMap.get(chtypeNo)+1);
//								}
//							}
//						}
//					}
//					
//					Map<String, String> time = new HashMap<String, String>();
//					if(StringUtils.hasText(recordTime)){
//						time.put("recordTime", recordTime);
//					}else{
//						time.put("recordTime", "-");
//					}
//					if(StringUtils.hasText(reportTime)){
//						time.put("reportTime", reportTime);
//					}else{
//						time.put("reportTime", "-");
//					}
//					dateMap.put(sceneKey, time);
//					
//					for(Map.Entry<String, Double> entryDouble:doubleMap.entrySet()){
//						String keyChtypeNo = entryDouble.getKey();
//						Double val = entryDouble.getValue();
//						double avgValue = val/numMap.get(keyChtypeNo);
//						avgMap.put(keyChtypeNo, DecimalUtils.subDecimal(avgValue));
//					}
//					sceneAvgChtypeMap.put(sceneKey, avgMap);
//				}
//				Map<String, List<String>> resultMap = new LinkedHashMap<String, List<String>>();
//				List<String> menuList = new ArrayList<String>();
//				Set<String> menuSet = new LinkedHashSet<String>();
//				if(latestdatas.size() > 0){
//					resultMap.put("场景名", menuList);
//				}
//				for(Map.Entry<String, Map<String, String>> la:sceneAvgChtypeMap.entrySet()){
//					String sceneId = la.getKey();
//					Map<String, String> mapValue = la.getValue();
//					List<String> valueList = new ArrayList<String>();
//					for(Map.Entry<String, String> entryChtype:chtypeMap.entrySet()){
//						String chtypeNo = entryChtype.getKey();
//						if(mapValue.get(chtypeNo)!=null){
//							valueList.add(mapValue.get(chtypeNo)+" "+unitMap.get(chtypeNo));
//						}else{
//							valueList.add("-");
//						}
//						String chtypeName = entryChtype.getValue();
//						if(menuSet.add(chtypeNo)){
//							menuList.add(chtypeName);
//						}
//					}
//					
//					Map<String, String> timeMap = dateMap.get(sceneId);
//					valueList.add(timeMap.get("recordTime"));//存入采集时间
//					//valueList.add(timeMap.get("reportTime"));//存入上报时间
//					resultMap.put(sceneMap.get(sceneId), valueList);
//				}
////				for (String string : menuSet) {
////					menuList.add(string);
////				}
//				menuList.add("采集时间");
//				//menuList.add("上报时间");
//				List<Map<String, String>> jsons = new ArrayList<Map<String,String>>();
//				for(Map.Entry<String, List<String>> ll:resultMap.entrySet()){
//					Map<String, String> jsonMap = new HashMap<String, String>();
//					jsonMap.put("sceneName", ll.getKey());
//					List<String> mapList = ll.getValue();
//					String value = mapList.toString();
//					value = value.substring(1);
//					value = value.substring(0,value.length()-1);
//					jsonMap.put("value", value);
//					jsons.add(jsonMap);
//				}
//				String json = new Gson().toJson(jsons);
//				out.print(json);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	/**
	 * 数据汇总
	 * @return MengQL
	 */
	public String dataCollect() {
		try {
			PrintWriter out = Struts2Utils.getResponse().getWriter();
			if (StringUtils.hasText(scene_id)) {
				String[] sceneIdArr = scene_id.split(",");
				/**
				 * [场景id[0]，场景名[1]，场景编号[2]，通道类型编号[3]，通道类型名[4]，通道类型单位[5]，最新数据采集值[
				 * 6]，最新数据采集时间[7]] 以通道编号排序
				 */
				List<Object[]> list = gm_LatestdataService
						.findByScene(sceneIdArr);
				Map<String, List<Object[]>> sceneLatesMap = new LinkedHashMap<String, List<Object[]>>();
				Map<String, String> sceneMap = new LinkedHashMap<String, String>();
				Map<String, String> chtypeMap = new LinkedHashMap<String, String>();
				Map<String, String> unitMap = new LinkedHashMap<String, String>();
				/**
				 * 根据场景id分组
				 */
				for (Object[] objects : list) {
					String sceneId = objects[0].toString();
					chtypeMap.put(objects[3] + "", objects[4] + "");
					unitMap.put(objects[3] + "", objects[5] + "");
					if (sceneLatesMap.get(sceneId) == null) {
						List<Object[]> objs = new ArrayList<Object[]>();
						objs.add(objects);
						sceneLatesMap.put(sceneId, objs);
						sceneMap.put(sceneId, objects[1] + "");
					} else {
						sceneLatesMap.get(sceneId).add(objects);
					}
				}

				/**
				 * 循环按场景分组的map，求平均值
				 */
				Map<String, String> dateMap = new HashMap<String, String>();
				Map<String, Map<String, String>> sceneAvgChtypeMap = new LinkedHashMap<String, Map<String, String>>();
				for (Map.Entry<String, List<Object[]>> entry : sceneLatesMap
						.entrySet()) {
					String sceneId = entry.getKey();
					List<Object[]> objects = entry.getValue();
					Map<String, Double> sumMap = new HashMap<String, Double>();
					Map<String, Integer> numMap = new HashMap<String, Integer>();
					Map<String, String> avgMap = new HashMap<String, String>();
					Date recordDate = null;
					for (Object[] objs : objects) {
						Date _recordDate = (Date) objs[7];
						if (_recordDate != null) {
							if (recordDate == null) {
								recordDate = _recordDate;
							} else {
								if (DateUtil.dateCompare(_recordDate,
										recordDate)) {
									recordDate = _recordDate;
								}
							}
						}
						if (objs[6] != null) {
							String chtypeNo = objs[3] + "";
							Double corrValue = Double.parseDouble(objs[6] + "");
							if (sumMap.get(chtypeNo) == null) {
								sumMap.put(chtypeNo, corrValue);
								numMap.put(chtypeNo, 1);
							} else {
								double sum = sumMap.get(chtypeNo) + corrValue;
								sumMap.put(chtypeNo, sum);
								numMap.put(chtypeNo, numMap.get(chtypeNo) + 1);
							}
						}
					}

					/**
					 * 取场景下距离当前时间最近的采集时间
					 */
					if (recordDate != null) {
						dateMap.put(sceneId, DateUtil.formatDate(recordDate,"yyyy-MM-dd HH:mm:ss"));
					} else {
						dateMap.put(sceneId, "-");
					}

					/**
					 * 计算平均值
					 */
					for (Map.Entry<String, Double> entryDouble : sumMap
							.entrySet()) {
						String chtypeNo = entryDouble.getKey();
						double value = entryDouble.getValue();
						double avg = value / numMap.get(chtypeNo);
						avgMap.put(chtypeNo, DecimalUtils.subDecimal(avg));
					}
					sceneAvgChtypeMap.put(sceneId, avgMap);
				}

				Map<String, List<String>> resultMap = new LinkedHashMap<String, List<String>>();
				List<String> menuList = new ArrayList<String>();
				Set<String> menuSet = new LinkedHashSet<String>();
				if (list.size() > 0) {
					resultMap.put("场景名", menuList);
				}
				for (Map.Entry<String, Map<String, String>> la : sceneAvgChtypeMap
						.entrySet()) {
					String sceneId = la.getKey();
					Map<String, String> mapValue = la.getValue();
					List<String> valueList = new ArrayList<String>();
					for (Map.Entry<String, String> entryChtype : chtypeMap
							.entrySet()) {
						String chtypeNo = entryChtype.getKey();
						if (mapValue.get(chtypeNo) != null) {
							valueList.add(mapValue.get(chtypeNo) + " "
									+ unitMap.get(chtypeNo));
						} else {
							valueList.add("-");
						}
						String chtypeName = entryChtype.getValue();
						if (menuSet.add(chtypeNo)) {
							menuList.add(chtypeName);
						}
					}
					valueList.add(dateMap.get(sceneId));// 存入采集时间
					resultMap.put(sceneMap.get(sceneId), valueList);
				}
				menuList.add("采集时间");
				List<Map<String, String>> jsons = new ArrayList<Map<String, String>>();
				for (Map.Entry<String, List<String>> ll : resultMap.entrySet()) {
					Map<String, String> jsonMap = new HashMap<String, String>();
					jsonMap.put("sceneName", ll.getKey());
					List<String> mapList = ll.getValue();
					String value = mapList.toString();
					value = value.substring(1);
					value = value.substring(0, value.length() - 1);
					jsonMap.put("value", value);
					jsons.add(jsonMap);
				}
				String json = new Gson().toJson(jsons);
				out.print(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
//分隔线----------------------------------------------------------------------------------------------------------------
	@Autowired Op_ChtypeOperateService op_ChtypeOperateService;
	@Autowired Op_UserInfo_SceneService op_UserInfo_SceneService;
	@Autowired Op_SceneService op_SceneService;
	@Autowired Op_ChannelTypeService op_ChannelTypeService;
	@Autowired Op_scene_gtypeService scene_gtypeService;
	@Autowired Gm_LatestdataService gm_LatestdataService;
	
	private String scene_id; //数据汇总 输入场景的ID
	private String user_id; //数据汇总 登陆用户ID
	private Op_ChtypeOperate op_ChtypeOperate = new Op_ChtypeOperate(); 
	private List<Op_ChannelType> op_ChannelType_list = new ArrayList<Op_ChannelType>(); //外键
	
	private Page<Op_ChtypeOperate> page; //分页查询
	private String hid_condition; //分页查询
	private String hid_value; //分页查询
	
	
	public String getScene_id() {
		return scene_id;
	}

	public void setScene_id(String scene_id) {
		this.scene_id = scene_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public Op_ChtypeOperate getOp_ChtypeOperate() {
		return op_ChtypeOperate;
	}

	public void setOp_ChtypeOperate(Op_ChtypeOperate op_ChtypeOperate) {
		this.op_ChtypeOperate = op_ChtypeOperate;
	}

	public Page<Op_ChtypeOperate> getPage() {
		return page;
	}

	public void setPage(Page<Op_ChtypeOperate> page) {
		this.page = page;
	}

	public String getHid_condition() {
		return hid_condition;
	}

	public void setHid_condition(String hid_condition) {
		this.hid_condition = hid_condition;
	}

	public String getHid_value() {
		return hid_value;
	}

	public void setHid_value(String hid_value) {
		this.hid_value = hid_value;
	}

	public List<Op_ChannelType> getOp_ChannelType_list() {
		return op_ChannelType_list;
	}

	public void setOp_ChannelType_list(List<Op_ChannelType> op_ChannelType_list) {
		this.op_ChannelType_list = op_ChannelType_list;
	}
}
