package org.unism.gm.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.unism.gm.domain.Gm_DevCtrlSts;
import org.unism.gm.service.Gm_DevCtrlService;
import org.unism.gm.service.Gm_DevCtrlStsService;
import org.unism.op.service.Op_SceneService;
import org.unism.op.service.Op_UserInfo_SceneService;
import org.wangzz.core.orm.Page;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.web.struts2.Struts2Utils;

public class Gm_DevCtrlStsAction
{
	@SuppressWarnings("unchecked")
	public String page(){
		String userId = (String) Struts2Utils.getSession().getAttribute("userid");
		List<String> dect_idList = new ArrayList<String>();
		//通过userId查控制设备id
		List<String> dectIdList = this.gm_DevCtrlService.findDect_idByUser_id(userId);
		Search search = new Search();
		Filter filter = new Filter();
		if(StringUtils.hasText(queryName) && StringUtils.hasText(queryValue)){
			if(queryName.equals("dect_no")){
				dect_idList = gm_DevCtrlService.findDectIdByDect_no(queryValue,dectIdList);
			}
			if(queryName.equals("scene_name")){
				List<String> scene_idList = op_SceneService.findLikeSceneName(queryValue);
				dect_idList = gm_DevCtrlService.findDectIdByscene_idList(scene_idList,dectIdList);
			}
			if(queryName.equals("dect_state")){
				filter = Filter.equal("dect_state", Integer.parseInt(queryValue));
				//search.addFilter(filter);
			}
		}else {
			dect_idList = dectIdList;
		}
		if(queryName != null){
			if(!queryName.equals("dect_state")){
				dect_idList.add("-1");
				filter = Filter.in("dect_id.dect_id", dect_idList);
			}
		}else {
			dect_idList.add("-1");
			filter = Filter.in("dect_id.dect_id", dect_idList);
		}
		search.addFilter(filter);
		search.addSortDesc("decst_time");
		this.page = this.gm_DevCtrlStsService.search(page, search);
		return "page";
	}
	
	
//---------------------------------------------------------
	@Autowired
	Gm_DevCtrlService gm_DevCtrlService;
	
	@Autowired
	Gm_DevCtrlStsService gm_DevCtrlStsService;
	
	@Autowired
	Op_UserInfo_SceneService op_UserInfo_SceneService;
	
	@Autowired
	Op_SceneService op_SceneService;
	
	private String scene_id;
	
	private Page<Gm_DevCtrlSts> page = new Page<Gm_DevCtrlSts>();
	
	private String queryName;
	
	private String queryValue;
	
	private String flag;

	public String getQueryName()
	{
		return queryName;
	}


	public void setQueryName(String queryName)
	{
		this.queryName = queryName;
	}


	public String getQueryValue()
	{
		return queryValue;
	}


	public void setQueryValue(String queryValue)
	{
		this.queryValue = queryValue;
	}


	public Page<Gm_DevCtrlSts> getPage()
	{
		return page;
	}


	public void setPage(Page<Gm_DevCtrlSts> page)
	{
		this.page = page;
	}


	public String getScene_id()
	{
		return scene_id;
	}


	public void setScene_id(String scene_id)
	{
		this.scene_id = scene_id;
	}


	public String getFlag()
	{
		return flag;
	}


	public void setFlag(String flag)
	{
		this.flag = flag;
	}
	
	
}
