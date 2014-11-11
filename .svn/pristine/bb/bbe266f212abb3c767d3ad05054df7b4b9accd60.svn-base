package org.unism.op.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.unism.op.domain.Op_RoleInfo;
import org.unism.op.domain.Op_Scene;
import org.unism.op.domain.Op_UserInfo;
import org.unism.op.domain.Op_UserInfo_Scene;
import org.unism.op.service.Op_SceneService;
import org.unism.op.service.Op_UserInfoService;
import org.unism.op.service.Op_UserInfo_SceneService;
import org.unism.web.action.LoginAction;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.web.struts2.Struts2Utils;

public class Op_UserInfo_SceneAction {

	private Logger logger = Logger.getLogger(LoginAction.class);
	
	/**
	 * 到用户场景关联信息管理
	 * @return
	 * @author Wang_Yuliang
	 */
	public String toManage(){
		List<Op_UserInfo> op_UserInfo_list = new ArrayList<Op_UserInfo>();
		Op_RoleInfo role = (Op_RoleInfo)Struts2Utils.getSession().getAttribute("role");
		if(role!=null && role.getRole_id()!=null){
			if("role-1".equals(role.getRole_id())){
				op_UserInfo_list = op_UserInfoService.findAll();
			}else if("role-2".equals(role.getRole_id())){
				List<String> role_id_list = new ArrayList<String>();
				role_id_list.add("role-1");
				role_id_list.add("role-2");
				op_UserInfo_list = op_UserInfoService.search(new Search().addFilterNotIn("role_id.role_id", role_id_list));
			}
		}
		Struts2Utils.getSession().setAttribute("op_UserInfo_list", op_UserInfo_list);
		return "manage";
	}
	
	/**
	 * 指定用户ID 构建场景树（树结构中包含了所有在用场景，与指定用户关联的CB为选中状态）
	 * @return
	 * @author Wang_Yuliang
	 * 未完成 ，未使用 的方法 Wang_Yuliang 0627 UP
	 */
	public String findSceneTree(){
		//HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String scene_tree = "[";
			List<Op_Scene> op_Scene_list = this.op_SceneService.findAllEq("scene_state", 1);
			
			
			//System.out.println("scene_tree:"+scene_tree);
			out.print(scene_tree);
		} catch (IOException e) {
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
	 * 加载树
	 * @return json [
	 * 					{ id:1, pid:0, name:"Hunter's Home", url:"#",open:true,checked:true},
	 * 					...	
	 * 				]
	 * @author Wang_Yuliang
	 */
	public String loadTree(){
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String json = this.op_UserInfo_SceneService.loadTree(this.user_id);
			//System.out.println("tree:"+json);
			out.print(json);
		} catch (IOException e) {
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
	 * 管理
	 * @return
	 * @author Wang_Yuliang
	 */
	public String manage(){
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			if(this.node_id_list==null){out.print("false");return null;}
			if(this.user_id==null){out.print("false");return null;}
			String[] node_id_arr = new String[0];
			if(node_id_list.length()>0)node_id_arr = node_id_list.split(",");
			this.op_UserInfo_SceneService.manage(this.user_id, node_id_arr);
			out.print("true");
			logger.info("用户"+Struts2Utils.getSession().getAttribute("userid")+" 用户场景关联信息管理 》保存 操作成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("false");
			}catch(Exception ex){ex.printStackTrace();}
		}
		return null;	
	}

//分割线-------------------------------------------------------------------------------------------------------------
	@Autowired Op_UserInfo_SceneService op_UserInfo_SceneService;
	@Autowired Op_UserInfoService op_UserInfoService;
	@Autowired Op_SceneService op_SceneService;
	
	private List<Op_UserInfo_Scene> list = new ArrayList<Op_UserInfo_Scene>();
	private Op_UserInfo_Scene op_UserInfo_Scene = new Op_UserInfo_Scene();
	private String user_id; //加载树
	private String node_id_list; //管理 选中的场景id 格式为XXX,XXA,XXB,XXC,XXD,...
	
	public String getNode_id_list() {
		return node_id_list;
	}

	public void setNode_id_list(String node_id_list) {
		this.node_id_list = node_id_list;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public List<Op_UserInfo_Scene> getList() {
		return list;
	}
	public void setList(List<Op_UserInfo_Scene> list) {
		this.list = list;
	}
	public Op_UserInfo_Scene getOp_UserInfo_Scene() {
		return op_UserInfo_Scene;
	}
	public void setOp_UserInfo_Scene(Op_UserInfo_Scene op_UserInfo_Scene) {
		this.op_UserInfo_Scene = op_UserInfo_Scene;
	}
	
	
}
