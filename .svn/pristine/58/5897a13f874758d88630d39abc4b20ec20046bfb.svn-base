package org.unism.gm.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.unism.gm.domain.Gm_DevChannel;
import org.unism.gm.domain.Gm_DevCtrl;
import org.unism.gm.domain.Gm_DevNet;
import org.unism.gm.domain.Gm_Device;
import org.unism.gm.domain.Gm_DevSetupTemplate;
import org.unism.gm.service.DevSetupService;
import org.unism.gm.service.Gm_DevNetService;
import org.unism.gm.service.Gm_DevSetupTemplateService;
import org.unism.gm.service.Gm_DeviceService;
import org.unism.op.domain.Op_ChannelType;
import org.unism.op.domain.Op_Scene;
import org.unism.op.domain.Op_UserInfo;
import org.unism.op.service.Op_ChannelTypeService;
import org.unism.util.Ch_procesStyle;
import org.unism.util.DevSetup;
import org.unism.util.StaticDataManage;
import org.unism.web.action.LoginAction;
import org.wangzz.core.orm.Page;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.web.struts2.Struts2Utils;

import com.google.common.collect.Lists;
import com.opensymphony.xwork2.ActionSupport;

public class DevSetupAction extends ActionSupport{
	
	@Autowired DevSetupService devSetupService;
	
	private Page<Gm_Device> page = new Page<Gm_Device>();
	private String code;
	private String sceneName;
	private String id;
	private Logger logger = Logger.getLogger(LoginAction.class);
	
	public String list() {
		List<String> sceneIds = new ArrayList<String>();
		if(StringUtils.hasText(sceneId)){
			String[] sceneidArr = sceneId.split(",");
			for (String id : sceneidArr) {
				sceneIds.add(id);
			}
		}
		Op_UserInfo user = (Op_UserInfo) Struts2Utils.getSession().getAttribute("user");
		page = devSetupService.findGprsDevByCodeScene(page, code, sceneName, user, sceneIds);
		return "list";
	}
	
	public String show() {
		Gm_Device device =  devSetupService.findDeviceById(id);
		Struts2Utils.getRequest().setAttribute("device", device);
		
		List<String> devIdList = new ArrayList<String>();
		
		Gm_DevNet devNet = devSetupService.findDevNetByDevId(id);
		if(devNet != null) {
			Struts2Utils.getRequest().setAttribute("devNet", devNet);
			
			StringBuffer buffer = new StringBuffer("[");
			buffer = devSetupService.getChildJson(devNet, buffer, devIdList);
			String netTreeJson = buffer.substring(0, buffer.length() - 1);
			netTreeJson = netTreeJson + "]";
			Struts2Utils.getRequest().setAttribute("netTreeJson", netTreeJson);
		}
		
		List<Gm_DevChannel> devChannelList = devSetupService.findOrderDevChlByDevId(id);
		Struts2Utils.getRequest().setAttribute("devChannelList", devChannelList);
		
		if(!devIdList.isEmpty()) {
			List<Gm_DevCtrl> devCtrlList = devSetupService.findDevCtrlByDevIds(devIdList);
			Struts2Utils.getRequest().setAttribute("devCtrlList", devCtrlList);
		}
		
		return "show";
	}
	
	public String showByScene(){
		List<Gm_Device> gm_Devices = gm_DeviceService.findByScene_id(sceneId);
		if(!gm_Devices.isEmpty()){
			Gm_Device device = gm_Devices.get(0);
			Struts2Utils.getRequest().setAttribute("device", device);
			List<String> devIdList = new ArrayList<String>();
			Gm_DevNet devNet = devSetupService.findDevNetByDevId(device.getDev_id());
			if(devNet != null) {
				Struts2Utils.getRequest().setAttribute("devNet", devNet);
				StringBuffer buffer = new StringBuffer("[");
				buffer = devSetupService.getChildJson(devNet, buffer, devIdList);
				String netTreeJson = buffer.substring(0, buffer.length() - 1);
				netTreeJson = netTreeJson + "]";
				Struts2Utils.getRequest().setAttribute("netTreeJson", netTreeJson);
			}
			List<Gm_DevChannel> devChannelList = devSetupService.findOrderDevChlByDevId(device.getDev_id());
			Struts2Utils.getRequest().setAttribute("devChannelList", devChannelList);
			if(!devIdList.isEmpty()) {
				List<Gm_DevCtrl> devCtrlList = devSetupService.findDevCtrlByDevIds(devIdList);
				Struts2Utils.getRequest().setAttribute("devCtrlList", devCtrlList);
			}
		}
		return "show";
	}
	
