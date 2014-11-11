package org.unism.op.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.unism.gm.domain.Gm_DevCtrl;
import org.unism.gm.service.Gm_DevCtrlOperateService;
import org.unism.gm.service.Gm_DevCtrlService;
import org.unism.gm.service.Gm_DevCtrlStsService;
import org.unism.gm.service.Gm_DeviceService;
import org.unism.op.domain.Op_DevCtrlBtn;
import org.unism.op.domain.Op_UserInfo;
import org.unism.op.service.Op_DevCtrlTypeService;
import org.unism.op.service.Op_SceneService;
import org.wangzz.core.orm.Page;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.web.struts2.Struts2Utils;

public class Op_DevCtrlBtnAction {

	/**
	 * 分页查询
	 * @return
	 */	
	public String findAll(){
		//System.out.println(queryValue);
		HttpSession session = Struts2Utils.getSession();
		if(session==null){
			return "sessinNull";
		}else{
//			Op_UserInfo user = (Op_UserInfo)Struts2Utils.getSession().getAttribute("user");
//			List<String> list = this.gm_DevCtrlService.findDect_idByUser_id(user.getUser_id());	//当前用户所关联的场景下的控制设备		
//			try{
//				Search search = new Search();
//				Filter filter = new Filter();//.and(Filter.equal("dect_state", 1),Filter.in("dect_id", list));
//				if(this.hid_condition != null && !this.hid_condition.equals("")){	
//					if(this.hid_condition.equals("desc_no")){//控制设备编号
//						List<String> dev_id_arr_byno = this.gm_DevCtrlService.findDev_id_arrByDev_no(this.hid_value);
//						String queryName="desc_no";
//						List<String> dect_id_by_desc_no=this.gm_DevCtrlService.dect_id_by_desc_no(queryName,this.hid_value);//获得所有符合条件的控制设备的ID
//						filter = Filter.and(Filter.equal("dect_state", 1),Filter.in("dev_id.dev_id", dev_id_arr_byno),Filter.in("dect_id", list));
//					}else if(this.hid_condition.equals("scene_name")){//所属设备编号
//						List<String> scene_id_arr_byname = this.op_SceneService.findScene_id_arrByPname(this.hid_value);
//						filter = Filter.and(Filter.equal("dect_state", 1),Filter.in("scene_id.scene_id", scene_id_arr_byname),Filter.in("dect_id", list));
//					}else{
//						filter = Filter.and(Filter.equal("dect_state", 1),Filter.like(this.hid_condition, this.hid_value),Filter.in("dect_id", list));
//					}				
//				}else{}
//				
//				search.addFilter(filter);
//				this.page = this.gm_DevCtrlService.search(page, search);
//			}catch (Exception e) {
//				// TODO: handle exception
//				System.out.println(">>>>>>>>>>>>>>>>>>>Error");
//				e.printStackTrace();
//			}
			return "page";
		}
	}
	//分割线--------------------------------------------------------------------------------------------------------------
	@Autowired Gm_DevCtrlService gm_DevCtrlService;
	private String queryValue;
	private String hid_condition;
	private String hid_value;
	private Page<Op_DevCtrlBtn> page=new Page<Op_DevCtrlBtn>();
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
	public String getQueryValue() {
		return queryValue;
	}
	public void setQueryValue(String queryValue) {
		this.queryValue = queryValue;
	}
	public Page<Op_DevCtrlBtn> getPage() {
		return page;
	}
	public void setPage(Page<Op_DevCtrlBtn> page) {
		this.page = page;
	}
}
