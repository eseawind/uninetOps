package org.unism.gm.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.unism.gm.domain.Gm_Channel;
import org.unism.gm.domain.Gm_Device;
import org.unism.gm.service.Gm_ChannelService;
import org.unism.gm.service.Gm_DevChannelService;
import org.unism.gm.service.Gm_DevFaultService;
import org.unism.gm.service.Gm_DeviceService;
import org.unism.gm.service.Gm_LatestdataService;
import org.unism.op.domain.Op_Scene;
import org.unism.op.service.Op_ChannelTypeService;
import org.unism.op.service.Op_DevCtrlStsService;
import org.unism.op.service.Op_SceneService;
import org.unism.op.service.Op_UserInfo_SceneService;
import org.unism.web.action.LoginAction;
import org.wangzz.core.orm.Page;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.web.struts2.Struts2Utils;

import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;



public class Gm_ChannelAction extends ActionSupport{
	
	private Logger logger = Logger.getLogger(LoginAction.class);
	
	/**
	 * 分页查询
	 * @return
	 */	
	public String findAll(){

		//System.out.println(">cccccccccccc");

		//Op_UserInfo user = (Op_UserInfo)Struts2Utils.getSession().getAttribute("user");
		HttpSession session = Struts2Utils.getSession();
		String user_id = (String)session.getAttribute("userid");
		
//		List<String> scene_id_arr = new ArrayList<String>();
//		scene_id_arr.add(this.scene_id);				
//		List<String> scene_id_list = this.op_UserInfo_SceneService.findScene_idByUser_id(user_id);
//		scene_id_arr = this.op_SceneService.findSceneTree_wangyuliang(scene_id_arr, scene_id_list);
//不能在循环里写查询 	
		List<String> list = this.gm_ChannelService.findCh_idByUser_id(user_id);	
		try{
			
			Search search = new Search();
			Filter filter = Filter.and(Filter.equal("ch_state", 1),Filter.in("ch_id", list));
			/**0713 UP Wang_Yuliang begin
			if(queryValue != null && !queryValue.equals("")){
				if(this.scene_id!=null && !this.scene_id.equals("")){
					filter = Filter.and(Filter.equal("ch_state", 1),Filter.like("ch_name", queryValue),Filter.in("scene_id.scene_id", scene_id_arr));
				}else{
					filter = Filter.and(Filter.equal("ch_state", 1),Filter.like("ch_name", queryValue),Filter.in("ch_id", list));
				}	
			}else{
				if(this.scene_id!=null && !this.scene_id.equals("")){
					filter = Filter.and(Filter.equal("ch_state", 1),Filter.in("scene_id.scene_id", scene_id_arr));
				}else{}
			}
			*/			
//			if(this.scene_id!=null && !this.scene_id.equals("")){
//不能在循环里写查询			
			if(this.scene_id_arr!=null){
				if(this.hid_condition != null && !this.hid_condition.equals("")){
					if(this.hid_condition.equals("scene_name")){
						List<String> scene_id_arr_byname = this.op_SceneService.findScene_id_arrByPname(this.hid_value);
						filter = Filter.and(Filter.equal("ch_state", 1),Filter.in("scene_id.scene_id", scene_id_arr_byname),Filter.in("scene_id.scene_id", scene_id_arr));
					}else if(this.hid_condition.equals("dev_no")){
						List<String> dev_id_arr_likeno = this.gm_DeviceService.findDev_id_arrLikeDev_no(this.hid_value);
						filter = Filter.and(Filter.equal("ch_state", 1),Filter.in("dev_collectId.dev_id", dev_id_arr_likeno),Filter.in("scene_id.scene_id", scene_id_arr));
					}else{
						filter = Filter.and(Filter.equal("ch_state", 1),Filter.like(this.hid_condition, this.hid_value),Filter.in("scene_id.scene_id", scene_id_arr));
					}				
				}else{
					filter = Filter.and(Filter.equal("ch_state", 1),Filter.in("scene_id.scene_id", scene_id_arr));
				}
			}else{
				if(this.hid_condition != null && !this.hid_condition.equals("")){
					if(this.hid_condition.equals("scene_name")){
						List<String> scene_id_arr_byname = this.op_SceneService.findScene_id_arrByPname(this.hid_value);
						filter = Filter.and(Filter.equal("ch_state", 1),Filter.in("scene_id.scene_id", scene_id_arr_byname),Filter.in("ch_id", list));
					}else if(this.hid_condition.equals("dev_no")){
						List<String> dev_id_arr_likeno = this.gm_DeviceService.findDev_id_arrLikeDev_no(this.hid_value);
						filter = Filter.and(Filter.equal("ch_state", 1),Filter.in("dev_collectId.dev_id", dev_id_arr_likeno),Filter.in("ch_id", list));
					}else{
						filter = Filter.and(Filter.equal("ch_state", 1),Filter.like(this.hid_condition, this.hid_value),Filter.in("ch_id", list));
					}	
				}else{
					
				}
			}
			//--end
			search.addFilter(filter);
			search.addSortAsc("ch_no");
			this.page = this.gm_ChannelService.search(page, search);
		}catch (Exception e) {
			// TODO: handle exception

			//System.out.println(">>>>>>>>>>>>>>Error");

			e.printStackTrace();
		}
		return "page";
	}
	/**
	 * 查询所有
	 * @return
	 */
//	public String findAll(){
//		@SuppressWarnings("unused")
//		List<Gm_Channel> list=new ArrayList<Gm_Channel>();
//		list=gm_ChannelService.findAll();
//		Struts2Utils.getRequest().setAttribute("list",gm_ChannelService.findAll());
//		return "list";
//	}
	