	/**
	 * 到添加
	 * @return
	 * @author Wang_Yuliang
	 */
	public String toSave() {
		//所属场景
		HttpServletRequest request = Struts2Utils.getRequest();
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
		
		//采集通道应用类型
		List<Op_ChannelType> op_ChannelType_list = this.op_ChannelTypeService.findAll();
		String json_op_ChannelType_list = "[";
		for(Op_ChannelType op_ChannelType : op_ChannelType_list){
			json_op_ChannelType_list += "{";
			json_op_ChannelType_list += "id:'"+op_ChannelType.getChtype_id()+"',";
			json_op_ChannelType_list += "name:'"+op_ChannelType.getChtype_displayName()+"',";
			json_op_ChannelType_list += "no:'"+op_ChannelType.getChtype_no()+"'";
			json_op_ChannelType_list += "},";
		}
		if(json_op_ChannelType_list.length()>1)
			json_op_ChannelType_list = json_op_ChannelType_list.substring(0,(json_op_ChannelType_list.length()-1));
		request.setAttribute("json_op_ChannelType_list", json_op_ChannelType_list+"]");
		
		//通道数据处理方式
		List<Ch_procesStyle> ch_procesStyle_list = StaticDataManage.getCh_procesStyles();
		String json_ch_procesStyle_list = "[";
		for(Ch_procesStyle ch_procesStyle : ch_procesStyle_list){
			json_ch_procesStyle_list += "{";
			json_ch_procesStyle_list += "id:'"+ch_procesStyle.getId()+"',";
			json_ch_procesStyle_list += "name:'"+ch_procesStyle.getName()+"'";
			json_ch_procesStyle_list += "},";
		}
		if(json_ch_procesStyle_list.length()>1)
			json_ch_procesStyle_list = json_ch_procesStyle_list.substring(0,(json_ch_procesStyle_list.length()-1));
		request.setAttribute("json_ch_procesStyle_list", json_ch_procesStyle_list+"]");
		return "save";
	}

	/**
	 * 到添加网络节点信息
	 * @return
	 * @author Wang_Yuliang
	 */
	public String toTianjiawangluojiedianxinxi(){
		//所属场景
		HttpServletRequest request = Struts2Utils.getRequest();
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
		
		return "tianjiawangluojiedianxinxi";
	}
	
	/**
	 * 到编辑网络节点信息
	 * @return
	 * @author liucl
	 */
	public String toBianjiwangluojiedianxinxi(){
		//所属场景
		HttpServletRequest request = Struts2Utils.getRequest();
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
		
		return "bianjiwangluojiedianxinxi";
	}
	
