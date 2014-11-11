package org.unism.pro.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.unism.gm.domain.Gm_DevCtrl;
import org.unism.gm.service.Gm_ChannelService;
import org.unism.gm.service.Gm_DevCtrlService;
import org.unism.gm.service.Gm_LatestdataService;
import org.unism.op.domain.Op_Scene;
import org.unism.op.domain.Op_UserInfo;
import org.unism.op.service.Op_ChannelTypeService;
import org.unism.op.service.Op_SceneService;
import org.unism.op.service.Op_UserInfo_SceneService;
import org.unism.pro.domain.Pro_Fisheries;
import org.unism.pro.service.Pro_FisheriesService;
import org.unism.pro.service.Pro_mapService;
import org.wangzz.core.orm.Page;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.web.struts2.Struts2Utils;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("unchecked")
public class Pro_FisheriesAction extends ActionSupport {

	private static final long serialVersionUID = -3896254648650696183L;

	//判断该场景是否配置
	public String edit() {	
		if(sceneId!=null){
			fisheries = pro_FisheriesService.findByScene_id(sceneId);			
			if(fisheries == null) {
				fisheries = new Pro_Fisheries();
				Op_Scene scene = this.op_SceneService.findById(sceneId);
				fisheries.setFi_userName(scene.getScene_creater());
				fisheries.setFi_pondName(scene.getScene_name());
				fisheries.setScene(scene);
			}
		}
		return "edit";
	}
	
	/**
	 * 场景检测 0518 养殖池信息 
	 * @return
	 * @author Wang_Yuliang
	 */
	public String findBySceneId(){
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			Search search = new Search();
			Filter filter = Filter.equal("scene.scene_id", sceneId);
			search.addFilter(filter);
			List<Pro_Fisheries> pro_Fisheries_list = this.pro_FisheriesService.search(search);
			if(pro_Fisheries_list.size()>0){
				Pro_Fisheries pro_Fisheries = pro_Fisheries_list.get(0);
				String json = "{";
				String scene_neme = "";
				Op_Scene op_Scene = pro_Fisheries.getScene();
				if(op_Scene!=null){
					scene_neme = "scene_name:'"+op_Scene.getScene_name()+"',";
				}
				json += scene_neme;
				json += "fi_id:'"+pro_Fisheries.getFi_id()+"',";
				json += "fi_pondNo:'"+pro_Fisheries.getFi_pondNo()+"',";
				json += "fi_pondName:'"+pro_Fisheries.getFi_pondName()+"',";
				json += "fi_userName:'"+pro_Fisheries.getFi_userName()+"',";
				json += "fi_area:'"+pro_Fisheries.getFi_area()+"',";
				json += "fi_depth:'"+pro_Fisheries.getFi_depth()+"',";

				json += "fi_aquaticType:'"+pro_Fisheries.getFi_aquaticType()+"',";
				json += "fi_cultureObject:'"+pro_Fisheries.getFi_cultureObject()+"',";
				json += "fi_species:'"+pro_Fisheries.getFi_species()+"',";
				json += "fi_batch:'"+pro_Fisheries.getFi_batch()+"',";
				json += "fi_remainNum:'"+pro_Fisheries.getFi_remainNum()+"',";
				
				json += "fi_productionStage:'"+pro_Fisheries.getFi_productionStage()+"',";
				json += "fi_doyj:'"+pro_Fisheries.getFi_doyj()+"',";
				json += "fi_do:'"+pro_Fisheries.getFi_do()+"',";
				json += "fi_putTime:'"+pro_Fisheries.getFi_putTime_sub()+"',";
				json += "fi_phone:'"+pro_Fisheries.getFi_phone()+"'}";
				
				out.print(json);
			}else{
				out.print("null");
			}			
		}catch(Exception ex){
			ex.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("null");
			}catch(Exception exx){exx.printStackTrace();}	
		}			
		return null;
	}
	
	//添加和修改
	public String update() throws IOException {
		if(fisheries.getScene() != null) {
			if(StringUtils.hasText(fisheries.getScene().getScene_id())){
				Op_Scene scene = this.op_SceneService.findById(fisheries.getScene().getScene_id());
				scene.setScene_name(fisheries.getFi_pondName());
				this.op_SceneService.update(scene);
				
				List<Gm_DevCtrl> devCtrls = gm_DevCtrlService.findByScene_id(scene.getScene_id());
				if(!devCtrls.isEmpty()) {
					fisheries.setDect_id(devCtrls.get(0));
				}
			}
		}
		if(StringUtils.hasText(fisheries.getFi_id())) {
			pro_FisheriesService.update(fisheries);
		} else {
			pro_FisheriesService.save(fisheries);
		}
		addActionMessage("保存成功.");
		return "success";
	}
	//显示应用配置信息
	public String list() {		
		Op_UserInfo user = (Op_UserInfo)Struts2Utils.getSession().getAttribute("user");//从session里获取用户实体
		List<String> list = this.op_UserInfo_SceneService.findScene_idByUser_id(user.getUser_id());//获取场景ID集合
		Search search = new Search();
		Filter filter2 = Filter.or(Filter.equal("scene_gtype",1), Filter.equal("scene_gtype",201), Filter.equal("scene_gtype",202), Filter.equal("scene_gtype",203));
		Filter filter = Filter.and(Filter.equal("scene_state", 1), filter2 ,Filter.in("scene_id", list));//根据场景使用状态和是否在场景内查询场景
		
		if(this.hid_condition!=null && !this.hid_condition.equals("")){
			filter = Filter.and(Filter.equal("scene_state", 1),filter2,Filter.in("scene_id", list),Filter.like(this.hid_condition, this.hid_value));		
		}else{
			filter  = Filter.and(Filter.equal("scene_state", 1),filter2,Filter.in("scene_id", list));//根据场景使用状态和是否在场景内查询场景
		}
//		if(this.op_Scene!=null) {
//			if(this.op_Scene.getScene_pid() != null && !this.op_Scene.getScene_pid().equals("")&&this.op_Scene.getScene_rank()==0){//页面提交的场景父Id不为null
//				filter = Filter.and(Filter.equal("scene_state", 1),Filter.equal("scene_pid", this.op_Scene.getScene_pid()),Filter.in("scene_id", list),Filter.equal("scene_rank",0));
//			}
//		}
		search.addFilter(filter);
		this.page = this.op_SceneService.search(page, search);			
		return "list";
	}
	
	public String checkPro_Fisheries() throws IOException{
		HttpServletResponse response = Struts2Utils.getResponse();
		PrintWriter out = response.getWriter();
		Pro_Fisheries pf=this.pro_FisheriesService.findByfi_pondNoAndfi_phone(phone, pondNo);
		if(pf==null){			
			String json = "[['true']]";
			out.print(json);
		}else{
			String json = "[['false']]";
			out.print(json);
		}
		return null;
	}
	
	//地图
	public String map() throws IOException{
		HttpServletResponse response = Struts2Utils.getResponse();
		PrintWriter out = response.getWriter();
//		List mapList=this.pro_FisheriesService.findAllByMapScene();
		Pro_Fisheries pf=this.pro_FisheriesService.findByfi_pondNoAndfi_phone(phone, pondNo);
		if(pf==null){			
			String json = "[['true']]";
			out.print(json);
		}else{
			String json = "[['false']]";
			out.print(json);
		}
		return null;
	}
	
	