	/**
	 * 添加
	 * @return
	 */
	public String save(){
		HttpSession session = Struts2Utils.getSession();
		//第一次点添加的时候，跳转到add页面
		if(post==0){			
			String user_id = (String)session.getAttribute("userid");			
			List<Gm_Device> device_list = this.gm_DeviceService.findByUser_id(user_id);
			Struts2Utils.getRequest().setAttribute("device_list",device_list);
			Struts2Utils.getRequest().setAttribute("channeltype",this.op_ChannelTypeService.findAll());
			/**
			List<String> list = this.op_UserInfo_SceneService.findScene_idByUser_id(user_id);
			Search search = new Search();
			Filter filter = Filter.and(Filter.equal("scene_state", 1),Filter.or(Filter.isNull("scene_pid"),Filter.notEqual("scene_pid", "FF")));
			search.addFilter(filter);
			List<Op_Scene> scene_list = this.op_SceneService.search(search);
			*/
			List<Op_Scene> scene_list = (List<Op_Scene>) Struts2Utils.getSession().getAttribute("sceneList");
			String scene_id_list = "[";
			for(Op_Scene op_Scene : scene_list){
				scene_id_list += "{";
				scene_id_list += "id:'"+op_Scene.getScene_id()+"',";
				if(op_Scene.getScene_pid()!=null){
					scene_id_list += "pid:'"+op_Scene.getScene_pid()+"',";
				}else{
					scene_id_list += "pid:0,";
				}
				scene_id_list += "name:'"+op_Scene.getScene_name()+"'";
				scene_id_list += "},";
			}
			if(scene_id_list.length()>1){
				scene_id_list = scene_id_list.substring(0,(scene_id_list.length()-1));
			}
			Struts2Utils.getRequest().setAttribute("scenes", scene_id_list+"]");
			return "add";
		}else if(post==1){	//第二次点添加提交值，并执行添加方法
//			this.gm_Channel.setCh_max(Float.parseFloat(ch_max));
//			this.gm_Channel.setCh_min(Float.parseFloat(ch_min));
//			this.gm_Channel.setCh_crateMax(Double.parseDouble(ch_crateMax));
			try {
//据2011-11-29 需求修改 Wang_Yuliang				
//				if(this.gm_Channel.getDev_sensorId().getDev_id().equals("-1")){
//					this.gm_Channel.setDev_sensorId(null);
//				}
				if(this.gm_Channel.getDev_collectId().getDev_id()==null){
					this.gm_Channel.setDev_collectId(null);
				}
				if(this.gm_Channel.getDev_sensorId().getDev_id()==null || this.gm_Channel.getDev_sensorId().getDev_id().equals("-1")){
					this.gm_Channel.setDev_sensorId(null);
				}				
				this.gm_ChannelService.save_wangyuliang(this.gm_Channel);
				//添加成功弹出提示框
				addActionMessage("操作成功!");
				logger.info("用户"+session.getAttribute("userid")+" 采集通道信息管理 》 添加 提交 操作成功");
			} catch (Exception e) {
				addActionMessage("操作失败!未知错误");
				e.printStackTrace();
				return "operationResult";
			}			
		}
		return "operationResult";
	}
	