	/**
	 * 添加
	 * @return
	 * @author Wang_Yuliang
	 */
	public String save(){		
		Map<String,DevSetup> devSetup_map = new LinkedHashMap<String,DevSetup>();
		if(!this.dev_list.equals("[]")){
			String[] dev_arr = (this.dev_list.substring(1, (this.dev_list.length()-1))).split(",,");
			for(String dev : dev_arr){
				JSONObject jsonObject = JSONObject.fromObject(dev);
				DevSetup devSetup = (DevSetup)JSONObject.toBean(jsonObject,DevSetup.class);
				if(devSetup!=null)
					devSetup_map.put(devSetup.getId(), devSetup);
			}
		}
//		try{
//			this.devSetupService.save(gm_Device, gm_DevNet, devSetup_map, _scene_id, _ch_no, _ch_name, _dev_collectId, _ch_chlNo, _chtype_id, _ch_offerSer, _order, _procesStyle, _memoryPeriod);
//不能在循环里写查询
		Map<String, Op_Scene> op_Scene_map = (Map<String, Op_Scene>)Struts2Utils.getSession().getAttribute("sceneMap");
		List<Op_ChannelType> op_ChannelType_list = this.op_ChannelTypeService.findAll();
		Map<String, Op_ChannelType> op_ChannelType_map = new HashMap<String, Op_ChannelType>();
		for(Op_ChannelType op_ChannelType : op_ChannelType_list){
			op_ChannelType_map.put(op_ChannelType.getChtype_id(), op_ChannelType);
		}
		try{
			this.devSetupService.save(gm_Device, gm_DevNet, devSetup_map, _scene_id, _ch_no, _ch_name, _dev_collectId, _ch_chlNo, _chtype_id, _ch_offerSer, _order, _procesStyle, _memoryPeriod, op_Scene_map, op_ChannelType_map);
			addActionMessage("操作成功!");
			logger.info("用户"+Struts2Utils.getSession().getAttribute("userid")+" 设备配置信息管理 》 添加 提交 操作成功");
		}catch(Exception ex){
			ex.printStackTrace();
			addActionMessage("操作失败!未知错误");
		}
		return "operationResult";
	}
	
	
	/**
	 * 添加模板
	 * @return
	 * @author liucl
	 */
	public String saveTemplate(){		
		try{
			HttpServletResponse response = Struts2Utils.getResponse();
			HttpSession session = Struts2Utils.getSession();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			this.gm_devSetupTemplate.setDevst_createDateTime(new Date());
			this.gm_devSetupTemplate.setDevst_author(session.getAttribute("username").toString());
			String devTemlXML = "<devTeml>";
			devTemlXML+="<devNO>"+gm_Device.getDev_no()+"</devNO>";
			devTemlXML+="<devName>"+gm_Device.getDev_name()+"</devName>";
			devTemlXML+="<devSerial>"+gm_Device.getDev_serial()+"</devSerial>";
			devTemlXML+="<devModel>"+gm_Device.getDev_model()+"</devModel>";
			devTemlXML+="<devPowerType>"+gm_Device.getDev_powerType()+"</devPowerType>";
			devTemlXML+="<sceneId>"+gm_Device.getScene_id().getScene_id()+"</sceneId>";
			devTemlXML+="<sceneName>"+gm_Device.getScene_id().getScene_name()+"</sceneName>";
			devTemlXML+="<netRole>"+gm_DevNet.getNet_role()+"</netRole>";
			devTemlXML+="<netLinkSts>"+gm_DevNet.getNet_linkSts()+"</netLinkSts>";
			devTemlXML+="<netSIM>"+gm_DevNet.getNet_sim()+"</netSIM>";
			devTemlXML+="<nets>";
			if(!this.dev_list.equals("[]")){				
				String[] dev_arr = (this.dev_list.substring(1, (this.dev_list.length()-1))).split(",,");
				for(String dev : dev_arr){
					devTemlXML+="<net>";
					JSONObject jsonObject = JSONObject.fromObject(dev);
					DevSetup devSetup = (DevSetup)JSONObject.toBean(jsonObject,DevSetup.class);
					if(devSetup!=null)
					{
						devTemlXML+="<id>"+devSetup.getId()+"</id>";
						devTemlXML+="<pid>"+devSetup.getPid()+"</pid>";
						devTemlXML+="<name>"+devSetup.getName()+"</name>";						
						devTemlXML+="<netDevId>"+devSetup.getDev_id()+"</netDevId>";
						devTemlXML+="<netDevNo>"+devSetup.getDev_no()+"</netDevNo>";
						devTemlXML+="<netDevName>"+devSetup.getDev_name()+"</netDevName>";
						devTemlXML+="<netDevSerial>"+devSetup.getDev_serial()+"</netDevSerial>";
						devTemlXML+="<netDevModel>"+devSetup.getDev_model()+"</netDevModel>";
						devTemlXML+="<netDevPowerType>"+devSetup.getNet_powerType()+"</netDevPowerType>";
						devTemlXML+="<netSceneId>"+devSetup.getTianjiawangluojiedian_scene_id()+"</netSceneId>";
						devTemlXML+="<netSceneName>"+devSetup.getNet_sceneName()+"</netSceneName>";
						devTemlXML+="<netAddr>"+devSetup.getNet_addr()+"</netAddr>";
						devTemlXML+="<netRole>"+devSetup.getNet_role()+"</netRole>";
						devTemlXML+="<netLinkSts>"+devSetup.getNet_linkSts()+"</netLinkSts>";
						devTemlXML+="<netDepth>"+devSetup.getNet_depth()+"</netDepth>";						
					}
					devTemlXML+="</net>";
				}
			}
			devTemlXML+="</nets>";
			devTemlXML+="<channels>";
			if(_scene_id != null){
				for(int i=0;i<_scene_id.length;i++){
					devTemlXML+="<channel>";
					devTemlXML+="<chSceneId>"+_scene_id[i]+"</chSceneId>";
					devTemlXML+="<chSceneName>"+_scene_name[i]+"</chSceneName>";
					devTemlXML+="<chNo>"+_ch_no[i]+"</chNo>";
					devTemlXML+="<chName>"+_ch_name[i]+"</chName>";
					devTemlXML+="<chDevCollectId>"+_dev_collectId[i]+"</chDevCollectId>";
					devTemlXML+="<chChlNo>"+_ch_chlNo[i]+"</chChlNo>";
					devTemlXML+="<chtypeId>"+_chtype_id[i]+"</chtypeId>";
					devTemlXML+="<chOfferSer>"+_ch_offerSer[i]+"</chOfferSer>";
					devTemlXML+="<chOrder>"+_order[i]+"</chOrder>";
					devTemlXML+="<chProcesStyle>"+_procesStyle[i]+"</chProcesStyle>";
					devTemlXML+="<chMemoryPeriod>"+_memoryPeriod[i]+"</chMemoryPeriod>";
					devTemlXML+="</channel>";
				}
			}			
			devTemlXML+="</channels>";
			devTemlXML+="</devTeml>";	
			this.gm_devSetupTemplate.setDevst_template(devTemlXML);
			this.gm_DevSetupTemplateService.save(gm_devSetupTemplate);
			out.print("{message:'添加模板成功!'}");
			logger.info("用户"+Struts2Utils.getSession().getAttribute("userid")+" 设备配置信息管理 》 添加模板 提交 操作成功");
			return null;
		}catch(Exception e){
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
	
	/**
	 * 加载模板
	 * @return
	 * @author liucl
	 */
	@SuppressWarnings("unchecked")
	public String loadTemplate(){
		Search search = new Search();
		if(this.hid_condition != null && !this.hid_condition.equals("")){
			search.addFilter(Filter.like(hid_condition, hid_value));
		}else{	
			search.addFilter(Filter.like("devst_name", ""));				
		}
		this.page = this.gm_DevSetupTemplateService.search(page, search);
		return "loadTemplate";		
	}
	
	/**
	 * 加载配置
	 * @return
	 * @author liucl
	 */
	@SuppressWarnings("unchecked")
	public String loadSet(){
		//所属场景
		HttpServletRequest request = Struts2Utils.getRequest();
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
		return "loadSet";		
	}
	
	/**
	 * 根据ID删除模板
	 * @return
	 */
	public String delete() {
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			gm_DevSetupTemplateService.deleteById(id);
			out.print("{message:'删除成功.'}");
			logger.info("用户"+Struts2Utils.getSession().getAttribute("userid")+" 控制设备信息管理 》 删除模板 提交 操作成功");
			return null;		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("{message:'删除失败.'}");
			}catch(Exception ex){ex.printStackTrace();}	
		}
		return null;
	}
	
