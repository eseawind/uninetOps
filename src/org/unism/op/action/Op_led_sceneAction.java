package org.unism.op.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.unism.op.domain.Op_Scene;
import org.unism.op.domain.Op_led;
import org.unism.op.domain.Op_led_scene;
import org.unism.op.service.Op_SceneService;
import org.unism.op.service.Op_ledService;
import org.unism.op.service.Op_led_sceneService;
import org.wangzz.core.orm.Page;
import org.wangzz.core.search.Filter;
import org.wangzz.core.search.Search;
import org.wangzz.core.web.struts2.Struts2Utils;

import com.opensymphony.xwork2.ActionSupport;

public class Op_led_sceneAction extends ActionSupport {

	/**
	 * 分页
	 * @return
	 * @author Wang_Yuliang
	 */
	public String page(){
		Search search = new Search();
		Search _search = new Search();
		if(this.hid_condition!=null && !this.hid_condition.equals("")){
			if(hid_condition.equals("led_name")){
				List<Op_led> list = this.op_ledService.search(_search.addFilterILike(hid_condition, hid_value));
				Op_led op_led = new Op_led();
				op_led.setLed_id("-1");
				list.add(op_led);
				search.addFilterIn("led_id", list);
			}else if(hid_condition.equals("led_no")){
				List<Op_led> list = this.op_ledService.search(_search.addFilterILike(hid_condition, hid_value));
				Op_led op_led = new Op_led();
				op_led.setLed_id("-1");
				list.add(op_led);
				search.addFilterIn("led_id", list);
			}else if(hid_condition.equals("scene_name")){//这个不需要考虑是否和用户相关
				List<Op_Scene> list = this.op_SceneService.search(_search.addFilterILike(hid_condition, hid_value));
				Op_Scene op_Scene = new Op_Scene();
				op_Scene.setScene_id("-1");
				list.add(op_Scene);
				search.addFilterIn("scene_id", list);
			}
		}else{}
		search.addSortAsc("led_id");
		this.page = this.op_led_sceneService.search(page, search);		
		return "page";
	}

	/**
	 * 到添加
	 * @return
	 * @author Wang_Yuliang
	 */
	public String toSave(){
		led_list = this.op_ledService.findAll();
		List<Op_Scene> scene_list = (List<Op_Scene>)Struts2Utils.getSession().getAttribute("sceneList");
		String scene_id_list = "[";
		for(Op_Scene op_Scene : scene_list){
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
		return "save";
	}
	
	/**
	 * 验证LED场景关联信息是否已存在
	 * @return
	 * @author Wang_Yuliang
	 */
	public String exist(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			List<Op_led_scene> list = this.op_led_sceneService.search(new Search().addFilterAnd(Filter.equal("led_id.led_id", this.op_led_scene.getLed_id().getLed_id()), Filter.equal("scene_id.scene_id", this.op_led_scene.getScene_id().getScene_id())));
			if(list!=null && list.size()>0)
				out.print("1");
			else
				out.print("0");
		}catch(Exception ex){
			ex.printStackTrace();	
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("2");
			}catch(Exception exx){exx.printStackTrace();}	
		}	
		return null;
	}
	
	/**
	 * 验证LED场景关联信息是否已存在 更新时用
	 * @return
	 * @author Wang_Yuliang
	 */
	public String exist_edit(){
		HttpSession session = Struts2Utils.getSession();
		try {
			HttpServletResponse response = Struts2Utils.getResponse();
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			List<Op_led_scene> list = this.op_led_sceneService.search(new Search().addFilterAnd(Filter.notEqual("id", this.op_led_scene.getId()),Filter.and(Filter.equal("led_id.led_id", this.op_led_scene.getLed_id().getLed_id()), Filter.equal("scene_id.scene_id", this.op_led_scene.getScene_id().getScene_id()))));			
			if(list!=null && list.size()>0)
				out.print("1");
			else
				out.print("0");
		}catch(Exception ex){
			ex.printStackTrace();	
			try {
				HttpServletResponse response = Struts2Utils.getResponse();
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.print("2");
			}catch(Exception exx){exx.printStackTrace();}	
		}	
		return null;
	}
	
	/**
	 * 添加
	 * @return
	 * @author Wang_Yuliang
	 */
	public String save(){
		try{
			op_led_scene.setScene_gtype(this.op_SceneService.findById(op_led_scene.getScene_id().getScene_id()).getScene_gtype());
			this.op_led_sceneService.save(op_led_scene);
			addActionMessage("操作成功!");
        }catch (Exception e)  {
            e.printStackTrace();
            addActionMessage("操作失败!未知错误");
        }	
		return "operationResult";
	}
	
	/**
	 * 到编辑
	 * @return
	 * @author Wang_Yuliang
	 */
	public String toEdit(){
		op_led_scene = this.op_led_sceneService.findById(op_led_scene.getId());
		led_list = this.op_ledService.findAll();
		List<Op_Scene> scene_list = (List<Op_Scene>)Struts2Utils.getSession().getAttribute("sceneList");
		String scene_id_list = "[";
		for(Op_Scene op_Scene : scene_list){
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
		return "edit";
	}
	
	/**
	 * 编辑
	 * @return
	 * @author Wang_Yuliang
	 */
	public String edit(){
		try {
			op_led_scene.setScene_gtype(this.op_SceneService.findById(op_led_scene.getScene_id().getScene_id()).getScene_gtype());
			this.op_led_sceneService.update(op_led_scene);
			addActionMessage("操作成功!");
        }catch (Exception e)  {
        	addActionMessage("操作失败!未知错误");
            e.printStackTrace();
        }
        return "operationResult";	
	}
	
	/**
	 * 删除
	 * @return
	 * @author Wang_Yuliang
	 */
	public String delete(){
		try {
			this.op_led_sceneService.deleteById(op_led_scene.getId());
			addActionMessage("操作成功!");
        }catch (Exception e)  {
        	addActionMessage("操作失败!未知错误");
            e.printStackTrace();
        }
        return "operationResult";
	}
//分隔线------------------------------------------------------------------------------------------------------------------
	@Autowired Op_led_sceneService op_led_sceneService;
	@Autowired Op_ledService op_ledService;
	@Autowired Op_SceneService op_SceneService;
	
	private Op_led_scene op_led_scene = new Op_led_scene();
	private Page<Op_led_scene> page = new Page<Op_led_scene>();
    private String hid_condition;
    private String hid_value;
    private List<Op_led> led_list = new ArrayList<Op_led>();
    
	public Op_led_scene getOp_led_scene() {
		return op_led_scene;
	}
	public void setOp_led_scene(Op_led_scene op_led_scene) {
		this.op_led_scene = op_led_scene;
	}
	public Page<Op_led_scene> getPage() {
		return page;
	}
	public void setPage(Page<Op_led_scene> page) {
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

	public List<Op_led> getLed_list() {
		return led_list;
	}

	public void setLed_list(List<Op_led> led_list) {
		this.led_list = led_list;
	}

}