	/**
	 * 更新
	 * @return
	 */
	public String update() {
		if(this.gm_Channel!=null){
			try {
				this.gm_ChannelService.update(this.gm_Channel);	
				//修改成功弹出提示框
				PrintWriter out = Struts2Utils.getResponse().getWriter();				
				out.print("<meta http-equiv='content-type' content='text/html;charset=UTF-8'>");
				out.print("<script type='text/javascript' charset='utf-8'>alert('修改成功')</script>");
				out.print("<script>window.location.href='Gm_Channel_page.action?page.pageSize=10'</script>");
				out.flush();
				out.close();
			} catch (Exception e) {
				e.printStackTrace();
				return "operationResult";
			}
		}
		return "operationResult";
	}
	
	/**
	 * 编辑
	 * @return
	 * @author Wang_Yuliang
	 */
	public String edit() {
		if(this.gm_Channel!=null){
			try {
//2011-11-29 据需求更新 Wang_Yuliang				
//				if(this.gm_Channel.getDev_sensorId().getDev_id().equals("-1")){
//					this.gm_Channel.setDev_sensorId(null);
//				}
				if(this.gm_Channel.getDev_collectId().getDev_id()==null){
					this.gm_Channel.setDev_collectId(null);
				}
				if(this.gm_Channel.getDev_sensorId().getDev_id()==null || this.gm_Channel.getDev_sensorId().getDev_id().equals("-1")){
					this.gm_Channel.setDev_sensorId(null);
				}
				this.gm_ChannelService.update(this.gm_Channel);	
				//修改成功弹出提示框			
				addActionMessage("修改成功");
				logger.info("用户"+Struts2Utils.getSession().getAttribute("userid")+" 采集通道信息管理 》 编辑 提交 操作成功");
			} catch (Exception e) {
				addActionMessage("修改失败");
				e.printStackTrace();
				return "operationResult";
			}
		}
		return "operationResult";
	}
	
	
	/**
	 * 根据ID查询
	 * @return
	 */
	public String findBychid(){		
		Search search = new Search();
		Filter filter = Filter.equal("ch_id",this.gm_Channel.getCh_id());
		search.addFilter(filter);
		Struts2Utils.getRequest().setAttribute("scene",this.op_SceneService.findAll());
		Struts2Utils.getRequest().setAttribute("device",this.gm_DeviceService.findAll());
		Struts2Utils.getRequest().setAttribute("channeltype",this.op_ChannelTypeService.findAll());
		Struts2Utils.getRequest().setAttribute("list",this.gm_ChannelService.search(search));
		return "edit";
	}
//不能在循环里写查询   >>>>>>>>>>>>>>>>	
	/**
	 * 到编辑
	 * @return
	 * @author Wang_Yuliang
	 */
	public String toEdit(){
		HttpSession session = Struts2Utils.getSession();
		String user_id = (String)session.getAttribute("userid");
		List<Gm_Device> device_list = this.gm_DeviceService.findByUser_id(user_id);
		Struts2Utils.getRequest().setAttribute("device_list",device_list);		
		Struts2Utils.getRequest().setAttribute("channeltype",this.op_ChannelTypeService.findAll());
		Struts2Utils.getRequest().setAttribute("gm_Channel",this.gm_ChannelService.findById(this.gm_Channel.getCh_id()));
		/**
		List<String> list = this.op_UserInfo_SceneService.findScene_idByUser_id(user_id);
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("scene_state", 1),Filter.or(Filter.isNull("scene_pid"),Filter.notEqual("scene_pid", "FF")));
		search.addFilter(filter);
		List<Op_Scene> scene_list = this.op_SceneService.search(search);
		*/
		List<Op_Scene> scene_list = (List<Op_Scene>) Struts2Utils.getSession().getAttribute("sceneList");
		String scene_id_list = "[";
		for(Op_Scene op_Scene : scene_list){
			scene_id_list += "{";
			scene_id_list += "id:'"+op_Scene.getScene_id()+"',";
			if(op_Scene.getScene_pid()!=null){
				scene_id_list += "pid:'"+op_Scene.getScene_pid()+"',";
			}else{
				scene_id_list += "pid:0,";
			}
			scene_id_list += "name:'"+op_Scene.getScene_name()+"'";
			scene_id_list += "},";
		}
		if(scene_id_list.length()>1){
			scene_id_list = scene_id_list.substring(0,(scene_id_list.length()-1));
		}
		Struts2Utils.getRequest().setAttribute("scenes", scene_id_list+"]");
		return "edit";
	}
	
