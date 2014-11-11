package org.unism.gm.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.unism.gm.action.form.DevCtrlBtnForm;
import org.unism.gm.action.form.DevCtrlStsForm;
import org.unism.gm.domain.Gm_DevCtrl;
import org.unism.gm.domain.Gm_Device;
import org.unism.gm.service.Gm_ChannelService;
import org.unism.gm.service.Gm_DevChannelService;
import org.unism.gm.service.Gm_DevCtrlOperateService;
import org.unism.gm.service.Gm_DevCtrlService;
import org.unism.gm.service.Gm_DevCtrlStsService;
import org.unism.gm.service.Gm_DeviceService;
import org.unism.op.domain.Op_Scene;
import org.unism.op.domain.Op_UserInfo;
import org.unism.op.service.Op_DevCtrlBtnService;
import org.unism.op.service.Op_DevCtrlStsService;
import org.unism.op.service.Op_DevCtrlTypeService;
import org.unism.op.service.Op_SceneService;
import org.unism.op.service.Op_UserInfo_SceneService;
import org.unism.web.action.LoginAction;
import org.wangzz.core.orm.Page;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.web.struts2.Struts2Utils;

import com.opensymphony.xwork2.ActionSupport;


@SuppressWarnings("unchecked")
public class Gm_DevCtrlAction extends ActionSupport {
	
	private static final long serialVersionUID = -6610737798067065565L;
	private Logger logger = Logger.getLogger(LoginAction.class);
	
