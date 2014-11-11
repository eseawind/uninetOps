package org.unism.gm.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.unism.gm.domain.Gm_Channel;
import org.unism.gm.domain.Gm_DevChannel;
import org.unism.gm.domain.Gm_DevNet;
import org.unism.gm.domain.Gm_Device;
import org.unism.gm.service.Gm_ChannelService;
import org.unism.gm.service.Gm_DevChannelService;
import org.unism.gm.service.Gm_DevNetService;
import org.unism.gm.service.Gm_DeviceService;
import org.unism.op.domain.Op_Scene;
import org.unism.op.domain.Op_Supplier;
import org.unism.op.domain.Op_UserInfo;
import org.unism.op.service.Op_SceneService;
import org.unism.op.service.Op_SupplierService;
import org.unism.op.service.Op_UserInfoService;
import org.unism.op.service.Op_UserInfo_SceneService;
import org.unism.web.action.LoginAction;
import org.wangzz.core.orm.Page;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.web.struts2.Struts2Utils;

import com.google.common.collect.Lists;
import com.opensymphony.xwork2.ActionSupport;

public class Gm_DeviceAction extends ActionSupport{
	
	private Logger logger = Logger.getLogger(LoginAction.class);
	
	/**
	 * 分页查询
	 * @return
	 * @author Wang_Yuliang
	 * 0704 改 Wang_Yuliang
	 */
	public String page(){
		HttpSession session = Struts2Utils.getSession();
		String user_id = (String)session.getAttribute("userid");	
		//Op_UserInfo user = (Op_UserInfo)Struts2Utils.getSession().getAttribute("user");
		//List<String> list = this.gm_DeviceService.findDev_idByUser_id(user.getUser_id());
		List<String> list = this.gm_DeviceService.findDev_idByUser_id(user_id);
		
		List<String> scene_id_arr = new ArrayList<String>();
		scene_id_arr.add(this.scene_id);				
		List<String> scene_id_list = this.op_UserInfo_SceneService.findScene_idByUser_id(user_id);
		scene_id_arr = this.op_SceneService.findSceneTree_wangyuliang(scene_id_arr, scene_id_list);
//不能在循环里写查询  >>>>>>>>>>>>>		
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("dev_state", 1),Filter.in("dev_id", list));
		