	/**
	 * 根据ID删除
	 * @return
	 * @2011-07-19 UP Wang_Yuliang
	 */
	public String delete() {
		try {
			this.gm_Channel = this.gm_ChannelService.findById(this.gm_Channel.getCh_id());
			this.gm_ChannelService.delete_wangyuliang(this.gm_Channel);
			addActionMessage("操作成功!");
		} catch (Exception e) {
			addActionMessage("操作失败!");
			e.printStackTrace();
			return "operationResult";
		}
		return "operationResult";
	}
	
	/**
	 * 曲线分析 指定场景id 查询对外提供服务的在用通道
	 * @return json
	 * @author Wang_Yuliang
	 */
	public String json_findByScene_idAndCh_offerSer(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(this.gm_ChannelService.json_findByScene_idAndCh_offerSer(this.scene_id));
		}catch(Exception ex){
			ex.printStackTrace();	
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("[]");
			}catch(Exception exx){exx.printStackTrace();}	
		}	
		return null;
	}
	
	
	/**
	 * 运维 曲线分析 指定场景id 查询在用通道
	 * @return
	 */
	
	public String json_findByScene_idAndCh_offerSer_run()
	{
		try
		{
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print(this.gm_ChannelService.json_findByScene_idAndCh_offerSer_run(scene_id));
		} catch (Exception e)
		{
			e.printStackTrace();
			try
			{
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("[]");
			} catch (Exception e1)
			{
			}
		}
		return null;
	}
	
	/**
	 * 曲线分析 指定场景id 查询所有相关场景中 对外提供服务的在用通道 并按通道应用类型分类
	 * @return json 
	 * [
	 * 		{
	 * 			chtype_id:'',
	 * 			chtype_no:'',
	 * 			chtype_name:'',
	 * 			channels:
	 * 					[
	 * 						{
	 * 							ch_id:'',
	 * 							ch_no:'',
	 * 							ch_name:''
	 * 						},
	 * 						...
	 * 					]
	 * 		},
	 * 		...
	 * ]
	 * @author Wang_Yuliang
	 */
	public String json_findAllByScene_idAndCh_offerSer(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String user_id = Struts2Utils.getSession().getAttribute("userid")+"";
			//与用户相关的场景id
			List<String> scene_id_list = this.op_UserInfo_SceneService.findScene_idByUser_id(user_id);
			List<String> scene_id_arr = new ArrayList<String>();
			scene_id_arr.add(scene_id);
			scene_id_arr = this.op_SceneService.findSceneTree_wangyuliang(scene_id_arr, scene_id_list);
			out.print(this.gm_ChannelService.json_findAllByScene_idAndCh_offerSer(scene_id_arr));
		}catch(Exception ex){
			ex.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("[]");
			}catch(Exception exx){exx.printStackTrace();}	
		}	
		return null;
	}
	
	
	public String json_findAllByScene_idAndCh_offerSer_run(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String user_id = Struts2Utils.getSession().getAttribute("userid")+"";
			//与用户相关的场景id
			List<String> scene_id_list = this.op_UserInfo_SceneService.findScene_idByUser_id(user_id);
			List<String> scene_id_arr = new ArrayList<String>();
			scene_id_arr.add(scene_id);
			scene_id_arr = this.op_SceneService.findSceneTree_wangyuliang(scene_id_arr, scene_id_list);
			out.print(this.gm_ChannelService.json_findAllByScene_idAndCh_offerSer_run(scene_id_arr));
		}catch(Exception ex){
			ex.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("[]");
			}catch(Exception exx){exx.printStackTrace();}	
		}
		
		return null;
	}
	
	public String findChannelByScene(){
		try {
			PrintWriter out = Struts2Utils.getResponse().getWriter();
			String user_id = Struts2Utils.getSession().getAttribute("userid")+"";
			//与用户相关的场景id
			List<String> scene_id_list = this.op_UserInfo_SceneService.findScene_idByUser_id(user_id);
			List<String> scene_id_arr = new ArrayList<String>();
			scene_id_arr.add(scene_id);
			scene_id_arr = this.op_SceneService.findSceneTree_wangyuliang(scene_id_arr, scene_id_list);
			List<Gm_Channel> gm_Channels = gm_ChannelService.findChannelsByScenes(scene_id_arr);
			List<Map<String, String>> jsons = new ArrayList<Map<String,String>>();
			for (Gm_Channel gm_Channel : gm_Channels) {
				Map<String, String> map = new LinkedHashMap<String, String>();
				map.put("chId", gm_Channel.getCh_id());
				map.put("chName", gm_Channel.getCh_name());
				map.put("chNo", gm_Channel.getCh_no());
				map.put("sceneName", gm_Channel.getScene_id().getScene_name());
				map.put("chtypeId", gm_Channel.getChtype_id().getChtype_id());
				map.put("chtypeNo", gm_Channel.getChtype_id().getChtype_no());
				map.put("chtypeName", gm_Channel.getChtype_id().getChtype_displayName());
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
	 * 验证通道编号是否重复
	 * @return json
	 * 			boolean
	 * @author Wang_Yuliang
	 */
	public String isCh_noExist(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String json = this.gm_ChannelService.isCh_noExist(this.gm_Channel.getCh_no());
			out.print(json);
		}catch(Exception ex){
			ex.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("2");
			}catch(Exception exx){exx.printStackTrace();}	
		}
		
		return null;
	}
	
	/**
	 * 曲线分析 按设备查询通道
	 * @return
	 * @author Wang_Yuling
	 */
	public String json_showChannelByDevice_run(){
		try
		{
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String[] dev_id_arr = this.dev_id_arr_str.split(",");
			out.print(this.gm_ChannelService.json_showChannelByDevice_run(dev_id_arr));
		} catch (Exception e)
		{
			e.printStackTrace();
			try
			{
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("[]");
			} catch (Exception e1)
			{
			}
		}
		return null;
	}
	
	/**
	 * 故障信息管理 查看通道信息
	 * @return
	 * @author Wang_Yuliang
	 */
	public String toDevFault_showChannel(){
		this.gm_Channel = this.gm_ChannelService.findById(this.gm_Channel.getCh_id());
		return "devFault_showChannel";
	}
	
	
	public String toBatchUpdateChannel(){
		return "batchUpdateChannel";
	}
	
	public String bathUpdate() throws IOException{
		String json = "";
		PrintWriter out = Struts2Utils.getResponse().getWriter();
		try {
			String[] chIdArr = chIds.split(",");
			List<Gm_Channel> channels = gm_ChannelService.findByIds(chIdArr);
			for (Gm_Channel gm_Channel : channels) {
				gm_Channel.setCh_dectDig(StringUtils.hasText(chDectDig)?Integer.parseInt(chDectDig):null);
				gm_Channel.setCh_max(StringUtils.hasText(chMax)?Float.parseFloat(chMax):null);
				gm_Channel.setCh_min(StringUtils.hasText(chMin)?Float.parseFloat(chMin):null);
				gm_Channel.setCh_crateMax(StringUtils.hasText(chCrateMax)?Double.parseDouble(chCrateMax):null);
				gm_Channel.setCh_corrFormula(StringUtils.hasText(chCorrFormula)?chCorrFormula:null);
				gm_ChannelService.update(gm_Channel);
			}
			json = "[{message:\"操作成功\"}]";
		} catch (Exception e) {
			e.printStackTrace();
			json = "[{message:\"操作失败\"}]";
		}
		out.print(json);
		return null;
	}
	
	
//分割线--------------------------------------------------------------------------------------------------------------
	@Autowired Gm_ChannelService gm_ChannelService;
	@Autowired Gm_DeviceService gm_DeviceService;
	@Autowired Op_SceneService op_SceneService;
	@Autowired Op_ChannelTypeService op_ChannelTypeService;
	@Autowired Op_UserInfo_SceneService op_UserInfo_SceneService;
	@Autowired
	Gm_LatestdataService gm_LatestdataService;
	@Autowired
	Gm_DevChannelService gm_DevChannelService;
	@Autowired
	Gm_DevFaultService gm_DevFaultService;
	@Autowired
	Op_DevCtrlStsService op_DevCtrlStsService;
	
	private Gm_Channel gm_Channel = new Gm_Channel();
	private String ch_max;
	private String ch_min;
	private String ch_crateMax;
	private int post;
	private String scene_id; //曲线分析 加载通道表单 json_findAllByScene_idAndCh_offerSer,json_findByScene_idAndCh_offerSer
	private String hid_condition; //分页查询
	private String hid_value; //分页查询
	private Page<Gm_Channel> page=new Page<Gm_Channel>();
	private String queryValue;
	private List<String> scene_id_arr;
	private String dev_id_arr_str; //曲线分析，按设备查询通道
	
	private String chDectDig;
	private String chMax;
	private String chMin;
	private String chCrateMax;
	private String chCorrFormula;
	private String chIds;
	
	public String getChDectDig() {
		return chDectDig;
	}

	public void setChDectDig(String chDectDig) {
		this.chDectDig = chDectDig;
	}

	public String getChMax() {
		return chMax;
	}

	public void setChMax(String chMax) {
		this.chMax = chMax;
	}

	public String getChMin() {
		return chMin;
	}

	public void setChMin(String chMin) {
		this.chMin = chMin;
	}

	public String getChCrateMax() {
		return chCrateMax;
	}

	public void setChCrateMax(String chCrateMax) {
		this.chCrateMax = chCrateMax;
	}

	public String getChCorrFormula() {
		return chCorrFormula;
	}

	public void setChCorrFormula(String chCorrFormula) {
		this.chCorrFormula = chCorrFormula;
	}

	public String getChIds() {
		return chIds;
	}

	public void setChIds(String chIds) {
		this.chIds = chIds;
	}

	public String getDev_id_arr_str() {
		return dev_id_arr_str;
	}

	public void setDev_id_arr_str(String dev_id_arr_str) {
		this.dev_id_arr_str = dev_id_arr_str;
	}

	public String getQueryValue() {
		return queryValue;
	}
	public void setQueryValue(String queryValue) {
		this.queryValue = queryValue;
	}
	public String getScene_id() {
		return scene_id;
	}

	public void setScene_id(String scene_id) {
		this.scene_id = scene_id;
	}

	public Gm_Channel getGm_Channel() {
		return gm_Channel;
	}

	public void setGm_Channel(Gm_Channel gm_Channel) {
		this.gm_Channel = gm_Channel;
	}

	public int getPost() {
		return post;
	}

	public void setPost(int post) {
		this.post = post;
	}

	public String getCh_max() {
		return ch_max;
	}

	public void setCh_max(String ch_max) {
		this.ch_max = ch_max;
	}

	public String getCh_min() {
		return ch_min;
	}

	public void setCh_min(String ch_min) {
		this.ch_min = ch_min;
	}

	public String getCh_crateMax() {
		return ch_crateMax;
	}

	public void setCh_crateMax(String ch_crateMax) {
		this.ch_crateMax = ch_crateMax;
	}
	public Page<Gm_Channel> getPage() {
		return page;
	}
	public void setPage(Page<Gm_Channel> page) {
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

	public String getScene_id_arr() {
		String scene_id_arr = "";
		if(this.scene_id_arr!=null && this.scene_id_arr.size()>0){
			for(String scene_id : this.scene_id_arr){
				scene_id_arr += "<input type=\"checkbox\" style=\"display: none;\" name=\"scene_id_arr\" value=\""+scene_id+"\" checked=\"checked\">";
			}
		}
		return scene_id_arr;
	}

	public void setScene_id_arr(List<String> scene_id_arr) {
		this.scene_id_arr = scene_id_arr;
	}


}
