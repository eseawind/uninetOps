package org.unism.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.unism.gm.service.Gm_DeviceService;
import org.unism.op.domain.Op_RoleInfo;
import org.unism.op.domain.Op_Scene;
import org.unism.op.domain.Op_UserInfo;
import org.unism.op.service.Op_SceneService;
import org.unism.op.service.Op_SysFunService;
import org.unism.op.service.Op_UserInfoService;
import org.unism.op.service.Op_UserInfo_SceneService;
import org.wangzz.core.web.struts2.Struts2Utils;

import com.opensymphony.xwork2.ActionSupport;


public class LoginAction extends ActionSupport {
	
	private static final long serialVersionUID = 323751134564060156L;
	
	private Logger logger = Logger.getLogger(LoginAction.class);
	
	@Autowired Op_UserInfoService userInfoService;
	@Autowired Op_SceneService sceneService;
	@Autowired Gm_DeviceService deviceService;
	@Autowired Op_UserInfo_SceneService userSceneService;
	@Autowired Op_SysFunService sysFunService;
	
	public String val() {
		Op_UserInfo currUser = userInfoService.findUniqueByProperty("user_loginName", userName);
		if(currUser != null) {
			if(password.equals(currUser.getUser_loginPwd())) {
				Op_RoleInfo currRole = currUser.getRole_id();
				String role_id = currRole.getRole_id();
		  		if(null != role_id && !"".equals(role_id)){
		  			//只有管理员和运维人员拥有菜单树和设备树
		  			if("role-1".equals(role_id) || "role-2".equals(role_id)) {
		  				
		  				HttpSession session = Struts2Utils.getSession();
						session.setAttribute("user", currUser);
						session.setAttribute("userid", currUser.getUser_id());
						session.setAttribute("username", currUser.getUser_name());
		  				
		  				//设置菜单树
//						String menu_tree = this.sysFunService.findMenuTree(currRole);
//						session.setAttribute("menu_tree", menu_tree);
						//设置设备树
						String device_tree = this.deviceService.findDeviceTree_endsWithMinScene(currUser);
						session.setAttribute("device_tree", device_tree);
						
						//设置当前角色信息
						session.setAttribute("role", currRole);		
						
						List<Op_Scene> sceneList = this.userSceneService.findSceneListByUserId(currUser.getUser_id());
						session.setAttribute("sceneList", sceneList);
						Map<String, Op_Scene> sceneMap = new HashMap<String, Op_Scene>();
						for(Op_Scene op_Scene : sceneList){
							sceneMap.put(op_Scene.getScene_id(), op_Scene);
						}
						session.setAttribute("sceneMap", sceneMap);
						//设置场景树
						String scene_tree = this.sceneService.generateSceneTree(sceneList);
						session.setAttribute("scene_tree", scene_tree);
						
						//用户默认选中第一个基地的场景的 ID
				  		String scene_id = "";
				  		for(Op_Scene scene : sceneList) {
				  			if(scene.getScene_gtype() == 4) {
				  				scene_id = scene.getScene_id();
				  				logger.info("默认选中场景:" + scene.getScene_name());
				  				break;
				  			}
				  		}
				  		
				  		Struts2Utils.getRequest().setAttribute("scene_id", scene_id);
						
						logger.info("用户"+currUser.getUser_name()+"成功登录");
						return "success";
						
		  			}
		  		}
			}
		}
		message = "用户名或密码不正确,请重新登录!";
		return "login";
	}
	
	private String userName;
	private String password;
	private String message;

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