		if(this.scene_id!=null && !this.scene_id.equals("")){
			if(this.hid_condition != null && !this.hid_condition.equals("")){
				if(this.hid_condition.equals("scene_name")){
					List<String> scene_id_arr_byname = this.op_SceneService.findScene_id_arrByPname(this.hid_value);
					filter = Filter.and(Filter.equal("dev_state", 1),Filter.in("scene_id.scene_id", scene_id_arr_byname),Filter.in("scene_id.scene_id", scene_id_arr));
				}else{
					filter = Filter.and(Filter.equal("dev_state", 1),Filter.like(this.hid_condition, this.hid_value),Filter.in("scene_id.scene_id", scene_id_arr));
				}				
			}else{
				filter = Filter.and(Filter.equal("dev_state", 1),Filter.in("scene_id.scene_id", scene_id_arr));
			}
		}else{
			if(this.hid_condition != null && !this.hid_condition.equals("")){
				if(this.hid_condition.equals("scene_name")){
					List<String> scene_id_arr_byname = this.op_SceneService.findScene_id_arrByPname(this.hid_value);
					filter = Filter.and(Filter.equal("dev_state", 1),Filter.in("scene_id.scene_id", scene_id_arr_byname),Filter.in("dev_id", list));
				}else{
					filter = Filter.and(Filter.equal("dev_state", 1),Filter.like(this.hid_condition, this.hid_value),Filter.in("dev_id", list));
				}	
			}else{
				
			}
		}
		search.addFilter(filter);
		search.addSortAsc("dev_no");
		this.page = this.gm_DeviceService.search(page, search);
		return "page";
	}
	
	/**
	 * 查询所有
	 * @return
	 */
	public String findAll(){
		List<Gm_Device> list=gm_DeviceService.findAll();
		if(list!=null&&list.size()>0){			
			Struts2Utils.getRequest().setAttribute("list", list);
		}
		return "list";
	}
	
	/**
	 * 
	 * @return
	 */
	public String deviceTree(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String device_tree = (String)session.getAttribute("device_tree");
			out.print(device_tree);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 加载设备树 始于GPRS
	 * @return json 	[
	 * 						{type:"11",id:"e_40288",pid:"e_402883e530b6bf830130b6c04dec0210",name:"t0718a1",url:"#",scene_rank:"-1",icon:"../images/dtree/channel.gif"},
	 *						{type:"11",id:"e_40282",pid:"e_402883e530b6bf830130b6c04dec0210",name:"t0718a2",url:"#",scene_rank:"-1",icon:"../images/dtree/channel.gif"}
	 *					]
	 * @author Wang_Yuliang
	 */
	public String loadDeviceTree_beginwithGPRS(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String user_id = (String)session.getAttribute("userid");
			List<String> list = this.gm_DevNetService.findNet_idByUser_id(user_id);
			//List<String> scene_id_list = this.op_UserInfo_SceneService.findScene_idByUser_id(user_id);
			
			List<String> net_id_arr = new ArrayList<String>();
			net_id_arr.add(this.gm_DevNet.getNet_id());
			net_id_arr = this.gm_DevNetService.findDeviceTreeByNet_id(net_id_arr, list);
			String device_tree = "[";
			for(String net_id: net_id_arr){
				Gm_DevNet gm_DevNet = this.gm_DevNetService.findById(net_id);
				if(!gm_DevNet.getNet_id().equals(this.gm_DevNet.getNet_id())){
					device_tree += "{type:\"" + gm_DevNet.getNet_role() + "\",id:\"e_" + gm_DevNet.getNet_id() + "\",pid:\"e_" + gm_DevNet.getNet_pid() + "\",name:\"" + gm_DevNet.getNet_addr() + "\",click:\"window.parent.document.getElementById('centerFrame').contentWindow.echoDeviceTree('"+gm_DevNet.getNet_id()+"','"+gm_DevNet.getNet_addr()+"','"+gm_DevNet.getNet_no()+"','"+gm_DevNet.getNet_type()+"')\",scene_rank:\"-1\",isParent:true, iconOpen:\"images/dtree/device.gif\",iconClose:\"images/dtree/device.gif\",target:\"_self\"},";	
				}
		    	Search search = new Search();
		    	Filter filter = Filter.and(Filter.equal("ch_state", 1),Filter.equal("dev_collectId.dev_id", gm_DevNet.getDev_id().getDev_id()));
		    	search.addFilter(filter);
		    	List<Gm_Channel> gm_channel_list = this.gm_ChannelService.search(search);
		    	for(Gm_Channel gm_Channel : gm_channel_list){
		    		device_tree += "{type:\"channel\",id:\"c_" + gm_Channel.getCh_id() + "\",pid:\"e_" + gm_DevNet.getNet_id() + "\",name:\"" + gm_Channel.getCh_name() + "\",url:\"#\",scene_rank:\"-1\", icon:\"images/dtree/channel.gif\",target:\"_self\"},";			    		
		    	}
			}
			if(device_tree.length()>1){
				device_tree = device_tree.substring(0,(device_tree.length()-1));
			}
			device_tree += "]";
			out.print(device_tree);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 加载设备树 始于 场景
	 * @return json 	[
	 * 						{type:"11",id:"e_40288",pid:"e_402883e530b6bf830130b6c04dec0210",name:"t0718a1",url:"#",scene_rank:"-1",icon:"../images/dtree/channel.gif"},
	 *						{type:"11",id:"e_40282",pid:"e_402883e530b6bf830130b6c04dec0210",name:"t0718a2",url:"#",scene_rank:"-1",icon:"../images/dtree/channel.gif"}
	 *					]
	 * @author Wang_Yuliang
	 */
	public String loadDeviceTree_beginwithMinScene(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String user_id = (String)session.getAttribute("userid");
			List<String> list = this.gm_DevNetService.findNet_idByUser_id(user_id);
			List<String> scene_id_list = this.op_UserInfo_SceneService.findScene_idByUser_id(user_id);				
			String device_tree = "[";
			List<Op_Scene> child_scene_list = this.op_SceneService.findByScene_pid(this.scene_id, scene_id_list);
			for(Op_Scene op_Scene : child_scene_list){
				//....
				String click = "click:\"window.parent.document.getElementById('centerFrame').contentWindow.echoSceneTree('"+op_Scene.getScene_id()+"','"+op_Scene.getScene_name()+"','"+op_Scene.getScene_no()+"','" + op_Scene.getScene_rank()+"','" + op_Scene.getScene_gtype() + "','"+op_Scene.getScene_videoUrl()+"');\"";
				if(op_Scene.getScene_gtype() == 97){
					click = "url:\""+op_Scene.getScene_videoUrl()+"\"";
				}
				//....唉
				device_tree += "{type:\"scene\",id:\"s_" + op_Scene.getScene_id() + "\",pid:\"s_" + op_Scene.getScene_pid() + "\",name:\"" + op_Scene.getScene_addr() + "-" + op_Scene.getScene_name() + "\","+click+",scene_rank:\"-1\",isParent:true,target:\"_self\"},";
			}
			Search search_net = new Search();//有问题
			Filter filter_net = Filter.and(Filter.equal("net_state", 1),Filter.in("dev_id.dev_id", this.gm_DeviceService.findDev_id_listByScene_id(this.scene_id)),Filter.or(Filter.notEqual("net_pid", "FF"),Filter.isNull("net_pid")));
			search_net.addFilter(filter_net);
			search_net.addSortAsc("net_role");
		    List<Gm_DevNet> gm_DevNet_list = this.gm_DevNetService.search(search_net);	
		    
			for(Gm_DevNet gm_DevNet: gm_DevNet_list){
				if(gm_DevNet.getNet_pid()==null){
					device_tree += "{type:\"" + gm_DevNet.getNet_role() + "\",id:\"e_" + gm_DevNet.getNet_id() + "\",pid:\"s_" + gm_DevNet.getDev_id().getScene_id().getScene_id() + "\",name:\"" + gm_DevNet.getNet_addr() + "\",click:\"window.parent.document.getElementById('centerFrame').contentWindow.echoDeviceTree('"+gm_DevNet.getNet_id()+"','"+gm_DevNet.getNet_addr()+"','"+gm_DevNet.getNet_no()+"','"+gm_DevNet.getNet_type()+"')\",scene_rank:\"-1\", icon:\"images/dtree/device.gif\", iconOpen:\"images/dtree/device.gif\",iconClose:\"images/dtree/device.gif\",target:\"_self\"},";	
				}else{
					device_tree += "{type:\"" + gm_DevNet.getNet_role() + "\",id:\"e_" + gm_DevNet.getNet_id() + "\",pid:\"e_" + gm_DevNet.getNet_pid() + "\",name:\"" + gm_DevNet.getNet_addr() + "\",click:\"window.parent.document.getElementById('centerFrame').contentWindow.echoDeviceTree('"+gm_DevNet.getNet_id()+"','"+gm_DevNet.getNet_addr()+"','"+gm_DevNet.getNet_no()+"','"+gm_DevNet.getNet_type()+"')\",scene_rank:\"-1\", icon:\"images/dtree/device.gif\", iconOpen:\"images/dtree/device.gif\",iconClose:\"images/dtree/device.gif\",target:\"_self\"},";	
				}
		    	Search search_channel = new Search();
		    	Filter filter_channel = Filter.and(Filter.equal("ch_state", 1),Filter.equal("dev_collectId.dev_id", gm_DevNet.getDev_id().getDev_id()));
		    	search_channel.addFilter(filter_channel);
		    	List<Gm_Channel> gm_channel_list = this.gm_ChannelService.search(search_channel);
		    	for(Gm_Channel gm_Channel : gm_channel_list){
		    		device_tree += "{type:\"channel\",id:\"c_" + gm_Channel.getCh_id() + "\",pid:\"e_" + gm_DevNet.getNet_id() + "\",name:\"" + gm_Channel.getCh_name() + "\",scene_rank:\"-1\", icon:\"images/dtree/channel.gif\",target:\"_self\"},";			    		
		    	}
			}
			if(device_tree.length()>1){
				device_tree = device_tree.substring(0,(device_tree.length()-1));
			}
			device_tree += "]";
			out.print(device_tree);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}	
	
	/**
	 * 到添加
	 * @return
	 * @author Wang_Yuliang
	 */
	public String toSave(){	
		cust_saleId_list = this.op_SupplierService.findAllEq("sup_type", 0);
		cust_serviceId_list = this.op_SupplierService.findAllEq("sup_type", 1);
		Op_UserInfo user = (Op_UserInfo)Struts2Utils.getSession().getAttribute("user");
		List<String> list = this.op_UserInfo_SceneService.findScene_idByUser_id(user.getUser_id());
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("scene_state", 1),Filter.in("scene_id", list));
		search.addFilter(filter);
		this.scene_id_list = this.op_SceneService.search(search);		
		return "save";
	}
	
	/**
	 * 到添加 0715
	 * @return
	 * @author Wang_Yuliang
	 */
	public String toSave_0715(){	
		HttpServletRequest request = Struts2Utils.getRequest();
		cust_saleId_list = this.op_SupplierService.findAllEq("sup_type", 0);
		cust_serviceId_list = this.op_SupplierService.findAllEq("sup_type", 1);
		Op_UserInfo user = (Op_UserInfo)Struts2Utils.getSession().getAttribute("user");
		/**
		List<String> list = this.op_UserInfo_SceneService.findScene_idByUser_id(user.getUser_id());
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("scene_state", 1),Filter.or(Filter.isNull("scene_pid"),Filter.notEqual("scene_pid", "FF")));
		search.addFilter(filter);
		this.scene_id_list = this.op_SceneService.search(search);
		**/
		this.scene_id_list = (List<Op_Scene>) Struts2Utils.getSession().getAttribute("sceneList");
		String scene_id_list = "[";
		for(Op_Scene op_Scene : this.scene_id_list){
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
		request.setAttribute("scenes", scene_id_list+"]");
		return "save_0715";
	}
	
	/**
	 * 异步判断设备编号是否已存在
	 * @return
	 * @author Wang_Yuliang
	 */
	public String json_isExist_dev_no(){
		//HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			if(this.gm_DeviceService.isExist_dev_no(this.gm_Device.getDev_no())){
				out.print("{value:true,msg:'操作失败!设备编号已存在'}");
			}else{
				out.print("{value:false}");				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("{value:true,msg:'操作失败!检查设备编号时发生错误'}");
			}catch(Exception ex){ex.printStackTrace();}	
		}
		return null;
	}
	
	/**
	 * 异步判断设备序列号是否已存在
	 * @return
	 * @author Wang_Yuliang
	 */
	public String json_isExist_dev_serial(){
		//HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			if(this.gm_DeviceService.isExist_dev_serial(this.gm_Device.getDev_serial())){
				out.print("{value:true,msg:'操作失败!设备序列号已存在'}");
			}else{
				out.print("{value:false}");				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("{value:true,msg:'操作失败!检查设备序列号时发生错误'}");
			}catch(Exception ex){ex.printStackTrace();}	
		}
		return null;
	}
	
	/**
	 * 添加
	 * @return
	 * @author Wang_Yuliang
	 */
	public String save(){
		HttpSession session = Struts2Utils.getSession();
		//request.setAttribute("list", "gm_device_page.action?page.pageSize=12");
		if(this.gm_DeviceService.isExist(this.gm_Device)){
			addActionMessage("操作失败!设备已存在");
			return "operationResult";
		}
		if(this.gm_Device.getCust_saleId().getSup_id().equals("-1")){
			this.gm_Device.setCust_saleId(null);
		}
		if(this.gm_Device.getCust_serviceId().getSup_id().equals("-1")){
			this.gm_Device.setCust_serviceId(null);
		}
		try{
			this.gm_DeviceService.save(this.gm_Device);
			addActionMessage("操作成功!");
			logger.info("用户"+session.getAttribute("userid")+" 设备信息管理 》 添加 提交 操作成功");
		}catch(Exception ex){ex.printStackTrace();}
		return "operationResult";
	}
	
	/**
	 * 到编辑
	 * @return
	 * @author Wang_Yuliang
	 */
	public String toEdit(){
		this.gm_Device = this.gm_DeviceService.findById(this.gm_Device.getDev_id());
		//准备外键数据
		cust_saleId_list = this.op_SupplierService.findAllEq("sup_type", 0);
		cust_serviceId_list = this.op_SupplierService.findAllEq("sup_type", 1);
		Op_UserInfo user = (Op_UserInfo)Struts2Utils.getSession().getAttribute("user");
		List<String> list = this.op_UserInfo_SceneService.findScene_idByUser_id(user.getUser_id());
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("scene_state", 1),Filter.in("scene_id", list));
		search.addFilter(filter);
		this.scene_id_list = this.op_SceneService.search(search);	
		return "edit";
	}
	
	/**
	 * 到编辑 0715
	 * @return
	 * @author Wang_Yuliang
	 */
	public String toEdit_0715(){
		HttpServletRequest request = Struts2Utils.getRequest();
		this.gm_Device = this.gm_DeviceService.findById(this.gm_Device.getDev_id());
		//准备外键数据
		cust_saleId_list = this.op_SupplierService.findAllEq("sup_type", 0);
		cust_serviceId_list = this.op_SupplierService.findAllEq("sup_type", 1);
		Op_UserInfo user = (Op_UserInfo)Struts2Utils.getSession().getAttribute("user");
		/**
		List<String> list = this.op_UserInfo_SceneService.findScene_idByUser_id(user.getUser_id());
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("scene_state", 1),Filter.in("scene_id", list),Filter.or(Filter.isNull("scene_pid"),Filter.notEqual("scene_pid", "FF")));
		search.addFilter(filter);
		this.scene_id_list = this.op_SceneService.search(search);
		*/
		this.scene_id_list = (List<Op_Scene>) Struts2Utils.getSession().getAttribute("sceneList");
		String scene_id_list = "[";
		for(Op_Scene op_Scene : this.scene_id_list){
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
		request.setAttribute("scenes", scene_id_list+"]");
		return "edit_0715";
	}
	
	/**
	 * 编辑设备信息
	 * @return
	 * @author Wang_Yuliang
	 */
	public String edit(){
		HttpSession session = Struts2Utils.getSession();
		//request.setAttribute("list", "gm_device_page.action?page.pageSize=12");
		//验证设备是否已存在
		List<Gm_Device> list = new ArrayList<Gm_Device>();
		Search search = new Search();
		Filter filter = Filter.and(Filter.equal("dev_state", 1),Filter.equal("dev_no", gm_Device.getDev_no()),Filter.notEqual("dev_id", this.gm_Device.getDev_id()));
		search.addFilter(filter);
		list = this.gm_DeviceService.search(search);
		if(list.size()>0){
			addActionMessage("操作失败!设备已存在");
			return "operationResult";
		}
		if(this.gm_Device.getCust_saleId().getSup_id().equals("-1")){
			this.gm_Device.setCust_saleId(null);
		}
		if(this.gm_Device.getCust_serviceId().getSup_id().equals("-1")){
			this.gm_Device.setCust_serviceId(null);
		}
		this.gm_DeviceService.update(this.gm_Device);
		addActionMessage("操作成功!");
		logger.info("用户"+session.getAttribute("userid")+" 设备信息管理 》 编辑 提交 操作成功");
		return "operationResult";
	}
	
	/**
	 * 删除
	 * @return
	 * @author Wang_Yuliang
	 */
	public String delete(){
		HttpServletRequest request = Struts2Utils.getRequest();
		//request.setAttribute("list", "gm_device_page.action?page.pageSize=12");
		if(this.gm_DeviceService.isExistNet(this.gm_Device.getDev_id())){
			addActionMessage("操作失败!设备在用");
			return "operationResult";
		}else if(this.gm_DeviceService.isExistChannel(this.gm_Device.getDev_id())){
			addActionMessage("操作失败!设备在用");
			return "operationResult";
		}else if(this.gm_DeviceService.isExistDevCtrl(this.gm_Device.getDev_id())){
			addActionMessage("操作失败!设备在用");
			return "operationResult";
		}else if(this.gm_DeviceService.isExistBtn(this.gm_Device.getDev_id())){
			addActionMessage("操作失败!设备在用");
			return "operationResult";
		}else{
			this.gm_Device = this.gm_DeviceService.findById(this.gm_Device.getDev_id());
			this.gm_Device.setDev_state(0);
			this.gm_DeviceService.update(this.gm_Device);
			List<Gm_DevChannel> gm_DevChannel_list = this.gm_DevChannelService.findByDev_id(this.gm_Device.getDev_id());
			for(Gm_DevChannel gm_DevChannel : gm_DevChannel_list){
				this.gm_DevChannelService.delete(gm_DevChannel);
			}
			addActionMessage("操作成功!");	
			return "operationResult";
		}				
	}
	
	/**
	 * 到智能设备配置管理
	 * @return
	 * @author Wang_Yuliang
	 */
	public String toManage(){
		return "manage";
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
	 * 						dest_recData:'?',
	 * 						dev_state:'?',
	 * 						dev_id:'?'
	 * 					},
	 * 					...
	 * 				]
	 * 		}
	 * @author Wang_Yuliang
	 */
	public String manage_page(){
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
			String json = this.gm_DeviceService.page_wangzz(scene_id_arr,no,size,dev_addr);
			out.print(json);			
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
	
	/**
	 * 智能设备配置管理 删除 （未优化更改的版本）
	 * @return
	 * @author Wang_Yuliang
	 
	public String manage_del(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String user_id = (String)session.getAttribute("userid");
			if(gm_DeviceService.isHasDevice(user_id,gm_Device.getDev_id())){
				this.gm_DeviceService.manage_del(gm_Device.getDev_id());
				out.print("{message:'操作成功!'}");
				logger.info("用户"+Struts2Utils.getSession().getAttribute("userid")+" 设备配置信息管理 》 删除 操作成功");
				return null;
			}else{
				out.print("{message:'操作失败!您没有权限删除该设备。'}");
				return null;
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("{message:'操作失败!未知错误'}");
			}catch(Exception ex){ex.printStackTrace();}	
		}
		return null;
	}
	*/
	
	/**
	 * 智能设备配置管理 删除 （未优化更改的版本）
	 * @return
	 * @author Wang_Yuliang
	 */
	public String manage_del(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String user_id = (String)session.getAttribute("userid");
			if(gm_DeviceService.isHasDevice(user_id,gm_Device.getDev_id())){
				String dev_id_arr_str=this.gm_DeviceService.manage_del(gm_Device.getDev_id());
				out.print("{type:'1',message:\""+dev_id_arr_str+"\"}");
				logger.info("用户"+Struts2Utils.getSession().getAttribute("userid")+" 设备配置信息管理 》 删除 操作进行中...");
				return null;
			}else{
				out.print("{type:'2',message:'操作失败!您没有权限删除该设备。'}");
				return null;
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("{type:'3',message:'操作失败!未知错误'}");
			}catch(Exception ex){ex.printStackTrace();}	
		}
		return null;
	}
	
	/**
	 * 删除Gm_Latestdata
	 */
	public String gm_Latestdata_del(){
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();	
			this.gm_DeviceService.gm_Latestdata_del(this.dev_id_arr_str);
			out.print("{type:'1',message:5}");
			logger.info("用户"+Struts2Utils.getSession().getAttribute("userid")+" 设备配置信息管理 》 删除Gm_Latestdata操作成功");
			return null;					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("{type:'2',message:'操作失败!未知错误'}");
			}catch(Exception ex){ex.printStackTrace();}
		}
		return null;
	}
	
	/**
	 * 删除Gm_Historydata
	 */
	public String gm_Historydata_del(){
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();			
			this.gm_DeviceService.gm_Historydata_del(this.dev_id_arr_str);
			out.print("{type:'1',message:10}");
			logger.info("用户"+Struts2Utils.getSession().getAttribute("userid")+" 设备配置信息管理 》 删除Gm_Historydata操作成功");
			return null;
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("{type:'2',message:'操作失败!未知错误'}");
			}catch(Exception ex){ex.printStackTrace();}
		}
		return null;
	}
	
	/**
	 * 删除Gm_DevChannel
	 */
	public String gm_DevChannel_del(){
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();			
			this.gm_DeviceService.gm_DevChannel_del(this.dev_id_arr_str);
			out.print("{type:'1',message:15}");
			logger.info("用户"+Struts2Utils.getSession().getAttribute("userid")+" 设备配置信息管理 》 删除Gm_DevChannel操作成功");
			return null;
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("{type:'2',message:'操作失败!未知错误'}");
			}catch(Exception ex){ex.printStackTrace();}
		}
		return null;
	}
	
	/**
	 * 删除Gm_DevFault
	 */
	public String gm_DevFault_del(){
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();			
			this.gm_DeviceService.gm_DevFault_del(this.dev_id_arr_str);
			out.print("{type:'1',message:20}");
			logger.info("用户"+Struts2Utils.getSession().getAttribute("userid")+" 设备配置信息管理 》 删除Gm_DevFault操作成功");
			return null;
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("{type:'2',message:'操作失败!未知错误'}");
			}catch(Exception ex){ex.printStackTrace();}
		}
		return null;
	}
	
	
	
	/**
	 * 删除op_alarmargument
	 */
	public String op_alarmargument_del(){
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();			
			this.gm_DeviceService.op_alarmargument_del(this.dev_id_arr_str);
			out.print("{type:'1',message:25}");
			logger.info("用户"+Struts2Utils.getSession().getAttribute("userid")+" 设备配置信息管理 》 删除Op_alarmargument操作成功");
			return null;
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("{type:'2',message:'操作失败!未知错误'}");
			}catch(Exception ex){ex.printStackTrace();}
		}
		return null;
	}
	
	/**
	 * 删除op_DevCtrlSts
	 */
	public String op_DevCtrlSts_del(){
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();			
			this.gm_DeviceService.op_DevCtrlSts_del(this.dev_id_arr_str);
			out.print("{type:'1',message:'30'}");
			logger.info("用户"+Struts2Utils.getSession().getAttribute("userid")+" 设备配置信息管理 》 删除Op_DevCtrlSts操作成功");
			return null;
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("{type:'2',message:'操作失败!未知错误'}");
			}catch(Exception ex){ex.printStackTrace();}
		}
		return null;
	}
	
	/**
	 * 删除op_DevCtrlBtn
	 */
	public String op_DevCtrlBtn_del(){
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();			
			this.gm_DeviceService.op_DevCtrlBtn_del(this.dev_id_arr_str);
			out.print("{type:'1',message:35}");
			logger.info("用户"+Struts2Utils.getSession().getAttribute("userid")+" 设备配置信息管理 》 删除Op_DevCtrlBtn操作成功");
			return null;
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("{type:'2',message:'操作失败!未知错误'}");
			}catch(Exception ex){ex.printStackTrace();}
		}
		return null;
	}
	
	/**
	 * 删除gm_DevCtrlOperate
	 */
	public String gm_DevCtrlOperate_del(){
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();			
			this.gm_DeviceService.gm_DevCtrlOperate_del(this.dev_id_arr_str);
			out.print("{type:'1',message:40}");
			logger.info("用户"+Struts2Utils.getSession().getAttribute("userid")+" 设备配置信息管理 》 删除Gm_DevCtrlOperate操作成功");
			return null;
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("{type:'2',message:'操作失败!未知错误'}");
			}catch(Exception ex){ex.printStackTrace();}
		}
		return null;
	}
	
	/**
	 * 删除gm_DevCtrlOperateHistory
	 */
	public String gm_DevCtrlOperateHistory_del(){
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();			
			this.gm_DeviceService.gm_DevCtrlOperateHistory_del(this.dev_id_arr_str);
			out.print("{type:'1',message:45}");
			logger.info("用户"+Struts2Utils.getSession().getAttribute("userid")+" 设备配置信息管理 》 删除Gm_DevCtrlOperateHistory操作成功");
			return null;
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("{type:'2',message:'操作失败!未知错误'}");
			}catch(Exception ex){ex.printStackTrace();}
		}
		return null;
	}
	
	/**
	 * 删除phone_DevCtrl
	 */
	public String phone_DevCtrl_del(){
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();			
			this.gm_DeviceService.phone_DevCtrl_del(this.dev_id_arr_str);
			out.print("{type:'1',message:50}");
			logger.info("用户"+Struts2Utils.getSession().getAttribute("userid")+" 设备配置信息管理 》 删除Phone_DevCtrl操作成功");
			return null;
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("{type:'2',message:'操作失败!未知错误'}");
			}catch(Exception ex){ex.printStackTrace();}
		}
		return null;
	}
	
	/**
	 * 删除gm_DevCtrlSts
	 */
	public String gm_DevCtrlSts_del(){
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();			
			this.gm_DeviceService.gm_DevCtrlSts_del(this.dev_id_arr_str);
			out.print("{type:'1',message:55}");
			logger.info("用户"+Struts2Utils.getSession().getAttribute("userid")+" 设备配置信息管理 》 删除Gm_DevCtrlSts操作成功");
			return null;
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("{type:'2',message:'操作失败!未知错误'}");
			}catch(Exception ex){ex.printStackTrace();}
		}
		return null;
	}
	
	/**
	 * 删除gm_DevCtrlStsHistory
	 */
	public String gm_DevCtrlStsHistory_del(){
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();			
			this.gm_DeviceService.gm_DevCtrlStsHistory_del(this.dev_id_arr_str);
			out.print("{type:'1',message:60}");
			logger.info("用户"+Struts2Utils.getSession().getAttribute("userid")+" 设备配置信息管理 》 删除Gm_DevCtrlStsHistory操作成功");
			return null;
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("{type:'2',message:'操作失败!未知错误'}");
			}catch(Exception ex){ex.printStackTrace();}
		}
		return null;
	}
	
	/**
	 * 删除pro_fisheries
	 */
	public String pro_fisheries_del(){
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();			
			this.gm_DeviceService.pro_fisheries_del(this.dev_id_arr_str);
			out.print("{type:'1',message:65}");
			logger.info("用户"+Struts2Utils.getSession().getAttribute("userid")+" 设备配置信息管理 》 删除Pro_fisheries操作成功");
			return null;
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("{type:'2',message:'操作失败!未知错误'}");
			}catch(Exception ex){ex.printStackTrace();}
		}
		return null;
	}
	
	/**
	 * 删除gm_Channel
	 */
	public String gm_Channel_del(){
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();			
			this.gm_DeviceService.gm_Channel_del(this.dev_id_arr_str);
			out.print("{type:'1',message:70}");
			logger.info("用户"+Struts2Utils.getSession().getAttribute("userid")+" 设备配置信息管理 》 删除Gm_Channel操作成功");
			return null;
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("{type:'2',message:'操作失败!未知错误'}");
			}catch(Exception ex){ex.printStackTrace();}
		}
		return null;
	}
	
	/**
	 * 删除gm_DevCtrl
	 */
	public String gm_DevCtrl_del(){
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();			
			this.gm_DeviceService.gm_DevCtrl_del(this.dev_id_arr_str);
			out.print("{type:'1',message:75}");
			logger.info("用户"+Struts2Utils.getSession().getAttribute("userid")+" 设备配置信息管理 》 删除Gm_DevCtrl操作成功");
			return null;
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("{type:'2',message:'操作失败!未知错误'}");
			}catch(Exception ex){ex.printStackTrace();}
		}
		return null;
	}
	
	/**
	 * 删除gm_Devnet
	 */
	public String gm_Devnet_del(){
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();			
			this.gm_DeviceService.gm_Devnet_del(this.dev_id_arr_str);
			out.print("{type:'1',message:80}");
			logger.info("用户"+Struts2Utils.getSession().getAttribute("userid")+" 设备配置信息管理 》 删除Gm_Devnet操作成功");
			return null;
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("{type:'2',message:'操作失败!未知错误'}");
			}catch(Exception ex){ex.printStackTrace();}
		}
		return null;
	}
	
	/**
	 * 删除gm_Devsts
	 */
	public String gm_Devsts_del(){
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();			
			this.gm_DeviceService.gm_Devsts_del(this.dev_id_arr_str);
			out.print("{type:'1',message:85}");
			logger.info("用户"+Struts2Utils.getSession().getAttribute("userid")+" 设备配置信息管理 》 删除Gm_Devsts操作成功");
			return null;
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("{type:'2',message:'操作失败!未知错误'}");
			}catch(Exception ex){ex.printStackTrace();}
		}
		return null;
	}
	
	/**
	 * 删除gm_DevstsHis
	 */
	public String gm_DevstsHis_del(){
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();			
			this.gm_DeviceService.gm_DevstsHis_del(this.dev_id_arr_str);
			out.print("{type:'1',message:'90'}");
			logger.info("用户"+Struts2Utils.getSession().getAttribute("userid")+" 设备配置信息管理 》 删除Gm_DevstsHis操作成功");
			return null;
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("{type:'2',message:'操作失败!未知错误'}");
			}catch(Exception ex){ex.printStackTrace();}
		}
		return null;
	}
	
	/**
	 * 删除gm_Device
	 */
	public String gm_Device_del(){
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();			
			this.gm_DeviceService.gm_Device_del(this.dev_id_arr_str);
			out.print("{type:'1',message:'100'}");
			logger.info("用户"+Struts2Utils.getSession().getAttribute("userid")+" 设备配置信息管理 》 删除Gm_Device操作成功");
			return null;
					
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("{type:'2',message:'操作失败!未知错误'}");
			}catch(Exception ex){ex.printStackTrace();}
		}
		return null;
	}
	
	/**
	 * 指定场景ID 返回场景及其子场景下的设备
	 * @return
	 * @author Wang_Yuliang
	 */
	public String json_findAllByScene_id_min(){
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
			out.print(this.gm_DeviceService.json_findAllByScene_id_min(scene_id_arr));
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
	
//分割线--------------------------------------------------------------------------------------------------------	
	@Autowired Gm_DeviceService gm_DeviceService;
	@Autowired Op_SupplierService op_SupplierService;
	@Autowired Op_SceneService op_SceneService;
	@Autowired Op_UserInfo_SceneService op_UserInfo_SceneService;
	@Autowired Gm_DevNetService gm_DevNetService;
	@Autowired Gm_DevChannelService gm_DevChannelService;
	@Autowired Gm_ChannelService gm_ChannelService;
	@Autowired Gm_DevChannelService gmDevChannelService;
	
	private Gm_Device gm_Device = new Gm_Device();
	private Gm_DevNet gm_DevNet = new Gm_DevNet();
	private Page<Gm_Device> page;
	private List<Op_Supplier> cust_saleId_list = Lists.newArrayList();
	private List<Op_Supplier> cust_serviceId_list = Lists.newArrayList();
	private List<Op_Scene> scene_id_list = Lists.newArrayList();
	private String scene_id;
	private String hid_condition;
	private String hid_value;
	private String dev_addr; //智能设备配置管理 查询字段
	
	private Integer no = 1; //智能设备配置管理 分页参数
	private Integer size = 12; //智能设备配置管理 分页参数
	
    private Boolean op; //用于重定向到列表action 的时候 传递操作结果信息
    private String result; //用于重定向到列表action 的时候 传递操作结果信息
    private String sms; //用于重定向到列表action 的时候 传递操作结果信息
    
    private String devId;
    
    private String dev_id_arr_str;
	
	public String getDev_id_arr_str() {
		return dev_id_arr_str;
	}

	public void setDev_id_arr_str(String dev_id_arr_str) {
		this.dev_id_arr_str = dev_id_arr_str;
	}

	public String getDevId() {
		return devId;
	}

	public void setDevId(String devId) {
		this.devId = devId;
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

	public Gm_DevNet getGm_DevNet() {
		return gm_DevNet;
	}

	public void setGm_DevNet(Gm_DevNet gm_DevNet) {
		this.gm_DevNet = gm_DevNet;
	}

	public String getScene_id() {
		return scene_id;
	}

	public void setScene_id(String scene_id) {
		this.scene_id = scene_id;
	}

	public Page<Gm_Device> getPage() {
		return page;
	}

	public void setPage(Page<Gm_Device> page) {
		this.page = page;
	}

	public Gm_Device getGm_Device() {
		return gm_Device;
	}

	public List<Op_Scene> getScene_id_list() {
		return scene_id_list;
	}

	public void setScene_id_list(List<Op_Scene> scene_id_list) {
		this.scene_id_list = scene_id_list;
	}

	public void setGm_Device(Gm_Device gm_Device) {
		this.gm_Device = gm_Device;
	}

	public List<Op_Supplier> getCust_saleId_list() {
		return cust_saleId_list;
	}

	public void setCust_saleId_list(List<Op_Supplier> cust_saleId_list) {
		this.cust_saleId_list = cust_saleId_list;
	}

	public List<Op_Supplier> getCust_serviceId_list() {
		return cust_serviceId_list;
	}

	public void setCust_serviceId_list(List<Op_Supplier> cust_serviceId_list) {
		this.cust_serviceId_list = cust_serviceId_list;
	}

	public String getDev_addr() {
		return dev_addr;
	}

	public void setDev_addr(String dev_addr) {
		this.dev_addr = dev_addr;
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

	public Boolean getOp() {
		return op;
	}

	public void setOp(Boolean op) {
		this.op = op;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getSms() {
		return sms;
	}

	public void setSms(String sms) {
		this.sms = sms;
	}

}
