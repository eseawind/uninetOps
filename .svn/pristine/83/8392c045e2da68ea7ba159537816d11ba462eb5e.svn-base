package org.unism.pro.action;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.unism.op.domain.Op_SceneFaultState;
import org.unism.op.service.Op_SceneService;
import org.unism.op.service.Op_UserInfo_SceneService;
import org.unism.pro.service.FaultTask;
import org.wangzz.core.orm.Page;
import org.wangzz.core.search.Search;
import org.wangzz.core.utils.DateUtil;
import org.wangzz.core.web.struts2.Struts2Utils;

import com.opensymphony.xwork2.ActionSupport;
/**
 * 处理捕获异常表单显示及查查询数据的Action
 * @author 杨昊
 *
 */
public class Pro_FaultTaskAction extends ActionSupport{
	@Autowired
	private FaultTask faultTask;
	@Autowired
	private Op_UserInfo_SceneService op_UserInfo_SceneService;
	@Autowired
	private Op_SceneService op_SceneService;
	private static final long serialVersionUID = 673758593289141928L;
	private String parentSceneId;
	private String selectedDate;
	private Page<Op_SceneFaultState> page=new Page<Op_SceneFaultState>();
	

	public Page<Op_SceneFaultState> getPage() {
		return page;
	}
	public void setPage(Page<Op_SceneFaultState> page) {
		this.page = page;
	}
	public String getParentSceneId() {
		return parentSceneId;
	}
	public void setParentSceneId(String parentSceneId) {
		this.parentSceneId = parentSceneId;
	}
	public String getSelectedDate() {
		return selectedDate;
	}
	public void setSelectedDate(String selectedDate) {
		this.selectedDate = selectedDate;
	}
	@SuppressWarnings("unchecked")
	public String findUserNeed() throws ParseException{
		List<String> childSceneIdList=findChildSceneId();
		Search search=new Search();
		if (childSceneIdList.size()>0) {
			search.addFilterIn("sfs_sceneId", childSceneIdList);
			if(StringUtils.hasText(selectedDate)){
				search.addFilterGreaterOrEqual("sfs_date",DateUtil.getDate(selectedDate+" 00:00:00","yyyy-MM-dd HH:mm:ss"));
				search.addFilterLessThan("sfs_date",DateUtil.getDate(selectedDate+" 23:59:59","yyyy-MM-dd HH:mm:ss"));
			}
			page=faultTask.search(page, search);
		}
		return "faultTable";
	}
	/**
	 * 查找子场景ID
	 * @return 返回子场景ID的List集合
	 * weixiaohua
	 * date 2012-06-25
	 */
	public List<String> findChildSceneId(){
		String userId=Struts2Utils.getSession().getAttribute("userid").toString();
		List<String> scene_id_arr = new ArrayList<String>();
		if(parentSceneId!=null){
			scene_id_arr.add(this.parentSceneId);				
			List<String> scene_id_list = this.op_UserInfo_SceneService.findScene_idByUser_id(userId);
			scene_id_arr = this.op_SceneService.findSceneTree_wangyuliang(scene_id_arr, scene_id_list);
		}
		return scene_id_arr;
	}
}