	/**
	 * 设备配置管理 验证模板是否有重复
	 */
	public String checkTemplate(){
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			HttpSession session = Struts2Utils.getSession();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			this.gm_devSetupTemplate.setDevst_author(session.getAttribute("username").toString());
			if(this.gm_DevSetupTemplateService.isExist(this.gm_devSetupTemplate)){
				out.print("{value:true,msg:'模板已存在'}");
				return null;
			}
			out.print("{value:false,msg:''}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("{value:true,msg:'未知错误'}");	
			} catch (IOException ex) {ex.printStackTrace();}
		}
		return null;
	}
	
	/**
	 * 设备配置管理 验证输入的GPRS设备编号是否有重复
	 * @return
	 * @author Wang_Yuliang
	 */
	public String checkGPRS_DEV_NO(){
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			if(this.gm_DeviceService.isExist(this.gm_Device)){
				out.print("{value:true,msg:'设备已存在'}");
				return null;
			}
			out.print("{value:false,msg:''}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("{value:true,msg:'未知错误'}");	
			} catch (IOException ex) {ex.printStackTrace();}
		}
		return null;
	}
	
	/**
	 * 设备配置管理 验证输入的M2M网络地址是否有重复
	 * @return
	 * @author Wang_Yuliang
	 */
	public String checkM2M_NET_ADDR(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			//远程网 M2M 设备地址唯一
			if(this.gm_DevNetService.addIsUq(this.gm_DevNet.getNet_addr())){
				out.print("{value:true,msg:'网络节点已存在'}");
				return null;
			}
			out.print("{value:false,msg:''}");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("{value:true,msg:'未知错误'}");	
			} catch (IOException ex) {ex.printStackTrace();}
		}
		return null;
	}
	
	/**
	 * 设备配置管理 验证输入的WSN设备编号是否有重复
	 * @return
	 * @author Wang_Yuliang
	 */
	public String checkWSN_DEV_NO(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			if(this.gm_DeviceService.isExist(this.gm_Device)){
				out.print("设备已存在");
				return null;
			}
			out.print("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("未知错误");	
			} catch (IOException ex) {ex.printStackTrace();}
		}
		return null;
	}
	
