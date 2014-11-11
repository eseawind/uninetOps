package org.unism.op.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.unism.op.domain.Op_RoleInfo;
import org.unism.op.domain.Op_UserInfo;
import org.unism.op.service.Op_RoleInfoService;
import org.unism.op.service.Op_RoleRegithService;
import org.unism.op.service.Op_SysFunService;
import org.wangzz.core.web.struts2.Struts2Utils;

import com.opensymphony.xwork2.ActionSupport;


public class Op_RoleInfoAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1101481199440850324L;

	public String menuTree(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String menu = (String)session.getAttribute("to_menu_tree");
			//System.out.println("menu:"+menu);
			out.print(menu);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 查询所有
	 * @return
	 */
	public String findAll(){
		List<Op_RoleInfo> list=op_RoleInfoService.findAll();
		if(list!=null&&list.size()>0){			
			Struts2Utils.getRequest().setAttribute("list", list);
		}
		return "list";
	}
	
	/**
	 * 添加
	 * @return
	 */
	public String save(){
		//第一次点添加的时候，跳转到add页面
		if(post==0){			
			return "add";
		}else if(post==1){//第二次点添加提交值，并执行添加方法
			try {
				this.op_RoleInfoService.save(this.op_RoleInfo);			
				Struts2Utils.getRequest().setAttribute("result", "success");
				addActionMessage("操作成功……");
			} catch (Exception e) {
				addActionMessage("操作失败……");
			}
		}
		return "ok";
	}
	
	/**
	 * 更新
	 * @return
	 */
	public String update() {
		if(this.op_RoleInfo!=null){
			try {
				this.op_RoleInfoService.update(this.op_RoleInfo);			
				Struts2Utils.getRequest().setAttribute("result", "success");
				addActionMessage("操作成功……");
			} catch (Exception e) {
				addActionMessage("操作失败……");
			}
		}
		return "ok";
	}
	
	public String edit(){
		if(StringUtils.hasText(role_id)){
			Op_RoleInfo op_RoleInfo =this.op_RoleInfoService.findById(this.role_id);
			Struts2Utils.getRequest().setAttribute("op_RoleInfo", op_RoleInfo);
		}
		return "edit";
	}

	
	/**
	 * 根据ID查询
	 * @return
	 * @throws IOException 
	 */
	public String findByroleid() throws IOException{
		HttpSession session = Struts2Utils.getSession();
		//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>"+this.role_id);
		Op_RoleInfo op_RoleInfo =this.op_RoleInfoService.findById(this.role_id);
		String to_menu_tree= this.op_SysFunService.findMenuTree(op_RoleInfo);		
		HttpServletResponse response = Struts2Utils.getResponse();
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		//System.out.println("menu:"+to_menu_tree);
		out.print(to_menu_tree);
		
//		HttpSession session = Struts2Utils.getSession();
//		Search search = new Search();
//		System.out.println(">>>>>>>>>>>>>>>>role>>"+this.role_id);
//		Filter filter = Filter.equal("role_id",this.role_id);
//		search.addFilter(filter);		
//		//Struts2Utils.getRequest().setAttribute("list",op_RoleRegithService.findAll());		
//		Struts2Utils.getRequest().setAttribute("rolelist",op_RoleInfoService.findAll());		
//		Op_UserInfo user = (Op_UserInfo)Struts2Utils.getSession().getAttribute("user");//从session里获取用户实体		
//		Struts2Utils.getRequest().setAttribute("role",user.getRole_id());
//		List<Op_RoleInfo> roleinfo=this.op_RoleInfoService.search(search);
//		String to_menu_tree = this.op_SysFunService.findMenuTree(roleinfo.get(0));
//		session.setAttribute("to_menu_tree", to_menu_tree);
//		List<Op_RoleRegith> roleRegith=op_RoleRegithService.findNode_idByRole_id(roleinfo.get(0));		
//		Struts2Utils.getRequest().setAttribute("roleRegith",roleRegith);
		return null;
	}
	/**
	 * 根据ID删除
	 * @return
	 */
	public String delete() {
		try {
			op_RoleInfoService.deleteById(this.op_RoleInfo.getRole_id());
			Struts2Utils.getRequest().setAttribute("result", "success");
			addActionMessage("操作成功……");
		} catch (Exception e) {
			addActionMessage("操作失败……");
		}
		return "ok";
	}
//分割线--------------------------------------------------------------------------------------------------------------
	@Autowired Op_RoleInfoService op_RoleInfoService;
	@Autowired Op_RoleRegithService op_RoleRegithService;
	@Autowired Op_SysFunService op_SysFunService;
	private Op_RoleInfo op_RoleInfo=new Op_RoleInfo();
	private int post;
	private String role_id;
	
	public String getRole_id() {
		return role_id;
	}
	public void setRole_id(String role_id) {
		this.role_id = role_id;
	}
	public int getPost() {
		return post;
	}

	public void setPost(int post) {
		this.post = post;
	}

	public Op_RoleInfo getOp_RoleInfo() {
		return op_RoleInfo;
	}

	public void setOp_RoleInfo(Op_RoleInfo op_RoleInfo) {
		this.op_RoleInfo = op_RoleInfo;
	}
}