//	public String foundXML(){
//		HttpServletRequest request = Struts2Utils.getRequest();
//		
//		HttpSession session = Struts2Utils.getSession();
//		
//		
//		List<Pro_map> pro_mapList = this.pro_mapService.findAll();
//		
//		
//		Document document = DocumentHelper.createDocument();
//		Element markersElement = document.addElement("markers");
//		Element markerElement;
//		
//		for (Pro_map pro_map : pro_mapList)
//		{
//			Pro_Fisheries pro_Fisheries = this.pro_FisheriesService.findByScene_id(pro_map.getScene_id().getScene_id());
//			if(pro_Fisheries != null){
//				
//				markerElement = markersElement.addElement("marker");
//				markerElement.addAttribute("lng", pro_map.getMa_longitude().toString());
//				markerElement.addAttribute("lat", pro_map.getMa_latitude().toString());
//				if(pro_Fisheries.getFi_area() != null){
//					markerElement.addAttribute("name", pro_Fisheries.getFi_area()+"亩");
//				}else {
//					markerElement.addAttribute("name", "0亩");
//				}
//				markerElement.addAttribute("thumb", "http://joyagri.com/_yixing/thumb/04.jpg");
//				if(pro_Fisheries.getFi_cultureObject() != null){
//					markerElement.addAttribute("description", pro_Fisheries.getFi_cultureObject());
//					
//				}else {
//					markerElement.addAttribute("description", "暂无信息……");
//				}
//				if(pro_Fisheries.getFi_putTime_sub() != null){
//					markerElement.addAttribute("addtime", pro_Fisheries.getFi_putTime_sub());
//				}else {
//					markerElement.addAttribute("addtime", "未知……");
//				}
//				markerElement.addAttribute("alarm", "1");
//				markerElement.addAttribute("mapType", pro_map.getMap_type()+"");
//			}
//		}
//		
//		String path = session.getServletContext().getRealPath("/");
//		
//		WriteXML writexml = new WriteXML();
//		
//		int result = writexml.write(document, path);
//		
//			
//		return "map";
//	}
	
	public String mapFishInfo(){
		try {
			String json = "[";
//			int low = 0;
//			int normal = 0;
//			int good = 0;
//			String panelInfo = "溶氧正常：" + good + "\n\n溶氧偏低："+ normal + "\n\n严重缺氧："+ low + "\n\n";
			HttpSession session = Struts2Utils.getSession();
			PrintWriter out = Struts2Utils.getResponse().getWriter();
			Object userIdObj = session.getAttribute("userid");
			if(userIdObj != null) {
				String userId = userIdObj.toString();
				List<Object[]> sceneDataList = this.pro_FisheriesService.findLatestDataByUserId(userId);
				for(Object[] objs : sceneDataList) {
					String flag = "";
					Object objValue=objs[5];
					if(objValue==null||objValue==""){
						flag = "gray";//灰色
					}else{
						Double value = (Double)objs[5];
						if(value >= 5) {
							flag = "green";//绿色
						}
						if(value < 5 && value >= 3) {
							flag = "yellow";//黄色
						}
						if(value < 3) {
							flag = "red";//红色
						}
					}
					
					Date putDate = (objs[11] == null ? null : (Date)objs[11]);
					SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					String putDateStr = "";
					if(putDate != null)
						putDateStr = formatter.format(putDate);
					
					String areaStr = "";
					if(objs[6] != null)
						areaStr = objs[6].toString();
					String dect_state="";
					if(objs[12] != null){
						dect_state = objs[12].toString();
						if("0".equals(dect_state)){
							dect_state="未知";
						}
						if("1".equals(dect_state)){
							dect_state="<font color='green'>开</font>";
						}
						if("2".equals(dect_state)){
							dect_state="<font color='red'>关</font>";
						}
						if("3".equals(dect_state)){
							dect_state="<font color='red'>停</font>";
						}
					}
					String scene_image="";
					if(objs[13] != null){
						scene_image = "thumb/"+objs[13].toString();
					}else{
						scene_image="thumb/01.jpg";
					}

					json += "{\"id\":\"" + objs[0] + "\",\"lat\":\""+ objs[1] + "\",\"lng\":\""+ objs[2] + "\",\"no\":\""+ objs[3] 
					    + "\",\"gtype\":\""+ objs[4]+ "\",\"value\":\""+ objValue + "\",\"area\":\""+ areaStr + "\",\"pondName\":\""+ objs[7] 
					    + "\",\"userName\":\""+ objs[8] + "\",\"aquaticType\":\""+ objs[9] + "\",\"cultureObject\":\""+ objs[10] 
					    + "\",\"putTime\":\""+ putDateStr + "\",\"dectState\":\""+ dect_state + "\",\"thumb\":\""+ scene_image + "\",\"description\":\"\",\"flag\":\""+ flag + "\"},";
				}
				if(json.length() > 0){
					json = json.substring(0,json.length()-1);
				}
				json = json + "]";
				
				out.print(json);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//查询养殖池信息并按溶解氧值的大小正序排序
	public String findAllFishInfo(){
		try
		{
			HttpServletResponse response = Struts2Utils.getResponse();
			HttpSession session = Struts2Utils.getSession();
			PrintWriter out = response.getWriter();
			String json = "[";
			String userId = session.getAttribute("userid").toString();
			List<Object[]> fisheriesList = this.pro_FisheriesService.findAllFisheriesMsg(userId);
			for (Object[] objects : fisheriesList)
			{
				
				
				String bgColour = "";
				String values="";
				Object obj=objects[3];
				if(obj!=null){
					Double value = (Double)objects[3];
					if(value >= 5){
						bgColour = "green";
					}else if(value >=3 && value < 5){
						bgColour = "yellow";
					}else if(value < 3){
						bgColour = "red";
					}
					NumberFormat   nbf=NumberFormat.getInstance(); 
					nbf.setMinimumFractionDigits(2); 
					values   =   nbf.format(value);
				}
				
				Date putDate = (objects[9] == null ? null : (Date)objects[9]);
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String putDateStr = "";
				if(putDate != null)
					putDateStr = formatter.format(putDate);
				
				String areaStr = "";
				if(objects[4] != null)
					areaStr = objects[4].toString();
				String dect_state="";
				if(objects[10] != null){
					dect_state = objects[10].toString();
					if("0".equals(dect_state)){
						dect_state="未知";
					}
					if("1".equals(dect_state)){
						dect_state="<font color='green'>开</font>";
					}
					if("2".equals(dect_state)){
						dect_state="<font color='red'>关</font>";
					}
					if("3".equals(dect_state)){
						dect_state="<font color='red'>停</font>";
					}
				}
				
				json += "{\"id\":\"" + objects[0] + "\",\"no\":\""+ objects[1] 
				     + "\",\"gtype\":\""+ objects[2]+ "\",\"value\":\""+ values + "\",\"area\":\""+ areaStr + "\",\"pondName\":\""+ objects[5] 
				     + "\",\"userName\":\""+ objects[6] + "\",\"aquaticType\":\""+ objects[7] + "\",\"cultureObject\":\""+ objects[8] 
				     + "\",\"putTime\":\""+ putDateStr + "\",\"dectState\":\""+ dect_state + "\",\"thumb\":\""+ "thumb/01.jpg" + "\",\"description\":\"\",\"flag\":\""+ bgColour + "\"},";
			}
			if(json.length() > 1){
				json = json.substring(0,json.length()-1);
			}
			json = json+"]";
			out.print(json);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String mapWetherVidioInfo(){
		try {
			String json = "[";
			HttpSession session = Struts2Utils.getSession();
			PrintWriter out = Struts2Utils.getResponse().getWriter();
			Object userIdObj = session.getAttribute("userid");
			if(userIdObj != null) {
				String userId = userIdObj.toString();
				List<Object[]> sceneDataList = this.pro_FisheriesService.findWeatherVidioSceneByUserId(userId);
				for(Object[] objs : sceneDataList) {
					String scene_image="";
					if(objs[7] != null){
						scene_image = "thumb/"+objs[7].toString();
					}else{
						scene_image="thumb/01.jpg";
					}
					json += "{\"id\":\"" + objs[0] + "\",\"lat\":\""+ objs[1] + "\",\"lng\":\""+ objs[2] + "\",\"no\":\""+ objs[3] 
					     + "\",\"gtype\":\""+ objs[4] + "\",\"name\":\""+ objs[5] + "\",\"url\":\""+ objs[6] + "\",\"thumb\":\""+ scene_image + "\"},";
				}
				if(json.length() > 0){
					json = json.substring(0,json.length()-1);
				}
				json = json + "]";
				out.print(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String getWetherInfoBySceneId(){
		try {
			String json = "[";
			String sceneId = Struts2Utils.getRequest().getParameter("sceneId");
			PrintWriter out = Struts2Utils.getResponse().getWriter();
			List<Object[]> sceneDataList = this.pro_FisheriesService.findWeatherDataBySceneId(sceneId);
			for(Object[] objs : sceneDataList) {
				json += "{\"sceneId\":\"" + sceneId + "\",\"value\":\""+ objs[0] + "\",\"unit\":\""+ objs[1] + "\",\"typeNo\":\""+ objs[2] + "\"},";
			}
			if(json.length() > 0){
				json = json.substring(0,json.length()-1);
			}
			json = json + "]";
			out.print(json);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	/**
	 * 废弃
	 * @return
	 */
	
//	public String findMsg(){
//		try
//		{
//			HttpServletResponse response = Struts2Utils.getResponse();
//			PrintWriter out = response.getWriter();
//			String json = "[";
//			List<Op_Scene> op_SceneList = op_SceneService.findOp_Scene();
//			int red = 0;
//			int green = 0;
//			int yellow = 0;
//			for (Op_Scene op_Scene : op_SceneList)
//			{
//				if(op_Scene.getScene_gtype() == 1){
//					//通过通道编号查询通道类型
//					Op_ChannelType op_ChannelType = this.op_ChannelTypeService.findByChtype_no("DO10-O");
//					List<Pro_Fisheries> pro_FisheriesList = this.pro_FisheriesService.findAllByScene_id(op_Scene.getScene_id());
//					List<Gm_Channel> gm_Channel_list = this.gm_ChannelService.findBySceneIDandChtype_id(op_Scene.getScene_id(), op_ChannelType.getChtype_id());
//					for (Pro_Fisheries pro_Fisheries : pro_FisheriesList)
//					{
//						int gtype = op_Scene.getScene_gtype();
//						String type = "1.3";
//						if(gm_Channel_list != null && gm_Channel_list.size() > 0){
//							List<Gm_Latestdata> gm_LatestdataList = this.gm_LatestdataService.findLatestdataByCh_id(gm_Channel_list);
//							if(gm_LatestdataList.size() > 0 && gm_LatestdataList != null){
//									if(gm_LatestdataList.get(0).getHida_corrValue() > 5 || gm_LatestdataList.get(0).getHida_corrValue() == 5){
//										type="1.3";//green
//										green++;
//									}
//									if(gm_LatestdataList.get(0).getHida_corrValue() > 3 && gm_LatestdataList.get(0).getHida_corrValue() < 5){
//										type="1.2";//yellow
//										yellow++;
//									}
//									if(gm_LatestdataList.get(0).getHida_corrValue() < 3){
//										type="1.1";//red
//										red++;
//									}
//								}else {
//									type="1.3";
//							}
//						}
//						if(pro_Fisheries != null){
//							json +=  "{\"lng\":\"" + op_Scene.getScene_longitude() + "\",\"lat\":\""+ op_Scene.getScene_latitude() + "\",\"id\":\""+ op_Scene.getScene_id() + "\",\"no\":\""+ op_Scene.getScene_no() + "\",\"name\":\""+ pro_Fisheries.getFi_userName() + "-" + pro_Fisheries.getFi_area() + "亩" + "\",\"thumb\":\""+ "thumb/01.jpg" + "\",\"description\":\"" + "养殖品种：" + pro_Fisheries.getFi_cultureObject() + ",水草种类："+ pro_Fisheries.getFi_aquaticType() + ",投放时间：" + pro_Fisheries.getFi_putTime_sub() + ",手机号："+ pro_Fisheries.getFi_phone() + "\",\"gType\":\""+ type + "\",\"video\":\""+ "" +"\"},"; 
//						}else {
//							json +=  "{\"lng\":\"" + op_Scene.getScene_longitude() + "\",\"lat\":\""+ op_Scene.getScene_latitude() + "\",\"id\":\""+ op_Scene.getScene_id()+ "\",\"no\":\""+ op_Scene.getScene_no() + "\",\"name\":\""+ "???" + "-" + "???" + "亩" + "\",\"thumb\":\""+ "thumb/01.jpg" + "\",\"description\":\"" + "养殖品种：" + "???……" + ",水草种类："+ "???……" + ",投放时间：" + "???……" + ",手机号："+ "???……" + "\",\"gType\":\""+ type + "\",\"video\":\""+ "" +"\"},"; 
//																																						
//						}
//					}
//				}
//				if(op_Scene.getScene_gtype() == 98){
//					List<String> channelTypeList = new ArrayList<String>();
//					channelTypeList.add("KQSD1201-T");
//					channelTypeList.add("KQSD1201-H");
//					channelTypeList.add("RAD-10");
//					channelTypeList.add("1200-S");
//					channelTypeList.add("1200-D");
//					List<Op_ChannelType> op_ChannelTypeList = this.op_ChannelTypeService.findAllByChtype_no(channelTypeList);
//					List<String> chtype_idList = new ArrayList<String>();
//					for (Op_ChannelType op_ChannelType : op_ChannelTypeList)
//					{
//						chtype_idList.add(op_ChannelType.getChtype_id());
//					}
//					List<Gm_Channel> gm_Channel_list = this.gm_ChannelService.findBySceneIDandChtype(op_Scene.getScene_id(), chtype_idList);
//					if(gm_Channel_list.size() > 0 && gm_Channel_list != null){
//						String qxz = "{\"lng\":\"" + op_Scene.getScene_longitude() + "\",\"lat\":\""+ op_Scene.getScene_latitude() + "\",\"no\":\""+op_Scene.getScene_name()+"-"+ op_Scene.getScene_id() + "\",\"thumb\":\""+ "thumb/01.jpg" + "\",\"description\":\"";
//						for (Gm_Channel gm_Channel : gm_Channel_list)
//						{
//							if(gm_Channel != null){
//								qxz +=  gm_Channel.getCh_name() +"：" + this.gm_LatestdataService.findByCh_id(gm_Channel.getCh_id()).getHida_corrValue()+" "+ gm_Channel.getCh_unit() + ","; 
//							}
//						}
//						
//						qxz +=  "\"gType:\""+ op_Scene.getScene_gtype()+ "\",\"video\":\""+ "" +"},";
//						
//						
//						json += qxz;
//					}else {
//						json +=  "{\"lng\":\"" + op_Scene.getScene_longitude() + "\",\"lat\":\""+ op_Scene.getScene_latitude() + "\",\"id\":\""+ op_Scene.getScene_id()+ "\",\"no\":\""+ op_Scene.getScene_no() + "\",\"name\":\""+ "气象站"  + "\",\"thumb\":\""+ "thumb/01.jpg" + "\",\"description\":\"" + "空气温度：" + "???……" + ",空气湿度："+ "???……" + ",太阳辐射：" + "???……" + ",风速：" + "???……" + ",风向："+ "???……" + "\",\"gType\":\""+ op_Scene.getScene_gtype() + "\",\"video\":\""+ "" +"\"},"; 
//					}
//				}
//				if(op_Scene.getScene_gtype() == 97){
//					json +=  "{\"lng\":\"" + op_Scene.getScene_longitude() + "\",\"lat\":\""+ op_Scene.getScene_latitude() + "\",\"id\":\""+ op_Scene.getScene_id()+ "\",\"no\":\""+ op_Scene.getScene_no() + "\",\"name\":\""+ "" + "-" + "" + "亩" + "\",\"thumb\":\""+ "" + "\",\"description\":\"" + "" + "\",\"gType\":\""+ op_Scene.getScene_gtype() + "\",\"video\":\""+ op_Scene.getScene_videoUrl() +"\"},"; 
//				}
//			}
//			json += "{\"lng\":\"" + "" + "\",\"lat\":\""+ "" + "\",\"id\":\""+ ""+ "\",\"no\":\""+ "" + "\",\"name\":\""+ "" + "-" + "" + "" + "\",\"thumb\":\""+ "" + "\",\"description\":\"" + "溶氧正常：" + green + ",溶氧偏低："+ yellow + ",严重缺氧："+ red + "\",\"gType\":\""+ "64" + "\",\"video\":\""+ "" +"\"},"; 
//			if(json.length() > 0){
//				json = json.substring(0,json.length()-1);
//			}
//			out.print(json+"]");
//			
//		} catch (Exception e)
//		{
//			e.printStackTrace();
//		}
//		
//		return null;
//	}
	
	
	public String cueMsg()
	{
		try
		{
//			HttpServletResponse response = Struts2Utils.getResponse();
			
//			PrintWriter out = response.getWriter();
			
//			String json = "[";
			
//			List<Object> list = this.gm_LatestdataService.findAllMsg();
//			
//			for (Object object : list)
//			{
//				json += "{\"name\":\"" +  +"\"}";
//			}
			
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	
	
//分割线----------------------------------------------------------------------------------------------------------------	
	@Autowired Pro_FisheriesService pro_FisheriesService;
	@Autowired Op_SceneService op_SceneService;
	@Autowired Op_UserInfo_SceneService op_UserInfo_SceneService;
	@Autowired Gm_DevCtrlService gm_DevCtrlService;;
	@Autowired Pro_mapService pro_mapService;
	@Autowired Gm_ChannelService gm_ChannelService;
	@Autowired Gm_LatestdataService gm_LatestdataService;
	@Autowired Op_ChannelTypeService op_ChannelTypeService;
	private Op_Scene op_Scene = new Op_Scene();
	private Page<Op_Scene> page=new Page<Op_Scene>();
	private String sceneId;
	private Pro_Fisheries fisheries;
	private String phone;
	private String pondNo;
	private String message;
	
	private String hid_condition;//查询的条件
    private String hid_value;//查询的值
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPondNo() {
		return pondNo;
	}

	public void setPondNo(String pondNo) {
		this.pondNo = pondNo;
	}	
	public String getSceneId() {
		return sceneId;
	}

	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
	}

	public Pro_Fisheries getFisheries() {
		return fisheries;
	}

	public void setFisheries(Pro_Fisheries fisheries) {
		this.fisheries = fisheries;
	}

	public Page<Op_Scene> getPage() {
		return page;
	}

	public void setPage(Page<Op_Scene> page) {
		this.page = page;
	}

	public Op_Scene getOp_Scene() {
		return op_Scene;
	}

	public void setOp_Scene(Op_Scene op_Scene) {
		this.op_Scene = op_Scene;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	
}