//分割线----------------------------------------------------------------------------------------------------------
	
	@Autowired Op_ChannelTypeService op_ChannelTypeService;
	@Autowired Gm_DeviceService gm_DeviceService;
	@Autowired Gm_DevNetService gm_DevNetService;
	@Autowired Gm_DevSetupTemplateService gm_DevSetupTemplateService;
	
	private List<Op_Scene> scene_id_list = Lists.newArrayList();
	private Gm_Device gm_Device = new Gm_Device();
	private Gm_DevNet gm_DevNet = new Gm_DevNet();	
	private Gm_DevSetupTemplate gm_devSetupTemplate = new Gm_DevSetupTemplate();
	
	private String dev_list;	
	private String[] _scene_id;
	private String[] _scene_name;
	private String[] _ch_no;
	private String[] _ch_name;
	private String[] _dev_collectId;
	private String[] _ch_chlNo;
	private String[] _chtype_id;
	private String[] _ch_offerSer;
	private String[] _order;
	private String[] _procesStyle;
	private String[] _memoryPeriod;
	private String root_id; //设备配置管理 验证输入的WSN网络地址是否有重复
	
	private String hid_condition;
	private String hid_value;
	private String sceneId;
	private String isScene;
	
	public String getIsScene() {
		return isScene;
	}

	public void setIsScene(String isScene) {
		this.isScene = isScene;
	}

	public String getSceneId() {
		return sceneId;
	}

	public void setSceneId(String sceneId) {
		this.sceneId = sceneId;
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

	public Gm_Device getGm_Device() {
		return gm_Device;
	}

	public void setGm_Device(Gm_Device gm_Device) {
		this.gm_Device = gm_Device;
	}

	public Gm_DevNet getGm_DevNet() {
		return gm_DevNet;
	}

	public void setGm_DevNet(Gm_DevNet gm_DevNet) {
		this.gm_DevNet = gm_DevNet;
	}

	public String getDev_list() {
		return dev_list;
	}

	public void setDev_list(String dev_list) {
		this.dev_list = dev_list;
	}

	public String[] get_scene_id() {
		return _scene_id;
	}

	public void set_scene_id(String[] _scene_id) {
		this._scene_id = _scene_id;
	}

	public String[] get_ch_no() {
		return _ch_no;
	}

	public void set_ch_no(String[] _ch_no) {
		this._ch_no = _ch_no;
	}

	public String[] get_ch_name() {
		return _ch_name;
	}

	public void set_ch_name(String[] _ch_name) {
		this._ch_name = _ch_name;
	}

	public String[] get_dev_collectId() {
		return _dev_collectId;
	}

	public void set_dev_collectId(String[] id) {
		_dev_collectId = id;
	}

	public String[] get_ch_chlNo() {
		return _ch_chlNo;
	}

	public void set_ch_chlNo(String[] no) {
		_ch_chlNo = no;
	}

	public String[] get_chtype_id() {
		return _chtype_id;
	}

	public void set_chtype_id(String[] _chtype_id) {
		this._chtype_id = _chtype_id;
	}

	public String[] get_ch_offerSer() {
		return _ch_offerSer;
	}

	public void set_ch_offerSer(String[] ser) {
		_ch_offerSer = ser;
	}

	public String[] get_order() {
		return _order;
	}

	public void set_order(String[] _order) {
		this._order = _order;
	}

	public String[] get_procesStyle() {
		return _procesStyle;
	}

	public void set_procesStyle(String[] style) {
		_procesStyle = style;
	}

	public String[] get_memoryPeriod() {
		return _memoryPeriod;
	}

	public void set_memoryPeriod(String[] period) {
		_memoryPeriod = period;
	}

	public List<Op_Scene> getScene_id_list() {
		return scene_id_list;
	}

	public void setScene_id_list(List<Op_Scene> scene_id_list) {
		this.scene_id_list = scene_id_list;
	}

	public Page<Gm_Device> getPage() {
		return page;
	}

	public void setPage(Page<Gm_Device> page) {
		this.page = page;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSceneName() {
		return sceneName;
	}

	public void setSceneName(String sceneName) {
		this.sceneName = sceneName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Gm_DevSetupTemplate getGm_devSetupTemplate() {
		return gm_devSetupTemplate;
	}

	public void setGm_devSetupTemplate(Gm_DevSetupTemplate gm_devSetupTemplate) {
		this.gm_devSetupTemplate = gm_devSetupTemplate;
	}

	public String[] get_scene_name() {
		return _scene_name;
	}

	public void set_scene_name(String[] _scene_name) {
		this._scene_name = _scene_name;
	}
	
	
}
