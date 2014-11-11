package org.unism.gm.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.unism.gm.action.form.DevFaultTypeForm;
import org.unism.gm.action.form.DevstsForm;
import org.unism.gm.domain.Gm_Channel;
import org.unism.gm.domain.Gm_DevChannel;
import org.unism.gm.domain.Gm_DevNet;
import org.unism.gm.domain.Gm_Device;
import org.unism.gm.domain.Gm_Devsts;
import org.unism.gm.domain.Gm_Latestdata;
import org.unism.gm.service.Gm_ChannelService;
import org.unism.gm.service.Gm_DevChannelService;
import org.unism.gm.service.Gm_DevNetService;
import org.unism.gm.service.Gm_DeviceService;
import org.unism.gm.service.Gm_DevstsService;
import org.unism.gm.service.Gm_LatestdataService;
import org.unism.gm.util.DateTool;
import org.unism.op.service.Op_SceneService;
import org.unism.op.service.Op_UserInfo_SceneService;
import org.unism.util.DateUtil;
import org.unism.util.StaticDataManage;
import org.wangzz.core.web.struts2.Struts2Utils;

import com.google.gson.Gson;

public class Gm_DevstsAction {
	
	/**
	 * 到智能设备状态
	 * @return
	 * @author Wang_Yuliang
	 */
	public String toGm_Devsts(){
		return "gm_devsts";
	}
	
