package org.unism.gm.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.unism.gm.domain.Gm_Device;
import org.unism.gm.domain.Gm_DevstsHis;
import org.unism.gm.service.Gm_DeviceService;
import org.unism.gm.service.Gm_DevstsHisService;
import org.unism.op.service.Op_SceneService;
import org.unism.op.service.Op_UserInfo_SceneService;
import org.wangzz.core.orm.Page;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.web.struts2.Struts2Utils;

public class Gm_DevstsHisAction {

	/**
	 * 分页查询
	 * @return
	 * @author Wang_Yuliang
	 * @throws ParseException 
	 */
	public String page(){
		//System.out.println("bbbbbbbbbbbb");
		HttpSession session = Struts2Utils.getSession();
		String user_id = (String)session.getAttribute("userid");	
		//Op_UserInfo user = (Op_UserInfo)Struts2Utils.getSession().getAttribute("user");
		//List<String> list = this.gm_DeviceService.findDev_idByUser_id(user.getUser_id());
		List<String> list = this.gm_DeviceService.findDev_idByUser_id(user_id);
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
		if(scene_id!=null){
			List<String> scene_id_arr = new ArrayList<String>();
			scene_id_arr.add(this.scene_id);				
			List<String> scene_id_list = this.op_UserInfo_SceneService.findScene_idByUser_id(user_id);
			scene_id_arr = this.op_SceneService.findSceneTree_wangyuliang(scene_id_arr, scene_id_list);
			search.addFilterIn("dev_id.scene_id.scene_id", scene_id_arr);
		}else{
			Filter filter = Filter.and(Filter.in("dev_id.dev_id", list),Filter.and(Filter.greaterOrEqual("dest_lastCommTime", beginTime),Filter.lessOrEqual("dest_lastCommTime", endTime)));
			if(!this.hid_condition.equals("-1")){
				if(this.hid_condition.equals("dev_addr")){
					filter = Filter.and(Filter.in("dev_id.dev_id", list),Filter.like("dev_addr", this.hid_value),Filter.and(Filter.greaterOrEqual("dest_lastCommTime", beginTime),Filter.lessOrEqual("dest_lastCommTime", endTime)));
				}else if(this.hid_condition.equals("dev_name")){
					Search search_likeName = new Search();
					Filter filter_likeName = Filter.like("dev_name", this.hid_value);
					search_likeName.addFilter(filter_likeName);
					List<Gm_Device> gm_Device_list = this.gm_DeviceService.search(search_likeName);
					//空集合，查询的时候会报错 
					Gm_Device gg = new Gm_Device();gg.setDev_id("-1");
					gm_Device_list.add(gg);
					filter = Filter.and(Filter.in("dev_id.dev_id", list),Filter.in("dev_id", gm_Device_list),Filter.and(Filter.greaterOrEqual("dest_lastCommTime", beginTime),Filter.lessOrEqual("dest_lastCommTime", endTime)));
				}
			}
			search.addFilter(filter);
		}
				
		search.addSortDesc("dest_lastCommTime");
		this.page = this.gm_DevstsHisService.search(page, search);
		return "page";
	}
	
//分隔线----------------------------------------------------------------------------------------------------------
	@Autowired Gm_DevstsHisService gm_DevstsHisService;
	@Autowired Gm_DeviceService gm_DeviceService;
	@Autowired Op_UserInfo_SceneService op_UserInfo_SceneService;
	@Autowired Op_SceneService op_SceneService;
	
	private Gm_DevstsHis gm_DevstsHis = new Gm_DevstsHis();
	private Page<Gm_DevstsHis> page;
	private String hid_condition;
	private String hid_value;
	private Date beginTime;
	private Date endTime;
	private String scene_id;
	
	public Gm_DevstsHis getGm_DevstsHis() {
		return gm_DevstsHis;
	}
	public void setGm_DevstsHis(Gm_DevstsHis gm_DevstsHis) {
		this.gm_DevstsHis = gm_DevstsHis;
	}
	public Page<Gm_DevstsHis> getPage() {
		return page;
	}
	public void setPage(Page<Gm_DevstsHis> page) {
		this.page = page;
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
	public String getScene_id() {
		return scene_id;
	}
	public void setScene_id(String scene_id) {
		this.scene_id = scene_id;
	}
	
	
}
