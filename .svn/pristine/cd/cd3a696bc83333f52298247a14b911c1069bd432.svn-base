package org.unism.op.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.unism.op.domain.Op_PlantformUser;
import org.unism.op.service.Op_PlantformUserService;
import org.unism.op.service.Op_SceneService;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.web.struts2.Struts2Utils;



public class Op_PlantformUserAction {
	
	/**
	 * 查询所有
	 * @return
	 */
	public String findAll(){
		List<Op_PlantformUser> list=op_PlantformUserService.findAll();
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
			Struts2Utils.getRequest().setAttribute("list", this.op_SceneService.findAll());
			return "add";
		}else if(post==1){//第二次点添加提交值，并执行添加方法
			this.op_PlantformUserService.save(this.op_PlantformUser);
			Struts2Utils.getRequest().setAttribute("result", "success");
			return "operationResult";
		}
		return "operationResult";
	}
	
	/**
	 * 更新
	 * @return
	 */
	public String update() {
		if(this.op_PlantformUser!=null){			
			this.op_PlantformUserService.update(this.op_PlantformUser);			
			Struts2Utils.getRequest().setAttribute("result", "success");
			return "operationResult";
		}
		return "operationResult";
	}

	
	/**
	 * 根据ID查询
	 * @return
	 */
	public String findByplaid(){		
		Search search = new Search();
		Filter filter = Filter.equal("pla_id",this.op_PlantformUser.getPla_id());
		search.addFilter(filter);
		Struts2Utils.getRequest().setAttribute("scene", this.op_SceneService.findAll());
		Struts2Utils.getRequest().setAttribute("list",this.op_PlantformUserService.search(search));
		return "edit";
	}
	/**
	 * 根据ID删除
	 * @return
	 */
	public String delete() {
		op_PlantformUserService.deleteById(this.op_PlantformUser.getPla_id());
		Struts2Utils.getRequest().setAttribute("result", "success");
		return "operationResult";
	}
//分割线--------------------------------------------------------------------------------------------------------------
	@Autowired
	Op_PlantformUserService op_PlantformUserService;
	@Autowired Op_SceneService op_SceneService;
	private Op_PlantformUser op_PlantformUser=new Op_PlantformUser();
	private int post;
	public Op_PlantformUser getOp_PlantformUser() {
		return op_PlantformUser;
	}
	public void setOp_PlantformUser(Op_PlantformUser op_PlantformUser) {
		this.op_PlantformUser = op_PlantformUser;
	}
	public int getPost() {
		return post;
	}
	public void setPost(int post) {
		this.post = post;
	}
}
