package org.unism.op.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.unism.op.domain.Op_Supplier;
import org.unism.op.domain.Op_UserInfo;
import org.unism.op.service.Op_SupplierService;
import org.wangzz.core.orm.Page;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.web.struts2.Struts2Utils;

import com.opensymphony.xwork2.ActionSupport;

public class Op_SupplierAction extends ActionSupport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 查询所有
	 * @return
	 */
	public String findAll(){		
		List<Op_Supplier> list=op_SupplierService.findAll();
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
		}else if(post==1){	//第二次点添加提交值，并执行添加方法		
			try {
				this.op_SupplierService.save(this.op_Supplier);
				Struts2Utils.getRequest().setAttribute("result", "success");
				addActionMessage("操作成功……");
			} catch (Exception e) {
				addActionMessage("操作失败……");
			}
		}
		return "ok";
	}
	
	/**
	 * 根据ID查询
	 * @return
	 */
	public String findBysupid(){		
		Search search = new Search();
		Filter filter = Filter.equal("sup_id",this.op_Supplier.getSup_id());
		search.addFilter(filter);
		Struts2Utils.getRequest().setAttribute("list",this.op_SupplierService.search(search));
		return "edit";
	}
	/**
	 * 更新
	 * @return
	 */
	public String update() {
		try {
			if(op_Supplier!=null){		
				this.op_SupplierService.update(this.op_Supplier);			
				Struts2Utils.getRequest().setAttribute("result", "success");
				addActionMessage("操作成功……");
			}
		} catch (Exception e) {
			addActionMessage("操作失败……");
		}
		return "ok";
	}
	/**
	 * 根据ID删除
	 * @return
	 */
	public String delete() {
		try {
			this.op_SupplierService.deleteById(this.op_Supplier.getSup_id());
			Struts2Utils.getRequest().setAttribute("result", "success");
			addActionMessage("操作成功……");
		} catch (Exception e) {
			addActionMessage("操作失败……");
		}
		return "ok";
	}
//分割线--------------------------------------------------------------------------------------------------------------
	@Autowired Op_SupplierService op_SupplierService;
	private Op_Supplier op_Supplier =new Op_Supplier();
	private Page<Op_Supplier> page=new Page<Op_Supplier>();
	private int post;
	public int getPost() {
		return post;
	}

	public void setPost(int post) {
		this.post = post;
	}

	public Op_Supplier getOp_Supplier() {
		return op_Supplier;
	}

	public void setOp_Supplier(Op_Supplier op_Supplier) {
		this.op_Supplier = op_Supplier;
	}
	public Page<Op_Supplier> getPage() {
		return page;
	}
	public void setPage(Page<Op_Supplier> page) {
		this.page = page;
	}
}
