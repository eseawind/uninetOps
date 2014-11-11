package org.unism.op.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.unism.op.domain.Op_RoleInfo;
import org.unism.op.domain.Op_Scene;
import org.unism.op.domain.Op_UserInfo;
import org.unism.op.service.Op_RoleInfoService;
import org.unism.op.service.Op_SysFunService;
import org.unism.op.service.Op_UserInfoService;
import org.unism.op.service.Op_UserInfo_SceneService;
import org.wangzz.core.orm.Page;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.web.struts2.Struts2Utils;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("unchecked")
public class Op_UserInfoAction extends ActionSupport{
	
	private static final long serialVersionUID = -2640152723253516113L;

	/**
	 * 分页查询
	 * @return
	 */	
	public String page(){		
		Search search = new Search();
		Op_UserInfo user = (Op_UserInfo)Struts2Utils.getSession().getAttribute("user");			
		Filter filter = Filter.equal("user_name",user.getUser_name());
		search.addFilter(filter);
		//System.out.println(">>>>>>>>>>>>>>>>>>");
		try{			
			if(this.page==null){
				//System.out.println("NULL");
			}
			this.page= this.op_UserInfoService.search(page,search);
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return "page";
	}
	
	/**
	 * 根据user_id 查询
	 * @return
	 */	
	public String findByUserId(){
		Search search = new Search();
		Filter filter = Filter.equal("user_id",this.op_UserInfo.getUser_id());
		search.addFilter(filter);
		Struts2Utils.getRequest().setAttribute("roleInfo", op_RoleInfoService.findAll());
		Struts2Utils.getRequest().setAttribute("list",this.op_UserInfoService.search(search));
		return "edit";
	}

	/**
	 * 查询所有用户信息
	 * @return
	 */	
	public String findAll() {
		StringBuffer buffer = new StringBuffer();
		List<Op_RoleInfo> roleList = op_RoleInfoService.findAll();
		for (Op_RoleInfo opRoleInfo : roleList) {
			if(!"role-1".equals(opRoleInfo.getRole_id())){
				buffer.append("<option value='"+opRoleInfo.getRole_id()+"'>"+opRoleInfo.getRole_name());
				buffer.append("</option>");
			}
		}
		Struts2Utils.getRequest().setAttribute("role", buffer.toString());
		Search search = new Search();
		Op_RoleInfo opRoleInfo = (Op_RoleInfo) Struts2Utils.getSession().getAttribute("role");
		if(opRoleInfo.getRole_id().equals("role-2")){
			List<String> roleIds = new ArrayList<String>();
			roleIds.add("role-1");
			roleIds.add("role-2");
			search.addFilterNotIn("role_id.role_id", roleIds);
		}
		if(StringUtils.hasText(queryName)){
			if("user_loginName".equals(queryName) || "user_name".equals(queryName)){
				search.addFilterLike(queryName, queryValue);
			}
			if("role_id".equals(queryName)){
				search.addFilterEqual("role_id.role_id", queryValue);
			}
		}
		page = this.op_UserInfoService.search(page, search);
		return "list";
	}
	
	/**
	 * 添加用户信息
	 * @return
	 */	
	public String save() {
		//第一次点添加的时候，跳转到add页面
		if(post==0){	
			Struts2Utils.getRequest().setAttribute("list", op_RoleInfoService.findAll());
			return "add";
		}else if(post==1){//第二次点添加提交值，并执行添加方法
			if(this.op_UserInfoService.isExist(this.op_UserInfo.getUser_loginName())){
				Struts2Utils.getRequest().setAttribute("loginname", "用户名已存在");
				return "operationResult";
			}else {
				try {
					HttpSession session = Struts2Utils.getSession();
					if(session!=null){
						Object object=session.getAttribute("username");
						if(object!=null&&!"".equals(object)){
							String user_creater=object.toString();//创建人
							this.op_UserInfo.setUser_creater(user_creater);
						}
					}
					this.op_UserInfo.setUser_enable(1);
					this.op_UserInfoService.save(this.op_UserInfo);
					Struts2Utils.getRequest().setAttribute("result", "success");
					addActionMessage("操作成功……");
				} catch (Exception e) {
					e.printStackTrace();
					addActionMessage("操作失败……");
				}				
			}
		}
		return "ok";
	}

	/**
	 * 修改用户信息
	 * @return
	 */	
	public String update() {
		if(op_UserInfo!=null){					
			try {
				HttpSession session = Struts2Utils.getSession();
				if(session!=null){
					Object object=session.getAttribute("username");
					if(object!=null&&!"".equals(object)){
						String user_creater=object.toString();//创建人
						this.op_UserInfo.setUser_creater(user_creater);
					}
				}
				this.op_UserInfoService.update(this.op_UserInfo);			
				Struts2Utils.getRequest().setAttribute("result", "success");
				addActionMessage("操作成功……");
			} catch (Exception e) {
				addActionMessage("操作失败……");
			}
		}
		return "ok";
	}
	
	/**
	 * 删除用户信息
	 * @return
	 */	
	public String delete() {
		try {
			op_UserInfoService.deleteById(this.op_UserInfo.getUser_id());
			Struts2Utils.getRequest().setAttribute("result", "success");
			addActionMessage("操作成功……");
		} catch (Exception e) {
			addActionMessage("操作失败……");
		}
		return "ok";
	}
	
	public String editPwd() {
		Object obUser = Struts2Utils.getSession().getAttribute("user");
		if(obUser != null) {
			Struts2Utils.getRequest().setAttribute("user", (Op_UserInfo)obUser);
		}
		return "editPwd";
	}
	
	public String updatePwd() {
		String uid = (String) Struts2Utils.getRequest().getParameter("uid");
		String pwd = (String) Struts2Utils.getRequest().getParameter("loginPwd");
		Op_UserInfo user = op_UserInfoService.findById(uid);
		user.setUser_loginPwd(pwd);
		op_UserInfoService.update(user);
		Struts2Utils.getRequest().setAttribute("message", "ok");
		return "editPwd";
	}
	
	/**
	 * unismPhone 登录
	 * @author Wang_Yuliang
	 */
	public String login_unismPhone(){
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html; charset=utf-8");
		try {
			PrintWriter out = response.getWriter();
			
			Op_UserInfo currUser = op_UserInfoService.findUniqueByProperty("user_loginName", op_UserInfo.getUser_loginName());
			if(currUser != null) {
				if(op_UserInfo.getUser_loginPwd().equals(currUser.getUser_loginPwd())) {
					List<Op_Scene> sceneList = this.op_UserInfo_SceneService.findSceneListByUserId(currUser.getUser_id());
					StringBuffer sb = new StringBuffer();
					sb.append("{suc:1,sceneList:[");
					for(Op_Scene op_Scene : sceneList){
						sb.append("{scene_id:'")
							.append(op_Scene.getScene_id())
							.append("',scene_no:'")
							.append(op_Scene.getScene_no())
							.append("',scene_name:'")
							.append(op_Scene.getScene_name())
							.append("'},");
					}
					if(sb.length()>18)
						sb.delete(sb.length()-1,sb.length());
					sb.append("]}");
				}
			}	
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
//分割线--------------------------------------------------------------------------------------------------------------
	@Autowired Op_UserInfoService op_UserInfoService;
	@Autowired Op_SysFunService op_SysFunService;
	@Autowired Op_RoleInfoService op_RoleInfoService;
	@Autowired Op_UserInfo_SceneService op_UserInfo_SceneService;
	private Op_UserInfo op_UserInfo=new Op_UserInfo();
	private Page<Op_UserInfo> page=new Page<Op_UserInfo>();
	private int post;
	
	private String queryName;
	
	private String queryValue;

	public String getQueryName() {
		return queryName;
	}

	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	public String getQueryValue() {
		return queryValue;
	}

	public void setQueryValue(String queryValue) {
		this.queryValue = queryValue;
	}

	public int getPost() {
		return post;
	}

	public void setPost(int post) {
		this.post = post;
	}

	public Op_UserInfo getOp_UserInfo() {
		return op_UserInfo;
	}

	public void setOp_UserInfo(Op_UserInfo op_UserInfo) {
		this.op_UserInfo = op_UserInfo;
	}
	public Page<Op_UserInfo> getPage() {
		return page;
	}
	public void setPage(Page<Op_UserInfo> page) {
		this.page = page;
	}	
}
