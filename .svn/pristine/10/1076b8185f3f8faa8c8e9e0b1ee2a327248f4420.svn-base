package org.unism.op.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.unism.gm.service.Gm_DeviceService;
import org.unism.op.domain.Op_RoleInfo;
import org.unism.op.domain.Op_Scene;
import org.unism.op.domain.Op_SysFun;
import org.unism.op.domain.Op_UserInfo;
import org.unism.op.service.Op_SceneService;
import org.unism.op.service.Op_SysFunService;
import org.unism.op.service.Op_UserInfoService;
import org.unism.op.service.Op_UserInfo_SceneService;
import org.wangzz.core.orm.Page;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.web.struts2.Struts2Utils;

import com.opensymphony.xwork2.ActionSupport;



public class Op_SysFunAction extends ActionSupport {
	@Autowired Op_UserInfoService op_UserInfoService;
	@Autowired Op_SceneService op_SceneService;
	@Autowired Gm_DeviceService gm_DeviceService;
	@Autowired Op_UserInfo_SceneService op_UserInfo_SceneService;
	
	public String menuTree(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String menu = (String)session.getAttribute("menu_tree");
			//System.out.println("menu:"+menu);
			//out.print("[{node:\"301\",pnode:\"300\",text:\"场景信息管理\",url:\"op_scene_page.action?page.pageSize=12&op_Scene.scene_pid=\"},{node:\"302\",pnode:\"300\",text:\"设备信息管理\",url:\"gm_device_page.action?page.pageSize=12&gm_Device.dev_id=\"},{node:\"303\",pnode:\"300\",text:\"网络信息管理\",url:\"gm_devnet_page.action?page.pageSize=12&gm_DevNet.net_addr=\"},{node:\"304\",pnode:\"300\",text:\"采集通道信息管理\",url:\"Gm_Channel_findAll.action\"},{node:\"305\",pnode:\"300\",text:\"设备上报通道配置管理\",url:\"Gm_DevChannel_toManage.action\"},	{node:\"306\",pnode:\"300\",text:\"控制设备信息管理\",url:\"Gm_DevCtrl_findAll.action\"},{node:\"307\",pnode:\"300\",text:\"控制设备按钮管理\",url:\"\"},{node:\"308\",pnode:\"300\",text:\"控制设备状态配置管理\",url:\"\"},{node:\"309\",pnode:\"300\",text:\"用户信息管理\",url:\"Op_UserInfo_findAll.action\"},{node:\"310\",pnode:\"300\",text:\"用户场景关联管理\",url:\"op_userinfo_scene_toManage.action\"},{node:\"311\",pnode:\"300\",text:\"设备配置展示\",url:\"devSetup_list.action\"},{node:\"312\",pnode:\"300\",text:\"设备维护\",url:\"gm_device_toManage.action\"},{node:\"500\",pnode:\"0\",text:\"故障管理\",url:\"\"},{node:\"501\",pnode:\"500\",text:\"故障信息管理\",url:\"Gm_DevFault_findAllDevFault.action?page.pageSize=10\"},{node:\"600\",pnode:\"0\",text:\"辅助功能\",url:\"\"},{node:\"601\",pnode:\"600\",text:\"短信接收管理\",url:\"\"},{node:\"602\",pnode:\"600\",text:\"短信发送管理\",url:\"\"},{node:\"700\",pnode:\"0\",text:\"数据导入\",url:\"\"},{node:\"702\",pnode:\"700\",text:\"项目信息导入\",url:\"import/importProject.jsp\"}]");
			out.print(menu);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String tt(){		
		HttpSession session = Struts2Utils.getSession();
		HttpServletRequest request = Struts2Utils.getRequest();
		List<Op_UserInfo> list = this.op_UserInfoService.findAllEq("user_loginName",this.op_UserInfo.getUser_loginName());		
		if(list!=null){
			for (Op_UserInfo op_UserInfo : list) {
				if (op_UserInfo != null) {
					if (op_UserInfo.getUser_loginPwd().equals(this.op_UserInfo.getUser_loginPwd())) {
						try {
							//设置当前用户信息
							//Op_UserInfo currUser = ((List<Op_UserInfo>)this.op_UserInfoService.findAllEq("user_id", "admin")) .get(0);
							Op_UserInfo currUser = op_UserInfo;
							session.setAttribute("user", currUser);
							//2011-05-19 16:20 Wang_Yuliang UP--------------------begin
							session.setAttribute("userid", currUser.getUser_id());
							//----------------------------------------------------end
							//2011-05-24 14:46 Wang_Yuliang UP--------------------begin
							session.setAttribute("username", currUser.getUser_name());
							//----------------------------------------------------end
							//设置当前角色信息
							Op_RoleInfo currRole = currUser.getRole_id();
							session.setAttribute("role", currRole);		
							
							List<Op_Scene> sceneList = this.op_UserInfo_SceneService.findSceneListByUserId(currUser.getUser_id());
							
							//设置场景树
							String scene_tree = this.op_SceneService.generateSceneTree(sceneList);
							session.setAttribute("scene_tree", scene_tree);
							
							String role_id = currRole.getRole_id();
					  		if(role_id!=null && !"".equals(role_id)){
					  			//只有管理员和运维人员拥有菜单树和设备树
					  			if("role-1".equals(role_id) || "role-2".equals(role_id)) {
					  				//设置菜单树
									String menu_tree = this.op_SysFunService.findMenuTree(currRole);
									session.setAttribute("menu_tree", menu_tree);
									//设置设备树
									String device_tree = this.gm_DeviceService.findDeviceTree(currUser);
									session.setAttribute("device_tree", device_tree);
					  			}
					  		}
							//用户默认选中的 最高等级的 场景的 ID
							String scene_id = this.op_UserInfo_SceneService.findByGtype1AndUser_id(currUser.getUser_id());
							request.setAttribute("scene_id", scene_id);
							
							Date date = new Date();
							DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
							String nowDate=format.format(date);
							session.setAttribute("nowDate", nowDate);
		//					Struts2Utils.getRequest().setAttribute("userinfo",list);
		//					Struts2Utils.getRequest().setAttribute("sysfun",this.op_SysFunService.findAll());
							//return "menu_tree";
							return "main";
						} catch (Exception e) {
							e.printStackTrace();
							return "operationResult";
						}											
					} else {
						//System.out.println("密码不正确.....");
						return "operationResult";
					}
				} else {
					//System.out.println("用户不存在.....");
					return "operationResult";
				}
			}
		}
		return "operationResult";
	}
	
	public String main(){
		HttpSession session = Struts2Utils.getSession();
		Op_UserInfo op_UserInfo = (Op_UserInfo) session.getAttribute("user");
		if(op_UserInfo != null){
			try
			{
				Op_RoleInfo currRole = op_UserInfo.getRole_id();
				//session.setAttribute("role", currRole);
				//设置菜单树
				//String menu_tree = this.op_SysFunService.findMenuTree(currRole);
				//session.setAttribute("menu_tree", menu_tree);
				//设置场景树
				String scene_tree = this.op_SceneService.findSceneTree(op_UserInfo);
				session.setAttribute("scene_tree", scene_tree);
				//设置设备树
				String device_tree = this.gm_DeviceService.findDeviceTree(op_UserInfo);
				session.setAttribute("device_tree", device_tree);
				
				Date date = new Date();
				DateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
				String nowDate=format.format(date);
				session.setAttribute("nowDate", nowDate);
				return "main";
			} catch (Exception e)
			{
				return "operationResult";
			}
		}
		
		return "operationResult";
	}

	/**
	 * 根据node_id 分页查询
	 * @return
	 */
	public String PageByNodeid(){
		Search search = new Search();
		Filter filter = Filter.equal("node_id", this.op_SysFun.getNode_id());
		search.addFilter(filter);
		this.page = this.op_SysFunService.search(page, search);
		return "page";
	}
	
	
	/**
	 * 查询所有
	 * @return
	 */
	public String findAll(){
		List<Op_SysFun>list=this.op_SysFunService.findAll();
		Struts2Utils.getRequest().setAttribute("list", list);
		return "list";
	}
	
	@SuppressWarnings("unchecked")
	public String findSysfunAll(){
		Search search = new Search();
		if(StringUtils.hasText(queryValue)){
			search.addFilterLike("node_displayName", queryValue);
		}
		search.addSortAsc("node_sequence");
		List<Op_SysFun> opSysFuns=this.op_SysFunService.search(search);
		Struts2Utils.getRequest().setAttribute("opSysFuns", opSysFuns);
		return "sysfunlist";
	}
	
	public String add(){
		Search search = new Search();
		search.addSortAsc("node_sequence");
		List<Op_SysFun> opSysFuns=this.op_SysFunService.search(search);
		Struts2Utils.getRequest().setAttribute("opSysFuns", opSysFuns);
		return "add";
	}
	
	public String edit(){
		Op_SysFun opSysFun = op_SysFunService.findById(id);
		Search search = new Search();
		search.addSortAsc("node_sequence");
		List<Op_SysFun> opSysFuns=this.op_SysFunService.search(search);
		Struts2Utils.getRequest().setAttribute("opSysFuns", opSysFuns);
		Struts2Utils.getRequest().setAttribute("opSysFun", opSysFun);
		return "edit";
	}
	
	public String update(){
		try {
			op_SysFunService.update(op_SysFun);
			addActionMessage("修改成功……");
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage("修改失败……");
		}
		return "ok";
	}
	
	public String save(){
		try {
			op_SysFunService.save(op_SysFun);
			addActionMessage("添加成功……");
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage("添加失败……");
		}
		return "ok";
	}
	
	public String delete(){
		try {
			op_SysFunService.deleteById(id);
			addActionMessage("删除成功……");
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage("删除失败……");
		}
		return "ok";
	}
	
//分割线--------------------------------------------------------------------------------------------------------------
	@Autowired Op_SysFunService op_SysFunService;
	private Op_SysFun op_SysFun= new Op_SysFun();
	private Op_UserInfo op_UserInfo=new Op_UserInfo();
	private String id;//功能菜单id
	private String queryValue;
	public String getQueryValue() {
		return queryValue;
	}

	public void setQueryValue(String queryValue) {
		this.queryValue = queryValue;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private Page<Op_SysFun> page;
	
	public Page<Op_SysFun> getPage() {
		return page;
	}
	public void setPage(Page<Op_SysFun> page) {
		this.page = page;
	}
	public Op_SysFun getOp_SysFun() {
		return op_SysFun;
	}
	public void setOp_SysFun(Op_SysFun op_SysFun) {
		this.op_SysFun = op_SysFun;
	}

	public Op_UserInfo getOp_UserInfo() {
		return op_UserInfo;
	}

	public void setOp_UserInfo(Op_UserInfo op_UserInfo) {
		this.op_UserInfo = op_UserInfo;
	}

}