	/**
	 * 分页查询
	 * @return json
	 * 		{
	 * 			count:'?',
	 * 			no:'?',
	 * 			size:'12',
	 * 			result:
	 * 				[
	 * 					{
	 * 						dev_name:'?',
	 * 						net_addr:'?',
	 * 						dest_regSts:'?',
	 * 						dest_lastCommTime:'?',
	 * 						dest_curTime:'?',
	 * 						dest_timeSts:'?',
	 * 						dest_workSts:'?',
	 * 						dest_sigStg:'?',
	 * 						dest_commQuaily:'?',
	 * 						dest_linkSts:'?',
	 * 						st_commCyc:'?',
	 * 						dest_repCyc:'?',
	 * 						dest_vol:'?',
	 * 						dest_resetNum:'?',
	 * 						dest_recData:'?'
	 * 					},
	 * 					...
	 * 				]
	 * 		}
	 * @author Wang_Yuliang
	 */
	public String page(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			List<String> scene_id_arr = new ArrayList<String>();
			scene_id_arr.add(this.scene_id);
			String user_id = (String)session.getAttribute("userid");			
			List<String> scene_id_list = this.op_UserInfo_SceneService.findScene_idByUser_id(user_id);
			scene_id_arr = this.op_SceneService.findSceneTree_wangyuliang(scene_id_arr, scene_id_list);
			if(this.searchOrTree.equals("search")){
				String json = this.gm_DevstsService.page_search(scene_id_list,hid_condition,hid_value,no,size);
				//System.out.println("智能设备状态"+json);
				//System.out.print("jj"+json);
				out.print(json);	
				return null;
			}else{
				String json = this.gm_DevstsService.page_wangzz(scene_id_arr,no,size);
				//System.out.println("智能设备状态"+json);
				out.print(json);	
				return null;
			}					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("{count:0,no:1,size:12,result:[]}");
			}catch(Exception ex){ex.printStackTrace();}	
		}
		return null;
	}
	
	public String findGm_LatestdataByAddr(){
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			Gm_DevNet gm_DevNet = this.gm_DevNetService.findByNet_addr(this.net_addr);
			if(gm_DevNet != null){
				List<Gm_DevChannel> devChannels = gm_DevChannelService.findBydDev_addr(net_addr);
				List<String> chIds = new ArrayList<String>();
				for (Gm_DevChannel gm_DevChannel : devChannels) {
					chIds.add(gm_DevChannel.getCh_id().getCh_id());
				}
				List<Gm_Latestdata> latestdatas = gm_LatestdataService.findByChIds(chIds);
				List<Map<String, String>> listmap = new ArrayList<Map<String,String>>();
				int i = 0;
				Date recordTime = null;
				Date reportTime = null;
				Map<String, String> mapMenu = new HashMap<String, String>();
				for (Gm_Latestdata gm_Latestdata : latestdatas) {
					Map<String, String> map = new HashMap<String, String>();
					if(i == 0){
						mapMenu.put("flag", "menu");
						mapMenu.put("devName", gm_DevNet.getDev_id().getDev_name());
						mapMenu.put("devAddr", net_addr);
						listmap.add(mapMenu);
					}
					if(gm_Latestdata.getHida_record_time()!=null){
						if(recordTime == null){
							recordTime = gm_Latestdata.getHida_record_time();
							mapMenu.put("recordTime", DateTool.getDateTime(recordTime));
						}else{
							if(gm_Latestdata.getHida_record_time().getTime() > recordTime.getTime()){
								recordTime = gm_Latestdata.getHida_record_time();
								mapMenu.put("recordTime", DateTool.getDateTime(recordTime));
							}
						}
					}
					if(gm_Latestdata.getHida_reportTime()!=null){
						if(reportTime == null){
							mapMenu.put("reportTime", DateTool.getDateTime(gm_Latestdata.getHida_reportTime()));
						}else{
							if(gm_Latestdata.getHida_reportTime().getTime() > reportTime.getTime()){
								mapMenu.put("reportTime", DateTool.getDateTime(gm_Latestdata.getHida_reportTime()));
							}
						}
					}
					
						map.put("flag", "content");
						map.put("hidaId", gm_Latestdata.getHida_id());
						map.put("channelNo", gm_Latestdata.getCh_id().getCh_no());
						map.put("channelName", gm_Latestdata.getCh_id().getCh_name());
						map.put("deviceNo", gm_Latestdata.getCh_id().getDev_collectId().getDev_no());
						map.put("chChlNo", gm_Latestdata.getCh_id().getCh_chlNo()+"");
						map.put("corrUnit", gm_Latestdata.getCh_id().getCh_corrUnit()==null?"":gm_Latestdata.getCh_id().getCh_corrUnit());
						map.put("corrValue", gm_Latestdata.getHida_corrValue()==null?"":gm_Latestdata.getHida_corrValue()+"");
						map.put("origUnit", gm_Latestdata.getCh_id().getCh_corrUnit()==null?"":gm_Latestdata.getCh_id().getCh_corrUnit());
						map.put("origValue", gm_Latestdata.getHida_origValue()==null?"":gm_Latestdata.getHida_origValue()+"");
						listmap.add(map);
					i++;
				}
				Gson gson = new Gson();
				String json = gson.toJson(listmap);
				out.print(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 保存
	 * @return
	 * @author MengQL
	 */
	public String saveLatestdata(){
		HttpServletResponse response = Struts2Utils.getResponse();
		PrintWriter out = null;
		try {
			out = response.getWriter();
			response.setContentType("text/html; charset=utf-8");
			List<Double> valuList = new ArrayList<Double>(Arrays.asList(hidaValue));
			List<String> idList = new ArrayList<String>(Arrays.asList(hidaId));
			Date record = DateUtil.parseDate(dateTime, "yyyy-MM-dd HH:mm:ss");
			for (int i = 0; i < idList.size(); i++) {
				Gm_Latestdata latestdata = gm_LatestdataService.findById(idList.get(i));
				if(latestdata!=null){
					latestdata.setHida_record_time(record);
					latestdata.setHida_corrValue(valuList.get(i));
				}
				gm_LatestdataService.update(latestdata);
			}
			out.print(true);
		} catch (Exception e) {
			e.printStackTrace();
			out.print(false);
		}
		return null;
	}

	/**
	 * 通过设备地址查ch_id，ch_name并转换为json格式
	 * 异步传回页面
	 * @return
	 */
	public String findChannelByAddr(){
		try
		{
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			List<Object[]> ch_idAndch_nameList =  this.gm_DevChannelService.findCh_idAndCh_nameByAddr(net_addr);
			List<Map<String, String>> jsonList = new ArrayList<Map<String,String>>();
			for (Object[] objects : ch_idAndch_nameList)
			{
				Map<String, String> map = new HashMap<String, String>();
				map.put("ch_id", objects[0]==null?"null":objects[0].toString());
				map.put("ch_name", objects[1]==null?"null":objects[1].toString());
				map.put("scene_name", objects[2]==null?"null":objects[2].toString());
				map.put("chtype_no", objects[3]==null? "null" : objects[3].toString());
				map.put("chtype_displayName", objects[4]==null?"null":objects[4].toString());
				jsonList.add(map);
			}
			Gson gson = new Gson();
			String json = gson.toJson(jsonList);
			//System.out.println(json);
			out.print(json);
		} catch (Exception e)
		{
			// TODO: handle exception
		}
		return null;
	}
	
	public String findDevByAddr(){
		try {
			PrintWriter out = Struts2Utils.getResponse().getWriter();
			Gm_DevNet gmDevNet = gm_DevNetService.findByNet_addr(net_addr);
			List<Gm_DevNet> devNets = gm_DevNetService.findByNet_pid(gmDevNet.getNet_pid());
			devNets.add(gmDevNet);
			List<Map<String, String>> jsons = new ArrayList<Map<String,String>>();
			for (Gm_DevNet devNet : devNets) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("devId", devNet.getDev_id().getDev_id());
				map.put("devNo", devNet.getDev_id().getDev_no());
				map.put("devName", devNet.getDev_id().getDev_name());
				map.put("sceneName", devNet.getDev_id().getScene_id().getScene_name());
				jsons.add(map);
			}
			String json = new Gson().toJson(jsons);
			out.print(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String findChannelByScene(){
		try {
			PrintWriter out = Struts2Utils.getResponse().getWriter();
			List<Gm_Channel> channels = gmChannelService.findByScene_id(scene_id);
			List<Map<String, String>> jsons = new ArrayList<Map<String,String>>();
			for (Gm_Channel gmChannel : channels) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("ch_id", gmChannel.getCh_id());
				map.put("ch_name", gmChannel.getCh_name());
				map.put("scene_name", gmChannel.getScene_id().getScene_name());
				map.put("chtype_no", gmChannel.getChtype_id().getChtype_no());
				map.put("chtype_displayName", gmChannel.getChtype_id().getChtype_displayName());
				jsons.add(map);
			}
			String json = new Gson().toJson(jsons);
			out.print(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String findDeviceByScene(){
		try {
			PrintWriter out = Struts2Utils.getResponse().getWriter();
			String user_id = Struts2Utils.getSession().getAttribute("userid")+"";
			//与用户相关的场景id
			List<String> scene_id_list = this.op_UserInfo_SceneService.findScene_idByUser_id(user_id);
			List<String> scene_id_arr = new ArrayList<String>();
			scene_id_arr.add(scene_id);
			scene_id_arr = this.op_SceneService.findSceneTree_wangyuliang(scene_id_arr, scene_id_list);
			
			List<Gm_Device> gmDevices = gm_DeviceService.findByScene(scene_id_arr);
			List<Map<String, String>> jsons = new ArrayList<Map<String,String>>();
			for (Gm_Device gmDevice : gmDevices) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("devId", gmDevice.getDev_id());
				map.put("devNo", gmDevice.getDev_no());
				map.put("devName", gmDevice.getDev_name());
				map.put("sceneName", gmDevice.getScene_id().getScene_name());
				jsons.add(map);
			}
			String json = new Gson().toJson(jsons);
			out.print(json);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 到设置扩展属性
	 * @return
	 * @author Wang_Yuliang
	 */
	public String toExtform(){
		gm_Devsts = gm_DevstsService.findByDev_id(gm_Devsts.getDev_id().getDev_id());
		return "extform";
	}
	
	/**
	 * 设置扩展属性
	 * @return
	 * @author Wang_Yuliang
	 */
	public String extup(){
		HttpServletResponse response = Struts2Utils.getResponse();
		try {
			PrintWriter out = response.getWriter();
			Gm_Devsts gm_Devsts_new = this.gm_DevstsService.findById(dest_id);
			gm_Devsts_new.setDest_repCyc(gm_Devsts.getDest_repCyc());
			gm_Devsts_new.setDest_collectCyc(gm_Devsts.getDest_collectCyc());
			gm_Devsts_new.setDest_storageCyc(gm_Devsts.getDest_storageCyc());
			gm_Devsts_new.setDest_ifClearData(gm_Devsts.getDest_ifClearData());
			gm_Devsts_new.setDest_ifReset(gm_Devsts.getDest_ifReset());
			gm_Devsts_new.setDest_ifOnlineTmp(gm_Devsts.getDest_ifOnlineTmp());
			String ask = gm_Devsts.getDest_askForAddr();
			if(StringUtils.hasText(ask)){
				gm_Devsts_new.setDest_askForAddr(ask);
			}else{
				gm_Devsts_new.setDest_askForAddr(null);
			}
			gm_DevstsService.update(gm_Devsts_new);
			out.print("true");
		} catch (Exception e) {
			e.printStackTrace();			
			try {
				PrintWriter out = response.getWriter();
				out.print("false");
			} catch (IOException e1) {e1.printStackTrace();}			
		}
		return null;
	}
	
	public String checkAskForAddr(){
		try {
			PrintWriter writer = Struts2Utils.getResponse().getWriter();
			String b = String.valueOf(gm_DevstsService.isExist("dev_addr", askForAddr, oldAskForAddr));
			writer.print(b);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 智能设备状态分类初始化页面
	 * @return 
	 * weixiaohua
	 * date 2012-06-25
	 */
	public String findAllDest_WorkSts(){
		return "gm_devsts_worksts";
	}
	
	/**
	 * 智能设备状态分类
	 * @return 
	 * weixiaohua
	 * date 2012-06-25
	 */
	public String findAllDest_WorkStsList(){
		Map<String, DevstsForm> map = new LinkedHashMap<String, DevstsForm>();
		String userId=Struts2Utils.getSession().getAttribute("userid").toString();
		List<String> scene_id_arr = new ArrayList<String>();
		if(scene_id!=null){
			scene_id_arr.add(this.scene_id);				
			List<String> scene_id_list = this.op_UserInfo_SceneService.findScene_idByUser_id(userId);
			scene_id_arr = this.op_SceneService.findSceneTree_wangyuliang(scene_id_arr, scene_id_list);
		}
		String scene_id_selectAll="";
		for(String sceid : scene_id_arr) {
			scene_id_selectAll=scene_id_selectAll+sceid+"','";
		}
		if(scene_id_selectAll.length()>0){
			scene_id_selectAll=scene_id_selectAll.substring(0, scene_id_selectAll.length()-3);
		}
		
		List<Object[] > dest_WorkStsList=this.gm_DevstsService.findDest_WorkStsListByUserIdAndDevaddr(userId,hid_condition,hid_value,scene_id_selectAll);
		for(Object[] str : dest_WorkStsList) {
			String sceneId=str[0].toString();
			String scene_name=str[1].toString();
			Integer dest_workSts=Integer.parseInt(str[2].toString());
			String strDest_workSts=StaticDataManage.getDest_workSts(dest_workSts);
			Object obj4=str[3];
			String dest_workStsInfo="";
			if(obj4!=null){
				dest_workStsInfo=str[3].toString();
			}		
			
			DevstsForm devstsForm=map.get(sceneId);
			if(devstsForm==null || "".equals(devstsForm)){//如果map中没有此场景
				devstsForm =new DevstsForm();
				devstsForm.setScene_name(scene_name);
				devstsForm.setDest_workSts(strDest_workSts);
				devstsForm.setDest_workStsInfo(dest_workStsInfo);
				map.put(sceneId, devstsForm);
			}else{
				devstsForm.setScene_name(scene_name);
				devstsForm.setDest_workSts(strDest_workSts);
				devstsForm.setDest_workStsInfo(dest_workStsInfo);
				map.put(sceneId, devstsForm);
			}
		}//for结束符
		Struts2Utils.getRequest().setAttribute("map", map);
		return "gm_devsts_worksts";
	}
	public String toDevState(){
		gm_Devsts = gm_DevstsService.findByDev_id(gm_Devsts.getDev_id().getDev_id());
		int key = gm_Devsts.getDest_workSts();
		String maintenanceOperation = StaticDataManage.getMaintenanceOperationMessage().get(key);
		Struts2Utils.getRequest().setAttribute("maintenanceOperation", maintenanceOperation);
		return "devState";
	}
//分割线-------------------------------------------------------------------------------------------------------------	
	@Autowired Gm_DevstsService gm_DevstsService;
	@Autowired Gm_DeviceService gm_DeviceService;
	@Autowired Op_SceneService op_SceneService;
	@Autowired Op_UserInfo_SceneService op_UserInfo_SceneService;
	@Autowired Gm_DevNetService gm_DevNetService;
	@Autowired Gm_LatestdataService gm_LatestdataService;
	@Autowired Gm_DevChannelService gm_DevChannelService;
	@Autowired Gm_ChannelService gmChannelService;
	
	
	private String scene_id; //分页查询 输入参数场景ID
	private Integer no = 1; //分页参数
	private Integer size = 12; //分页参数
	private String net_addr;
	private String hid_condition;//查询的条件
    private String hid_value;//查询的值
    private String searchOrTree; //查询类别
    private Gm_Devsts gm_Devsts = new Gm_Devsts();
	private String dest_id;
	
	private String askForAddr;
	private String oldAskForAddr;
	
	private Double[] hidaValue;
	private String[] hidaId;
	private String dateTime;
    
	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	public Double[] getHidaValue() {
		return hidaValue;
	}

	public void setHidaValue(Double[] hidaValue) {
		this.hidaValue = hidaValue;
	}

	public String[] getHidaId() {
		return hidaId;
	}

	public void setHidaId(String[] hidaId) {
		this.hidaId = hidaId;
	}

	public String getAskForAddr() {
		return askForAddr;
	}

	public void setAskForAddr(String askForAddr) {
		this.askForAddr = askForAddr;
	}

	public String getOldAskForAddr() {
		return oldAskForAddr;
	}

	public void setOldAskForAddr(String oldAskForAddr) {
		this.oldAskForAddr = oldAskForAddr;
	}

	public String getDest_id() {
		return dest_id;
	}

	public void setDest_id(String dest_id) {
		this.dest_id = dest_id;
	}

	public Gm_Devsts getGm_Devsts() {
		return gm_Devsts;
	}

	public void setGm_Devsts(Gm_Devsts gm_Devsts) {
		this.gm_Devsts = gm_Devsts;
	}

	public String getSearchOrTree() {
		return searchOrTree;
	}

	public void setSearchOrTree(String searchOrTree) {
		this.searchOrTree = searchOrTree;
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

	public Integer getNo() {
		return no;
	}
	public void setNo(Integer no) {
		this.no = no;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public String getScene_id() {
		return scene_id;
	}
	public void setScene_id(String scene_id) {
		this.scene_id = scene_id;
	}

	public String getNet_addr()
	{
		return net_addr;
	}

	public void setNet_addr(String net_addr)
	{
		this.net_addr = net_addr;
	}
	
}