	/**
	 * 分页查询
	 * @return
	 */	
	public String findAll(){
		Op_UserInfo user = (Op_UserInfo)Struts2Utils.getSession().getAttribute("user");
		List<String> list = this.gm_DevCtrlService.findDect_idByUser_id(user.getUser_id());			
		try{
			Search search = new Search();
			Filter filter = Filter.in("dect_id", list);
			if(this.hid_condition != null && !this.hid_condition.equals("")){	
				if(this.hid_condition.equals("dev_no")){
					List<String> dev_id_arr_byno = this.gm_DevCtrlService.findDev_id_arrByDev_no(this.hid_value);
					filter = Filter.and(Filter.in("dev_id.dev_id", dev_id_arr_byno),Filter.in("dect_id", list));
				}else if(this.hid_condition.equals("scene_name")){
					List<String> scene_id_arr_byname = this.op_SceneService.findScene_id_arrByPname(this.hid_value);
					filter = Filter.and(Filter.in("scene_id.scene_id", scene_id_arr_byname),Filter.in("dect_id", list));
				}else{
					filter = Filter.and(Filter.like(this.hid_condition, this.hid_value),Filter.in("dect_id", list));
				}				
			}else{}
			search.addSortAsc("dect_no");
			search.addFilter(filter);
			this.page = this.gm_DevCtrlService.search(page, search);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "page";
	}

	
	public String add() {
		List<Op_Scene> sceneList = (List<Op_Scene>) Struts2Utils.getSession().getAttribute("sceneList");
		Struts2Utils.getRequest().setAttribute("sceneList", sceneList);
		
		String scene_id_list = "[";
		for(Op_Scene op_Scene : sceneList){
			scene_id_list += "{";
			scene_id_list += "id:'"+op_Scene.getScene_id()+"',";
			if(op_Scene.getScene_pid()!=null){
				scene_id_list += "pid:'"+op_Scene.getScene_pid()+"',";
			}else{
				scene_id_list += "pid:0,";
			}
			scene_id_list += "name:'"+op_Scene.getScene_name()+"'";
			scene_id_list += "},";
		}
		if(scene_id_list.length()>1){
			scene_id_list = scene_id_list.substring(0,(scene_id_list.length()-1));
		}
		Struts2Utils.getRequest().setAttribute("scenes", scene_id_list+"]");
				
		Search search2 = new Search();
		search2.addFilterIn("scene_id", sceneList);
		List<Gm_Device> devices = gm_DeviceService.search(search2);
		Struts2Utils.getRequest().setAttribute("device", devices);
		
		Struts2Utils.getRequest().setAttribute("devctrtype", this.op_DevCtrlTypeService.findAll());
		
		return "add";
	}
	
	/**
	 * 保存
	 */
	public String save(){
		try {
			if(devCtrlBtnForm == null)
				return null;
			gm_DevCtrlService.save(gm_DevCtrl, devCtrlBtnForm, devCtrlStsForm);
			addActionMessage("添加成功.");
			logger.info("用户"+Struts2Utils.getSession().getAttribute("userid")+" 控制设备信息管理 》 添加 提交 操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage("添加失败.");
		}	
		return "success";
	}
	
	
	public String edit() {
		List<Op_Scene> sceneList = (List<Op_Scene>) Struts2Utils.getSession().getAttribute("sceneList");
		Struts2Utils.getRequest().setAttribute("sceneList", sceneList);

		String scene_id_list = "[";
		for(Op_Scene op_Scene : sceneList){
			scene_id_list += "{";
			scene_id_list += "id:'"+op_Scene.getScene_id()+"',";
			if(op_Scene.getScene_pid()!=null){
				scene_id_list += "pid:'"+op_Scene.getScene_pid()+"',";
			}else{
				scene_id_list += "pid:0,";
			}
			scene_id_list += "name:'"+op_Scene.getScene_name()+"'";
			scene_id_list += "},";
		}
		if(scene_id_list.length()>1){
			scene_id_list = scene_id_list.substring(0,(scene_id_list.length()-1));
		}
		Struts2Utils.getRequest().setAttribute("scenes", scene_id_list+"]");
		
		gm_DevCtrl = gm_DevCtrlService.findById(id);

		Search search2 = new Search();
		search2.addFilterIn("scene_id", sceneList);
		List<Gm_Device> devices = gm_DeviceService.search(search2);
		Struts2Utils.getRequest().setAttribute("deviceList", devices);
		Struts2Utils.getRequest().setAttribute("devctrtype",this.op_DevCtrlTypeService.findAll());
		Struts2Utils.getRequest().setAttribute("devCtrlBtnList", this.op_DevCtrlBtnService.findAllEq("dect_id", id));
		Struts2Utils.getRequest().setAttribute("devCtrlStsList", opDevCtrlStsService.findAllEq("dect_id.dect_id", id));
		Struts2Utils.getRequest().setAttribute("devChannels", gmDevChannelService.findAllEq("dev_id.dev_id", devId));
		Struts2Utils.getRequest().setAttribute("devCtrlStsList", opDevCtrlStsService.findAllEq("dect_id.dect_id", id));
		return "edit";
	}
	
	/**
	 * 更新
	 * @return
	 * @throws IOException 
	 */
	public String update(){
		if(this.gm_DevCtrl!=null){
			try {
				this.gm_DevCtrlService.update(this.gm_DevCtrl, devCtrlBtnForm, devCtrlStsForm);
				addActionMessage("修改成功.");
				logger.info("用户"+Struts2Utils.getSession().getAttribute("userid")+" 控制设备信息管理 》 编辑 提交 操作成功");
			} catch (Exception e) {
				e.printStackTrace();
				addActionMessage("修改失败.");
			}			
		}
		return "success";
	}
	
	/**
	 * 根据ID查询
	 * @return
	 */
	public String findBydectid(){		
		Search search = new Search();
		Filter filter = Filter.equal("dect_id",this.gm_DevCtrl.getDect_id());
		search.addFilter(filter);
		Struts2Utils.getRequest().setAttribute("devctrtype",this.op_DevCtrlTypeService.findAll());
		Struts2Utils.getRequest().setAttribute("scene",this.op_SceneService.findAll());
		Struts2Utils.getRequest().setAttribute("device",this.gm_DeviceService.findAll());
		Struts2Utils.getRequest().setAttribute("list",this.gm_DevCtrlService.search(search));
		return "edit";
	}
	/**
	 * 根据ID删除
	 * @return
	 */
	public String delete() {
		try {
			gm_DevCtrlService.deleteById(id);
			addActionMessage("删除成功.");
			logger.info("用户"+Struts2Utils.getSession().getAttribute("userid")+" 控制设备信息管理 》 删除 提交 操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			addActionMessage("删除失败.");
		}
		return "success";
	}
	
	public String check() {
		try {
			String code = Struts2Utils.getRequest().getParameter("code");
			String oldCode = Struts2Utils.getRequest().getParameter("oldCode");
			String result = String.valueOf(gm_DevCtrlService.isPropertyUnique("dect_no", code, oldCode));
			Struts2Utils.renderText(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 加载状态通道编号
	 * @return
	 * @auther Wang_Yuliang
	 */
	public String json_jiazaizhuangtaitongdaobianhao(){
		try
		{
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			String json = this.gm_ChannelService.json_jiazaizhuangtaitongdaobianhao(gm_DevCtrl.getDev_id().getDev_id(),q);
			out.print(json);
		} catch (Exception e)
		{
			e.printStackTrace();
			try
			{
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("[]");
			} catch (Exception e1)
			{e.printStackTrace();
			}
		}
		return null;
	}

//分割线--------------------------------------------------------------------------------------------------------------
	@Autowired Gm_DevCtrlService gm_DevCtrlService;
	@Autowired Gm_DeviceService gm_DeviceService;
	@Autowired Op_SceneService op_SceneService;
	@Autowired Op_DevCtrlTypeService op_DevCtrlTypeService;
	@Autowired Gm_DevCtrlOperateService gm_DevCtrlOperateService;
	@Autowired Op_DevCtrlBtnService op_DevCtrlBtnService;
	@Autowired Gm_DevCtrlStsService gm_DevCtrlStsService;
	@Autowired Op_UserInfo_SceneService op_UserInfo_SceneService;
	@Autowired Op_DevCtrlStsService opDevCtrlStsService;
	@Autowired Gm_DevChannelService gmDevChannelService;
	@Autowired Gm_ChannelService gm_ChannelService;
	
	private Gm_DevCtrl gm_DevCtrl=new Gm_DevCtrl();
	private String queryValue;
	private Page<Gm_DevCtrl> page=new Page<Gm_DevCtrl>();
	private int post;
	private String hid_condition;
	private String hid_value;
	private String id;
	private DevCtrlBtnForm devCtrlBtnForm;
	private DevCtrlStsForm devCtrlStsForm;
	private String q; //加载状态通道编号 
	



	public DevCtrlStsForm getDevCtrlStsForm() {
		return devCtrlStsForm;
	}


	public void setDevCtrlStsForm(DevCtrlStsForm devCtrlStsForm) {
		this.devCtrlStsForm = devCtrlStsForm;
	}

	private String devId;

	
	public String getDevId() {
		return devId;
	}


	public void setDevId(String devId) {
		this.devId = devId;
	}

	public Page<Gm_DevCtrl> getPage() {
		return page;
	}
	public void setPage(Page<Gm_DevCtrl> page) {
		this.page = page;
	}
	public Gm_DevCtrl getGm_DevCtrl() {
		return gm_DevCtrl;
	}
	public void setGm_DevCtrl(Gm_DevCtrl gm_DevCtrl) {
		this.gm_DevCtrl = gm_DevCtrl;
	}

	public int getPost() {
		return post;
	}
	public void setPost(int post) {
		this.post = post;
	}
	public String getQueryValue()
	{
		return queryValue;
	}
	public void setQueryValue(String queryValue)
	{
		this.queryValue = queryValue;
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


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public DevCtrlBtnForm getDevCtrlBtnForm() {
		return devCtrlBtnForm;
	}


	public void setDevCtrlBtnForm(DevCtrlBtnForm devCtrlBtnForm) {
		this.devCtrlBtnForm = devCtrlBtnForm;
	}


	public String getQ() {
		return q;
	}


	public void setQ(String q) {
		this.q = q;
	}



}
