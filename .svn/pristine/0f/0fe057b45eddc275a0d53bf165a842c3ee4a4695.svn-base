package org.unism.op.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.unism.op.domain.Op_RoleInfo;
import org.unism.op.domain.Op_RoleRegith;
import org.unism.op.domain.Op_UserInfo;
import org.unism.op.service.Op_RoleInfoService;
import org.unism.op.service.Op_RoleRegithService;
import org.unism.op.service.Op_SysFunService;
import org.wangzz.core.web.struts2.Struts2Utils;

import com.opensymphony.xwork2.ActionSupport;

public class Op_RoleRegithAction extends ActionSupport{
	
	public String menuTree(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String menu = (String)session.getAttribute("menu_tree");
			//System.out.println("menu:"+menu);
			out.print(menu);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public String permission() {
		//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>"+post);		
		//第一次点添加的时候，跳转到add页面
		if(post==0){
			HttpSession session = Struts2Utils.getSession();
			String menu_tree = this.op_RoleRegithService.findMenuTree();			
			session.setAttribute("menu_tree", menu_tree);						
			Struts2Utils.getRequest().setAttribute("rolelist",op_RoleInfoService.findAll());
			Op_UserInfo user = (Op_UserInfo)Struts2Utils.getSession().getAttribute("user");//从session里获取用户实体
			Struts2Utils.getRequest().setAttribute("role",user.getRole_id());
			return "permission";
		}else if(post==1){
			try {
				String[] arr = nodeID_arr.split(",");
				List<Op_RoleRegith> RoleRegith_list =this.op_RoleRegithService.findAllEq("role_id.role_id", this.op_RoleInfo.getRole_id());
				for(Op_RoleRegith RoleRegith : RoleRegith_list){
					this.op_RoleRegithService.deleteById(RoleRegith.getId());
				}
				if(arr.length > 1){
					for (int i = 0; i < arr.length; i++) {
						Op_RoleRegith roleRegith = new Op_RoleRegith();
						roleRegith.setOpRoleInfo(op_RoleInfoService.findById(this.op_RoleInfo.getRole_id()));
						roleRegith.setOpSysFun(op_SysFunService.findById(arr[i].toString()));
						this.op_RoleRegithService.save(roleRegith);
					}
				}
				Struts2Utils.getRequest().setAttribute("result", "success");
				addActionMessage("操作成功……");
			} catch (Exception e) {
				addActionMessage("操作失败……");
			}
		}
		return "ok";
	}
//分割线--------------------------------------------------------------------------------------------------------------
	@Autowired Op_RoleRegithService op_RoleRegithService;
	@Autowired Op_RoleInfoService op_RoleInfoService;
	@Autowired Op_SysFunService op_SysFunService;
	private Op_RoleRegith op_RoleRegith;
	private Op_RoleInfo op_RoleInfo;
	private int post;
	private String nodeID_arr;
	
	public String getNodeID_arr() {
		return nodeID_arr;
	}
	public void setNodeID_arr(String nodeID_arr) {
		this.nodeID_arr = nodeID_arr;
	}
	public Op_RoleRegith getOp_RoleRegith() {
		return op_RoleRegith;
	}
	public void setOp_RoleRegith(Op_RoleRegith op_RoleRegith) {
		this.op_RoleRegith = op_RoleRegith;
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
