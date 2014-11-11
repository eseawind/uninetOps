package org.unism.gm.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.unism.gm.domain.Gm_DevCtrl;
import org.unism.gm.domain.Gm_DevCtrlStsHistory;
import org.unism.gm.service.Gm_DevCtrlService;
import org.unism.gm.service.Gm_DevCtrlStsHistoryService;
import org.unism.op.domain.Op_UserInfo;
import org.unism.op.service.Op_SceneService;
import org.unism.op.service.Op_UserInfo_SceneService;
import org.wangzz.core.orm.Page;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.web.struts2.Struts2Utils;

public class Gm_DevCtrlStsHistoryAction {

	/**
	 * 分页查询
	 * @return
	 */
	public String page(){
		//System.out.println("bbbbbbbbbbbb");
		HttpSession session = Struts2Utils.getSession();
		//String user_id = (String)session.getAttribute("userid");	
		Op_UserInfo user = (Op_UserInfo)Struts2Utils.getSession().getAttribute("user");
		List<String> list = this.gm_DevCtrlService.findDect_idByUser_id(user.getUser_id());		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat tem_df = new SimpleDateFormat("yyyy-MM-dd");
		Date tmp_d = new Date();
		if(this.beginTime==null){
			String tmp = tem_df.format(tmp_d);
			tmp = tmp.substring(0,10);
			tmp = tmp + " 00:00:00";
			try {
				this.beginTime = df.parse(tmp);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(this.endTime==null){
			String tmp = tem_df.format(tmp_d);
			tmp = tmp.substring(0,10);
			tmp = tmp + " 23:59:59";
			try {
				this.endTime = df.parse(tmp);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Search search = new Search();
		if(this.scene_id_arr!=null){
//			List<String> scene_id_arr = new ArrayList<String>();
//			scene_id_arr.add(this.scene_id);				
//			List<String> scene_id_list = this.op_UserInfo_SceneService.findScene_idByUser_id(user_id);
//			scene_id_arr = this.op_SceneService.findSceneTree_wangyuliang(scene_id_arr, scene_id_list);
			search.addFilterIn("dect_id.scene_id.scene_id", scene_id_arr);
		}else{
			Filter filter = Filter.and(Filter.in("dect_id.dect_id", list),Filter.and(Filter.greaterOrEqual("decst_time", beginTime),Filter.lessOrEqual("decst_time", endTime)));
			if(this.hid_condition.equals("dect_no")){
				Search search_nolike = new Search();
				Filter filter_nolike = Filter.and(Filter.equal("dect_state", 1),Filter.like("dect_no", this.hid_value));
				search_nolike.addFilter(filter_nolike);
				List<Gm_DevCtrl> gm_DevCtrl_nolike = this.gm_DevCtrlService.search(search_nolike);
				//如果是空集合的话会报错
				Gm_DevCtrl gg = new Gm_DevCtrl();
				gg.setDect_id("-1");
				gm_DevCtrl_nolike.add(gg);
				filter = Filter.and(Filter.in("dect_id.dect_id", list),Filter.in("dect_id", gm_DevCtrl_nolike),Filter.and(Filter.greaterOrEqual("decst_time", beginTime),Filter.lessOrEqual("decst_time", endTime)));
			}else if(this.hid_condition.equals("dect_state")){
				filter = Filter.and(Filter.in("dect_id.dect_id", list),Filter.equal("dect_state", Integer.parseInt(this.hid_value)),Filter.and(Filter.greaterOrEqual("decst_time", beginTime),Filter.lessOrEqual("decst_time", endTime)));
			}
			search.addFilter(filter);
		}
		search.addSortAsc("dect_id");
		search.addSortDesc("decst_time");
		this.page = this.gm_DevCtrlStsHistoryService.search(page, search);
		return "page";
	}
	
//分割线---------------------------------------------------------------------------------------------------
	@Autowired Gm_DevCtrlStsHistoryService gm_DevCtrlStsHistoryService;
	@Autowired Gm_DevCtrlService gm_DevCtrlService;
	@Autowired Op_UserInfo_SceneService op_UserInfo_SceneService;
	@Autowired Op_SceneService op_SceneService;
	
	private Gm_DevCtrlStsHistory gm_DevCtrlStsHistory = new Gm_DevCtrlStsHistory();
	private Page<Gm_DevCtrlStsHistory> page;
	private String hid_condition;
	private String hid_value;
	private Date beginTime;
	private Date endTime;
	//private String scene_id;
	private List<String> scene_id_arr;
	
	public String getScene_id_arr() {
		String scene_id_arr = "";
		if(this.scene_id_arr!=null && this.scene_id_arr.size()>0){
			for(String scene_id : this.scene_id_arr){
				scene_id_arr += "<input type=\"checkbox\" style=\"display: none;\" name=\"scene_id_arr\" value=\""+scene_id+"\" checked=\"checked\">";
			}
		}
		return scene_id_arr;
	}
	public void setScene_id_arr(List<String> scene_id_arr) {
		this.scene_id_arr = scene_id_arr;
	}
	public Date getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
	public Gm_DevCtrlStsHistory getGm_DevCtrlStsHistory() {
		return gm_DevCtrlStsHistory;
	}
	public void setGm_DevCtrlStsHistory(Gm_DevCtrlStsHistory gm_DevCtrlStsHistory) {
		this.gm_DevCtrlStsHistory = gm_DevCtrlStsHistory;
	}
	public Page<Gm_DevCtrlStsHistory> getPage() {
		return page;
	}
	public void setPage(Page<Gm_DevCtrlStsHistory> page) {
		this.page = page;
	}
	
	
}
